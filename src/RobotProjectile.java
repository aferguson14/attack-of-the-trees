
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;


public class RobotProjectile extends Projectile{
    //Constructor
    public RobotProjectile(double x, double y, int direction, Graphics g, double angle, Player p) {
        super(x , y, direction, g, angle, p);
        if(direction == 0){
            setXCoord(x - 210);
        }else{
            setXCoord(x + 210);
        }
        setXAcc(0);
        setYAcc(0);
        setSpeed(7);
        setYVel(0);
        
        CreateImage(g);
        setHorizontalSize(10);
        setVerticalSize(5);
        setAttack(10);

    }
    //paints projectile
    public void CreateImage(Graphics g){
	Graphics2D g2d = (Graphics2D) g;
	if(getFacing() == 0){
            setXVel(-15);
	    g2d.setColor(Color.red);
	    g2d.fill3DRect((int) getXCoord(), (int) getYCoord() + 5, 200, 10, true);
	}
	else if(getFacing() == 1){
            setXVel(15);
	    g2d.setColor(Color.red);
	     g2d.fill3DRect((int) getXCoord(), (int) getYCoord() + 5, 200, 10, true);
	    
	}	
    }
    public void paintImage(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
	if(getFacing() == 0){
	     Rectangle rect = new Rectangle((int) getXCoord(), (int) getYCoord(), 400, 10);
	    g2d.setColor(Color.red);
	    g2d.fill(rect);
	}
	else if(getFacing() == 1){
	    Rectangle rect = new Rectangle((int) getXCoord() + 57, (int) getYCoord() + 5, 400, 10);
	    g2d.setColor(Color.red);
	    g2d.fill(rect);
	    
	}
    }
}
