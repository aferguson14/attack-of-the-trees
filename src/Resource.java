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

    private double XCoord, YCoord; //x and y coordinates for the resource
    private ImageIcon resourceImage; //the image of the resource
    private String resourceType; //string naming the type of resource
    
    /**
     * @param x double for x-coordinate
     * @param y double for y-coordinate
     * Constructor for the resource that sets its position
     */
    public Resource(double x, double y){
	setXCoord(x);
	setYCoord(y);
    }

    /**
     * Method to paint the resources should be overridden by subclasses 
     */
    public void paintResource(Graphics g){}


    //SETTERS AND GETTERS
    
    /**
     * @param XCoord double x coordinate to set
     */
    public void setXCoord(double XCoord){
	this.XCoord = XCoord;
    }

    /**
     * @return the XCoord 
     */
    public double getXCoord(){
	return XCoord;
    }

    /**
     * @param YCoord double y coordinate to set
     */	
    public void setYCoord(double YCoord){
	this.YCoord = YCoord;
    }

    /**
     * @return the YCoord 
     */
    public double getYCoord(){
	return YCoord;
    }

    /**
     * @param resourceImage the image of the resource to set
     */
    public void setResourceImage(ImageIcon resourceImage){
	this.resourceImage = resourceImage;
    }

    /**
     * @return the resourceImage 
     */
    public ImageIcon getResourceImage(){
	return resourceImage;
    }

    /**
     * @param resourceType the type of resource to set
     */
    public void setResourceType(String resourceType){
	this.resourceType = resourceType;
    }

    /**
     * @return the resourceType 
     */
    public String getResourceType(){
	return resourceType;
    }
}
