import java.awt.*;
import java.awt.event.KeyEvent;
import static java.lang.Math.abs;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class EnemyBear extends Enemies{

    //Constructor
    private boolean attackdone = false;
    public EnemyBear(double x, double y){
        super(x, y);
        ImageIcon i = new ImageIcon("../images/enemyImages/bear/bearSide.png");
	ResourceCoin coin = new ResourceCoin(this.getXCoord(), this.getYCoord());
        this.setStill(i.getImage());
        this.setHorizontalSize(60);
        this.setVerticalSize(115);
        
	this.setResource(coin);
        this.setHp(150);
        this.setTotalHp(getHp());
        this.setAttack(10);
        this.setSpeed(2.5);
        this.setAttackSpeed(50);
        this.setAttackRange(20);
        this.setJumpSpeed(-20);
        this.setAttackSpeedCount(49);
    }
    
    @Override
    public void Attack(Player p, Graphics g){
        if(Board.getState() == Board.STATE.GAME){
        //if attacking and in attack range
        if(this.isAttacking()){
            if(this.checkInRange(p)){
            //increment attack speed count
            //if attack speed count == the enemies attack speed
            if(!attackdone){
            //create a projectile towards the player
                BearProjectile claw = new BearProjectile(this.getXCoord()
                        , this.getYCoord(), this.getFacing(), g, 
                        findAngle(p.getPlayerPoint()), p);
                this.addProjectile(claw);
            //reset attack speed count
                attackdone = true;
            }
            }
        }
        }
    }
   
    @Override
    public void AI(Player p, Graphics g, ArrayList<Terrain>terrain, ArrayList<Enemies> enem){
        //If it can't move, Jump
       //  System.out.println("before: " + this.getYVel());
        if(isInAir()){
            // System.out.println("1");
            if(getYVel() == 0){
                setStartedJump(false);
                //setInAir(true);
            }
        }
        if(isAttacking() == false){
            this.getProjectiles().clear();
            attackdone = false;
        }
//        if(isInAir() && checkInAirMove()){
//            setStartedJump(false);
//            setYCoord(getYCoord() + 2);
//            this.setYVel(Math.abs(getYVel()));
//            setYAcc(.5);
//            
//            
//        }
         if(checkMove()){
            //  System.out.println("2");
                    this.setYVel(this.getJumpSpeed());
                    setStartedJump(true);
                    setYAcc(.5);
                    setInAir(true);
        }    
         else if(checkSpeed()){
            //  System.out.println("3");
                    this.setYVel(this.getJumpSpeed());
                    this.setXVel(this.getSpeed());
                    setStartedJump(true);
                    setYAcc(.5);
                    setInAir(true);
         }
         //if not in air and is in range, attack
        if(this.checkInRange(p) && !isInAir()){
           //  System.out.println("4");
                this.setAttacking(true);
                this.Attack(p, g);
                this.attackAnimation(g);
                
            }
        //else, move toward player
            else{
           //  System.out.println("5");
               this.setAttacking(false);
                if((p.getXCoord() + p.getHorizontalSize()) < this.getXCoord()){
                    this.setXVel(-1 * (this.getSpeed()));
                    this.move(terrain, enem);
                }else if((p.getXCoord()) > (this.getXCoord() + this.getHorizontalSize())){
                    this.setXVel(this.getSpeed());
                    this.move(terrain, enem);
                }   
                else{
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
           // System.out.println("after: " + this.getYVel());
        
    }
    @Override
    public void die(){
    //not sure if needed, keep just in case
    }

    
    public void attackAnimation(Graphics g){
        
        
    }

    @Override
    public void print() {
        System.out.println("Bear");
    }

    
}

