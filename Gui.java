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

public class Gui extends JFrame {

	private JPanel contentPane;
	private JButton btnInstructions;
	private JButton btnMainMenu;
	private JLabel lblInsertInstructionsFor;
	private JPanel MainMenu;
	private JPanel Instructions;
	private JPanel Settings;
	private JPanel Board;
	private JButton btnPlay;
	private JButton btnSettings;
	private JButton btnExit;
	private JButton btnMainMenu_2;
	
	private final JPanel GameBoard = new JPanel(new BorderLayout(3, 3));
	private JButton[][] blockusBoardSquares = new JButton[20][20];
    private JPanel blockusBoard;
    private JButton btnMainMenu2;
	private JButton[] selectionButtons = new JButton[21];
    
    
    int xPick = 0;
    int yPick = 0;
    boolean pickReady = false;
    int piecePick = -1;
    
    private JPanel PiecePanel;
    private JPanel SelectionPanel;
    
	/**
	 * Create the frame.
	 */
	public Gui() {
		
		componentInit();
		eventInit();
		
	}

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
		
		btnSettings.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MainMenu.setVisible(false);
				Settings.setVisible(true);
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
		
		////////////Settings Events//////////
		btnMainMenu_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Settings.setVisible(false);
				MainMenu.setVisible(true);
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
		
		
		 for (int ii = 0; ii < 20; ii++) {
	            for (int jj = 0; jj <20; jj++) {
	            	int j = ii;
	            	int i = jj;
	                 blockusBoardSquares[ii][jj].addMouseListener(new MouseAdapter() {
	         			@Override
	        			public void mouseClicked(MouseEvent arg0) {
	        				
	         				xPick = i;
	         				yPick = j;
	         				pickReady = true;
	         				

	        			}
	        		});
	            }
	        }
		
	      for (int i = 0; i <20; i++) {
          	
          	int pick = i;
               selectionButtons[i].addMouseListener(new MouseAdapter() {
       			@Override
      			public void mouseClicked(MouseEvent arg0) {
       				
       				piecePick = pick + 1;

      			}
      		});
          }
		
		
		

	}
	
	

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
		btnSettings = new JButton("Settings");
		btnSettings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnExit = new JButton("Exit");
		
		GroupLayout gl_MainMenu = new GroupLayout(MainMenu);
		gl_MainMenu.setHorizontalGroup(
			gl_MainMenu.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_MainMenu.createSequentialGroup()
					.addGap(167)
					.addGroup(gl_MainMenu.createParallelGroup(Alignment.LEADING)
						.addComponent(btnExit, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 563, Short.MAX_VALUE)
						.addComponent(btnSettings, GroupLayout.DEFAULT_SIZE, 563, Short.MAX_VALUE)
						.addComponent(lblBlockus, GroupLayout.DEFAULT_SIZE, 563, Short.MAX_VALUE)
						.addComponent(btnPlay, GroupLayout.DEFAULT_SIZE, 563, Short.MAX_VALUE)
						.addComponent(btnInstructions, GroupLayout.DEFAULT_SIZE, 563, Short.MAX_VALUE))
					.addGap(584))
		);
		gl_MainMenu.setVerticalGroup(
			gl_MainMenu.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_MainMenu.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblBlockus, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE)
					.addGap(49)
					.addComponent(btnPlay, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnInstructions, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnSettings, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
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

       
        for (int ii = 0; ii < 20; ii++) {
            for (int jj = 0; jj <20; jj++) {
                 blockusBoard.add(blockusBoardSquares[jj][ii]);
            }
        }
        
        
        
		
		btnMainMenu2 = new JButton("MainMenu");
		
		PiecePanel = new JPanel();
		try {
		BufferedImage myPicture = ImageIO.read(new File("Screen Shot 2018-11-14 at 1.33.19 PM.png"));
		JLabel picLabel = new JLabel(new ImageIcon(myPicture));
		PiecePanel.add(picLabel);
		}
		catch(IOException ex){
			
		}
		
		SelectionPanel = new JPanel(new GridLayout(0, 10));;
		
		
		 for (int i = 0; i < selectionButtons.length; i++) {
             JButton b = new JButton();
             b.setMargin(buttonMargin);
             String num = Integer.toString(i+1);
             b.setText(num);
             b.setBackground(Color.WHITE);
             selectionButtons[i] = b;
         }
		
		 for (int i = 0; i <20; i++) {
             SelectionPanel.add(selectionButtons[i]);
        }
		
		
		GroupLayout gl_Board = new GroupLayout(Board);
		gl_Board.setHorizontalGroup(
			gl_Board.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_Board.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_Board.createParallelGroup(Alignment.LEADING)
						.addComponent(btnMainMenu2, Alignment.TRAILING)
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
					.addComponent(btnMainMenu2)
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
		
		Settings = new JPanel();
		contentPane.add(Settings, "name_3011991141227893");
		
		btnMainMenu_2 = new JButton("Main Menu");
		
		GroupLayout gl_Settings = new GroupLayout(Settings);
		gl_Settings.setHorizontalGroup(
			gl_Settings.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_Settings.createSequentialGroup()
					.addContainerGap(252, Short.MAX_VALUE)
					.addComponent(btnMainMenu_2)
					.addContainerGap())
		);
		gl_Settings.setVerticalGroup(
			gl_Settings.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_Settings.createSequentialGroup()
					.addContainerGap(163, Short.MAX_VALUE)
					.addComponent(btnMainMenu_2)
					.addContainerGap())
		);
		Settings.setLayout(gl_Settings);
		contentPane.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{MainMenu}));
		
		
		
	}
	
	public void setColor(int i , int j , Color color) {
		
		blockusBoardSquares[j][i].setBackground(color);
		
	}
	public int getPiecePick() {
		return piecePick;
	}
	
	
	public int getXPick() {
		return xPick;
	}
	
	public int getYPick() {
		return yPick;
	}
	
	public boolean getPickReady() {
		return pickReady;
	}
}
