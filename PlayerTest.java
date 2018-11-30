import static org.junit.jupiter.api.Assertions.*;
import java.awt.Color;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class PlayerTest {

	@Test
	void testPlayerColor() {
		Color testColor = new Color(1,1,1);
		Player testPlayer = new Player(testColor);
		 
		assertNotNull(testPlayer.pieces);
		assertEquals(testPlayer.pieceColor, testColor);
		
	}

	/*
	@Test
	void testPlayer() {
		fail("Not yet implemented");
	}
	*/

	@Test
	void testPlayerTurn() {
		Color testColor = new Color(1,1,1);
		Player testPlayer = new Player(testColor);
		int testX = 1;
		int testY = 1;
		Board testBoard = new Board();
		Board newBoard = new Board();
		testBoard.makeBoard();
		
		newBoard = testPlayer.playerTurn(testBoard);
		
		testBoard = testPlayer.placePiece(testX, testY, testPlayer.pieces[0], testPlayer.pieceColor, testBoard);
		
		
		assertEquals(testBoard, newBoard);
	}

	@Test
	void testChooseXLocation() {
		
		Player testPlayer = new Player();
		assertNotNull(testPlayer.chooseXLocation());
		
	}

	@Test
	void testChooseYLocation() {
		
		Player testPlayer = new Player();
		assertNotNull(testPlayer.chooseYLocation());
		
	}

	@Test
	void testCheckPlacement() {
		Color testColor = new Color(1,1,1);
		Player testPlayer = new Player(testColor);
		int testX = 1;
		int testY = 1;
		Board testBoard = new Board();
		testBoard.makeBoard();
		
		//testBoard.setSquare(testX, testY, testColor);
		testPlayer.placePiece(testX, testY, testPlayer.pieces[0], testPlayer.pieceColor, testBoard);
		
		
		assertTrue(testPlayer.checkPlacement(testX, testY, testPlayer.pieces[0], testBoard));
		
	}

	@Test
	void testPlacePiece() {
		Color testColor = new Color(1,1,1);
		Player testPlayer = new Player(testColor);
		int testX = 1;
		int testY = 1;
		Board testBoard = new Board();
		testBoard.makeBoard();
		
		testPlayer.placePiece(testX, testY, testPlayer.pieces[0], testPlayer.pieceColor, testBoard);
		
		for (Square s : testPlayer.pieces[0]){
       	 
        	
        	assertEquals(testBoard.getSquare(s.xLoc, s.yLoc).color , testColor);
    		assertEquals(testBoard.getSquare(s.xLoc, s.yLoc).xLoc , testX);
    		assertEquals(testBoard.getSquare(s.xLoc, s.yLoc).yLoc , testY);
    		assertEquals(testBoard.getSquare(s.xLoc, s.yLoc).taken , true);
                      
        }
		
	}

	@Test
	void testGetPoints() {
		Color testColor = new Color(1,1,1);
		Player testPlayer = new Player(testColor);
		
		assertNotNull(testPlayer.getPoints());
		
	}

	@Test
	void testGetColor() {
		Color testColor = new Color(1,1,1);
		Player testPlayer = new Player(testColor);
		 
		assertEquals(testPlayer.pieceColor, testColor);
	}

	@Test
	void testSetColor() {
		Color testColor = new Color(1,1,1);
		Color setColor = new Color(1,0,0);
		Player testPlayer = new Player(testColor);
		
		testPlayer.setColor(setColor);
		 
		assertEquals(testPlayer.pieceColor, setColor);
	}
	/*
	@Test
	void testRemovePiece() {
		fail("Not yet implemented");
	}
	*/

}
