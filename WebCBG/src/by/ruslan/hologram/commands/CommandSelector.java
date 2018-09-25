package by.ruslan.hologram.commands;

import by.ruslan.hologram.utill.ParamsHelper;
import java.util.HashMap;

/**
 * 
 * Singleton class defines project commands
 * 
 * @author Ruslan
 *
 */

public class CommandSelector {
	

    private static CommandSelector instance = null;
    private HashMap commands;

	private CommandSelector() {
		commands = new HashMap();
		commands.put("rows", new Rows());
		commands.put("load", new LoadAllCBG());
		commands.put("search", new Search());
		commands.put("loadCBG", new LoadCBG());
		commands.put("clickMe", new ClickMe());
		commands.put("openDefault", new OpenDefault());
		commands.put("sortByWaferID", new Sort.ByWaferID());
		commands.put("sortByWaferIDStock", new Sort.ByWaferIDStock());
		commands.put("sortBySurfaceType", new Sort.BySurfaceType());
		commands.put("sortByWavelength", new Sort.ByWavelength());
		commands.put("sortByFWHM", new Sort.ByFWHM());
		commands.put("sortByDispersion", new Sort.ByDispersion());
		commands.put("sortByProjectID", new Sort.ByProjectID());
		commands.put("sortByProjectName", new Sort.ByProjectName());
		commands.put("sortByRecDate", new Sort.ByRecDate());
		commands.put("sortByStatus", new Sort.ByStatus());
		commands.put("loadM2history", new M2History());
		commands.put("goHome", new GoHome());
		commands.put("goToStock", new GoToStock());
		commands.put("goToLensesStock", new GoToLensesStock());
		commands.put("searchInStock", new Search());
		commands.put("refresh", new Refresh());
		commands.put("openRecFile", new OpenRecFile());
		commands.put("lensSearch", new LensSearch());
	}

	public ICommand getCommand(ParamsHelper ph) {
		String action = ph.getParameter("command");
		return (ICommand) commands.get(action);
	}

	public static CommandSelector getInstance() {
		if (instance == null)
			instance = new CommandSelector();
		return instance;
	}

}
