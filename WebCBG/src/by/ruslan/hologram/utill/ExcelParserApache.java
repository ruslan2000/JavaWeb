package by.ruslan.hologram.utill;

import java.io.*;
import java.util.*;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.format.CellDateFormatter;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelParserApache {

	private String excelFilePath;

	public ExcelParserApache(String excelFilePath) {
		this.excelFilePath = excelFilePath;
	}

	public void parseSpreadSheet(int sheetNumber) throws IOException {
		Workbook workbook = getWorkbook();
		Sheet readSheet = workbook.getSheetAt(sheetNumber);
		Iterator iterator = readSheet.iterator();
		int raws = 0;
		for (; iterator.hasNext(); System.out.println()) {
			Row nextRow = (Row) iterator.next();
			Iterator cellIterator = nextRow.cellIterator();
			raws = nextRow.getRowNum();
			for (; cellIterator.hasNext(); System.out.print(" - ")) {
				Cell cell = (Cell) cellIterator.next();
				switch (cell.getCellType()) {
				case 3: // '\003'
				default:
					break;

				case 1: // '\001'
					System.out.print(cell.getStringCellValue());
					break;

				case 4: // '\004'
					System.out.print(cell.getBooleanCellValue());
					break;

				case 0: // '\0'
					if (HSSFDateUtil.isCellDateFormatted(cell)) {
						java.util.Date date = HSSFDateUtil.getJavaDate(cell.getNumericCellValue());
						String dateFmt = cell.getCellStyle().getDataFormatString();
						String strValue = (new CellDateFormatter("mm/dd/yyyy")).format(date);
						System.out.print(strValue);
					} else {
						System.out.print(cell.getNumericCellValue());
					}
					break;

				case 2: // '\002'
					System.out.print(cell.getCellFormula());
					break;
				}
			}

		}

		System.out.println(readSheet.getSheetName());
		System.out.println((new StringBuilder("raws# ")).append(raws).toString());
		workbook.close();
	}

	public Workbook getWorkbook() {
		Workbook workbook = null;
		FileInputStream inputStream = null;
		try {
			inputStream = new FileInputStream(new File(excelFilePath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		if (excelFilePath.endsWith("xlsx"))
			try {
				workbook = new XSSFWorkbook(inputStream);
			} catch (IOException e) {
				e.printStackTrace();
			}
		else if (excelFilePath.endsWith("xls"))
			try {
				workbook = new HSSFWorkbook(inputStream);
			} catch (IOException e) {
				e.printStackTrace();
			}
		try {
			inputStream.close();
		} catch (IOException e) {
			System.out.println("Can't close stream");
			e.printStackTrace();
		}
		return workbook;
	}

	public Cell getCell(String sheetName, int row, int column) {
		Workbook workbook = getWorkbook();
		if (workbook != null) {
			Sheet readSheet = workbook.getSheet(sheetName);
			if (readSheet != null) {
				for (Iterator iterator = readSheet.iterator(); iterator.hasNext();) {
					Row nextRow = (Row) iterator.next();
					if (nextRow.getRowNum() == row) {
						for (Iterator cellIterator = nextRow.cellIterator(); cellIterator.hasNext();) {
							Cell cell = (Cell) cellIterator.next();
							if (cell.getColumnIndex() == column)
								return cell;
						}

					}
				}

			}
		}
		return null;
	}

	public List getAllPictures() throws IOException {
		Workbook workbook = getWorkbook();
		List lst = workbook.getAllPictures();
		int i = 0;
		for (Iterator it = lst.iterator(); it.hasNext(); System.out.println(i++)) {
			PictureData pict = (PictureData) it.next();
			String ext = pict.suggestFileExtension();
			byte data[] = pict.getData();
			if (ext.equals("jpeg")) {
				FileOutputStream out = new FileOutputStream(
						(new StringBuilder(String.valueOf(i))).append("pict.jpg").toString());
				out.write(data);
				out.close();
			} else if (ext.equals("png")) {
				FileOutputStream out = new FileOutputStream(
						(new StringBuilder(String.valueOf(i))).append("pict.png").toString());
				out.write(data);
				out.close();
			}
		}

		return lst;
	}

	public List getRows(int sheetNumber) throws IOException {
		Workbook workbook = getWorkbook();
		Sheet readSheet = workbook.getSheetAt(sheetNumber);
		List list = new ArrayList();
		Row nextRow;
		for (Iterator iterator = readSheet.iterator(); iterator.hasNext(); list.add(nextRow.toString()))
			nextRow = (Row) iterator.next();

		return list;
	}

}
