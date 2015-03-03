
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import javax.swing.Timer;



public class TreeProjectile extends Projectile{
    //Constructor
    private int time = 0;
    public TreeProjectile(double x, double y, int direction, Graphics g, double angle) {
        super(x, y + 50, direction, g, angle);
        setXAcc(0);
        setYAcc(0);
        setSpeed(1.5);
        setYVel(0);
        CreateImage(g);
        setHorizontalSize(8);
        setVerticalSize(5);
        setAttack(10);
        if(direction == 0){
            setXVel(-1 * (getSpeed()));
        }
	else{
            setXVel(getSpeed());
        }        
        getXY(this.getSpeed(), angle);
    }
    @Override
     public void move(Player p){
        //adjust velocities
         
         setTerrainDimensions(p.getTerrains());
        for(int i = 0; i < getTerrains().size(); i++){
            getTerrains().get(i).CheckProjectileContact(this, i);
            
        }
        
         time++;
         if(time >= 700){
             this.setRemove(true);
         }
         
        getXY(this.getSpeed(), findAngle(p.getPlayerPoint()));
        if((p.getXCoord() > this.getXCoord()) && ((this.getXVel() < 0) || (Math.abs(this.getXVel()) < this.getSpeed()))){
             setXAcc(.5);
         }
         else if((p.getXCoord() < this.getXCoord()) && ((this.getXVel() > 0) || (Math.abs(this.getXVel()) < this.getSpeed()))){
             setXAcc(-.5);
         } else{
             setXAcc(0);
         }
        
        setXVel(getXVel() + getXAcc());
        setYVel(getYVel() + getYAcc());
        
        //adjust coords
        setXCoord(getXCoord() + getXVel());
        setYCoord(getYCoord() + getYVel());
        
        //check world boundaries
        if((this.getYCoord() + this.getVerticalSize()) >= getWorldBot()){
            setRemove(true);
        }
        if(getXCoord() <= getWorldLeft()){
            setRemove(true);
        }
        if((this.getXCoord() + this.getHorizontalSize()) >= getWorldRight()){
            setRemove(true);
        }
        if(getYCoord() <= getWorldTop()){
            setRemove(true);
        }
        
        //if player contact, deal dmg
        if(PlayerContact(p) == true){
            dealDmg(p);
        }
    }
    //paints projectile
    public void CreateImage(Graphics g){
	Graphics2D g2d = (Graphics2D) g;
	if(getFacing() == 0){
	    Rectangle rect = new Rectangle((int) getXCoord(), (int) getYCoord(), 20, 10);
	    g2d.setColor(Color.green);
	    g2d.fill(rect);
	}
	else if(getFacing() == 1){
	    Rectangle rect = new Rectangle((int) getXCoord() + 57, (int) getYCoord() + 5, 20, 10);
	    g2d.setColor(Color.green);
	    g2d.fill(rect);
	    
	}	
    }
     public void paintImage(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        Rectangle rect = new Rectangle((int) getXCoord(), (int) getYCoord(), 20, 10);
			    g2d.setColor(Color.green);
			    g2d.fill(rect);
    }
    @Override
     public void getXY(Double spd, Double ang){
        double yvel;
        yvel = -1 * (spd * (Math.sin(ang)));
        setYVel(yvel);

    }

}


