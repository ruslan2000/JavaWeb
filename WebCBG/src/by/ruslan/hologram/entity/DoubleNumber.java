package by.ruslan.hologram.entity;

public abstract class DoubleNumber {

	public DoubleNumber(double value) {
		setValue(value);
	}

	public double getValue() {
		return value;
	}

	private void setValue(double value) {
		this.value = value;
	}

	private double value;
}
