package by.ruslan.hologram.utill;

import java.io.File;
import java.io.FilenameFilter;

public class FindFile {

	public FindFile() {
	}

	public static File[] getFileList(String filePath, final String searchString) {
		File dir = new File(filePath);
		File fileList[] = dir.listFiles(new FilenameFilter() {

			public boolean accept(File dir, String name) {
				return name.contains(searchString);
			}
		});
		return fileList;
	}
}
