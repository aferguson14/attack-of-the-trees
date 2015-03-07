import static java.lang.Math.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;
import javax.swing.ImageIcon;

//extremely similar to Projectile Class
//difference being: made to attck enemies rather than player


public class PlayerProjectile {
    //private data
    private double XCoord, YCoord;
    private double XVel, YVel;
    private double XAcc, YAcc;
    private Image stillLeft;
    private Image stillRight;
    private int facing;
    private int HorizontalSize, VerticalSize;
    private int WorldBot = 700;
    private int WorldLeft = 0;
    private int WorldRight = 7478;
    private int WorldTop = 0;
    private int attack;
    private double Speed;
    private boolean remove = false;
    private double Angle;
    private ArrayList<Terrain> terrains = new ArrayList<Terrain>();
    private ArrayList<Double> tops = new ArrayList<Double>();
    private double mouseX;
    private double mouseY;
    private double mouseAngle;
    private int playerDirection;
    //Constructor
    public PlayerProjectile(double x, double y, int direction, Graphics g, double angle, Player p){
	if((direction == 0) && (this.getPlayerDirection()== 0)){
	    double cx = x+25; //25, center x  of arm rotation
	    double cy = y+53; //center y  of arm rotation
	    double r = -77;//77
	    setXCoord(cx-r*Math.cos(angle));
	    setYCoord(cy+r*Math.sin(angle));
	}
	else if((direction == 0) && (this.getPlayerDirection()== 1)){
	    double cx = x+25; //center x  of arm rotation
	    double cy = y+53; //center y  of arm rotation
	    double r = -142;//77
	    setXCoord(cx-r*Math.cos(angle));
	    setYCoord(cy+r*Math.sin(angle));
	}

	else if((direction == 1) && (this.getPlayerDirection()==0)){
	    double cx = x+13; //15
	    double cy = y+63;
	    double r = 75;
	    setXCoord(cx+r*Math.cos(angle)/*+Math.sin(angle)*10*/);
	    setYCoord((cy-r*Math.sin(angle))/*-Math.cos(angle)*10*/);
	}
	else if((direction == 1) && (this.getPlayerDirection()==1)){
	    double cx = x+13; //15
	    double cy = y+63;
	    double r = 75;
	    setXCoord(cx+r*Math.cos(angle)/*+Math.sin(angle)*10*/);
	    setYCoord((cy-r*Math.sin(angle))/*-Math.cos(angle)*10*/);
	}
        setFacing(direction);
        setTerrainDimensions(p.getTerrains());
    }

    //move same as Projectile
        public void move(ArrayList<Enemies> e, Player p){
            if(Board.getState() == Board.STATE.GAME){
                //adjust velocities
                for(int i = 0; i < terrains.size(); i++){
                    terrains.get(i).CheckPlayerProjectileContact(this, i, p);
                }

                setXVel(getXVel() + getXAcc());
                setYVel(getYVel() + getYAcc());

                //adjust coordinates
                setXCoord(getXCoord() + getXVel());
                setYCoord(getYCoord() + getYVel());

                //check world boundaries
                if((this.getYCoord() + this.getVerticalSize()) >= getWorldBot()){
                    setRemove(true);
                }
                if(getXCoord() <= p.getXCoord() - 600){
                    setRemove(true);
                }
                if((this.getXCoord() + this.getHorizontalSize()) >= p.getXCoord() + 800){
                    setRemove(true);
                }
                if(getYCoord() <= getWorldTop()){
                    setRemove(true);
                }
                //check enemy contact
                for(Enemies enem : e){
                    if(EnemyContact(enem) == true){
                        dealDmg(enem);
                    }
                }
            }
    }

//return true if in contact with enemy
public boolean EnemyContact(Enemies enem){
    if((getXCoord() >= enem.getXCoord()) && 
            (getXCoord() <= (enem.getXCoord() + enem.getHorizontalSize())) 
            && ((getYCoord() + getVerticalSize()) <= 
                (enem.getYCoord() + enem.getVerticalSize())) 
            && (getYCoord() >= enem.getYCoord())){
        return true;
    }
    else{
        return false;
    }
}
    //deal dmg to enemy based on projectile attack
    public void dealDmg(Enemies e){
        e.setHp(e.getHp() - this.getAttack());
        setRemove(true);
    }
    
