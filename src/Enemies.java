
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.Serializable;
import static java.lang.Math.abs;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/** 
 * Enemies represents the various enemies the player encounters.
 * Abstract class that the concrete enemies will derive from.
 * 
 * @author Shadee Barzin
 * @author Andrew Ferguson
 * @author Michele Haque
 * @author Brendan Murphy
 * @author Fengyu Wang
 * @version CS 48, Winter 2015
*/

public abstract class Enemies implements Serializable{
	//Private Data
	private double XCoord, YCoord;
	private double XVel, YVel;
	private double XAcc, YAcc;
	private ImageIcon still;
	private ImageIcon stillRight;
	private boolean InAir;
	private boolean IsAttacking;
	private int HorizontalSize, VerticalSize;
	//World dimensions
	private int WorldBot = 700;
	private int WorldLeft = 0;
	private int WorldRight = 7478;
	private int WorldTop = 0;
	//More private data
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
	private ArrayList<Terrain> terrains = new ArrayList<Terrain>();
	private ArrayList<Double> tops = new ArrayList<Double>();
	private boolean startAttacking = false;
	private boolean cantMove = false;
	private double lastCoord1 = 0, lastCoord2;
	private double lastCoord1Y = 0, lastCoord2Y;
	private boolean startedJump = false;
	private Resource resource;
	private double angle = 0;
	private int totalHp;

