package by.ruslan.hologram.utill;

public class Round {

	public static double value(double val, int places) {
		if (places < 0) {
			throw new IllegalArgumentException();
		} else {
			long factor = (long) Math.pow(10D, places);
			val *= factor;
			long tmp = Math.round(val);
			return (double) tmp / (double) factor;
		}
	}
}
