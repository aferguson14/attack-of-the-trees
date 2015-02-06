import java.awt.*;
import javax.swing.*;

public class PauseMenu extends JFrame{
    
    public JTabbedPane tabbedPane;
    public JPanel shop ;
    public JPanel save;
    public JPanel help;
    
    public PauseMenu(){
	setTitle("Pause Menu");
	setSize(500,350);
	setBackground(Color.gray);
	
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
    
    public void setPauseMenuVisible(boolean b){
	this.setVisible(b);
    }
    
    public void createShopPage(){
	shop = new JPanel();
	shop.setLayout(new GridLayout(2,2));
	shop.add(new Button("Item 1"));
	shop.add(new Button("Item 2"));
	shop.add(new Button("Item 3"));
	shop.add(new Button("Item 4"));
    }
    
    public void createSavePage(){
	save = new JPanel();
	save.setLayout(new GridLayout(1,2));
	save.add(new Button("Save"));
	save.add(new Button("Save and Exit"));
    }
    
    public void createHelpPage(){
	help = new JPanel();
	JLabel label = new JLabel();
	label.setText("this is the help menu");
	help.add(label);
    }

    
    public void paintComponent(Graphics g){
	super.paint(g);
	Graphics2D g2d = (Graphics2D) g;
    }
   
}
