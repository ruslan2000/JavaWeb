package by.ruslan.hologram.utill;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesManager {
	
	public static String getProperty(String key) {
	
	String path = "E:\\IT\\Git\\WebRepository\\WebCBG\\src\\properties\\text.properties"; //System.getProperty("user.dir").concat("/src/properties/text.properties");
		
	Properties p = new Properties();
	
	InputStream input;
	
	String value = "";

	try {
		input = new FileInputStream(new File(path));

		p.load(input);
		
		value = p.getProperty(key);

	} catch (IOException e) {

		e.printStackTrace();
	}
	
	return value;
}
}

