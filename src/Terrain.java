import java.awt.Graphics;
import java.awt.Image;
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/** 
 * Terrain represents the terrain on the board the player and enemies must traverse.
 * Abstract class the specific, concrete terrain derive from.
 * 
 * @author Shadee Barzin
 * @author Andrew Ferguson
 * @author Michele Haque
 * @author Brendan Murphy
 * @author Fengyu Wang
 * @version CS 48, Winter 2015
*/

public abstract class Terrain implements Serializable{
    //private data
    private double YCoord;
    private double XCoord;
    private double HorizontalSize;
    private double VerticalSize;
    private ImageIcon still;
    private boolean InAir;
    private double LeftSide;
    private double RightSide;
    private double top;
    private double EnemyTop;
    private double EnemyBot;
    private double EnemyRight;
    private double EnemyLeft;
    private double Bot;
    private double XVel, YVel;
    private double XAcc, YAcc;
    private int WorldBot = 700;
    private int WorldLeft = 0;
    private int WorldRight = 7478;
    private int WorldTop = 0;
    private boolean ignoreLeft = false, ignoreTop = false, ignoreRight = false, ignoreBot = false;
    private ArrayList<Enemies> EnemyTops = new ArrayList<Enemies>();
    private int FrameWorldLeft = -300;
    private int FrameWorldRight = 1450;
    private int FrameWorldTop = 0;
    private int FrameWorldBot = WorldBot;    
    
    /**
     * @param x int
     * @param y int
     * Constructor
     * Sets location of the terrain object
     */
    public Terrain(int x, int y){
        setXCoord(x);
        setYCoord(y);
    }
    
    /**
     * Move the terrain if can be moved
     */
    public void move(){
	setXVel(getXVel() + getXAcc());
	//adjust velocities
        setYVel(getYVel() + getYAcc());
        setXVel(getXVel() + getXAcc());
	//adjust coords
        setXCoord(getXCoord() + getXVel());
        setYCoord(getYCoord() + getYVel());
    }

    /**
     * Method to print String representation of Terrain object.
     * To be overridden by derived classes.
     */
    public void print(){}

    /**
     * 
     * To be overridden by derived classes.
     */
    public void UpdateSides(Player p){}

    /**
     * 
     * To be overridden by derived classes.
     */
    public void UpdateSides(Enemies e, int i){}

    /**
     * 
     * To be overridden by derived classes.
     */
    public void UpdateSides(Projectile p, int i){}

    /**
     * 
     * To be overridden by derived classes.
     */
    public void UpdateSides(PlayerProjectile p, int i){}

    /**
     * @param g Graphics
     * @param p Player
     * @param e ArrayList<Enemies>
     * @param proj ArrayList<Projectile>
     * @param Playerproj ArrayList<PlayerProjectile>
     * Paints terrain object onto the Board.
     * To be implemented by derived classes.
     */
    public abstract void paintTerrain(Graphics g, Player p, ArrayList <Enemies> e, ArrayList <Projectile> proj, ArrayList <PlayerProjectile> Playerproj);
     
