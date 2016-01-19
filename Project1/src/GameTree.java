import java.util.ArrayList;

public class GameTree {
	//Variables
	private Board rootBoard;		//Variable containing the starting board state
	private int maxEvaluatedDepth = 0;
	ArrayList<ArrayList<ArrayList<Board>>> fullTree;
	
	//Constants
	private final static int MAX_NUMBER_BRANCHES = 10000; 
	
	//Base constructor
	public GameTree(Board root)
	{
		this.rootBoard = root;
	}
	
	public ArrayList<Board> generateBoards(Board board, int originalDepth)
	{
		ArrayList<Board> generatedBoards = new ArrayList<Board>();
		ArrayList<Move> availableMoves = generateMoves(board);
		for (int i = 0; i < availableMoves.size(); i ++)
		{
			Move currMove = availableMoves.get(i);
			Board newBoard = applyMove(board, currMove);
			generatedBoards.add(newBoard);
		}
		return generatedBoards;
	}
	
	public ArrayList<Move> generateMoves(Board board)
	{
		ArrayList<Move> tempVal = new ArrayList<Move>();
		return tempVal;
	}

	public Board applyMove(Board board, Move move)
	{
		return new Board(0, 0, 0);
	}
	
	public void evaluateToDepth(int newDepth)
	{
		if (newDepth < maxEvaluatedDepth)
			return;
		else
		{
			
		}
	}
}
