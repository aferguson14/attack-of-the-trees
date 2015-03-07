import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import javax.swing.*;

import java.util.*;


public class PauseMenu extends JFrame{
    
    public JTabbedPane tabbedPane;
    public JPanel shop ;
    public JPanel save;
    public JPanel help;
    //JButton clicked;
    //ArrayList<String> weaponNames = new ArrayList<String>(Arrays.asList("sword", "stick", "axe", "gun"));
    Player p;
    Board bd;
    JButton saveGame;
    JButton saveNQuit;
    
    public PauseMenu(Player p, Board b){
	this.p = p;
	bd = b;
	setTitle("Pause Menu");
	setSize(500,350);
	//setBackground(Color.gray);
        
    //set the location of the pause menu in the middle of the board
    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
	
	addKeyListener(new KL());
	setFocusable(true);

	setUndecorated(true);
	//setOpacity(.85f);
	//this.setLocationRelativeTo(null);

	JPanel topPanel = new JPanel();
	topPanel.setLayout(new BorderLayout());
	getContentPane().add(topPanel);
	this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	
	shop = new ShopPanel(p);
	createSavePage();
	createHelpPage();
	
	tabbedPane = new JTabbedPane();
	tabbedPane.addTab("Shop", shop);
	tabbedPane.addTab("Save", save);
	tabbedPane.addTab("Help", help);
	topPanel.add(tabbedPane, BorderLayout.CENTER);
	
    }
    
	public void createSavePage() {
		// create the panel for the save game feature
		save = new JPanel();
		// save.setLayout(new GridLayout(1,2));
		saveGame = new JButton("Save");
		saveNQuit = new JButton("Save and Exit");
		saveGame.addActionListener(actionListener);
		saveNQuit.addActionListener(actionListener);
		save.add(saveGame);
		save.add(saveNQuit);
	}

	ActionListener actionListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent ae) {
			// TODO Auto-generated method stub
			JButton b = (JButton) ae.getSource();
			if (b.equals(saveGame) || b.equals(saveNQuit)) {
				// save the game
				try {
					// Doesn't save progress, can't buy weapons, and don't have
					// enemy images
					FileOutputStream fs = new FileOutputStream("saved.ser");
					ObjectOutputStream os = new ObjectOutputStream(fs);
					os.flush();
					// os.writeObject(bd);
					os.writeObject(bd.getP());
					os.writeObject(bd.getEnemies());
					os.writeObject(bd.getResources());
					os.writeInt(bd.getTotalProgress());
					os.writeInt(bd.getLevel());
					//System.out.println(bd.getTotalProgress());
					//System.out.println(bd.getLevel());

					os.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}

				if (b.equals(saveNQuit)) {
					System.exit(0);
				}
			}

		}

	};
	
	
    public void createHelpPage(){
    	//create the help menu
	help = new JPanel();
	String text = 
	    "Controls:&nbsp;&nbsp;&nbsp;&nbsp;Jump&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;&nbsp;&nbsp;W<br>" +
	    "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +

	    "Move Left&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;&nbsp;&nbsp;A<br>" +
    	    "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +

	    "Move Right   -   D<br>" +
	    "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +

            "Duck         -   S<br>" +
	              "            Attack/Shoot -   Left Click<br>";
	JLabel label = new JLabel("<html>"+text+"</html>");
	//	label.setText("Controls: To move/jump use A,W,D");
	help.add(label);
	/*JLabel up = new JLabel();
	label.setText("To jump press W");
	help.add(up);
	JLabel lr = new JLabel();
	label.setText("To move left press A, to move right press D");
	help.add(lr);*/
    }

    
    public void paintComponent(Graphics g){
    	//pretty sure this is unused...
	super.paint(g);
	Graphics2D g2d = (Graphics2D) g;
    }

    public void keyPressedMenu(KeyEvent e) {
	int key = e.getKeyCode();
	//check to see if the key pressed is P
	//if so if the state is in game then change it to pause
	//if the state is paused then put it in game
	if(key == KeyEvent.VK_P){
	    if(Board.getState() == Board.STATE.GAME) {
		Board.setState(Board.STATE.PAUSE);
		this.setVisible(true);
	    }
	    else if (Board.getState() == Board.STATE.PAUSE){
		this.dispose();
		//this.setVisible(false);
		Board.setState(Board.STATE.GAME);
	    }
        }
    }	
	
    private class KL extends KeyAdapter {
	public void keyPressed(KeyEvent e) {
	    keyPressedMenu(e);
	}
	public int getKeyPressed(KeyEvent e) {
	    int key = e.getKeyCode();
	    return key;
	}
    }
   
}