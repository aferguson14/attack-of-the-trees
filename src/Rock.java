
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/** 
 * Rock represents the rock terrain on the board the player and enemies must traverse.
 * 
 * @author Shadee Barzin
 * @author Andrew Ferguson
 * @author Michele Haque
 * @author Brendan Murphy
 * @author Fengyu Wang
 * @version CS 48, Winter 2015
*/

public class Rock extends Terrain implements Serializable{
//Construcotr
    public Rock(int x, int y) {
        super(x, y);
        ImageIcon i = new ImageIcon("../images/blockImage/block.png");
        setStill(i);
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
    public void paintTerrain(Graphics g, Player p, ArrayList <Enemies> e, ArrayList <Projectile> proj, ArrayList <PlayerProjectile> Playerproj) {
       Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(this.getStill().getImage(), (int) this.getXCoord(), (int) this.getYCoord(), null);
    }

    public void print(){
        System.out.println("Rock");
    }

}
