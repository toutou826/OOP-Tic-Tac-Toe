
public class TTT extends Game{


	
	//The winning number should be the minimum of row and col.
	public TTT(int row, int col, Player p1, Player p2) {
		super(row, col, Math.min(row, col), p1, p2, new TTTSpecs());
	}
	


	@Override
	public Player checkWinner() {
		if(nConsec()) {
			return lastPlayer;
		} else {
			return null;
		}
	}
	
	


	



}
