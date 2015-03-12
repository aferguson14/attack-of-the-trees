import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/** 
 * Resource represents the resources dropped by enemies when they are killed that the player collects.
 * The specific resources derive from this class.
 * 
 * @author Shadee Barzin
 * @author Andrew Ferguson
 * @author Michele Haque
 * @author Brendan Murphy
 * @author Fengyu Wang
 * @version CS 48, Winter 2015
*/

public class Resource implements Serializable{

    private double XCoord, YCoord;
    private ImageIcon resourceImage;
    private String resourceType;
    public Resource(double x, double y){
	setXCoord(x);
	setYCoord(y);
    }

    public void paintResource(Graphics g){}


    //SETTERS AND GETTERS

    public void setXCoord(double XCoord){
	this.XCoord = XCoord;
    }

    public double getXCoord(){
	return XCoord;
    }

    public void setYCoord(double YCoord){
	this.YCoord = YCoord;
    }

    public double getYCoord(){
	return YCoord;
    }

    public void setResourceImage(ImageIcon resourceImage){
	this.resourceImage = resourceImage;
    }

    public ImageIcon getResourceImage(){
	return resourceImage;
    }

    public void setResourceType(String resourceType){
	this.resourceType = resourceType;
    }

    public String getResourceType(){
	return resourceType;
    }
}
