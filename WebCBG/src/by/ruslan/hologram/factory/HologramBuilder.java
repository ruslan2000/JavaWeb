package by.ruslan.hologram.factory;

import by.ruslan.hologram.entity.Hologram;
import by.ruslan.hologram.entity.Project;
import by.ruslan.hologram.entity.enums.ExcelTabs;
import by.ruslan.hologram.entity.enums.GratingType;
import by.ruslan.hologram.utill.ExcelParserApache;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.*;


/**
 * @deprecated as xSubstrates_in_stock file was splitted for RBGs and Substractes
 * 
 * @author Ruslan
 */

public class HologramBuilder {

	private Workbook workBook;
	private static GratingType type;
	public List list = null;

	public HologramBuilder(GratingType type) {
		this.type = type;
		list = getList();
	}

	private List getList() {
		String excelFilePath = "//Ruslan1/O/OG Mnfg/x Substrates in stock.xls";
		String excelTab = null;

		switch (ExcelTabs.values().length) {
		case 1: // '\001'
			excelFilePath = "//Ruslan1/O/OG Mnfg/x CBGs in stock.xls";
			excelTab = ExcelTabs.CBG.name();
			break;

		case 2: // '\002'
			excelTab = ExcelTabs.RBG.name();
			break;

		case 3: // '\003'
			excelTab = ExcelTabs.TBG.name();
			break;

		case 7: // '\007'
			excelTab = ExcelTabs.MTBG.name();
			break;

		case 5: // '\005'
			excelTab = ExcelTabs.CUBES_prisms.name();
			break;

		case 6: // '\006'
			excelTab = ExcelTabs.CUBES_prisms.name();
			break;
		}
		ExcelParserApache parser = new ExcelParserApache(excelFilePath);
		workBook = parser.getWorkbook();
		list = new ArrayList();
		Sheet readSheet = workBook.getSheet(excelTab);
		Hologram hologram;
		for (Iterator iterator = readSheet.iterator(); iterator.hasNext(); list.add(hologram)) {
			hologram = new Hologram();
			Project project = new Project();
			Row nextRow = (Row) iterator.next();
			for (Iterator cellIterator = nextRow.cellIterator(); cellIterator.hasNext();) {
				Cell cell = (Cell) cellIterator.next();
				switch (cell.getColumnIndex()) {
				default:
					break;

				case 0: // '\0'
					try {
						if (!cell.getStringCellValue().isEmpty())
							hologram.setWaferID(cell.getStringCellValue());
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;

				case 1: // '\001'
					try {
						hologram.setWaferThickness(Double.valueOf(cell.getNumericCellValue()));
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;

				case 2: // '\002'
					try {
						hologram.setWaferDimention(cell.getStringCellValue());
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;

				case 9: // '\t'
					if (cell.getCellType() == 0) {
						Date date = HSSFDateUtil.getJavaDate(cell.getNumericCellValue());
						LocalDateTime datetime = LocalDateTime.parse(date.toString(),
								DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss z yyyy"));
						String newstring = datetime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
						hologram.setRecDate(newstring);
					}
					break;

				case 10: // '\n'
					try {
						project.setName(cell.getStringCellValue());
						hologram.setProject(project);
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;

				case 13: // '\r'
					try {
						hologram.setTargetCWL(cell.getNumericCellValue());
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;

				case 19: // '\023'
					try {
						hologram.setNote(cell.getStringCellValue());
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;
				}
			}

		}

		return list;
	}
}