    public void CreateImage(Graphics g){}
    
    //paint projectile **REMOVE CALL TO THIS FUNCTION TO RID RED PROJECTILE**
    public void paintImage(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        Rectangle rect = new Rectangle((int) getXCoord(), 
                            (int) getYCoord(), 20, 10);
			    g2d.setColor(Color.red);
			    g2d.fill(rect);
    }
    
    //get xy velocities based on angle and speed, like a right traingle
    public void getXY(Double spd, Double ang){
        double yvel, xvel;
        yvel = -1 * (spd * (Math.sin(ang)));
        xvel = (spd * (Math.cos(ang)));
        setXVel(xvel);
        setYVel(yvel);
        
	setXVel(getXVel());
	/*       if(this.getFacing() == 0){
	   setXVel(-1 * (getXVel()));
        }
	else{
            setXVel(getXVel());
	}
	*/
    }
    
    public void setTerrainDimensions(ArrayList <Terrain> ter){
         this.setTerrains(ter);
         for(int i = 0; i < getTerrains().size(); i++){
             getTops().add(i, getTerrains().get(i).getTop());
         }
     }
     public void updateTerrainDimensions(){
         for(int i = 0; i < getTerrains().size(); i++){
             getTerrains().get(i).UpdateSides(this, i);
         }
     }
     
     public void setTopIndex(int index, double value){
         tops.set(index, value);
     }
    
    
    //-------------------Setters/Getters--------------------------------------------------------[
    /**
     * @return the XCoord
     */
    public double getXCoord() {
        return XCoord;
    }

    /**
     * @param XCoord the XCoord to set
     */
    public void setXCoord(double XCoord) {
        this.XCoord = XCoord;
    }

    /**
     * @return the YCoord
     */
    public double getYCoord() {
        return YCoord;
    }

    /**
     * @param YCoord the YCoord to set
     */
    public void setYCoord(double YCoord) {
        this.YCoord = YCoord;
    }

    /**
     * @return the XVel
     */
    public double getXVel() {
        return XVel;
    }

    /**
     * @param XVel the XVel to set
     */
    public void setXVel(double XVel) {
        this.XVel = XVel;
    }

    /**
     * @return the YVel
     */
    public double getYVel() {
        return YVel;
    }

    /**
     * @param YVel the YVel to set
     */
    public void setYVel(double YVel) {
        this.YVel = YVel;
    }

    /**
     * @return the XAcc
     */
    public double getXAcc() {
        return XAcc;
    }

    /**
     * @param XAcc the XAcc to set
     */
    public void setXAcc(double XAcc) {
        this.XAcc = XAcc;
    }

    /**
     * @return the YAcc
     */
    public double getYAcc() {
        return YAcc;
    }

    /**
     * @param YAcc the YAcc to set
     */
    public void setYAcc(double YAcc) {
        this.YAcc = YAcc;
    }



    /**
     * @return the facing
     */
    public int getFacing() {
        return facing;
    }

    /**
     * @param facing the facing to set
     */
    public void setFacing(int facing) {
        this.facing = facing;
    }

    /**
     * @return the HorizontalSize
     */
    public int getHorizontalSize() {
        return HorizontalSize;
    }

    /**
     * @param HorizontalSize the HorizontalSize to set
     */
    public void setHorizontalSize(int HorizontalSize) {
        this.HorizontalSize = HorizontalSize;
    }

    /**
     * @return the VerticalSize
     */
    public int getVerticalSize() {
        return VerticalSize;
    }

