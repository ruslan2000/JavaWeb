package by.ruslan.hologram.commands;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import by.ruslan.hologram.entity.CBG;
import by.ruslan.hologram.entity.Project;
import by.ruslan.hologram.entity.enums.GratingType;
import by.ruslan.hologram.entity.enums.Status;
import by.ruslan.hologram.factory.CbgBuilder;
import by.ruslan.hologram.utill.ParamsHelper;
import by.ruslan.hologram.utill.Trim;

public class Search implements ICommand {

	CbgBuilder cbgBuilder;

	public String execute(ParamsHelper ph) {
		String waferID = ph.getParameter("searchID");
		String projectName = ph.getParameter("searchProject");
		String wavelength = ph.getParameter("searchWL");
		String deltaWavelength = ph.getParameter("searcDeltahWL");
		String command = ph.getParameter("command");
		String page = "/index.jsp";
		List listOfAll = new ArrayList();
		List listSearched = new ArrayList();
		cbgBuilder = (CbgBuilder) ph.getAttribute("CbgBuilder");
		if (cbgBuilder == null) {
			cbgBuilder = new CbgBuilder();
			ph.setAttributeToSession("CbgBuilder", cbgBuilder);
		}
		if (command.equals("search")) {
			System.out.println("search");
			listOfAll = cbgBuilder.list;
			listSearched = findCBGs(listOfAll, waferID, projectName, wavelength);
			page = "/jsp/cbgList.jsp";
		}
		if (command.equals("searchInStock")) {
			System.out.println("searchInStock");
			listOfAll = cbgBuilder.stockList;
			listSearched = findCBGsStock(listOfAll, ph);
			String checkBox = ph.getParameter("checkBoxReady");
			boolean ready = checkBox != null;
			if (ready)
				listSearched = filterSearchedList(listSearched, ready);
			page = "/jsp/cbgListStock.jsp";
		}
		ph.setAttributeToSession("records", listSearched);
		System.out.println((new StringBuilder("records size: ")).append(listSearched.size()).toString());
		return page;
	}

	private List filterSearchedList(List listSearched, boolean ready) {
		List newList = new ArrayList();
		if (listSearched != null && ready) {
			for (Iterator iterator = listSearched.iterator(); iterator.hasNext();) {
				CBG cbg = (CBG) iterator.next();
				if (cbg.getStatus() == Status.READY_TO_GO)
					newList.add(cbg);
			}

		}
		return newList;
	}

	private void loadProject(CBG cbg) {
		String waferIDtrimmed = Trim.waferID(cbg.getWaferID());
		Project unknown = new Project(0, "Unknown", GratingType.UNKNOWN, "N/A", 0.0D, 0.0D, "N/A", "N/A", "N/A", "N/A",
				"N/A", 0);
		for (Iterator iterator = cbgBuilder.list.iterator(); iterator.hasNext(); cbg.setProject(unknown)) {
			CBG cbgLook = (CBG) iterator.next();
			if (cbgLook.getWaferID() != null && cbgLook.getWaferID().equals(waferIDtrimmed))
				unknown = cbgLook.getProject();
		}

	}

	private List findCBGsStock(List listOfAll, ParamsHelper ph) {
		List searchedList = new ArrayList();
		String waferID = ph.getParameter("searchID");
		String projectName = ph.getParameter("searchProject");
		String wavelength = ph.getParameter("searchWL");
		String deltaWavelength = ph.getParameter("searcDeltahWL");
		String fwhm = ph.getParameter("searchFWHM");
		String deltaFwhm = ph.getParameter("searchDeltaFWHM");
		String thickness = ph.getParameter("searchThickness");
		String deltaThickness = ph.getParameter("searchDeltaThickness");
		String dispersion = ph.getParameter("searchDispersion");
		String delatDispersion = ph.getParameter("searchDeltaDispersion");
		if (listOfAll != null) {
			for (Iterator iterator = listOfAll.iterator(); iterator.hasNext();) {
				CBG cbg = (CBG) iterator.next();
				if (checkString(cbg.getWaferID(), waferID)
						&& checkNumberRange(cbg.getTestCWL(), wavelength, deltaWavelength)
						&& checkNumberRange(cbg.getFWHM(), fwhm, deltaFwhm)
						&& checkNumberRange(cbg.getWaferThickness().doubleValue(), thickness, deltaThickness)
						&& checkNumberRange(cbg.getDispersion(), dispersion, delatDispersion)) {
					loadProject(cbg);
					if (cbg != null && cbg.getProject() != null && cbg.getProject().getName().contains(projectName))
						searchedList.add(cbg);
				}
			}

		}
		return searchedList;
	}

	private List findCBGs(List list, String waferID, String projectName, String wavelength) {
		List searchedList = new ArrayList();
		if (list != null) {
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				CBG cbg = (CBG) iterator.next();
				if (cbg.getWaferID() != null && cbg.getWaferID().toLowerCase().contains(waferID.toLowerCase())
						&& cbg.getProject() != null
						&& cbg.getProject().getName().toLowerCase().contains(projectName.toLowerCase())
						&& (new Double(cbg.getProject().getCWL())).toString().toLowerCase()
								.contains(wavelength.toLowerCase()))
					searchedList.add(cbg);
			}

		}
		return searchedList;
	}

	private boolean checkNumberRange(double testCWL, String target, String delta) {
		double dTarget = stringToDouble(target);
		double dDelta = stringToDouble(delta);
		if (dDelta == 0.0D) {
			if (dTarget == 0.0D)
				return true;
			return testCWL == dTarget;
		}
		if (dTarget == 0.0D)
			return true;
		return testCWL >= dTarget - dDelta && testCWL <= dTarget + dDelta;
	}

	private boolean checkString(String waferID, String searchWaferID) {
		if (waferID != null)
			return waferID.toLowerCase().contains(searchWaferID.toLowerCase());
		else
			return false;
	}

	private double stringToDouble(String s) {
		if (s.isEmpty())
			return 0.0D;
		else
			return (new Double(s)).doubleValue();
	}

}
