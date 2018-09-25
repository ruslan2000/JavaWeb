package by.ruslan.hologram.entity;

import by.ruslan.hologram.entity.enums.GratingSide;
import by.ruslan.hologram.entity.enums.M2Direction;

public class M2 extends DoubleNumber {

	private M2Direction direction;
	private GratingSide side;

	public M2(double value) {
		super(value);
	}

	public M2Direction getDirection() {
		return direction;
	}

	public void setDirection(M2Direction direction) {
		this.direction = direction;
	}

	public GratingSide getSide() {
		return side;
	}

	public void setSide(GratingSide side) {
		this.side = side;
	}

	@Override
	public String toString() {
		return (new StringBuilder(" M2")).append(direction).append(" ").append(side).append(" ").append(getValue())
				.toString();
	}

}
