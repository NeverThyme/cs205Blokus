import java.lang.Object;
import java.util.Scanner;
import java.awt.Color;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class Player {

    int points = 0;
    Color pieceColor;
    Square[][] pieces = new Square [1][1];

//======================================================================================================================

    public Player(Color color){
        pieceColor = color;
        
        pieces[0][0] = new Square();
        
    }

    //temp default constructor to fix computer heirarchy problems
    public Player(){
       
    }
//======================================================================================================================

    public Board playerTurn(Board board , Gui frame) {

        //prompt player to pick a piece
        System.out.println("Pick a piece to play.");

        //player chooses piece
        //until GUI this will be a random piece
        Square[] player1Pick = pieces[0];
        
        while(!frame.getPickReady())
        {
        	try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
        }
        
        

        //player chooses location for piece
        int player1XPick = frame.getXPick();
        int player1YPick = frame.getYPick();
        
        frame.pickReady = false;
        
        player1Pick[0].setXY(player1XPick, player1YPick);
     

        //validate location
        boolean canPlacePiece = checkPlacement(player1XPick, player1YPick, player1Pick, board);

        
        if (canPlacePiece){
        	
            board = placePiece(player1XPick, player1YPick, player1Pick, pieceColor, board);
            
        }else{
            System.out.println("The piece does not fit there.");
        }
        

        while (!canPlacePiece){

        	 while(!frame.getPickReady())
             {
             	try {
     				TimeUnit.SECONDS.sleep(1);
     			} catch (InterruptedException e) {
     				
     				e.printStackTrace();
     			}
             }
             
             

             //player chooses location for piece
             player1XPick = frame.getXPick();
             player1YPick = frame.getYPick();
             
             frame.pickReady = false;
             
             player1Pick[0].setXY(player1XPick, player1YPick);

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
    Square[] pieceOne (int locationX,int locationY) {
    	Square[] tempPiece = new Square[1];
    	tempPiece[0] = new Square(locationX,locationY);
    	return tempPiece;
    
    }

    Square[] pieceTwo (int locationX,int locationY) {
    	Square[] tempPiece = new Square[2];
    	tempPiece[0] = new Square(locationX,locationY);
    	tempPiece[1] = new Square(locationX+1,locationY);
    	return tempPiece;
    }
    
    Square[] pieceThree (int locationX,int locationY) {
    	 
    	Square[] tempPiece = new Square[3];
    	tempPiece[0] = new Square(locationX,locationY);
    	tempPiece[1] = new Square(locationX+1,locationY);
    	tempPiece[2] = new Square(locationX+1,locationY+1);
    	return tempPiece;
    	
    	
    }
    
    Square[] pieceFour (int locationX,int locationY) {
    	Square[] tempPiece = new Square[3];
    	tempPiece[0] = new Square(locationX,locationY);
    	tempPiece[1] = new Square(locationX+1,locationY);
    	tempPiece[2] = new Square(locationX-1,locationY);
    	return tempPiece;
    }
    
    Square[] pieceFive (int locationX,int locationY) {
    	Square[] tempPiece = new Square[4];
    	tempPiece[0] = new Square(locationX,locationY);
    	tempPiece[1] = new Square(locationX+1,locationY);
    	tempPiece[2] = new Square(locationX+1,locationY+1);
    	tempPiece[3] = new Square(locationX,locationY+1);
    	return tempPiece;
    }
    
    Square[] pieceSix (int locationX,int locationY) {
    	Square[] tempPiece = new Square[4];
    	tempPiece[0] = new Square(locationX,locationY);
    	tempPiece[1] = new Square(locationX+1,locationY+1);
    	tempPiece[2] = new Square(locationX-1,locationY+1);
    	tempPiece[3] = new Square(locationX,locationY+1);
    	return tempPiece;
    }
    
    Square[] pieceSeven (int locationX,int locationY) {
    	Square[] tempPiece = new Square[4];
    	tempPiece[0] = new Square(locationX,locationY);
    	tempPiece[1] = new Square(locationX-1,locationY);
    	tempPiece[2] = new Square(locationX+1,locationY+1);
    	tempPiece[3] = new Square(locationX+2,locationY+1);
    	return tempPiece;
    }
    
    Square[] pieceEight (int locationX,int locationY) {
    	Square[] tempPiece = new Square[4];
    	tempPiece[0] = new Square(locationX,locationY);
    	tempPiece[1] = new Square(locationX-1,locationY);
    	tempPiece[2] = new Square(locationX+1,locationY);
    	tempPiece[3] = new Square(locationX+1,locationY-1);
    	return tempPiece;
    	
    }
    
    Square[] pieceNine (int locationX,int locationY) {
    	Square[] tempPiece = new Square[4];
    	tempPiece[0] = new Square(locationX,locationY);
    	tempPiece[1] = new Square(locationX-1,locationY);
    	tempPiece[2] = new Square(locationX,locationY-1);
    	tempPiece[3] = new Square(locationX+1,locationY-1);
    	return tempPiece;
    	
    }

    Square[] pieceTen (int locationX,int locationY) {
    	Square[] tempPiece = new Square[5];
    	tempPiece[0] = new Square(locationX,locationY);
    	tempPiece[1] = new Square(locationX-1,locationY);
    	tempPiece[2] = new Square(locationX-1,locationY-1);
    	tempPiece[3] = new Square(locationX+1,locationY);
    	tempPiece[4] = new Square(locationX+2,locationY);
    	return tempPiece;
    	
    }
    
    Square[] pieceEleven (int locationX,int locationY) {
    	Square[] tempPiece = new Square[5];
    	tempPiece[0] = new Square(locationX,locationY);
    	tempPiece[1] = new Square(locationX,locationY-1);
    	tempPiece[2] = new Square(locationX,locationY-2);
    	tempPiece[3] = new Square(locationX-1,locationY);
    	tempPiece[4] = new Square(locationX+1,locationY);
    	return tempPiece;
    }
    
    Square[] pieceTwelve (int locationX,int locationY) {
    	Square[] tempPiece = new Square[5];
    	tempPiece[0] = new Square(locationX,locationY);
    	tempPiece[1] = new Square(locationX,locationY-1);
    	tempPiece[2] = new Square(locationX,locationY-2);
    	tempPiece[3] = new Square(locationX+1,locationY);
    	tempPiece[4] = new Square(locationX+2,locationY);
    	return tempPiece;
    	
    }
    
    Square[] pieceThirteen (int locationX,int locationY) {
    	Square[] tempPiece = new Square[5];
    	tempPiece[0] = new Square(locationX,locationY);
    	tempPiece[1] = new Square(locationX-1,locationY);
    	tempPiece[2] = new Square(locationX,locationY-1);
    	tempPiece[3] = new Square(locationX+1,locationY-1);
    	tempPiece[4] = new Square(locationX+2,locationY-1);
    	return tempPiece;
    	
    }
    
    Square[] pieceFourteen (int locationX,int locationY) {
    	Square[] tempPiece = new Square[5];
    	tempPiece[0] = new Square(locationX,locationY);
    	tempPiece[1] = new Square(locationX-1,locationY);
    	tempPiece[2] = new Square(locationX+1,locationY);
    	tempPiece[3] = new Square(locationX-1,locationY+1);
    	tempPiece[4] = new Square(locationX+1,locationY-1);
    	return tempPiece;
    	
    }
    
    Square[] pieceFifteen (int locationX,int locationY) {
    	Square[] tempPiece = new Square[5];
    	tempPiece[0] = new Square(locationX,locationY);
    	tempPiece[1] = new Square(locationX,locationY-1);
    	tempPiece[2] = new Square(locationX,locationY-2);
    	tempPiece[3] = new Square(locationX,locationY+1);
    	tempPiece[4] = new Square(locationX,locationY+2);
    	return tempPiece;
    	
    }
    
    Square[] pieceSixteen (int locationX,int locationY) {
    	Square[] tempPiece = new Square[5];
    	tempPiece[0] = new Square(locationX,locationY);
    	tempPiece[1] = new Square(locationX,locationY-1);
    	tempPiece[2] = new Square(locationX,locationY+1);
    	tempPiece[3] = new Square(locationX+1,locationY);
    	tempPiece[4] = new Square(locationX+1,locationY+1);
    	return tempPiece;
    	
    }
    
    Square[] peiceSeventeen (int locationX,int locationY) {
    	Square[] tempPiece = new Square[5];
    	tempPiece[0] = new Square(locationX,locationY);
    	tempPiece[1] = new Square(locationX-1,locationY);
    	tempPiece[2] = new Square(locationX-1,locationY+1);
    	tempPiece[3] = new Square(locationX,locationY-1);
    	tempPiece[4] = new Square(locationX+1,locationY-1);
    	return tempPiece;
    	
    }
    
    Square[] pieceEighteen (int locationX,int locationY) {
    	Square[] tempPiece = new Square[5];
    	tempPiece[0] = new Square(locationX,locationY);
    	tempPiece[1] = new Square(locationX,locationY-1);
    	tempPiece[2] = new Square(locationX+1,locationY-1);
    	tempPiece[3] = new Square(locationX,locationY+1);
    	tempPiece[4] = new Square(locationX+1,locationY+1);
    	return tempPiece;
    	
    }
    
    Square[] pieceNineteen (int locationX,int locationY) {
    	Square[] tempPiece = new Square[5];
    	tempPiece[0] = new Square(locationX,locationY);
    	tempPiece[1] = new Square(locationX-1,locationY);
    	tempPiece[2] = new Square(locationX,locationY+1);
    	tempPiece[3] = new Square(locationX,locationY-1);
    	tempPiece[4] = new Square(locationX+1,locationY-1);
    	return tempPiece;
    	
    }
    
    Square[] pieceTwenty (int locationX,int locationY) {
    	Square[] tempPiece = new Square[5];
    	tempPiece[0] = new Square(locationX,locationY);
    	tempPiece[1] = new Square(locationX-1,locationY);
    	tempPiece[2] = new Square(locationX+1,locationY);
    	tempPiece[3] = new Square(locationX,locationY-1);
    	tempPiece[4] = new Square(locationX,locationY+1);
    	return tempPiece;
    	
    }
    
    Square[] pieceTwentyOne (int locationX,int locationY) {
    	Square[] tempPiece = new Square[5];
    	tempPiece[0] = new Square(locationX,locationY);
    	tempPiece[1] = new Square(locationX,locationY-1);
    	tempPiece[2] = new Square(locationX-1,locationY);
    	tempPiece[3] = new Square(locationX+1,locationY);
    	tempPiece[4] = new Square(locationX+2,locationY);
    	return tempPiece;
    	
    }
   Square[] rotateRight(Square[] piece) {
    	
    	for (int i=1;i<piece.length;i++){
    		int relativeCoordinateX = piece[i].xLoc - piece[0].xLoc;
    		int relativeCoordinateY = piece[i].yLoc - piece[0].yLoc;
    		int temp=relativeCoordinateX;
    		relativeCoordinateX=-relativeCoordinateY;
    		relativeCoordinateY=temp;
    		piece[i].xLoc = piece[0].xLoc + relativeCoordinateX;
    		piece[i].yLoc = piece[0].yLoc + relativeCoordinateY;
    	}
    	return piece;
    }
   
   Square[] mirrorPiece(Square[] piece) {
	   for (int i=1;i<piece.length;i++){
		   int relativeCoordinateX = piece[i].xLoc - piece[0].xLoc;
		   int temp=relativeCoordinateX;
		   relativeCoordinateX=-relativeCoordinateX;
		   relativeCoordinateX=temp;
		   piece[i].xLoc = piece[0].xLoc + relativeCoordinateX;
	   }
   	return piece;
   }
}










