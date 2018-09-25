package by.ruslan.hologram.utill;

public class Trim {

	public static String waferID(String waferID) {
		String delim = "-";
		String tokens[] = waferID.split(delim);
		if (tokens.length > 1)
			waferID = tokens[0].concat("-").concat(tokens[1]);
		if (tokens.length > 2 && tokens[2].matches("\\d+"))
			waferID = waferID.concat("-").concat(tokens[2]);
		return waferID;
	}
}
