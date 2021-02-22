
public class Board {
	private int rowNum;
	private int colNum;
	private int movesMade;
//	private Player lastMoved;
//	private int[] lastMovedIndex;
	private Cell[][] cells;
	
	public Board(int row, int col, Specs spec) {
		if(row >= spec.minRow && row <= spec.maxRow && col >= spec.minCol && col <= spec.maxCol && (spec.sameSize ? row==col: true)) {
			rowNum = row;
			colNum = col;	
			//Initialize 2D arrays of Cells
//			System.out.println(row + " " + col);
			cells = new Cell[row][col];
			for(int i = 0; i < rowNum; i++) {
				for(int j = 0; j < colNum; j++) {
					cells[i][j] = new Cell();
				}
			}
		} else {
			//If the row and col size do not meet requirement, throw exception.
			throw new IllegalArgumentException("The row and col number is not acceptable!");
		}
	}
	
	
	public boolean isValid(int row, int col) {
		return (row < rowNum && row >= 0)&&(col < colNum && col >= 0) && (cells[row][col].getItem() == -1 );
	}

	
	
	public void printBoard() {
		StringBuilder sb = new StringBuilder();
		
		//Top line
		sb.append("+");
		for(int i = 0; i < colNum; i++) {
			sb.append("--+");
		}
		sb.append("\n");
		
		
		
		for(int i = 0; i < rowNum; i++) {
			sb.append("|");
			
			for(int j = 0; j < colNum; j++) {
				sb.append(" " + (this.cells[i][j].getItem() == -1? " " : this.cells[i][j].getItem()));
				sb.append("|");
			}
			
			sb.append("\n");
			
			
			//Mid line
			sb.append("+");
			for(int k = 0; k < colNum; k++) {
				sb.append("--+");
			}
			sb.append("\n");
			
		}
		
		
		System.out.println(sb.toString());
		
		
	}



	public boolean isFull() {
		return movesMade == rowNum * colNum;
	}


	public void makeMove(int row, int col, Player nextUp) {
		cells[row][col].makeMove(nextUp.getPlayerID());
		movesMade++;
//		lastMoved = nextUp;
//		lastMovedIndex = new int[] {row, col};
		
	}

//
//	public Player getLastMoved() {
//		return lastMoved;
//	}
//
//
//	public int[] getLastMovedIndex() {
//		return lastMovedIndex;
//	}
//	
//	

	public int getRowNum() {
		return this.rowNum;
	}
	
	
	public int getColNum() {
		return this.colNum;
	}
	
	public int getCell(int row, int col) {
		return cells[row][col].getItem();
	}
	

}
