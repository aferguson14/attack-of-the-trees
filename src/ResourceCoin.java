import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/** 
 * ResourceCoin represents the coin dropped by enemies when they are killed that the player collects.
 * 
 * @author Shadee Barzin
 * @author Andrew Ferguson
 * @author Michele Haque
 * @author Brendan Murphy
 * @author Fengyu Wang
 * @version CS 48, Winter 2015
*/

public class ResourceCoin extends Resource implements Serializable{

    /**
     * @param x double for x coordinate
     * @param y double for y coordinate
     * Creates a coin resource that sets it coordinates, image, and type
     */
    public ResourceCoin(double x, double y){
	super(x,y);
	//Image needs to be replaced by log
	ImageIcon i = new ImageIcon("../images/sourceImage/coin.png");
	this.setResourceImage(i);
	this.setResourceType("coin");
    }

    /**
     * @param g Graphics
     * Draws the image of the resource at its x and y coordinates
     */
    public void paintResource(Graphics g){
	//paint resource
	Graphics2D g2d = (Graphics2D) g;
	g2d.drawImage(this.getResourceImage().getImage(),(int) this.getXCoord(), (int) this.getYCoord(), null);
	// implement gravity if not on ground
    }


}
