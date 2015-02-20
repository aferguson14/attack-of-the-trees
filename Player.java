
import java.awt.*;
import java.awt.event.KeyEvent;
import static java.lang.Math.abs;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Player{
    private double XCoord;
    private double XVel;
    private double YCoord;
    private double YVel;
    private double XAcc;
    private double YAcc;
    private double scrollX;
    private double scrollY;
    private int hp;
    private int attack;
    private Image still;
    private boolean InAir = false;
    private boolean attacking = false;
    private int facing = 1;
    private int AttackSpeed;
    private int AttackSpeedCount = 19;
    private int HorizontalSize = 50;
    private int VerticalSize = 115;
    private int WorldBot = 700;
    private int WorldLeft = 0;
    private int WorldRight = 7478;
    private int WorldTop = 0;
    private double Speed;
    private double JumpSpeed;
    private ArrayList<Weapon> weapon = new ArrayList<Weapon>(); 
    private Weapon currentWeapon;
    private Point MousePoint;
    private int WeaponTracker = 0;
    
    public Player() {
<<<<<<< HEAD
    	ImageIcon i = new ImageIcon("images/playerImages/guy/guySideDown.png"); //character image
    	setStill(i.getImage());
        //starting Player position/velocity/acceleration
    	XCoord = 10;
    	YCoord = WorldBot - VerticalSize;
        YAcc = 1;
=======
	ImageIcon i = new ImageIcon("images/playerImages/guy/guySideDown.png"); //character image
	setStill(i.getImage());
        //starting Player postion/velocity/acceleration
	XCoord = 10;
	YCoord = WorldBot - VerticalSize;
        YAcc =  .5;
>>>>>>> 7e6f5866f938029954048dab077603e0318884ea
        YVel = 0;
        XVel = 0;
        hp = 100;
        attack = 10;
        AttackSpeed = 20;
        Speed = 4;
        JumpSpeed = -15;
    }
    
    public void move(ArrayList <Terrain> terrain) {
        //add velocities to positions/add gravity to yVel
<<<<<<< HEAD
    	setXCoord(getXCoord() + getXVel());
    	setYVel(getYVel() + getYAcc());
    	setYCoord(getYCoord() + getYVel());
        
    	//check boundaries, if on ground, InAir = false
    	if((getYCoord() + VerticalSize) >= 334){
=======
	setXCoord(getXCoord() + getXVel());
	setYVel(getYVel() + getYAcc());
	setYCoord(getYCoord() + getYVel());
        for(Terrain t : terrain){
            t.CheckPlayerContact(this);
            
        }
        
	//check boundaries, if on ground, InAir = false
	if((getYCoord() + VerticalSize) >= 700){
>>>>>>> 7e6f5866f938029954048dab077603e0318884ea
	    setYCoord(getWorldBot() - VerticalSize);
	    setYVel(0);
	    setInAir(false);
	}
        if(getXCoord() <= 0){
            setXCoord(0);
        }
        else if((getXCoord() + 50) >= 7000){
            setXCoord(7000 - 50);
        }
        
        setScrollX(getXCoord()*(-1)); //For background
	setScrollY(getYCoord()); //For background

        
    }
    
    public void paintPlayer(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
<<<<<<< HEAD
        if (getHp() > 0){
        	g2d.drawImage(this.getStill(), this.getXCoord(), this.getYCoord(), null);

        	g.setColor(Color.white);
        	g.fillRect(this.getXCoord() - 10 , this.getYCoord() - 10, this.getHp(), 7);
        }
   //     if (getHp() < 0)
     //   	Board.State = Board.State.GAMEOVER;
=======
        g2d.drawImage(this.getStill(), (int) (this.getXCoord()), (int) (this.getYCoord()), null);

        if(this.getHp() >= 0){
            g.setColor(Color.white);
            g.fillRect((int) (this.getXCoord() -10) , (int) (this.getYCoord() - 10), this.getHp(), 7);
        }
>>>>>>> 7e6f5866f938029954048dab077603e0318884ea
	/*
        Font fntP = new Font("arial", Font.BOLD, 10);
        g.setFont(fntP);
        g.setColor(Color.blue);
        g.drawString("" + this.getHp(), this.getXCoord(), this.getYCoord() - 10);
	*/
<<<<<<< HEAD
    }
    
    public void pausePlayer(){
    	setXVel(0);
    	setYVel(0);
=======
>>>>>>> 7e6f5866f938029954048dab077603e0318884ea
    }
    
    public void AttackAnimation(Graphics g){
        if(isAttacking()){
	    if(getFacing() == 0){

		ImageIcon i = new ImageIcon("images/playerImages/guy/guySideUpLeft.png"); //character image
		setStill(i.getImage());


	    }
	    else if(getFacing() == 1){
<<<<<<< HEAD
		Rectangle rect = new Rectangle(getXCoord() + 57, getYCoord() + 20, 20, 10);
		g2d.setColor(Color.blue);
		g2d.fill(rect);		
	    }	    
	}
=======
		ImageIcon i = new ImageIcon("images/playerImages/guy/guySideUpRight.png");
		setStill(i.getImage());
		
	    }
	    
	}else{
            if(getFacing() == 0){
                ImageIcon i = new ImageIcon("images/playerImages/guy/guySideDownLeft.png");
		setStill(i.getImage());
            }else{
                ImageIcon i = new ImageIcon("images/playerImages/guy/guySideDownRight.png");
		setStill(i.getImage());
            }
        }
>>>>>>> 7e6f5866f938029954048dab077603e0318884ea
    }
    
    public void takeDmg(Enemies e){
        setHp(getHp() - e.getAttack());
    }