    /**
     * @param e Enemies
     * @param index int
     * Checks if enemies are in contact with the terrain
     */
    public void CheckEnemyContact(Enemies e, int index){        
          //check player coords, if in contact ith left
         if(!ignoreLeft){
         if((((e.getXCoord() + e.getHorizontalSize()) >= this.getXCoord()) 
                 && (e.getXCoord() + e.getHorizontalSize() 
                 <= this.getXCoord() + e.getSpeed()))
                 && (((e.getYCoord() >= e.getTops().get(index)) 
                 && ((e.getYCoord()) <= this.getBot()))
                 || ((((e.getYCoord() + e.getVerticalSize()) 
                 >= e.getTops().get(index)) && ((e.getYCoord() + e.getVerticalSize()) 
                 <= this.getBot())))
                 || ((e.getTops().get(index) >= e.getYCoord()) 
                 && (this.getBot() <= e.getYCoord() + e.getVerticalSize())))
                 ){
             //adjust coords accordingly           
           e.setXCoord(this.getXCoord() - e.getHorizontalSize() - e.getSpeed()); 
           return;
           }
         }
         //if player coords in contact with right
         if(!ignoreRight){
          if((((e.getXCoord()) 
                 <= (this.getXCoord() + this.getHorizontalSize())) 
                 && (e.getXCoord() >= this.getXCoord() + 
                    this.getHorizontalSize() - e.getSpeed()))
                 && (((e.getYCoord() >= e.getTops().get(index)) && ((e.getYCoord()) 
                 <= this.getBot()))
                 || ((((e.getYCoord() + e.getVerticalSize()) >= e.getTops().get(index)) 
                 && ((e.getYCoord() + e.getVerticalSize()) 
                    <= this.getBot())))
                 || ((e.getTops().get(index) > e.getYCoord()) && (this.getBot() 
                    <= e.getYCoord() + e.getVerticalSize())))
                 ){
             //adjust coords accordingly
             
                e.setXCoord(this.getXCoord() + this.getHorizontalSize() + e.getSpeed()); 
                return;
             }
         }
         //if player coords in contact with top of terrain
         if(!ignoreTop){
          if(((((e.getYCoord() + e.getVerticalSize()) >= e.getTops().get(index)) 
                 && (e.getYCoord() <= e.getTops().get(index))))
                 && (((e.getXCoord() >= this.getXCoord()) 
                 && ((e.getXCoord()) <= this.getRightSide()))
                 || ((((e.getXCoord() + e.getHorizontalSize()) 
                    >= this.getXCoord()) && ((e.getXCoord() 
                        + e.getHorizontalSize()) <= this.getRightSide()))))){
             //adjust vel, acc, and coords accordngly
                e.setInAir(false);
                e.setYCoord(e.getTops().get(index) - e.getVerticalSize());
                e.setYVel(0);
                e.setYAcc(0);
                return;
             }
         }
         //if player coords in contact with bot
         if(!ignoreBot){
        if((((e.getYCoord()) <= (e.getTops().get(index) + this.getVerticalSize()))
                && (e.getYCoord() + e.getVerticalSize() 
                    >= (e.getTops().get(index) + this.getVerticalSize())))
                 && (((e.getXCoord() >= this.getXCoord()) && ((e.getXCoord()) 
                    <= this.getRightSide()))
                 || ((((e.getXCoord() + e.getHorizontalSize()) 
                    >= this.getXCoord()) && ((e.getXCoord() 
                        + e.getHorizontalSize()) <= this.getRightSide()))))){
            //adjust vel accordingly            
                e.setYCoord(this.getYCoord() + this.getVerticalSize());
                e.setYVel(Math.abs(e.getYVel()));
                return;
            }
         }
        //else set acc to .5 if 0
        //(if on ground, reset to 0 in move)        
            if(e.getYAcc() == 0)
            e.setYAcc(.5);        
     }

