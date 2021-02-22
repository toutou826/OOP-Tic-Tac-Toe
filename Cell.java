
public class Cell {
	private int item = -1;
	
	public Cell() {
	}

	public int getItem() {
		return item;
		
	}
	

	public void makeMove(int playerID) {
		this.item = playerID;
		
	}

}
