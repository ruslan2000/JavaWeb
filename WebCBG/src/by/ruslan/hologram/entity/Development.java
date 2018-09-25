package by.ruslan.hologram.entity;

import java.util.*;

import by.ruslan.hologram.utill.PropertiesManager;

public class Development {

	private List devSteps;
	private String developmentPath;

	public static class DevelopmentStep {

		public String toString() {
			return (new StringBuilder(String.valueOf(devName))).append("\t").append(furnace).toString();
		}

		private static int stepsNumber = 0;
		private String devName;
		private String furnace;

		public DevelopmentStep(String devName, String furnace) {
			this.devName = "";
			this.furnace = "";
			this.devName = devName;
			this.furnace = furnace;
			stepsNumber++;
		}
	}

	public Development() {
		devSteps = new ArrayList();
		developmentPath = PropertiesManager.getProperty("developmentPath");
	}

	public void setDevStep(String devName, String furnace) {
		devSteps.add(new DevelopmentStep(devName, furnace));
	}

	public List getDevSteps() {
		return devSteps;
	}

	public DevelopmentStep getDevStep(int index) {
		return (DevelopmentStep) devSteps.get(index);
	}

	public String getDevelopmentPath() {
		return developmentPath;
	}

	public void setDevelopmentPath(String developmentPath) {
		this.developmentPath = developmentPath;
	}

	public int getStepsNumber() {
		return DevelopmentStep.stepsNumber;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Iterator iterator = devSteps.iterator(); iterator.hasNext(); sb.append("\n")) {
			DevelopmentStep developmentStep = (DevelopmentStep) iterator.next();
			sb.append(developmentStep.toString());
		}

		return sb.toString();
	}

}
