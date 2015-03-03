
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.swing.ImageIcon;


public class SunFlowerProjectile extends Projectile{
    //Constructor
    public SunFlowerProjectile(double x, double y, int direction, Graphics g, double angle) {
        super(x + 5, y, direction, g, angle);
        ImageIcon i = new ImageIcon("../images/sourceImage/fire.png");
        this.setStill(i.getImage());
        setXAcc(0);
        setYAcc(0);
        setSpeed(5);
        setYVel(0);
        CreateImage(g);
        setHorizontalSize(28);
        setVerticalSize(43);
        setAttack(10);
        
                
        getXY(this.getSpeed(), angle);
    }
    //paints projectile
    public void CreateImage(Graphics g){
	Graphics2D g2d = (Graphics2D) g;
	if(getFacing() == 0){
	    g2d = (Graphics2D) g;
            g2d.drawImage(this.getStill(), (int) (this.getXCoord()), (int) (this.getYCoord()), null);
	}
	else if(getFacing() == 1){
	    g2d = (Graphics2D) g;
            g2d.drawImage(this.getStill(), (int) (this.getXCoord()), (int) (this.getYCoord()), null);
	    
	}	
    }
    public void paintImage(Graphics g){
	Graphics2D g2d = (Graphics2D) g;
	    g2d = (Graphics2D) g;
            g2d.drawImage(this.getStill(), (int) this.getXCoord(), (int) this.getYCoord(), null);
    }
}
