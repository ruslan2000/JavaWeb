package by.ruslan.hologram.entity;

import by.ruslan.hologram.entity.enums.LensType;

public class Lens {

	private String id;
	private String vendor;
	private Double focus;
	private String distance;
	private LensType type;
	private String dimention;
	
	public Lens(LensType type, String vendor, Double focus, String distance) {
		this.type = type;
		this.vendor = vendor;
		this.focus = focus;
		this.distance = distance;
	}

	public Lens() {
		id = "N/A";
		type = LensType.Convex;
		vendor = "Unknown";
		focus = Double.valueOf(0.0D);
		distance = "N/A";
		dimention = "N/A";
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public Double getFocus() {
		return focus;
	}

	public void setFocus(Double focus) {
		this.focus = focus;
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

	public LensType getType() {
		return type;
	}

	public void setType(LensType type) {
		this.type = type;
	}

	public String getDimention() {
		return dimention;
	}

	public void setDimention(String dimention) {
		this.dimention = dimention;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return (new StringBuilder("Lens [id=")).append(id).append(", vendor=").append(vendor).append(", focus=")
				.append(focus).append(", distance=").append(distance).append(", type=").append(type)
				.append(", dimention=").append(dimention).append("]").toString();
	}

}
