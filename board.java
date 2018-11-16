import java.util.*;
import java.io.*;
import java.awt.Color;

class Square {
  boolean taken = false;
  int value = 0;
  int xLoc;
  int yLoc;
  Color color;
}

public class Board extends Test{
  //Turn counter: 1 = player, 2 = comp1, 3 = player, 4 = comp2
  int turnCounter = 1;
  int finish = 0;

  Board game = makeBoard();

  while (finish == 0) {
    switch turnCounter {
      case 1 {
        player1Turn();
        ++turnCounter;
      }
      case 2 {
        player2Turn();
        turnCounter = 1;
      }
      /* if(endGame()) {
        finish = 0;
      } */
    }
  }
}


// ----------------------------Functions----------------------------------


void makeBoard(){
//Create a gameboard, and fill it with white squares.
  Square[][] gameboard = new Square[20][20];
  Square placehold = new Square();

  for(int i = 0; i < 20 ; ++i) {
    for(int j = 0; j < 20; ++j) {
      gameboard[i][j] = placehold;
    }
  }
}

boolean getTaken(int x, int y) {
  return square[x][y].taken;
}
