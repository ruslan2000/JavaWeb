package by.ruslan.hologram.utill.plot;

import java.io.*;
import java.util.*;

public class Reader {

	public Reader() {
	}

	public static double[][] read(String filePath) {
		return readData(filePath);
	}

	private static double[][] readData(String fileName) {
		double A[][] = null;
		try {
			List m2xy = new ArrayList();
			File input = new File(fileName);
			Scanner scanLine;
			List row;
			for (scanLine = new Scanner(input); scanLine.hasNext(); m2xy.add(row)) {
				int j = 0;
				row = new ArrayList();
				String line = scanLine.nextLine();
				for (Scanner scanNumber = new Scanner(line); scanNumber.hasNextDouble();) {
					row.add(j, Double.valueOf(scanNumber.nextDouble()));
					j++;
				}

			}

			List innerList = (List) m2xy.get(0);
			int rows = m2xy.size();
			int columns = innerList.size();
			A = new double[rows][columns];
			for (int i = 0; i < rows; i++)
				if (!((List) m2xy.get(i)).isEmpty()) {
					for (int j = 0; j < columns; j++) {
						A[i][j] = ((Double) ((List) m2xy.get(i)).get(j)).doubleValue();
						System.out.println(((List) m2xy.get(i)).get(j));
					}

				}

			scanLine.close();
		} catch (FileNotFoundException e) {
			System.out.println(e);
		}
		return A;
	}
}
