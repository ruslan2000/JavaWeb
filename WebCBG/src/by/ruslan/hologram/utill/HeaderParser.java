package by.ruslan.hologram.utill;

import java.util.*;
import org.apache.poi.ss.usermodel.*;

public class HeaderParser {
	
    private int headerRow;
    private String filePath;
    private String sheetName;
    private List names;

	public HeaderParser(String filePath, String sheetName, int headerRow) {
		this.filePath = filePath;
		this.headerRow = headerRow;
		this.sheetName = sheetName;
		names = new ArrayList();
	}

	public List getHeaderNames() {
		ExcelParserApache parser = new ExcelParserApache(filePath);
		Workbook workBook = parser.getWorkbook();
		Sheet readSheet = workBook.getSheet(sheetName);
		Iterator iterator = readSheet.iterator();
		if (iterator.hasNext()) {
			Row row = (Row) iterator.next();
			for (int i = 0; i < headerRow; i++)
				row = (Row) iterator.next();

			for (Iterator rowIterator = row.cellIterator(); rowIterator.hasNext();) {
				Cell cell = (Cell) rowIterator.next();
				if (cell.getCellType() == 1 && !cell.getStringCellValue().isEmpty())
					names.add(cell.getStringCellValue());
			}

		}
		return names;
	}

	public List getNamesIndex(List namesToFind) {
		List namesIndex = new ArrayList();
		names = getHeaderNames();
		for (Iterator iterator = namesToFind.iterator(); iterator.hasNext();) {
			String name = (String) iterator.next();
			int i = 0;
			for (Iterator iterator1 = names.iterator(); iterator1.hasNext(); i++) {
				String headerName = (String) iterator1.next();
				if (!name.equals(headerName))
					continue;
				namesIndex.add(Integer.valueOf(i));
				break;
			}

		}

		return namesIndex;
	}
}
