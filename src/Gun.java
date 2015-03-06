import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import javax.swing.ImageIcon;


public class Gun extends Weapon{
    //constructor
    public Gun(double x, double y){
        setXCoord(x);
        setYCoord(y);
        setAttack(40);
        setAttackSpeed(100);
        setAttackSpeedTimer(99);
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
		    bullet.setMouseY(this.getMouseY());
		    bullet.setMouseX(this.getMouseX());
		    bullet.setMouseAngle(this.getMouseAngle());
	   	    bullet.setPlayerDirection(this.getPlayerDirection());
		    this.addProjectile(bullet);
		    this.setAttackSpeedTimer(0);
		    //System.out.println(findAngle(p)*180/Math.PI);
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
    
}
