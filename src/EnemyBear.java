import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.Serializable;
import java.util.Random;
import static java.lang.Math.abs;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/** 
 * EnemyBear represents the bear enemy the player must defeat.
 * 
 * @author Shadee Barzin
 * @author Andrew Ferguson
 * @author Michele Haque
 * @author Brendan Murphy
 * @author Fengyu Wang
 * @version CS 48, Winter 2015
*/

public class EnemyBear extends Enemies implements Serializable{

	private boolean attackdone = false;

	//Constructor	
	public EnemyBear(double x, double y){
		super(x, y);
		ImageIcon i = new ImageIcon("../images/enemyImages/bear/bearLeft.png");
		ResourceCoin coin = new ResourceCoin(this.getXCoord(), this.getYCoord());
		ResourceHeart heart = new ResourceHeart(this.getXCoord(),this.getYCoord());
		this.setStill(i);
		ImageIcon iRight = new ImageIcon("../images/enemyImages/bear/bearRight.png");
		this.setStillRight(iRight);
		this.setHorizontalSize(60);
		this.setVerticalSize(115);
		
		Random randomGenerator = new Random();
		int randomInt = randomGenerator.nextInt(6);
		if(randomInt>2)
		    this.setResource(coin);
		else
		    this.setResource(heart);
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
						BearProjectile claw = new BearProjectile(this.getXCoord()-34
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
		
		//If Enemy can't move, jump
		if(checkMove()){
			this.setYVel(this.getJumpSpeed());
			setStartedJump(true);
			setYAcc(.5);
			setInAir(true);
		}    
		//If enemy is stuck behind another enemy, jump
		else if(checkSpeed()){
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

	}

	@Override
	public void print() {
		System.out.println("Bear");
	}

}
