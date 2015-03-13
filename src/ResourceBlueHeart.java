import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/** 
 * ResourceHeart represents the heart dropped by enemies when they are killed that the player collects.
 * 
 * @author Shadee Barzin
 * @author Andrew Ferguson
 * @author Michele Haque
 * @author Brendan Murphy
 * @author Fengyu Wang
 * @version CS 48, Winter 2015
*/

public class ResourceBlueHeart extends Resource implements Serializable{

    /**
     * @param x double for x coordinate
     * @param y double for y coordinate
     * Creates a blue heart resource that sets it coordinates, image, and type
     */
    public ResourceBlueHeart(double x, double y){
	super(x,y);
	//Image needs to be replaced by heart
	ImageIcon i = new ImageIcon("../images/sourceImage/heartBlue.png");
	this.setResourceImage(i);
	this.setResourceType("blueHeart");
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
