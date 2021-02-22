import java.util.Scanner;

public class GameMaster {
	static Scanner scanner = new Scanner(System.in);
	static Player p1 = new Player(1);
	static Player p2 = new Player(2);
	static int p1Win = 0;
	static int p2Win = 0;
	
	
	
	
	
	private static void startGameCenter() {
		System.out.println("Hi! Welcome to Game Center!\nRight now we have two games. TicTacToe and Order and Chaos.");
		System.out.println("If you want to player TicTacToe, type T.");
		System.out.println("If you want to player Order and Chaos, type O.");
		Player winner = null;
		Game newGame = null;
		boolean NotvalidChoice = true;
		
		while(NotvalidChoice) {
			String userInput = scanner.nextLine();
			if(userInput.equals("T") || userInput.equals("O")) {
				NotvalidChoice = false;
				if(userInput.equals("T")){
					System.out.println("Welcome to TicTacToe!");
					//Ask player to pick size, throw error if size is wrong
					System.out.println("TicTacToe is held on a nxn board, what would you want the size to be? The maximum is 100 and the minumum is 3.");
					System.out.println("Something like this: 3");
					boolean keepTrying = true;
					while(keepTrying) {
						try {
				    		String sizeStr = scanner.nextLine();
				    		int size = Integer.valueOf(sizeStr);
							newGame = new TTT(size, size, p1, p2);
							keepTrying = false;
						} catch(Exception e) {
							System.out.println("The input size is not valid or out of limit. Try again.");
							continue;
						}
						
					}

				} else {
					System.out.println("Welcome to Order and Chaos!");
					System.out.println("In this implementation, only 5 in a row would be allowed.");
					newGame = new OAC(p1, p2);
				}
				winner = newGame.startGame();
				
				if(winner == null) {
					System.out.println("It's a draw");
				} else {
					System.out.println("winner is Player" + winner.getPlayerID());
					//Increase win count.
					if(winner.getPlayerID() == 1) {
						p1Win++;
					} else {
						p2Win++;
					}
				}
			} else {
				System.out.println("Invald choice of game. Choose again!");
				
			} 

		}

		
	}
	
	private static void printGameResult() {
		System.out.println("Player 1 has won " + p1Win + " times.");
		System.out.println("Player 2 has won " + p2Win + " times.");
		
		if(p1Win > p2Win) {
			System.out.println("Congratulations Player1!");
		} else if(p2Win > p1Win) {
			System.out.println("Congratulations Player2!");
		} else {
			System.out.println("The final result is a draw!");
		}
		
	}

	public static void main(String[] args) {
		
		boolean stillPlay = true;
		
		while(stillPlay) {
			startGameCenter();
        	//Check if user wants to quit.
    		System.out.println("Would you like to play again? Enter Y if yes, enter any other key to quit.");
    		String quitResponse = scanner.nextLine();
    		if(!quitResponse.equals("Y")  && !quitResponse.equals("y")) {
    			stillPlay = false;
    		}
		}
		printGameResult();
		System.out.println("Thank you for choosing Game Center! Goodbye!");
		
	}


}
