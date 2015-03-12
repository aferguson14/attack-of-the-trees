/*
//fix water robot
//update gnome with grenade
//ask ted for a few new images
*/
import javax.swing.*;

/** 
 * Frame represents the window that contains the game.
 * 
 * @author Shadee Barzin
 * @author Andrew Ferguson
 * @author Michele Haque
 * @author Brendan Murphy
 * @author Fengyu Wang
 * @version CS 48, Winter 2015
*/

public class Frame {
    /** 
     * No-arg constructor that creates the window for the game.
     */
    public Frame(){
	JFrame frame = new JFrame();
	frame.getContentPane().add(new Board());
	frame.setTitle("Attack of the Trees");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setSize(1750,800); //1750, 800frame size
	frame.setVisible(true);
	frame.setLocationRelativeTo(null);
    }

    /** 
     * Main method that when run, starts the game.
     */
    public static void main(String args[]){
        Frame frame = new Frame();
    }
    
}
