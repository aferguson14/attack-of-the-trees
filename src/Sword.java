import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/** 
 * Sword represents a weapon the player uses to defeat enemies.
 * 
 * @author Shadee Barzin
 * @author Andrew Ferguson
 * @author Michele Haque
 * @author Brendan Murphy
 * @author Fengyu Wang
 * @version CS 48, Winter 2015
*/

public class Sword extends Weapon implements Serializable{
	//constructor
	public Sword(double x, double y){
		setXCoord(x);
		setYCoord(y);
		setAttack(40);
		setAttackSpeed(50);
		setAttackSpeedTimer(49);
		setSpeed(3);
		setXVel(0);
		setYVel(0);
		setWeaponType("Sword");

		ImageIcon iWeapLeft = new ImageIcon("../images/weaponImage/swordLeft.png");
		setStillLeft(iWeapLeft);
		ImageIcon iWeapRight = new ImageIcon("../images/weaponImage/swordRight.png");
		setStillRight(iWeapRight);

	}
	//creates projectile towards mouse point
	public void shoot(Point p, Graphics g, Player player){
		if(p.getX() > getXCoord()){
			setFacing(1);
		} else{
			setFacing(0);
		}
		this.setAttackSpeedTimer(this.getAttackSpeedTimer() + 1);
		if(this.getAttackSpeedTimer() == this.getAttackSpeed()){
			AxeProjectile axeproj = new AxeProjectile(this.getXCoord(), 
					this.getYCoord(), this.getFacing(), g, findAngle(p), player); //+45
					axeproj.setAttack(30);
					axeproj.setMouseY(this.getMouseY());
					axeproj.setMouseX(this.getMouseX());
					axeproj.setMouseAngle(this.getMouseAngle());
					this.addProjectile(axeproj);
					this.setAttackSpeedTimer(0);
					this.playSound();
		}
	}
	@Override
	public void DealDmgE(Enemies e){
		e.takeDmg(this.getAttack());
	}

	@Override
	public void print() {
		System.out.println("sword");
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
	MP3 stickSound = new MP3("../sound/sword.mp3");
	stickSound.play();
    }


}
