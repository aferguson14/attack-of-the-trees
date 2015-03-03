import java.awt.*;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import javax.swing.ImageIcon;


public abstract class Weapon {
    //private data
    private Image stillLeft;
    private Image stillRight;
    
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
    private ArrayList <PlayerProjectile> projectiles = new ArrayList<PlayerProjectile>();
    private int facing = 0;

    
    public void AttackAnimation(Graphics g){}
    public void Attack(){}
    public void DealDmgP(Player p){}
    public void DealDmgE(Enemies e){}
    public void shoot(Point p, Graphics g){}
    //move weapon in correspondence with player
    public void move(Player p){
        if(p.getFacing() == 1){
            setXCoord(p.getXCoord()+ p.getHorizontalSize()); //changed
            setYCoord(p.getYCoord() + 25); //25 to make weapon lower
        }
        else{
            setXCoord(p.getXCoord());
            setYCoord(p.getYCoord() + 25);
        }
        
	//check boundaries, if on ground, InAir = false
	if((getYCoord() + p.getVerticalSize()) >= 700){
	    setYCoord(p.getWorldBot() - p.getVerticalSize());
	    setYVel(0);
	}
        if(p.getXCoord() <= 0){
            setXCoord(p.getHorizontalSize());
        }
        else if((p.getXCoord() + 50) >= 7000){
            setXCoord(1024 - 50 + p.getHorizontalSize());
        }
    }
    //move then paint weapon's projectiles
    public void paintProjectile(ArrayList <Enemies> e, Graphics g, Player p){
        for(PlayerProjectile proj : this.getProjectiles()){
            proj.move(e, p);
        }
        for(PlayerProjectile proj : this.getProjectiles()){
            proj.paintImage(g);
        }
    }
    //if projectile needs to be removed, remove
    public void deleteProjectiles(){
        for(PlayerProjectile proj : projectiles){
            if(proj.isRemove()){
                projectiles.remove(proj);
                break;
            }
        }
    }
    public void addProjectile(PlayerProjectile p){
        projectiles.add(p);
    }
    public void deleteProjectile(PlayerProjectile p){
        projectiles.remove(p);
    }
    //find angle between player and point
    public double findAngle(Point p){
        return Math.sinh((this.getYCoord() - p.getY()) 
                / p.distance(this.getXCoord(), this.getYCoord()));
    }
    public abstract void paintWeapon(Graphics g, Player p,  
                                        ArrayList <Enemies> e);
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
    
    public Image getStillLeft() {
        return stillLeft;
    }

    public void setStillLeft(Image stillLeft) {
        this.stillLeft = stillLeft;
    }

    public Image getStillRight() {
        return stillRight;
    }

    public void setStillRight(Image stillRight) {
        this.stillRight = stillRight;
    }
    
}
