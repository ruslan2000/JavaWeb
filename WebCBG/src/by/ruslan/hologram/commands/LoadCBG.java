package by.ruslan.hologram.commands;

import java.io.File;

import org.apache.commons.lang3.time.StopWatch;

import by.ruslan.hologram.factory.CbgBuilder;
import by.ruslan.hologram.utill.FindFile;
import by.ruslan.hologram.utill.ParamsHelper;
import by.ruslan.hologram.utill.PropertiesManager;
import by.ruslan.hologram.utill.Trim;

class LoadCBG implements ICommand {

	public String execute(ParamsHelper ph) {
		String page = PropertiesManager.getProperty("singleCBGjsp");
		String waferID = ph.getParameter("clickedID");
		CbgBuilder cbgBuilder = (CbgBuilder) ph.getAttribute("CbgBuilder");
		StopWatch timer = new StopWatch();
		timer.start();
		waferID = Trim.waferID(waferID);
		if (cbgBuilder != null) {
			by.ruslan.hologram.entity.CBG cbg = cbgBuilder.grabCBG(waferID, getRecFilePath(waferID));
			ph.setAttributeToSession("cbg", cbg);
			timer.stop();
			System.out.println((new StringBuilder("Grating loaded in: ")).append(timer.getTime()).toString());
		} else {
			page = PropertiesManager.getProperty("errorJsp");
			(new Refresh()).execute(ph);
			ph.setAttributeToRequest("msg", "Session expired");
		}
		return page;
	}

	private String getRecFilePath(String waferID) {
		String recFilePath = "";
		File fileList[] = FindFile.getFileList(PropertiesManager.getProperty("cbgRecFiles"),
				waferID);
		if (fileList.length > 0)
			recFilePath = fileList[0].getAbsolutePath();
		return recFilePath;
	}
}
