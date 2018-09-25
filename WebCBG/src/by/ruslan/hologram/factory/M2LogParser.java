package by.ruslan.hologram.factory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import by.ruslan.hologram.entity.Aberration;
import by.ruslan.hologram.entity.M2;
import by.ruslan.hologram.entity.M2LogRecord;
import by.ruslan.hologram.entity.enums.GratingSide;
import by.ruslan.hologram.entity.enums.M2Direction;
import by.ruslan.hologram.entity.enums.M2LogTabs;
import by.ruslan.hologram.entity.enums.M2Method;
import by.ruslan.hologram.entity.enums.SurfaceType;
import by.ruslan.hologram.utill.Converter;
import by.ruslan.hologram.utill.ExcelParserApache;
import by.ruslan.hologram.utill.PropertiesManager;

public class M2LogParser {

	public static M2LogRecord parse() {
		M2LogRecord records = new M2LogRecord();
		List list = new ArrayList();
		ExcelParserApache parser = new ExcelParserApache(PropertiesManager.getProperty("m2LogFile"));
		Workbook workBook = parser.getWorkbook();
		Sheet readSheet = workBook.getSheet(M2LogTabs.M2_LOG.name());
		for (Iterator iterator = readSheet.iterator(); iterator.hasNext();) {
			Row nextRow = (Row) iterator.next();
			Cell rowCell = nextRow.getCell(0);
			if (rowCell != null && !rowCell.getStringCellValue().isEmpty()) {
				M2LogRecord record = new M2LogRecord();
				Iterator cellIterator = nextRow.cellIterator();
				List astigmatism = new ArrayList();
				List m2 = new ArrayList();
				while (cellIterator.hasNext()) {
					Cell cell = (Cell) cellIterator.next();
					switch (cell.getColumnIndex()) {
					default:
						break;

					case 0: // '\0'
						record.setId(cell.getStringCellValue());
						break;

					case 1: // '\001'
						if (cell.getCellType() == 1)
							record.setProjectName(cell.getStringCellValue());
						break;

					case 2: // '\002'
						if (cell.getCellType() == 1)
							record.setGratingAperture(cell.getStringCellValue());
						break;

					case 3: // '\003'
						if (cell.getCellType() == 0)
							record.setGratingThickness(cell.getNumericCellValue());
						break;

					case 4: // '\004'
						record.setSurfaceType(SurfaceType.UNKNOWN);
						if (cell.getCellType() == 1)
							record.setSurfaceType(Converter.getSurfaceType(cell.getStringCellValue()));
						break;

					case 5: // '\005'
						if (cell.getCellType() == 1)
							record.setBleached(Converter.getBleached(cell.getStringCellValue()));
						break;

					case 6: // '\006'
						if (cell.getCellType() == 0)
							record.setCenterWavelength(cell.getNumericCellValue());
						break;

					case 7: // '\007'
						if (cell.getCellType() == 0) {
							M2 m2xBlue = new M2(cell.getNumericCellValue());
							m2xBlue.setDirection(M2Direction.X);
							m2xBlue.setSide(GratingSide.BLUE);
							m2.add(m2xBlue);
						}
						break;

					case 8: // '\b'
						if (cell.getCellType() == 0) {
							M2 m2yBlue = new M2(cell.getNumericCellValue());
							m2yBlue.setDirection(M2Direction.Y);
							m2yBlue.setSide(GratingSide.BLUE);
							m2.add(m2yBlue);
						}
						break;

					case 9: // '\t'
						if (cell.getCellType() == 0) {
							Aberration astigmatismBlue = new Aberration(cell.getNumericCellValue());
							astigmatismBlue.setSide(GratingSide.BLUE);
							astigmatism.add(astigmatismBlue);
						}
						break;

					case 10: // '\n'
						if (cell.getCellType() == 0) {
							M2 m2xRed = new M2(cell.getNumericCellValue());
							m2xRed.setDirection(M2Direction.X);
							m2xRed.setSide(GratingSide.RED);
							m2.add(m2xRed);
						}
						break;

					case 11: // '\013'
						if (cell.getCellType() == 0) {
							M2 m2yRed = new M2(cell.getNumericCellValue());
							m2yRed.setDirection(M2Direction.Y);
							m2yRed.setSide(GratingSide.RED);
							m2.add(m2yRed);
						}
						break;

					case 12: // '\f'
						if (cell.getCellType() == 0) {
							Aberration astigmatismRed = new Aberration(cell.getNumericCellValue());
							astigmatismRed.setSide(GratingSide.RED);
							astigmatism.add(astigmatismRed);
						}
						break;

					case 13: // '\r'
						if (cell.getCellType() == 1)
							record.setLightSource(cell.getStringCellValue());
						break;

					case 14: // '\016'
						if (cell.getCellType() == 1)
							record.setBeamDiameter(cell.getStringCellValue());
						break;

					case 15: // '\017'
						if (cell.getCellType() == 1)
							record.setDateOfTest(cell.getStringCellValue());
						break;

					case 16: // '\020'
						record.setM2method(M2Method.UNDEFINED);
						if (cell.getCellType() == 1)
							record.setM2method(Converter.getM2Method(cell.getStringCellValue()));
						break;

					case 17: // '\021'
						if (cell.getCellType() == 1)
							record.setOperator(cell.getStringCellValue());
						break;
					}
				}
				record.setAberrationList(astigmatism);
				record.setM2List(m2);
				list.add(record);
			}
		}

		records.setRecords(list);
		return records;
	}
}
