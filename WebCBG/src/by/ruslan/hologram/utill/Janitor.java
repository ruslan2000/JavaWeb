package by.ruslan.hologram.utill;

public class Janitor {

	public static void clear(String data[]) {
		for (int i = 0; i < data.length; i++)
			data[i] = "";

	}

	public static void filter(double data[][]) {
		if (data != null) {
			int rows = data.length;
			int columns = data[0].length;
			for (int i = 0; i < columns; i++)
				data[rows / 4][i] = 0.0D;

		}
	}
}
