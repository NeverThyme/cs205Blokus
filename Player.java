//Player class contains teh majority of the game logic including the computer
//Robert Duarte, Kyle Michel, George Tolley, Aaron Wise

import java.lang.Object;
import java.util.Random;
import java.util.Scanner;
import java.awt.Color;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;


public class Player {
	
	//Field Init
	int compLastX;
	int compLastY;
	Square[] compLastPiece;
	int numThrough =0; 
	
	boolean placed = false;

	int player1Click = -1;
	Square[] player1Pick;
	int player1XPick = -1;
	int player1YPick = -1;
	int compPiecePick;
	
	boolean[] pieceUsed = new boolean[21];
	
	
    int points = 0;
   
    Color color;
    //[][] pieces = new Square [1][1];
    boolean firstTurnP = true;
    boolean firstTurnC = true;
    
  
//======================================================================================================================
    
    //Constructor that sets color and pieceUsed init
    public Player(Color olor){
        color = olor;
        
     //   pieces[0][0] = new Square();
        
        for(int i = 0; i < 21; i++) {
    		pieceUsed[i] = false;
    	}
        
    }

    
    public Player(){
    	 for(int i = 0; i < 21; i++) {
     		pieceUsed[i] = false;
     	}
    }
//======================================================================================================================
    
    //Method that takes a Piece, checks if it can be placed at location, and if so calls the placement method
    //Robert Duarte, Kyle Michel, George Tolley, Aaron Wise
    public Board playerTurn(Board board , Gui frame, Square[] pick, int playerPick) {
    	
    	//field init
    	player1Click = playerPick;
    	placed = false;
    	
    	if(pieceUsed[player1Click]) {
    		System.out.println("You have already used that piece.");
    		return board;
    	}

    	boolean canPlacePiece = false;
    	
    	player1Pick = pick;

        //validate location
        canPlacePiece = checkPlacement( player1Pick, board, color);
		
        //if valid calls place
        if (canPlacePiece){
        	
            board = placePiece(player1XPick, player1YPick, player1Pick, color, board);
            if (firstTurnP) {
            	if (color == Color.GREEN) {
            		board.gameBoard[0][0].color = Color.GREEN;
            	}
            		
            }
           
            placed = true;
            
        }else{
            System.out.println("The piece does not fit there.");
          //player1Pick = getPiece(frame);
        	player1Pick = pick;
        	placed = false;
        }

    	//}
    	
    	pieceUsed[player1Click] = true;
    	
       // System.out.println("Player 1 went.");
       
        return board;


    }
    
    //Computer Turn method picks piece, and placements. Then validates and places on the board
  //Robert Duarte, Kyle Michel, George Tolley, Aaron Wise
    
