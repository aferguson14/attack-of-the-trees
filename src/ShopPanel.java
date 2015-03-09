import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class ShopPanel extends JPanel {

    ArrayList<String> weaponNames = new ArrayList<String>(Arrays.asList("sword", "bow", "axe", "gun"));
    ArrayList<JButton> buttons = new ArrayList<JButton>();
    int indexClicked;
    JButton buy = new JButton("Purchase");
    JButton purchased = new JButton("Already Purchased");
    Player p;
    int logs, coins;
    int[] logCost = {0,1,5,7};
    int[] coinCost = {1,3,5,10};
    String[] weaponDescrip;
    int[] weaponsBought = {0,0,0,0};
    JFrame jf;
    
    public ShopPanel(Player p) {
	// shop.setLayout(new GridLayout(2,2));
	//setBackground(Color.gray);
	//add buttons for the different weapons
	this.p = p;
	jf = new JFrame();
	jf.add(new JMenuItem("You don't have enough resources"));
	Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	jf.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
	//jf.setLocationRelativeTo(null);
	jf.pack();
	for (int i = 0; i < weaponNames.size(); i++) {
	    JButton j = new JButton(new ImageIcon("../images/weaponImage/" + weaponNames.get(i) + "Left.png"));
	    j.setPreferredSize(new Dimension(100,100));
	    buttons.add(i, j);
	    this.add(buttons.get(i));
	    buttons.get(i).addActionListener(actionListener);
	}
    }

    ActionListener actionListener = new ActionListener() {
	    public void actionPerformed(ActionEvent actionEvent) {
		//System.out.println(actionEvent.getSource());
		//check to see which button was clicked 
		jf.setVisible(false);
		JButton b = (JButton) actionEvent.getSource();

		if(b.equals(buttons.get(0))){
		    indexClicked = 0;
		    repaint();
		}
		else if(b.equals(buttons.get(1))){
		    indexClicked = 1;
		    repaint();
		}
		else if(b.equals(buttons.get(2))){
		    indexClicked = 2;
		    repaint();
		}
		else if(b.equals(buttons.get(3))){
		    indexClicked = 3;
		    repaint();
		}
		else if(b.equals(buy)){
		    //check which weapon
		    //check if player has those resources
		    //if not, tell them like JPopUpMenu
		    //if purchased, then change player's resource numbers
		    //add weapon to player's list of weapons...
		    logs = p.getLogCount();
		    coins = p.getCoinCount();
		    if(logs >= logCost[indexClicked] && coins >= coinCost[indexClicked] && weaponsBought[indexClicked] == 0){
			p.setCoinCount((coins-coinCost[indexClicked]));
			p.setLogCount((logs-logCost[indexClicked]));
			weaponsBought[indexClicked] = 1;
			
			//System.out.println(logs + " " + coins);
			//need to add this weapon to the players arraylist of weapons
			//Uncomment next three lines when we have the other classes
			if(indexClicked == 0) p.AddWeapon(1, new Sword(p.getXCoord(), p.getYCoord()));
			if(indexClicked == 1) p.AddWeapon(4, new Bow(p.getXCoord(), p.getYCoord()));
			if(indexClicked == 2) p.AddWeapon(2, new Axe(p.getXCoord(), p.getYCoord()));
			if(indexClicked == 3) p.AddWeapon(3, new Gun(p.getXCoord(), p.getYCoord()));
			repaint();
		    }
		    else if (weaponsBought[indexClicked] == 0){
			jf.setVisible(true);
			//System.out.println("Don't have enough resources");
		    }
		    //jmenu.setVisible(false);
		    //repaint();
		}
		//shop.remove(cost);
	    }
	};
    
    public void paintComponent(Graphics g) {
	super.paintComponent(g);
	Graphics2D g2d = (Graphics2D) g;
	
	this.remove(buy);
	this.remove(purchased);
	
	//paint the different images and costs for the weapons and the purchase button
	ImageIcon i = new ImageIcon("../images/weaponImage/" + weaponNames.get(indexClicked) + "Left.png");
	Image weapon = i.getImage();
	g2d.drawImage(weapon, 220, 150, null);
	g2d.drawString("Cost: " + logCost[indexClicked] + " logs, " + coinCost[indexClicked] + " coins", 185, 250);
	//g2d.drawString(weaponDescrip[indexClicked], 150, 250);
	
	// check to see if they've already purchased that item
	// if not draw buy button
	if(weaponsBought[indexClicked] == 0){
	    buy.setLocation(200, 275);
	    this.add(buy);
	    buy.addActionListener(actionListener);
	}
 
	// if so, draw already purchased (which might not want to be a button
	// and just some text)
	else{ //if(weaponsBought[indexClicked] == 1){
	    /*Rectangle box = new Rectangle(20, 50, 200, 275);
	            g2d.draw(box);
		    g2d.drawString("Already Purchased", 205, 280);*/
	    purchased.setLocation(170,275);
	    this.add(purchased);
	}
    }

}
