package by.ruslan.hologram.commands;

import by.ruslan.hologram.utill.ParamsHelper;
import by.ruslan.hologram.utill.PropertiesManager;

public class GoToStock implements ICommand {

	public GoToStock() {
	}

	public String execute(ParamsHelper ph) {
		return PropertiesManager.getProperty("stockJsp");
	}
}
