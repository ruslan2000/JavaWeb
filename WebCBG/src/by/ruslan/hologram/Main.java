package by.ruslan.hologram;

import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;

import by.ruslan.hologram.entity.CBG;
import by.ruslan.hologram.factory.CbgBuilder;
import by.ruslan.hologram.utill.ExcelParserApache;
import by.ruslan.hologram.utill.PropertiesManager;
import by.ruslan.hologram.utill.Trim;

public class Main {

	public Main() {
	}

	public static void main(String args[]){
		System.out.println("Hello World");
		
		//System.out.println(System.getProperty("user.dir").concat("\\src\\properties\\text.properties"));

		System.out.println(System.getProperty("user.dir"));
		
	}
}
