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
		// ���ʱ�������վmail.126.com
		driver.get("http://www.baidu.com");
		// ��λ126��¼���û�������
//		WebElement userName = driver.findElement(By.id("idInput"));
//		WebElement passWord = driver.findElement(By.id("pwdInput"));
//		WebElement loginButton = driver.findElement(By.id("loginBtn"));
//		userName.clear();
//		userName.sendKeys("joey_fang01@163.com");
//		passWord.clear();
//		passWord.sendKeys("Changeme_123");
//		loginButton.click();
		// ���÷�װ����ʾ�ȴ�����Ҳ������ʾ�˳����Ӻ󣬼���ִ�к�������
/*		
		 * WebElement userName = driver.findElement(By
		 * .xpath("//*[@id='textfield-1018-inputEl']"));
		 */
		sleep(10000);
		waitWebElement(driver, "//*[@id='u1']/a[7]");
		// ��λҳ��д������ .//*[@id='_mail_component_61_61']/span[2]  //*[@id='_mail_component_61_61']/span[2]
		WebElement writeMailLink = driver.findElement(By.xpath("//*[@id='u1']/a[7]"));
		// .findElement(By.xpath("//*[contains(@id,'_mail_component_61_61')]/span[contains(.,'д��')]"));
		writeMailLink.click();
		// ���÷�װ����ʾ�ȴ���������ҳ����ʾ�����˺�ִ�д���
		waitWebElement(driver, "//a[contains(.,'�ռ���')]");
		// ��λд��ҳ����ռ��������
		WebElement recipients = driver.findElement(By.xpath("//*[contains(@id,'_mail_emailinput_0_')]/input"));
		// ��λд��ҳ����ʼ����������
		WebElement mailSubject = driver.findElement(By.xpath("//*[contains(@id,'_mail_input_2')]/input"));
		// ���ռ�������
		recipients.sendKeys("ylylfang@126.com");
		mailSubject.sendKeys("test send to self with selenium");
		/*
		 * ����keyboardutil��presstabkey�������Զ�tab ֮���Զ�ҳ������뽹���л�������
		 */
		pressTabKey();
		/*
		 * ����keyboardutil�е�setandctrlvclipboarddata���� ģ����а�ճ������
		 */
		setAndctrlVClipboardData("this is testmail send by selenium setandctrlvclipboarddata");
		// ��λ��Ӹ���������
		driver.findElement(By.xpath("//a[contains(@id,'_attachAdd')]")).click();
		// ����waitutil�е�sleep����0.5s,�ȴ������ļ�ѡ���windows����
		sleep(500);
		/*
		 * ����keyboardutil�е�setandctrlvclipboarddata����
		 * ���ϴ��ļ��ľ���·���ַ�ճ�����ʼ�����ѡ������е����ļ��������
		 */
		setAndctrlVClipboardData("c:\\a.log");
		pressEnterKey();
		// ����waitutil�е�sleep��������4s,�ȴ��ϴ����
		sleep(4000);
		/*
		 * ��λҳ���ϵ��������Ͱ�ť���洢��list������ҳ�� �����������Ͱ�ť��ҳ�������Ի�����ͬ�����Ժ���ֻ��λ��Ψһ�ķ��Ͱ�ť
		 * ����ʹ�÷��Ͱ�ť���������Խ��������Ͱ�ťͬʱ��λ���洢��list Ȼ����������е�����һ����ť����λΨһһ�����Ͱ�ť
		 */
		List<WebElement> buttons = driver
				.findElements(By.xpath("//a[contains(@id,'_mail_button_')]/span[contains(.,'����')]"));
		buttons.get(1).click();
		/* ������ʾ�ȴ�����ҳ����ʾ�������ؼ��֡�succinfo����ID���Ժ󣬼���ִ�� */
		waitWebElement(driver, "//*[contains(@id,'_succInfo')]");
		Assert.assertTrue(driver.getPageSource().contains("���ͳɹ�"));

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

