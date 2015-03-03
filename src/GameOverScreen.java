import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class GameOverScreen extends JFrame {
    
	public JPanel buttonPanel;
	public JPanel labelPanel;
	public JButton playAgainButton;
	public JButton exitButton;
	public JLabel label;
	
	public GameOverScreen() {
		super();
		// set frame characteristics
		this.setTitle("Game Over Screen");
		this.setSize(1000,600);
		
		// set location of game over screen to middle of board
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	    this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
	    
	    this.setVisible(true);
	    //this.setFocusable(true);
	    //this.setUndecorated(true);
	    
	    // create background panel to hold
	    JPanel bkgd = new JPanel();
	    bkgd.setLayout(new BorderLayout());
	    this.getContentPane().add(bkgd, BorderLayout.CENTER);
	    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    
	    // set layout for adding buttons to panel
        buttonPanel = new JPanel();
	    //buttonPanel.setLayout(new BoxLayout(buttonPane,BoxLayout.X_AXIS));
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));

        // add buttons and listeners to buttonPanel
        buttonPanel.add(Box.createHorizontalGlue());
    	playAgainButton = new JButton("Play Again");
        buttonPanel.add(playAgainButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        exitButton = new JButton("Exit Game");
        buttonPanel.add(exitButton);
		// add buttonPanel to background
        bkgd.add(buttonPanel, BorderLayout.SOUTH);
        
		playAgainButton.addActionListener(new PGL());
		exitButton.addActionListener(new EL());
         
	   /* buttonPanel.add(playAgainButton);
		playAgainButton.addMouseListener(new PGL());
	    buttonPanel.add(exitButton);
		exitButton.addMouseListener(new EL());*/
		
		// add label that says "GAME OVER" in center
        labelPanel = new JPanel();
        label = new JLabel("GAME OVER");
		label.setFont(new Font("arial", Font.BOLD, 80));
		label.setForeground(new Color(0x0099FF));
		labelPanel.add(label);

		// add labelPanel to frame
		bkgd.add(labelPanel, BorderLayout.CENTER);
		
		this.pack();
	}
	
	public void createButtonPanel(){
		 // set layout for adding buttons to panel
        buttonPanel = new JPanel();
	    //buttonPanel.setLayout(new BoxLayout(buttonPane,BoxLayout.X_AXIS));
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));

        // add buttons and listeners to buttonPanel
        buttonPanel.add(Box.createHorizontalGlue());
    	playAgainButton = new JButton("Play Again");
        buttonPanel.add(playAgainButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        exitButton = new JButton("Exit Game");
        buttonPanel.add(exitButton);
        
		playAgainButton.addActionListener(new PGL());
		exitButton.addActionListener(new EL());
	}
	
	public void createLabelPanel(){
		// add label that says "GAME OVER" in center
        labelPanel = new JPanel();
        label = new JLabel("GAME OVER");
		label.setFont(new Font("arial", Font.BOLD, 80));
		labelPanel.add(label);
	}
	
	/*
	 * @param g Graphics object g
	 */
	public void paintComponent(Graphics g) {	
		super.paint(g);
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