    /**
     * @param p Player
     * Checks if player is in contact with the terrain
     */
     public void CheckPlayerContact(Player p){
         if((this.getXCoord() >= p.getXCoord() + FrameWorldLeft) && (this.getXCoord() <= p.getXCoord() + FrameWorldRight)){
         //check player coords, if in contact ith left
             if(!ignoreLeft){
         if((((p.getXCoord() + p.getHorizontalSize()) >= this.getXCoord()) 
                 && (p.getXCoord() + p.getHorizontalSize() 
                 <= this.getXCoord() + p.getSpeed()))
                 && (((p.getYCoord() >= this.getTop()) 
                 && ((p.getYCoord()) <= this.getBot()))
                 || ((((p.getYCoord() + p.getVerticalSize()) 
                 >= this.getTop()) && ((p.getYCoord() + p.getVerticalSize()) 
                 <= this.getBot() + 1)))
                 || ((this.getTop() >= p.getYCoord()) 
                 && (this.getBot() <= p.getYCoord() + p.getVerticalSize())))
                 ){
             //adjust coords accordingly
           p.setXCoord(this.getXCoord() - p.getHorizontalSize() - p.getSpeed()); 
           return;
           }
         }
         //if player coords in contact with right
             if(!ignoreRight){
          if((((p.getXCoord()) 
                 <= (this.getXCoord() + this.getHorizontalSize())) 
                 && (p.getXCoord() >= this.getXCoord() + 
                    this.getHorizontalSize() - p.getSpeed()))
                 && (((p.getYCoord() >= this.getTop()) && ((p.getYCoord()) 
                 <= this.getBot()))
                 || ((((p.getYCoord() + p.getVerticalSize()) >= this.getTop()) 
                 && ((p.getYCoord() + p.getVerticalSize()) 
                    <= this.getBot() + 1)))
                 || ((this.getTop() >= p.getYCoord()) && (this.getBot() 
                    <= p.getYCoord() + p.getVerticalSize())))
                 ){
             //adjust coords accordingly             
                p.setXCoord(this.getXCoord() + this.getHorizontalSize() + p.getSpeed()); 
                return;
             }
         }
         //if player coords in contact with top of terrain
             if(!ignoreTop){
          if(((((p.getYCoord() + p.getVerticalSize()) >= this.getTop()) 
                 && (p.getYCoord() <= this.getTop())))
                 && (((p.getXCoord() >= this.getXCoord()) 
                 && ((p.getXCoord()) <= this.getRightSide()))
                 || ((((p.getXCoord() + p.getHorizontalSize()) 
                    >= this.getXCoord()) && ((p.getXCoord() 
                        + p.getHorizontalSize()) <= this.getRightSide()))))){
             //adjust vel, acc, and coords accordngly             
                p.setInAir(false);
                p.setYCoord(this.getTop() - p.getVerticalSize());
                p.setYVel(0);
                p.setYAcc(0);
                return;
             }
         }
         //if player coords in contact with bot
             if(!ignoreBot){
        if((((p.getYCoord()) <= (this.getTop() + this.getVerticalSize()))
                && (p.getYCoord() + p.getVerticalSize() 
                    >= (this.getTop() + this.getVerticalSize())))
                 && (((p.getXCoord() >= this.getXCoord()) && ((p.getXCoord()) 
                    <= this.getRightSide()))
                 || ((((p.getXCoord() + p.getHorizontalSize()) 
                    >= this.getXCoord()) && ((p.getXCoord() 
                        + p.getHorizontalSize()) <= this.getRightSide()))))){
            //adjust vel accordingly
            
                p.setYCoord(this.getYCoord() + this.getVerticalSize());
                p.setYVel(Math.abs(p.getYVel()));
                return;
            }
         }
        //else set acc to .5 if 0
        //(if on ground, reset to 0 in move)
            if(p.getYAcc() == 0)
            p.setYAcc(.5);        
         }
     }

    /**
     * @param e PlayerProjectile
     * @param index int
     * @param p Player
     * Checks if player projectile are in contact with the terrain
     */
      public void CheckPlayerProjectileContact(PlayerProjectile e, int index, Player p){
          //check player coords, if in contact ith left
          if((this.getXCoord() >= p.getXCoord() + FrameWorldLeft) && (this.getXCoord() <= p.getXCoord() + FrameWorldRight)){
         if((((e.getXCoord() + e.getHorizontalSize()) >= this.getXCoord()) 
                 && (e.getXCoord() + e.getHorizontalSize() 
                 <= this.getXCoord() + e.getSpeed()))
                 && (((e.getYCoord() >= e.getTops().get(index)) 
                 && ((e.getYCoord()) <= this.getBot()))
                 || ((((e.getYCoord() + e.getVerticalSize()) 
                 >= e.getTops().get(index)) && ((e.getYCoord() + e.getVerticalSize()) 
                 <= this.getBot() + 1)))
                 || ((e.getTops().get(index) >= e.getYCoord()) 
                 && (this.getBot() <= e.getYCoord() + e.getVerticalSize())))
                 ){
             //adjust coords accordingly
	     e.setRemove(true);            
         }
         //if player coords in contact with right
         else if((((e.getXCoord()) 
                 <= (this.getXCoord() + this.getHorizontalSize())) 
                 && (e.getXCoord() >= this.getXCoord() + 
                    this.getHorizontalSize() - e.getSpeed()))
                 && (((e.getYCoord() >= e.getTops().get(index)) && ((e.getYCoord()) 
                 <= this.getBot()))
                 || ((((e.getYCoord() + e.getVerticalSize()) >= e.getTops().get(index)) 
                 && ((e.getYCoord() + e.getVerticalSize()) 
                    <= this.getBot() + 1)))
                 || ((e.getTops().get(index) >= e.getYCoord()) && (this.getBot() 
                    <= e.getYCoord() + e.getVerticalSize())))
                 ){
             //adjust coords accordingly
               e.setRemove(true);             
         }
         //if player coords in contact with top of terrain
         else if(((((e.getYCoord() + e.getVerticalSize()) >= e.getTops().get(index)) 
                 && (e.getYCoord() <= e.getTops().get(index))))
                 && (((e.getXCoord() >= this.getXCoord()) 
                 && ((e.getXCoord()) <= this.getRightSide()))
                 || ((((e.getXCoord() + e.getHorizontalSize()) 
                    >= this.getXCoord()) && ((e.getXCoord() 
                        + e.getHorizontalSize()) <= this.getRightSide()))))){
             //adjust vel, acc, and coords accordngly
                e.setRemove(true);             
         }
         //if player coords in contact with bot
        else if((((e.getYCoord()) <= (e.getTops().get(index) + this.getVerticalSize()))
                && (e.getYCoord() + e.getVerticalSize() 
                    >= (e.getTops().get(index) + this.getVerticalSize())))
                 && (((e.getXCoord() >= this.getXCoord()) && ((e.getXCoord()) 
                    <= this.getRightSide()))
                 || ((((e.getXCoord() + e.getHorizontalSize()) 
                    >= this.getXCoord()) && ((e.getXCoord() 
                        + e.getHorizontalSize()) <= this.getRightSide()))))){
            //adjust vel accordingly
                e.setRemove(true);            
	}
          }
     }

