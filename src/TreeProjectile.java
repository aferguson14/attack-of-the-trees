
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.io.Serializable;

import javax.swing.ImageIcon;
import javax.swing.Timer;



public class TreeProjectile extends Projectile implements Serializable{
    //Constructor
    private int time = 0;
    public TreeProjectile(double x, double y, int direction, Graphics g, double angle, Player p) {
        super(x, y, direction, g, angle, p);
        setXAcc(0);
        setYAcc(0);
        setSpeed(2.5);
        setYVel(0);
        CreateImage(g);
        setHorizontalSize(8);
        setVerticalSize(5);
        setAttack(15);
        ImageIcon iLeft = new ImageIcon("../images/attackImage/treeAttackLeft.png");
	ImageIcon iRight = new ImageIcon ("../images/attackImage/treeAttackRight.png");
	setStillLeft(iLeft.getImage());
	setStillRight(iRight.getImage());
        if(direction == 0){
            setXVel(-1 * (getSpeed()));
        }
	else{
            setXVel(getSpeed());
        }        
        getXY(this.getSpeed(), angle);
    }
    @Override
     public void move(Player p){
        //adjust velocities
        if(Board.getState() == Board.STATE.GAME){
        	setTerrainDimensions(p.getTerrains());
        	for(int i = 0; i < getTerrains().size(); i++){
        	    getTerrains().get(i).CheckProjectileContact(this, i, p);
            
        	}
        
        	time++;
        	if(time >= 700){
        	     this.setRemove(true);
        	}
         
        	getXY(this.getSpeed(), findAngle(p.getPlayerPoint()));
        	if((p.getXCoord() > this.getXCoord()) && ((this.getXVel() < 0) || (Math.abs(this.getXVel()) < this.getSpeed()))){
        	     setXAcc(.5);
         	}
         	else if((p.getXCoord() < this.getXCoord()) && ((this.getXVel() > 0) || (Math.abs(this.getXVel()) < this.getSpeed()))){
           	     setXAcc(-.5);
         	} else{
        	    setXAcc(0);
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
    //paints projectile
    public void CreateImage(Graphics g){
	Graphics2D g2d = (Graphics2D) g;
	if(getFacing() == 0){
	    g2d = (Graphics2D) g;
            g2d.drawImage(this.getStillLeft(), (int) (this.getXCoord()), (int) (this.getYCoord() + 5), null);
	}
	else if(getFacing() == 1){
	    g2d = (Graphics2D) g;
            g2d.drawImage(this.getStillRight(), (int) (this.getXCoord() + 57), (int) (this.getYCoord() + 5), null);
	    
	}	
    }

    @Override
     public void getXY(Double spd, Double ang){
        double yvel;
        yvel = -1 * (spd * (Math.sin(ang)));
        setYVel(yvel);

    }

}
