import java.util.*;
import java.io.*;
import java.lang.Object;

class Square {
  int taken;
  int value;
  int xLoc;
  int yLoc;
  Color(white);
}

class Board {

}

public class Board {
  //Create a gameboard, and fill it with white squares.
  Square gameboard[][] = new Square;
  Square token = new Square;
  for(int i = 0; i < 20; ++i) {
    for(int j = 0; j < 20; ++j) {
      gameboard[i][j] = token;
    }
  }

  int finish = 0;
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
