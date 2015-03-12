import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.Serializable;
import static java.lang.Math.abs;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/** 
 * TreeBoss represents the Tree boss enemy the player must defeat.
 * 
 * @author Shadee Barzin
 * @author Andrew Ferguson
 * @author Michele Haque
 * @author Brendan Murphy
 * @author Fengyu Wang
 * @version CS 48, Winter 2015
*/

public class TreeBoss extends Enemies implements Serializable{

    //Constrcutor
    private int AttackSpeed2 = 100;
    private int AttackSpeed3 = 250;
    public TreeBoss(double x, double y){
        super(x, y);
        ImageIcon iLeft = new ImageIcon("../images/enemyImages/treeBossSideLeft.png");
        ImageIcon iRight = new ImageIcon("../images/enemyImages/treeBossSideRight.png");
        ResourceLog log = new ResourceLog(this.getXCoord(), this.getYCoord());
        this.setStill(iLeft);
        this.setStillRight(iRight);
        this.setHorizontalSize(102);
        this.setVerticalSize(115);
        
        this.setResource(log);
        this.setHp(1000);
        this.setTotalHp(getHp());
        this.setAttack(20);
        this.setSpeed(3);
        this.setAttackSpeed(500);
        this.setAttackRange(700);
        this.setJumpSpeed(-20);
        this.setAttackSpeedCount(99);
    }
        @Override
    public void Attack(Player p, Graphics g){
        if(Board.getState() == Board.STATE.GAME){
        //if attacking and in attack range
        if(this.isAttacking()){
            if(this.checkInRange(p)){
            //increment attack speed count
            this.setAttackSpeedCount(this.getAttackSpeedCount() + 1);
            //if attack speed count == the enemies attack speed
            if(this.getAttackSpeedCount() == AttackSpeed2){
            //create a projectile towards the player
                TreeBossProjectile leaf = new TreeBossProjectile(this.getXCoord()
                        , this.getYCoord() + 30, this.getFacing(), g, 
                        findAngle(p.getPlayerPoint()), p);
                this.addProjectile(leaf);
            }
            else if(this.getAttackSpeedCount() == AttackSpeed2 + 20){
                TreeBossProjectile leaf = new TreeBossProjectile(this.getXCoord()
                        , this.getYCoord() + 30, this.getFacing(), g, 
                        findAngle(p.getPlayerPoint()), p);
                this.addProjectile(leaf);
            } else if(this.getAttackSpeedCount() == AttackSpeed2 + 40){
                TreeBossProjectile leaf = new TreeBossProjectile(this.getXCoord()
                        , this.getYCoord() + 30, this.getFacing(), g, 
                        findAngle(p.getPlayerPoint()), p);
                this.addProjectile(leaf);
            }
            else if(this.getAttackSpeedCount() == AttackSpeed3){
                
                GrenadeProjectile grenade = new GrenadeProjectile(this.getXCoord()
                        , this.getYCoord() + 30, this.getFacing(), g, 
                        findAngle(p.getPlayerPoint()), p);
                grenade.setXVel(-1 * grenade.getSpeed());
                grenade.setYVel(-15);
                GrenadeProjectile grenade1 = new GrenadeProjectile(this.getXCoord()
                        , this.getYCoord() + 30, this.getFacing(), g, 
                        findAngle(p.getPlayerPoint()), p);
                grenade1.setXVel(-1 * grenade1.getSpeed() - 5);
                grenade1.setYVel(-15);
                GrenadeProjectile grenade2 = new GrenadeProjectile(this.getXCoord()
                        , this.getYCoord() + 30, this.getFacing(), g, 
                        findAngle(p.getPlayerPoint()), p);
                grenade2.setXVel(grenade2.getSpeed() + 5);
                grenade2.setYVel(-15);
                GrenadeProjectile grenade3 = new GrenadeProjectile(this.getXCoord()
                        , this.getYCoord() + 30, this.getFacing(), g, 
                        findAngle(p.getPlayerPoint()), p);
                grenade3.setXVel(grenade3.getSpeed());
                grenade3.setYVel(-15);
                this.addProjectile(grenade);
                this.addProjectile(grenade1);
                this.addProjectile(grenade2);
                this.addProjectile(grenade3);
            }
            else if(getAttackSpeedCount() == getAttackSpeed()){
                TreeBossTimedProjectile timed = new TreeBossTimedProjectile(this.getXCoord()
                        , this.getYCoord() + 30, this.getFacing(), g, 
                        findAngle(p.getPlayerPoint()), p);
                timed.setXVel(timed.getSpeed());
                timed.setYVel(0);
                TreeBossTimedProjectile timed1 = new TreeBossTimedProjectile(this.getXCoord()
                        , this.getYCoord() + 30, this.getFacing(), g, 
                        findAngle(p.getPlayerPoint()), p);
                timed1.setXVel(-1 * timed1.getSpeed());
                timed1.setYVel(0);
                TreeBossTimedProjectile timed2 = new TreeBossTimedProjectile(this.getXCoord()
                        , this.getYCoord() + 30, this.getFacing(), g, 
                        findAngle(p.getPlayerPoint()), p);
                timed2.setXVel(0);
                timed2.setYVel(-1 * timed2.getSpeed());
                TreeBossTimedProjectile timed3 = new TreeBossTimedProjectile(this.getXCoord()
                        , this.getYCoord() + 30, this.getFacing(), g, 
                        findAngle(p.getPlayerPoint()), p);
                timed3.setXVel(Math.cos(45) * timed3.getSpeed());
                timed3.setYVel( -1 * Math.sin(45) * timed3.getSpeed());
                TreeBossTimedProjectile timed4 = new TreeBossTimedProjectile(this.getXCoord()
                        , this.getYCoord() + 30, this.getFacing(), g, 
                        findAngle(p.getPlayerPoint()), p);
                timed4.setXVel(-1 * Math.cos(45) * timed4.getSpeed());
                timed4.setYVel( -1 * Math.sin(45) * timed4.getSpeed());
                TreeBossTimedProjectile timed5 = new TreeBossTimedProjectile(this.getXCoord()
                        , this.getYCoord() + 30, this.getFacing(), g, 
                        findAngle(p.getPlayerPoint()), p);
                timed5.setXVel(-1 * Math.cos(30) * timed5.getSpeed());
                timed5.setYVel(-1 * Math.sin(30) * timed5.getSpeed());
                TreeBossTimedProjectile timed6 = new TreeBossTimedProjectile(this.getXCoord()
                        , this.getYCoord() + 30, this.getFacing(), g, 
                        findAngle(p.getPlayerPoint()), p);
                timed6.setXVel(-1 * Math.cos(45) * timed6.getSpeed());
                timed6.setYVel( -1 * Math.sin(45) * timed6.getSpeed());
                TreeBossTimedProjectile timed7 = new TreeBossTimedProjectile(this.getXCoord()
                        , this.getYCoord() + 30, this.getFacing(), g, 
                        findAngle(p.getPlayerPoint()), p);
                timed7.setXVel(-1 * Math.cos(60) * timed7.getSpeed());
                timed7.setYVel(-1 * Math.sin(60) * timed7.getSpeed());
                TreeBossTimedProjectile timed8 = new TreeBossTimedProjectile(this.getXCoord()
                        , this.getYCoord() + 30, this.getFacing(), g, 
                        findAngle(p.getPlayerPoint()), p);
                timed8.setXVel(Math.cos(60) * timed8.getSpeed());
                timed8.setYVel(-1 * Math.sin(60) * timed8.getSpeed());
                TreeBossTimedProjectile timed9 = new TreeBossTimedProjectile(this.getXCoord()
                        , this.getYCoord() + 30, this.getFacing(), g, 
                        findAngle(p.getPlayerPoint()), p);
                timed5.setXVel( Math.cos(30) * timed5.getSpeed());
                timed5.setYVel(-1 * Math.sin(30) * timed5.getSpeed());
                
                this.addProjectile(timed);
                this.addProjectile(timed1);
                this.addProjectile(timed2);
                this.addProjectile(timed3);
                this.addProjectile(timed4);
                this.addProjectile(timed5);
                this.addProjectile(timed6);
                this.addProjectile(timed7);
                this.addProjectile(timed8);
                this.addProjectile(timed9);
            }
                
                
            //reset attack speed count
            if(getAttackSpeedCount() == getAttackSpeed()){
                this.setAttackSpeedCount(0);
            }
            }
            }
        }
        
    }
   
