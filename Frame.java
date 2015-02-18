//Clean up Robot/Propjectile Code. Really Messy
//Push to gitHub
//Pull from github
//need robot facing right
//implement a better projectile class
//find out why there was a former bug involving player contact
//make projectile implementable with some player weapons
//fix hitboxes on robot and player
//fix attack range's on robot and player
//give robot and player legit attack animations
//yaaaaaaaaa

import javax.swing.*;

public class Frame {
    public Frame(){
	JFrame frame = new JFrame();
	frame.getContentPane().add(new Board());
	frame.setTitle("Attack of the Trees");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setSize(1750,800); //frame size
	frame.setVisible(true);
	frame.setLocationRelativeTo(null);
    }
    
    public static void main(String args[]){
        Frame frame = new Frame();
    }
    
}
