import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ShopPanel extends JPanel {

    ArrayList<String> weaponNames = new ArrayList<String>(Arrays.asList("sword", "stick", "axe", "gun"));
    ArrayList<JButton> buttons = new ArrayList<JButton>();
    int indexClicked;
    JButton buy = new JButton("Purchase");
    JButton purchased = new JButton("Already Purchased");
    
    public ShopPanel() {
	// shop.setLayout(new GridLayout(2,2));
	//setBackground(Color.gray);
	//add buttons for the different weapons
	for (int i = 0; i < weaponNames.size(); i++) {
	    JButton j = new JButton(new ImageIcon("../images/weaponImage/" + weaponNames.get(i) + ".png"));
	    buttons.add(i, j);
	    this.add(buttons.get(i));
	    buttons.get(i).addActionListener(actionListener);
	}
    }

    ActionListener actionListener = new ActionListener() {
	    public void actionPerformed(ActionEvent actionEvent) {
		//System.out.println(actionEvent.getSource());
		//check to see which button was clicked 
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
		}
		//shop.remove(cost);
	    }
    };
    
    public void paintComponent(Graphics g) {
	super.paintComponent(g);
	Graphics2D g2d = (Graphics2D) g;
	
	//paint the different images and costs for the weapons and the purchase button
	if(indexClicked == 0){
	    ImageIcon i = new ImageIcon("../images/weaponImage/sword.png");
	    Image weapon = i.getImage();
	    g2d.drawImage(weapon, 220, 150, null);
	    g2d.drawString("Cost: 20 gold, 10 coins", 175, 220);
	    g2d.drawString("Pointy weapon with a short range", 150, 250);
	    //this.remove(buy);
	    /*buy.setLocation(220, 300);
	      this.add(buy);*/
	}
	
	if(indexClicked == 1){
	    ImageIcon i = new ImageIcon("../images/weaponImage/stick.png");
	    Image weapon = i.getImage();
	    g2d.drawImage(weapon, 220, 150, null);
	    g2d.drawString("Cost: 5 wood", 175, 220);
	    g2d.drawString("Weak weapon with a short range", 150, 250);
	}
	
	if(indexClicked == 2){
	    ImageIcon i = new ImageIcon("../images/weaponImage/axe.png");
	    Image weapon = i.getImage();
	    g2d.drawImage(weapon, 220, 150, null);
	    g2d.drawString("Cost: 15 wood, 20 gold, 10 coins", 175, 220);
	    g2d.drawString("Strong weapon with a short range", 150, 250);
	}

	if(indexClicked == 3){
	    ImageIcon i = new ImageIcon("../images/weaponImage/gun.png");
	    Image weapon = i.getImage();
	    g2d.drawImage(weapon, 220, 150, null);
	    g2d.drawString("Cost: 30 fire, 20 gold, 10 coins", 175, 220);
	    g2d.drawString("Powerful weapon with a long range", 150, 250);
	}
	//check to see if they've already purchased that item
	//if not draw buy button
	buy.setLocation(200, 275);
	this.add(buy);
	//if so, draw already purchased (which might not want to be a button and just some text)
    }

}