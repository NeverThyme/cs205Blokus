// Gui class made by Aaron Wise Robert Duarte Kyle Michel


import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.awt.CardLayout;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


import java.awt.Color;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Gui extends JFrame {

	//Component field variables
	private JPanel contentPane;
	private JButton btnInstructions;
	private JButton btnMainMenu;
	private JLabel lblInsertInstructionsFor;
	private JPanel MainMenu;
	private JPanel Instructions;
	private JPanel Board;
	private JButton btnPlay;
	private JButton btnExit;
	private final JPanel GameBoard = new JPanel(new BorderLayout(3, 3));
	private static JButton[][] blockusBoardSquares = new JButton[20][20];
    private JPanel blockusBoard;
    private JButton btnMainMenu2;
	private JButton[] selectionButtons = new JButton[21];
	private JPanel PiecePanel;
	private JPanel SelectionPanel;
	private JButton btnMirror;
	private JButton btnRotate;
	
	
	//field variables to be used with the methods
	int rotates = 0;
	int mirrors = 0;
    static int xPick = -1;
    static int yPick = -1;
    boolean pickReady = false;
    static int piecePick = 1;
    
    Square[] hover;
    
    int[] xy = new int[2];
    
    private static boolean pTransfer = true;
    private static boolean xTransfer = true;
    private static boolean yTransfer = true;
    
   
   
	//Constructor calls the initialization methods
    //Aaron Wise
	public Gui() {
		
		
		componentInit();
		eventInit();
		
		
	}
	
	//Initializes the Event Listeners which get input from the user
	//Aaron Wise
	private void eventInit() {
		////////Main Menu events//////////
		btnInstructions.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MainMenu.setVisible(false);
				Instructions.setVisible(true);
				
			}
		});
		
		btnPlay.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MainMenu.setVisible(false);
				Board.setVisible(true);
				
			}
		});
		
		
		
		btnExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		
		////////Instructions Events/////////
		btnMainMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MainMenu.setVisible(true);
				Instructions.setVisible(false);
			}
		});
		
		///////////Baord events////////////
		btnMainMenu2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Board.setVisible(false);
				MainMenu.setVisible(true);

			}
		});
		
		//Board event
		 for (int ii = 0; ii < 20; ii++) {
	            for (int jj = 0; jj <20; jj++) {
	            	int j = ii;
	            	int i = jj;
	                 blockusBoardSquares[ii][jj].addMouseListener(new MouseAdapter() {
	         			@Override
	        			public void mouseClicked(MouseEvent arg0) {
	        				
	         				xy[0] = i;
	         				xy[1] = j;
	         				
	         				sendXY(xy);
	         				pickReady = true;
	         				

	        			}
	        		});
	            }
	        }
		
		 	//picking piece event
	      for (int i = 0; i <20; i++) {
          	
          	int pick = i;
               selectionButtons[i].addMouseListener(new MouseAdapter() {
       			@Override
      			public void mouseClicked(MouseEvent arg0) {
       				
       				//piecePick = pick + 1;
       				sendPick(pick + 1);

      			}
      		});
          }
	      
	      //Events that create the hover effect
	      for (int ii = 0; ii < 20; ii++) {
	            for (int jj = 0; jj <20; jj++) {
	            	int i = ii;
	            	int j = jj;
	            	
	                 blockusBoardSquares[ii][jj].addMouseListener(new java.awt.event.MouseAdapter() {
	    	    private Color temp = blockusBoardSquares[i][j].getBackground();
				public void mouseEntered(java.awt.event.MouseEvent evt) {
					
					
						hover = getHover();
						for(int i = 0; i < rotates;i++) {
							hover = rotateRight(hover);
						}
						for(int i = 0; i < mirrors;i++) {
							hover = mirrorPiece(hover);
						}
						
						
						for(Square s : hover) {
							try {
								if (blockusBoardSquares[i+s.yLoc][j+s.xLoc].getBackground() == Color.white) {
							blockusBoardSquares[i+s.yLoc][j+s.xLoc].setBackground(Color.pink);
								}
							}
							catch(Exception e){
								
							
							
						}
	    	    	
					}
	    	    }
				// Event that removes hover effect
	    	    public void mouseExited(java.awt.event.MouseEvent evt) {
	    	    	
	    	    		hover = getHover();
	    	    		for(int i = 0; i < rotates;i++) {
							hover = rotateRight(hover);
						}
						for(int i = 0; i < mirrors;i++) {
							hover = mirrorPiece(hover);
						}
						
						for(Square s : hover) {
							try {
					if (blockusBoardSquares[i+s.yLoc][j+s.xLoc].getBackground() == Color.pink) {
	    	    		blockusBoardSquares[i+s.yLoc][j+s.xLoc].setBackground(temp);
					}
							}
							catch(Exception e){
								
							}
							
						
	    	    	}
	    	    }
	    	});
		
	            }
	            
	            
	      }
	     
	      //event for rotate and mirror methods
	      btnRotate.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					
					rotates ++;
				}
			});
	      
	      btnMirror.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					mirrors ++;
				}
			});
	   
	    
	      
	      
	}
	
	
	//Method that initializes the components in the Gui
	//Aaron Wise
	private void componentInit() {
		
		
		// TODO Auto-generated method stub
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 300, 1350, 900);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		MainMenu = new JPanel();
		contentPane.add(MainMenu, "name_3011542245689215");
		
		JLabel lblBlockus = new JLabel("Blockus");
		
		btnInstructions = new JButton("Instructions");
		btnPlay = new JButton("Play");
		btnExit = new JButton("Exit");
		
		GroupLayout gl_MainMenu = new GroupLayout(MainMenu);
		gl_MainMenu.setHorizontalGroup(
			gl_MainMenu.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_MainMenu.createSequentialGroup()
					.addGap(167)
					.addGroup(gl_MainMenu.createParallelGroup(Alignment.LEADING)
						.addComponent(btnExit, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 563, Short.MAX_VALUE)
						.addComponent(lblBlockus, GroupLayout.DEFAULT_SIZE, 563, Short.MAX_VALUE)
						.addComponent(btnPlay, GroupLayout.DEFAULT_SIZE, 563, Short.MAX_VALUE)
						.addComponent(btnInstructions, GroupLayout.DEFAULT_SIZE, 563, Short.MAX_VALUE))
					.addGap(584))
		);
		gl_MainMenu.setVerticalGroup(
			gl_MainMenu.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_MainMenu.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblBlockus, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE)
					.addGap(49)
					.addComponent(btnPlay, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnInstructions, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
					.addGap(130)
					.addComponent(btnExit, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(113, Short.MAX_VALUE))
		);
		MainMenu.setLayout(gl_MainMenu);
		
		Board = new JPanel();
		
		contentPane.add(Board, "name_3012008420420962");
		
		
		//JPanel GameBoard = new JPanel();
		//GameBoard.setBackground(Color.GRAY);
		
		
		GameBoard.setBorder(new EmptyBorder(10, 10, 10, 10));
		JPanel blockusBoard = new JPanel(new GridLayout(0, 20));
        blockusBoard.setBorder(new LineBorder(Color.BLACK));
        GameBoard.add(blockusBoard);

        // create the board squares
        Insets buttonMargin = new Insets(0,0,0,0);
        for (int ii = 0; ii < blockusBoardSquares.length; ii++) {
            for (int jj = 0; jj < blockusBoardSquares[ii].length; jj++) {
                JButton b = new JButton();
                b.setMargin(buttonMargin);
               
                
                ImageIcon icon = new ImageIcon(
                        new BufferedImage(24, 24, BufferedImage.TYPE_INT_ARGB));
                b.setIcon(icon);
                b.setBackground(Color.WHITE);
               
                blockusBoardSquares[jj][ii] = b;
            }
        }

       //add square to the board
        for (int ii = 0; ii < 20; ii++) {
            for (int jj = 0; jj <20; jj++) {
                 blockusBoard.add(blockusBoardSquares[jj][ii]);
            }
        }
        
        
        
		
		btnMainMenu2 = new JButton("MainMenu");
	
		// start of piece selection buttons
		PiecePanel = new JPanel();
		try {
		BufferedImage myPicture = ImageIO.read(new File("Screen Shot 2018-11-14 at 1.33.19 PM.png"));
		JLabel picLabel = new JLabel(new ImageIcon(myPicture));
		PiecePanel.add(picLabel);
		}
		catch(IOException ex){
			
		}
		
		SelectionPanel = new JPanel(new GridLayout(0, 11));;
		
		//Creation of selection buttons
		 for (int i = 0; i < selectionButtons.length; i++) {
             JButton b = new JButton();
             b.setMargin(buttonMargin);
             String num = Integer.toString(i+1);
             b.setText(num);
             b.setBackground(Color.WHITE);
             selectionButtons[i] = b;
         }
		
		 //adding buttons to Panel
		 for (int i = 0; i <20; i++) {
             SelectionPanel.add(selectionButtons[i]);
        }
		
		 //Start of adding mirror and rotate buttons
		btnRotate = new JButton("Rotate");
		
		
		btnMirror = new JButton("Mirror");
		
		
		//adding buttons to the game pane
		GroupLayout gl_Board = new GroupLayout(Board);
		gl_Board.setHorizontalGroup(
			gl_Board.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_Board.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_Board.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_Board.createSequentialGroup()
							.addComponent(btnMirror)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnRotate)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnMainMenu2))
						.addGroup(gl_Board.createSequentialGroup()
							.addComponent(GameBoard, GroupLayout.PREFERRED_SIZE, 600, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_Board.createParallelGroup(Alignment.LEADING)
								.addComponent(SelectionPanel, GroupLayout.DEFAULT_SIZE, 660, Short.MAX_VALUE)
								.addComponent(PiecePanel, GroupLayout.DEFAULT_SIZE, 660, Short.MAX_VALUE))))
					.addContainerGap())
		);
		gl_Board.setVerticalGroup(
			gl_Board.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_Board.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_Board.createParallelGroup(Alignment.LEADING)
						.addComponent(GameBoard, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(PiecePanel, GroupLayout.PREFERRED_SIZE, 397, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(SelectionPanel, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 203, Short.MAX_VALUE)
					.addGroup(gl_Board.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnMainMenu2)
						.addComponent(btnRotate)
						.addComponent(btnMirror))
					.addContainerGap())
		);
		
		
		
		

		GroupLayout gl_GameBoard = new GroupLayout(GameBoard);
		gl_GameBoard.setHorizontalGroup(
			gl_GameBoard.createParallelGroup(Alignment.LEADING)
				.addGap(0, 415, Short.MAX_VALUE)
		);
		gl_GameBoard.setVerticalGroup(
			gl_GameBoard.createParallelGroup(Alignment.LEADING)
				.addGap(0, 414, Short.MAX_VALUE)
		);
		//GameBoard.setLayout(gl_GameBoard);
		Board.setLayout(gl_Board);
		
		
		//Start of the instructions pane
		Instructions = new JPanel();
		contentPane.add(Instructions, "name_3011557504655764");
		
		btnMainMenu = new JButton("Main Menu");
		
		
		lblInsertInstructionsFor = new JLabel("HOW TO PLAY\\r\\n\" + \r\n" + 
				"				\"1. Each player chooses a color and places that set of 21 pieces in front of his/her side of the\\r\\n\" + \r\n" + 
				"				\"board. The order of play is as follows: blue, yellow, red, and then green.\\r\\n\" + \r\n" + 
				"				\"2. The first player (blue) places any of his/her pieces in a corner square. Play proceeds\\r\\n\" + \r\n" + 
				"				\"clockwise around the board (yellow, red, and green), each player putting their first piece down\\r\\n\" + \r\n" + 
				"				\"in one of the corner squares.\\r\\n\" + \r\n" + 
				"				\"3. Play continues as each player lays down one piece during a turn.\\r\\n\" + \r\n" + 
				"				\"- Each new piece must touch at least one other piece of the same color, but only at the\\r\\n\" + \r\n" + 
				"				\"corners.\\r\\n\" + \r\n" + 
				"				\"- No flat edges of same color pieces can touch.\\r\\n\" + \r\n" + 
				"				\"- There are no restrictions on how pieces of different colors can touch one another.\\r\\n\" + \r\n" + 
				"				\"4. Whenever a player is unable to place one of his/her remaining pieces on the board, that\\r\\n\" + \r\n" + 
				"				\"player must pass his/her turn.\\r\\n\" + \r\n" + 
				"				\"End of Game\\r\\n\" + \r\n" + 
				"				\"The game ends when all players are blocked from laying down any more of their pieces. This\\r\\n\" + \r\n" + 
				"				\"also includes any players who may have placed all of their pieces on the board. Scores are\\r\\n\" + \r\n" + 
				"				\"tallied, and the player with the highest score is the winner.");
		GroupLayout gl_Instructions = new GroupLayout(Instructions);
		gl_Instructions.setHorizontalGroup(
			gl_Instructions.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_Instructions.createSequentialGroup()
					.addContainerGap(1158, Short.MAX_VALUE)
					.addComponent(btnMainMenu)
					.addContainerGap())
				.addGroup(gl_Instructions.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblInsertInstructionsFor)
					.addContainerGap(921, Short.MAX_VALUE))
		);
		gl_Instructions.setVerticalGroup(
			gl_Instructions.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_Instructions.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblInsertInstructionsFor)
					.addPreferredGap(ComponentPlacement.RELATED, 716, Short.MAX_VALUE)
					.addComponent(btnMainMenu)
					.addContainerGap())
		);
		Instructions.setLayout(gl_Instructions);
		contentPane.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{MainMenu}));
		
		
		
	}
	//Method that sets color of the gui game board
	//Aaron Wise
	
	public void setColor(int i , int j , Color color) {
		
		blockusBoardSquares[j][i].setBackground(color);
		
	}
	//Method that return the piece Num the user picked
	//Aron Wise
	public static int getPiecePick() {
		
		int temp = piecePick;
		piecePick = -1;
		return temp;
	}
	//Method that returns color of a specific square
	//Aaron Wise
