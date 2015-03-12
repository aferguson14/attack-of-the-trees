
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/** 
 * RampDown represents the angled ramp terrain on the board the player and enemies must traverse.
 * 
 * @author Shadee Barzin
 * @author Andrew Ferguson
 * @author Michele Haque
 * @author Brendan Murphy
 * @author Fengyu Wang
 * @version CS 48, Winter 2015
*/

public class RampDown extends Terrain{
//constructor
    public RampDown(int x, int y) {
        super(x, y);
        ImageIcon i = new ImageIcon("../images/blockImage/rampDown.png");
        setStill(i);
        setIgnoreRight(true);
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
    
    //paints a triangle
    @Override
    public void paintTerrain(Graphics g, Player p, ArrayList <Enemies> e, ArrayList <Projectile> proj, ArrayList <PlayerProjectile> Playerproj) {
        Updatesides(p);
        for(Enemies enem : e){
            for(int i = 0; i < enem.getTops().size(); i++){
                Updatesides(enem, i);
                for(Projectile Proj : proj){
                    for(int x = 0; x < Proj.getTops().size(); x++){
                        Updatesides(Proj, x);
                    }
                }
            }
        }
        for(PlayerProjectile PlayProj : Playerproj){
            for(int i = 0; i < PlayProj.getTops().size(); i++){
                Updatesides(PlayProj, i);
            }
        }
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(this.getStill().getImage(), (int) this.getXCoord(), (int) this.getYCoord(), null);
        
    }
//becuase of triangle's slope, changes the top based on the player's position 
//in correspondence with the triangle's slope
public void Updatesides(Player p){
    if(((p.getXCoord() + p.getHorizontalSize()/2) > this.getXCoord()) && 
       (p.getXCoord() < this.getXCoord() + 
                this.getHorizontalSize() - (p.getHorizontalSize()/2))){
        setTop(this.getYCoord() - (this.getXCoord() - 
                    p.getXCoord() - (p.getHorizontalSize()/2)));
    }
}
//same as previous method, but for enemies
public void Updatesides(Enemies e, int index){
    if(((e.getXCoord() + e.getHorizontalSize()/2) > this.getXCoord()) && 
            (e.getXCoord() < this.getXCoord() + 
                this.getHorizontalSize() - (e.getHorizontalSize()/2))){
        e.getTops().set(index, this.getYCoord() - (this.getXCoord() - 
                    e.getXCoord() - (e.getHorizontalSize()/2)));
    }
}
public void Updatesides(Projectile e, int index){
    if(((e.getXCoord() + e.getHorizontalSize()/2) > this.getXCoord()) && 
            (e.getXCoord() < this.getXCoord() + 
                this.getHorizontalSize() - (e.getHorizontalSize()/2))){
        e.getTops().set(index, this.getYCoord() - (this.getXCoord() - 
                    e.getXCoord() - (e.getHorizontalSize()/2)));
    }
}
public void Updatesides(PlayerProjectile e, int index){
   
    if(((e.getXCoord() + (e.getHorizontalSize()/2)) > this.getXCoord()) && 
            (e.getXCoord() < (this.getXCoord() + 
                this.getHorizontalSize()) + (e.getHorizontalSize()/2))){
         
    
        e.setTopIndex(index, this.getYCoord() - (this.getXCoord() - 
                    e.getXCoord() - (e.getHorizontalSize()/2)));
//        System.out.println(" Update: ");
//        System.out.println("proj: " + (e.getYCoord() + e.getVerticalSize()));
//    System.out.println("terr: " + (e.getTops().get(5)));
//    System.out.println("-----------------------------------------------------------");
    }
}
    

public void print(){
        System.out.println("Ramp");
    }
    
}
