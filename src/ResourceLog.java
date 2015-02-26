import java.awt.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class ResourceLog extends Resource{

    public ResourceLog(double x, double y){
	super(x,y);
	//Image needs to be replaced by log
	ImageIcon i = new ImageIcon("../images/weaponImage/stick.png");
	this.setResourceImage(i.getImage());
    }

    public void paintResource(Graphics g){
	//paint resource
	Graphics2D g2d = (Graphics2D) g;
	g2d.drawImage(this.getResourceImage(),(int) this.getXCoord(), (int) this.getYCoord(), null);
	// implement gravity if not on ground
    }


}
