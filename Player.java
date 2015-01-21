package lumberjackGame;

import java.util.*;
import java.awt.*;
import javax.swing.*;


public class Player {
    int x, dx, y, dy; //x coordinate, dx is change in x coordinate (determine left or right)

    public Player(){
	ImageIcon i = new ImageIcon("/images/playerImages/guy/guyFront.jpg"); //character image
	still = i.getImage();
	x = 10;
	y = 172;
    }
    
    public void move(){
	x = x + dx;
	y = y + dy;
    }

    public int getX(){
	return x;
    }

    public int getY(){
	return y;
    }

    public Image getImage(){
	return still;
    }

    public void keyPressed(KeyEvent e){
	int key = e.getKeyCode();
	
	if(key == KeyEvent.VK_KEY_A)
	    dx = -1;

	if(key == KeyEvent.VK_KEY_D)
	    dx = 1;

	if(key == KeyEvent.VK_KEY_W)
	    dy = 1;
    }
    public void keyReleased(KeyEvent e){
	int key = e.getKeyCode();
	
	if(key == KeyEvent.VK_KEY_A)
	    dx = 0;

	if(key == KeyEvent.VK_KEY_D)
	    dx = 0;

	if(key == KeyEvent.VK_KEY_W)
	    dy = 0;
    }

}
