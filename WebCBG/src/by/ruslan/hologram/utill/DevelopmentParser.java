package by.ruslan.hologram.utill;

import by.ruslan.hologram.entity.Development;
import by.ruslan.hologram.entity.enums.RecFileTabs;
import org.apache.poi.ss.usermodel.Cell;

public class DevelopmentParser {

	private ExcelParserApache parser;

	public DevelopmentParser(ExcelParserApache parser) {
		this.parser = parser;
	}

	public Development getDevelopment() {
		Development development = new Development();
		int devNameRow = 14;
		int devNameColumn = 1;
		int furnaceRow = 15;
		int furnaceColumn = 1;
		int stepsNumber = 5;
		for (int i = 0; i < stepsNumber; i++) {
			String devName = "";
			String furnace = "";
			int rowIncrement = 6 * i;
			Cell devNameCell = parser.getCell(RecFileTabs.Development.name(), devNameRow + rowIncrement, devNameColumn);
			Cell furnaceCell = parser.getCell(RecFileTabs.Development.name(), furnaceRow + rowIncrement, furnaceColumn);
			if (devNameCell != null)
				switch (devNameCell.getCellType()) {
				case 1: // '\001'
					devName = devNameCell.getStringCellValue();
					break;
				}
			if (furnaceCell != null)
				switch (furnaceCell.getCellType()) {
				case 1: // '\001'
					furnace = furnaceCell.getStringCellValue();
					break;

				case 2: // '\002'
					try {
						furnace = furnaceCell.getStringCellValue();
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;

				default:
					furnace = "N/A";
					break;
				}
			if (!devName.isEmpty() && !furnace.isEmpty())
				development.setDevStep(devName, furnace);
		}

		return development;
	}

}
