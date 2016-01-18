//irshusdock & arkessler
//Class for storing a move. Moves are denoted by a move type and the column the move will be in
public class Move {
	
	private int type;	//The type of the move, 1 is put, 0 is pop
	private int col; 	//The column number of the move
	
	//Constructor
	//type is the type of move
	//col is the column number
	public Move(int type, int col){
		this.type = type;
		this.col = col;
	}
	
	//Getter for the type of the move
	public int getType(){
		return type;
	}
	
	//Getter for the col of the move
	public int getCol(){
		return col;
	}
	

}
