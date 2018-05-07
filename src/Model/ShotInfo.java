package Model;
/**
 * this class simply holds a location coordinate for board and whether it is a hit
 * @author Mohammad Nafis
 * @version 1.0
 * @since 04-20-2018
 */
public class ShotInfo {

	/**
	 * the Y-axis of the coordinate
	 */
	private int row;
	
	/**
	 * the X-axis of the coordinate
	 */
	private int column;
	
	/**
	 * holds the boolean whether it is a hit
	 */
	private boolean hitStatus;
	
	/**
	 * returns the Y-axis
	 * @return row
	 */
	public int getRow() {
		return row;
	}

	/**
	 * returns the X-axis
	 * @return column
	 */
	public int getColumn() {
		return column;
	}

	/**
	 * returns the hitStatus
	 * @return hitStatus
	 */
	public boolean getHitStatus() {
		return hitStatus;
	}

	/**
	 * this is the constructor
	 * @param hitStatus boolean whether it is a hit
	 * @param row Y-axis
	 * @param column X-axis
	 */
	public ShotInfo(boolean hitStatus, int row, int column) {
		this.hitStatus = hitStatus;
		this.row = row;
		this.column = column;
	}
	
}
