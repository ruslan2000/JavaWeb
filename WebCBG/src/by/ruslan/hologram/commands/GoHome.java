package by.ruslan.hologram.commands;

import by.ruslan.hologram.utill.ParamsHelper;
import by.ruslan.hologram.utill.PropertiesManager;

public class GoHome implements ICommand {

	public String execute(ParamsHelper ph) {
		
		return PropertiesManager.getProperty("homeJsp");
	}
}
