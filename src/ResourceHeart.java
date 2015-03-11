import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class ResourceHeart extends Resource implements Serializable{

    public ResourceHeart(double x, double y){
	super(x,y);
	//Image needs to be replaced by heart
	ImageIcon i = new ImageIcon("../images/sourceImage/heart.png");
	this.setResourceImage(i);
	this.setResourceType("heart");
    }

    public void paintResource(Graphics g){
	//paint resource
	Graphics2D g2d = (Graphics2D) g;
	g2d.drawImage(this.getResourceImage().getImage(),(int) this.getXCoord(), (int) this.getYCoord(), null);
	// implement gravity if not on ground
    }


}
