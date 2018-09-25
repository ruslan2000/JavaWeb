package by.ruslan.hologram.entity;

import by.ruslan.hologram.entity.enums.GratingType;

public class Project {
	
	private int id;
	private String name;
	private GratingType type;
	private String productCode;
	private double CWL;
	private double CWLtolerance;
	private String FWHM;
	private String streatchingFactor;
	private String gratingAperture;
	private String gratingThickness;
	private String DE;
	private int gratingQuantity;
	
	public Project() {
		
	}

	public Project(int id, String name, GratingType type, String productCode, double CWL, double CWLtolerance,
			String FWHM, String streatchingFactor, String gratingAperture, String gratingThickness, String DE,
			int gratingQuantity) {
		setID(id);
		setName(name);
		setType(type);
		setProductCode(productCode);
		setCWL(CWL);
		setCWLtolerance(CWLtolerance);
		setFWHM(FWHM);
		setStreatchingFactor(streatchingFactor);
		setGratingAperture(gratingAperture);
		setGratingThickness(gratingThickness);
		setDE(DE);
		setGratingQuantity(gratingQuantity);
	}

	public int getID() {
		return id;
	}

	public void setID(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public GratingType getType() {
		return type;
	}

	public void setType(GratingType type) {
		this.type = type;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public double getCWL() {
		return CWL;
	}

	public void setCWL(double CWL) {
		this.CWL = CWL;
	}

	public double getCWLtolerance() {
		return CWLtolerance;
	}

	public void setCWLtolerance(double CWLtolerance) {
		this.CWLtolerance = CWLtolerance;
	}

	public String getFWHM() {
		return FWHM;
	}

	public void setFWHM(String FWHM) {
		this.FWHM = FWHM;
	}

	public String getStreatchingFactor() {
		return streatchingFactor;
	}

	public void setStreatchingFactor(String streatchingFactor) {
		this.streatchingFactor = streatchingFactor;
	}

	public String getGratingAperture() {
		return gratingAperture;
	}

	public void setGratingAperture(String gratingAperture) {
		this.gratingAperture = gratingAperture;
	}

	public String getGratingThickness() {
		return gratingThickness;
	}

	public void setGratingThickness(String gratingThickness) {
		this.gratingThickness = gratingThickness;
	}

	public String getDE() {
		return DE;
	}

	public void setDE(String DE) {
		this.DE = DE;
	}

	public int getGratingQuantity() {
		return gratingQuantity;
	}

	public void setGratingQuantity(int gratingQuantity) {
		this.gratingQuantity = gratingQuantity;
	}

	@Override
	public int hashCode() {
		int prime = 31;
		int result = 1;
		long temp = Double.doubleToLongBits(CWL);
		result = 31 * result + (int) (temp ^ temp >>> 32);
		temp = Double.doubleToLongBits(CWLtolerance);
		result = 31 * result + (int) (temp ^ temp >>> 32);
		result = 31 * result + (DE == null ? 0 : DE.hashCode());
		result = 31 * result + (FWHM == null ? 0 : FWHM.hashCode());
		result = 31 * result + (gratingAperture == null ? 0 : gratingAperture.hashCode());
		result = 31 * result + gratingQuantity;
		result = 31 * result + (gratingThickness == null ? 0 : gratingThickness.hashCode());
		result = 31 * result + id;
		result = 31 * result + (name == null ? 0 : name.hashCode());
		result = 31 * result + (productCode == null ? 0 : productCode.hashCode());
		result = 31 * result + (streatchingFactor == null ? 0 : streatchingFactor.hashCode());
		result = 31 * result + (type == null ? 0 : type.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Project other = (Project) obj;
		if (Double.doubleToLongBits(CWL) != Double.doubleToLongBits(other.CWL))
			return false;
		if (Double.doubleToLongBits(CWLtolerance) != Double.doubleToLongBits(other.CWLtolerance))
			return false;
		if (DE == null) {
			if (other.DE != null)
				return false;
		} else if (!DE.equals(other.DE))
			return false;
		if (FWHM == null) {
			if (other.FWHM != null)
				return false;
		} else if (!FWHM.equals(other.FWHM))
			return false;
		if (gratingAperture == null) {
			if (other.gratingAperture != null)
				return false;
		} else if (!gratingAperture.equals(other.gratingAperture))
			return false;
		if (gratingQuantity != other.gratingQuantity)
			return false;
		if (gratingThickness == null) {
			if (other.gratingThickness != null)
				return false;
		} else if (!gratingThickness.equals(other.gratingThickness))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (productCode == null) {
			if (other.productCode != null)
				return false;
		} else if (!productCode.equals(other.productCode))
			return false;
		if (streatchingFactor == null) {
			if (other.streatchingFactor != null)
				return false;
		} else if (!streatchingFactor.equals(other.streatchingFactor))
			return false;
		return type == other.type;
	}

	@Override
	public String toString() {
		return (new StringBuilder("Project [id=")).append(id).append(", name=").append(name).append(", type=")
				.append(type).append(", productCode=").append(productCode).append(", CWL=").append(CWL)
				.append(", CWLtolerance=").append(CWLtolerance).append(", FWHM=").append(FWHM)
				.append(", streatchingFactor=").append(streatchingFactor).append(", gratingAperture=")
				.append(gratingAperture).append(", gratingThickness=").append(gratingThickness).append(", DE=")
				.append(DE).append(", gratingQuantity=").append(gratingQuantity).append("]").toString();
	}


	public int compareTo(Project other) {
		if (other != null)
			return getName().toLowerCase().compareTo(other.getName().toLowerCase());
		else
			return -1;
	}
}
