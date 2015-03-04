import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;


public class RobotProjectile extends Projectile{
    //Constructor
    public RobotProjectile(double x, double y, int direction, Graphics g, double angle) {
        super(x, y, direction, g, angle);
        setXAcc(0);
        setYAcc(0);
        setSpeed(4);
        setYVel(0);
        CreateImage(g);
        setHorizontalSize(10);
        setVerticalSize(5);
        setAttack(10);
        
                
        getXY(this.getSpeed(), angle);
    }
    //paints projectile
    public void CreateImage(Graphics g){
	Graphics2D g2d = (Graphics2D) g;
	if(getFacing() == 0){
	    Rectangle rect = new Rectangle((int) getXCoord(), (int) getYCoord(), 20, 10);
	    g2d.setColor(Color.red);
	    g2d.fill(rect);
	}
	else if(getFacing() == 1){
	    Rectangle rect = new Rectangle((int) getXCoord() + 57, (int) getYCoord() + 5, 20, 10);
	    g2d.setColor(Color.red);
	    g2d.fill(rect);
	    
	}	
    }
}
