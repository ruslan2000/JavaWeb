package by.ruslan.hologram.entity;

import by.ruslan.hologram.entity.enums.GratingSide;

public class Aberration extends DoubleNumber {

	public Aberration(double value) {
		super(value);

	}

	public GratingSide getSide() {
		return side;
	}

	public void setSide(GratingSide side) {
		this.side = side;
	}

	private GratingSide side;
}
