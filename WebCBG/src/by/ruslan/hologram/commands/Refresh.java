package by.ruslan.hologram.commands;

import by.ruslan.hologram.factory.CbgBuilder;
import by.ruslan.hologram.utill.ParamsHelper;
import by.ruslan.hologram.utill.PropertiesManager;

public class Refresh implements ICommand {

	public String execute(ParamsHelper ph) {
		String page = PropertiesManager.getProperty("homeJsp");
		CbgBuilder cbgBuilder = new CbgBuilder();
		System.out.println("refresh");
		ph.setAttributeToSession("CbgBuilder", cbgBuilder);
		return page;
	}
}