public static Color getColor(int x,int y) {
		
		
		return blockusBoardSquares[x][y].getBackground();
	}
	
	
	public static int getXPick() {
		int temp = xPick;
		xPick = -1;
		return temp;
	}
	
	public static int getYPick() {
		int temp = yPick;
		yPick = -1;
		return temp;
	}
	
//Method that gets user input from gui and return the chosen piece for use in turn method
	//Aaron Wise Robert Duarte Kyle Michel
	
	public Square[] getPiece(){
		
		int piecePick = -1;
		System.out.println("Pick what piece to play");
		//input from user
		piecePick = receivePick();
		
		
	    
	    System.out.println("Pick where to place piece");
	 
	   int xPick = -1;
	   int yPick = -1;
	   
	   // input from user
	   int[] tempCoord = new int[2];
	   tempCoord = receiveXY();
	   
	   xPick = tempCoord[0];
	   yPick = tempCoord[1];
	    
	   
		
	    Square [] temp = pieceOne(xPick, yPick);
	    
	    //Switch that calls the correct method to build the desired piece
		
		switch(piecePick) {
	 	case 1: temp = pieceOne(xPick, yPick);
	 			break;
		case 2: temp = pieceTwo(xPick, yPick);
		break;
		case 3: temp = pieceThree(xPick, yPick);
		break;
		case 4: temp = pieceFour(xPick, yPick);
		break;
		case 5: temp = pieceFive(xPick, yPick);
		break;
		case 6: temp = pieceSix(xPick, yPick);
		break;
		case 7: temp = pieceSeven(xPick, yPick);
		break;
		case 8: temp = pieceEight(xPick, yPick);
		break;
		case 9: temp = pieceNine(xPick, yPick);
		break;
		case 10: temp = pieceTen(xPick, yPick);
		break;
		case 11: temp = pieceEleven(xPick, yPick);
		break;
		case 12: temp = pieceTwelve(xPick, yPick);
		break;
		case 13: temp = pieceThirteen(xPick, yPick);
		break;
		case 14: temp = pieceFourteen(xPick, yPick);
		break;
		case 15: temp = pieceFifteen(xPick, yPick);
		break;
		case 16: temp = pieceSixteen(xPick, yPick);
		break;
		case 17: temp = pieceSeventeen(xPick, yPick);
		break;
		case 18: temp = pieceEighteen(xPick, yPick);
		break;
		case 19: temp = pieceNineteen(xPick, yPick);
		break;
		case 20: temp = pieceTwenty(xPick, yPick);
		break;
		case 21: temp = pieceTwentyOne(xPick, yPick);
		break;
			
	  	
	  }
			
			xPick = -1;
			yPick = -1;
			piecePick = -1;
			
			//rotates and mirrors piece as appropriate
			for(int i = 0; i < rotates;i++) {
				temp = rotateRight(temp);
			}
			for(int i = 0; i < mirrors;i++) {
				temp = mirrorPiece(temp);
			}
			
			rotates = 0;
			mirrors = 0;
			return temp;
			
	  }
	
	//These methods return an array of squares to be used as a piece in the turn method
	//Kyle Michel

	public static Square[] pieceOne (int locationX,int locationY) {
		Square[] tempPiece = new Square[1];
		tempPiece[0] = new Square(locationX,locationY);
		return tempPiece;

	}

	public static Square[] pieceTwo (int locationX,int locationY) {
		Square[] tempPiece = new Square[2];
		tempPiece[0] = new Square(locationX,locationY);
		tempPiece[1] = new Square(locationX+1,locationY);
		return tempPiece;
	}

	public static Square[] pieceThree (int locationX,int locationY) {
		 
		Square[] tempPiece = new Square[3];
		tempPiece[0] = new Square(locationX,locationY);
		tempPiece[1] = new Square(locationX+1,locationY);
		tempPiece[2] = new Square(locationX+1,locationY+1);
		return tempPiece;
		
		
	}

	public static Square[] pieceFour (int locationX,int locationY) {
		Square[] tempPiece = new Square[3];
		tempPiece[0] = new Square(locationX,locationY);
		tempPiece[1] = new Square(locationX+1,locationY);
		tempPiece[2] = new Square(locationX-1,locationY);
		return tempPiece;
	}

	public static Square[] pieceFive (int locationX,int locationY) {
		Square[] tempPiece = new Square[4];
		tempPiece[0] = new Square(locationX,locationY);
		tempPiece[1] = new Square(locationX+1,locationY);
		tempPiece[2] = new Square(locationX+1,locationY+1);
		tempPiece[3] = new Square(locationX,locationY+1);
		return tempPiece;
	}

	public static Square[] pieceSix (int locationX,int locationY) {
		Square[] tempPiece = new Square[4];
		tempPiece[0] = new Square(locationX,locationY);
		tempPiece[1] = new Square(locationX+1,locationY+1);
		tempPiece[2] = new Square(locationX-1,locationY+1);
		tempPiece[3] = new Square(locationX,locationY+1);
		return tempPiece;
	}

	public static Square[] pieceSeven (int locationX,int locationY) {
		Square[] tempPiece = new Square[4];
		tempPiece[0] = new Square(locationX,locationY);
		tempPiece[1] = new Square(locationX-1,locationY);
		tempPiece[2] = new Square(locationX+1,locationY+1);
		tempPiece[3] = new Square(locationX+2,locationY+1);
		return tempPiece;
	}

	public static Square[] pieceEight (int locationX,int locationY) {
		Square[] tempPiece = new Square[4];
		tempPiece[0] = new Square(locationX,locationY);
		tempPiece[1] = new Square(locationX-1,locationY);
		tempPiece[2] = new Square(locationX+1,locationY);
		tempPiece[3] = new Square(locationX+1,locationY-1);
		return tempPiece;
		
	}

	public static Square[] pieceNine (int locationX,int locationY) {
		Square[] tempPiece = new Square[4];
		tempPiece[0] = new Square(locationX,locationY);
		tempPiece[1] = new Square(locationX-1,locationY);
		tempPiece[2] = new Square(locationX,locationY-1);
		tempPiece[3] = new Square(locationX+1,locationY-1);
		return tempPiece;
		
	}

	public static Square[] pieceTen (int locationX,int locationY) {
		Square[] tempPiece = new Square[5];
		tempPiece[0] = new Square(locationX,locationY);
		tempPiece[1] = new Square(locationX-1,locationY);
		tempPiece[2] = new Square(locationX-1,locationY-1);
		tempPiece[3] = new Square(locationX+1,locationY);
		tempPiece[4] = new Square(locationX+2,locationY);
		return tempPiece;
		
	}

	public static Square[] pieceEleven (int locationX,int locationY) {
		Square[] tempPiece = new Square[5];
		tempPiece[0] = new Square(locationX,locationY);
		tempPiece[1] = new Square(locationX,locationY-1);
		tempPiece[2] = new Square(locationX,locationY-2);
		tempPiece[3] = new Square(locationX-1,locationY);
		tempPiece[4] = new Square(locationX+1,locationY);
		return tempPiece;
	}

	public static Square[] pieceTwelve (int locationX,int locationY) {
		Square[] tempPiece = new Square[5];
		tempPiece[0] = new Square(locationX,locationY);
		tempPiece[1] = new Square(locationX,locationY-1);
		tempPiece[2] = new Square(locationX,locationY-2);
		tempPiece[3] = new Square(locationX+1,locationY);
		tempPiece[4] = new Square(locationX+2,locationY);
		return tempPiece;
		
	}

	public static Square[] pieceThirteen (int locationX,int locationY) {
		Square[] tempPiece = new Square[5];
		tempPiece[0] = new Square(locationX,locationY);
		tempPiece[1] = new Square(locationX-1,locationY);
		tempPiece[2] = new Square(locationX,locationY-1);
		tempPiece[3] = new Square(locationX+1,locationY-1);
		tempPiece[4] = new Square(locationX+2,locationY-1);
		return tempPiece;
		
	}

	public static Square[] pieceFourteen (int locationX,int locationY) {
		Square[] tempPiece = new Square[5];
		tempPiece[0] = new Square(locationX,locationY);
		tempPiece[1] = new Square(locationX-1,locationY);
		tempPiece[2] = new Square(locationX+1,locationY);
		tempPiece[3] = new Square(locationX-1,locationY+1);
		tempPiece[4] = new Square(locationX+1,locationY-1);
		return tempPiece;
		
	}

	public static Square[] pieceFifteen (int locationX,int locationY) {
		Square[] tempPiece = new Square[5];
		tempPiece[0] = new Square(locationX,locationY);
		tempPiece[1] = new Square(locationX,locationY-1);
		tempPiece[2] = new Square(locationX,locationY-2);
		tempPiece[3] = new Square(locationX,locationY+1);
		tempPiece[4] = new Square(locationX,locationY+2);
		return tempPiece;
		
	}

	public static Square[] pieceSixteen (int locationX,int locationY) {
		Square[] tempPiece = new Square[5];
		tempPiece[0] = new Square(locationX,locationY);
		tempPiece[1] = new Square(locationX,locationY-1);
		tempPiece[2] = new Square(locationX,locationY+1);
		tempPiece[3] = new Square(locationX+1,locationY);
		tempPiece[4] = new Square(locationX+1,locationY+1);
		return tempPiece;
		
	}

	public static Square[] pieceSeventeen (int locationX,int locationY) {
		Square[] tempPiece = new Square[5];
		tempPiece[0] = new Square(locationX,locationY);
		tempPiece[1] = new Square(locationX-1,locationY);
		tempPiece[2] = new Square(locationX-1,locationY+1);
		tempPiece[3] = new Square(locationX,locationY-1);
		tempPiece[4] = new Square(locationX+1,locationY-1);
		return tempPiece;
		
	}

	public static Square[] pieceEighteen (int locationX,int locationY) {
		Square[] tempPiece = new Square[5];
		tempPiece[0] = new Square(locationX,locationY);
		tempPiece[1] = new Square(locationX,locationY-1);
		tempPiece[2] = new Square(locationX+1,locationY-1);
		tempPiece[3] = new Square(locationX,locationY+1);
		tempPiece[4] = new Square(locationX+1,locationY+1);
		return tempPiece;
		
	}

	public static Square[] pieceNineteen (int locationX,int locationY) {
		Square[] tempPiece = new Square[5];
		tempPiece[0] = new Square(locationX,locationY);
		tempPiece[1] = new Square(locationX-1,locationY);
		tempPiece[2] = new Square(locationX,locationY+1);
		tempPiece[3] = new Square(locationX,locationY-1);
		tempPiece[4] = new Square(locationX+1,locationY-1);
		return tempPiece;
		
	}

	public static Square[] pieceTwenty (int locationX,int locationY) {
		Square[] tempPiece = new Square[5];
		tempPiece[0] = new Square(locationX,locationY);
		tempPiece[1] = new Square(locationX-1,locationY);
		tempPiece[2] = new Square(locationX+1,locationY);
		tempPiece[3] = new Square(locationX,locationY-1);
		tempPiece[4] = new Square(locationX,locationY+1);
		return tempPiece;
		
	}

	public static Square[] pieceTwentyOne (int locationX,int locationY) {
		Square[] tempPiece = new Square[5];
		tempPiece[0] = new Square(locationX,locationY);
		tempPiece[1] = new Square(locationX,locationY-1);
		tempPiece[2] = new Square(locationX-1,locationY);
		tempPiece[3] = new Square(locationX+1,locationY);
		tempPiece[4] = new Square(locationX+2,locationY);
		return tempPiece;
		
	}
	public static Square[] rotateRight(Square[] piece) {
		
		for (int i=1;i<piece.length;i++){
			int relativeCoordinateX = piece[i].xLoc - piece[0].xLoc;
			int relativeCoordinateY = piece[i].yLoc - piece[0].yLoc;
			int temp=relativeCoordinateX;
			relativeCoordinateX=-relativeCoordinateY;
			relativeCoordinateY=temp;
			piece[i].xLoc = piece[0].xLoc + relativeCoordinateX;
			piece[i].yLoc = piece[0].yLoc + relativeCoordinateY;
		}
		return piece;
	}

	 Square[] mirrorPiece(Square[] piece) {
		   for (int i=1;i<piece.length;i++){
			   int relativeCoordinateX = piece[i].yLoc - piece[0].yLoc;
			   relativeCoordinateX=-relativeCoordinateX;
			   piece[i].yLoc = piece[0].yLoc + relativeCoordinateX;
		   }
	   	return piece;
	   }
	
	 //Method that returns a piece array to be used for hover effect
	 //Aaron Wise Robert Duarte
	Square[] getHover() {
		
		int xPick = 0;
		int yPick = 0;
		
		 Square [] temp = pieceOne(xPick, yPick);
		    
			
			switch(piecePick) {
		 	case 1: temp = pieceOne(xPick, yPick);
		 			break;
			case 2: temp = pieceTwo(xPick, yPick);
			break;
			case 3: temp = pieceThree(xPick, yPick);
			break;
			case 4: temp = pieceFour(xPick, yPick);
			break;
			case 5: temp = pieceFive(xPick, yPick);
			break;
			case 6: temp = pieceSix(xPick, yPick);
			break;
			case 7: temp = pieceSeven(xPick, yPick);
			break;
			case 8: temp = pieceEight(xPick, yPick);
			break;
			case 9: temp = pieceNine(xPick, yPick);
			break;
			case 10: temp = pieceTen(xPick, yPick);
			break;
			case 11: temp = pieceEleven(xPick, yPick);
			break;
			case 12: temp = pieceTwelve(xPick, yPick);
			break;
			case 13: temp = pieceThirteen(xPick, yPick);
			break;
			case 14: temp = pieceFourteen(xPick, yPick);
			break;
			case 15: temp = pieceFifteen(xPick, yPick);
			break;
			case 16: temp = pieceSixteen(xPick, yPick);
			break;
			case 17: temp = pieceSeventeen(xPick, yPick);
			break;
			case 18: temp = pieceEighteen(xPick, yPick);
			break;
			case 19: temp = pieceNineteen(xPick, yPick);
			break;
			case 20: temp = pieceTwenty(xPick, yPick);
			break;
			case 21: temp = pieceTwentyOne(xPick, yPick);
			break;
			
			
	}
			return temp;
	}
	
	//Last 4 methods for sending and reciveing user input 
	//Aaron Wise
	
	 public synchronized void sendPick(int packet) {
	        while (!pTransfer) {
	            try { 
	                wait();
	            } catch (InterruptedException e)  {
	                
	            }
	        }
	        pTransfer = false;
	         
	        this.piecePick = packet;
	        notifyAll();
	    }
	 
	 public synchronized int receivePick() {
	        while (pTransfer) {
	            try {
	                wait();
	            } catch (InterruptedException e)  {
	                 
	            }
	        }
	        pTransfer = true;
	 
	        notifyAll();
	        return piecePick;
	    }
	 
	
	 public synchronized void sendXY(int[] packet) {
	        while (!yTransfer) {
	            try { 
	                wait();
	            } catch (InterruptedException e)  {
	                
	            }
	        }
	        yTransfer = false;
	         
	        this.xy = packet;
	        notifyAll();
	    }
	 
	 public synchronized int[] receiveXY() {
	        while (yTransfer) {
	            try {
	                wait();
	            } catch (InterruptedException e)  {
	                 
	            }
	        }
	        yTransfer = true;
	 
	        notifyAll();
	        return xy;
	    }
}
