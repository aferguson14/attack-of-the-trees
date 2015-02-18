
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import javafx.scene.shape.Line;


public class Ramp extends Terrain{

    public Ramp(int x, int y) {
        super(x, y);
        setHorizontalSize(100);
        setVerticalSize(0);
        setLeftSide(x);
        setRightSide(x + getHorizontalSize());
        setTop(y);
        setBot(y + getVerticalSize());
        
        setEnemyTop(getTop());
        setEnemyLeft(getLeftSide());
        setEnemyRight(getRightSide());
        setEnemyBot(getBot());
    }
    @Override
    public void paintTerrain(Graphics g, Player p, ArrayList <Enemies> e) {
        Updatesides(p);
        for(Enemies enem : e){
            Updatesides(enem);
        }
        int[] xpoints = new int[3];
        xpoints[0] = (int) getXCoord();
        xpoints[1] = (int)getRightSide();
        xpoints[2] = (int)getRightSide();
        int[] ypoints = new int[3];
        ypoints[0] = (int) getYCoord();
        ypoints[1] = (int) getYCoord();
        ypoints[2] = (int) getYCoord() - (int)getHorizontalSize();
        Graphics2D g2d = (Graphics2D) g;
        Polygon tri = new Polygon(xpoints, ypoints, 3);
	g2d.setColor(Color.red);
	g2d.fill(tri);
        //System.out.println(getTop()+"");
    }
    public void Updatesides(Player p){

        if(((p.getXCoord() + p.getHorizontalSize()/2) >= this.getXCoord()) && (p.getXCoord() <= this.getXCoord() + this.getHorizontalSize() - (p.getHorizontalSize()/2))){
            setTop(this.getYCoord() - (p.getXCoord() - this.getXCoord() + (p.getHorizontalSize()/2)));
        }
    }
    public void Updatesides(Enemies e){

        if(((e.getXCoord() + e.getHorizontalSize()/2) >= this.getXCoord()) && (e.getXCoord() <= this.getXCoord() + this.getHorizontalSize() - (e.getHorizontalSize()/2))){
            setEnemyTop(this.getYCoord() - (e.getXCoord() - this.getXCoord() + (e.getHorizontalSize()/2)));
        }
    }
    
}
