package by.ruslan.hologram.utill;

import by.ruslan.hologram.entity.enums.*;

public class Converter {

	public static GratingType getGratingType(String string) {
		GratingType type = GratingType.UNKNOWN;
		if (string.equals("CBG"))
			type = GratingType.CBG;
		else if (string.equals("RBG"))
			type = GratingType.RBG;
		return type;
	}

	public static SurfaceType getSurfaceType(String string) {
		SurfaceType type = SurfaceType.UNKNOWN;
		if (string.toLowerCase().contains("ground"))
			type = SurfaceType.GROUND;
		else if (string.toLowerCase().contains("arc"))
			type = SurfaceType.ARC;
		else if (string.toLowerCase().contains("pol"))
			type = SurfaceType.POLISHED;
		return type;
	}

	public static Bleached getBleached(String string) {
		if (string.equals("Y"))
			return Bleached.Yes;
		else
			return Bleached.No;
	}

	public static M2Method getM2Method(String string) {
		M2Method method = M2Method.UNDEFINED;
		if (string.toLowerCase().contains("sigma"))
			method = M2Method.D4_SIGMA;
		else if (string.toLowerCase().contains("slit"))
			method = M2Method.D_SLIT;
		return method;
	}

	public static Status getStatus(String string) {
		Status status = Status.NA;
		if (string.equalsIgnoreCase("Ready to go"))
			status = Status.READY_TO_GO;
		if (string.equalsIgnoreCase("In stock"))
			status = Status.IN_STOCK;
		return status;
	}
}
