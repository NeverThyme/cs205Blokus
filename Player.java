import java.lang.Object;
import java.util.Scanner;
import java.awt.Color;
import java.util.concurrent.ThreadLocalRandom;

public class Player {

    int points = 0;
    Color pieceColor;
    Square[][] pieces = new Square [1][1];

//======================================================================================================================

    public Player(Color color){
        pieceColor = color;
        

    }

    //temp default constructor to fix computer heirarchy problems
    public Player(){
       
    }
//======================================================================================================================

    public Board playerTurn(Board board) {

        //prompt player to pick a piece
        System.out.println("Pick a piece to play.");

        //player chooses piece
        //until GUI this will be a random piece
        Square[] player1Pick = pieces[0];

        //player chooses location for piece
        int player1XPick = chooseXLocation();
        int player1YPick = chooseYLocation();

     

        //validate location
        boolean canPlacePiece = checkPlacement(player1XPick, player1YPick, player1Pick, board);

        
        if (canPlacePiece){
        	
            board = placePiece(player1XPick, player1YPick, player1Pick, pieceColor, board);
            
        }else{
            System.out.println("The piece does not fit there.");
        }
        

        while (!canPlacePiece){

            player1XPick = chooseXLocation();
            player1YPick = chooseYLocation();

            //validate location
            canPlacePiece = checkPlacement(player1XPick, player1YPick, player1Pick, board);
        }
        //update pieces array
        //currently does nothing because we only have one piece which is a single square
        removePiece(player1Pick);
       

        System.out.println("Player 1 went.");
        return board;


    }

    public Board computerTurn(Board board) {
    	Square[] computerPiece = pieces[0];
    	int x,y;
    	x = computerPick();
    	y = computerPick();
    	
        boolean canPlacePiece = checkPlacement(x, y, computerPiece, board);

    	if (canPlacePiece) {
    		board = placePiece(x, y, computerPiece, pieceColor, board);
    	}
    	while(!canPlacePiece) {
    		x = computerPick();
    		y = computerPick();
    		
    		canPlacePiece = checkPlacement(x, y, computerPiece, board);
    	}
    	
    	removePiece(computerPiece);
    	System.out.println("Computer went.");
    	return board;
    }

//======================================================================================================================

    //                          FUNCTIONS

//======================================================================================================================




//////////////switched to 0-19 because our database is zero indexed

    int chooseXLocation(){
        System.out.println("Pick a column for your piece (0-19): ");
        Scanner input = new Scanner(System.in);
        int player1XPick = input.nextInt();
        while (player1XPick < 0 || player1XPick > 19){
            System.out.println("Please enter a value 0-19: ");
            player1XPick = input.nextInt();
        }
        return player1XPick;
    }
    
    int computerPick() {
    	int randNum = ThreadLocalRandom.current().nextInt(0,20);
    	return randNum;
    }


    int chooseYLocation(){
        System.out.println("Pick a row for your piece (0-19): ");
        Scanner input = new Scanner(System.in);
        int player1YPick = input.nextInt();
        while (player1YPick < 0 || player1YPick > 19){
            System.out.println("Please enter a value 0-19: ");
            player1YPick = input.nextInt();
        }
        return player1YPick;
    }

    boolean checkPlacement(int x, int y, Square[] piece, Board board){
    	
    	
            
        if (!board.getTaken(x, y)){
            for (int i = 0; i < piece.length; i++) {
                if(!Board.getTaken(x + piece[i].xLoc, y + piece[i].yLoc)){

                }else{
                    return false;
                }
            }
        }
        else{
            return false;
        }
       
        return true;
        
    }

    //also adds points
    Board placePiece(int x, int y, Square[] piece, Color color, Board board) {
        for (Square s : piece){
        	 
        	board.setSquare(s.xLoc, s.yLoc, s.color);
        	
            points += 1;

        }
        return board;
    }



    int getPoints(){
        return points;
    }

    Color getColor(){
        return pieceColor;
    }


    void setColor(Color newColor){
        pieceColor = newColor;
    }


    void removePiece(Square[] piece){
        for(int i = 0; i < pieces.length; i++){
        	
        }
            
        
    }
    
    void pieceOne (int locationX,int locationY) {
    	pieces[locationX][locationY];
    
    }

    void pieceTwo (int locationX,int locationY) {
    	pieces[locationX][locationY];
    	pieces[locationX+1][locationY];
    }
    
    void pieceThree (int locationX,int locationY) {
    	pieces[locationX][locationY];
    	pieces[locationX+1][locationY];
    	pieces[locationX+1][locationY+1];
    }
    
