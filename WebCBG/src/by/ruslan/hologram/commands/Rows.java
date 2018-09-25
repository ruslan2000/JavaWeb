package by.ruslan.hologram.commands;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import by.ruslan.hologram.utill.ExcelParserApache;
import by.ruslan.hologram.utill.ParamsHelper;
import by.ruslan.hologram.utill.PropertiesManager;

public class Rows implements ICommand {

	public String execute(ParamsHelper ph) {
		String page = PropertiesManager.getProperty("cbgListJsp");
		String excelFilePath = PropertiesManager.getProperty("cbgsInStockFile");
		ExcelParserApache parser = new ExcelParserApache(excelFilePath);
		List list = new ArrayList();
		try {
			list = parser.getRows(0);
		} catch (IOException e) {
			e.printStackTrace();
		}
		ph.setAttributeToSession("records", list);
		return page;
	}

}
