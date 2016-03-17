package cn.gloryroad.util;

import java.io.FileInputStream;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import cn.gloryroad.configuration.Constants;

//本类主要实现扩展名为.xlsx的excel文件操作
public class ExcelUtil {
	private static XSSFSheet ExcelWSheet;
	private static XSSFWorkbook ExcelWBook;
	private static XSSFCell Cell;

	// 设定要操作的excel的文件路径和excel文件中的sheet名称
	// 在读写excel文件的时候，需要先调用此方法，设定要操作的excel文件的路径和sheet名称
	public static void setExcelFile(String Path, String SheetName) throws Exception {
		FileInputStream ExcelFile;
		try {
			// 实例化excel文件的fileinputstream对象
			ExcelFile = new FileInputStream(Path);
			// 实例化excel的xssfworkbook对象
			ExcelWBook = new XSSFWorkbook(ExcelFile);
			// 实例化xssfsheet对象，指定excel文件的sheet名称，后续用于sheet中行和列和单元格的错左
			ExcelWSheet = ExcelWBook.getSheet(SheetName);

		} catch (Exception e) {
			throw (e);
		}
	}

	/*
	 * // 读取excel文件指定单元格的函数，只支持扩展名为/xlsx的文件 public static String
	 * getCellData(String SheetName, int RowNum, int ColNum) throws Exception {
	 * try { // 通过函数参数指定单元格的行号和列号，获取指定的单元格对象 Cell =
	 * ExcelWSheet.getRow(RowNum).getCell(ColNum); //
	 * 如果单元格的内容为字符串型，则使用getStringCellValue方法获取单元格的内容 //
	 * 如果单元格的内容为数字类型，则使用getnumbecellvalue方法 String CellData = Cell.getCellType()
	 * == XSSFCell.CELL_TYPE_STRING ? Cell.getStringCellValue() + "" :
	 * String.valueOf(Math.round(Cell.getNumericCellValue())); //
	 * 函数返回指定的单元格的字符串内容 return CellData;
	 * 
	 * } catch (Exception e) { e.printStackTrace(); // 读取遇到异常，则返回空字符串 return "";
	 * 
	 * } }
	 */

	// 获取excel最后一行的行号
	public static int getLastRowNum() {
		// 函数返回sheet中最后一行的行号
		return ExcelWSheet.getLastRowNum();
	}

	// 设定要操作的excel文件路径
	// 在读写excel的时候，需要首先设定操作的excel文件路径
	public static void setExcelFile(String Path) {
		FileInputStream ExcelFile;
		try {
			// 实例化excel文件的fileinputstream对象
			ExcelFile = new FileInputStream(Path);
			// 实例化excel文件的XSSFWorkbook对象
			ExcelWBook = new XSSFWorkbook(ExcelFile);
		} catch (Exception e) {
			System.out.println("Excel路径设定失败");
			e.printStackTrace();
		}
	}

	// 读取指定sheet中的指定单元格函数，此函数只支持扩展名为.xlsx的excel
	public static String getCellData(String SheetName, int RowNum, int ColNum) throws Exception {
		ExcelWSheet = ExcelWBook.getSheet(SheetName);
		try {
			// 通过函数参数指定单元格的行号和列号，获取指定的单元格对象
			Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
			// 如果单元格的内容为字符串类型，则使用getStringCellValue方法获取单元格的内容
			// 如果单元格的内容为数字类型，则使用getNumberCellValue方法获取单元格的内容
			String CellData = Cell.getCellType() == XSSFCell.CELL_TYPE_STRING ? Cell.getStringCellValue() + ""
					: String.valueOf(Math.round(Cell.getNumericCellValue()));
			// 函数返回指定单元格的字符串内容
			return CellData;
		} catch (Exception e) {
			e.printStackTrace();
			// 读取遇到异常，则返回空字符串
			return "";
		}
	}

	// 获取指定sheet中的数据总行数
	public static int getRowCount(String SheetName) {
		ExcelWSheet = ExcelWBook.getSheet(SheetName);
		int number = ExcelWSheet.getLastRowNum();
		return number;
	}

	// 在excel指定sheet中，获取第一次包含指定测试用例序号文字的行号
	public static int getFirstRowContainsTestCaseID(String sheetName, String testCaseName, int colNum)
			throws Exception {
		int i;
		ExcelWSheet = ExcelWBook.getSheet(sheetName);
		int rowCount = ExcelUtil.getRowCount(sheetName);
		for (i = 0; i < rowCount; i++) {
			// 使用循环的方法遍历测试用例序号列的所有行，判断是否包含某个测试用例序号关键字
			if (ExcelUtil.getCellData(sheetName, i, colNum).equalsIgnoreCase(testCaseName)) {
				// 如果包含，则退出for循环，并返回包含测试用例序号关键字的行号
				break;
			}
		}
		return i;
	}

	// 获取指定sheet中的某个测试用例步骤的个数
	public static int getTestCaseLastStepRow(String SheetName, String testcaseID, int testCaseStartRowNumber)
			throws Exception {
		ExcelWSheet = ExcelWBook.getSheet(SheetName);
		// 从包含指定用例序号的第一行开始逐行遍历，直到某一行不出现指定测试用例序号，此时的遍历次数就是此测试用例步骤的个数
		for (int i = testCaseStartRowNumber; i <= ExcelUtil.getRowCount(SheetName) - 1; i++) {
			if (!testcaseID.equals(ExcelUtil.getCellData(SheetName, i, Constants.Col_TestCaseID))) {
				int number = i;
				return number;
			}
		}
		int number = ExcelWSheet.getLastRowNum() + 1;
		return number;
	}
}
