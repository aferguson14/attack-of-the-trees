package src;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

import java.util.*;

public class GameOverScreen extends JFrame {
    
	private JPanel button;
	private JButton playAgainButton = new JButton("Play Again");
	private JButton exitButton = new JButton("Exit Game");
	
	public GameOverScreen() {
		// set frame characteristics
		this.setTitle("Game Over Screen");
		this.setSize(1500,500);
		this.setBackground(Color.gray);
		
		// set location of game over screen to middle of board
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	    this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
	    
	    this.setFocusable(true);
	    this.setUndecorated(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    
	    // set layout for adding buttons to panel
        JPanel buttonPanel = new JPanel();
        //buttonPane.setLayout(new BoxLayout(buttonPane,BoxLayout.X_AXIS));
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));

        // add buttons and listeners to bottom of frame
        buttonPanel.add(Box.createHorizontalGlue());
        buttonPanel.add(playAgainButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        buttonPanel.add(exitButton);
        
		playAgainButton.addActionListener(new PGL());
		exitButton.addActionListener(new EL());
         
	   /* buttonPane.add(playAgainButton);
		playAgainButton.addMouseListener(new PGL());
	    buttonPane.add(exitButton);
		exitButton.addMouseListener(new EL());*/
		
		// add label that says "GAME OVER" in center of frame
		JPanel labelPanel = new JPanel();
		JLabel label = new JLabel("GAME OVER");
		label.setFont(new Font("arial", Font.BOLD, 80));
		labelPanel.add(label);
        
		this.getContentPane().add(BorderLayout.SOUTH, buttonPanel);
		this.getContentPane().add(BorderLayout.CENTER, labelPanel);
	}
	
	public void paint(Graphics g) {	
		Graphics2D g2 = (Graphics2D) g;
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