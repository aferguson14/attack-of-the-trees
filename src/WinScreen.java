import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.Border;

/** 
 * WinScreen represents the window that appears on the user's screen when the game is won.
 * 
 * @author Shadee Barzin
 * @author Andrew Ferguson
 * @author Michele Haque
 * @author Brendan Murphy
 * @author Fengyu Want
 * @version CS 48, Winter 2015
*/

public class WinScreen extends JFrame {
	public JPanel board;
	public JPanel buttonPanel;
	public JPanel labelPanel;
	public JButton playAgainButton;
	public JButton exitButton;
	public JLabel label;

/** 
 * @param b the game Board
 * Constructor
*/
	public WinScreen(JPanel b) {
		
		// superclass constructor
		super();
		
		board = b;
		
		// set frame characteristics
		this.setTitle("Win Screen");
		this.setSize(500, 300);

		// set location of game over screen to middle of board
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		this.setLocationRelativeTo(null);

		// remove frame buttons
		this.setFocusable(true);
		this.setUndecorated(true);

		// create background panel to hold
		JPanel bkgd = new JPanel();
		bkgd.setLayout(new BorderLayout());
		this.getContentPane().add(bkgd, BorderLayout.CENTER);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// set layout for adding buttons to panel
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(0,10,10,10));

		// add buttons to buttonPanel
		buttonPanel.add(Box.createHorizontalGlue());
		playAgainButton = new JButton("Play Again");
		buttonPanel.add(playAgainButton);
		buttonPanel.add(Box.createRigidArea(new Dimension(10,0)));
		exitButton = new JButton("Exit Game");
		buttonPanel.add(exitButton);
		// add listeners to button
		playAgainButton.addActionListener(new PAL());
		exitButton.addActionListener(new EL());		
		// add buttonPanel to background
		bkgd.add(buttonPanel, BorderLayout.SOUTH);

		// add label that says "YOU WIN" in center
		labelPanel = new JPanel();
		label = new JLabel("YOU WIN!");
		label.setFont(new Font("arial", Font.BOLD, 120));
		label.setForeground(new Color(0x000066));
		Border labelBorder = BorderFactory.createLineBorder(new Color(0xBAE6DA), 8);
		label.setBorder(labelBorder);
		labelPanel.add(label);
		// add labelPanel to background
		bkgd.add(labelPanel, BorderLayout.CENTER);

		this.pack();
	}

	/*
	 * @param e ActionEvent
	 * when Play Again Button is clicked, change state of the game to Menu
	 */	
	public void playAgain(ActionEvent e) {
		this.dispose();
		Board.setState(Board.STATE.MENU);
		board.repaint();
	}

	/*
	 * @param g Graphics object
	 */
	public void paintComponent(Graphics g) {	
		super.paint(g);
	}
	
	/** 
	 * inner class that is an Action Listener for playAgainButton 
	*/
	private class PAL implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (Board.getState() == Board.STATE.GAMEOVER)
				playAgain(e);
		}	
	} //PAL

	/** 
	 * inner class that is an Action Listener for exitButton 
	*/
	private class EL implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);			
		}			
	} //EL

} //WinScreen
