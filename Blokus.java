import java.awt.Color;
import java.awt.EventQueue;

public class Blokus {
	public static void main(String[] args) {
		
		
		// field init
		Color tempColorRed = new Color(1,0,0);
		Player player = new Player(tempColorRed);
		
		Board board = new Board();
		board.makeBoard();
		
		///Runs Gui
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gui frame = new Gui();
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
			
			board = player.playerTurn(board);
			
		
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
		
		
		
		
		
		
		
		
		
		
		
		 // int turnCounter = 1;
		  //int finish = 0;

		  

		  /* honestly dont know what up wiht this or hgow to fix it
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
		      if(endGame()) {
		        finish = 0;
		      } 
		    }
		  }
		
		*/
		
		
	}
	
	
	
	
}
