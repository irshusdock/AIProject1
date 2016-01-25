
//irshusdock & arkessler
//Class that controls the core gameplay loop. Handles communication with the referee

import java.util.Scanner;
import java.util.StringTokenizer;

public class Controller {

	//Variables
	private static boolean isPlayer1;
	private static int firstPlayer;
	private static int timeLimit;
	private static long timeRecieved;
	
	//Constants
	private static final String PLAYER_NAME = "Tester2";
	private static final String PLAYER_1 = "player1: ";
	private static final String WIN_STRING = "win";
	private static final String LOSE_STRING = "lose";
	private static final String DRAW_STRING = "draw";
	private static final int P1 = 1;
	private static final int P2 = 2;
	private static final long MILLI_TO_SECONDS = 1000;
	

	// Main method. Calls the core gameplay loop
	public static void main(String[] args) {
		// Call controller loop
		playGame();
	}

	// Instantiates all necessary variables for the game and handles initial, 1
	// time communication messages with the referee
	public static Board instantiateGame() {
		Scanner scan = new Scanner(System.in);

		// Output our player name
		System.out.println(PLAYER_NAME);

		// Read in player assignments
		String playerAssignments = scan.nextLine();
		// Read in the initial game parameters
		String initialParameters = scan.nextLine();
		timeRecieved = System.currentTimeMillis();

		isPlayer1 = false;

		// Determine if we are player1 or player2
		int ourAssignmentLength = PLAYER_1.length() + PLAYER_NAME.length();
		for (int i = 0; i < (playerAssignments.length() - (ourAssignmentLength - 1)); i++) {
			if (playerAssignments.substring(i, i + ourAssignmentLength).equals(PLAYER_1 + PLAYER_NAME)) {
				isPlayer1 = true;
				break;
			}
		}

		// Parse the initial parameters and set up the board accordingly
		StringTokenizer tokenizer = new StringTokenizer(initialParameters);
		int rows = Integer.parseInt(tokenizer.nextToken());
		int cols = Integer.parseInt(tokenizer.nextToken());
		int n = Integer.parseInt(tokenizer.nextToken());
		firstPlayer = Integer.parseInt(tokenizer.nextToken());
		timeLimit = Integer.parseInt(tokenizer.nextToken());

		Board initialBoard = new Board(rows, cols, n);
		initialBoard.setTurn(1); // Always assume the player going first is
									// trying to max
		scan.close();
		return initialBoard;
	}

	// Method that handles main gameplay loop. It will continue to make moves
	// and wait for its opponent until the game is terminated
	public static void playGame() {
		Board board = instantiateGame();
		Scanner scan = new Scanner(System.in);

		// Determine if it is initially our turn
		boolean isOurTurn = (isPlayer1 && firstPlayer == P1) || (!isPlayer1 && firstPlayer == P2);

		while (true) {

			// When it is our opponents turn, wait for their move, update the
			// board with their move
			if (!isOurTurn) {
				// Read in our opponents move
				String nextResponse = scan.nextLine();
				timeRecieved = System.currentTimeMillis();

				// Check if the game terminates
				if (nextResponse.equals(WIN_STRING) || nextResponse.equals(LOSE_STRING)
						|| nextResponse.equals(DRAW_STRING)) {
					return;
				}

				// Interpret our opponents move
				StringTokenizer tokenizer = new StringTokenizer(nextResponse);

				int column = Integer.parseInt(tokenizer.nextToken());
				int moveType = Integer.parseInt(tokenizer.nextToken());

				Move opponentsMove = new Move(moveType, column);

				// Update the board with our opponents move
				board.makeMove(opponentsMove);
			}

			// When it is our turn, evaluate the best option and print out the
			// move it is correlated with
			int maxDepthCounter = 1;
			Move moveToMake = new Move(1, 0);
			double endTime = timeRecieved + (MILLI_TO_SECONDS * timeLimit);

			// While we believe we still have time, run iterative minimax with
			// alpha beta prunning
			while (endTime > System.currentTimeMillis() + (Math.pow(1.3, maxDepthCounter) * MILLI_TO_SECONDS)) {
				int bestMoveVal = board.evaluateToDepth(0, maxDepthCounter, Integer.MIN_VALUE, Integer.MAX_VALUE);

				// Determine the corresponding best move based on the evaluation
				// value returned
				for (Board childBoard : board.getNextPossibleBoards()) {
					if (childBoard.getEvaluationValue() == bestMoveVal) {
						moveToMake = childBoard.getMoveToGetThisBoard();
						break;
					}
				}
				maxDepthCounter++;

				// We were unable to get depth 6-7 to run in a reasonable amount
				// of time so always stop before depth 6 is run
				if (maxDepthCounter == 6) {
					break;
				}
			}

			// Update the board with the move we decided on
			board.makeMove(moveToMake);
			// Give our move to the referee
			System.out.println(moveToMake.getCol() + " " + moveToMake.getType());
			isOurTurn = false;
			
		}

	}
}
