/*
 * 
 * NOTE: THIS ASSUMES OLDBOARD IS STILL CALLED BOARD
 * 
 * import java.util.ArrayList;

public class GameTree {
	//Variables
	private Board rootBoard;		//Variable containing the starting board state
	private int maxEvaluatedDepth = 0;
	ArrayList<ArrayList<ArrayList<Board>>> fullTree;
	
	//Constants
	private final static int MAX_NUMBER_BRANCHES = 10000; 
	private final static int STRATEGY_MAX = 0;
	private final static int STRATEGY_MIN = 1;
	
	//Base constructor
	public GameTree(Board root)
	{
		this.rootBoard = root;
		this.fullTree = new ArrayList<ArrayList<ArrayList<Board>>>();
	}
	
	public ArrayList<Board> generateBoards(Board board, int originalDepth)
	{
		ArrayList<Board> generatedBoards = new ArrayList<Board>();
		ArrayList<Move> availableMoves = generateMoves(board);
		for (int i = 0; i < availableMoves.size(); i ++)
		{
			Move currMove = availableMoves.get(i);
			Board newBoard = applyMove(board, currMove);
			//TODO Replace this with Board.makeMove
			generatedBoards.add(newBoard);
		}
		return generatedBoards;
	}
	
	public ArrayList<Move> generateMoves(Board board)
	{
		//TODO: Fill in
		ArrayList<Move> tempVal = new ArrayList<Move>();
		return tempVal;
	}

	public Board applyMove(Board board, Move move)
	{
		//TODO: Fill in
		//Have to add move to generated board
		return new Board(0, 0, 0);
	}
	
	public void generateToDepth(int currentDepth, int newDepth)
	{
		if (currentDepth == 0)
		{
			fullTree.clear();
			for (int i = 0; i < newDepth; i++)
			{
				fullTree.add(new ArrayList<ArrayList<Board>>());
			}
			ArrayList<Board> rootGroup = new ArrayList<Board>();
			rootGroup.add(rootBoard);
			fullTree.get(0).add(rootGroup);
		}
		// for each board state on this level, generate each move  and then evaluate to depth for each move
		//for each move, max/min(board.evaluate)
		if (currentDepth < newDepth)
		{
			for (ArrayList<Board> boardGroup : fullTree.get(currentDepth))	//Iterate through tree row at current depth
			{
				for (Board board : boardGroup)
				{
					ArrayList<Board> childBoards = generateBoards(board, currentDepth);
					fullTree.get(currentDepth+1).add(childBoards);	//Add children to next row down
					generateToDepth(currentDepth+1, newDepth);
				}
			}
		}
		else
		{
			maxEvaluatedDepth = newDepth;
			return;
		}
	
	}
	
	//When calling, always pass 0 for currentDepth
	public int evaluateTree(int currentDepth, int maxDepth, int index)
	{
		int boardCounter = 0;
		if (currentDepth < maxDepth)
		{
			int boardGroupIndex = 0;
			for (ArrayList<Board> boardGroup : fullTree.get(currentDepth))	//Iterate through tree row at current depth
			{
				for (Board board : boardGroup)
				{
					int heuristicValue = evaluateTree(currentDepth+1, maxDepth, boardCounter);
					board.setEvaluationValue(heuristicValue);
					board.getParentMove().setValue(heuristicValue); //TODO test if this work properly with memory referencing
					boardCounter++;
				}
				Board boardToReturn = boardGroup.get(0);
				for (Board currBoard : boardGroup){
					if(currBoard.getTurn() == currBoard.P2){
						if(currBoard.getEvaluationValue() > boardToReturn.getEvaluationValue()){
							boardToReturn = currBoard;
						}
					}
					else{
						if (currBoard.getEvaluationValue() < boardToReturn.getEvaluationValue()){
							boardToReturn = currBoard;
						}
					}
				}
				
				boardGroupIndex++;
			}
			//Return the min or max
		} else {
			//TODO check for possible alpha beta pruning
			ArrayList<Board> currBoardGroup = fullTree.get(currentDepth).get(index);
			int finalHeuristicValue = 0; 
			
			for (Board board: currBoardGroup)
			{
				int currBoardVal = board.evaluate();
				if (board.getTurn() == board.P2)
				{
					if (currBoardVal > finalHeuristicValue)
					{
						finalHeuristicValue = currBoardVal;
					}
				}
				else
				{
					if (currBoardVal < finalHeuristicValue)
					{
						finalHeuristicValue = currBoardVal;
					}
				}
			}
			
			return finalHeuristicValue;
		}
		return -999999;
	}
}
*/
