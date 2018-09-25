package by.ruslan.hologram.entity;

public class Stock {
	
	private int column;
	private int shelf;
	private int box;

	public Stock(int column, int shelf, int box) {
		setColumn(column);
		setShelf(shelf);
		setBox(box);
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public int getShelf() {
		return shelf;
	}

	public void setShelf(int shelf) {
		this.shelf = shelf;
	}

	public int getBox() {
		return box;
	}

	public void setBox(int box) {
		this.box = box;
	}

	public String toString() {
		return (new StringBuilder("Column ")).append(column).append(", Shelf ").append(shelf).append(", Box ")
				.append(box).toString();
	}

}
