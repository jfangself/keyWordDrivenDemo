package cn.gloryroad.util;

import org.testng.annotations.Test;

import org.testng.Assert;

import org.testng.annotations.BeforeMethod;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import static cn.gloryroad.util.KeyBoardUtil.*;
import static cn.gloryroad.util.WaitUitl.*;

import java.util.List;

public class baidu {
	WebDriver driver;
	String baseUrl;

	@Test
	public void testSendMailWithAttachment() {
		// 访问被测试网站mail.126.com
		driver.get("http://www.baidu.com");
		// 定位126登录的用户名密码
//		WebElement userName = driver.findElement(By.id("idInput"));
//		WebElement passWord = driver.findElement(By.id("pwdInput"));
//		WebElement loginButton = driver.findElement(By.id("loginBtn"));
//		userName.clear();
//		userName.sendKeys("joey_fang01@163.com");
//		passWord.clear();
//		passWord.sendKeys("Changeme_123");
//		loginButton.click();
		// 调用封装的显示等待，在也买年显示退出连接后，继续执行后续代码
/*		
		 * WebElement userName = driver.findElement(By
		 * .xpath("//*[@id='textfield-1018-inputEl']"));
		 */
		sleep(10000);
		waitWebElement(driver, "//*[@id='u1']/a[7]");
		// 定位页面写信连接 .//*[@id='_mail_component_61_61']/span[2]  //*[@id='_mail_component_61_61']/span[2]
		WebElement writeMailLink = driver.findElement(By.xpath("//*[@id='u1']/a[7]"));
		// .findElement(By.xpath("//*[contains(@id,'_mail_component_61_61')]/span[contains(.,'写信')]"));
		writeMailLink.click();
		// 调用封装的显示等待函数，在页面显示收信人后，执行代码
		waitWebElement(driver, "//a[contains(.,'收件人')]");
		// 定位写信页面的收件人输入框
		WebElement recipients = driver.findElement(By.xpath("//*[contains(@id,'_mail_emailinput_0_')]/input"));
		// 定位写信页面的邮件主题输入狂
		WebElement mailSubject = driver.findElement(By.xpath("//*[contains(@id,'_mail_input_2')]/input"));
		// 在收件人输入
		recipients.sendKeys("ylylfang@126.com");
		mailSubject.sendKeys("test send to self with selenium");
		/*
		 * 调用keyboardutil的presstabkey方法，自动tab 之后自动页面的输入焦点切换到正文
		 */
		pressTabKey();
		/*
		 * 调用keyboardutil中的setandctrlvclipboarddata方法 模拟剪切板粘贴操作
		 */
		setAndctrlVClipboardData("this is testmail send by selenium setandctrlvclipboarddata");
		// 定位添加福建的链接
		driver.findElement(By.xpath("//a[contains(@id,'_attachAdd')]")).click();
		// 调用waitutil中的sleep休眠0.5s,等待弹出文件选择的windows窗口
		sleep(500);
		/*
		 * 调用keyboardutil中的setandctrlvclipboarddata方法
		 * 将上传文件的绝对路径字符粘贴到问及爱你选择框体中的问文件名输入框
		 */
		setAndctrlVClipboardData("c:\\a.log");
		pressEnterKey();
		// 调用waitutil中的sleep方法休眠4s,等待上传完毕
		sleep(4000);
		/*
		 * 定位页面上的两个发送按钮，存储到list容器中页面 由于两个发送按钮在页面中属性基本相同，所以很难只定位到唯一的发送按钮
		 * 所以使用发送按钮的文字属性将两个发送按钮同时定位，存储到list 然后调用容器中的其中一个按钮，定位唯一一个发送按钮
		 */
		List<WebElement> buttons = driver
				.findElements(By.xpath("//a[contains(@id,'_mail_button_')]/span[contains(.,'发送')]"));
		buttons.get(1).click();
		/* 调用显示等待，在页面显示出包含关键字”succinfo“的ID属性后，继续执行 */
		waitWebElement(driver, "//*[contains(@id,'_succInfo')]");
		Assert.assertTrue(driver.getPageSource().contains("发送成功"));

	}

	@BeforeMethod
	public void beforeMethod() {
		driver = new FirefoxDriver();
	}

	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}

}

