
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/** 
 * AxeProjectile represents the projectile released by the player from its Axe weapon
 * 
 * @author Shadee Barzin
 * @author Andrew Ferguson
 * @author Michele Haque
 * @author Brendan Murphy
 * @author Fengyu Wang
 * @version CS 48, Winter 2015
*/

public class AxeProjectile extends PlayerProjectile implements Serializable{
    private int time = 0;
 public AxeProjectile(double x, double y, int direction, Graphics g, double angle, Player player) {
        super(x, y, direction, g, angle, player);
        setXAcc(0);
        setYAcc(0);
        setSpeed(6);
        setYVel(0);
        CreateImage(g);
        setHorizontalSize(50);
        setVerticalSize(40);
        setAttack(20);
	ImageIcon iLeft = new ImageIcon(""/*"../images/attackImage/bulletLeft.png"*/);
	ImageIcon iRight = new ImageIcon (""/*"../images/attackImage/bulletRight.png"*/);
	setStillLeft(iLeft.getImage());
	setStillRight(iRight.getImage());
        //modify x and y velocities based on angle fired 
        getXY(this.getSpeed(), angle);

    }
    public void CreateImage(Graphics g){
	Graphics2D g2d = (Graphics2D) g;
     
        //paint, if goint left starting point changes based on facing direction
	
		if(getFacing() == 0){
		    g2d.rotate(this.getMouseAngle(), this.getXCoord()+25, this.getYCoord()+63);
		    g2d.rotate(Math.PI, this.getXCoord()+25, this.getYCoord()+63);
		    g2d.drawImage(this.getStillLeft(),(int) (this.getXCoord())-52, (int) (this.getYCoord())+53, null);

		    g2d.rotate(-Math.PI, this.getXCoord()+25, this.getYCoord()+63);
		    g2d.rotate(-this.getMouseAngle(), this.getXCoord()+25, this.getYCoord()+63);

	    	}
	    	else if(getFacing() == 1){
		    g2d.rotate(this.getMouseAngle(), this.getXCoord()+15, this.getYCoord()+60+3);
		    g2d.drawImage(this.getStillRight(),(int) (this.getXCoord())+15+33, (int) (this.getYCoord())+60-7, null);
		    g2d.rotate(-this.getMouseAngle(), this.getXCoord()+15, this.getYCoord()+60+3);
	    }
	
    }
    public void move(ArrayList<Enemies> e, Player p){
            if(Board.getState() == Board.STATE.GAME){
                //adjust velocities
                for(int i = 0; i < getTerrains().size(); i++){
                    getTerrains().get(i).CheckPlayerProjectileContact(this, i, p);
                }
                
                time++;
                if(time >= 5){
                    this.setRemove(true);
                }

                setXVel(getXVel() + getXAcc());
                setYVel(getYVel() + getYAcc());

                //adjust coordinates
                setXCoord(getXCoord() + getXVel());
                setYCoord(getYCoord() + getYVel());

                //check world boundaries
                if((this.getYCoord() + this.getVerticalSize()) >= getWorldBot()){
                    setRemove(true);
                }
                if(getXCoord() <= p.getXCoord() - 600){
                    setRemove(true);
                }
                if((this.getXCoord() + this.getHorizontalSize()) >= p.getXCoord() + 800){
                    setRemove(true);
                }
                if(getYCoord() <= getWorldTop()){
                    setRemove(true);
                }
                //check enemy contact
                for(Enemies enem : e){
                    if(EnemyContact(enem) == true){
                        dealDmg(enem);
                    }
                }
            }
    }
    
}