	/**
	*@param x double
	*@param y double
	*Constructor, place an enemy at a specific coordinate
	*/
	public Enemies(double x, double y){
		XCoord = x;
		YCoord = y;
	}
	/**
	*@param terrain ArrayList <Terrain>
	*@param enem ArrayList <Enemies>
	*adjust coordinates based on velocity, adjust velocity based on acceleration
	*check contact with world/terrain
	*/
	public void move(ArrayList <Terrain> terrain, ArrayList <Enemies> enem){
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
			for(int i = 0; i < terrains.size(); i++){
				terrains.get(i).CheckEnemyContact(this, i);
			}
			//checks contact with enemies
			for(Enemies en : enem){
				this.CheckEnemyContact(en);
			}

			//check world contacts
			if((this.getYCoord() + this.getVerticalSize()) >= getWorldBot()){
				setYCoord(getWorldBot() - getVerticalSize());
				setYVel(0);
				setInAir(false);
			}
			if(getXCoord() <= getWorldLeft()){
				setXCoord(0);
			}
			if((this.getXCoord() + this.getHorizontalSize()) >= getWorldRight()){
				setXCoord(WorldRight = getHorizontalSize());
			}

			//set the last coordinate for future checking
			setLastCoord2(getLastCoord1());
			setLastCoord1(getXCoord());
			setLastCoord2Y(getLastCoord1Y());
			setLastCoord1Y(getYCoord());
		}
	}
	/**
	*@return boolean
	*if current coord and last coord are equal, and not attacking, return true
	*basically, if not moving and not attacking return true
	*/
	public boolean checkMove(){
		if((getLastCoord1() <= getLastCoord2()) && (getLastCoord1() 
				>= getLastCoord2()) && !attacking && !isInAir()){
			return true;
		}
		return false;
	}
	/**
	*@return boolean
	*if the enemy is slower than his normal speed, return true
	*/
	public boolean checkSpeed(){
		if((abs(getLastCoord2() - getLastCoord1()) < abs(this.getSpeed())) && !isAttacking() && !isInAir()){
			return true;
		}
		else{
			return false;
		}
	}
	/**
	*@return boolean
	*if the enemies last coordinate and current coordinate are the same, return true
	*/
	public boolean checkInAirMove(){
		if(checkMove() && (getLastCoord1Y() <= getLastCoord2Y() && (getLastCoord1Y() 
				>= getLastCoord2Y()))){
			return true;
		}
		else{
			return false;
		}
	}
	/**
	 * @param p Playerr
	 *@param g Graphics
	*implemented in subclasses
	*/
	public void Attack(Player p, Graphics g){}
	
	/**
	*@param p Player
	*subtract health by player's attack dmg
	*/
	public void takeDmg(Player p){
		hp -= p.getAttack();
		if(hp <= 0){
			die();
		}
	}
	/**
	*@param dmg int
	*subtract health by an integer
	*/
	public void takeDmg(int dmg){
		hp -= dmg;
		if(hp <= 0){
			die();
		}
	}
	
	/**
	@param p Player
	@param g Graphics
	@param terrain ArrayList<Terrain>
	@param enem ArrayList<Enemies>
	*implemented in subclasses
	*/
	public void AI(Player p, Graphics g, ArrayList<Terrain> terrain, ArrayList<Enemies> enem){}
	/**
	*implemented in subclasses
	*/
	public void dropItem(){}
	/**
	*implemented in subclasses
	*/
	public void die(){}

	/**
	*@param p Player
	*@param g Graphics
	*Paints image based on facing direction
	*/
	public void paintEnemy(Player p , Graphics g){
		//paint enemy based on direction
		Graphics2D g2d = (Graphics2D) g;
		if (getFacing() < 1){
			g2d.drawImage(this.getStill().getImage(), (int) this.getXCoord(), (int) this.getYCoord(), null);
		}
		else if (getFacing() >= 1){
			g2d.drawImage(this.getStillRight().getImage(), (int) this.getXCoord(), (int) this.getYCoord(), null);	
		}
		
		//paint health bar
		g.setColor(Color.red);
		g.fillRect((int) (this.getXCoord() -10) , (int) (this.getYCoord() - 10), (int) ((double)this.getHp() * (double)(100/(double)this.getTotalHp())) , 7);        
		//paints it's projectiles
		paintProjectile(p, g);
		//deletes and projectiles that need to be deleted
		deleteProjectiles();
	}
	/**
	*@param p Player
	*@param g Graphics
	*move enemies projectiles, then paint them
	*/
	public void paintProjectile(Player p, Graphics g){
		for(Projectile proj : this.getProjectiles()){
			proj.move(p);
		}
		for(Projectile proj : this.getProjectiles()){
			proj.paintImage(g);
		}
	}
	/**
	*@param p Player
	*@return boolean
	*Check if the player is within the Enemies attack range
	*/
	public boolean checkInRange(Player p){
		//if player right side within enemy's range, return true
		if(facing == 0){
			if(abs((p.getXCoord() + p.getHorizontalSize()) - XCoord) 
					<= AttackRange){
				return true;
			}
		}
		//if player's xcoord is within enemy range, return true
		else if(facing == 1){
			if(abs((XCoord + HorizontalSize) - p.getXCoord()) <= AttackRange){
				return true;
			}
		}
		//else return false
		return false;
	}
	/**
	*if any projectiles need to be removed, remove them
	*/
	public void deleteProjectiles(){
		for(Projectile proj : projectiles){
			if(proj.isRemove()){
				projectiles.remove(proj); 
				break;
			}
		}
	}
	/**
	*implemented in subclasses
	*/
	public abstract void print();
	/**
	*@param p Point
	*@retrun double
	*finds the angle between enemy position and a given point
	*/
	public double findAngle(Point p){
		return Math.sinh((this.getYCoord() - p.getY()) 
				/ p.distance(this.getXCoord(), this.getYCoord()));
	}
	/**
	*implemented in subclasses
	*/
	public void attackAnimation(Graphics g){}
	/**
	*@param p Projectile
	*adds a Projectile object to the projectiles arraylist
	*/
	public void addProjectile(Projectile p){
		projectiles.add(p);
	}
	/**
	*@param index int
	*removes a Projectile from projectiles arraylist at an index
	*/
	public void deleteProjectile(int index){
		projectiles.remove(index);
	}
	/**
	*@param p Projectile
	*removes a Projectile from projectiles arraylist based on the object
	*/
	public void deleteProjectile(Projectile p){
		projectiles.remove(p);
	}
	/**
	*@param e Enemies
	*Check if the Enemy is in contact with another enemy
	*/
	public void CheckEnemyContact(Enemies e){
		double speedDif = 0;
		//gets the largest speed difference
		if((e.getSpeed()) < (this.getSpeed())){
			speedDif = this.getSpeed();

		}      
		else{
			speedDif = e.getSpeed();
		}
		//checks coords of enemy and if in contact with left side of Enemy
		if((startedJump == false) &&(((e.getXCoord() + e.getHorizontalSize() + speedDif) >= this.getXCoord()) && 
				(e.getXCoord() + e.getHorizontalSize() <=
				this.getXCoord() + speedDif))
				&& (((e.getYCoord() >= this.getYCoord())
						&& ((e.getYCoord()) <= (this.getYCoord() + this.getVerticalSize())))
						|| ((((e.getYCoord() + e.getVerticalSize()) 
								>= this.getYCoord()) && ((e.getYCoord() + e.getVerticalSize()) 
										<= (this.getYCoord() + this.getVerticalSize()) + 1)))
										|| ((this.getYCoord() >= e.getYCoord()) && ((this.getYCoord() + this.getVerticalSize()) 
												<= e.getYCoord() + e.getVerticalSize())))
				){
			//set enemy coords accordingly
			e.setXCoord(this.getXCoord() - e.getHorizontalSize() - e.getSpeed()); 
		}
		//check if coords of enemy in contact with Right side of Enemy
		else if((startedJump == false) && (((e.getXCoord() - speedDif) <= (this.getXCoord() 
				+ this.getHorizontalSize()))
				&& (e.getXCoord() >= this.getXCoord() + 
				this.getHorizontalSize() - speedDif))
				&& (((e.getYCoord() >= this.getYCoord()) 
						&& ((e.getYCoord()) <= this.getYCoord() + this.getVerticalSize()))
						|| ((((e.getYCoord() + e.getVerticalSize()) >= this.getYCoord()) 
								&& ((e.getYCoord() + e.getVerticalSize()) <= (this.getYCoord() + this.getVerticalSize() + 1))))
								|| ((this.getYCoord() >= e.getYCoord()) && ((this.getYCoord() + this.getVerticalSize())
										<= e.getYCoord() + e.getVerticalSize())))
				){
			//adjust coords accordingly
			e.setXCoord(this.getXCoord() + this.getHorizontalSize() + e.getSpeed()); 
		}
		//if not in contact, if acc == 0, set to .5
		//(if on ground will be reset in move)
		else{
			if(e.getYAcc() == 0)
				e.setYAcc(.5);
			if(abs(getYVel()) > 0){
				setInAir(true);
			}
		}
	}
	/**
	*@param ter ArrayList <Terrain>
	*sets the top dimension of the terrain in the terrains arraylist
	*/
	public void setTerrainDimensions(ArrayList <Terrain> ter){
		this.setTerrains(ter);
		for(int i = 0; i < terrains.size(); i++){
			getTops().add(i, terrains.get(i).getTop());
		}
	}
	/**
	*Updates the top dimension of the terrain in the terrains arraylist
	*/
	public void updateTerrainDimensions(){
		for(int i = 0; i < terrains.size(); i++){
			terrains.get(i).UpdateSides(this, i);
		}
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

	public ImageIcon getStill() {
		return still;
	}

	public void setStill(ImageIcon still) {
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

	/**
	 * @return the startedJump
	 */
	public boolean isStartedJump() {
		return startedJump;
	}

	/**
	 * @param startedJump the startedJump to set
	 */
	public void setStartedJump(boolean startedJump) {
		this.startedJump = startedJump;
	}

	/**
	 * @return the terrain
	 */
	public ArrayList<Terrain> getTerrains() {
		return terrains;
	}

	/**
	 * @param terrain the terrain to set
	 */
	public void setTerrains(ArrayList<Terrain> terrain) {
		this.terrains = terrain;
	}


	/**
	 * @return the tops
	 */
	public ArrayList<Double> getTops() {
		return tops;
	}

	/**
	 * @param tops the tops to set
	 */
	public void setTops(ArrayList<Double> tops) {
		this.tops = tops;
	}

	public Resource getResource(){
		return resource;
	}

	public void setResource(Resource resource){
		this.resource = resource;
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
	 * @return the totalHp
	 */
	public int getTotalHp() {
		return totalHp;
	}

	/**
	 * @param totalHp the totalHp to set
	 */
	public void setTotalHp(int totalHp) {
		this.totalHp = totalHp;
	}

	/**
	 * @return the lastCoord2Y
	 */
	public double getLastCoord2Y() {
		return lastCoord2Y;
	}

	/**
	 * @param lastCoord2Y the lastCoord2Y to set
	 */
	public void setLastCoord2Y(double lastCoord2Y) {
		this.lastCoord2Y = lastCoord2Y;
	}

	/**
	 * @return the lastCoord1Y
	 */
	public double getLastCoord1Y() {
		return lastCoord1Y;
	}

	/**
	 * @param lastCoord1Y the lastCoord1Y to set
	 */
	public void setLastCoord1Y(double lastCoord1Y) {
		this.lastCoord1Y = lastCoord1Y;
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

}
