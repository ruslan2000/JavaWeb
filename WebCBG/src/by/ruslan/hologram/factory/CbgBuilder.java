package by.ruslan.hologram.factory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import by.ruslan.hologram.entity.CBG;
import by.ruslan.hologram.entity.DiffractedBeam;
import by.ruslan.hologram.entity.Exposure;
import by.ruslan.hologram.entity.Project;
import by.ruslan.hologram.entity.Stock;
import by.ruslan.hologram.entity.enums.CbgTabs;
import by.ruslan.hologram.entity.enums.GratingType;
import by.ruslan.hologram.entity.enums.Status;
import by.ruslan.hologram.entity.enums.StockTabs;
import by.ruslan.hologram.utill.Converter;
import by.ruslan.hologram.utill.DevelopmentParser;
import by.ruslan.hologram.utill.ExcelParserApache;
import by.ruslan.hologram.utill.ExposureParser;
import by.ruslan.hologram.utill.Filewalker;
import by.ruslan.hologram.utill.LensesParser;
import by.ruslan.hologram.utill.PropertiesManager;
import by.ruslan.hologram.utill.Round;

public class CbgBuilder {
	
	private Workbook workBook;
	public List list;
	public List stockList;	

	public CbgBuilder() {
		list = null;
		stockList = null;
		System.out.println("Loading CBGs...");
		list = getList(CbgTabs.CBG.name());
		list.addAll(getList(CbgTabs.CBG_LXL.name()));
		System.out.println(list.size());
		System.out.println("Loading Stock...");
		stockList = getStockList(StockTabs.STOCK_FILE.name());
		stockList.addAll(getStockList(StockTabs._1030nm.name()));
		stockList.addAll(getStockList(StockTabs.READY_TO_GO.name()));
	}