    void pieceFour (int locationX,int locationY) {
    	pieces[locationX][locationY];
    	pieces[locationX+1][locationY];
    	pieces[locationX-1][locationY];
    }
    
    void pieceFive (int locationX,int locationY) {
    	pieces[locationX][locationY];
    	pieces[locationX+1][locationY];
    	pieces[locationX+1][locationY+1];
    	pieces[locationX][locationY+1];
    }
    
    void pieceSix (int locationX,int locationY) {
    	pieces[locationX][locationY];
    	pieces[locationX+1][locationY+1];
    	pieces[locationX-1][locationY+1];
    	pieces[locationX][locationY+1];
    }
    
    void pieceSeven (int locationX,int locationY) {
    	pieces[locationX][locationY];
    	pieces[locationX-1][locationY];
    	pieces[locationX+1][locationY];
    	pieces[locationX+2][locationY];
    }
    
    void pieceEight (int locationX,int locationY) {
    	pieces[locationX][locationY];
    	pieces[locationX-1][locationY];
    	pieces[locationX+1][locationY];
    	pieces[locationX+1][locationY-1];
    }
    
    void pieceNine (int locationX,int locationY) {
    	pieces[locationX][locationY];
    	pieces[locationX-1][locationY];
    	pieces[locationX][locationY-1];
    	pieces[locationX+1][locationY-1];
    }

    void pieceTen (int locationX,int locationY) {
    	pieces[locationX][locationY];
    	pieces[locationX-1][locationY];
    	pieces[locationX-1][locationY-1];
    	pieces[locationX+1][locationY];
    	pieces[locationX+2][locationY];
    }
    
    void pieceEleven (int locationX,int locationY) {
    	pieces[locationX][locationY];
    	pieces[locationX][locationY-1];
    	pieces[locationX][locationY-2];
    	pieces[locationX-1][locationY];
    	pieces[locationX+1][locationY];
    }
    
    void pieceTwelve (int locationX,int locationY) {
    	pieces[locationX][locationY];
    	pieces[locationX][locationY-1];
    	pieces[locationX][locationY-2];
    	pieces[locationX+1][locationY];
    	pieces[locationX+2][locationY];
    }
    
    void pieceThirteen (int locationX,int locationY) {
    	pieces[locationX][locationY];
    	pieces[locationX-1][locationY];
    	pieces[locationX][locationY-1];
    	pieces[locationX+1][locationY-1];
    	pieces[locationX+2][locationY-1];
    }
    
    void pieceFourteen (int locationX,int locationY) {
    	pieces[locationX][locationY];
    	pieces[locationX-1][locationY];
    	pieces[locationX+1][locationY];
    	pieces[locationX-1][locationY+1];
    	pieces[locationX+1][locationY-1];
    }
    
    void pieceFifteen (int locationX,int locationY) {
    	pieces[locationX][locationY];
    	pieces[locationX][locationY-1];
    	pieces[locationX][locationY-2];
    	pieces[locationX][locationY+1];
    	pieces[locationX][locationY+2];
    }
    
    void pieceSixteen (int locationX,int locationY) {
    	pieces[locationX][locationY];
    	pieces[locationX][locationY-1];
    	pieces[locationX][locationY+1];
    	pieces[locationX+1][locationY];
    	pieces[locationX+1][locationY+1];
    }
    
    void peiceSeventeen (int locationX,int locationY) {
    	pieces[locationX][locationY];
    	pieces[locationX-1][locationY];
    	pieces[locationX-1][locationY+1];
    	pieces[locationX][locationY-1];
    	pieces[locationX+1][locationY-1];
    }
    
    void pieceEighteen (int locationX,int locationY) {
    	pieces[locationX][locationY];
    	pieces[locationX][locationY-1];
    	pieces[locationX+1][locationY-1];
    	pieces[locationX][locationY+1];
    	pieces[locationX+1][locationY+1];
    }
    
    void pieceNineteen (int locationX,int locationY) {
    	pieces[locationX][locationY];
    	pieces[locationX-1][locationY];
    	pieces[locationX][locationY+1];
    	pieces[locationX][locationY-1];
    	pieces[locationX+1][locationY-1];
    }
    
    void pieceTwenty (int locationX,int locationY) {
    	pieces[locationX][locationY];
    	pieces[locationX-1][locationY];
    	pieces[locationX+1][locationY];
    	pieces[locationX][locationY-1];
    	pieces[locationX][locationY+1];
    }
    
    void pieceTwentyOne (int locationX,int locationY) {
    	pieces[locationX][locationY];
    	pieces[locationX][locationY-1];
    	pieces[locationX-1][locationY];
    	pieces[locationX+1][locationY];
    	pieces[locationX+2][locationY];
    }
}
