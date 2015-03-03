package src;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class GameOverScreen extends JFrame {
    
	private JPanel bkgd;
	private JPanel buttonPanel;
	private JPanel labelPanel;
	private JButton playAgainButton;
	private JButton exitButton;
	private JLabel label;
	
	public GameOverScreen() {
		// set frame characteristics
		this.setTitle("Game Over Screen");
		this.setSize(1000,600);
		
		// set location of game over screen to middle of board
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	    this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
	    
	    //this.setVisible(true);
	    this.setFocusable(true);
	    this.setUndecorated(true);
	    
	    // create background panel to hold
	    bkgd = new JPanel();
	    bkgd.setLayout(new BorderLayout());
	    this.getContentPane().add(BorderLayout.CENTER, bkgd);
	    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    
	    // set layout for adding buttons to panel
        buttonPanel = new JPanel();
	    //buttonPanel.setLayout(new BoxLayout(buttonPane,BoxLayout.X_AXIS));
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));

        // add buttons and listeners to bottom of frame
        buttonPanel.add(Box.createHorizontalGlue());
    	playAgainButton = new JButton("Play Again");
        buttonPanel.add(playAgainButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        exitButton = new JButton("Exit Game");
        buttonPanel.add(exitButton);
		bkgd.add(BorderLayout.SOUTH, buttonPanel);
        
		playAgainButton.addActionListener(new PGL());
		exitButton.addActionListener(new EL());
         
	   /* buttonPanel.add(playAgainButton);
		playAgainButton.addMouseListener(new PGL());
	    buttonPanel.add(exitButton);
		exitButton.addMouseListener(new EL());*/
		
		// add label that says "GAME OVER" in center of frame
        labelPanel = new JPanel();
        label = new JLabel("GAME OVER");
		label.setFont(new Font("arial", Font.BOLD, 80));
		labelPanel.add(label);
		bkgd.add(BorderLayout.CENTER, labelPanel);

	}
	
	/*
	 * @param g Graphics object g
	 */
	public void paint(Graphics g) {	
	}

	private class PGL implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Board.setState(Board.STATE.MENU);
		}	
	} //PGL
	
	private class EL implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);			
		}			
	} //EL
	
} //GOScreen