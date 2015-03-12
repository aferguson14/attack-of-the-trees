import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.Serializable;

import javax.swing.ImageIcon;

/** 
 * Arrow represents the projectile shot by the player from its Bow weapon
 * 
 * @author Shadee Barzin
 * @author Andrew Ferguson
 * @author Michele Haque
 * @author Brendan Murphy
 * @author Fengyu Wang
 * @version CS 48, Winter 2015
*/

public class Arrow extends PlayerProjectile implements Serializable{
    
 public Arrow(double x, double y, int direction, Graphics g, double angle, Player player) {
        super(x, y, direction, g, angle, player);
        setXAcc(0);
        setYAcc(0);
        setSpeed(8);
        setYVel(0);
        CreateImage(g);
        setHorizontalSize(40);
        setVerticalSize(4);
        setAttack(500);
	ImageIcon iLeft = new ImageIcon("../images/attackImage/arrowLeft.png");
	ImageIcon iRight = new ImageIcon ("../images/attackImage/arrowRight.png");
	setStillLeft(iLeft.getImage());
	setStillRight(iRight.getImage());
        //modify x and y velocities based on angle fired 
        getXY(this.getSpeed(), angle);

    }
    public void CreateImage(Graphics g){
	Graphics2D g2d = (Graphics2D) g;
     
        //paint, if goint left starting point changes based on facing direction
	
		if(getFacing() == 0){
		    g2d.rotate(this.getMouseAngle(), this.getXCoord()/*+25*/, this.getYCoord()/*+63*/);
		    g2d.rotate(Math.PI, this.getXCoord()/*+25*/, this.getYCoord()/*+63*/);
		    g2d.drawImage(this.getStillLeft(),(int) (this.getXCoord())/*-52*/, (int) (this.getYCoord())+2/*+53*/, null);

		    g2d.rotate(-Math.PI, this.getXCoord()/*+25*/, this.getYCoord()/*+63*/);
		    g2d.rotate(-this.getMouseAngle(), this.getXCoord()/*+25*/, this.getYCoord()/*+63*/);

	    	}
	    	else if(getFacing() == 1){
		    g2d.rotate(this.getMouseAngle(), this.getXCoord()/*+15*/, this.getYCoord()/*+60+3*/);
		    g2d.drawImage(this.getStillRight(),(int) (this.getXCoord())-10/*+15+33*/, (int) (this.getYCoord())/*+60-7*/, null);
		    g2d.rotate(-this.getMouseAngle(), this.getXCoord()/*+15*/, this.getYCoord()-2/*+60+3*/);
	    }
	
    }	
}
