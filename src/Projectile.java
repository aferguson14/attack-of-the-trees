import static java.lang.Math.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.ImageIcon;

//extremely similar to player projectile
public class Projectile implements Serializable{
    //private data
    private double XCoord, YCoord;
    private double XVel, YVel;
    private double XAcc, YAcc;
    private ImageIcon stillLeft;
    private ImageIcon stillRight;
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
    
    //constructor
    public Projectile(double x, double y, int direction, Graphics g, double angle, Player p){
        setXCoord(x);
        setYCoord(y);
        setFacing(direction);
        setTerrainDimensions(p.getTerrains());
    }
    //moves projectile
    public void move(Player p){
        //adjust velocities
    	if(Board.getState() == Board.STATE.GAME){
	        for(int i = 0; i < getTerrains().size(); i++){
	            getTerrains().get(i).CheckProjectileContact(this, i, p);
	            
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
    }
    
//return true if player contact
public boolean PlayerContact(Player p){
    if(((((getXCoord() >= p.getXCoord()) && 
            (getXCoord() <= (p.getXCoord() + p.getHorizontalSize()))) || 
            (((getXCoord() + getHorizontalSize()) >= p.getXCoord()) && 
            ((getXCoord() + getHorizontalSize()) <= (p.getXCoord() + p.getHorizontalSize())))) && 
            ((getYCoord() + getVerticalSize()) <= 
                (p.getYCoord() + p.getVerticalSize())) && 
            (getYCoord() >= p.getYCoord()))){
        
        
        return true;
    }
    else{
        return false;
    }

}

    //deal dmg to player based on projectile attack
    public void dealDmg(Player p){
        p.setHp(p.getHp() - this.getAttack());
        setRemove(true);
    }
    
    public void CreateImage(Graphics g){}
    
    //paint projectile
    public void paintImage(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
	if(getFacing() == 0){
	    g2d.drawImage(this.getStillLeft().getImage(),(int) (this.getXCoord()), (int) (this.getYCoord() + 5), null);
	}
	else if(getFacing() == 1){
	    g2d.drawImage(this.getStillLeft().getImage(),(int) (this.getXCoord() + this.getHorizontalSize()), (int) (this.getYCoord() + 5), null);
	    
	}
    }
    
    //gets xy velocities based on angle and hypotenuse (speed)
    public void getXY(Double spd, Double ang){
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
    
        public double findAngle(Point p){
        return Math.sinh((this.getYCoord() - p.getY()) 
                / p.distance(this.getXCoord(), this.getYCoord()));
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
    
    
    //-------------------Setters/Getters--------------------------------------
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
    public ImageIcon getStillLeft() {
        return stillLeft;
    }

    /**
     * @param stillLeft the stillLeft to set
     */
    public void setStillLeft(ImageIcon stillLeft) {
        this.stillLeft = stillLeft;
    }

    /**
     * @return the stillRight
     */
    public ImageIcon getStillRight() {
        return stillRight;
    }

    /**
     * @param stillRight the stillRight to set
     */
    public void setStillRight(ImageIcon stillRight) {
        this.stillRight = stillRight;
    }
}