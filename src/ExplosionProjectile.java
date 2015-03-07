
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.Serializable;

import javax.swing.ImageIcon;


public class ExplosionProjectile extends Projectile implements Serializable{
    //Constructor
    private int time = 0;
    public ExplosionProjectile(double x, double y, int direction, Graphics g, double angle, Player p) {
        super(x - 25, y + 25, direction, g, angle, p);
        setXAcc(0);
        setYAcc(0);
        setSpeed(0);
        setYVel(0);
        CreateImage(g);
        setHorizontalSize(50);
        setVerticalSize(50);
        setAttack(30);
        ImageIcon iLeft = new ImageIcon("../images/attackImage/bearAttackLeft.png");
	ImageIcon iRight = new ImageIcon ("../images/attackImage/bearAttackRight.png");
        setStillLeft(iLeft.getImage());
	setStillRight(iRight.getImage());
        
    }
    //paints projectile
    public void CreateImage(Graphics g){
	Graphics2D g2d = (Graphics2D) g;
	if(getFacing() == 0){
        Rectangle rect = new Rectangle((int) getXCoord(), 
                            (int) getYCoord(), getHorizontalSize(), getVerticalSize());
			    g2d.setColor(Color.red);
			    g2d.fill(rect);
	    //g2d.drawImage(this.getStillLeft(),(int) (this.getXCoord()), (int) (this.getYCoord()), null);
	}
	else if(getFacing() == 1){
	    //g2d.drawImage(this.getStillRight(),(int) (this.getXCoord()), (int) (this.getYCoord()), null);
        Rectangle rect = new Rectangle((int) getXCoord(), 
                            (int) getYCoord(), getHorizontalSize(), getVerticalSize());
			    g2d.setColor(Color.red);
			    g2d.fill(rect);
	}	
    }
    
    public void move(Player p){
        //adjust velocities
        
        if(Board.getState() == Board.STATE.GAME){
                time++;
	       	        
	        //if player contact, deal dmg
	        if(PlayerContact(p) == true){
	            dealDmg(p);
	        }
                if(time == 3){
                    setRemove(true);
                }
        }
    }
    public void dealDmg(Player p){

        p.setHp(p.getHp() - this.getAttack());
        setRemove(true);
    }
}