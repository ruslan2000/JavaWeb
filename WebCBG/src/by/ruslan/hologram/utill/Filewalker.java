package by.ruslan.hologram.utill;

import java.io.File;
import java.util.Collection;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.*;

public class Filewalker {

	public Collection walkOneDir(String path, String regex) {
		return walk(path, regex, FalseFileFilter.FALSE);
	}

	public Collection walkAllDir(String path, String regex) {
		return walk(path, regex, DirectoryFileFilter.INSTANCE);
	}

	private Collection walk(String path, String regex, IOFileFilter filter) {
		File dir = new File(path);
		Collection files = null;
		if (dir.isDirectory())
			files = FileUtils.listFiles(dir, new RegexFileFilter(regex), filter);
		return files;
	}
}
