package by.ruslan.hologram.commands;

import by.ruslan.hologram.utill.ParamsHelper;
import by.ruslan.hologram.utill.Trim;

public class LoadParentCBG implements ICommand {

	public LoadParentCBG() {
	}

	public String execute(ParamsHelper ph) {
		String waferID = ph.getParameter("clickedID");
		String trimedWaferID = Trim.waferID(waferID);
		ph.setAttributeToSession("clickedID", trimedWaferID);
		return (new LoadCBG()).execute(ph);
	}
}
