import java.lang.Object;
import java.util.Scanner;
import java.awt.Color;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class Player {
	
	 int player1Click = -1;
    int points = 0;
    int player1Click;
    Color pieceColor;
    Square[][] pieces = new Square [1][1];
    boolean firstTurnP = true;
    boolean firstTurnC = true;

//======================================================================================================================

    public Player(Color color){
        pieceColor = color;
        
        pieces[0][0] = new Square();
        
    }

    //temp default constructor to fix computer heirarchy problems
    public Player(){
       
    }
//======================================================================================================================

    public Board playerTurn(Board board , Gui frame, Color color) {

        //prompt player to pick a piece
        System.out.println("Pick a piece to play.");

        //player chooses piece
        //until GUI this will be a random piece
        Square[] player1Pick = pieces[0];
        
        while(!frame.getPickReady() && frame.piecePick == -1)
        {
        	try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
        }
        
        player1Click = frame.getPiecePick();

        //player chooses location for piece
        int player1XPick = frame.getXPick();
        int player1YPick = frame.getYPick();
        
        frame.pickReady = false;
        

//      player1Pick[0].setXY(player1XPick, player1YPick);
  
       
		switch(player1Click) {
     	case 1: player1Pick = pieceOne(player1XPick, player1YPick);
     		break;
    
     	case 2: player1Pick = pieceTwo(player1XPick, player1YPick);
     		break;
     		
     	case 3: player1Pick = pieceThree(player1XPick, player1YPick);
     		break;
     		
     	case 4: player1Pick = pieceFour(player1XPick, player1YPick);
     		break;
     		
     	case 5: player1Pick = pieceFive(player1XPick, player1YPick);
     		break;
     	
     	case 6: player1Pick = pieceSix(player1XPick, player1YPick);
 			break;
 		
     	case 7: player1Pick = pieceSeven(player1XPick, player1YPick);
 			break;
 		
     	case 8: player1Pick = pieceEight(player1XPick, player1YPick);
 			break;
 		
     	case 9: player1Pick = pieceNine(player1XPick, player1YPick);
 			break;
 		
     	case 10: player1Pick = pieceTen(player1XPick, player1YPick);
 			break;
 		
     	case 11: player1Pick = pieceEleven(player1XPick, player1YPick);
 			break;
 		
     	case 12: player1Pick = pieceTwelve(player1XPick, player1YPick);
 			break;
 		
     	case 13: player1Pick = pieceThirteen(player1XPick, player1YPick);
 			break;
 		
     	case 14: player1Pick = pieceFourteen(player1XPick, player1YPick);
 			break;
 			
     	case 15: player1Pick = pieceFifteen(player1XPick, player1YPick);
 			break;
 		
     	case 16: player1Pick = pieceSixteen(player1XPick, player1YPick);
 			break;
 		
     	case 17: player1Pick = pieceSeventeen(player1XPick, player1YPick);
 			break;
 		
     	case 18: player1Pick = pieceEighteen(player1XPick, player1YPick);
 			break;
 		
     	case 19: player1Pick = pieceNineteen(player1XPick, player1YPick);
 			break;
 		
     	case 20: player1Pick = pieceTwenty(player1XPick, player1YPick);
 			break;
 		
     	case 21: player1Pick = pieceTwentyOne(player1XPick, player1YPick);
 			break;
 		
 		
     }

        //validate location
        boolean canPlacePiece = checkPlacement(player1XPick, player1YPick, player1Pick, board, color);

        
        if (canPlacePiece){
        	
            board = placePiece(player1XPick, player1YPick, player1Pick, pieceColor, board);
            
        }else{
            System.out.println("The piece does not fit there.");
        }
        

        while (!canPlacePiece){

        	 while(!frame.getPickReady() && frame.piecePick == -1)
             {
             	try {
     				TimeUnit.SECONDS.sleep(1);
     			} catch (InterruptedException e) {
     				
     				e.printStackTrace();
     			}
             }
             
        	 player1Click = frame.getPiecePick();
             

             //player chooses location for piece
             player1XPick = frame.getXPick();
             player1YPick = frame.getYPick();
             
             frame.pickReady = false;
             

             //player1Pick[0].setXY(player1XPick, player1YPick);
             
             switch(player1Click) {
          	case 1: player1Pick = pieceOne(player1XPick, player1YPick);
          		break;
         
          	case 2: player1Pick = pieceTwo(player1XPick, player1YPick);
          		break;
          		
          	case 3: player1Pick = pieceThree(player1XPick, player1YPick);
          		break;
          		
          	case 4: player1Pick = pieceFour(player1XPick, player1YPick);
          		break;
          		
          	case 5: player1Pick = pieceFive(player1XPick, player1YPick);
          		break;
          	
          	case 6: player1Pick = pieceSix(player1XPick, player1YPick);
      			break;
      		
          	case 7: player1Pick = pieceSeven(player1XPick, player1YPick);
      			break;
      		
          	case 8: player1Pick = pieceEight(player1XPick, player1YPick);
      			break;
      		
          	case 9: player1Pick = pieceNine(player1XPick, player1YPick);
      			break;
      		
          	case 10: player1Pick = pieceTen(player1XPick, player1YPick);
      			break;
      		
          	case 11: player1Pick = pieceEleven(player1XPick, player1YPick);
      			break;
      		
          	case 12: player1Pick = pieceTwelve(player1XPick, player1YPick);
      			break;
      		
          	case 13: player1Pick = pieceThirteen(player1XPick, player1YPick);
      			break;
      		
          	case 14: player1Pick = pieceFourteen(player1XPick, player1YPick);
      			break;
      			
          	case 15: player1Pick = pieceFifteen(player1XPick, player1YPick);
      			break;
      		
          	case 16: player1Pick = pieceSixteen(player1XPick, player1YPick);
      			break;
      		
          	case 17: player1Pick = pieceSeventeen(player1XPick, player1YPick);
      			break;
      		
          	case 18: player1Pick = pieceEighteen(player1XPick, player1YPick);
      			break;
      		
          	case 19: player1Pick = pieceNineteen(player1XPick, player1YPick);
      			break;
      		
          	case 20: player1Pick = pieceTwenty(player1XPick, player1YPick);
      			break;
      		
          	case 21: player1Pick = pieceTwentyOne(player1XPick, player1YPick);
      			break;
      		
      		
          }
            //validate location
            canPlacePiece = checkPlacement(player1XPick, player1YPick, player1Pick, board, color);
        }
        //update pieces array
        //currently does nothing because we only have one piece which is a single square
        removePiece(player1Pick);
       

        System.out.println("Player 1 went.");
        return board;


    }

    public Board computerTurn(Board board, Color color) {
    	Square[] computerPiece = pieces[0];
    	int x,y;
    	x = computerPick();
    	y = computerPick();
    	
        boolean canPlacePiece = checkPlacement(x, y, computerPiece, board, color);

    	if (canPlacePiece) {
    		board = placePiece(x, y, computerPiece, pieceColor, board);
    	}
    	while(!canPlacePiece) {
    		x = computerPick();
    		y = computerPick();
    		
    		canPlacePiece = checkPlacement(x, y, computerPiece, board, color);
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

    boolean checkPlacement(int x, int y, Square[] piece, Board board, Color color){

    	if (firstTurnP && color == color.RED) {
            for (int i = 0; i < piece.length; i++) {
            	if(piece[i].xLoc == 0 && piece[i].yLoc == 19) {
            		firstTurnP = false;
            		return true;
            		
            	}
            }
    	}
    	
    	if (firstTurnC && color == color.BLUE) {
            for (int i = 0; i < piece.length; i++) {
            	if(piece[i].xLoc == 19 && piece[i].yLoc == 0) {
            		firstTurnC = false;
            		return true;
            		
            	}
            }
    	}
        if (!board.getTaken(x, y)){
            for (int i = 0; i < piece.length; i++) {
                if(!Board.getTaken(piece[i].xLoc, piece[i].yLoc) && piece[i].xLoc >= 0 && piece[i].yLoc >= 0 && piece[i].xLoc <= 19 && piece[i].yLoc <= 19){
                	if(Board.getTaken(piece[i].xLoc + 1, piece[i].yLoc + 1) || Board.getTaken(piece[i].xLoc + 1, piece[i].yLoc - 1) || Board.getTaken(piece[i].xLoc - 1, piece[i].yLoc + 1) || Board.getTaken(piece[i].xLoc - 1, piece[i].yLoc - 1)) {
                    	if(Board.getColor(piece[i].xLoc + 1, piece[i].yLoc + 1) == color || Board.getColor(piece[i].xLoc + 1, piece[i].yLoc - 1) == color ||Board.getColor(piece[i].xLoc - 1, piece[i].yLoc + 1) == color ||Board.getColor(piece[i].xLoc - 1, piece[i].yLoc - 1) == color) {
                    		
                		}
                    	else {
                    		return false;
                    	}
                	}
                	else {
                		return false;
                	}
                }
                else {
                	return false;
                }
            }
        }
        else {
            return false;
        }
       
        return true;
        
    }

    //also adds points
    Board placePiece(int x, int y, Square[] piece, Color color, Board board) {
        for (Square s : piece){
        	 
        	board.setSquare(s.xLoc, s.yLoc, color);
        	
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
    
    Square[] pieceSeventeen (int locationX,int locationY) {
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










