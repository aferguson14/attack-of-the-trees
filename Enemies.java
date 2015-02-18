//implement facing once we have a flipped image
//implement any other methods written down
//look into animation
//fix attacking hitboxes

import java.awt.*;
import java.awt.event.KeyEvent;
import static java.lang.Math.abs;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public abstract class Enemies {
    //Private Data
    private double XCoord, YCoord;
    private double XVel, YVel;
    private double XAcc, YAcc;
    private Image still;
    private boolean InAir;
    private boolean IsAttacking;
    private int HorizontalSize, VerticalSize;
    private int WorldBot = 700;
    private int WorldLeft = 0;
    private int WorldRight = 7478;
    private int WorldTop = 0;
    private boolean InRange; 
    private int hp;
    private int attack;
    private double Speed;
    private int AttackSpeed;
    private int AttackRange;
    private int facing = 0;
    private boolean attacking = false;
    private double JumpSpeed;
    private int AttackSpeedCount = 0;
    private ArrayList <Projectile> projectiles = new ArrayList<Projectile>();
    private boolean startAttacking = false;
    
    public Enemies(double x, double y){
        XCoord = x;
        YCoord = y;
    }
    //Move Method, Similar to Player's
    public void move(ArrayList <Terrain> terrain){
        if(Board.getState() == Board.STATE.GAME){
	    setXVel(getXVel() + getXAcc());
	    setYVel(getYVel() + getYAcc());
	    if(XVel > 0){
		setFacing(1);
	    }
	    else if(XVel < 0){
		setFacing(0);
	    }
	    setXCoord(getXCoord() + getXVel());
	    setYCoord(getYCoord() + getYVel());
	    for(Terrain t : terrain){
		t.CheckEnemyContact(this);
            
	    }
        
	    if((this.getYCoord() + this.getVerticalSize()) >= getWorldBot()){
		setYCoord(getWorldBot() - getVerticalSize());
		setYVel(0);
		setInAir(false);
	    }
	    if(getXCoord() <= getWorldLeft()){
		setYCoord(0);
	    }
	    if((this.getXCoord() + this.getHorizontalSize()) >= getWorldRight()){
		setYCoord(WorldRight = getHorizontalSize());
	    }
	    if(getYCoord() <= getWorldTop()){
		setYCoord(0);
	    }
        }
    }
    
    public void Attack(Player p, Graphics g){}
    public void takeDmg(Player p){
        hp -= p.getAttack();
        if(hp <= 0){
            die();
        }
    }
    public void takeDmg(int dmg){
        hp -= dmg;
        if(hp <= 0){
            die();
        }
    }
    public void AI(Player p, Graphics g, ArrayList<Terrain> terrain){}
    public void dropItem(){}
    public void die(){
	
    }
    public void paintEnemy(Player p , Graphics g){}
    
    public void paintProjectile(Player p, Graphics g){
        for(Projectile proj : this.getProjectiles()){
	    proj.move(p);
	}
        for(Projectile proj : this.getProjectiles()){
            proj.paintImage(g);
        }
    }
    //Check if the player is within the Enemies attack range
    public boolean checkInRange(Player p){
        if(facing == 0){
            if(abs((p.getXCoord() + p.getHorizontalSize()) - XCoord) <= AttackRange){
                return true;
            }
        }
        else if(facing == 1){
            if(abs((XCoord + HorizontalSize) - p.getXCoord()) <= AttackRange){
                return true;
            }
        }
        
        return false;
        
    }
    
    public void deleteProjectiles(){
        for(Projectile proj : projectiles){
            if(proj.isRemove()){
                projectiles.remove(proj); 
                break;
            }
        }
    }
    public void attackAnimation(Graphics g){}
    
    public void addProjectile(Projectile p){
        projectiles.add(p);
    }
    public void deleteProjectile(int index){
        projectiles.remove(index);
    }
    public void deleteProjectile(Projectile p){
        projectiles.remove(p);
    }
    
    
    //-------------Getters/Setters------------------------------------------------------------------------------------------------------------------------
    public double getXCoord() {
        return XCoord;
    }

    public void setXCoord(double XCoord) {
        this.XCoord = XCoord;
    }

    public double getYCoord() {
        return YCoord;
    }

    public void setYCoord(double YCoord) {
        this.YCoord = YCoord;
    }

    public double getXVel() {
        return XVel;
    }

    public void setXVel(double XVel) {
        this.XVel = XVel;
    }

    public double getYVel() {
        return YVel;
    }

    public void setYVel(double YVel) {
        this.YVel = YVel;
    }

    public double getXAcc() {
        return XAcc;
    }

    public void setXAcc(double XAcc) {
        this.XAcc = XAcc;
    }

    public double getYAcc() {
        return YAcc;
    }

    public void setYAcc(double YAcc) {
        this.YAcc = YAcc;
    }

    public Image getStill() {
        return still;
    }

    public void setStill(Image still) {
        this.still = still;
    }

    public boolean isInAir() {
        return InAir;
    }

    public void setInAir(boolean InAir) {
        this.InAir = InAir;
    }

    public boolean isIsAttacking() {
        return IsAttacking;
    }

    public void setIsAttacking(boolean IsAttacking) {
        this.IsAttacking = IsAttacking;
    }

    public int getHorizontalSize() {
        return HorizontalSize;
    }

    public void setHorizontalSize(int HorizontalSize) {
        this.HorizontalSize = HorizontalSize;
    }

    public int getVerticalSize() {
        return VerticalSize;
    }

    public void setVerticalSize(int VerticalSize) {
        this.VerticalSize = VerticalSize;
    }

    public int getWorldBot() {
        return WorldBot;
    }

    public void setWorldBot(int WorldBot) {
        this.WorldBot = WorldBot;
    }

    public int getWorldLeft() {
        return WorldLeft;
    }

    public void setWorldLeft(int WorldLeft) {
        this.WorldLeft = WorldLeft;
    }

    public int getWorldRight() {
        return WorldRight;
    }

    public void setWorldRight(int WorldRight) {
        this.WorldRight = WorldRight;
    }

    public int getWorldTop() {
        return WorldTop;
    }

    public void setWorldTop(int WorldTop) {
        this.WorldTop = WorldTop;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public double getSpeed() {
        return Speed;
    }

    public void setSpeed(double Speed) {
        this.Speed = Speed;
    }

    public int getAttackSpeed() {
        return AttackSpeed;
    }

    public void setAttackSpeed(int AttackSpeed) {
        this.AttackSpeed = AttackSpeed;
    }

    public boolean isInRange() {
        return InRange;
    }

    public void setInRange(boolean InRange) {
        this.InRange = InRange;
    }
    
    public int getAttackRange() {
        return AttackRange;
    }

    public void setAttackRange(int AttackRange) {
        this.AttackRange = AttackRange;
    }

    public int getFacing() {
        return facing;
    }

    public void setFacing(int facing) {
        this.facing = facing;
    }

    public boolean isAttacking() {
        return attacking;
    }

    public void setAttacking(boolean attacking) {
        this.attacking = attacking;
    }

    public double getJumpSpeed() {
        return JumpSpeed;
    }

    public void setJumpSpeed(double JumpSpeed) {
        this.JumpSpeed = JumpSpeed;
    }

    public int getAttackSpeedCount() {
        return AttackSpeedCount;
    }

    public void setAttackSpeedCount(int AttackSpeedCount) {
        this.AttackSpeedCount = AttackSpeedCount;
    }

    /**
     * @return the projectiles
     */
    public ArrayList <Projectile> getProjectiles() {
        return projectiles;
    }

    /**
     * @param projectiles the projectiles to set
     */
    public void setPojectiles(ArrayList <Projectile> projectiles) {
        this.projectiles = projectiles;
    }

    /**
     * @return the startAttacking
     */
    public boolean isStartAttacking() {
        return startAttacking;
    }

    /**
     * @param startAttacking the startAttacking to set
     */
    public void setStartAttacking(boolean startAttacking) {
        this.startAttacking = startAttacking;
    }
}
