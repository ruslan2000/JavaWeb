package by.ruslan.hologram.entity;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import by.ruslan.hologram.entity.enums.GratingType;
import by.ruslan.hologram.entity.enums.Status;

public class CBG extends Hologram {
	
	private static final long serialVersionUID = 1L;
	private String m2x;
	private String m2y;
	private List<Lens> lenses;
	private Map recordingImages;
	private Collection exposedAndDeveloped;
	private double dispersion;
	private Stock stock;

	public CBG() {
		setGratingType(GratingType.CBG);
		setStatus(Status.NA);
	}

	public List<Lens> getLenses() {
		return lenses;
	}

	public void setLenses(List lenses) {
		this.lenses = lenses;
	}

	public String getM2x() {
		return m2x;
	}

	public void setM2x(String m2x) {
		this.m2x = m2x;
	}

	public String getM2y() {
		return m2y;
	}

	public void setM2y(String m2y) {
		this.m2y = m2y;
	}

	public Collection getExposedAndDeveloped() {
		return exposedAndDeveloped;
	}

	public void setExposedAndDeveloped(Collection exposedAndDeveloped) {
		this.exposedAndDeveloped = exposedAndDeveloped;
	}

	public Map getRecordingImages() {
		return recordingImages;
	}

	public void setRecordingImages(Map recordingImages) {
		this.recordingImages = recordingImages;
	}

	public String toString() {
		return (new StringBuilder("CBG [m2x=")).append(m2x).append(", m2y=").append(m2y).append(", lenses=")
				.append(lenses).append(", recordingImages=").append(recordingImages).append(", exposedAndDeveloped=")
				.append(exposedAndDeveloped).append(", toString()=").append(super.toString()).append("]").toString();
	}

	public double getDispersion() {
		return dispersion;
	}

	public void setDispersion(double dispersion) {
		this.dispersion = dispersion;
	}

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

}
