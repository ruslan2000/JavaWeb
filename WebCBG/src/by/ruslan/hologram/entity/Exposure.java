package by.ruslan.hologram.entity;

public class Exposure {

	public Exposure() {
		dosage = 0.0D;
		recTime = 0.0D;
	}

	public Exposure(double dosage, double recTime) {
		setDosage(dosage);
		setRecTime(recTime);
	}

	public double getDosage() {
		return dosage;
	}

	public void setDosage(double dosage) {
		this.dosage = dosage;
	}

	public double getRecTime() {
		return recTime;
	}

	public void setRecTime(double recTime) {
		this.recTime = recTime;
	}

	private double dosage;
	private double recTime;
}
