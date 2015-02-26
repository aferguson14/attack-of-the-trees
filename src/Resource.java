import java.awt.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

public class Resource{

    private double XCoord, YCoord;
    private Image resourceImage;
    private String resourceType;
    public Resource(double x, double y){
	setXCoord(x);
	setYCoord(y);
    }

    public void paintResource(Graphics g){
    }


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

    public void setResourceImage(Image resourceImage){
	this.resourceImage = resourceImage;
    }

    public Image getResourceImage(){
	return resourceImage;
    }

    public void setResourceType(String resourceType){
	this.resourceType = resourceType;
    }

    public String getResourceType(){
	return resourceType;
    }
}
