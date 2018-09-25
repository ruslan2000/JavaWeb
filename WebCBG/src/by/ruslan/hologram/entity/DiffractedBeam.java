package by.ruslan.hologram.entity;

public class DiffractedBeam {

	public DiffractedBeam(String diffractedBeamQualityReport) {
		setBeamQualityReport(diffractedBeamQualityReport);
	}

	public String getBeamQualityReport() {
		return diffractedBeamQualityReport;
	}

	public void setBeamQualityReport(String beamQualityReport) {
		diffractedBeamQualityReport = beamQualityReport;
	}

	private String diffractedBeamQualityReport;
}
