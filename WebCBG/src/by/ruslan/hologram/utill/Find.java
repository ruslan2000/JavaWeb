package by.ruslan.hologram.utill;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class Find
{
    public static class Finder extends SimpleFileVisitor
    {

        void find(Path file)
        {
            Path name = file.getFileName();
            if(name != null && matcher.matches(name))
            {
                numMatches++;
                System.out.println(file);
            }
        }

        void done()
        {
            System.out.println((new StringBuilder("Matched: ")).append(numMatches).toString());
        }

        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
        {
            find(file);
            return FileVisitResult.CONTINUE;
        }

        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs)
        {
            find(dir);
            return FileVisitResult.SKIP_SUBTREE;
        }

        public FileVisitResult visitFileFailed(Path file, IOException exc)
        {
            System.err.println(exc);
            return FileVisitResult.CONTINUE;
        }

        private final PathMatcher matcher;
        private int numMatches;

        Finder(String pattern)
        {
            numMatches = 0;
            matcher = FileSystems.getDefault().getPathMatcher((new StringBuilder("glob:")).append(pattern).toString());
        }
    }



    public static void main(String args[])
        throws IOException
    {
        Path startingDir = Paths.get(PropertiesManager.getProperty("cbgRecFiles"), new String[0]);
        String pattern = "*D06-40*";
        Finder finder = new Finder(pattern);
        Files.walkFileTree(startingDir, finder);
        finder.done();
    }
}
