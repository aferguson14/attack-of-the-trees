import java.awt.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/** 
 * ResourceLog represents the logs dropped by enemies when they are killed that the player collects.
 * 
 * @author Shadee Barzin
 * @author Andrew Ferguson
 * @author Michele Haque
 * @author Brendan Murphy
 * @author Fengyu Wang
 * @version CS 48, Winter 2015
*/

public class ResourceLog extends Resource{

    public ResourceLog(double x, double y){
	super(x,y);
	//Image needs to be replaced by log
	ImageIcon i = new ImageIcon("../images/sourceImage/wood.png");
	this.setResourceImage(i);
	this.setResourceType("log");
    }

    public void paintResource(Graphics g){
	//paint resource
	Graphics2D g2d = (Graphics2D) g;
	g2d.drawImage(this.getResourceImage().getImage(),(int) this.getXCoord(), (int) this.getYCoord(), null);
	// implement gravity if not on ground
    }


}