    public Board computerTurn(Board board, Color color) {
    	
    	numThrough = 0;
    	Square[] computerPiece;
    	//getting placement
    	int x,y;
    	x = computerXPick();
    	y = computerYPick();
    	
    	//getting piece
    	computerPiece = getPiece(x,y);
    	
    	//loop that validates and places or picks new piece and placement then repeats.
    	boolean canPlacePiece = false;
    	while(!canPlacePiece) {
    		
        canPlacePiece = checkPlacement( computerPiece, board, color);
        if(!canPlacePiece) {
        	x = computerXPick();
        	y = computerYPick();
        	computerPiece = getPiece(x,y);
        }
    	}
    	
    	boolean ex = true;
    	while(ex) {
    		try {
    			if (numThrough == 200){
    				break;
    			}
    			numThrough ++;
    			board = placePiece(x, y, computerPiece, color, board);
    			ex = false;
    		}
    		catch(Exception e) {
    			x = computerXPick();
            	y = computerYPick();
            	computerPiece = getPiece(x,y);
    		}
    	}
    	
    	
    	compLastX = x;
    	compLastY = y;
    	compLastPiece = computerPiece;
    	firstTurnC = false;
    	pieceUsed[compPiecePick] = true;
    	
    	/*
    	boolean canPlacePiece = false;
    	while(!canPlacePiece) {
    	
        canPlacePiece = checkPlacement(x, y, computerPiece, board, color);
    	//boolean canPlacePiece = true;
    	if (canPlacePiece) {
    		board = placePiece(x, y, computerPiece, color, board);
    		placed = true;
    	}
    	
    	else {
    		x = computerPick();
    		y = computerPick();
    		
    		canPlacePiece = checkPlacement(x, y, computerPiece, board, color);
    	}
    	}
    	//removePiece(computerPiece);
    	 * 
    	 * 
    	 */
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
    
    //returns x location for computer turn
  //George Tolley, Aaron Wise
    int computerXPick() {
    	
    	//First turn placement
    	if(firstTurnC) {
    	if(color == Color.BLUE) {
			return 19;
			}
		
			
		if(color == Color.YELLOW) {
			return 0;
			}
    	}
    	
    	//Otherwise random with a bias twoards previously placed pieces
    	Random rand = new Random();
    	
    	int randSquare = rand.nextInt(compLastPiece.length);
    	int oneOrTwo = rand.nextInt(2);
    	
    	if(oneOrTwo == 1) {
    		return compLastPiece[randSquare].xLoc + 1;
    	}
    	if(oneOrTwo == 2) {
    		return compLastPiece[randSquare].xLoc - 1;
    	}
    	
    	
    	int randNum = rand.nextInt(20);
    	System.out.println(randNum);
    	return randNum;
    	
    	
    	
    }
    //returns y location for computer turn same as previous method
    //George Tolley, Aaron Wise
    
int computerYPick() {
    	
	if(firstTurnC) {
    	if(color == Color.BLUE) {
			return 0;
			}
		
			
		if(color == Color.YELLOW) {
			return 19;
			}
	}
    	
    	Random rand = new Random();
    	
    	int randSquare = rand.nextInt(compLastPiece.length);
    	int oneOrTwo = rand.nextInt(2);
    	
    	if(oneOrTwo == 1) {
    		return compLastPiece[randSquare].yLoc + 1;
    	}
    	if(oneOrTwo == 2) {
    		return compLastPiece[randSquare].yLoc - 1;
    	}
    	
    	int randNum = rand.nextInt(20);
    	System.out.println(randNum);
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

    //Methods that checks the piece bieng placed against the current board state to see if it will fit
    //Aaron Wise
    
    boolean checkPlacement( Square[] piece, Board board, Color color){

    	//Checks for out of bounds or already taken
    	for (Square s : piece){
    		
    		//System.out.println(s.xLoc);
    		//System.out.println(s.yLoc);
    		
    		if (s.xLoc > 19 || s.xLoc < 0) {
    			return false;
    		}
    		
    		if (s.yLoc > 19 || s.yLoc < 0) {
    			return false;
    		}
    		
    		if(Board.getTaken(s.xLoc , s.yLoc)) {
    			return false;
    		}

    		// if not first turn
    		if(!firstTurnP) {
    			
    			boolean up = false;
				boolean left = false;
				boolean right = false;
				boolean down = false;
				
				// loop that checks which parts of a piece are a corner
    			for (int i = 0; i < piece.length; i ++) {
    					
    				
    				
    				if (piece[i].xLoc + 1 == s.xLoc && piece[i].yLoc == s.yLoc){
    					down = true;
    				}
    				
    				if (piece[i].xLoc - 1 == s.xLoc && piece[i].yLoc == s.yLoc){
    					up = true;
    				}
    				
    				if (piece[i].yLoc + 1 == s.yLoc && piece[i].xLoc == s.xLoc){
    					right = true;
    				}
    				
    				if (piece[i].yLoc - 1 == s.yLoc && piece[i].xLoc == s.xLoc){
    					left = true;
    				}

    				}
    				
    			//System.out.println(up);
    			//System.out.println(down);
    			//System.out.println(left);
    			//System.out.println(right);
    			
    			//The rest of the if statements check corners against the current state of the board. checking if taken and color of pieces on board
    			
    			if(!up && !down && !left && !right ) {
					
					try {
					if(board.getTaken(s.xLoc - 1, s.yLoc -1) && board.gameBoard[s.xLoc - 1][s.yLoc -1].color == color) {
	
							
						if(board.gameBoard[s.xLoc - 1][s.yLoc].color == color ||board.gameBoard[s.xLoc][s.yLoc-1].color == color) {
							return false;
							
						}
						else {
							return true;
						}
					
						
					}
					if(board.getTaken(s.xLoc - 1, s.yLoc + 1)&& board.gameBoard[s.xLoc - 1][s.yLoc +1].color == color) {
						if(board.gameBoard[s.xLoc - 1][s.yLoc].color == color ||board.gameBoard[s.xLoc][s.yLoc+1].color == color) {
							return false;
							
						}
						else {
							return true;
						}
					}
					if(board.getTaken(s.xLoc + 1, s.yLoc -1)&& board.gameBoard[s.xLoc + 1][s.yLoc -1].color == color) {
						if(board.gameBoard[s.xLoc + 1][s.yLoc].color == color ||board.gameBoard[s.xLoc][s.yLoc-1].color == color) {
							return false;
							
						}
						else {
							return true;
						}
					}
					if(board.getTaken(s.xLoc + 1, s.yLoc + 1)&& board.gameBoard[s.xLoc + 1][s.yLoc +1].color == color) {
						if(board.gameBoard[s.xLoc + 1][s.yLoc].color == color ||board.gameBoard[s.xLoc][s.yLoc+1].color == color) {
							return false;
							
						}
						else {
							return true;
						}
					}
					}
					catch(Exception e) {
						
					}
					
				}
    				
    				
    			
    				if(up && !down && !left && !right) {
    					
    					try {
    					if(board.getTaken(s.xLoc - 1, s.yLoc + 1)&& board.gameBoard[s.xLoc - 1][s.yLoc +1].color == color) {
    						if(board.gameBoard[s.xLoc - 1][s.yLoc].color == color ||board.gameBoard[s.xLoc][s.yLoc+1].color == color) {
    							return false;
    							
    						}
    						else {
    							return true;
    						}
    					}
    					
    					if (board.getTaken(s.xLoc - 1, s.yLoc - 1)&& board.gameBoard[s.xLoc - 1][s.yLoc -1].color == color){
    						if(board.gameBoard[s.xLoc - 1][s.yLoc].color == color ||board.gameBoard[s.xLoc][s.yLoc-1].color == color) {
    							return false;
    							
    						}
    						else {
    							return true;
    						}
    					}
    					
    					
    					}
    					catch(Exception e) {
    						
    					}
    				}
    				
    				
    				if(down&& !left && !right && !up) {
    					
    					
    					try {
    					if(board.getTaken(s.xLoc + 1, s.yLoc +1)&& board.gameBoard[s.xLoc + 1][s.yLoc +1].color == color) {
    						if(board.gameBoard[s.xLoc + 1][s.yLoc].color == color ||board.gameBoard[s.xLoc][s.yLoc+1].color == color) {
    							return false;
    							
    						}
    						else {
    							return true;
    						}
    					}
    					
    					if(board.getTaken(s.xLoc + 1, s.yLoc - 1)&& board.gameBoard[s.xLoc + 1][s.yLoc -1].color == color) {
    						if(board.gameBoard[s.xLoc + 1][s.yLoc].color == color ||board.gameBoard[s.xLoc][s.yLoc-1].color == color) {
    							return false;
    							
    						}
    						else {
    							return true;
    						}
    					}
    					}
    					catch(Exception e) {
    						
    					}
    				}
    				
    				
    				
    				if(left && !up && !down && !right) {
    					
    					try {
    					if(board.getTaken(s.xLoc + 1, s.yLoc -1)&& board.gameBoard[s.xLoc + 1][s.yLoc -1].color == color) {
    						if(board.gameBoard[s.xLoc + 1][s.yLoc].color == color ||board.gameBoard[s.xLoc][s.yLoc-1].color == color) {
    							return false;
    							
    						}
    						else {
    							return true;
    						}
    					}
    					if(board.getTaken(s.xLoc - 1, s.yLoc - 1)&& board.gameBoard[s.xLoc - 1][s.yLoc -1].color == color) {
    						if(board.gameBoard[s.xLoc - 1][s.yLoc].color == color ||board.gameBoard[s.xLoc][s.yLoc-1].color == color) {
    							return false;
    							
    						}
    						else {
    							return true;
    						}
    					}
    					}
    					catch(Exception e) {
    						
    					}

    				}
    				
    				if(right && !up && !down && !left) {
    			
    					
    					try {
    					if(board.getTaken(s.xLoc + 1, s.yLoc +1)&& board.gameBoard[s.xLoc + 1][s.yLoc +1].color == color) {
    						if(board.gameBoard[s.xLoc + 1][s.yLoc].color == color ||board.gameBoard[s.xLoc][s.yLoc+1].color == color) {
    							return false;
    							
    						}
    						else {
    							return true;
    						}
    					}
    					if(board.getTaken(s.xLoc - 1, s.yLoc + 1)&& board.gameBoard[s.xLoc - 1][s.yLoc +1].color == color) {
    						if(board.gameBoard[s.xLoc - 1][s.yLoc].color == color ||board.gameBoard[s.xLoc][s.yLoc+1].color == color) {
    							return false;
    							
    						}
    						else {
    							return true;
    						}
    					}
    					}
    					catch(Exception e) {
    						
    					}

    				}
    				
    			
    				if(down&& !left && right && !up) {
    					
    					try {
    				
    					if(board.getTaken(s.xLoc + 1, s.yLoc +1)&& board.gameBoard[s.xLoc + 1][s.yLoc +1].color == color){
    						if(board.gameBoard[s.xLoc + 1][s.yLoc].color == color ||board.gameBoard[s.xLoc][s.yLoc+1].color == color) {
    							return false;
    							
    						}
    						else {
    							return true;
    						}
    					}
    					}
    					catch(Exception e) {
    						
    					}
    				}
    				
    				if(!down && !left && right && up) {
    					
    					try {
    				
    					if(board.getTaken(s.xLoc - 1, s.yLoc +1)&& board.gameBoard[s.xLoc - 1][s.yLoc +1].color == color){
    						
    						if(board.gameBoard[s.xLoc - 1][s.yLoc].color == color ||board.gameBoard[s.xLoc][s.yLoc+1].color == color) {
    							return false;
    							
    						}
    						else {
    							return true;
    						}
    					}
    					}
    					catch(Exception e) {
    						
    					}
    				}
    				
    				if(down && left && !right && !up) {
    					
    					try {
    				
    					if(board.getTaken(s.xLoc + 1, s.yLoc -1)&& board.gameBoard[s.xLoc + 1][s.yLoc -1].color == color){
    						
    						if(board.gameBoard[s.xLoc + 1][s.yLoc].color == color ||board.gameBoard[s.xLoc][s.yLoc-1].color == color) {
    							return false;
    							
    						}
    						else {
    							return true;
    						}
    					}
    					}
    					catch(Exception e) {
    						
    					}
    				}
    				if(!down&& left && !right && up) {
    				
    					try {
    				
    					if(board.getTaken(s.xLoc - 1, s.yLoc -1)&& board.gameBoard[s.xLoc - 1][s.yLoc -1].color == color){
    						if(board.gameBoard[s.xLoc - 1][s.yLoc].color == color ||board.gameBoard[s.xLoc][s.yLoc-1].color == color) {
    							return false;
    							
    						}
    						else {
    							return true;
    						}
    					}
    					}
    					catch(Exception e) {
    						
    					}
    				}
    			
    				up = false;
    				left = false;
    				right = false;
    				down = false;
    			
    		}
    			
    		//placement check for first turn. Must be corner
    		
    		if (firstTurnP){
    			
    			
    			System.out.print(s.xLoc);
    			System.out.println(s.yLoc);
    			if(color == Color.RED) {
    			if(s.xLoc == 19 && s.yLoc == 19) {
    				return true;
    			}
    			}
    			if(color == Color.BLUE) {
        			if(s.xLoc == 19 && s.yLoc == 0) {
        				return true;
        			}
        			}
    			if(color == Color.GREEN) {
        			if(s.xLoc == 0 && s.yLoc == 0) {
        				return true;
        			}
        			}
    			if(color == Color.YELLOW) {
        			if(s.xLoc == 0 && s.yLoc == 19) {
        				return true;
        			}
        			}
    			
    			
    		
    			
    		}
    		
    		
    	}

    	return false;
    	
    	
    }

    //also adds points
    // updates board with piece
    Board placePiece(int x, int y, Square[] piece, Color color, Board board) {
    	firstTurnP = false;
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
        return color;
    }


    void setColor(Color newColor){
        color = newColor;
    }

    //Methods that return a array of squares that work as pieces
    //Kyle Michel
   
   
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
    	tempPiece[2] = new Square(locationX+1,locationY);
    	tempPiece[3] = new Square(locationX+2,locationY);
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
    
    //Methods for rotate a mirror
    //Kyle Michel
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
		   int relativeCoordinateX = piece[i].yLoc - piece[0].yLoc;
		   relativeCoordinateX=-relativeCoordinateX;
		   piece[i].yLoc = piece[0].yLoc + relativeCoordinateX;
	   }
   	return piece;
   }
   
