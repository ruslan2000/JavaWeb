package by.ruslan.hologram.commands;

import by.ruslan.hologram.utill.ParamsHelper;

/**
 * 
 * The project's command interface
 * 
 * 
 * @author Ruslan
 *
 */

public interface ICommand {

	public abstract String execute(ParamsHelper paramshelper);
}