    /**
     * @param e Projectile
     * @param index int
     * @param p Player
     * Checks if projectiles are in contact with the terrain
     */
       public void CheckProjectileContact(Projectile e, int index, Player p){
          //check player coords, if in contact ith left
           if((this.getXCoord() >= p.getXCoord() + FrameWorldLeft) && (this.getXCoord() <= p.getXCoord() + FrameWorldRight)){
         if((((e.getXCoord() + e.getHorizontalSize()) >= this.getXCoord()) 
                 && (e.getXCoord() + e.getHorizontalSize() 
                 <= this.getXCoord() + e.getSpeed()))
                 && (((e.getYCoord() >= e.getTops().get(index)) 
                 && ((e.getYCoord()) <= this.getBot()))
                 || ((((e.getYCoord() + e.getVerticalSize()) 
                 >= e.getTops().get(index)) && ((e.getYCoord() + e.getVerticalSize()) 
                 <= this.getBot() + 1)))
                 || ((e.getTops().get(index) >= e.getYCoord()) 
                 && (this.getBot() <= e.getYCoord() + e.getVerticalSize())))
                 ){
             //adjust coords accordingly
           e.setRemove(true);
         }
         //if player coords in contact with right
         else if((((e.getXCoord()) 
                 <= (this.getXCoord() + this.getHorizontalSize())) 
                 && (e.getXCoord() >= this.getXCoord() + 
                    this.getHorizontalSize() - e.getSpeed()))
                 && (((e.getYCoord() >= e.getTops().get(index)) && ((e.getYCoord()) 
                 <= this.getBot()))
                 || ((((e.getYCoord() + e.getVerticalSize()) >= e.getTops().get(index)) 
                 && ((e.getYCoord() + e.getVerticalSize()) 
                    <= this.getBot() + 1)))
                 || ((e.getTops().get(index) >= e.getYCoord()) && (this.getBot() 
                    <= e.getYCoord() + e.getVerticalSize())))
                 ){
             //adjust coords accordingly
                e.setRemove(true); 
         }
         //if player coords in contact with top of terrain
         else if(((((e.getYCoord() + e.getVerticalSize()) >= e.getTops().get(index)) 
                 && (e.getYCoord() <= e.getTops().get(index))))
                 && (((e.getXCoord() >= this.getXCoord()) 
                 && ((e.getXCoord()) <= this.getRightSide()))
                 || ((((e.getXCoord() + e.getHorizontalSize()) 
                    >= this.getXCoord()) && ((e.getXCoord() 
                        + e.getHorizontalSize()) <= this.getRightSide()))))){
             //adjust vel, acc, and coords accordngly
                e.setRemove(true);
         }
         //if player coords in contact with bot
        else if((((e.getYCoord()) <= (e.getTops().get(index) + this.getVerticalSize()))
                && (e.getYCoord() + e.getVerticalSize() 
                    >= (e.getTops().get(index) + this.getVerticalSize())))
                 && (((e.getXCoord() >= this.getXCoord()) && ((e.getXCoord()) 
                    <= this.getRightSide()))
                 || ((((e.getXCoord() + e.getHorizontalSize()) 
                    >= this.getXCoord()) && ((e.getXCoord() 
                        + e.getHorizontalSize()) <= this.getRightSide()))))){
            //adjust vel accordingly
               e.setRemove(true);
         }
           }
     }

