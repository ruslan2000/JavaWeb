package by.ruslan.hologram.commands;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import by.ruslan.hologram.entity.CBG;
import by.ruslan.hologram.entity.Lens;
import by.ruslan.hologram.factory.CbgBuilder;
import by.ruslan.hologram.utill.FindFile;
import by.ruslan.hologram.utill.LensRegistry;
import by.ruslan.hologram.utill.ParamsHelper;
import by.ruslan.hologram.utill.PropertiesManager;

public class LensSearch implements ICommand {


	private List listCBG;
	private CbgBuilder cbgBuilder;

	public String execute(ParamsHelper ph) {
		cbgBuilder = (CbgBuilder) ph.getAttribute("CbgBuilder");
		if (cbgBuilder == null) {
			cbgBuilder = new CbgBuilder();
			ph.setAttributeToSession("CbgBuilder", cbgBuilder);
		}
		List list = cbgBuilder.list;
		System.out.println((new StringBuilder("list size: ")).append(list.size()).toString());
		System.out.println(ph.getParameter("modified"));
		String date = ph.getParameter("modified");
		String selectedLensID = ph.getParameter("lensSelect");
		Lens seLectedLens = (new LensRegistry()).getLens(selectedLensID);
		System.out.println((new StringBuilder("Selected lens: ")).append(seLectedLens.getId()).append(" - Focus=")
				.append(seLectedLens.getFocus()).append(" - Dim ").append(seLectedLens.getDimention())
				.append(" Vendor:").append(seLectedLens.getVendor()).toString());
		filter(list, date, seLectedLens);
		ph.setAttributeToSession("records", listCBG);
		return PropertiesManager.getProperty("cbgListJsp");
	}

	private List filter(List list, String date, Lens seLectedLens) {
		Double tollerance = Double.valueOf(0.20000000000000001D);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		listCBG = new ArrayList();
		try {
			Date from = df.parse(date);
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				CBG cbg = (CBG) iterator.next();
				if (!cbg.getRecDate().isEmpty()) {
					Date recDate = df.parse(cbg.getRecDate());
					if (recDate.after(from)) {
						CBG newCBG = fillCbgWithRecData(cbg);
						System.out.println(newCBG.getWaferID());
						if (cbgRecLens(newCBG, seLectedLens, tollerance)) {
							Lens l = (Lens) newCBG.getLenses().get(0);
							System.out
									.println((new StringBuilder(String.valueOf(newCBG.getWaferID()))).append(" Lens: ")
											.append(l.getVendor()).append(" F=").append(l.getFocus()).toString());
							listCBG.add(newCBG);
						}
					}
				}
			}

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return listCBG;
	}

	private boolean cbgRecLens(CBG newCBG, Lens seLectedLens, Double tollerance) {
		List lensList = newCBG.getLenses();
		Lens lens = new Lens();
		if (lensList != null)
			lens = (Lens) lensList.get(0);
		Double x = Double.valueOf(seLectedLens.getFocus().doubleValue() - lens.getFocus().doubleValue());
		Boolean focusBoolean = Boolean
				.valueOf(Math.abs(x.doubleValue()) < tollerance.doubleValue() * seLectedLens.getFocus().doubleValue());
		Boolean vendorBoolean = Boolean.valueOf(seLectedLens.getVendor().equals(lens.getVendor()));
		System.out.println((new StringBuilder(" vendor: ")).append(lens.getVendor()).append(" - ").append("focus: ")
				.append(lens.getFocus()).toString());
		return focusBoolean.booleanValue() & vendorBoolean.booleanValue();
	}

	private CBG fillCbgWithRecData(CBG cbg) {
		String waferID = cbg.getWaferID();
		String recFilePath = "";
		File fileList[] = FindFile.getFileList(PropertiesManager.getProperty("cbgRecFiles"), waferID);
		if (fileList.length > 0)
			recFilePath = fileList[0].getAbsolutePath();
		cbg = cbgBuilder.grabCBG(waferID, recFilePath);
		return cbg;
	}

}
