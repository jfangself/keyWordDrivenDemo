package cn.gloryroad.testScript;

import org.apache.log4j.xml.DOMConfigurator;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.lang.reflect.Method;
import cn.gloryroad.configuration.*;
import cn.gloryroad.util.*;
import org.junit.BeforeClass;

public class TestSuiteByExcel {
	public static Method method[];
	public static String keyword;
	public static String value;
	public static KeyWordsAction keyWordsaction;
	public static int testStep;
	public static int testLastStep;
	public static String testCaseID;
	public static String testCaseRunFlag;
	public static Boolean testResult;

	@Test
	public void testTestSuite() throws Exception {
		// 声明一个关键动作类的实例
		keyWordsaction = new KeyWordsAction();
		// 使用java的反射机制获取keywordsaction类的所有方法对象
		method = keyWordsaction.getClass().getMethods();

		// 定义excel关键文件的路径
		String excelFilePath = Constants.Path_ExcelFile;
		// 设定读取 excel文件中的“发送邮件”sheet为操作目标
		ExcelUtil.setExcelFile(excelFilePath);
		// 读取“测试用例集合”Sheet中的测试用例总数

		int testCasesCount = ExcelUtil.getRowCount(Constants.Sheet_TestSuite);
		// 使用for循环，执行所有标记为Y的测试用例

		for (int testCaseNo = 1; testCaseNo <= testCasesCount; testCaseNo++) {
			// 读取“测试用例集合”sheet中的每行的测试用例序号
			testCaseID = ExcelUtil.getCellData(Constants.Sheet_TestSuite, testCaseNo, Constants.Col_TestCaseID);
			// 读取“测试用例集合”sheet中的每行是否运行行列中的值
			testCaseRunFlag = ExcelUtil.getCellData(Constants.Sheet_TestSuite, testCaseNo, Constants.Col_RunFlag);
			// 如果是否运行中的值为y，则执行用例中的所有步骤
			if (testCaseRunFlag.equalsIgnoreCase("y")) {
				Log.startTestCase(testCaseID);
				// 在“发送邮件”sheet中，获取当前要执行用例的第一个步骤所在行号
				testStep = ExcelUtil.getFirstRowContainsTestCaseID(Constants.Sheet_TestSteps, testCaseID,
						Constants.Col_TestCaseID);
				// 在发送邮件sheet中，获取当前要执行的测试用例的最后一个步骤所在行行号
				testLastStep = ExcelUtil.getTestCaseLastStepRow(Constants.Sheet_TestSteps, testCaseID, testStep);
				// 遍历测试用例中的所有测试步骤
				for (; testStep < testLastStep; testStep++) {
					// 从“发送邮件”sheet中读取关键子和操作值，调用execute_Actions方法
					keyword = ExcelUtil.getCellData(Constants.Sheet_TestSteps, testStep, Constants.Col_keyWordAction);
					value = ExcelUtil.getCellData(Constants.Sheet_TestSteps, testStep, Constants.Col_ActionValue);
					execute_Actions();
				}
				// 在日志中打印测试用例执行完毕
				Log.endTestCase(testCaseID);
			}
		}
	}

	private static void execute_Actions() {
		try {
			for (int i = 0; i < method.length; i++) {
				/*
				 * 使用反射的方式，找到关键字对应的测试方法，并使用value(操作值) 作为测试方法的函数值进行调用
				 */
				if (method[i].getName().equals(keyword)) {
					method[i].invoke(keyWordsaction, value);
					break;
				}
			}
		} catch (Exception e) {
			// 调用测试方法过程中，若出现异常，则将测试设定为失败状态，停止测试用例执行
			Assert.fail("执行出现异常，测试用例执行失败");
		}
	}

	@BeforeClass
	public void BeforeClass() {
		// 配置Log4j的配置文件为log4j.xml
		DOMConfigurator.configure("log4j.xml");
	}
}