    /**
     * @param e GrenadeProjectile
     * @param index int
     * @param p Player
     * Checks if enemies are in contact with the terrain
     */
       public void CheckGrenadeProjectileContact(GrenadeProjectile e, int index, Player p){
          //check player coords, if in contact ith left
           if((this.getXCoord() >= p.getXCoord() + FrameWorldLeft) && (this.getXCoord() <= p.getXCoord() + FrameWorldRight)){
         if((((e.getXCoord() + e.getHorizontalSize()) >= this.getXCoord()) 
                 && (e.getXCoord() + e.getHorizontalSize() 
                 <= this.getXCoord() + e.getSpeed()))
                 && (((e.getYCoord() >= e.getTops().get(index)) 
                 && ((e.getYCoord()) <= this.getBot()))
                 || ((((e.getYCoord() + e.getVerticalSize()) 
                 >= e.getTops().get(index)) && ((e.getYCoord() + e.getVerticalSize()) 
                 <= this.getBot() + 1)))
                 || ((e.getTops().get(index) >= e.getYCoord()) 
                 && (this.getBot() <= e.getYCoord() + e.getVerticalSize())))
                 ){
             //adjust coords accordingly
           e.explode(p);
         }
         //if player coords in contact with right
         else if((((e.getXCoord()) 
                 <= (this.getXCoord() + this.getHorizontalSize())) 
                 && (e.getXCoord() >= this.getXCoord() + 
                    this.getHorizontalSize() - e.getSpeed()))
                 && (((e.getYCoord() >= e.getTops().get(index)) && ((e.getYCoord()) 
                 <= this.getBot()))
                 || ((((e.getYCoord() + e.getVerticalSize()) >= e.getTops().get(index)) 
                 && ((e.getYCoord() + e.getVerticalSize()) 
                    <= this.getBot() + 1)))
                 || ((e.getTops().get(index) >= e.getYCoord()) && (this.getBot() 
                    <= e.getYCoord() + e.getVerticalSize())))
                 ){
             //adjust coords accordingly
                 e.explode(p);
         }
         //if player coords in contact with top of terrain
         else if(((((e.getYCoord() + e.getVerticalSize()) >= e.getTops().get(index)) 
                 && (e.getYCoord() <= e.getTops().get(index))))
                 && (((e.getXCoord() >= this.getXCoord()) 
                 && ((e.getXCoord()) <= this.getRightSide()))
                 || ((((e.getXCoord() + e.getHorizontalSize()) 
                    >= this.getXCoord()) && ((e.getXCoord() 
                        + e.getHorizontalSize()) <= this.getRightSide()))))){
             //adjust vel, acc, and coords accordngly
                 e.explode(p);
         }
         //if player coords in contact with bot
        else if((((e.getYCoord()) <= (e.getTops().get(index) + this.getVerticalSize()))
                && (e.getYCoord() + e.getVerticalSize() 
                    >= (e.getTops().get(index) + this.getVerticalSize())))
                 && (((e.getXCoord() >= this.getXCoord()) && ((e.getXCoord()) 
                    <= this.getRightSide()))
                 || ((((e.getXCoord() + e.getHorizontalSize()) 
                    >= this.getXCoord()) && ((e.getXCoord() 
                        + e.getHorizontalSize()) <= this.getRightSide()))))){
            //adjust vel accordingly
               e.explode(p);
         }
           }
     }

    
    //-----------setters/getters--------------------------
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
     * @return the HorizontalSize
     */
    public double getHorizontalSize() {
        return HorizontalSize;
    }

    /**
     * @param HorizontalSize the HorizontalSize to set
     */
    public void setHorizontalSize(double HorizontalSize) {
        this.HorizontalSize = HorizontalSize;
    }

    /**
     * @return the VerticalSize
     */
    public double getVerticalSize() {
        return VerticalSize;
    }

