import java.awt.*;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

public class Enemy {
    int x, dx, y, dy, ay;
    int hp, attack;
    Image still;
    boolean InAir = false;
    boolean attacking = false;
    
    public Enemy() {
	ImageIcon i = new ImageIcon("images/playerImages/guy/robot.png"); //enemy image
	still = i.getImage();
        //starting Enemy postion/velocity/acceleration
	x = 700;
	y = 265;
        ay = 1;
        dy = 0;
        dx = 0;
        
        hp = 100;
        attack = 10;
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
    public int getHP(){
        return hp;
    }
    public int getAttack(){
        return attack;
    }
    
    
    public void takeDmg(Player p){
        hp -= p.getAttack();
    }
    
    
    public void AI(){
        
    }
    
}
