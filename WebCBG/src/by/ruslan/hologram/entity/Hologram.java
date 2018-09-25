package by.ruslan.hologram.entity;

/**
 * 
 * The project main entity
 * 
 * @author Ruslan
 */


import by.ruslan.hologram.entity.enums.GratingType;
import by.ruslan.hologram.entity.enums.Status;
import by.ruslan.hologram.entity.enums.SurfaceType;
import java.io.Serializable;

public class Hologram implements Comparable, Serializable {
	
	private static final long serialVersionUID = 1L;
	private int id;
	private String waferID;
	private double waferThickness;
	private String waferDimention;
	private GratingType gratingType;
	private SurfaceType surfaceType;
	private String recDate;
	private double fwhm;
	private Project project;
	private double targetCWL;
	private double testCWL;
	private double losses;
	private double ADE;
	private String note;
	private Status status;
	private double dosage;
	private double recTime;
	private Development development;
	private String recFilePath;
	private DiffractedBeam diffractedBeam;
	
	public Hologram() {
	}

	public Development getDevelopment() {
		return development;
	}

	public void setDevelopment(Development development) {
		this.development = development;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getWaferID() {
		return waferID;
	}

	public void setWaferID(String waferID) {
		this.waferID = waferID;
	}

	public Double getWaferThickness() {
		return Double.valueOf(waferThickness);
	}

	public void setWaferThickness(Double waferThickness) {
		this.waferThickness = waferThickness.doubleValue();
	}

	public String getWaferDimention() {
		return waferDimention;
	}

	public void setWaferDimention(String waferDimention) {
		this.waferDimention = waferDimention;
	}

	public GratingType getGratingType() {
		return gratingType;
	}

	public void setGratingType(GratingType gratingType) {
		this.gratingType = gratingType;
	}

	public String getRecDate() {
		return recDate;
	}

	public void setRecDate(String recDate) {
		this.recDate = recDate;
	}

	public double getTargetCWL() {
		return targetCWL;
	}

	public void setTargetCWL(double targetCWL) {
		this.targetCWL = targetCWL;
	}

	public double getTestCWL() {
		return testCWL;
	}

	public void setTestCWL(double testCWL) {
		this.testCWL = testCWL;
	}

	public int hashCode() {
		int result = 7;
		result = 31 * result + id;
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Hologram other = (Hologram) obj;
		return id == other.id;
	}

	public double getRecTime() {
		return recTime;
	}

	public void setRecTime(double recTime) {
		this.recTime = recTime;
	}

	public double getDosage() {
		return dosage;
	}

	public void setDosage(double dosage) {
		this.dosage = dosage;
	}

	public int compareTo(Hologram another) {
		return getId() < another.getId() ? -1 : 1;
	}

	public String getRecFilePath() {
		return recFilePath;
	}

	public void setRecFilePath(String recFilePath) {
		this.recFilePath = recFilePath;
	}

	public void setDiffractedBeam(DiffractedBeam diffractedBeam) {
		this.diffractedBeam = diffractedBeam;
	}

	public DiffractedBeam getDiffractedBeam() {
		return diffractedBeam;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public double getFWHM() {
		return fwhm;
	}

	public void setFWHM(double fwhm) {
		this.fwhm = fwhm;
	}

	public void setLosses(double losses) {
		this.losses = round(losses, 2);
	}

	public double getLosses() {
		return losses;
	}

	public void setADE(double ADE) {
		this.ADE = round(ADE, 2);
	}

	public double getADE() {
		return ADE;
	}

	public SurfaceType getSurfaceType() {
		return surfaceType;
	}

	public void setSurfaceType(SurfaceType surfaceType) {
		this.surfaceType = surfaceType;
	}

	public static double round(double value, int places) {
		if (places < 0) {
			throw new IllegalArgumentException();
		} else {
			long factor = (long) Math.pow(10D, places);
			value *= factor;
			long tmp = Math.round(value);
			return (double) tmp / (double) factor;
		}
	}

	@Override
	public String toString() {
		return (new StringBuilder("Hologram [id=")).append(id).append(", waferID=").append(waferID)
				.append(", waferThickness=").append(waferThickness).append(", waferDimention=").append(waferDimention)
				.append(", gratingType=").append(gratingType).append(", surfaceType=").append(surfaceType)
				.append(", recDate=").append(recDate).append(", fwhm=").append(fwhm).append(", project=")
				.append(project).append(", targetCWL=").append(targetCWL).append(", testCWL=").append(testCWL)
				.append(", note=").append(note).append(", dosage=").append(dosage).append(", recTime=").append(recTime)
				.append(", recFilePath=").append(recFilePath).append("]").toString();
	}

	@Override
	public int compareTo(Object obj) {
		return compareTo((Hologram) obj);
	}

}
