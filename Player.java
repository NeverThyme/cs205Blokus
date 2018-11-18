import java.lang.Object;
import java.util.Scanner;

public class Player extends Board {

    int points;
    Color pieceColor;
    Square[][] pieces;

//======================================================================================================================

    public Player(Color color){
        pieceColor = color;
    }

//======================================================================================================================

    public void player1Turn() {

        //prompt player to pick a piece
        System.out.println("Pick a piece to play.");

        //player chooses piece
        //until GUI this will be a random piece
        Square[] player1Pick = pieces[1];

        //player chooses location for piece
        int player1XPick = chooseXLocation();
        int player1YPick = chooseYLocation();


        //validate location
        boolean canPlacePiece = checkPlacement(player1XPick, player1YPick, player1Pick);


        if (canPlacePiece){
            placePiece(player1XPick, player1YPick, player1Pick, pieceColor);
        }else{
            System.out.println("The piece does not fit there.")
        }


        while (!canPlacePiece){

            player1XPick = chooseXLocation();
            player1YPick = chooseYLocation();

            //validate location
            canPlacePiece = checkPlacement(player1XPick, player1YPick, player1Pick);
        }
        //update pieces array
        removePiece(player1Pick);


        System.out.println("Player 1 went.");


    }

//======================================================================================================================

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

//======================================================================================================================

    //                          FUNCTIONS

//======================================================================================================================






    int chooseXLocation(){
        System.out.println("Pick a column for your piece (1-20): ");
        Scanner input = new Scanner(System.in);
        int player1XPick = input.nextInt();
        while (player1XPick < 1 || player1XPick > 20){
            System.out.println("Please enter a value 1-20: ");
            player1XPick = input.nextInt();
        }
        return player1XPick;
    }


    int chooseYLocation(){
        System.out.println("Pick a row for your piece (1-20): ");
        Scanner input = new Scanner(System.in);
        int player1YPick = input.nextInt();
        while (player1YPick < 1 || player1YPick > 20){
            System.out.println("Please enter a value 1-20: ");
            player1YPick = input.nextInt();
        }
        return player1YPick;
    }

    boolean checkPlacement(int x, int y, Square[] piece){
        if (!Board.getTaken(x, y)){
            for (int i = 0; i < piece.length; i++) {
                if(!Board.getTaken(x + piece[i].getX, y + piece[i].getY)){

                }else{
                    return false;
                }
            }
        }else{
            return false;
        }
        return true;
    }

    //also adds points
    void placePiece(int x, int y, Square[] piece, Color color) {
        for (Square s : piece){
            board[s[i.getX]][s[i].getY].capture;
            board[s[i.getX]][s[i].getY].setColor(color);
            points += 1;

        }
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
            if(comparePieces(pieces[i], piece)){
                pieces[i] = null;
            }
        }
    }




}
