
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;


public class Projectile {
    private int XCoord, YCoord;
    private int XVel, YVel;
    private int XAcc, YAcc;
    private Image still;
    private int facing;
    private int HorizontalSize, VerticalSize;
    private int LeftBound, RightBound;
    private int TopBound, BotBound;
    private int WorldBot = 334;
    private int WorldLeft = 0;
    private int WorldRight = 1024;
    private int WorldTop = 0;
    private int attack;
    private int speed;
    private boolean remove = false;
    
    public Projectile(int x, int y, int direction, Graphics g){
        setXCoord(x);
        setYCoord(y);
        setFacing(direction);
        
    }
    
    public void move(Player p){
        setXVel(getXVel() + getXAcc());
        setYVel(getYVel() + getYAcc());
        
        setXCoord(getXCoord() + getXVel());
        setYCoord(getYCoord() + getYVel());
        
        if(getBotBound() >= getWorldBot()){
            setRemove(true);
        }
        if(getLeftBound() <= getWorldLeft()){
            setRemove(true);
        }
        if(getRightBound() >= getWorldRight()){
            setRemove(true);
        }
        if(getTopBound() <= getWorldTop()){
            setRemove(true);
        }
        if(PlayerContact(p) == true){
            dealDmg(p);
        }
    }
    
    public boolean PlayerContact(Player p){
        if((getXCoord() >= p.getXCoord()) && (getXCoord() <= (p.getXCoord() + p.getHorizontalSize())) && ((getYCoord() + getVerticalSize()) <= (p.getYCoord() + p.getVerticalSize())) && (getYCoord() >= p.getYCoord())){
            return true;
        }
        else{
            return false;
        }
        
    }
    
    public void dealDmg(Player p){
        p.setHp(p.getHp() - this.getAttack());
        setRemove(true);
    }
    
    public void CreateImage(Graphics g){}
    
    public void paintImage(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        Rectangle rect = new Rectangle(getXCoord(), getYCoord(), 20, 10);
			    g2d.setColor(Color.red);
			    g2d.fill(rect);
    }

    
    
    
    //-------------------Setters/Getters--------------------------------------------------------[
    /**
     * @return the XCoord
     */
    public int getXCoord() {
        return XCoord;
    }

    /**
     * @param XCoord the XCoord to set
     */
    public void setXCoord(int XCoord) {
        this.XCoord = XCoord;
    }

    /**
     * @return the YCoord
     */
    public int getYCoord() {
        return YCoord;
    }

    /**
     * @param YCoord the YCoord to set
     */
    public void setYCoord(int YCoord) {
        this.YCoord = YCoord;
    }

    /**
     * @return the XVel
     */
    public int getXVel() {
        return XVel;
    }

    /**
     * @param XVel the XVel to set
     */
    public void setXVel(int XVel) {
        this.XVel = XVel;
    }

    /**
     * @return the YVel
     */
    public int getYVel() {
        return YVel;
    }

    /**
     * @param YVel the YVel to set
     */
    public void setYVel(int YVel) {
        this.YVel = YVel;
    }

    /**
     * @return the XAcc
     */
    public int getXAcc() {
        return XAcc;
    }

    /**
     * @param XAcc the XAcc to set
     */
    public void setXAcc(int XAcc) {
        this.XAcc = XAcc;
    }

    /**
     * @return the YAcc
     */
    public int getYAcc() {
        return YAcc;
    }

    /**
     * @param YAcc the YAcc to set
     */
    public void setYAcc(int YAcc) {
        this.YAcc = YAcc;
    }

    /**
     * @return the still
     */
    public Image getStill() {
        return still;
    }

    /**
     * @param still the still to set
     */
    public void setStill(Image still) {
        this.still = still;
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

    /**
     * @return the LeftBound
     */
    public int getLeftBound() {
        return LeftBound;
    }

    /**
     * @param LeftBound the LeftBound to set
     */
    public void setLeftBound(int LeftBound) {
        this.LeftBound = LeftBound;
    }

    /**
     * @return the RightBound
     */
    public int getRightBound() {
        return RightBound;
    }

    /**
     * @param RightBound the RightBound to set
     */
    public void setRightBound(int RightBound) {
        this.RightBound = RightBound;
    }

    /**
     * @return the TopBound
     */
    public int getTopBound() {
        return TopBound;
    }

    /**
     * @param TopBound the TopBound to set
     */
    public void setTopBound(int TopBound) {
        this.TopBound = TopBound;
    }

    /**
     * @return the BotBound
     */
    public int getBotBound() {
        return BotBound;
    }

    /**
     * @param BotBound the BotBound to set
     */
    public void setBotBound(int BotBound) {
        this.BotBound = BotBound;
    }

    /**
     * @return the WorldBot
     */
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
    public int getSpeed() {
        return speed;
    }

    /**
     * @param speed the speed to set
     */
    public void setSpeed(int speed) {
        this.speed = speed;
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
}
