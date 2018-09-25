package by.ruslan.hologram.commands;

import by.ruslan.hologram.entity.Lens;
import by.ruslan.hologram.utill.LensRegistry;
import by.ruslan.hologram.utill.ParamsHelper;
import by.ruslan.hologram.utill.PropertiesManager;

import java.util.ArrayList;
import java.util.List;

public class GoToLensesStock implements ICommand {

	public String execute(ParamsHelper ph) {
		List<Lens> lenses = new ArrayList<Lens>();
		lenses = (new LensRegistry()).getLenses();
		ph.setAttributeToRequest("lenses", lenses);
		
		return PropertiesManager.getProperty("lensStockJsp");
	}
}
