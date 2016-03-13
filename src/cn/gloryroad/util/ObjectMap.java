package cn.gloryroad.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;

public class ObjectMap {
	Properties properties;

	public ObjectMap(String propFile) {
		properties = new Properties();
		try {
			FileInputStream in = new FileInputStream(propFile);
			properties.load(in);
			in.close();
		} catch (IOException e) {
			System.out.println("��ȡ�����ļ�����");
			e.printStackTrace();
		}

	}

	public By getLocator(String ElementNameInpropFile) throws Exception {
		// ���ݱ���elementnameinpropfile,�����������ļ��ж�ȡ��Ӧ�����ö���
		String locator = properties.getProperty(ElementNameInpropFile);
		// �����ö����еĶ�λ���ʹ洢��locatorType�����У�����λ���ʽ��ֵ�洢��locatorvalue������
		String locatorType = locator.split(">")[0];
		String locatorValue = locator.split(">")[1];
		locatorValue = new String(locatorValue.getBytes("ISO-8859-1"), "UTF-8");
		// ���locatorType��locatorvalue����ֵ����֤��ֵ�Ƿ���ȷ
		System.out.println("��ȡ�Ķ�λ���ͣ�" + locatorType + "\t ��ȡ�Ķ�λ���ʽ" + locatorValue);
		// ����locatorType����ֵ�������жϷ��غ��ֶ�λ��ʽ��by����
		if (locatorType.toLowerCase().equals("id"))
			return By.id(locatorValue);
		else if (locatorType.toLowerCase().equals("name"))
			return By.name(locatorValue);
		else if (locatorType.toLowerCase().equals("classname") || (locatorType.toLowerCase().equals("class")))
			return By.className(locatorValue);
		else if (locatorType.toLowerCase().equals("tagname") || (locatorType.toLowerCase().equals("tag")))
			return By.className(locatorValue);
		else if (locatorType.toLowerCase().equals("linktext") || (locatorType.toLowerCase().equals("link")))
			return By.linkText(locatorValue);
		else if (locatorType.toLowerCase().equals("partiallinktext"))
			return By.partialLinkText(locatorValue);
		else if (locatorType.toLowerCase().equals("cssselector") || (locatorType.toLowerCase().equals("css")))
			return By.cssSelector(locatorValue);
		else if (locatorType.toLowerCase().equals("xpath"))
			return By.xpath(locatorValue);
		else
			throw new Exception("�����locator typeδ�ڳ����б����壺" + locatorType);
	}

}
