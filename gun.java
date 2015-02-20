

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

public class gun extends Weapon{
    public gun(double x, double y){
        setXCoord(x);
        setYCoord(y);
        setAttack(10);
        setAttackSpeed(50);
        setAttackSpeedTimer(49);
        setSpeed(3);
        setXVel(0);
        setYVel(0);
    }
    public void shoot(Point p, Graphics g){
        if(p.getX() > getXCoord()){
            setFacing(1);
        } else{
            setFacing(0);
        }
        this.setAttackSpeedTimer(this.getAttackSpeedTimer() + 1);
        if(this.getAttackSpeedTimer() == this.getAttackSpeed()){
		    Bullet bullet = new Bullet(this.getXCoord(), this.getYCoord(), this.getFacing(), g, findAngle(p));
		    this.addProjectile(bullet);
		    this.setAttackSpeedTimer(0);
//                    RobotProjectile laser = new RobotProjectile(this.getXCoord(), this.getYCoord(), this.getFacing(), g, 0);
//		    this.addProjectile(laser);
//		    this.setAttackSpeedTimer(0);
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

    @Override
    public void paintWeapon(Graphics g, Player p, ArrayList <Enemies> e) {
       Graphics2D g2d = (Graphics2D) g;
	g.setColor(Color.red);
        if(p.getFacing() == 1){
            g.fillRect((int) (p.getXCoord() + p.getHorizontalSize()) , (int) p.getYCoord() +25, 10, 10);
        
        }
        else{
            g.fillRect((int) (p.getXCoord()) , (int) p.getYCoord() +25, 10, 10);
        }

        paintProjectile(e, g);
        deleteProjectiles();
    }
    
}
