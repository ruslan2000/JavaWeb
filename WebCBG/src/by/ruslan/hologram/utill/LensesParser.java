package by.ruslan.hologram.utill;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.ss.usermodel.Cell;

import by.ruslan.hologram.entity.Lens;
import by.ruslan.hologram.entity.enums.LensType;
import by.ruslan.hologram.entity.enums.RecFileTabs;

public class LensesParser {
	
    private int row1;
    private int row2;
    private int lensTypeColumn;
    private int lensManufactureColumn;
    private int lensFocusColumn;
    private int lensDistanceColumn;
    private ExcelParserApache parser;

	public LensesParser(ExcelParserApache parser) {
		row1 = 17;
		row2 = 18;
		lensTypeColumn = 4;
		lensManufactureColumn = 5;
		lensFocusColumn = 7;
		lensDistanceColumn = 8;
		this.parser = parser;
	}

	public List getLenses() {
		List lensSet = new ArrayList();
		lensSet.add(parseRecFile(row1));
		lensSet.add(parseRecFile(row2));
		return lensSet;
	}

	private Lens parseRecFile(int row) {
		String sheetName = RecFileTabs.Main.name();
		Lens lens = new Lens();
		Cell cell = null;
		cell = parser.getCell(sheetName, row, lensTypeColumn);
		if (cell != null && cell.getColumnIndex() == lensTypeColumn) {
			LensType type = null;
			if (cell.getCellType() == 1) {
				if (cell.getStringCellValue().contains("Convex"))
					type = LensType.Convex;
				if (cell.getStringCellValue().contains("Concave"))
					type = LensType.Concave;
			}
			lens.setType(type);
		}
		Cell vendor = parser.getCell(sheetName, row, lensManufactureColumn);
		if (vendor != null && vendor.getCellType() == 1) {
			String lensIDCellString = vendor.getStringCellValue();
			String vendorID = parseVendorCellString(lensIDCellString);
			lens.setVendor(vendorID);
		}
		Cell focus = parser.getCell(sheetName, row, lensFocusColumn);
		if (focus != null)
			switch (focus.getCellType()) {
			case 1: // '\001'
				String s = focus.getStringCellValue();
				Double f = stringFocusToDouble(s);
				lens.setFocus(f);
				break;

			case 0: // '\0'
				lens.setFocus(Double.valueOf(focus.getNumericCellValue()));
				break;

			case 2: // '\002'
				if (focus.getCachedFormulaResultType() == 0)
					lens.setFocus(Double.valueOf(focus.getNumericCellValue()));
				if (focus.getCachedFormulaResultType() == 1) {
					String formulaString = focus.getRichStringCellValue().toString();
					lens.setFocus(stringFocusToDouble(formulaString));
				}
				break;

			default:
				System.out.println("Error during parsing. Focus set to defaul - 0.0");
				lens.setFocus(Double.valueOf(0.0D));
				break;
			}
		Cell distance = parser.getCell(sheetName, row, lensDistanceColumn);
		if (distance != null && distance.getCellType() == 1)
			lens.setDistance(distance.getStringCellValue());
		return lens;
	}

	private String parseVendorCellString(String vendorCellString) {
		String vendorID = "";
		Pattern p = Pattern.compile("\\w+[#]*\\d*");
		Matcher m = p.matcher(vendorCellString);
		if (m.find())
			vendorID = m.group();
		return vendorID;
	}

	private Double stringFocusToDouble(String s) {
		Double focus = Double.valueOf(0.0D);
		Pattern p = Pattern.compile("[-]?\\d+[.]*\\d*");
		Matcher m = p.matcher(s);
		if (m.find())
			focus = new Double(m.group());
		return focus;
	}
}
