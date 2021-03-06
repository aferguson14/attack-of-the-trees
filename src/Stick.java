import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.ImageIcon;


/** 
 * Stick represents a weapon the player uses to defeat enemies.
 * 
 * @author Shadee Barzin
 * @author Andrew Ferguson
 * @author Michele Haque
 * @author Brendan Murphy
 * @author Fengyu Wang
 * @version CS 48, Winter 2015
*/

public class Stick extends Weapon implements Serializable{

    //constructor
    public Stick(double x, double y){
        setXCoord(x);
        setYCoord(y);
        setAttack(90);
        setAttackSpeed(20);
        setAttackSpeedTimer(89);
        setSpeed(3);
        setXVel(0);
        setYVel(0);
	setWeaponType("Stick");


		ImageIcon iWeapLeft = new ImageIcon("../images/weaponImage/stickLeft.png");
		setStillLeft(iWeapLeft);
		ImageIcon iWeapRight = new ImageIcon("../images/weaponImage/stickRight.png");
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
					axeproj.setAttack(20);
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
	
	MP3 stickSound = new MP3("../sound/swish.mp3");
	stickSound.play();
    

    }
}
