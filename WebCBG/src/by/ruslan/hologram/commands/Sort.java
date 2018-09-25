package by.ruslan.hologram.commands;

import by.ruslan.hologram.utill.ParamsHelper;
import by.ruslan.hologram.utill.SortComparator;
import java.util.Collections;
import java.util.List;

public class Sort implements ICommand {

	private static boolean reverse = false;

	public static class ByDispersion implements ICommand {

		public String execute(ParamsHelper ph) {
			List records = (List) ph.getAttribute("records");
			Collections.sort(records, new by.ruslan.hologram.utill.SortComparator.ByDispersion());
			if (Sort.reverse)
				Collections.reverse(records);
			ph.setAttributeToRequest("records", records);
			Sort.reverse = !Sort.reverse;
			return "/jsp/cbgListStock.jsp";
		}
	}

	public static class ByFWHM implements ICommand {

		public String execute(ParamsHelper ph) {
			List records = (List) ph.getAttribute("records");
			Collections.sort(records, new by.ruslan.hologram.utill.SortComparator.ByFWHM());
			if (Sort.reverse)
				Collections.reverse(records);
			ph.setAttributeToRequest("records", records);
			Sort.reverse = !Sort.reverse;
			return "/jsp/cbgListStock.jsp";
		}
	}

	public static class ByProjectID implements ICommand {

		public String execute(ParamsHelper ph) {
			Sort.sortByProjectID(ph);
			return "/jsp/cbgList.jsp";
		}
	}

	public static class ByProjectName implements ICommand {

		public String execute(ParamsHelper ph) {
			Sort.sortByProjectID(ph);
			return "/jsp/cbgListStock.jsp";
		}
	}

	public static class ByRecDate implements ICommand {

		public String execute(ParamsHelper ph) {
			List records = (List) ph.getAttribute("records");
			Collections.sort(records, new by.ruslan.hologram.utill.SortComparator.ByRecDate());
			if (Sort.reverse)
				Collections.reverse(records);
			ph.setAttributeToRequest("records", records);
			Sort.reverse = !Sort.reverse;
			return "/jsp/cbgList.jsp";
		}
	}

	public static class ByStatus implements ICommand {

		public String execute(ParamsHelper ph) {
			List records = (List) ph.getAttribute("records");
			Collections.sort(records, new by.ruslan.hologram.utill.SortComparator.ByStatus());
			if (Sort.reverse)
				Collections.reverse(records);
			ph.setAttributeToRequest("records", records);
			Sort.reverse = !Sort.reverse;
			return "/jsp/cbgListStock.jsp";
		}
	}

	public static class BySurfaceType implements ICommand {

		public String execute(ParamsHelper ph) {
			List records = (List) ph.getAttribute("records");
			Collections.sort(records, new by.ruslan.hologram.utill.SortComparator.BySurfaceType());
			if (Sort.reverse)
				Collections.reverse(records);
			ph.setAttributeToRequest("records", records);
			Sort.reverse = !Sort.reverse;
			return "/jsp/cbgListStock.jsp";
		}
	}

	public static class ByWaferID implements ICommand {

		public String execute(ParamsHelper ph) {
			Sort.sortByWaferID(ph);
			return "/jsp/cbgList.jsp";
		}
	}

	public static class ByWaferIDStock implements ICommand {

		public String execute(ParamsHelper ph) {
			Sort.sortByWaferID(ph);
			return "/jsp/cbgListStock.jsp";
		}
	}

	public static class ByWavelength implements ICommand {

		public String execute(ParamsHelper ph) {
			List records = (List) ph.getAttribute("records");
			Collections.sort(records, new by.ruslan.hologram.utill.SortComparator.ByWavelength());
			if (Sort.reverse)
				Collections.reverse(records);
			ph.setAttributeToRequest("records", records);
			Sort.reverse = !Sort.reverse;
			return "/jsp/cbgList.jsp";
		}
	}

	public String execute(ParamsHelper ph) {
		return "/jsp/cbgList.jsp";
	}

	public static void sortByWaferID(ParamsHelper ph) {
		List records = (List) ph.getAttribute("records");
		Collections.sort(records, new by.ruslan.hologram.utill.SortComparator.ByWaferID());
		if (reverse)
			Collections.reverse(records);
		ph.setAttributeToRequest("records", records);
		reverse = !reverse;
	}

	public static void sortByProjectID(ParamsHelper ph) {
		List records = (List) ph.getAttribute("records");
		Collections.sort(records, new by.ruslan.hologram.utill.SortComparator.ByProjectID());
		if (reverse)
			Collections.reverse(records);
		ph.setAttributeToRequest("records", records);
		reverse = !reverse;
	}

}
