import java.awt.*;
import java.awt.event.KeyEvent;
import static java.lang.Math.abs;
import javax.swing.ImageIcon;

public class EnemyTree extends Enemies{

    public EnemyTree(int x, int y){
        //Each enemy will build of the default enemy class
        //Lots of unique info for each enemy, meaning lots of repitive code :(
        super(x, y);
        ImageIcon i = new ImageIcon("images/enemyImages/tree/treeFront.png");
        this.setStill(i.getImage());
        this.setHorizontalSize(81);
        this.setVerticalSize(115);
        this.setLeftBound(this.getXCoord());
        this.setRightBound(this.getXCoord() + this.getHorizontalSize());
        this.setTopBound(this.getYCoord());
        this.setBotBound(this.getYCoord() + this.getVerticalSize());
        
        this.setHp(50);
        this.setAttack(15);
        this.setSpeed(1);
        this.setAttackSpeed(75);
        this.setAttackRange(20);
        this.setJumpSpeed(-20);
        this.setAttackSpeedCount(19);
    }
    
    @Override
    public void Attack(Player p, Graphics g){
        //if player is in range, attack him/her. 
        //Very difficult for player, but got the functionality down. Just need to tweak things.
        if(this.isAttacking()){
            if(this.checkInRange(p)){
                this.setAttackSpeedCount(this.getAttackSpeedCount() + 1);
                if(this.getAttackSpeedCount() == this.getAttackSpeed()){
                      RobotProjectile laser = new RobotProjectile(this.getXCoord(), this.getYCoord(), this.getFacing(), g);
                      this.addProjectile(laser);
                      this.setAttackSpeedCount(0);
                }
            }
        }
        
    }
    @Override
    public void AI(Player p, Graphics g){
        //move until in range, then attack. Wanna make this enemy shoot lasers. Will work on.
        if(this.checkInRange(p)){
            this.setAttacking(true);
            this.Attack(p, g);
            this.attackAnimation(g);
        }
        else{
            if((!(p.isInAir())) && (p.getYCoord() < this.getYCoord())){
                this.setYVel(this.getJumpSpeed());
            }
            if((p.getXCoord() + p.getHorizontalSize()) < this.getXCoord()){
                this.setXVel(-1 * (this.getSpeed()));
                this.move();
            }else if((p.getXCoord()) > (this.getXCoord() + this.getHorizontalSize())){
                this.setXVel(this.getSpeed());
                this.move();
            }

        }
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
    @Override
    public void paintEnemy(Player p, Graphics g){
        //moved painting of enemy to the Enemy class to make the Board class cleaner and stream lined
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(this.getStill(), this.getXCoord(), this.getYCoord(), null);


	g.setColor(Color.red);
	g.fillRect(this.getXCoord() -10 , this.getYCoord() - 10, this.getHp(), 7);        
        paintProjectile(p, g);
        deleteProjectiles();
    }
    
    public void attackAnimation(Graphics g){
        
        
    }

    
}
