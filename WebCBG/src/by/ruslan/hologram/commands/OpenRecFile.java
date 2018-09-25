package by.ruslan.hologram.commands;

import by.ruslan.hologram.entity.CBG;
import by.ruslan.hologram.utill.OpenFile;
import by.ruslan.hologram.utill.ParamsHelper;
import by.ruslan.hologram.utill.PropertiesManager;

import java.io.*;

public class OpenRecFile implements ICommand {

	public String execute(ParamsHelper paramshelper) {
		String page = PropertiesManager.getProperty("singleCBGjsp");
		CBG cbg = new CBG();
		cbg = (CBG) paramshelper.getAttribute("cbg");
		File f = new File(cbg.getRecFilePath());
		String fileName = f.getName();
		String command = PropertiesManager.getProperty("pushdPath").concat(fileName);
		System.out.println(command);
		try {
			OpenFile.execute(command);
			OpenFile.execute("popd");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return page;
	}
}