	private List getList(String sheetName) {
		ExcelParserApache parser = new ExcelParserApache(PropertiesManager.getProperty("cbgsInStockFile"));
		workBook = parser.getWorkbook();
		List list = new ArrayList();
		Sheet readSheet = workBook.getSheet(sheetName);
		CBG cbg;
		for (Iterator iterator = readSheet.iterator(); iterator.hasNext(); list.add(cbg)) {
			cbg = new CBG();
			cbg.setRecDate("");
			Project project = new Project();
			project.setName("");
			project.setType(GratingType.CBG);
			cbg.setDispersion(0.0D);
			Row nextRow = (Row) iterator.next();
			for (Iterator cellIterator = nextRow.cellIterator(); cellIterator.hasNext();) {
				Cell cell = (Cell) cellIterator.next();
				switch (cell.getColumnIndex()) {
		
				case 0: // '\0'
					if (cell.getCellType() == 1 && !cell.getStringCellValue().isEmpty())
						cbg.setWaferID(cell.getStringCellValue());
					break;

				case 1: // '\001'
					if (cell.getCellType() == 0)
						cbg.setWaferThickness(Double.valueOf(cell.getNumericCellValue()));
					break;

				case 2: // '\002'
					if (cell.getCellType() == 1)
						cbg.setWaferDimention(cell.getStringCellValue());
					break;

				case 9: // '\t'
					if (cell.getCellType() == 0) {
						Date date = HSSFDateUtil.getJavaDate(cell.getNumericCellValue());
						LocalDateTime datetime = LocalDateTime.parse(date.toString(),
								DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss z yyyy"));
						String newstring = datetime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
						cbg.setRecDate(newstring);
					}
					break;

				case 10: // '\n'
					if (cell.getCellType() == 1) {
						project.setName(cell.getStringCellValue());
						cbg.setProject(project);
					}
					break;

				case 13: // '\r'
					if (cell.getCellType() == 0) {
						project.setCWL(cell.getNumericCellValue());
						cbg.setProject(project);
					}
					break;

				case 14: // '\016'
					if (cell.getCellType() == 0)
						cbg.setTestCWL(cell.getNumericCellValue());
					break;

				case 16: // '\020'
					if (cell.getCellType() == 1) {
						double dispersion = parseDispersion(cell.getStringCellValue());
						cbg.setDispersion(dispersion);
					}
					break;

				case 19: // '\023'
					if (cell.getCellType() == 1)
						cbg.setNote(cell.getStringCellValue());
					break;

				case 21: // '\025'
					if (cell.getCellType() == 0)
						cbg.setM2x((new Double(cell.getNumericCellValue())).toString());
					if (cell.getCellType() == 1)
						cbg.setM2x(cell.getStringCellValue());
					break;

				case 22: // '\026'
					if (cell.getCellType() == 0)
						cbg.setM2y((new Double(cell.getNumericCellValue())).toString());
					if (cell.getCellType() == 1)
						cbg.setM2y(cell.getStringCellValue());
					break;
				
				default:
					//unimplemented
					break;
				}
			}

		}

		return list;
	}

	private double parseDispersion(String stringCellValue) {
		double dispersion = 0.0D;
		if (stringCellValue != null) {
			Pattern p = Pattern.compile("\\d+[.]?\\d*");
			Matcher m = p.matcher(stringCellValue);
			if (m.find())
				dispersion = (new Double(m.group())).doubleValue();
		}
		return dispersion;
	}

	private List getStockList(String sheetName) {
		ExcelParserApache parser = new ExcelParserApache(PropertiesManager.getProperty("cbgStockLoc"));
		Workbook workBook = parser.getWorkbook();
		Sheet readSheet = workBook.getSheet(sheetName);
		List stockList = new ArrayList();
		CBG cbg;
		for (Iterator iterator = readSheet.iterator(); iterator.hasNext(); stockList.add(cbg)) {
			cbg = new CBG();
			cbg.setRecDate("");
			Project project = new Project();
			project.setName("");
			project.setType(GratingType.CBG);
			Stock stock = new Stock(0, 0, 0);
			cbg.setStatus(Status.IN_STOCK);
			Row nextRow = (Row) iterator.next();
			for (Iterator cellIterator = nextRow.cellIterator(); cellIterator.hasNext();) {
				Cell cell = (Cell) cellIterator.next();
				switch (cell.getColumnIndex()) {
				default:
					break;

				case 0: // '\0'
					if (cell.getCellType() == 1 && !cell.getStringCellValue().isEmpty())
						cbg.setWaferID(cell.getStringCellValue());
					break;

				case 1: // '\001'
					if (cell.getCellType() == 0)
						cbg.setTestCWL(Round.value(cell.getNumericCellValue(), 2));
					break;

				case 2: // '\002'
					if (cell.getCellType() == 0)
						cbg.setFWHM(Round.value(cell.getNumericCellValue(), 2));
					break;

				case 3: // '\003'
					if (cell.getCellType() == 0)
						cbg.setLosses(Double.valueOf(cell.getNumericCellValue()).doubleValue());
					break;

				case 4: // '\004'
					if (cell.getCellType() == 0)
						cbg.setADE(Double.valueOf(cell.getNumericCellValue()).doubleValue());
					break;

				case 5: // '\005'
					if (cell.getCellType() == 1)
						cbg.setSurfaceType(Converter.getSurfaceType(cell.getStringCellValue()));
					break;

				case 6: // '\006'
					if (cell.getCellType() == 0)
						cbg.setWaferThickness(Double.valueOf(cell.getNumericCellValue()));
					break;

				case 7: // '\007'
					if (cell.getCellType() == 1)
						cbg.setWaferDimention(cell.getStringCellValue());
					break;

				case 8: // '\b'
					if (cell.getCellType() == 1)
						cbg.setNote(cell.getStringCellValue());
					break;

				case 9: // '\t'
					if (cell.getCellType() == 0)
						stock.setColumn((int) cell.getNumericCellValue());
					break;

				case 10: // '\n'
					if (cell.getCellType() == 0)
						stock.setShelf((int) cell.getNumericCellValue());
					break;

				case 11: // '\013'
					if (cell.getCellType() == 0)
						stock.setBox((int) cell.getNumericCellValue());
					cbg.setStock(stock);
					break;

				case 12: // '\f'
					if (cell.getCellType() == 0)
						cbg.setM2x((new Double(cell.getNumericCellValue())).toString());
					if (cell.getCellType() == 1)
						cbg.setM2x(cell.getStringCellValue());
					break;

				case 13: // '\r'
					if (cell.getCellType() == 0)
						cbg.setM2y((new Double(cell.getNumericCellValue())).toString());
					if (cell.getCellType() == 1)
						cbg.setM2y(cell.getStringCellValue());
					break;

				case 14: // '\016'
					if (!((cell.getCellType() == 0) | (cell.getCellType() == 2)))
						break;
					try {
						cbg.setDispersion(Round.value(cell.getNumericCellValue(), 2));
					} catch (Exception exception) {
					}
					break;

				case 15: // '\017'
					if (cell.getCellType() == 1)
						cbg.setStatus(Converter.getStatus(cell.getStringCellValue()));
					break;
				}
			}

		}

		return stockList;
	}

	public CBG grabCBG(String waferID, String recFilePath) {
		ExcelParserApache parser = new ExcelParserApache(recFilePath);
		CBG grabedCBG = new CBG();
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			CBG cbg = (CBG) iterator.next();
			if (cbg.getWaferID() != null && cbg.getWaferID().equals(waferID)) {
				grabedCBG = cbg;
				break;
			}
		}

		grabedCBG.setRecFilePath(recFilePath);
		addFiles(grabedCBG);
		List lensSet = (new LensesParser(parser)).getLenses();
		grabedCBG.setLenses(lensSet);
		Project project = (new ProjectParser(grabedCBG.getProject(), parser)).getProject();
		grabedCBG.setProject(project);
		Exposure exposure = (new ExposureParser(parser)).getExposure();
		grabedCBG.setDosage(exposure.getDosage());
		grabedCBG.setRecTime(exposure.getRecTime());
		by.ruslan.hologram.entity.Development development = (new DevelopmentParser(parser)).getDevelopment();
		grabedCBG.setDevelopment(development);
		String diffractedBeamQualityReport = PropertiesManager.getProperty("diffractedBeamPath").concat(project.getName()).concat("/").concat(grabedCBG.getWaferID());
		DiffractedBeam diffractedBeam = new DiffractedBeam(diffractedBeamQualityReport);
		grabedCBG.setDiffractedBeam(diffractedBeam);
		return grabedCBG;
	}

