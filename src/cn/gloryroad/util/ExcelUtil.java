package cn.gloryroad.util;

import java.io.FileInputStream;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

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

	// 读取excel文件指定单元格的函数，只支持扩展名为/xlsx的文件
	public static String getCellData(int RowNum, int ColNum) throws Exception {
		try {
			// 通过函数参数指定单元格的行号和列号，获取指定的单元格对象
			Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
			// 如果单元格的内容为字符串型，则使用getStringCellValue方法获取单元格的内容
			// 如果单元格的内容为数字类型，则使用getnumbecellvalue方法
			String CellData = Cell.getCellType() == XSSFCell.CELL_TYPE_STRING ? Cell.getStringCellValue() + ""
					: String.valueOf(Math.round(Cell.getNumericCellValue()));
			// 函数返回指定的单元格的字符串内容
			return CellData;

		} catch (Exception e) {
			e.printStackTrace();
			// 读取遇到异常，则返回空字符串
			return "";

		}
	}

	// 获取excel最后一行的行号
	public static int getLastRowNum() {
		// 函数返回sheet中最后一行的行号
		return ExcelWSheet.getLastRowNum();
	}
}
