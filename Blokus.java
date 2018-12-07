import java.awt.Color;
import java.awt.EventQueue;

public class Blokus {
	static int xPick = -1;
	static int yPick = -1;
	static int pieceNum =-1;

	public static void main(String[] args) {
		
		
		// field init
		
		Player player = new Player(Color.RED);
		Player computer = new Player(Color.BLUE);
		Player player2 = new Player(Color.GREEN);
		Player computer2 = new Player(Color.YELLOW);
		Gui frame = new Gui();
		
		

		
		Board board = new Board();
		board.makeBoard();
		board.getTaken(0, 0);
		
	
		///Runs Gui
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//Gui frame = new Gui();
					frame.setVisible(true);
					
			
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		///End run Gui
		
		
		//Loop for running turns in game need to be linked with gui pages
		while(true) {
			
			
			while(!player.placed) {
				
			
			board = player.playerTurn(board, frame, frame.getPiece(), frame.piecePick);
		
			}
			player.placed = false;
			updateGUI(board,frame);

			
			board = computer.computerTurn(board, computer.getColor());

			updateGUI(board,frame);
			
			
			while(!player2.placed) {
				
			board = player2.playerTurn(board, frame, frame.getPiece(),frame.piecePick);
			
			}
			updateGUI(board,frame);
			board = computer2.computerTurn(board, computer2.getColor());

			player2.placed = false;
			
			
			updateGUI(board,frame);
			
		
			//just quickly printing the board so we can test things until i finish hooking up gui
			/*
			for(int i = 0; i < 20; i++)
			   {
			      for(int j = 0; j < 20; j++)
			      {
			    	  
			    	 
			         if (board.gameBoard[i][j].taken == true) {
			        
			            
			         System.out.printf("x");
			         }
			         else {
			        	 System.out.printf("o");
			         }
			      }
			      System.out.println();
			   }
			   */
		
			
	}}
		
	public static void updateGUI( Board board, Gui frame ) {
			
			for(int i = 0; i < 20 ; ++i) {
			    for(int j = 0; j < 20; ++j) {
			    	
			    	frame.setColor(board.gameBoard[i][j].xLoc, board.gameBoard[i][j].yLoc, board.gameBoard[i][j].color);
			    	
			    	
			    }
			  }
			}
		
			
		
		
	


}
	
	
