import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/** 
 * Gun represents a weapon the player uses to defeat enemies.
 * 
 * @author Shadee Barzin
 * @author Andrew Ferguson
 * @author Michele Haque
 * @author Brendan Murphy
 * @author Fengyu Wang
 * @version CS 48, Winter 2015
*/

public class Gun extends Weapon{
    //constructor
    public Gun(double x, double y){
        setXCoord(x);
        setYCoord(y);
        setAttack(40);
        setAttackSpeed(55);//100
        setAttackSpeedTimer(54);
        setSpeed(3);
        setXVel(0);
        setYVel(0);
	setWeaponType("Gun");
	ImageIcon iWeapLeft = new ImageIcon("../images/weaponImage/gunLeft.png");
	setStillLeft(iWeapLeft);
	ImageIcon iWeapRight = new ImageIcon("../images/weaponImage/gunRight.png");
	setStillRight(iWeapRight);
 
   }
    //creates projectile towards mouse point
    public void shoot(Point p, Graphics g, Player player){
        if(p.getX() > getXCoord()-20){ //not sure if -20 needed (it is to make cetner by the shoulder of arm)
            setFacing(1);
        } else{
            setFacing(0);
        }
        this.setAttackSpeedTimer(this.getAttackSpeedTimer() + 1);
        if(this.getAttackSpeedTimer() == this.getAttackSpeed()){
		    Bullet bullet = new Bullet(this.getXCoord(), 
					       this.getYCoord(), this.getFacing(), g, findAngle(p), player);
					       bullet.setAttack(50);
		    bullet.setMouseY(this.getMouseY());
		    bullet.setMouseX(this.getMouseX());
		    bullet.setMouseAngle(this.getMouseAngle());
	   	    bullet.setPlayerDirection(this.getPlayerDirection());
		    this.addProjectile(bullet);
		    this.setAttackSpeedTimer(0);
		    //System.out.println(findAngle(p)*180/Math.PI);
		    this.playSound();
                }
    }
    @Override
    public void DealDmgE(Enemies e){
        e.takeDmg(this.getAttack());
    }

    @Override
    public void print() {
        System.out.println("gun");
    }

    //paints weapon (pending)
    //paints weapon's projectiles
    @Override
    public void paintWeapon(Graphics g, Player p, ArrayList <Enemies> e) {

        paintProjectile(e, g, p);
        deleteProjectiles();
    }

    @Override
    public void playSound(){
	MP3 gunSound = new MP3("../sound/gunSound.mp3");
	gunSound.play();
    }

    
}
