import java.awt.*;
import java.awt.event.KeyEvent;
import static java.lang.Math.abs;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class Player {
    private int XCoord;
    private int XVel;
    private int YCoord;
    private int YVel;
    private int XAcc;
    private int YAcc;
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
    private int LeftBound;
    private int RightBound;
    private int TopBound;
    private int BotBound;
    private int WorldBot = 334;
    private int WorldLeft = 0;
    private int WorldRight = 1024;
    private int WorldTop = 0;
    
    public Player() {
	ImageIcon i = new ImageIcon("images/playerImages/guy/guySideDown.png"); //character image
	setStill(i.getImage());
        //starting Player postion/velocity/acceleration
	XCoord = 10;
	YCoord = WorldBot - VerticalSize;
        YAcc = 1;
        YVel = 0;
        XVel = 0;
        LeftBound = XCoord;
        RightBound = XCoord + HorizontalSize;
        TopBound = YCoord;
        BotBound = YCoord + VerticalSize;
        hp = 100;
        attack = 10;
        AttackSpeed = 20;
    }
    
    public void move() {
        //add velocities to positions/add gravity to yVel
	setXCoord(getXCoord() + getXVel());
	setYVel(getYVel() + getYAcc());
	setYCoord(getYCoord() + getYVel());
        
	//check boundaries, if on ground, InAir = false
	if((getYCoord() + VerticalSize) >= 334){
	    setYCoord(getWorldBot() - VerticalSize);
	    setYVel(0);
	    setInAir(false);
	}
        if(getXCoord() <= 0){
            setXCoord(0);
        }
        else if((getXCoord() + 50) >= 1024){
            setXCoord(1024 - 50);
        }
        
    }
    
    public void paintPlayer(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(this.getStill(), this.getXCoord(), this.getYCoord(), null);

	g.setColor(Color.white);
	g.fillRect(this.getXCoord() -10 , this.getYCoord() - 10, this.getHp(), 7);
	/*
        Font fntP = new Font("arial", Font.BOLD, 10);
        g.setFont(fntP);
        g.setColor(Color.blue);
        g.drawString("" + this.getHp(), this.getXCoord(), this.getYCoord() - 10);
	*/
    }
    
    public void AttackAnimation(Graphics g){
	
        if(isAttacking()){
	    ImageIcon iAttack = new ImageIcon("images/playerImages/guy/guySideUp.png"); 
	    setStill(iAttack.getImage());	    
            Graphics2D g2d = (Graphics2D) g;
	    if(getFacing() == 0){
		    
		Rectangle rect = new Rectangle(getXCoord() - 20, getYCoord() + 20, 20, 10);
		g2d.setColor(Color.blue);
		g2d.fill(rect);

		ImageIcon i = new ImageIcon("images/playerImages/guy/guySideUpLeft.png"); //character image
		setStill(i.getImage());


	    }
	    else if(getFacing() == 1){
		ImageIcon i = new 
		    ImageIcon("images/playerImages/guy/guySideUpRight.png");
		setStill(i.getImage());
		Rectangle rect = new Rectangle(getXCoord() + 57, getYCoord() + 20, 20, 10);
		g2d.setColor(Color.blue);
		g2d.fill(rect);
		
	    }
	    
	}
    }
    
    public void takeDmg(Enemies e){
        setHp(getHp() - e.getAttack());
    }
    
    public void PlayerAttack(ArrayList <Enemies> enemies){
        if(isAttacking()){
	  
            if(getFacing() == 0){
                for(Enemies e : enemies){
                    if(abs(getXCoord() - (e.getXCoord() + e.getHorizontalSize())) <= 20){
			setAttackSpeedCount(getAttackSpeedCount() + 1);
			if(getAttackSpeedCount() == getAttackSpeed()){
			    e.takeDmg(this);
			    setAttackSpeedCount(0);
			}
                    }
                }
            }
            else if(getFacing() == 1){
                for(Enemies e : enemies){
                    if(abs((getXCoord() + getHorizontalSize()) - e.getXCoord()) <= 20){
                        setAttackSpeedCount(getAttackSpeedCount() + 1);
			if(getAttackSpeedCount() == getAttackSpeed()){
			    e.takeDmg(this);
			    setAttackSpeedCount(0);
			}
                    }
                }
            }
        }
    }
    
    
    public void keyPressed(KeyEvent e) {
	int key = e.getKeyCode();
        //input cahnges velocity
	if (key == KeyEvent.VK_LEFT){
            ImageIcon iLeft = new ImageIcon("images/playerImages/guy/guySideDownLeft.png"); // character image
            setStill(iLeft.getImage());
	    setXVel(-3);
            setFacing(0);
        }
	if (key == KeyEvent.VK_RIGHT){
            ImageIcon iRight = new ImageIcon("images/playerImages/guy/guySideDown.png"); // character image
            setStill(iRight.getImage());
	    setXVel(3);
            setFacing(1);
        }
        //if on ground, can jump
        if(!isInAir()){
            if(key == KeyEvent.VK_UP){
                setYVel(-22);
                setInAir(true);    
            }
        }
        if(key == KeyEvent.VK_SPACE){
	    setAttacking(true);
	    if(getFacing() == 0){
		ImageIcon aLeft = new 
		    ImageIcon("images/playerImages/guy/guySideUpLeft.png");
		setStill(aLeft.getImage());

	    }

	    else{
		ImageIcon aRight = new 
		    ImageIcon("images/playerImages/guy/guySideUpRight.png"); 
		setStill(aRight.getImage());

	    }
	}
    }
    
    public void keyReleased(KeyEvent e) {
	int key = e.getKeyCode();
        //realease of L/R key's result in 0 horiz vel
	if (key == KeyEvent.VK_LEFT)
	    setXVel(0);
	
	if (key == KeyEvent.VK_RIGHT)
	    setXVel(0);
        if(key == KeyEvent.VK_SPACE){
            setAttacking(false);
	     if(getFacing() == 0){
		ImageIcon iLeft = new 
		    ImageIcon("images/playerImages/guy/guySideDownLeft.png");
		setStill(iLeft.getImage());
	    }
	    else if(getFacing() == 1){
		ImageIcon iRight = new 
		    ImageIcon("images/playerImages/guy/guySideDownRight.png");
		setStill(iRight.getImage());
	    }
	}
    }
    //-----------------Getters/Setters------------------------------------------------------------------------------------------------
    public int getXCoord() {
        return XCoord;
    }
    
    public void setXCoord(int XCoord) {
        this.XCoord = XCoord;
    }
    
    public int getXVel() {
        return XVel;
    }
    
    public void setXVel(int XVel) {
        this.XVel = XVel;
    }

    public int getYCoord() {
        return YCoord;
    }
    
    public void setYCoord(int YCoord) {
        this.YCoord = YCoord;
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

}
