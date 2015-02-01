import java.awt.*;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

public class Player {
    int x, dx, y, dy, ay;
    int hp, attack;
    Image still;
    boolean InAir = false;
    boolean attacking = false;
    // 0 = left, 1 = right
    int facing = 0;
    int AttackSpeed;
    
    public Player() {
    	ImageIcon i = new ImageIcon("images/playerImages/guy/guySideDown.png"); //character image
    	still = i.getImage();
        // starting Player position/velocity/acceleration
    	x = 10;
    	y = 265;
        ay = 1;
        dy = 0;
        dx = 0;
        hp = 100;
        attack = 10;
        AttackSpeed = 10;
    }

    public void move() {
        //add velocities to positions/add gravity to yVel
         x += dx;
         dy += ay;
         y += dy;
         
         //check boundaries, if on ground, InAir = false
         if((y + 69) >= 334){
        	 y = 265;
        	 dy = 0;
        	 InAir = false;
         }
         if(x <= 0){
        	 x = 0;
         }
        else if((x + 57) >= 1024){
        	x = 1024 - 57;
        	}
    }

    public int getX() {
    	return x;
    }

    public int getY() {
    	return y;
    }

    public Image getImage() {
    	return still;
    }
    
    public boolean attacking(){
        return attacking;
    }
    public int facing(){
        return facing;
    }
    public int getHP(){
        return hp;
    }
    public int getAttack(){
        return attack;
    }
    public int getAttackSpeed(){
        return AttackSpeed;
    }
        
    public void takeDmg(Enemy e){
        hp -= e.getAttack();
    }
    

    public void keyPressed(KeyEvent e) {
    	int key = e.getKeyCode();
        	//input changes velocity
    	if (key == KeyEvent.VK_LEFT){
    		ImageIcon iLeft = new ImageIcon("images/playerImages/guy/guySideDownLeft.png"); //character image
    		still = iLeft.getImage();
    		dx = -3; //changed from -5 TO -3 so moves slower
    		facing = 0;
        }

    	if (key == KeyEvent.VK_RIGHT){
    		ImageIcon iRight = new ImageIcon("images/playerImages/guy/guySideDown.png"); //character image
    		still = iRight.getImage();
    		dx = 3; // changed from 5 TO 3 so moves slower
    		facing = 1;
        }
        //if on ground, can jump
        if(!InAir){
            if(key == KeyEvent.VK_UP){
                dy = -22; // changed from -20 to -22 so jumps higher
            	InAir = true;    
            }
        }
        if(key == KeyEvent.VK_SPACE)
            attacking = true;
    }

    public void keyReleased(KeyEvent e) {
    	int key = e.getKeyCode();
        //release of L/R key's result in 0 horizontal velocity
    	if (key == KeyEvent.VK_LEFT)
    		dx = 0;
    	
    	if (key == KeyEvent.VK_RIGHT)
    		dx = 0;
    	
    	if(key == KeyEvent.VK_SPACE)
            attacking = false;
    }

}
