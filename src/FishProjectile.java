
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.swing.ImageIcon;


public class FishProjectile extends Projectile{
    //Constructor
    public FishProjectile(double x, double y, int direction, Graphics g, double angle, Player p) {
        super(x, y, direction, g, angle, p);
        ImageIcon iLeft = new ImageIcon("../images/sourceImage/water.png");
	ImageIcon iRight = new ImageIcon ("../images/sourceImage/water.png");
        setStillLeft(iLeft.getImage());
	setStillRight(iRight.getImage());
        setXAcc(0);
        setYAcc(.5);
        setSpeed(4);
        setYVel(-20);
        setXVel(4);
        CreateImage(g);
        setHorizontalSize(30);
        setVerticalSize(43);
        setAttack(15);
        
    }
    //paints projectile
    public void CreateImage(Graphics g){
	Graphics2D g2d = (Graphics2D) g;
	if(getFacing() == 0){
	    g2d = (Graphics2D) g;
            g2d.drawImage(this.getStillLeft(), (int) (this.getXCoord()), (int) (this.getYCoord()), null);
	}
	else if(getFacing() == 1){
	    g2d = (Graphics2D) g;
            g2d.drawImage(this.getStillRight(), (int) (this.getXCoord() + 57), (int) (this.getYCoord() + 5), null);
	    
	}	
    }


}
