import java.awt.Color;

public class Square {

  
  boolean taken = false;
  int value = 0;
  int xLoc;
  int yLoc;
  Color color = new Color(0,0,0);
  
  public Square() {
	  
  }
  public Square(Color c) {
	  
  color = c;
  }
	  
  public Square(int x, int y) {
	  xLoc = x;
	  yLoc = y;
}
public void setXY(int player1xPick, int player1yPick) {
	xLoc = player1xPick;
	yLoc = player1yPick;
}

}
