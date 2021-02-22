import java.util.Scanner;

public abstract class Game {
	protected Board board;
	protected boolean hasWinner;
	private Player winner;
	protected int winNum;
	private Scanner scanner = new Scanner(System.in);
	protected Player p1;
	protected Player p2;
	protected Player lastPlayer;
	private int[] lastMovedIndex;
	
	public Game(int row, int col, int winNum, Player p1, Player p2, Specs spec) {
		this.board = new Board(row, col, spec);
		this.winNum = winNum;
		this.p1 = p1;
		this.p2 = p2;
		
	}
	
	public Player startGame() {
		board.printBoard();
    	System.out.println("Enter your move by specifying the row and column number. \nThe row and col numbers starts from 0");
    	System.out.println("Something like this: 1,2");
    	int playerCounter = 0;
		while(winner == null && !BoardisFull()) {
			Player nextUp = (playerCounter == 0? p1 : p2);
			playerCounter ^= 1;
			System.out.println("Next up: Player" + nextUp.getPlayerID());
    		boolean validInput = false;
    		
    		//Continue to ask for user input until the input is valid.
    		while(!validInput) {
        		
        		try {
        			String userInput = scanner.nextLine();
        			String[] twoNumbers = userInput.split(",", 2);
        	        int row = Integer.parseInt(twoNumbers[0]);
        	        int col = Integer.parseInt(twoNumbers[1]);
        	        //Check if it is a valid move.
        	        if(board.isValid(row, col)){
        	        	//Update Board, flip counter.
                		board.makeMove(row, col, nextUp);
                		board.printBoard();
                		lastPlayer = nextUp;
                		lastMovedIndex = new int[] {row, col};
                		winner = checkWinner();
                		validInput = true;
        	        	
        	        }
        	        else{
        	        	System.out.println("Can't go there!");
        	        }
        		}
        		
        		catch(Exception e){
        			System.out.println(e.getMessage());
        			System.out.println("Invalid input!, Try again!");
        			continue;
        			
        		}
    			
    		}
			
		}
		//When we break out of the loop, winner would either be set to the winner or null, which means it's a draw.s
		return winner;
	}
	
	//The winning requirement for each game is different.
	abstract public Player checkWinner(); 
	
	
	//But the full requirement is the same: when the board is full
	public boolean BoardisFull() {
		return board.isFull();
	}
	
	
	//Check if there is n consective in a row/col/diag
	public boolean nConsec() {
		boolean nConsec = true;
		int lastRow = lastMovedIndex[0];
		int lastCol = lastMovedIndex[1];
		
		return checkRow(lastRow, lastCol) || checkCol(lastRow, lastCol) || checkDiag(lastRow, lastCol) || checkAntiDiag(lastRow, lastCol);	
	}

	
	
	private boolean checkRow(int lastRow, int lastCol) {
		int nCon = 0;
		
		//Go left and right to check if neighbors match.
		for(int i = lastCol; i < board.getColNum(); i++) {
			if(board.getCell(lastRow, i) == lastPlayer.getPlayerID()) {
				nCon++;
			} else {
				break;
			}
		}
		
		for(int i = lastCol-1; i >= 0; i--) {
			if(board.getCell(lastRow, i) == lastPlayer.getPlayerID()) {
				nCon++;
			} else {
				break;
			}
		}
		
		return nCon == winNum;
	}
	
	
	//Go up and down to see if neighbors match
	private boolean checkCol(int lastRow, int lastCol) {
		int nCon = 0;

			for(int i = lastRow; i < board.getRowNum(); i++) {
				if(board.getCell(i, lastCol) == lastPlayer.getPlayerID()) {
					nCon++;
				} else {
					break;
				}		
			}
			
			for(int i = lastRow-1; i >= 0; i--) {
				if(board.getCell(i, lastCol) == lastPlayer.getPlayerID()) {
					nCon++;
				} else {
					break;
				}		
			}
			
			

		return nCon == winNum;
	}
	
	//Go diagonally to check if match.
	private boolean checkDiag(int lastRow, int lastCol) {
		int nCon = 0;
		int rUp = lastRow;
		int cUp = lastCol;
		while(rUp < board.getRowNum() && cUp < board.getRowNum()) {
			if(board.getCell(rUp, cUp) == lastPlayer.getPlayerID()) {
				nCon++;
				cUp++;
				rUp++;
			} else {
				break;
			}
		}
		
		int rDown = lastRow-1;
		int cDown = lastCol-1;
		
		while(rDown >= 0 && cDown >= 0) {
			if(board.getCell(rDown, cDown) == lastPlayer.getPlayerID()) {
				nCon++;
				cDown--;
				rDown--;
			} else {
				break;
			}
		}
	
	
		return nCon == winNum;
	}
	
	
	//Go Antidiagonally to check if match.
	private boolean checkAntiDiag(int lastRow, int lastCol) {
		int nCon = 0;

		int rUp = lastRow;
		int cUp = lastCol;
		while(rUp < board.getRowNum() && cUp >= 0) {
			if(board.getCell(rUp, cUp) == lastPlayer.getPlayerID()) {
				nCon++;
				cUp--;
				rUp++;
			} else {
				break;
			}
		}
		
		int rDown = lastRow-1;
		int cDown = lastCol+1;
		
		while(rDown >= 0 && cDown < board.getColNum()) {
			if(board.getCell(rDown, cDown) == lastPlayer.getPlayerID()) {
				nCon++;
				cDown++;
				rDown--;
			} else {
				break;
			}
		}
	
	
		return nCon == winNum;
	}
	

}