    /**
     * @param VerticalSize the VerticalSize to set
     */
    public void setVerticalSize(double VerticalSize) {
        this.VerticalSize = VerticalSize;
    }

    /**
     * @return the still
     */
    public ImageIcon getStill() {
        return still;
    }

    /**
     * @param still the still to set
     */
    public void setStill(ImageIcon still) {
        this.still = still;
    }

    /**
     * @return the InAir
     */
    public boolean isInAir() {
        return InAir;
    }

    /**
     * @param InAir the InAir to set
     */
    public void setInAir(boolean InAir) {
        this.InAir = InAir;
    }

    /**
     * @return the LeftSide
     */
    public double getLeftSide() {
        return LeftSide;
    }

    /**
     * @param LeftSide the LeftSide to set
     */
    public void setLeftSide(double LeftSide) {
        this.LeftSide = LeftSide;
    }

    /**
     * @return the RightSide
     */
    public double getRightSide() {
        return RightSide;
    }

    /**
     * @param RightSide the RightSide to set
     */
    public void setRightSide(double RightSide) {
        this.RightSide = RightSide;
    }

    /**
     * @return the top
     */
    public double getTop() {
        return top;
    }

    /**
     * @param top the top to set
     */
    public void setTop(double top) {
        this.top = top;
    }

    /**
     * @return the Bot
     */
    public double getBot() {
        return Bot;
    }

    /**
     * @param Bot the Bot to set
     */
    public void setBot(double Bot) {
        this.Bot = Bot;
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
     * @return the EnemyTop
     */
    public double getEnemyTop() {
        return EnemyTop;
    }

    /**
     * @param EnemyTop the EnemyTop to set
     */
    public void setEnemyTop(double EnemyTop) {
        this.EnemyTop = EnemyTop;
    }

    /**
     * @return the EnemyBot
     */
    public double getEnemyBot() {
        return EnemyBot;
    }

    /**
     * @param EnemyBot the EnemyBot to set
     */
    public void setEnemyBot(double EnemyBot) {
        this.EnemyBot = EnemyBot;
    }

    /**
     * @return the EnemyRight
     */
    public double getEnemyRight() {
        return EnemyRight;
    }

    /**
     * @param EnemyRight the EnemyRight to set
     */
    public void setEnemyRight(double EnemyRight) {
        this.EnemyRight = EnemyRight;
    }

    /**
     * @return the EnemyLeft
     */
    public double getEnemyLeft() {
        return EnemyLeft;
    }

    /**
     * @param EnemyLeft the EnemyLeft to set
     */
    public void setEnemyLeft(double EnemyLeft) {
        this.EnemyLeft = EnemyLeft;
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
     * @return the igonoreLeft
     */
    public boolean isIgnoreLeft() {
        return ignoreLeft;
    }

    /**
     * @param igonoreLeft the igonoreLeft to set
     */
    public void setIgnoreLeft(boolean igonoreLeft) {
        this.ignoreLeft = igonoreLeft;
    }

    /**
     * @return the ignoreTop
     */
    public boolean isIgnoreTop() {
        return ignoreTop;
    }

    /**
     * @param ignoreTop the ignoreTop to set
     */
    public void setIgnoreTop(boolean ignoreTop) {
        this.ignoreTop = ignoreTop;
    }

    /**
     * @return the ignoreRight
     */
    public boolean isIgnoreRight() {
        return ignoreRight;
    }

    /**
     * @param ignoreRight the ignoreRight to set
     */
    public void setIgnoreRight(boolean ignoreRight) {
        this.ignoreRight = ignoreRight;
    }

    /**
     * @return the ignoreBot
     */
    public boolean isIgnoreBot() {
        return ignoreBot;
    }

    /**
     * @param ignoreBot the ignoreBot to set
     */
    public void setIgnoreBot(boolean ignoreBot) {
        this.ignoreBot = ignoreBot;
    }

    /**
     * @return the EnemyTops
     */
    public ArrayList <Enemies> getEnemyTops() {
        return EnemyTops;
    }

    /**
     * @param EnemyTops the EnemyTops to set
     */
    public void setEnemyTops(ArrayList <Enemies> EnemyTops) {
        this.EnemyTops = EnemyTops;
    }
}
