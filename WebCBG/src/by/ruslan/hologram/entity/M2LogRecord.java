package by.ruslan.hologram.entity;

import by.ruslan.hologram.entity.enums.Bleached;
import by.ruslan.hologram.entity.enums.M2Method;
import by.ruslan.hologram.entity.enums.SurfaceType;
import java.util.List;

public class M2LogRecord {

	private String id;
	private String projectName;
	private String gratingAperture;
	private double gratingThickness;
	private SurfaceType surfaceType;
	private Bleached bleached;
	private double centerWavelength;
	private String lightSource;
	private String beamDiameter;
	private String dateOfTest;
	private M2Method m2method;
	private String operator;
	private List records;
	private List m2List;
	private List aberrationList;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getGratingAperture() {
		return gratingAperture;
	}

	public void setGratingAperture(String gratingAperture) {
		this.gratingAperture = gratingAperture;
	}

	public double getGratingThickness() {
		return gratingThickness;
	}

	public void setGratingThickness(double gratingThickness) {
		this.gratingThickness = gratingThickness;
	}

	public SurfaceType getSurfaceType() {
		return surfaceType;
	}

	public void setSurfaceType(SurfaceType surfaceType) {
		this.surfaceType = surfaceType;
	}

	public Bleached getBleached() {
		return bleached;
	}

	public void setBleached(Bleached bleached) {
		this.bleached = bleached;
	}

	public double getCenterWavelength() {
		return centerWavelength;
	}

	public void setCenterWavelength(double centerWavelength) {
		this.centerWavelength = centerWavelength;
	}

	public String getLightSource() {
		return lightSource;
	}

	public void setLightSource(String lightSource) {
		this.lightSource = lightSource;
	}

	public String getBeamDiameter() {
		return beamDiameter;
	}

	public void setBeamDiameter(String beamDiameter) {
		this.beamDiameter = beamDiameter;
	}

	public String getDateOfTest() {
		return dateOfTest;
	}

	public void setDateOfTest(String dateOfTest) {
		this.dateOfTest = dateOfTest;
	}

	public M2Method getM2method() {
		return m2method;
	}

	public void setM2method(M2Method m2method) {
		this.m2method = m2method;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public List getRecords() {
		return records;
	}

	public void setRecords(List records) {
		this.records = records;
	}

	public List getM2List() {
		return m2List;
	}

	public void setM2List(List m2List) {
		this.m2List = m2List;
	}

	public List getAberrationList() {
		return aberrationList;
	}

	public void setAberrationList(List aberrationList) {
		this.aberrationList = aberrationList;
	}
}
