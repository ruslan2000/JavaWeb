package by.ruslan.hologram.commands;

import java.util.ArrayList;
import java.util.List;

import by.ruslan.hologram.factory.CbgBuilder;
import by.ruslan.hologram.utill.ParamsHelper;
import by.ruslan.hologram.utill.PropertiesManager;

public class LoadAllCBG implements ICommand {

	public LoadAllCBG() {
	}

	public String execute(ParamsHelper ph) {
		String page = PropertiesManager.getProperty("cbgListJsp");
		List list = new ArrayList();
		CbgBuilder cbgBuilder = (CbgBuilder) ph.getAttribute("cbgBuilder");
		list = cbgBuilder.list;
		System.out.println((new StringBuilder("list size from records: ")).append(list.size()).toString());
		ph.setAttributeToSession("records", list);
		return page;
	}
}
