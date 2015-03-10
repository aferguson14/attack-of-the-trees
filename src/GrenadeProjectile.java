//placehlder images
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.swing.ImageIcon;


public class GrenadeProjectile extends Projectile{
    //Constructor
    private int timer = 0;
    public GrenadeProjectile(double x, double y, int direction, Graphics g, double angle, Player p) {
        super(x, y, direction, g, angle, p);
        ImageIcon iLeft = new ImageIcon("../images/sourceImage/water.png");
	ImageIcon iRight = new ImageIcon ("../images/sourceImage/water.png");
        setStillLeft(iLeft);
	setStillRight(iRight);
        setXAcc(0);
        setYAcc(.5);
        setSpeed(4);
        setYVel(-20);
        setXVel(4);
        CreateImage(g);
        setHorizontalSize(30);
        setVerticalSize(43);
        setAttack(15);
        
    }
    public void move(Player p){
        //adjust velocities
        
    	if(Board.getState() == Board.STATE.GAME){
	        for(int i = 0; i < getTerrains().size(); i++){
	            getTerrains().get(i).CheckGrenadeProjectileContact(this, i, p);
	            
	        }
	        setXVel(getXVel() + getXAcc());
	        setYVel(getYVel() + getYAcc());
	        
	        //adjust coords
	        setXCoord(getXCoord() + getXVel());
	        setYCoord(getYCoord() + getYVel());
	        
	        //check world boundaries
	        if((this.getYCoord() + this.getVerticalSize()) >= getWorldBot()){
                    explode(p);
	        }
	        if(getXCoord() <= getWorldLeft()){
                    explode(p);
	        }
	        if((this.getXCoord() + this.getHorizontalSize()) >= getWorldRight()){
                    explode(p);
	        }
	        if(getYCoord() <= getWorldTop()){
                    explode(p);
	        }
	        
	        
	        //if player contact, deal dmg
	        if(PlayerContact(p) == true){
	            explode(p);
                    dealDmg(p);
	        }
    	}
    }
    
    public void explode(Player p){
        timer++;
        System.out.println("here!");
        ImageIcon iLeft = new ImageIcon("../images/sourceImage/fire.png");
        ImageIcon iRight = new ImageIcon("../images/sourceImage/fire.png");
        setStillLeft(iLeft);
        setStillRight(iRight);
        setHorizontalSize(150);
        setVerticalSize(150);
        if(timer == 1){
        setXCoord(getXCoord() - 75);
        setYCoord(getYCoord() - 75);
        }
        setYVel(0);
        setYAcc(0);
        setXVel(0);
        setAttack(30);
        if(PlayerContact(p) == true){
            dealDmg(p);
        }
        if(timer == 6){
            setRemove(true);
        }
    }
    //paints projectile
    public void CreateImage(Graphics g){
	Graphics2D g2d = (Graphics2D) g;
	if(getFacing() == 0){
	    g2d = (Graphics2D) g;
            g2d.drawImage(this.getStillLeft().getImage(), (int) (this.getXCoord()), (int) (this.getYCoord()), null);
	}
	else if(getFacing() == 1){
	    g2d = (Graphics2D) g;
            g2d.drawImage(this.getStillRight().getImage(), (int) (this.getXCoord() + 57), (int) (this.getYCoord() + 5), null);
	    
	}	
    }


}
