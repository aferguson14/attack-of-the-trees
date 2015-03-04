import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

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
    }
    //creates projectile towards mouse point
    public void shoot(Point p, Graphics g){
        if(p.getX() > getXCoord()){
            setFacing(1);
        } else{
            setFacing(0);
        }
        this.setAttackSpeedTimer(this.getAttackSpeedTimer() + 1);
        if(this.getAttackSpeedTimer() == this.getAttackSpeed()){
		    Bullet bullet = new Bullet(this.getXCoord(), 
                    this.getYCoord() + 45, this.getFacing(), g, findAngle(p));
		    this.addProjectile(bullet);
		    this.setAttackSpeedTimer(0);
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
