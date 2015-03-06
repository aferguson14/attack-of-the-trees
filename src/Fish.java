import java.awt.*;
import java.awt.event.KeyEvent;
import static java.lang.Math.abs;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class Fish extends Enemies{

    //Constrcutor
    public Fish(double x, double y){
        super(x, y);
        ImageIcon iLeft = new ImageIcon("../images/enemyImages/fish/fishLeft.png");
        ImageIcon iRight = new ImageIcon("../images/enemyImages/fish/fishRight.png");
        ResourceLog log = new ResourceLog(this.getXCoord(), this.getYCoord());
        this.setStill(iLeft.getImage());
        this.setStillRight(iRight.getImage());
        this.setHorizontalSize(120);
        this.setVerticalSize(115);
        
        this.setResource(log);
        this.setHp(600);
        this.setTotalHp(getHp());
        this.setAttack(15);
        this.setSpeed(4);
        this.setAttackSpeed(300);
        this.setAttackRange(700);
        this.setJumpSpeed(-20);
        this.setAttackSpeedCount(299);
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
            if(this.getAttackSpeedCount() == this.getAttackSpeed()){
            //create a projectile towards the player
                FishFollowingProjectile sting1 = new FishFollowingProjectile(this.getXCoord()
                        , this.getYCoord() + 30, this.getFacing(), g, 
                        findAngle(p.getPlayerPoint()), p);
                FishFollowingProjectile sting2 = new FishFollowingProjectile(this.getXCoord()
                        , this.getYCoord() + 30, this.getFacing(), g, 
                        findAngle(p.getPlayerPoint()), p);
                sting2.setSpeed(2);
                FishFollowingProjectile sting3 = new FishFollowingProjectile(this.getXCoord()
                        , this.getYCoord() + 30, this.getFacing(), g, 
                        findAngle(p.getPlayerPoint()), p);
                sting3.setSpeed(1.5);
                
                
                FishProjectile water1 = new FishProjectile(this.getXCoord() + 30, this.getYCoord() + 30, 0, g, findAngle(p.getPlayerPoint()), p);
                water1.setXVel(-1 * water1.getSpeed());
                water1.setYVel(-15);
                FishProjectile water2 = new FishProjectile(this.getXCoord() + 30, this.getYCoord(), 1, g, findAngle(p.getPlayerPoint()), p);
                water2.setXVel(water2.getSpeed());
                water2.setYVel(-15);
                FishProjectile water3 = new FishProjectile(this.getXCoord()-30, this.getYCoord() + 30, 0, g, findAngle(p.getPlayerPoint()), p);
                water3.setXVel(-1 * water3.getSpeed());
                water3.setYVel(-15);
                FishProjectile water4 = new FishProjectile(this.getXCoord() - 30, this.getYCoord(), 1, g, findAngle(p.getPlayerPoint()), p);
                water4.setXVel(water4.getSpeed());
                water4.setYVel(-15);
                
                this.addProjectile(sting1);
                this.addProjectile(sting2);
                this.addProjectile(sting3);
                this.addProjectile(water1);
                this.addProjectile(water2);
                this.addProjectile(water3);
                this.addProjectile(water4);
                
            //reset attack speed count
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
