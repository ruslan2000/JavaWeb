package by.ruslan.hologram.commands;

import java.awt.Desktop;
import java.io.File;

import by.ruslan.hologram.entity.CBG;
import by.ruslan.hologram.utill.ParamsHelper;
import by.ruslan.hologram.utill.PropertiesManager;

public class OpenDefault implements ICommand {


	public String execute(ParamsHelper ph) {
		CBG cbg = new CBG();
		cbg = (CBG) ph.getAttribute("cbg");
		File f = new File(cbg.getRecFilePath());

		//Works only on local desktop
		Desktop desktop = Desktop.getDesktop();
		return PropertiesManager.getProperty("singleCBGjsp");
	}
}
