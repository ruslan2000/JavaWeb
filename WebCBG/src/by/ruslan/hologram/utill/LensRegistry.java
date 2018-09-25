package by.ruslan.hologram.utill;

import by.ruslan.hologram.entity.Lens;
import by.ruslan.hologram.entity.enums.ExcelTabs;
import java.util.*;
import org.apache.poi.ss.usermodel.*;

public class LensRegistry {
	
    private Lens lens;
    private List lenses;
    private int idColumn;
    private int vendorColumn;
    private int focusColumn;
    private int dimensionColumn;

	public LensRegistry() {
		idColumn = 0;
		vendorColumn = 1;
		focusColumn = 3;
		dimensionColumn = 4;
		lenses = parseLensRegistry();
	}

	private List parseLensRegistry() {
		List lenses = new ArrayList();
		String sheetName = ExcelTabs.Cyl_Lens_Pairs_for_CBG.name();
		ExcelParserApache parser = new ExcelParserApache(PropertiesManager.getProperty("lensRegistryFile"));
		Workbook workbook = parser.getWorkbook();
		Sheet readSheet = workbook.getSheet(sheetName);
		Cell cell = null;
		Lens lens;
		for (Iterator iterator = readSheet.iterator(); iterator.hasNext(); lenses.add(lens)) {
			Row nextRow = (Row) iterator.next();
			lens = new Lens();
			cell = nextRow.getCell(idColumn);
			if (validCell(cell))
				lens.setId(cell.getStringCellValue());
			cell = nextRow.getCell(vendorColumn);
			if (validCell(cell))
				lens.setVendor(cell.getStringCellValue());
			cell = nextRow.getCell(focusColumn);
			if (validCell(cell) && cell.getCellType() == 0)
				lens.setFocus(Double.valueOf(cell.getNumericCellValue()));
			cell = nextRow.getCell(dimensionColumn);
			if (validCell(cell))
				lens.setDimention(cell.getStringCellValue());
		}

		return lenses;
	}

	private boolean validCell(Cell cell) {
		return cell != null;
	}

	public Lens getLens(String id) {
		for (Iterator iterator = lenses.iterator(); iterator.hasNext();) {
			Lens lens = (Lens) iterator.next();
			if (lens.getId().equals(id))
				return lens;
		}

		return this.lens;
	}

	public List getLenses() {
		return lenses;
	}

}
