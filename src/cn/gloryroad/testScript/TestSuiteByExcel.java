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
		// ����һ���ؼ��������ʵ��
		keyWordsaction = new KeyWordsAction();
		// ʹ��java�ķ�����ƻ�ȡkeywordsaction������з�������
		method = keyWordsaction.getClass().getMethods();

		// ����excel�ؼ��ļ���·��
		String excelFilePath = Constants.Path_ExcelFile;
		// �趨��ȡ excel�ļ��еġ������ʼ���sheetΪ����Ŀ��
		ExcelUtil.setExcelFile(excelFilePath);
		// ��ȡ�������������ϡ�Sheet�еĲ�����������

		int testCasesCount = ExcelUtil.getRowCount(Constants.Sheet_TestSuite);
		// ʹ��forѭ����ִ�����б��ΪY�Ĳ�������

		for (int testCaseNo = 1; testCaseNo <= testCasesCount; testCaseNo++) {
			// ��ȡ�������������ϡ�sheet�е�ÿ�еĲ����������
			testCaseID = ExcelUtil.getCellData(Constants.Sheet_TestSuite, testCaseNo, Constants.Col_TestCaseID);
			// ��ȡ�������������ϡ�sheet�е�ÿ���Ƿ����������е�ֵ
			testCaseRunFlag = ExcelUtil.getCellData(Constants.Sheet_TestSuite, testCaseNo, Constants.Col_RunFlag);
			// ����Ƿ������е�ֵΪy����ִ�������е����в���
			if (testCaseRunFlag.equalsIgnoreCase("y")) {
				Log.startTestCase(testCaseID);
				// �ڡ������ʼ���sheet�У���ȡ��ǰҪִ�������ĵ�һ�����������к�
				testStep = ExcelUtil.getFirstRowContainsTestCaseID(Constants.Sheet_TestSteps, testCaseID,
						Constants.Col_TestCaseID);
				// �ڷ����ʼ�sheet�У���ȡ��ǰҪִ�еĲ������������һ�������������к�
				testLastStep = ExcelUtil.getTestCaseLastStepRow(Constants.Sheet_TestSteps, testCaseID, testStep);
				// �������������е����в��Բ���
				for (; testStep < testLastStep; testStep++) {
					// �ӡ������ʼ���sheet�ж�ȡ�ؼ��ӺͲ���ֵ������execute_Actions����
					keyword = ExcelUtil.getCellData(Constants.Sheet_TestSteps, testStep, Constants.Col_keyWordAction);
					value = ExcelUtil.getCellData(Constants.Sheet_TestSteps, testStep, Constants.Col_ActionValue);
					execute_Actions();
				}
				// ����־�д�ӡ��������ִ�����
				Log.endTestCase(testCaseID);
			}
		}
	}

	private static void execute_Actions() {
		try {
			for (int i = 0; i < method.length; i++) {
				/*
				 * ʹ�÷���ķ�ʽ���ҵ��ؼ��ֶ�Ӧ�Ĳ��Է�������ʹ��value(����ֵ) ��Ϊ���Է����ĺ���ֵ���е���
				 */
				if (method[i].getName().equals(keyword)) {
					method[i].invoke(keyWordsaction, value);
					break;
				}
			}
		} catch (Exception e) {
			// ���ò��Է��������У��������쳣���򽫲����趨Ϊʧ��״̬��ֹͣ��������ִ��
			Assert.fail("ִ�г����쳣����������ִ��ʧ��");
		}
	}

	@BeforeClass
	public void BeforeClass() {
		// ����Log4j�������ļ�Ϊlog4j.xml
		DOMConfigurator.configure("log4j.xml");
	}
}
