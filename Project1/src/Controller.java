import java.util.Scanner;
import java.util.StringTokenizer;

public class Controller {
	
	private static final String PLAYER_NAME = "Godzilla";
	private static final String PLAYER_1 = "player1: ";
	private static final int P1 = 1;
	private static final int P2 = 2;
	private static boolean isPlayer1;
	private static int firstPlayer;
	private static int timeLimit;

	public static void main(String[] args) {
		//Call controller loop
		playGame();
	}
	
	public static Board instantiateGame(){
		Scanner scan = new Scanner(System.in);
		
		//Output our player name
		System.out.println(PLAYER_NAME);
		
		String playerAssignments = scan.nextLine();
		String initialParameters = scan.nextLine();
		
		isPlayer1 = false;
		
		//Determine if we are player1 or player2
		int ourAssignmentLength = PLAYER_1.length() + PLAYER_NAME.length();
		for(int i = 0; i < (playerAssignments.length() - (ourAssignmentLength - 1)); i++){
			if(playerAssignments.substring(i, i+17).equals(PLAYER_1 + PLAYER_NAME)){
				isPlayer1 = true;
				break;
			}
		}
		
		//Parse the initial parameters
		StringTokenizer tokenizer = new StringTokenizer(initialParameters);
		int rows = Integer.parseInt(tokenizer.nextToken());
		int cols = Integer.parseInt(tokenizer.nextToken());
		int n = Integer.parseInt(tokenizer.nextToken());
		firstPlayer = Integer.parseInt(tokenizer.nextToken());
		timeLimit = Integer.parseInt(tokenizer.nextToken());
		
		Board initialBoard = new Board(rows, cols, n);
		initialBoard.setTurn(1);
		return initialBoard;
	}
	
	public static void playGame(){
		Board board = instantiateGame();
		Scanner scan = new Scanner(System.in);
		
		//Determine if it is intially our turn
		boolean isOurTurn = (isPlayer1 && firstPlayer == P1) || (!isPlayer1 && firstPlayer == P2); 
		
		//When it is our turn, evaluate the best option and print out the move it is correlated with
		//When it is our opponents turn, wait for their move, update the board, then 
		
	}

}
