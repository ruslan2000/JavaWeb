package by.ruslan.hologram.commands;

import by.ruslan.hologram.entity.M2LogRecord;
import by.ruslan.hologram.factory.M2LogParser;
import by.ruslan.hologram.utill.ParamsHelper;
import by.ruslan.hologram.utill.PropertiesManager;

import java.util.*;

public class M2History implements ICommand {

	public M2History() {
	}

	public String execute(ParamsHelper ph) {
		String waferID = ph.getParameter("waferID");
		M2LogRecord m2Log = new M2LogRecord();
		M2LogRecord records = M2LogParser.parse();
		List found = new ArrayList();
		for (Iterator iterator = records.getRecords().iterator(); iterator.hasNext();) {
			M2LogRecord record = (M2LogRecord) iterator.next();
			if (record != null && record.getId().toLowerCase().contains(waferID.toLowerCase()))
				found.add(record);
		}

		m2Log.setRecords(found);
		ph.setAttributeToRequest("m2Log", m2Log);
		return PropertiesManager.getProperty("singleCBGjsp");
	}
}
