
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.Serializable;
import javax.swing.ImageIcon;

/** 
 * BearProjectile represents the projectile released by the Bear enemy
 * 
 * @author Shadee Barzin
 * @author Andrew Ferguson
 * @author Michele Haque
 * @author Brendan Murphy
 * @author Fengyu Wang
 * @version CS 48, Winter 2015
*/

public class BearProjectile extends Projectile implements Serializable{
    //Constructor
    private int time = 0;
    public BearProjectile(double x, double y, int direction, Graphics g, double angle, Player p) {
        super(x, y + 40, direction, g, angle, p);
        setXAcc(0);
        setYAcc(0);
        setSpeed(0);
        setYVel(0);
        
        setHorizontalSize(43);
        setVerticalSize(9);
        setAttack(1);
        ImageIcon iLeft = new ImageIcon("../images/attackImage/bearAttackLeft.png");
	ImageIcon iRight = new ImageIcon ("../images/attackImage/bearAttackRight.png");
        setStillLeft(iLeft);
	setStillRight(iRight);
	CreateImage(g);
        if(getFacing() == 0){
	    setXCoord(getXCoord() -20);
	}
	else if(getFacing() == 1){
	    setXCoord(getXCoord() + 60);
	    
	}	
                
        getXY(this.getSpeed(), angle);
    }
    //paints projectile
    public void CreateImage(Graphics g){
	Graphics2D g2d = (Graphics2D) g;
	if(getFacing() == 0){
	    g2d.drawImage(this.getStillLeft().getImage(),(int) (this.getXCoord()), (int) (this.getYCoord()), null);
	}
	else if(getFacing() == 1){
	    g2d.drawImage(this.getStillRight().getImage(),(int) (this.getXCoord()), (int) (this.getYCoord()), null);
	    
	}	
    }
    
    public void move(Player p){
        //adjust velocities
        if(Board.getState() == Board.STATE.GAME){
	    	for(int i = 0; i < getTerrains().size(); i++){
	            getTerrains().get(i).CheckProjectileContact(this, i, p);
	            
	        }
	
	        
	        setXVel(getXVel() + getXAcc());
	        setYVel(getYVel() + getYAcc());
	        
	        //adjust coords
	        setXCoord(getXCoord() + getXVel());
	        setYCoord(getYCoord() + getYVel());
	        
	        //check world boundaries
	        if((this.getYCoord() + this.getVerticalSize()) >= getWorldBot()){
	            setRemove(true);
	        }
	        if(getXCoord() <= getWorldLeft()){
	            setRemove(true);
	        }
	        if((this.getXCoord() + this.getHorizontalSize()) >= getWorldRight()){
	            setRemove(true);
	        }
	        if(getYCoord() <= getWorldTop()){
	            setRemove(true);
	        }
	        
	        
	        //if player contact, deal dmg
	        if(PlayerContact(p) == true){
	            dealDmg(p);
	        }
        }
    }
    public void dealDmg(Player p){
        time++;
        if(time >= 10){
        p.setHp(p.getHp() - this.getAttack());
        time = 0;
        }
    }
}
