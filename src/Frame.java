/*
//fix water robot
//update gnome with grenade
//ask ted for a few new images
*/

import javax.swing.*;

public class Frame {
    //Sets up frame
    //executable
    public Frame(){
	JFrame frame = new JFrame();
	frame.getContentPane().add(new Board());
	frame.setTitle("Attack of the Trees");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setSize(1750,800); //1750, 800frame size
	frame.setVisible(true);
	frame.setLocationRelativeTo(null);
    }
    
    public static void main(String args[]){
        Frame frame = new Frame();
    }
    
}
