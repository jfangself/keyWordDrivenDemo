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
		// ����һ���ؼ��������ʵ��
		keyWordsaction = new KeyWordsAction();
		// ʹ��java�ķ�����ƻ�ȡKeyWordsaction������з�������
		method = keyWordsaction.getClass().getMethods();

		// ����excel�ؼ��ļ���·��
		String excelFilePath = Constants.Path_ExcelFile;
		// �趨��ȡexcel�еġ������ʼ���sheetΪ����Ŀ��
		ExcelUtil.setExcelFile(excelFilePath, Constants.Sheet_TestSteps);

		/*
		 * ��excel�ļ��ġ������ʼ���sheet�У���ÿһ�еĵ����ж�ȡ������Ϊ�ؼ�����Ϣ��ͨ�������Ƚϵķ�����
		 * ִ�йؼ�����keywordsAction���ж�Ӧ��ӳ�䷽������excel�ļ��� �������ʼ���sheet��
		 * ����ÿһ�еĵ����ж�ȡ������Ϊӳ�䷽���ĺ�������������execute_actions�������ӳ�䷽���ĵ���ִ�й���
		 */

		for (int iRow = 1; iRow <= ExcelUtil.getLastRowNum(); iRow++) {
			// ��ȡexcel�ļ�sheet�еĵ�����
			keyword = ExcelUtil.getCellData(Constants.Sheet_TestSteps, iRow, Constants.Col_keyWordAction);
			// ��ȡexcel�ļ��еĵ�����
			value = ExcelUtil.getCellData(Constants.Sheet_TestSteps, iRow, Constants.Col_ActionValue);
			execute_Actions();

		}
	}

	private static void execute_Actions() {
		try {
			for (int i = 0; i < method.length; i++) {
				// ͨ���������жϹؼ��ֺ�keywordsaction���е��ĸ���������һֱ
				if (method[i].getName().equals(keyword)) {
					// �ҵ�keywordsaction���е�ӳ�䷽����ͨ������invoke������ɺ�������
					method[i].invoke(keyWordsaction, value);
					break;
				}
			}
		} catch (Exception e) {
			// ִ���г���һ�����򽫲��������趨Ϊʧ��״̬
			Assert.fail("ִ���г����쳣����������ִ��ʧ��");
		}

	}
}
