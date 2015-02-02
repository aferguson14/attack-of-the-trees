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
    private int XCoord, YCoord;
    private int XVel, YVel;
    private int XAcc, YAcc;
    private Image still;
    private boolean InAir;
    private boolean IsAttacking;
    private int HorizontalSize, VerticalSize;
    private int LeftBound, RightBound;
    private int TopBound, BotBound;
    private int WorldBot = 334;
    private int WorldLeft = 0;
    private int WorldRight = 1024;
    private int WorldTop = 0;
    private boolean InRange; 
    private int hp;
    private int attack;
    private int Speed;
    private int AttackSpeed;
    private int AttackRange;
    private int facing = 0;
    private boolean attacking = false;
    private int JumpSpeed;
    private int AttackSpeedCount = 0;
    private ArrayList <Projectile> projectiles = new ArrayList<Projectile>();
    private boolean startAttacking = false;
    
    public Enemies(int x, int y){
        XCoord = x;
        YCoord = y;
    }
    //Move Method, Similar to Player's
    public void move(){
        setXVel(getXVel() + getXAcc());
        setYVel(getYVel() + getYAcc());
        if(XVel > 0){
            setFacing(1);
        } else if(XVel < 0){
            setFacing(0);
        }
        setXCoord(getXCoord() + getXVel());
        setYCoord(getYCoord() + getYVel());
        
        if(getBotBound() >= getWorldBot()){
            setYCoord(getWorldBot() - getVerticalSize());
            setYVel(0);
            setInAir(false);
        }
        if(getLeftBound() <= getWorldLeft()){
            setYCoord(0);
        }
        if(getRightBound() >= getWorldRight()){
            setYCoord(WorldRight = getHorizontalSize());
        }
        if(getTopBound() <= getWorldTop()){
            setYCoord(0);
        }
        
    }

    public void Attack(Player p, Graphics g){}
    public void takeDmg(Player p){
        hp -= p.getAttack();
        if(hp <= 0){
            die();
        }
    }
    public void AI(Player p, Graphics g){}
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
    public int getXCoord() {
        return XCoord;
    }
    public void setXCoord(int XCoord) {
        this.XCoord = XCoord;
    }
    public int getYCoord() {
        return YCoord;
    }
    public void setYCoord(int YCoord) {
        this.YCoord = YCoord;
    }
    public int getXVel() {
        return XVel;
    }
    public void setXVel(int XVel) {
        this.XVel = XVel;
    }
    public int getYVel() {
        return YVel;
    }
    public void setYVel(int YVel) {
        this.YVel = YVel;
    }
    public int getXAcc() {
        return XAcc;
    }
    public void setXAcc(int XAcc) {
        this.XAcc = XAcc;
    }
    public int getYAcc() {
        return YAcc;
    }
    public void setYAcc(int YAcc) {
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
    public int getLeftBound() {
        return LeftBound;
    }
    public void setLeftBound(int LeftBound) {
        this.LeftBound = LeftBound;
    }
    public int getRightBound() {
        return RightBound;
    }
    public void setRightBound(int RightBound) {
        this.RightBound = RightBound;
    }
    public int getTopBound() {
        return TopBound;
    }
    public void setTopBound(int TopBound) {
        this.TopBound = TopBound;
    }
    public int getBotBound() {
        return BotBound;
    }
    public void setBotBound(int BotBound) {
        this.BotBound = BotBound;
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
    public int getSpeed() {
        return Speed;
    }
    public void setSpeed(int Speed) {
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

    public int getJumpSpeed() {
        return JumpSpeed;
    }

    public void setJumpSpeed(int JumpSpeed) {
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
