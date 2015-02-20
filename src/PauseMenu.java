import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;
import javax.swing.JTabbedPane;


public class PauseMenu extends JFrame{
    
    public JTabbedPane tabbedPane;
    public JPanel shop ;
    public JPanel save;
    public JPanel help;
    
    public PauseMenu(){
	setTitle("Pause Menu");
	setSize(500,350);
	setBackground(Color.gray);
        
        //set the location of the pause menu in the middle of the board
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
	
	addKeyListener(new KL());
	setFocusable(true);

	setUndecorated(true);
	setOpacity(.85f);
	//this.setLocationRelativeTo(null);

	JPanel topPanel = new JPanel();
	topPanel.setLayout(new BorderLayout());
	getContentPane().add(topPanel);
	this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	
	createShopPage();
	createSavePage();
	createHelpPage();
	
	tabbedPane = new JTabbedPane();
	tabbedPane.addTab("Shop", shop);
	tabbedPane.addTab("Save", save);
	tabbedPane.addTab("Help", help);
	topPanel.add(tabbedPane, BorderLayout.CENTER);
	
    }
    
    public void createShopPage(){
    	//create the panel for the shop
	shop = new JPanel();
	shop.setLayout(new GridLayout(2,2));
	ImageIcon sword = new ImageIcon("../images/weaponImage/sword.png");
	ImageIcon stick = new ImageIcon("../images/weaponImage/stick.png");
	ImageIcon gun = new ImageIcon("../images/weaponImage/gun.png");
	ImageIcon axe = new ImageIcon("../images/weaponImage/axe.png");
	shop.add(new JButton(sword));
	shop.add(new JButton(stick));
	shop.add(new JButton(gun));
	shop.add(new JButton(axe));
    }
    
    public void createSavePage(){
    	//create the panel for the save game feature
	save = new JPanel();
	save.setLayout(new GridLayout(1,2));
	save.add(new Button("Save"));
	save.add(new Button("Save and Exit"));
    }
    
    public void createHelpPage(){
    	//create the help menu
	help = new JPanel();
	JLabel label = new JLabel();
	label.setText("this is the help menu");
	help.add(label);
    }

    
    public void paintComponent(Graphics g){
    	//pretty sure this is unused...
	super.paint(g);
	Graphics2D g2d = (Graphics2D) g;
    }

    public void keyPressedMenu(KeyEvent e) {
	// TODO Auto-generated method stub
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