//    public void testWeapon(Graphics g){
//        Point p = new Point(650, 265);
//        getCurrentWeapon().shoot(p, g);
//    }
    
    public void AddWeapon(Weapon w){
        weapon.add(w);
        currentWeapon = w;
    }
    public void switchWeapon(){
        if(WeaponTracker < weapon.size()){
            WeaponTracker++;
            currentWeapon = weapon.get(WeaponTracker);
        }
        else if(WeaponTracker == weapon.size()){
            WeaponTracker = 0;
            currentWeapon = weapon.get(WeaponTracker);
        }
    }
    
    public void PlayerAttack(Graphics g){
        setMousePoint(Board.MouseCoords);
        if(isAttacking()){
            getCurrentWeapon().shoot(this.getMousePoint(), g);
        }
//        if(isAttacking()){
//            if(getFacing() == 0){
//                for(Enemies e : enemies){
//                    if(abs(getXCoord() - (e.getXCoord() + e.getHorizontalSize())) <= 20){
//			setAttackSpeedCount(getAttackSpeedCount() + 1);
//			if(getAttackSpeedCount() == getAttackSpeed()){
//			    e.takeDmg(this);
//			    setAttackSpeedCount(0);
//			}
//                    }
//                }
//            }
//            else if(getFacing() == 1){
//                for(Enemies e : enemies){
//                    if(abs((getXCoord() + getHorizontalSize()) - e.getXCoord()) <= 20){
//                        setAttackSpeedCount(getAttackSpeedCount() + 1);
//			if(getAttackSpeedCount() == getAttackSpeed()){
//			    e.takeDmg(this);
//			    setAttackSpeedCount(0);
//			}
//                    }
//                }
//            }
//        }
    }

    
    public void keyPressed(KeyEvent e) {
	int key = e.getKeyCode();
        //input changes velocity
	if (key == KeyEvent.VK_LEFT){
            if(!isAttacking()){
                ImageIcon iLeft = new ImageIcon("images/playerImages/guy/guySideDownLeft.png"); // character image
                setStill(iLeft.getImage());
            }
	    setXVel(-1 * (this.getSpeed()));
            setFacing(0);
        }
	if (key == KeyEvent.VK_RIGHT){
            if(!isAttacking()){
                ImageIcon iRight = new ImageIcon("images/playerImages/guy/guySideDown.png"); // character image
                setStill(iRight.getImage());
            }
	    setXVel(getSpeed());
            setFacing(1);
        }
        //if on ground, can jump
        if(!isInAir()){
            if(key == KeyEvent.VK_UP){
                setYAcc(.5);
                setYVel((getJumpSpeed()));
                setInAir(true);    
            }
        }
//        if(key == KeyEvent.VK_SPACE)
//            setAttacking(true);
    }
    
    public void keyReleased(KeyEvent e) {
	int key = e.getKeyCode();
        //release of L/R key's result in 0 horiz vel
	if (key == KeyEvent.VK_LEFT)
	    setXVel(0);
	
	if (key == KeyEvent.VK_RIGHT)
	    setXVel(0);
//        if(key == KeyEvent.VK_SPACE){
//            setAttacking(false);
//            if(getFacing() == 0){
//                ImageIcon iLeft = new ImageIcon("images/playerImages/guy/guySideDownLeft.png"); // character image
//            setStill(iLeft.getImage());
//            }else if(getFacing() == 1){
//                ImageIcon iRight = new ImageIcon("images/playerImages/guy/guySideDown.png"); // character image
//            setStill(iRight.getImage());
//            }
//                }
    }

    
    
    //-----------------Getters/Setters------------------------------------------------------------------------------------------------
    public double getXCoord() {
        return XCoord;
    }
    
    public void setXCoord(double XCoord) {
        this.XCoord = XCoord;
    }
    
    public double getXVel() {
        return XVel;
    }
    
    public void setXVel(double XVel) {
        this.XVel = XVel;
    }

    public double getYCoord() {
        return YCoord;
    }
    
    public void setYCoord(double YCoord) {
        this.YCoord = YCoord;
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

    public boolean isAttacking() {
        return attacking;
    }

    public void setAttacking(boolean attacking) {
        this.attacking = attacking;
    }

    public int getFacing() {
        return facing;
    }

    public void setFacing(int facing) {
        this.facing = facing;
    }

    public int getAttackSpeed() {
        return AttackSpeed;
    }

    public void setAttackSpeed(int AttackSpeed) {
        this.AttackSpeed = AttackSpeed;
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
     * @return the AttackSpeedCount
     */
    public int getAttackSpeedCount() {
        return AttackSpeedCount;
    }

    /**
     * @param AttackSpeedCount the AttackSpeedCount to set
     */
    public void setAttackSpeedCount(int AttackSpeedCount) {
        this.AttackSpeedCount = AttackSpeedCount;
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
     * @return the jumpspeed
     */
    public double getJumpSpeed() {
        return JumpSpeed;
    }

    /**
     * @param jumpspeed the jumpspeed to set
     */
    public void setJumpspeed(double jumpspeed) {
        this.JumpSpeed = jumpspeed;
    }

    /**
     * @return the weapon
     */
    public ArrayList<Weapon> getWeapon() {
        return weapon;
    }

    /**
     * @param weapon the weapon to set
     */
    public void setWeapon(ArrayList<Weapon> weapon) {
        this.weapon = weapon;
    }

    /**
     * @return the MousePoint
     */
    public Point getMousePoint() {
        return MousePoint;
    }

    /**
     * @param MousePoint the MousePoint to set
     */
    public void setMousePoint(Point MousePoint) {
        this.MousePoint = MousePoint;
    }

    /**
     * @return the currentWeapon
     */
    public Weapon getCurrentWeapon() {
        return currentWeapon;
    }

    /**
     * @param currentWeapon the currentWeapon to set
     */
    public void setCurrentWeapon(Weapon currentWeapon) {
        this.currentWeapon = currentWeapon;
    }

    /**
     * @return the WeaponTracker
     */
    public int getWeaponTracker() {
        return WeaponTracker;
    }

    /**
     * @param WeaponTracker the WeaponTracker to set
     */
    public void setWeaponTracker(int WeaponTracker) {
        this.WeaponTracker = WeaponTracker;
    }

    /**
     * @return the scrollX
     */
    public double getScrollX() {
        return scrollX;
    }

    /**
     * @param scrollX the scrollX to set
     */
    public void setScrollX(double scrollX) {
        this.scrollX = scrollX;
    }

    /**
     * @return the scrollY
     */
    public double getScrollY() {
        return scrollY;
    }

    /**
     * @param scrollY the scrollY to set
     */
    public void setScrollY(double scrollY) {
        this.scrollY = scrollY;
    }



}
