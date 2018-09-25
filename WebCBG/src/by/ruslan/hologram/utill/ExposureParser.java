package by.ruslan.hologram.utill;

import org.apache.poi.ss.usermodel.Cell;

import by.ruslan.hologram.entity.Exposure;
import by.ruslan.hologram.entity.enums.RecFileTabs;

public class ExposureParser {

	private ExcelParserApache parser;
	
	public ExposureParser(ExcelParserApache parser) {
		this.parser = parser;
	}

	public Exposure getExposure() {
		int dosageRow = 20;
		int dosageColumn = 1;
		int recTimeRow = 21;
		int recTimeColumn = 1;
		Exposure exposure = new Exposure();
		Cell cellDosage = parser.getCell(RecFileTabs.Main.name(), dosageRow, dosageColumn);
		if (cellDosage != null) {
			double grabedDasage = 0.0D;
			try {
				grabedDasage = cellDosage.getNumericCellValue();
			} catch (Exception e) {
				System.out.println("ERROR parsing Dosage's cell");
			}
			Cell cellTime = parser.getCell(RecFileTabs.Main.name(), recTimeRow, recTimeColumn);
			double grabedRecTime = 0.0D;
			if (cellTime != null) {
				try {
					grabedRecTime = cellTime.getNumericCellValue();
				} catch (Exception e) {
					System.out.println("ERROR parsing RecTime's cell");
				}
				exposure = new Exposure(Round.value(grabedDasage, 2), Round.value(grabedRecTime, 2));
			}
		}
		return exposure;
	}

}
