package by.ruslan.hologram.utill;

public class PagesPath {

	public static String getCbgTested() {

		return PropertiesManager.getProperty("cbgRecFiles");
	}

	public String getxCbgInStock() {

		return PropertiesManager.getProperty("cbgsInStockFile");
	}

	public static String getSingleCbg() {

		return PropertiesManager.getProperty("singleCBGjsp");
	}

	public String getHome() {

		return PropertiesManager.getProperty("imageHome");
	}

	public String getBack() {

		return PropertiesManager.getProperty("imageBack");
	}

	public String getLogout() {

		return PropertiesManager.getProperty("imageLogout");
	}

	public static String getCbgList() {

		return PropertiesManager.getProperty("cbgListJsp");
	}

	public static String getCbgListStock() {

		return PropertiesManager.getProperty("cbgListStockJsp");
	}

	public String getAjaxLoader() {

		return PropertiesManager.getProperty("imageLoader");
	}

}
