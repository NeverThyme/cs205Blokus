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

public class Board {
  void makeBoard(){
  //Create a gameboard, and fill it with white squares.
    Square[][] gameboard = new Square[20][20];
    Square placehold = new Square();

    for(int i = 0; i < 20 ; ++i) {
    for(int j = 0; j < 20; ++j) {
      gameboard[i][j] = placehold;
      }
    }

    int finish = 1;
    while (finish == 0) {
      player1Turn();
      comp1Turn();
      player2Turn();
      comp2Turn();
      if(endGame()) {
        finish = 1;
      }
    }
  }
}
