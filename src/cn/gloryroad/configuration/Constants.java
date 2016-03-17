package cn.gloryroad.configuration;

public class Constants {
	// 测试数据相关常量设定
	public static final String Path_ExcelFile = "D://ECLIPSE//GitHub//Demo//keyWordDrivenDemo//src//cn//gloryroad//data//关键字驱动测试用例.xlsx";
	public static final String Path_ConfigurationFile = "D://ECLIPSE//GitHub//Demo//keyWordDrivenDemo//objectMap.properties";

	// 测试数据sheet中的列号常量设定
	// 第一列用0表示，测试用例序号列
	public static final int Col_TestCaseID = 0;
	// 第四列用3表示，关键字列
	public static final int Col_keyWordAction = 3;
	// 第五列用4表示，操作值列
	public static final int Col_ActionValue = 4;
	// 测试集合sheet中的列号常量设定
	public static final int Col_RunFlag = 2;
	// 测试集合sheet名称常量设定
	public static final String Sheet_TestSteps = "发送邮件";
	// 测试用例集sheet的常量设定
	public static final String Sheet_TestSuite = "测试用例集合";

}
