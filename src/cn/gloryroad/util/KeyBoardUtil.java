package cn.gloryroad.util;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

public class KeyBoardUtil {

	// ��tab�ķ�װ����
	public static void pressTabKey() {
		Robot robot = null;
		try {
			robot = new Robot();

		} catch (AWTException e) {
			e.printStackTrace();
		}
		// ����keyPressʵ�ְ���tab
		robot.keyPress(KeyEvent.VK_TAB);
		// ����keyReleaseʵ���ͷ�tab
		robot.keyRelease(KeyEvent.VK_TAB);
	}

	// ��enter���ķ�װ����
	public static void pressEnterKey() {
		Robot robot = null;
		try {
			robot = new Robot();

		} catch (AWTException e) {
			e.printStackTrace();
		}
		// ����keypressʵ��enter��
		robot.keyPress(KeyEvent.VK_ENTER);
		// ����keyReleaseʵ���ͷ�enter
		robot.keyRelease(KeyEvent.VK_ENTER);

	}

	/*
	 * ���ƶ��ַ�����Ϊ���а�����ݣ�Ȼ��ִ��ճ������ ��ҳ�潹���л��������󣬵��ô˺������ƶ��ַ���ճ�����������
	 */

	public static void setAndctrlVClipboardData(String string) {
		StringSelection stringSelection = new StringSelection(string);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
		Robot robot = null;
		try {
			robot = new Robot();

		} catch (AWTException e1) {
			e1.printStackTrace();
		}
		// ����4�б�ʾ���º��ͷ�ctrl+v
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
	}

}
