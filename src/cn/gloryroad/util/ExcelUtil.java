package cn.gloryroad.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import cn.gloryroad.configuration.Constants;
import cn.gloryroad.testScript.TestSuiteByExcel;

//������Ҫʵ����չ��Ϊ.xlsx��excel�ļ�����
public class ExcelUtil {
	private static XSSFSheet ExcelWSheet;
	private static XSSFWorkbook ExcelWBook;
	private static XSSFCell Cell;
	private static XSSFRow Row;

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
			TestSuiteByExcel.testResult = false;
			System.out.println("Excel·������ʧ��");
			e.printStackTrace();
		}
	}

	/*
	 * // ��ȡexcel�ļ�ָ����Ԫ��ĺ�����ֻ֧����չ��Ϊ/xlsx���ļ� public static String
	 * getCellData(String SheetName, int RowNum, int ColNum) throws Exception {
	 * try { // ͨ����������ָ����Ԫ����кź��кţ���ȡָ���ĵ�Ԫ����� Cell =
	 * ExcelWSheet.getRow(RowNum).getCell(ColNum); //
	 * �����Ԫ�������Ϊ�ַ����ͣ���ʹ��getStringCellValue������ȡ��Ԫ������� //
	 * �����Ԫ�������Ϊ�������ͣ���ʹ��getnumbecellvalue���� String CellData = Cell.getCellType()
	 * == XSSFCell.CELL_TYPE_STRING ? Cell.getStringCellValue() + "" :
	 * String.valueOf(Math.round(Cell.getNumericCellValue())); //
	 * ��������ָ���ĵ�Ԫ����ַ������� return CellData;
	 * 
	 * } catch (Exception e) { e.printStackTrace(); // ��ȡ�����쳣���򷵻ؿ��ַ��� return "";
	 * 
	 * } }
	 */

	// ��ȡexcel���һ�е��к�
	public static int getLastRowNum() {
		// ��������sheet�����һ�е��к�
		return ExcelWSheet.getLastRowNum();
	}

	// �趨Ҫ������excel�ļ�·��
	// �ڶ�дexcel��ʱ����Ҫ�����趨������excel�ļ�·��
	public static void setExcelFile(String Path) {
		FileInputStream ExcelFile;
		try {
			// ʵ����excel�ļ���fileinputstream����
			ExcelFile = new FileInputStream(Path);
			// ʵ����excel�ļ���XSSFWorkbook����
			ExcelWBook = new XSSFWorkbook(ExcelFile);
		} catch (Exception e) {
			TestSuiteByExcel.testResult = false;
			System.out.println("Excel·���趨ʧ��");
			e.printStackTrace();
		}
	}

	// ��ȡָ��sheet�е�ָ����Ԫ�������˺���ֻ֧����չ��Ϊ.xlsx��excel
	public static String getCellData(String SheetName, int RowNum, int ColNum) throws Exception {
		ExcelWSheet = ExcelWBook.getSheet(SheetName);
		try {
			// ͨ����������ָ����Ԫ����кź��кţ���ȡָ���ĵ�Ԫ�����
			Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
			// �����Ԫ�������Ϊ�ַ������ͣ���ʹ��getStringCellValue������ȡ��Ԫ�������
			// �����Ԫ�������Ϊ�������ͣ���ʹ��getNumberCellValue������ȡ��Ԫ�������
			String CellData = Cell.getCellType() == XSSFCell.CELL_TYPE_STRING ? Cell.getStringCellValue() + ""
					: String.valueOf(Math.round(Cell.getNumericCellValue()));
			// ��������ָ����Ԫ����ַ�������
			return CellData;
		} catch (Exception e) {
			TestSuiteByExcel.testResult = false;
			e.printStackTrace();
			// ��ȡ�����쳣���򷵻ؿ��ַ���
			return "";
		}
	}

	// ��ȡָ��sheet�е�����������
	public static int getRowCount(String SheetName) {
		ExcelWSheet = ExcelWBook.getSheet(SheetName);
		int number = ExcelWSheet.getLastRowNum();
		return number;
	}

	// ��excelָ��sheet�У���ȡ��һ�ΰ���ָ����������������ֵ��к�
	public static int getFirstRowContainsTestCaseID(String sheetName, String testCaseName, int colNum) {
		int i;
		try {
			ExcelWSheet = ExcelWBook.getSheet(sheetName);
			int rowCount = ExcelUtil.getRowCount(sheetName);
			for (i = 0; i < rowCount; i++) {
				// ʹ��ѭ���ķ�������������������е������У��ж��Ƿ����ĳ������������Źؼ���
				if (ExcelUtil.getCellData(sheetName, i, colNum).equalsIgnoreCase(testCaseName)) {
					// ������������˳�forѭ���������ذ�������������Źؼ��ֵ��к�
					break;
				}
			}
			return i;
		} catch (Exception e) {
			TestSuiteByExcel.testResult = false;
			return 0;
		}
	}

	// ��ȡָ��sheet�е�ĳ��������������ĸ���
	public static int getTestCaseLastStepRow(String SheetName, String testcaseID, int testCaseStartRowNumber)
			throws Exception {
		ExcelWSheet = ExcelWBook.getSheet(SheetName);
		// �Ӱ���ָ��������ŵĵ�һ�п�ʼ���б�����ֱ��ĳһ�в�����ָ������������ţ���ʱ�ı����������Ǵ˲�����������ĸ���
		for (int i = testCaseStartRowNumber; i <= ExcelUtil.getRowCount(SheetName) - 1; i++) {
			if (!testcaseID.equals(ExcelUtil.getCellData(SheetName, i, Constants.Col_TestCaseID))) {
				int number = i;
				return number;
			}
		}
		int number = ExcelWSheet.getLastRowNum() + 1;
		return number;
	}

	// ��excel�ļ���ִ�е�Ԫ����д�����ݣ��˺���ֻ֧����չ��Ϊ.xlsx��excel
	public static void setCellData(String SheetName,int RowNum,int ColNum,String Result){
		ExcelWSheet = ExcelWBook.getSheet(SheetName);
		try{
			//��ȡexcel�ļ��е��ж���
			Row=ExcelWSheet.getRow(RowNum);
			//�����Ԫ��Ϊ�գ��򷵻�null
			Cell=Row.getCell(ColNum,Row.RETURN_BLANK_AS_NULL);
			
			if(Cell==null){
				//����Ԫ�������null��ʱ���򴴽���Ԫ��
				//�����Ԫ��Ϊ�գ��޷�ֱ�ӵ��õ�Ԫ������setCellValue�����趨��Ԫ���ֵ
				Cell=Row.createCell(ColNum);
				//������Ԫ�����Ե��õ�Ԫ������setCellValue�����趨��Ԫ���ֵ
				Cell.setCellValue(Result);
			}else{
				//��Ԫ���������ݣ������ֱ�ӵ�Ӿ��Ԫ������setCellValue�������in����Ԫ��ֵ
				Cell.setCellValue(Result);
			}
			//ʵ����д��Excel�ļ����ļ����������
			FileOutputStream fileOut = new FileOutputStream(Constants.Path_ExcelFile);
			//������д��excel�ļ�
			ExcelWBook.write(fileOut);
			//����flush����ǿ��ˢ��д���ļ�
			fileOut.flush();
			//�ر��ļ����������
			fileOut.close();
			
			
		}catch (Exception e){
			TestSuiteByExcel.testResult=false;
			e.printStackTrace();
		}
	}
}
