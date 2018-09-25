package by.ruslan.hologram.factory;

import by.ruslan.hologram.entity.Project;
import by.ruslan.hologram.entity.enums.RecFileTabs;
import by.ruslan.hologram.utill.ExcelParserApache;
import org.apache.poi.ss.usermodel.Cell;

public class ProjectParser {

	private int projectRow;
	private int projectNumberColumn;
	private int projectTypeColumn;
	private ExcelParserApache parser;
	private Project project;

	public ProjectParser(Project project, ExcelParserApache parser) {
		projectRow = 2;
		projectNumberColumn = 1;
		projectTypeColumn = 4;
		this.project = null;
		this.parser = parser;
		this.project = parseRecFile(projectRow, project);
	}

	public Project getProject() {
		return project;
	}

	private Project parseRecFile(int row, Project project) {
		String sheetName = RecFileTabs.Main.name();
		Cell cell = null;
		cell = parser.getCell(sheetName, row, projectNumberColumn);
		int id = 0;
		if (cell != null) {
			switch (cell.getCellType()) {
			case 1: // '\001'
				try {
					id = (new Integer(cell.getStringCellValue())).intValue();
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;

			case 0: // '\0'
				id = (int) cell.getNumericCellValue();
				break;

			default:
				id = 0;
				break;
			}
			project.setID(id);
			parser.getCell(sheetName, row, projectTypeColumn).getStringCellValue();
		}
		return project;
	}
}
