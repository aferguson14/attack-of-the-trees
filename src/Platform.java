import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/** 
 * Platform represents the platform on the board the player and enemies must traverse.
 * 
 * @author Shadee Barzin
 * @author Andrew Ferguson
 * @author Michele Haque
 * @author Brendan Murphy
 * @author Fengyu Wang
 * @version CS 48, Winter 2015
*/

public class Platform extends Terrain{

    /**
     * @param x int x-coordinate
     * @param y in y-coordinate
     * Constructor that creates and sets the platform
     */
    public Platform(int x, int y) {
        super(x, y);
        ImageIcon i = new ImageIcon("../images/blockImage/step.png");
        setStill(i);
        setHorizontalSize(100);
        setVerticalSize(22);
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
     * Paints platform onto the Board.
     */
    @Override
    public void paintTerrain(Graphics g, Player p, ArrayList <Enemies> e, ArrayList <Projectile> proj, ArrayList <PlayerProjectile> Playerproj) {
       Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(this.getStill().getImage(), (int) this.getXCoord(), (int) this.getYCoord(), null);
    }

    /**
     * Prints "Platform" to system
     */
    public void print(){
        System.out.println("Platform");
    }

}
