import java.awt.*;
import java.awt.event.KeyEvent;
import static java.lang.Math.abs;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Player{
    //private data
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
    private Image armStill;
    private boolean InAir = false;
    private boolean attacking = false;
    private int facing = 1;
    private int AttackSpeed;
    private int AttackSpeedCount = 49;
    //player size
    private int HorizontalSize = 50;
    private int VerticalSize = 115;
    //world dimensions
    private int WorldBot = 700;
    private int WorldLeft = 0;
    private int WorldRight = 7478;
    private int WorldTop = 0;
    private double Speed;
    private double JumpSpeed;
    private ArrayList<Weapon> weapon = new ArrayList<Weapon>(); 
    private ArrayList<Terrain> terrains = new ArrayList<Terrain>();
    private Weapon currentWeapon;
    private Point MousePoint;
    private int WeaponTracker = 0;
    private double HealthBarY = 0;
    private int FrameWorldLeft = -300;
    private int FrameWorldRight = 1450;
    private int FrameWorldTop = 0;
    private int FrameWorldBot = WorldBot;
    
    //resource counters
    private int logCount;
    private int coinCount;
    
    //Constructor
    public Player() {
	ImageIcon i = new ImageIcon("../images/playerImages/guy/guySideDown.png"); //character image
	setStill(i.getImage());
        ImageIcon iArm = new ImageIcon ("../images/playerImages/guy/armRight.png");
	setArmStill(iArm.getImage());
        
        //starting Player postion/velocity/acceleration
	XCoord = 10; //was 10
	YCoord = WorldBot - VerticalSize;
        YAcc =  .5;
        YVel = 0;
        XVel = 0;
        hp = 100;
        attack = 500;
        AttackSpeed = 50;
        Speed = 4;
        JumpSpeed = -15;
	HealthBarY = (this.getYCoord() - 580);
	
	logCount = 0;
	coinCount = 0;

    }
    
    public void move(ArrayList <Terrain> terrain) {
        //add velocities to positions/add gravity to yVel
        if(Board.getState() == Board.STATE.GAME){
            //System.out.println("x: " + this.getXCoord());
        if(Math.abs(getYVel()) > 0){
            setInAir(true);
        }
            setXCoord(getXCoord() + getXVel());
            setYVel(getYVel() + getYAcc());
            setYCoord(getYCoord() + getYVel());
            //check terrain contact
            for(Terrain t : terrain){
                t.CheckPlayerContact(this);

            }
            //check world boundaries
            if((getYCoord() + VerticalSize) >= 700){
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
    }
    
    public void paintPlayer(Graphics g){
        //paint player and health bar
		Graphics2D g2d = (Graphics2D) g;
		//  g2d.drawImage(this.getStill(), (int) (this.getXCoord()), (int) (this.getYCoord()), null);

		//Arm movement
		int mouseX = (int) MouseInfo.getPointerInfo().getLocation().getX() - (int) Board.BoardLoc.getX();
		this.getCurrentWeapon().setMouseX(mouseX);
		int mouseY = (int) MouseInfo.getPointerInfo().getLocation().getY() - (int) Board.BoardLoc.getY();
		//System.out.println("Coords: " +mouseX + ", " + mouseY);
		this.getCurrentWeapon().setMouseY(mouseY);
		if(this.getFacing()==0){ //images are drawn in specific order
			double angle = Math.atan2(
			((mouseY)-(this.getYCoord()+60)), 
			((mouseX-600+this.getXCoord())-(this.getXCoord()+25)));
			this.getCurrentWeapon().setMouseAngle(angle);

			g2d.rotate(angle, this.getXCoord()+25, this.getYCoord()+63);
			g2d.rotate(Math.PI, this.getXCoord()+25, this.getYCoord()+63);
			
			//WEAPONS POSITIONED DIFFERENTLY
			if(this.getCurrentWeapon().getWeaponType()=="Gun"){
			    g2d.drawImage(this.getCurrentWeapon().getStillLeft(),(int) (this.getXCoord())-52, (int) (this.getYCoord())+53, null);
			}
			else if(this.getCurrentWeapon().getWeaponType()=="Bow"){
			    g2d.drawImage(this.getCurrentWeapon().getStillLeft(),(int) (this.getXCoord())-52+30, (int) (this.getYCoord())+53-25, null);
			}
			//END WEAPON POSITIONING
			g2d.drawImage(this.getArmStill(), (int) (this.getXCoord())-15, (int) (this.getYCoord())+60, null);


			g2d.rotate(-Math.PI, this.getXCoord()+25, this.getYCoord()+63);
			g2d.rotate(-angle, this.getXCoord()+25, this.getYCoord()+63);
			g2d.drawImage(this.getStill(), (int) (this.getXCoord()), (int) (this.getYCoord()), null);
		}

		if(this.getFacing()==1){
			g2d.drawImage(this.getStill(), (int) (this.getXCoord()), (int) (this.getYCoord()), null);

			double angle = Math.atan2(
					((mouseY)-(this.getYCoord()+60)), 
					((mouseX-600+this.getXCoord())-(this.getXCoord()+15)));
			this.getCurrentWeapon().setMouseAngle(angle);

			g2d.rotate(angle, this.getXCoord()+15, this.getYCoord()+60+3);

			//WEAPONS POSITIONED DIFFERENTLY
			if(this.getCurrentWeapon().getWeaponType()=="Gun"){
			    g2d.drawImage(this.getCurrentWeapon().getStillRight(),(int) (this.getXCoord())+15+30, (int) (this.getYCoord())+60-5, null);//was +33,-7
			}
			else if(this.getCurrentWeapon().getWeaponType()=="Bow"){
			    g2d.drawImage(this.getCurrentWeapon().getStillRight(),(int) (this.getXCoord())+15+30, (int) (this.getYCoord())+60-5-30, null);//was +33
			}
			//END WEAPON POSITIONING
			g2d.drawImage(this.getArmStill(), (int) (this.getXCoord())+15, (int) (this.getYCoord())+60, null);
			g2d.rotate(-angle, this.getXCoord()+15, this.getYCoord()+60+3);

		}
		//End Arm Movement
		if(this.getHp() >= 0){
			g.drawRect((int) (this.getXCoord() -280) , (int) getHealthBarY(), 100 * 7, 20);
			g.setColor(Color.RED);
			g.fillRect((int) (this.getXCoord() -280) , (int) getHealthBarY(), this.getHp() * 7, 20);
		}
		else{
			Board.setState(Board.STATE.GAMEOVER);
			Board.getGoscreen().setVisible(true);
		} 
    }
    
public void AttackAnimation(Graphics g){
//change player image depending on attack and facing
if(isAttacking()){
    //change player image depending on attack and facing
		if(isAttacking()){
			if(getFacing() == 0){

				//	ImageIcon i = new ImageIcon("images/playerImages/guy/guySideUpLeft.png"); //character image
				//setStill(i.getImage());
			}
			else if(getFacing() == 1){
				//ImageIcon i = new ImageIcon("images/playerImages/guy/guySideUpRight.png");
				//setStill(i.getImage());
			}

		}else{
			if(getFacing() == 0){
				//ImageIcon i = new ImageIcon("images/playerImages/guy/guySideDownLeft.png");
				//setStill(i.getImage());
			}else{
				//ImageIcon i = new ImageIcon("images/playerImages/guy/guySideDownRight.png");
				//setStill(i.getImage());
			}
		}
}
}
    
//subtract Hp by enemy attack
    public void takeDmg(Enemies e){
        setHp(getHp() - e.getAttack());
    }
//add weapon    
    public void AddWeapon(int index, Weapon w){
        weapon.add(index, w);
        currentWeapon = w;
    }
//switch current weapon forward
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


//switch current weapon backward
    public void switchWeaponBackward(){
        if(WeaponTracker > 0){
            WeaponTracker--;
            currentWeapon = weapon.get(WeaponTracker);
        }
        else if(WeaponTracker == 0){
            WeaponTracker = weapon.size();
            currentWeapon = weapon.get(WeaponTracker);
        }
    }

//get mouse coords
// if attacking, shoot weapon
//readjust mouse coords
    public void PlayerAttack(Graphics g){
        Board.MouseCoords.x += (this.getXCoord() - 650);
	//-650 relates to painting player in Board
        setMousePoint(Board.MouseCoords);
        if(isAttacking()){
            getCurrentWeapon().shoot(this.getMousePoint(), g, this);
        }
        Board.MouseCoords.x -= (this.getXCoord() - 650);
    }
//get player coords in point form
    public Point getPlayerPoint(){
        Point p = new Point((int)this.getXCoord(), (int)this.getYCoord());
        return p;
    }
    
    //player movement input
    public void keyPressed(KeyEvent e) {
	int key = e.getKeyCode();
		//input cahnges velocity
		if (key == KeyEvent.VK_A){
			if(!isAttacking()){
			}
			ImageIcon iLeft = new ImageIcon
					("../images/playerImages/guy/guyArmlessLeft.png");
			setStill(iLeft.getImage());
			ImageIcon iArmLeft = new ImageIcon
					("../images/playerImages/guy/armLeft.png");
			setArmStill(iArmLeft.getImage());

			setXVel(-1 * (this.getSpeed()));
			setFacing(0);
		}
		if (key == KeyEvent.VK_D){
			if(!isAttacking()){
				/*                ImageIcon iRight = new ImageIcon("images/playerImages/guy/guyArmlessRight.png"); // character image
                setStill(iRight.getImage());
		ImageIcon iArmRight = new ImageIcon("images/playerImages/guy/armRight.png");
		setArmStill(iArmRight.getImage());
				 */
			}
			ImageIcon iRight = new ImageIcon
					("../images/playerImages/guy/guyArmlessRight.png");
			setStill(iRight.getImage());
			ImageIcon iArmRight = new ImageIcon
					("../images/playerImages/guy/armRight.png");
			setArmStill(iArmRight.getImage());

			setXVel(getSpeed());
			setFacing(1);
		}
		//if on ground, can jump
		if(!isInAir()){
			if(key == KeyEvent.VK_W){
				setYAcc(.5);
				setYVel((getJumpSpeed()));
				setInAir(true);    
			}
		}
		//WEAPON SWITCHING
		if(key == KeyEvent.VK_E){
		    switchWeapon();
		}
		
		if(key == KeyEvent.VK_Q){
		    switchWeaponBackward();
		}

		if(key == KeyEvent.VK_1){
		    if(weapon.get(0)!=null)
			currentWeapon = weapon.get(0);
		}
		if(key == KeyEvent.VK_2){
		    if(weapon.get(1)!=null)
			currentWeapon = weapon.get(1);
		}
		if(key == KeyEvent.VK_3){
		    if(weapon.get(2)!=null)
			currentWeapon = weapon.get(2);
		}
		if(key == KeyEvent.VK_4){
		    if(weapon.get(3)!=null)
			currentWeapon = weapon.get(3);
		}
		if(key == KeyEvent.VK_5){
		    if(weapon.get(4)!=null)
			currentWeapon = weapon.get(4);
		}
		if(key == KeyEvent.VK_6){
		    if(weapon.get(5)!=null)
			currentWeapon = weapon.get(5);
		}

	}

	//player movement input
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		//realease of L/R key's result in 0 horiz vel
		if (key == KeyEvent.VK_A)
			setXVel(0);

		if (key == KeyEvent.VK_D)
			setXVel(0);
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
        this.setJumpSpeed(jumpspeed);
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
    
        /**
     * @param JumpSpeed the JumpSpeed to set
     */
    public void setJumpSpeed(double JumpSpeed) {
        this.JumpSpeed = JumpSpeed;
    }

    /**
     * @return the HealthBarY
     */
    public double getHealthBarY() {
        return HealthBarY;
    }

    /**
     * @param HealthBarY the HealthBarY to set
     */
    public void setHealthBarY(double HealthBarY) {
        this.HealthBarY = HealthBarY;
    }


//RESOURCES SETTER/GETTERS

    public int getLogCount(){
	return logCount;
    }

    public void setLogCount(int logCount){
	this.logCount = logCount;
    }

    public int getCoinCount(){
	return coinCount;
    }

    public void setCoinCount(int coinCount){
	this.coinCount = coinCount;
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
     * @return the armStill
     */
    public Image getArmStill() {
        return armStill;
    }

    /**
     * @param armStill the armStill to set
     */
    public void setArmStill(Image armStill) {
        this.armStill = armStill;
    }

}
