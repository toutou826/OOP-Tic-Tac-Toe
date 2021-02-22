// A template for the specification class
public abstract class Specs {
	final int maxRow;
	final int maxCol;
	final int minRow;
	final int minCol;
	final boolean sameSize;

	
	
	//I will set the specification in the subclass
	protected Specs(int maxRow, int maxCol, int minRow, int minCol, boolean sameSize) {
		this.maxRow = maxRow;
		this.maxCol = maxCol;
		this.minRow = minRow;
		this.minCol = minCol;
		this.sameSize = sameSize;

	}
	
}
