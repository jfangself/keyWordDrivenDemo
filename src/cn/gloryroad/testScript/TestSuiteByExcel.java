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
	public static boolean testResult;

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
					Log.info("��excel�ļ���ȡ���Ĺؼ����ǣ�" + keyword);
					value = ExcelUtil.getCellData(Constants.Sheet_TestSteps, testStep, Constants.Col_ActionValue);
					Log.info("��excel�ļ���ȡ�Ĳ���ֵ�ǣ�" + value);
					execute_Actions();
					if (testResult == false) {
						// ��������������κ�һ�����Բ���ִ��ʧ�ܣ��������������sheet�еĵ�ǰִ�в���������ִ�н���趨Ϊ������ִ��ʧ�ܡ�
						ExcelUtil.setCellData("������������", testCaseNo, Constants.Col_TestSuiteTestResult, "����ִ��ʧ��");
					}
				}
				// ����־�д�ӡ��������ִ�����
				Log.endTestCase(testCaseID);
				// ��ǰ������������ִ��ʧ�ܵĲ��裬���������������趨Ϊʧ��״̬��break���������ǰforѭ��������ִ�в��Լ����е���һ����������
				break;
			}
			if (testResult == true) {
				ExcelUtil.setCellData(Constants.Sheet_TestSuite, testCaseNo, Constants.Col_TestSuiteTestResult,
						"����ִ�гɹ�");
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
					if (method[i].getName().equals(keyword)) {
						method[i].invoke(keyWordsaction, value);
						if (testResult == true) {
							// ��ǰ���Բ���ִ�гɹ����ڡ������ʼ���sheet�У��Ὣ��ǰִ�еĲ��Բ������趨Ϊ�����Բ���ִ�гɹ���
							ExcelUtil.setCellData(Constants.Sheet_TestSteps, testStep, Constants.Col_TestStepTestResult,
									"���Բ���ִ�гɹ�");
							break;
						} else {
							ExcelUtil.setCellData(Constants.Sheet_TestSteps, testStep, Constants.Col_TestStepTestResult,
									"���Բ���ִ��ʧ��");
							// ���Բ���ִ��ʧ�ܣ���ֱ�ӹر������������ִ�к����Ĳ��Բ���
							KeyWordsAction.close_browser("");
							break;
						}
					}

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
