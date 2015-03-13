import java.awt.*;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.PointerInfo;
import java.io.Serializable;
import java.lang.Math;

/** 
 * Weapon represents a weapon the player uses to defeat enemies.
 * An abstract class that the player's weapons will derive from.
 * 
 * @author Shadee Barzin
 * @author Andrew Ferguson
 * @author Michele Haque
 * @author Brendan Murphy
 * @author Fengyu Wang
 * @version CS 48, Winter 2015
*/

public abstract class Weapon implements Serializable{
    //private data
    private ImageIcon stillLeft;
    private ImageIcon stillRight;
    private int Attack;
    private int AttackSpeed;
    private int AttackSpeedTimer;
    private double angle;
    private Point MouseCoords;
    private double XShootVel;
    private double YShootVel;
    private double Speed;
    private double XCoord;
    private double YCoord;
    private double YVel, XVel;
    private double mouseX;
    private double mouseY;
    private double mouseAngle;
    private ArrayList <PlayerProjectile> projectiles = new ArrayList<PlayerProjectile>();
    private int facing = 0;
    private int playerDirection;
    private String weaponType;

    /**
     * Animation for attacking should be overridden by subclasses
     */
    public void AttackAnimation(Graphics g){}
    
    /**
     * Animation for attacking should be overridden by subclasses
     */
    public void Attack(){}
    
    /**
     * Deals damage to the player should be overridden by subclasses
     */
    public void DealDmgP(Player p){}
    
    /**
     * Deals damage to enemies should be overridden by subclasses
     */
    public void DealDmgE(Enemies e){}
    
    /**
     * Shoots the weapon's projectiles should be overridden by subclasses
     */
    public void shoot(Point p, Graphics g, Player player){}
    
    /**
     * Changes the weapons coordinates
     */
    public void move(Player p){
	//move weapon in correspondence with player

	//set the corrdinate at the player coords
	setXCoord(p.getXCoord());
	setYCoord(p.getYCoord());
		
	//necessary for projectiles to know player facing
	setPlayerDirection(p.getFacing());
	
	//check boundaries, if on ground, InAir = false
	if((getYCoord() + p.getVerticalSize()) >= 700){
	    setYCoord(p.getWorldBot() - p.getVerticalSize());
	    setYVel(0);
	}
    }
    
    /**
     * Move then paint the weapon's projectiles 
     */
    public void paintProjectile(ArrayList <Enemies> e, Graphics g, Player p){
        for(PlayerProjectile proj : this.getProjectiles()){
            proj.move(e, p);
        }
        for(PlayerProjectile proj : this.getProjectiles()){
            proj.CreateImage(g);
        }
    }
    /**
     * If the projectile needs to be removed, delete
     */
    public void deleteProjectiles(){
        for(PlayerProjectile proj : projectiles){
            if(proj.isRemove()){
                projectiles.remove(proj);
                break;
            }
        }
    }
    
    /**
     * Adds the PlayerProjectile to the projectiles arraylist
     */
    public void addProjectile(PlayerProjectile p){
        projectiles.add(p);
    }
    
    /**
     * Removes the PlayerProjectile from the projectiles arraylist
     */
    public void deleteProjectile(PlayerProjectile p){
        projectiles.remove(p);
    }
    
    /**
     * Finds the angle between player and point
     */
    public double findAngle(Point p){
	if(facing == 1){
	//get angle based on facing right
	    return -Math.atan2((p.getY()-63-this.getYCoord()),(p.getX()+15-this.getXCoord()));
	    
	}
	else if(facing == 0){
	//get angle based on facing left
	    return -Math.atan2((p.getY()-63-this.getYCoord()),(p.getX()+25-this.getXCoord()));
	}
	return -1;
    }
    
    /**
     * Method to paint the weaoon should be overridden by subclasses
     */
    public abstract void paintWeapon(Graphics g, Player p,  
                                        ArrayList <Enemies> e);
    /**
     * Prints the weapon's name should be overridden by subclasses
     */
    public abstract void print();
    
    //----------------------------------------Getters/Setters--------------------------------------
    /**
     * @return the Attack
     */
    public int getAttack() {
        return Attack;
    }

    /**
     * @param Attack the Attack to set
     */
    public void setAttack(int Attack) {
        this.Attack = Attack;
    }

    /**
     * @return the AttackSpeed
     */
    public int getAttackSpeed() {
        return AttackSpeed;
    }

    /**
     * @param AttackSpeed the AttackSpeed to set
     */
    public void setAttackSpeed(int AttackSpeed) {
        this.AttackSpeed = AttackSpeed;
    }

    /**
     * @return the AttackSpeedTimer
     */
    public int getAttackSpeedTimer() {
        return AttackSpeedTimer;
    }

    /**
     * @param AttackSpeedTimer the AttackSpeedTimer to set
     */
    public void setAttackSpeedTimer(int AttackSpeedTimer) {
        this.AttackSpeedTimer = AttackSpeedTimer;
    }

    /**
     * @return the angle
     */
    public double getAngle() {
        return angle;
    }

    /**
     * @param angle the angle to set
     */
    public void setAngle(double angle) {
        this.angle = angle;
    }

    /**
     * @return the MouseCoords
     */
    public Point getMouseCoords() {
        return MouseCoords;
    }

    /**
     * @param MouseCoords the MouseCoords to set
     */
    public void setMouseCoords(Point MouseCoords) {
        this.MouseCoords = MouseCoords;
    }

    /**
     * @return the XVel
     */
    public double getXShootVel() {
        return XShootVel;
    }

    /**
     * @param XVel the XVel to set
     */
    public void setXShootVel(double XShootVel) {
        this.XShootVel = XShootVel;
    }

    /**
     * @return the YVel
     */
    public double getYShootVel() {
        return YShootVel;
    }

    /**
     * @param YVel the YVel to set
     */
    public void setYShootVel(double YShootVel) {
        this.YShootVel = YShootVel;
    }

    /**
     * @return the Speed
     */
    public double getSpeed() {
        return Speed;
    }

    /**
     * @param Speed the Speed to set
     */
    public void setSpeed(double Speed) {
        this.Speed = Speed;
    }

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
     * @return the projectiles
     */
    public ArrayList <PlayerProjectile> getProjectiles() {
        return projectiles;
    }

    /**
     * @param projectiles the projectiles to set
     */
    public void setProjectiles(ArrayList <PlayerProjectile> projectiles) {
        this.projectiles = projectiles;
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
    
    /**
     * @return the playerDirection
     */
    public int getPlayerDirection(){
	return playerDirection;
    }

    /**
     * @param playerDirection the direction of the player to set
     */
    public void setPlayerDirection(int playerDirection){
	this.playerDirection = playerDirection;
    }

    /**
     * @param weaponType the weapon type to set
     */
    public void setWeaponType(String weaponType){
	this.weaponType = weaponType;
    }

    /**
     * @return the weaponType
     */	
    public String getWeaponType(){
	return weaponType;
    }
}
