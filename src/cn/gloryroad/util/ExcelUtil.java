package cn.gloryroad.util;

import java.io.FileInputStream;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

//������Ҫʵ����չ��Ϊ.xlsx��excel�ļ�����
public class ExcelUtil {
	private static XSSFSheet ExcelWSheet;
	private static XSSFWorkbook ExcelWBook;
	private static XSSFCell Cell;

	// �趨Ҫ������excel���ļ�·����excel�ļ��е�sheet����
	// �ڶ�дexcel�ļ���ʱ����Ҫ�ȵ��ô˷������趨Ҫ������excel�ļ���·����sheet����
	public static void setExcelFile(String Path, String SheetName) throws Exception {
		FileInputStream ExcelFile;
		try {
			// ʵ����excel�ļ���fileinputstream����
			ExcelFile = new FileInputStream(Path);
			// ʵ����excel��xssfworkbook����
			ExcelWBook = new XSSFWorkbook(ExcelFile);
			// ʵ����xssfsheet����ָ��excel�ļ���sheet���ƣ���������sheet���к��к͵�Ԫ��Ĵ���
			ExcelWSheet = ExcelWBook.getSheet(SheetName);

		} catch (Exception e) {
			throw (e);
		}
	}

	// ��ȡexcel�ļ�ָ����Ԫ��ĺ�����ֻ֧����չ��Ϊ/xlsx���ļ�
	public static String getCellData(int RowNum, int ColNum) throws Exception {
		try {
			// ͨ����������ָ����Ԫ����кź��кţ���ȡָ���ĵ�Ԫ�����
			Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
			// �����Ԫ�������Ϊ�ַ����ͣ���ʹ��getStringCellValue������ȡ��Ԫ�������
			// �����Ԫ�������Ϊ�������ͣ���ʹ��getnumbecellvalue����
			String CellData = Cell.getCellType() == XSSFCell.CELL_TYPE_STRING ? Cell.getStringCellValue() + ""
					: String.valueOf(Math.round(Cell.getNumericCellValue()));
			// ��������ָ���ĵ�Ԫ����ַ�������
			return CellData;

		} catch (Exception e) {
			e.printStackTrace();
			// ��ȡ�����쳣���򷵻ؿ��ַ���
			return "";

		}
	}

	// ��ȡexcel���һ�е��к�
	public static int getLastRowNum() {
		// ��������sheet�����һ�е��к�
		return ExcelWSheet.getLastRowNum();
	}
}
