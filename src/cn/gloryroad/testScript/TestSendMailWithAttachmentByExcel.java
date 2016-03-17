package cn.gloryroad.testScript;

import java.lang.reflect.Method;
import org.testng.annotations.Test;
import org.testng.Assert;
import cn.gloryroad.configuration.*;
import cn.gloryroad.util.*;

public class TestSendMailWithAttachmentByExcel {
	public static Method method[];
	public static String keyword;
	public static String value;
	public static KeyWordsAction keyWordsaction;

	@Test

	public void testSendMailWithAttachment() throws Exception {
		// 声明一个关键动作类的实例
		keyWordsaction = new KeyWordsAction();
		// 使用java的反射机制获取KeyWordsaction类的所有方法对象
		method = keyWordsaction.getClass().getMethods();

		// 定义excel关键文件的路径
		String excelFilePath = Constants.Path_ExcelFile;
		// 设定读取excel中的“发送邮件”sheet为操作目标
		ExcelUtil.setExcelFile(excelFilePath, Constants.Sheet_TestSteps);

		/*
		 * 从excel文件的“发送邮件”sheet中，将每一行的第四列读取出来作为关键字信息，通过遍历比较的方法，
		 * 执行关键字在keywordsAction类中对应的映射方法，从excel文件的 “发送邮件”sheet中
		 * ，将每一行的第五列读取出来作为映射方法的函数参数，调用execute_actions函数完成映射方法的调用执行过程
		 */

		for (int iRow = 1; iRow <= ExcelUtil.getLastRowNum(); iRow++) {
			// 读取excel文件sheet中的第四列
			keyword = ExcelUtil.getCellData(Constants.Sheet_TestSteps, iRow, Constants.Col_keyWordAction);
			// 读取excel文件中的第五列
			value = ExcelUtil.getCellData(Constants.Sheet_TestSteps, iRow, Constants.Col_ActionValue);
			execute_Actions();

		}
	}

	private static void execute_Actions() {
		try {
			for (int i = 0; i < method.length; i++) {
				// 通过遍历，判断关键字和keywordsaction类中的哪个方法名称一直
				if (method[i].getName().equals(keyword)) {
					// 找到keywordsaction类中的映射方法后，通过调用invoke方法完成函数调用
					method[i].invoke(keyWordsaction, value);
					break;
				}
			}
		} catch (Exception e) {
			// 执行中出现一场，则将测试用例设定为失败状态
			Assert.fail("执行中出现异常，测试用例执行失败");
		}

	}
}
