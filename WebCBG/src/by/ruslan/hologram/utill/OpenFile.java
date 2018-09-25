package by.ruslan.hologram.utill;

import java.io.*;

public class OpenFile {

	public static void execute(String command) throws IOException {
		ProcessBuilder builder = new ProcessBuilder(new String[] { "cmd.exe", "/c", command });
		builder.redirectErrorStream(true);
		Process p = builder.start();
		BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
		do {
			String line = r.readLine();
			if (line != null)
				System.out.println((new StringBuilder("ERROR: ")).append(line).toString());
			else
				return;
		} while (true);
	}
}
