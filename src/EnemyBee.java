import java.awt.*;
import java.awt.event.KeyEvent;
import static java.lang.Math.abs;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class EnemyBee extends Enemies{
private boolean allgood = false;
    //Constrcutor
    public EnemyBee(double x, double y){
        super(x, y);
        ImageIcon i = new ImageIcon("../images/enemyImages/bee/beeFront.png");
        ResourceCoin coin = new ResourceCoin(this.getXCoord(), this.getYCoord());
        this.setStill(i.getImage());
        this.setHorizontalSize(74);
        this.setVerticalSize(115);
        
        this.setResource(coin);
        this.setHp(20);
        this.setTotalHp(getHp());
        this.setAttack(30);
        this.setSpeed(6);
        this.setAttackSpeed(500);
        this.setAttackRange(5);
        this.setJumpSpeed(-20);
        this.setAttackSpeedCount(499);
        
    }
        @Override
    public void Attack(Player p, Graphics g){}
   
    @Override
    public void AI(Player p, Graphics g, ArrayList<Terrain>terrain, ArrayList<Enemies> enem){


        this.move(terrain, enem, p);
         
                
            
        //adjust facing
        if(p.getXCoord() > getXCoord()){
            setFacing(1);
        }
        else if(p.getXCoord() < getXCoord()){
            setFacing(0);
        }   
    }
    
    public void move(ArrayList <Terrain> terrain, ArrayList <Enemies> enem, Player p){
        //adjust velocities
         if(Board.getState() == Board.STATE.GAME){
             Point point = p.getPlayerPoint();
             point.setLocation(point.getX(), point.getY() - 20);
             
                getXY(this.getSpeed(), findAngle(point));
             
            if((p.getXCoord() > this.getXCoord()) && ((this.getXVel() < 0) || (Math.abs(this.getXVel()) < this.getSpeed()))){
                 setXAcc(.5);
             }
             else if((p.getXCoord() < this.getXCoord()) && ((this.getXVel() > 0) || (Math.abs(this.getXVel()) < this.getSpeed()))){
                 setXAcc(-.5);
             } else{
                 setXAcc(0);
             }
            if(checkInAirMove() && !allgood){
                this.setYVel(Math.abs(getYVel()));
                setYAcc(2);
            }
            else if(checkMove() && !allgood){
                setYAcc(-2);
               
            }
            else{
                setYAcc(0);
            }
             
                setXVel(getXVel() + getXAcc());
                setYVel(getYVel() + getYAcc());
            
            for(Enemies en : enem){
            this.CheckEnemyContact(en);
            
        }

            if(getXVel() > 0){
                setFacing(1);
            }
            else if(getXVel() < 0){
                setFacing(0);
            }

            //adjust coords
            setXCoord(getXCoord() + getXVel());
            setYCoord(getYCoord() + getYVel());

            for(int i = 0; i < getTerrains().size(); i++){
                getTerrains().get(i).CheckEnemyContact(this, i);

            }
            if((this.getYCoord() + this.getVerticalSize()) >= getWorldBot()){
                setYCoord(getWorldBot() - getVerticalSize());
            }
            if(getXCoord() <= getWorldLeft()){
                setXCoord(0);
            }
            if((this.getXCoord() + this.getHorizontalSize()) >= getWorldRight()){
                setXCoord(getWorldRight() - getHorizontalSize());
            }
            if(getYCoord() <= getWorldTop()){
                setYCoord(0);
            }

            //if player contact, deal dmg
            if(PlayerContact(p) == true){
                setHp(0);
                p.takeDmg(this);
            }
         }
         setLastCoord2(getLastCoord1());
         setLastCoord1(getXCoord());
    }
    @Override
    public void dropItem(){
    //Do once items have been implemented
    }
    @Override
    public void die(){
    //not sure if needed, keep just in case
    }
void getXY(Double spd, Double ang){
        double yvel, xvel;
        yvel = -1 * (spd * (Math.sin(ang)));
        xvel = (spd * (Math.cos(ang)));
        setXVel(xvel);
        setYVel(yvel);
        
        if(this.getFacing() == 0){
            
            setXVel(-1 * (getXVel()));
        }
	else{
            setXVel(getXVel());
        }
    }
    public void attackAnimation(Graphics g){}
    
    public boolean PlayerContact(Player p){
    if((getXCoord() >= p.getXCoord()) && 
            (getXCoord() <= (p.getXCoord() + p.getHorizontalSize())) && (
            (((getYCoord() + getVerticalSize()) >= 
                (p.getYCoord())) && 
            (getYCoord() + getVerticalSize() <= p.getYCoord() + p.getVerticalSize())) || ((getYCoord() >= p.getYCoord()) && (getYCoord() <= p.getYCoord() + p.getVerticalSize()))  )){
        return true;
    }
    else{
        return false;
    }

}
    
    public boolean checkMove(){
        if((getLastCoord1() <= getLastCoord2()) && (getLastCoord1() 
                >= getLastCoord2())){
             allgood = false;
            return true;
        }
         allgood = true;
        return false;
    }
    public boolean checkInAirMove(){
        if(checkMove() && (getLastCoord1Y() <= getLastCoord2Y() && (getLastCoord1Y() 
                >= getLastCoord2Y()))){
            allgood = false;
            return true;
        }
         allgood = true;
        return false;
    }


    @Override
    public void print() {
        System.out.println("Tree");
    }

    
}
