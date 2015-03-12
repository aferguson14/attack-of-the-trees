
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

    /**
     * @param x int x-coordinate
     * @param y int y-coordinate
     * Constructor
     * creates and places rock onto Board
     */
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

    /**
     * @param g Graphics
     * @param p Player
     * @param e ArrayList<Enemies>
     * @param proj ArrayList<Projectile>
     * @param Playerproj ArrayList<PlayerProjectile>
     * paints rock onto the Board
     */
    @Override
    public void paintTerrain(Graphics g, Player p, ArrayList <Enemies> e, ArrayList <Projectile> proj, ArrayList <PlayerProjectile> Playerproj) {
       Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(this.getStill().getImage(), (int) this.getXCoord(), (int) this.getYCoord(), null);
    }

    /**
     * prints "Rock" to system console
     */
    public void print(){
        System.out.println("Rock");
    }

}