    @Override
    public void AI(Player p, Graphics g, ArrayList<Terrain>terrain, ArrayList<Enemies> enem){
        //If it can't move, Jump
        if(isInAir()){
            if(getYVel() == 0){
                setStartedJump(false);
            }
        }
        if(isInAir() && checkInAirMove()){
            this.setYVel(Math.abs(getYVel()));
            setYAcc(.5);
        }
        else if(checkMove()){
                    this.setYVel(this.getJumpSpeed());
                    setStartedJump(true);
                    setYAcc(.5);
                    setInAir(true);
        }    
         else if(checkSpeed()){
                    this.setYVel(this.getJumpSpeed());
                    this.setXVel(this.getSpeed());
                    setStartedJump(true);
                    setYAcc(.5);
                    setInAir(true);
         }
         //if not in air and is in range, attack
        if(this.checkInRange(p) && !isInAir()){
                this.setAttacking(true);
                this.Attack(p, g);
                this.attackAnimation(g);
                
            }
        //else, move toward player
            else{
               this.setAttacking(false);
                if((p.getXCoord() + p.getHorizontalSize()) < this.getXCoord()){
                    this.setXVel(-1 * (this.getSpeed()));
                    this.move(terrain, enem);
                }else if((p.getXCoord()) > (this.getXCoord() + this.getHorizontalSize())){
                    this.setXVel(this.getSpeed());
                    this.move(terrain, enem);
                }   
            }
        //adjust facing
            if(p.getXCoord() > getXCoord()){
                setFacing(1);
            }
            else if(p.getXCoord() < getXCoord()){
                setFacing(0);
            }   
    }
    @Override
    public void dropItem(){
    //Do once items have been implemented
    }
    @Override
    public void die(){
    //not sure if needed, keep just in case
    }

    public void attackAnimation(Graphics g){
        
        
    }

    @Override
    public void print() {
        System.out.println("Tree");
    }

    
}
