import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import javax.swing.ImageIcon;


public class Bow extends Weapon{
    //constructor
    public Bow(double x, double y){
        setXCoord(x);
        setYCoord(y);
        setAttack(10);
        setAttackSpeed(100);
        setAttackSpeedTimer(99);
        setSpeed(3);
        setXVel(0);
        setYVel(0);
	setWeaponType("Bow");

	ImageIcon iWeapLeft = new ImageIcon("../images/weaponImage/bowLeft.png");
	setStillLeft(iWeapLeft.getImage());
	ImageIcon iWeapRight = new ImageIcon("../images/weaponImage/bowRight.png");
	setStillRight(iWeapRight.getImage());
 
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
		    Arrow arrow = new Arrow(this.getXCoord(), 
					       this.getYCoord(), this.getFacing(), g, findAngle(p), player);
		    arrow.setMouseY(this.getMouseY());
		    arrow.setMouseX(this.getMouseX());
		    arrow.setMouseAngle(this.getMouseAngle());
	   	    arrow.setPlayerDirection(this.getPlayerDirection());
		    this.addProjectile(arrow);
		    this.setAttackSpeedTimer(0);
                }
    }
    @Override
    public void DealDmgE(Enemies e){
        e.takeDmg(this.getAttack());
    }

    @Override
    public void print() {
        System.out.println("bow");
    }

    //paints weapon (pending)
    //paints weapon's projectiles
    @Override
    public void paintWeapon(Graphics g, Player p, ArrayList <Enemies> e) {

        paintProjectile(e, g, p);
        deleteProjectiles();
    }
    
}
