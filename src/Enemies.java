
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
    //World dimensions
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
    private boolean cantMove = false;
    private double lastCoord1 = 0, lastCoord2;
    private Resource resource;
    
    //Constructor
    public Enemies(double x, double y){
        XCoord = x;
        YCoord = y;
    }
    //Move Method, Similar to Player's
    public void move(ArrayList <Terrain> terrain){

        if(Board.getState() == Board.STATE.GAME){
        //adjust velocities
        setXVel(getXVel() + getXAcc());
        setYVel(getYVel() + getYAcc());
        //set facing depending on movement direction
        if(XVel > 0){
            setFacing(1);
        }
	else if(XVel < 0){
            setFacing(0);
        }
        //adjust coords
        setXCoord(getXCoord() + getXVel());
        setYCoord(getYCoord() + getYVel());
        //check terrain contact
        for(Terrain t : terrain){
            t.CheckEnemyContact(this);
            
        }
        //check world contacts
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
        //set the last coordinate for future checking
            setLastCoord2(getLastCoord1());
            setLastCoord1(getXCoord());
        }
    }
    //if current coord and last coord are equal, and not attacking, return true
    //basically, if not moving and not attacking return true
    public boolean checkMove(){
        if((getLastCoord1() <= getLastCoord2()) && (getLastCoord1() 
                >= getLastCoord2()) && !attacking){
            return true;
        }
        return false;
    }
    
    public void Attack(Player p, Graphics g){}
    //subtract health by player's attack dmg
    public void takeDmg(Player p){
        hp -= p.getAttack();
        if(hp <= 0){
            die();
        }
    }
    //subtract health by an integer
    public void takeDmg(int dmg){
        hp -= dmg;
        if(hp <= 0){
            die();
        }
    }
    public void AI(Player p, Graphics g, ArrayList<Terrain> terrain){}
    public void dropResource(Graphics g){}
    public void die(){
	
    }
    public void paintEnemy(Player p , Graphics g){}
    
    //move enemies projectiles, then paint them
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
            if(abs((p.getXCoord() + p.getHorizontalSize()) - XCoord) 
                    <= AttackRange){
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
    
    //if any projectiles need to be removed, remove them
    public void deleteProjectiles(){
        for(Projectile proj : projectiles){
            if(proj.isRemove()){
                projectiles.remove(proj); 
                break;
            }
        }
    }
    public abstract void print();
    
    //finds the angle between enemy position and a given point
    public double findAngle(Point p){
        return Math.sinh((this.getYCoord() - p.getY()) 
                / p.distance(this.getXCoord(), this.getYCoord()));
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
    
    
    //-------------Getters/Setters---------------------------------------------
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
        this.setProjectiles(projectiles);
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

    /**
     * @param projectiles the projectiles to set
     */
    public void setProjectiles(ArrayList <Projectile> projectiles) {
        this.projectiles = projectiles;
    }

    /**
     * @return the cantMove
     */
    public boolean isCantMove() {
        return cantMove;
    }

    /**
     * @param cantMove the cantMove to set
     */
    public void setCantMove(boolean cantMove) {
        this.cantMove = cantMove;
    }

    /**
     * @return the lastCoord1
     */
    public double getLastCoord1() {
        return lastCoord1;
    }

    /**
     * @param lastCoord1 the lastCoord1 to set
     */
    public void setLastCoord1(double lastCoord1) {
        this.lastCoord1 = lastCoord1;
    }

    /**
     * @return the lastCoord2
     */
    public double getLastCoord2() {
        return lastCoord2;
    }

    /**
     * @param lastCoord2 the lastCoord2 to set
     */
    public void setLastCoord2(double lastCoord2) {
        this.lastCoord2 = lastCoord2;
    }

    public Resource getResource(){
	return resource;
    }

    public void setResource(Resource resource){
	this.resource = resource;
    }
}
