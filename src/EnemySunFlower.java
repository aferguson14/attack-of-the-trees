import java.awt.*;
import java.awt.event.KeyEvent;
import static java.lang.Math.abs;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class EnemySunFlower extends Enemies{

    //Constructor
    public EnemySunFlower(double x, double y){
        super(x, y);
        ImageIcon i = new ImageIcon("../images/enemyImages/sunflower/sunflowerFront.png");
	ResourceCoin coin = new ResourceCoin(this.getXCoord(), this.getYCoord());

        this.setStill(i.getImage());
        this.setHorizontalSize(90);
        this.setVerticalSize(115);
        
	this.setResource(coin);
        this.setHp(50);
        this.setTotalHp(getHp());
        this.setAttack(30);
        this.setSpeed(2.5);
        this.setAttackSpeed(150);
        this.setAttackRange(600);
        this.setJumpSpeed(-20);
        this.setAttackSpeedCount(149);
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
               SunFlowerProjectile fire = new SunFlowerProjectile(this.getXCoord()
                        , this.getYCoord(), this.getFacing(), g, 
                        findAngle(p.getPlayerPoint()), p);
                this.addProjectile(fire);
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
                    setYAcc(.5);
                    setInAir(true);
        }    
                 else if(checkSpeed()){
                    this.setYVel(this.getJumpSpeed());
                    this.setXVel(this.getSpeed());
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
    public void die(){
    //not sure if needed, keep just in case
    }

    
    public void attackAnimation(Graphics g){
        
        
    }

    @Override
    public void print() {
        System.out.println("SunFlower");
    }

    
}
