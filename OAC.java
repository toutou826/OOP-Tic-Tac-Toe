
public class OAC extends Game{
	
	
	//The value here is hard coded because for OAC, the rol and col num is set to 6, the winning number is 6 as well.
	public OAC(Player p1, Player p2) {
		super(6, 6, 5, p1, p2, new OACSpecs());
	}



	@Override
	public Player checkWinner() {
		//If there are n consecutive, then return order.
		if(nConsec() && lastPlayer == p1) {
			return p1;
		//If there are no consecutive, and the board is full, return chaos;
		} else if(board.isFull()) {
			return p2;
		} else {
			return null;
		}
	}


}
