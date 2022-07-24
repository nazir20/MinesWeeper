package mnSharifi;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MinesWeeper implements MouseListener{
	
	/**
	 * 
	 * @variables
	 * 
	 */
	JFrame frame;
	Btn[][] board = new Btn[10][10];
	int openedButton;
	
	/**
	 * 
	 * @constructor
	 * 
	 */
	public MinesWeeper() {
		
		setOpenedButton(0);
		frame = new JFrame("MinesWeeper Game");
		frame.setSize(800, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new GridLayout(10,10));
		
		for(int row = 0; row<board.length; row++) {
			for(int col = 0; col<board[0].length; col++) {
				Btn btn = new Btn(row, col);
				frame.add(btn);
				btn.addMouseListener(this);
				board[row][col] = btn;
			}
		}
		
		generateMine();
		updateCount();
//		printMine();
		frame.setVisible(true);
		
	}
	

	/**
	 * 
	 * @methods 
	 * 
	 */
	
	/**
	 * 
	 * 
	 * @generateMine() method, generates mines on different board locations randomly...
	 * 
	 * 
	 */
	public void generateMine() {
		
		int i = 0;
		while(i<10) {
			
			int randomRow = (int) (Math.random() * board.length);
			int randomCol = (int) (Math.random() * board[0].length);
			
			while(board[randomRow][randomCol].isMine()) {
				
				randomRow = (int) (Math.random() * board.length);
				randomCol = (int) (Math.random() * board[0].length);
				
			}
			
			board[randomRow][randomCol].setMine(true);
			i++;
			
		}
		
	}
	
	/**
	 * 
	 *
	 * @print() method, set 'Mine.png' icon to the locations which has mine
	 * if there is no mine, the method sets a new value to that location according to count() method
	 *
	 *
	 */
	
	public void print() {
		
		for(int row = 0; row<board.length; row++) {
			for(int col = 0; col<board[0].length; col++) {
				if(board[row][col].isMine()) {
					Image img = new ImageIcon("mine.png").getImage() ;  
					Image newImg = img.getScaledInstance( 45, 45,  java.awt.Image.SCALE_SMOOTH ) ;  
					board[row][col].setIcon(new ImageIcon(newImg));
				}else {
					board[row][col].setText(board[row][col].getCount()+"");
					board[row][col].setEnabled(false);
				}
			}
		}
		
	}
	
	/**
	 * 
	 *
	 * @printMine() method is going to print the all the mines on the board...
	 *
	 *
	 */
	
	public void printMine() {
		
		for(int row = 0; row<board.length; row++) {
			for(int col = 0; col<board[0].length; col++) {
				if(board[row][col].isMine()) {
					Image img = new ImageIcon("mine.png").getImage() ;  
					Image newImg = img.getScaledInstance( 45, 45,  java.awt.Image.SCALE_SMOOTH ) ;  
					board[row][col].setIcon(new ImageIcon(newImg));
				}
			}
		}
	}
	
	
	/**
	 * 
	 * 
	 * @counting() method, counts the number of mines around the board location which do not have mine
	 * it counts the number of mines for 8 side of the location
	 * 
	 * 
	 */
	public void counting(int row, int col) {
		
		for(int i = row-1; i<=row+1; i++) {
			for(int j = col-1; j<=col+1; j++) {
				try {
					int value = board[i][j].getCount();
					board[i][j].setCount(++value);
				}catch(Exception e) {
					
				}
	
			}
		}
	}
	
	/**
	 * 
	 *
	 * @updateCount() method, updates the values of the location which has no mine inside it
	 *
	 *
	 */
	public void updateCount() {
		
		for(int row = 0; row<board.length; row++) {
			for(int col = 0; col<board[0].length; col++) {
				if(board[row][col].isMine()) {
					counting(row, col);
				}
			}
		}
	}
	
	/**
	 * 
	 * 
	 * @open() method
	 * 
	 * 
	 */
	public void open(int row, int col) {
		
		if(row< 0 || row >= board.length || col<0 || col>=board[0].length 
		|| board[row][col].getText().length() > 0 || board[row][col].isEnabled()==false) {
			return;
		}else if(board[row][col].getCount() != 0){
			board[row][col].setText(board[row][col].getCount()+"");
			board[row][col].setEnabled(false);
			setOpenedButton(getOpenedButton()+1);
		}else {
			setOpenedButton(getOpenedButton()+1);
			board[row][col].setEnabled(false);
			open(row-1, col);
			open(row+1, col);
			open(row, col-1);
			open(row, col+1);
		}
		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
		Btn b = (Btn) e.getComponent();
		/*
		 * 
		 * @left click event
		 * 
		 */
		if(e.getButton() == 1) {
			System.out.println("Left Click!");
			if(b.isMine()) {
				/**
				 * 
				 * if the player selects the location which is filled with mine,
				 * the game finishes
				 * and the print() method is called
				 * 
				 */
				JOptionPane.showMessageDialog(frame, "You donated a mine! Game is Over!");
				print();
			}else {
				open(b.getRow(), b.getColumn());
				/**
				 * 
				 * in this part, we control if the player has opened all the location which
				 * was not filled with mine;
				 * if so, the player wins the game
				 * 
				 */
				if(getOpenedButton() == (board.length * board[0].length)-10) {
					
					JOptionPane.showMessageDialog(frame, "Congratulations! You won the game!");
					print();
				}
			}
		}
		/*
		 * 
		 * @right click event
		 * 
		 */
		else if(e.getButton() == 3) {
			System.out.println("Right Click!");
			if(!b.isFlag()) {
				Image img = new ImageIcon("flag.png").getImage() ;  
				Image newImg = img.getScaledInstance( 45, 45,  java.awt.Image.SCALE_SMOOTH ) ;  
				b.setIcon(new ImageIcon(newImg));
				b.setFlag(true);
			}else {
				b.setIcon(null);
				b.setFlag(false);
			}
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * 
	 * @getter and @setter
	 * 
	 */
	public int getOpenedButton() {
		return openedButton;
	}

	public void setOpenedButton(int openedButton) {
		this.openedButton = openedButton;
	}

}
