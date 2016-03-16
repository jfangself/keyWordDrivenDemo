package cn.gloryroad.configuration;

import static cn.gloryroad.util.WaitUitl.waitWebElement;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;

import java.util.List;

import cn.gloryroad.util.KeyBoardUtil;
import cn.gloryroad.util.ObjectMap;
import cn.gloryroad.util.WaitUitl;

public class KeyWordsAction {
	// ������̬webdriver���������ڴ��������driver�Ĳ���
	public static WebDriver driver;
	// �����洢��λ��������ļ���objectmap����
	private static ObjectMap objectMap = new ObjectMap(
			"D://ECLIPSE/GitHub//Demo//keyWordDrivenDem//objectMap.properties");

	/*
	 * �˷��������ƶ�Ӧexcel�ļ����ؼ��֡����е�open_browser�ؼ���
	 * excel�ļ�������ֵ�����е���������ָ�����������ú�����������в���������ie��ʾ����IE��������в���������firefox��ʾ��������������
	 * chrome��ʾ����chrome���в���
	 */
	public static void open_browser(String browserName) {
		if (browserName.equals("ie")) {
			System.setProperty("webdriver.ie.driver", "C:\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		} else if (browserName.equals("firefox")) {
			driver = new FirefoxDriver();
		} else {
			System.setProperty("Webdriver.chrome.driver", "C:\\chromedriver.exe");
			driver = new ChromeDriver();
		}

	}

	// �˷��������ƶ�Ӧexcel�ļ��ؼ������е�navigate�ؼ���
	// ��ȡexcel������ֵ���е���ַ������Ϊ��������ʵ���ַ
	public static void navigate(String url) {
		driver.get(url);
	}

	// �˷��������ƶ�Ӧexcel�ļ����ؼ��֡����е�input_username�ؼ���
	// ��ȡexcel������ֵ���е������û����ƣ���Ϊ��¼�û�������������
	public static void input_userName(String userName) {
		System.out.println("�յ��Ĳ���ֵ��" + userName);
		try {
			driver.findElement(objectMap.getLocator("login.username")).clear();
			driver.findElement(objectMap.getLocator("login.username")).sendKeys(userName);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// �˷��������ƶ�Ӧexcel�ļ����ؼ��֡����е�input_passWord�ؼ���
	// ��ȡexcel������ֵ���е��������룬��Ϊ��¼�������������
	public static void input_passWord(String password) throws Exception {
		try {
			driver.findElement(objectMap.getLocator("login.password")).clear();
			driver.findElement(objectMap.getLocator("login.password")).sendKeys(password);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// �˷��������ƶ�Ӧexcel�ļ����ؼ��֡����е�click_login�ؼ���
	// ʵ�ֵ�¼������ť������string��������Ϊ����������ֵ���趨
	// һ�����õĺ�����������Ϊ��ͳһ���䷽���ĵ��÷�ʽ��������һ��������
	public static void click_login(String string) {
		try {
			driver.findElement(objectMap.getLocator("login.button")).click();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// �˷��������ƶ�Ӧexcel�ļ����ؼ��֡����е�WaitFor_element�ؼ���
	// ������ʾ�ȴ�ҳ��Ԫ�س�����ҳ���У�������ȡexcel�ļ�������ֵ�����еı��ʽ��Ϊ����������objectMap�����getLocator���������
	// ��������ֵ�������ļ��в���keyֵ��Ӧ�Ķ�λ���ʽ
	public static void WaitFor_Element(String xpathExpression) {
		try {
			// ���÷�װ��waitWebElement������ʽ�ȴ�ҳ��Ԫ���Ƿ����
			waitWebElement(driver, objectMap.getLocator(xpathExpression));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// �˷��������ƶ�Ӧexcel�ļ����ؼ��֡�����Input_recipients�ؼ���
	// �������ռ��������������ָ�����ռ�����Ϣ����������recipientsΪ�ռ�����Ϣ
	public static void input_recipients(String recipients) {
		try {
			driver.findElement(objectMap.getLocator("writemailpage.recipients")).sendKeys(recipients);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// �˷������ƶ�Ӧ���ؼ��֡����е�press_tab�ؼ��֣����ڰ�tab
	public static void press_Tab(String string) {
		try {
			Thread.sleep(2000);
			// ����KeyBoardUtil��ķ�װ����pressTabKey
			KeyBoardUtil.pressTabKey();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// �˷�����Ӧpaste_mailcontent�ؼ���
	// ͨ���Ӽ��а�ճ���ķ�ʽ����ָ��������������ַ������磬�ʼ�����
	public static void paster_mailContent(String mailContent) {
		try {
			KeyBoardUtil.setAndctrlVClipboardData(mailContent);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// �˷�����Ӧclick_addachment�ؼ��֣����ڵ�����Ӹ����İ�ť
	public static void click_addAttachment(String string) {
		try {
			driver.findElement(objectMap.getLocator("writemailpage.attachmentlink")).click();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// �˷������ƶ�Ӧpaste_uploadFileName
	// ͨ���Ӽ��а�ճ���ķ�ʽ�����ļ��ϴ�������ļ��������������Ҫ�ϴ����ļ�·��������
	public static void paste_uploadFileName(String uploadFileName) {
		try {
			KeyBoardUtil.setAndctrlVClipboardData(uploadFileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// �˷�����Ӧpress_enter�����ڰ�enter
	public static void press_enter(String string) {
		try {
			KeyBoardUtil.pressEnterKey();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// �˷�����Ӫsleep,���ڵȴ�������ͣ���룬�����Ĳ����Ժ���Ϊ��λ
	public static void sleep(String sleepTime) {
		try {
			WaitUitl.sleep(Integer.parseInt(sleepTime));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// �˷���click_sendMailButton�����ڵ������Ͱ�ť
	public static void click_sendMailButton(String string) {
		try {
			// ҳ�������������Ͱ�ť����ִ�з��͹��ܣ�Ϊ��ʹ��xpathƥ�䷽�㣬ͬʱƥ�����������Ͱ�ť�����洢��list�У����ȡ��һ����ť��������ɵ��۷����ʼ��Ĳ���
			List<WebElement> buttons = driver.findElements(objectMap.getLocator("writemailpage.sendmailbuttons"));
			buttons.get(0).click();
			System.out.println("���Ͱ�ť���ɹ�����");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// �˷�����Ӧexcel��assert_string��������ɶ��Բ�������������Ϊ���Ե���������
	public static void Assert_String(String assertString) {
		try {
			Assert.assertTrue(driver.getPageSource().contains(assertString));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// �˷�������close_browser
	public static void close_browser(String string) {
		try {
			System.out.println("������رպ�����ִ��");
			driver.quit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
