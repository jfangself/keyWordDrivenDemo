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
	// 声明静态webdriver对象，用于在此类中相关driver的操作
	public static WebDriver driver;
	// 声明存储定位表达配置文件的objectmap对象
	private static ObjectMap objectMap = new ObjectMap(
			"D://ECLIPSE/GitHub//Demo//keyWordDrivenDem//objectMap.properties");

	/*
	 * 此方法的名称对应excel文件“关键字”列中的open_browser关键字
	 * excel文件“操作值”列中的内容用于指定测试用例用何种浏览器运行测试用例。ie表示启动IE浏览器运行测试用例，firefox表示启动火狐浏览器，
	 * chrome表示启动chrome进行测试
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

	// 此方法的名称对应excel文件关键子列中的navigate关键子
	// 读取excel“操作值”中的网址内容作为浏览器访问的网址
	public static void navigate(String url) {
		driver.get(url);
	}

	// 此方法的名称对应excel文件“关键字”列中的input_username关键字
	// 读取excel“操作值”中的邮箱用户名称，作为登录用户名的输入内容
	public static void input_userName(String userName) {
		System.out.println("收到的参数值：" + userName);
		try {
			driver.findElement(objectMap.getLocator("login.username")).clear();
			driver.findElement(objectMap.getLocator("login.username")).sendKeys(userName);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 此方法的名称对应excel文件“关键字”列中的input_passWord关键字
	// 读取excel“操作值”中的邮箱密码，作为登录密码的输入内容
	public static void input_passWord(String password) throws Exception {
		try {
			driver.findElement(objectMap.getLocator("login.password")).clear();
			driver.findElement(objectMap.getLocator("login.password")).sendKeys(password);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 此方法的名称对应excel文件“关键字”列中的click_login关键字
	// 实现登录操作按钮，参数string本身不会作为操作的输入值，设定
	// 一个无用的函数参数仅仅为了统一反射方法的调用方式（均传入一个参数）
	public static void click_login(String string) {
		try {
			driver.findElement(objectMap.getLocator("login.button")).click();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 此方法的名称对应excel文件“关键字”列中的WaitFor_element关键字
	// 用于显示等待页面元素出现在页面中，函数读取excel文件“操作值”列中的表达式作为函数参数，objectMap对象的getLocator方法会根据
	// 函数参数值在配置文件中查找key值对应的定位表达式
	public static void WaitFor_Element(String xpathExpression) {
		try {
			// 调用封装的waitWebElement函数显式等待页面元素是否出现
			waitWebElement(driver, objectMap.getLocator(xpathExpression));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 此方法的名称对应excel文件“关键字”列中Input_recipients关键字
	// 用于在收件人输入框中输入指定的收件人信息，函数参数recipients为收件人信息
	public static void input_recipients(String recipients) {
		try {
			driver.findElement(objectMap.getLocator("writemailpage.recipients")).sendKeys(recipients);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 此方法名称对应“关键字”列中的press_tab关键字，用于按tab
	public static void press_Tab(String string) {
		try {
			Thread.sleep(2000);
			// 调用KeyBoardUtil类的封装发发pressTabKey
			KeyBoardUtil.pressTabKey();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 此方法对应paste_mailcontent关键字
	// 通过从剪切板粘贴的方式，在指定输入框在输入字符，例如，邮件正文
	public static void paster_mailContent(String mailContent) {
		try {
			KeyBoardUtil.setAndctrlVClipboardData(mailContent);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 此方法对应click_addachment关键字，用于单机添加附件的按钮
	public static void click_addAttachment(String string) {
		try {
			driver.findElement(objectMap.getLocator("writemailpage.attachmentlink")).click();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 此方法名称对应paste_uploadFileName
	// 通过从剪切板粘贴的方式，在文件上传框体的文件名输入框中输入要上传的文件路径和名称
	public static void paste_uploadFileName(String uploadFileName) {
		try {
			KeyBoardUtil.setAndctrlVClipboardData(uploadFileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 此方法对应press_enter，用于按enter
	public static void press_enter(String string) {
		try {
			KeyBoardUtil.pressEnterKey();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 此方法敌营sleep,用于等待操作暂停几秒，函数的参数以毫秒为单位
	public static void sleep(String sleepTime) {
		try {
			WaitUitl.sleep(Integer.parseInt(sleepTime));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 此方法click_sendMailButton，用于单机发送按钮
	public static void click_sendMailButton(String string) {
		try {
			// 页面上有两个发送按钮可以执行发送功能，为了使用xpath匹配方便，同时匹配了两个发送按钮，并存储在list中，随便取出一个按钮对象来完成单价发送邮件的操作
			List<WebElement> buttons = driver.findElements(objectMap.getLocator("writemailpage.sendmailbuttons"));
			buttons.get(0).click();
			System.out.println("发送按钮被成功单击");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 此方法对应excel中assert_string，用于完成断言操作，函数参数为断言的文字内容
	public static void Assert_String(String assertString) {
		try {
			Assert.assertTrue(driver.getPageSource().contains(assertString));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 此方法用于close_browser
	public static void close_browser(String string) {
		try {
			System.out.println("浏览器关闭函数被执行");
			driver.quit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