    /**
     * @param VerticalSize the VerticalSize to set
     */
    public void setVerticalSize(int VerticalSize) {
        this.VerticalSize = VerticalSize;
    }

    public int getWorldBot() {
        return WorldBot;
    }

    /**
     * @param WorldBot the WorldBot to set
     */
    public void setWorldBot(int WorldBot) {
        this.WorldBot = WorldBot;
    }

    /**
     * @return the WorldLeft
     */
    public int getWorldLeft() {
        return WorldLeft;
    }

    /**
     * @param WorldLeft the WorldLeft to set
     */
    public void setWorldLeft(int WorldLeft) {
        this.WorldLeft = WorldLeft;
    }

    /**
     * @return the WorldRight
     */
    public int getWorldRight() {
        return WorldRight;
    }

    /**
     * @param WorldRight the WorldRight to set
     */
    public void setWorldRight(int WorldRight) {
        this.WorldRight = WorldRight;
    }

    /**
     * @return the WorldTop
     */
    public int getWorldTop() {
        return WorldTop;
    }

    /**
     * @param WorldTop the WorldTop to set
     */
    public void setWorldTop(int WorldTop) {
        this.WorldTop = WorldTop;
    }

    /**
     * @return the attack
     */
    public int getAttack() {
        return attack;
    }

    /**
     * @param attack the attack to set
     */
    public void setAttack(int attack) {
        this.attack = attack;
    }

    /**
     * @return the speed
     */
    public double getSpeed() {
        return Speed;
    }

    /**
     * @param speed the speed to set
     */
    public void setSpeed(double speed) {
        this.Speed = speed;
    }

    /**
     * @return the remove
     */
    public boolean isRemove() {
        return remove;
    }

    /**
     * @param remove the remove to set
     */
    public void setRemove(boolean remove) {
        this.remove = remove;
    }

    /**
     * @return the angle
     */
    public double getAngle() {
        return Angle;
    }

    /**
     * @param angle the angle to set
     */
    public void setAngle(double angle) {
        this.Angle = angle;
    }

    /**
     * @return the terrains
     */
    public ArrayList<Terrain> getTerrains() {
        return terrains;
    }

    /**
     * @param terrains the terrains to set
     */
    public void setTerrains(ArrayList<Terrain> terrains) {
        this.terrains = terrains;
    }

    /**
     * @return the tops
     */
    public ArrayList<Double> getTops() {
        return tops;
    }

    /**
     * @param tops the tops to set
     */
    public void setTops(ArrayList<Double> tops) {
        this.tops = tops;
    }

    /**
     * @return the stillLeft
     */
    public Image getStillLeft() {
        return stillLeft;
    }

    /**
     * @param stillLeft the stillLeft to set
     */
    public void setStillLeft(Image stillLeft) {
        this.stillLeft = stillLeft;
    }

    /**
     * @return the stillRight
     */
    public Image getStillRight() {
        return stillRight;
    }

    /**
     * @param stillRight the stillRight to set
     */
    public void setStillRight(Image stillRight) {
        this.stillRight = stillRight;
    }

    /**
     * @return the mouseX
     */
    public double getMouseX() {
        return mouseX;
    }

    /**
     * @param mouseX the mouseX to set
     */
    public void setMouseX(double mouseX) {
        this.mouseX = mouseX;
    }

    /**
     * @return the mouseY
     */
    public double getMouseY() {
        return mouseY;
    }

    /**
     * @param mouseY the mouseY to set
     */
    public void setMouseY(double mouseY) {
        this.mouseY = mouseY;
    }

    /**
     * @return the mouseAngle
     */
    public double getMouseAngle() {
        return mouseAngle;
    }

    /**
     * @param mouseAngle the mouseAngle to set
     */
    public void setMouseAngle(double mouseAngle) {
        this.mouseAngle = mouseAngle;
    }

    public int getPlayerDirection(){
	return playerDirection;
    }

    public void setPlayerDirection(int playerDirection){
	this.playerDirection = playerDirection;
    }

}