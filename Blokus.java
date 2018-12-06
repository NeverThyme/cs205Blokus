import java.awt.Color;
import java.awt.EventQueue;

public class Blokus {
	public static void main(String[] args) {
		
		
		// field init
		Color tempColorRed = new Color(1,0,0);
		Color tempColorBlue = new Color(0,0,1);
		Player player = new Player(tempColorRed);
		Player computer = new Player(tempColorBlue);
		Gui frame = new Gui();
		
		Board board = new Board();
		board.makeBoard();
		
		
		
	
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
			
		
			
			int x = 0;
			int y = 0;
			
			board = player.playerTurn(board , frame);
			//board = computer.computerTurn(board);
			
			updateGUI(board,frame);
			
		
			//just quickly printing the board so we can test things until i finish hooking up gui
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
		}	
	}
		
	public static void updateGUI( Board board, Gui frame ) {
			
			for(int i = 0; i < 20 ; ++i) {
			    for(int j = 0; j < 20; ++j) {
			    	frame.setColor(board.gameBoard[i][j].xLoc, board.gameBoard[i][j].yLoc, board.gameBoard[i][j].color);
			    }
			  }
			}
		
			
		
		
	}
	
	
	
