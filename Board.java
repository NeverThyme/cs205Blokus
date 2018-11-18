import java.util.*;
import java.io.*;
import java.awt.Color;

public class Board{
  

	 static Square[][] gameBoard = new Square[20][20];


// ----------------------------Functions----------------------------------


void makeBoard(){
//Create a gameboard, and fill it with white squares.

  for(int i = 0; i < 20 ; ++i) {
    for(int j = 0; j < 20; ++j) {
      gameBoard[i][j] = new Square();
    }
  }
}

static boolean getTaken(int x, int y) {

  return gameBoard[x][y].taken;
	
  
}

void setSquare(int x, int y , Color color) {
	
	gameBoard[x][y].color = color;
	gameBoard[x][y].xLoc = x;
	gameBoard[x][y].yLoc = y;
	gameBoard[x][y].taken = true;
	 
}

Square getSquare(int x, int y) {
	
	return gameBoard[x][y];
	
	 
}


}
