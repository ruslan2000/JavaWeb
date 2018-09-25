package by.ruslan.hologram.utill;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class PrintFiles extends SimpleFileVisitor {

	public PrintFiles() {
	}

	public FileVisitResult visitFile(Path file, BasicFileAttributes attr) {
		if (attr.isSymbolicLink())
			System.out.format("Symbolic link: %s ", new Object[] { file });
		else if (attr.isRegularFile())
			System.out.format("Regular file: %s ", new Object[] { file });
		else
			System.out.format("Other: %s ", new Object[] { file });
		System.out.println((new StringBuilder("(")).append(attr.size()).append("bytes)").toString());
		return FileVisitResult.CONTINUE;
	}

	public FileVisitResult postVisitDirectory(Path dir, IOException exc) {
		System.out.format("Directory: %s%n", new Object[] { dir });
		return FileVisitResult.CONTINUE;
	}

	public FileVisitResult visitFileFailed(Path file, IOException exc) {
		System.err.println(exc);
		return FileVisitResult.CONTINUE;
	}
}
