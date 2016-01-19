//irshusdock & arkessler
//Class for a board state. Includes the locations of pieces in the board, checks on if pops have been used, and whose turn it is

public class Board {

	private int[][] board; //The board. 0s represent empty spaces, 1's represent player 1's pieces, 2's represent player 2's pieces
	private int rows; //The number of rows on the board
	private int cols; //The number of cols on the board
	private boolean player1popused; //Boolean for if player 1 has used their pop
	private boolean player2popused; //Boolean for if player 2 has used their pop
	private int turn; //Whos turn it is. 1 represents player 1's turn, 2 represents player 2's turn
	private int n; //The number of pieces in a row to win
	private int evaluationValue; //The evaluation value of the current board state
	
	//Constants
	private final static int P1 = 1;
	private final static int P2 = 2;
	
	//Constructor for creating a new board.
	//rows is the number of rows on the board
	//cols is the number of cols on the board
	//n is the number of pieces in a row to win
	public Board(int rows, int cols, int n){
		this.rows = rows;
		this.cols = cols;
		this.board = new int[rows][cols]; //will be filled with 0s initially
		this.n = n;
		this.player1popused = false;
		this.player2popused = false;
		this.evaluationValue = 0;
	}
	
	//Constructor when the game is in progress
	//rows is the number of rows on the board
	//cols is the number of cols on the board
	//board is the board state to start with
	//n is the number of pieces in a row to win
	//turn is whos turn it is
	//player1popused is the boolean if player 1 has used their pop
	//player2popused is the boolean if player 2 has used their pop
	public Board(int rows, int cols, int[][] board, int n, int turn, boolean player1popused, boolean player2popused){
		this.rows = rows;
		this.cols = cols;
		this.board = board;
		this.n = n;
		this.turn = turn;
		this.player1popused = player1popused;
		this.player2popused = player2popused;
		this.evaluationValue = 0;
	}
	
	//Updated the board to account for making the passed move 
	public void makeMove(Move move){
	
		//TODO if the move type is pop
		//If the move type of is put
		if (move.getType() == 1){
			int row = this.findOpenRow(move.getCol());
			this.board[row][move.getCol()] = this.turn;
		}
	}
	//Find the first open row in the passed column and return its index. Return -1 if column is full
	public int findOpenRow(int col){
		int lastOpen = -1;
		
		for(int i = 0; i < rows; i++){
			if(board[i][col] != 0)
				break;
			lastOpen = i;
			}
		
		return lastOpen;
	}
	
	//Evaluation function for the current board state
	public int evaluate(){
		return 1;
	}
	
	//Getter for the board
	public int[][] getBoardArray(){
		return board;
	}
	
	//Getter for turn
	public int getTurn(){
		return turn;
	}
	
	//Setter for turn
	public void setTurn(int turn)
	{
		this.turn = turn;
	}
	
	public int getRows()
	{
		return this.rows;
	}
	
	public int getCols()
	{
		return this.cols;
	}
}


