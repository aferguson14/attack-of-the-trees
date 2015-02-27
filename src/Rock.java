
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;


public class Rock extends Terrain{
//Construcotr
    public Rock(int x, int y) {
        super(x, y);
        setHorizontalSize(100);
        setVerticalSize(100);
        setLeftSide(x);
        setRightSide(x + getHorizontalSize());
        setTop(y);
        setBot(y + getVerticalSize());
        
        setEnemyTop(getTop());
        setEnemyLeft(getLeftSide());
        setEnemyRight(getRightSide());
        setEnemyBot(getBot());
    }
//paints rock
    @Override
    public void paintTerrain(Graphics g, Player p, ArrayList <Enemies> e) {
        Graphics2D g2d = (Graphics2D) g;
        Rectangle rect = new Rectangle((int) getXCoord(), (int) getYCoord(), (int) getHorizontalSize(), (int) getVerticalSize());
	g2d.setColor(Color.red);
	g2d.fill(rect);
    }
    public void Updatesides(Enemies e, int i){
        e.getTops().set(i, this.getTop());
    }
    public void print(){
        System.out.println("Rock");
    }

}