   //Returns piece based on computer input
   
   //Kyle Michel, Aaron Wise, Robert Duarte
   
   public Square[] getPiece(int x , int y){
		
	   	int xPick = x;
	   	int yPick = y;
	   	Random rand = new Random();
	   	
	   	 compPiecePick = rand.nextInt(20) + 1;
		
	    Square [] temp = pieceOne(xPick, yPick);
	    
	    boolean cont = true;
	    while(cont) {
	    	if(!pieceUsed[compPiecePick - 1]) {
	    		cont = false;
	    	}
	    	else {
	    		compPiecePick = rand.nextInt(20) + 1;
	    	}
	    }
		
		switch(compPiecePick) {
	 	case 1: temp = pieceOne(xPick, yPick);
	 			break;
		case 2: temp = pieceTwo(xPick, yPick);
		break;
		case 3: temp = pieceThree(xPick, yPick);
		break;
		case 4: temp = pieceFour(xPick, yPick);
		break;
		case 5: temp = pieceFive(xPick, yPick);
		break;
		case 6: temp = pieceSix(xPick, yPick);
		break;
		case 7: temp = pieceSeven(xPick, yPick);
		break;
		case 8: temp = pieceEight(xPick, yPick);
		break;
		case 9: temp = pieceNine(xPick, yPick);
		break;
		case 10: temp = pieceTen(xPick, yPick);
		break;
		case 11: temp = pieceEleven(xPick, yPick);
		break;
		case 12: temp = pieceTwelve(xPick, yPick);
		break;
		case 13: temp = pieceThirteen(xPick, yPick);
		break;
		case 14: temp = pieceFourteen(xPick, yPick);
		break;
		case 15: temp = pieceFifteen(xPick, yPick);
		break;
		case 16: temp = pieceSixteen(xPick, yPick);
		break;
		case 17: temp = pieceSeventeen(xPick, yPick);
		break;
		case 18: temp = pieceEighteen(xPick, yPick);
		break;
		case 19: temp = pieceNineteen(xPick, yPick);
		break;
		case 20: temp = pieceTwenty(xPick, yPick);
		break;
		case 21: temp = pieceTwentyOne(xPick, yPick);
		break;
			
	  	
	  }
			
			return temp;
	  }
   
}










