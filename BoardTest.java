import static org.junit.jupiter.api.Assertions.*;
import java.awt.Color;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class BoardTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		
	}

	@Test
	void testMakeBoard() {
		Board testBoard = new Board();
		testBoard.makeBoard();
		
		for(int i = 0; i < 20 ; ++i) {
		    for(int j = 0; j < 20; ++j) {
		    	
		    	Square test = new Square();
		      assertNotNull(testBoard.gameBoard[i][j]);
		    }
		  }
		
		
		assertEquals(testBoard.gameBoard.length , 20 );
		for(int i = 0; i < 20 ; ++i) {
			assertEquals(testBoard.gameBoard[i].length , 20 );
		}
		
		
	}

	@Test
	void testGetTaken() {
		Board testBoard = new Board();
		testBoard.makeBoard();
		
		
		for(int i = 0; i < 20 ; ++i) {
		    for(int j = 0; j < 20; ++j) {
	
		
		assertEquals(testBoard.getTaken(i, j), testBoard.gameBoard[i][j].taken);
		
		    }
		}
		
		
	}

	@Test
	void testSetSquare() {
		Board testBoard = new Board();
		testBoard.makeBoard();
		
		Color testColor = new Color(1,1,1);
		int testX = 1;
		int testY = 1;
		
		testBoard.setSquare(testX, testY, testColor);
		
		assertEquals(testBoard.gameBoard[testX][testY].color , testColor);
		assertEquals(testBoard.gameBoard[testX][testY].xLoc , testX);
		assertEquals(testBoard.gameBoard[testX][testY].yLoc , testY);
		assertEquals(testBoard.gameBoard[testX][testY].taken , true);
		
	}
	
	@Test
	void testGetSquare() {
		Board testBoard = new Board();
		testBoard.makeBoard();
		
		Color testColor = new Color(1,1,1);
		int testX = 1;
		int testY = 1;
	
		testBoard.setSquare(testX, testY, testColor);
		
		assertEquals(testBoard.getSquare(testX, testY), testBoard.gameBoard[testX][testY]);


	}

}
