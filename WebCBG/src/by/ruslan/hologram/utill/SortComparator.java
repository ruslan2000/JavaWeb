package by.ruslan.hologram.utill;

import java.io.File;
import java.util.Comparator;

import by.ruslan.hologram.entity.CBG;
import by.ruslan.hologram.entity.Hologram;

public class SortComparator {
	
	public static class ByDispersion implements Comparator {

		public int compare(CBG c1, CBG c2) {
			if (c1.getDispersion() > c2.getDispersion())
				return 1;
			else
				return c1.getDispersion() < c2.getDispersion() ? -1 : 0;
		}

		public int compare(Object obj, Object obj1) {
			return compare((CBG) obj, (CBG) obj1);
		}

		public ByDispersion() {
		}
	}

	public static class ByFWHM implements Comparator {

		public int compare(Hologram h1, Hologram h2) {
			if (h1.getFWHM() > h2.getFWHM())
				return 1;
			else
				return h1.getFWHM() < h2.getFWHM() ? -1 : 0;
		}

		public int compare(Object obj, Object obj1) {
			return compare((Hologram) obj, (Hologram) obj1);
		}

		public ByFWHM() {
		}
	}

	public static class ByFileName implements Comparator {

		public int compare(File f1, File f2) {
			return f1.getName().compareTo(f2.getName());
		}

		public int compare(Object obj, Object obj1) {
			return compare((File) obj, (File) obj1);
		}

		public ByFileName() {
		}
	}

	public static class ByProjectID implements Comparator {

		public int compare(Hologram h1, Hologram h2) {
			return h1.getProject().compareTo(h2.getProject());
		}

		public int compare(Object obj, Object obj1) {
			return compare((Hologram) obj, (Hologram) obj1);
		}

		public ByProjectID() {
		}
	}

	public static class ByProjectName implements Comparator {

		public int compare(Hologram h1, Hologram h2) {
			return h1.getProject().getName().compareTo(h1.getProject().getName());
		}

		public int compare(Object obj, Object obj1) {
			return compare((Hologram) obj, (Hologram) obj1);
		}

		public ByProjectName() {
		}
	}

	public static class ByRecDate implements Comparator {

		public int compare(Hologram h1, Hologram h2) {
			return h1.getRecDate().compareTo(h2.getRecDate());
		}

		public int compare(Object obj, Object obj1) {
			return compare((Hologram) obj, (Hologram) obj1);
		}

		public ByRecDate() {
		}
	}

	public static class ByStatus implements Comparator {

		public int compare(Hologram h1, Hologram h2) {
			return h1.getStatus().compareTo(h2.getStatus());
		}

		public int compare(Object obj, Object obj1) {
			return compare((Hologram) obj, (Hologram) obj1);
		}

		public ByStatus() {
		}
	}

	public static class BySurfaceType implements Comparator {

		public int compare(Hologram h1, Hologram h2) {
			return h1.getSurfaceType().compareTo(h2.getSurfaceType());
		}

		public int compare(Object obj, Object obj1) {
			return compare((Hologram) obj, (Hologram) obj1);
		}

		public BySurfaceType() {
		}
	}

	public static class ByWaferID implements Comparator {

		public int compare(Hologram h1, Hologram h2) {
			return h1.getWaferID().compareTo(h2.getWaferID());
		}

		public int compare(Object obj, Object obj1) {
			return compare((Hologram) obj, (Hologram) obj1);
		}

		public ByWaferID() {
		}
	}

	public static class ByWavelength implements Comparator {

		public int compare(Hologram h1, Hologram h2) {
			if (h1.getProject().getCWL() > h2.getProject().getCWL())
				return 1;
			else
				return h1.getProject().getCWL() < h2.getProject().getCWL() ? -1 : 0;
		}

		public int compare(Object obj, Object obj1) {
			return compare((Hologram) obj, (Hologram) obj1);
		}

		public ByWavelength() {
		}
	}

}