	private void addFiles(CBG grabedCBG) {
		String waferID = grabedCBG.getWaferID();
		String recImagesPath = PropertiesManager.getProperty("cbgRecFiles").concat("RecImages/").concat(waferID);
		
		Collection exposedAndDeveloped = (new Filewalker()).walkAllDir(PropertiesManager.getProperty("exposedAndDevelopedPath").concat(waferID), "^(.*?)(bmp)");
		if (exposedAndDeveloped != null) {
			List list = new ArrayList(exposedAndDeveloped);
			Collections.sort(list, new by.ruslan.hologram.utill.SortComparator.ByFileName());
			grabedCBG.setExposedAndDeveloped(list);
		}
		
		Collection recImgList = (new Filewalker()).walkOneDir(recImagesPath, "^(.*?)(jpg)");
		Collection recBeamList = (new Filewalker()).walkOneDir(recImagesPath.concat("/RecBeam"), "^(.*?)(jpg)");
		if (recImgList != null && recBeamList != null) {
			List recImgList2 = new ArrayList(recImgList);
			Collections.sort(recImgList2, new by.ruslan.hologram.utill.SortComparator.ByFileName());
			List recBeamList2 = new ArrayList(recBeamList);
			Collections.sort(recBeamList2, new by.ruslan.hologram.utill.SortComparator.ByFileName());
			Map recImages = new HashMap();
			recImages.put("recImg", recImgList2);
			recImages.put("recBeam", recBeamList2);
			grabedCBG.setRecordingImages(recImages);
		}
	}
}
