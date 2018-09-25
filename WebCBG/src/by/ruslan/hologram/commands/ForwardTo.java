package by.ruslan.hologram.commands;

import by.ruslan.hologram.utill.ParamsHelper;
import by.ruslan.hologram.utill.PropertiesManager;

public class ForwardTo implements ICommand {

	public String execute(ParamsHelper ph) {
		//just for test
		return PropertiesManager.getProperty("errorJsp");
	}
}
