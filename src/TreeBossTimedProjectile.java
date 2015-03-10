
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.Serializable;

import javax.swing.ImageIcon;


public class TreeBossTimedProjectile extends Projectile implements Serializable{
    //Constructor
    private int timer = 0;
    public TreeBossTimedProjectile(double x, double y, int direction, Graphics g, double angle, Player p) {
        super(x + 5, y, direction, g, angle, p);
        ImageIcon iLeft = new ImageIcon("../images/attackImage/treeAttackLeft.png");
	ImageIcon iRight = new ImageIcon ("../images/attackImage/treeAttackRight.png");
        setStillLeft(iLeft);
	setStillRight(iRight);
        setXAcc(0);
        setYAcc(0);
        setSpeed(5);
        setYVel(0);
        CreateImage(g);
        setHorizontalSize(25);
        setVerticalSize(25);
        setAttack(20);
        
                
        getXY(this.getSpeed(), angle);
    }
    
    //moves projectile
    public void move(Player p){
        //adjust velocities
        if(timer == 200){
            setRemove(true);
            return;
        }
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
        timer++;
    }
    
    //paints projectile
    public void CreateImage(Graphics g){
	Graphics2D g2d = (Graphics2D) g;
	if(getFacing() == 0){
	    g2d = (Graphics2D) g;
            g2d.drawImage(this.getStillLeft().getImage(), (int) (this.getXCoord()), (int) (this.getYCoord()), null);
	}
	else if(getFacing() == 1){
	    g2d = (Graphics2D) g;
            g2d.drawImage(this.getStillRight().getImage(), (int) (this.getXCoord() + 57), (int) (this.getYCoord() + 5), null);
	    
	}	
    }

}