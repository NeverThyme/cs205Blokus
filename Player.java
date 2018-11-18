import java.lang.Object;
import java.util.Scanner;
import java.awt.Color;

public class Player {

    int points = 0;
    Color pieceColor;
    Square[][] pieces = new Square [1][1];

//======================================================================================================================

    public Player(Color color){
        pieceColor = color;
        
        //default single square added until all pieces are added.
        
        pieces[0][0] = new Square();
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
        //currently does nothign because we only have one piece which is a single square
        removePiece(player1Pick);
       

        System.out.println("Player 1 went.");
        return board;


    }

//======================================================================================================================

    /*
    public void player2Turn() {

        //prompt player to pick a piece
        System.out.println("Pick a piece to play.");

        //player chooes piece
        //random until GUI
        Square[] player2Pick = pieces[1];

        //player chooses location for piece
        int player2XPick = chooseXLocation();
        int player2YPick = chooseYLocation();

        //validate location
        boolean canPlacePiece = checkPlacement(player2XPick, player2YPick, player2Pick);

        if(canPlacePiece){
            placePiece(player2XPick, player2YPick, player2Pick, pieceColor);
        }else{
            System.out.println("The piece does not fit there.");
        }

        while (!canPlacePiece){
            player2XPick = chooseXLocation();
            player2YPick = chooseYLocation();

            //validate location
            canPlacePiece = checkPlacement(player2XPick, player2YPick, player2Pick);
        }

        //update pieces array
        removePiece(player2Pick);



        System.out.println("Player 2 went.");


    }
    */

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
        	
        	/* commented out until comparePieces implemented
            if(comparePieces(pieces[i], piece)){
                pieces[i] = null;
               
            }
            */
        }
            
        
    }



}
