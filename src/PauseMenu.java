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
	
	public void createHelpPage(){
		//create the help menu
		help = new JPanel();
		help.setLayout(new BorderLayout());
		
		// add controls instructions
		JTextArea label = new JTextArea("Controls:\n");
		label.setFont(new Font("arial", Font.BOLD, 18));
		JTextArea text = new JTextArea("Use A/D to move left/right\n" + "Use W to jump\n" + "Use S to duck\n" 
				+ "Use your mouse to aim and left-click to shoot\n" + "Use Q and E or 1-5 to swap weapons");
		text.setRows(5);
		text.setColumns(25);
		text.setFont(new Font("arial", Font.PLAIN, 15));
		text.setLineWrap(false);
		text.setWrapStyleWord(false);
		
		// add text areas to panel
		help.add(label, BorderLayout.NORTH);
		help.add(text, BorderLayout.CENTER);
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
					os.writeObject(bd.getLvlhandler());
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

	public void paintComponent(Graphics g){
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
	
	public JPanel getShop() {
		return shop;
	}

	public void setShop(JPanel shop) {
		this.shop = shop;
	}
	
}
