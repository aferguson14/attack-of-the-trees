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
    private ArrayList<Terrain> terrains = new ArrayList<Terrain>();
    private ArrayList<Double> tops = new ArrayList<Double>();
    private boolean startAttacking = false;
    private boolean cantMove = false;
    private double lastCoord1 = 0, lastCoord2;
    private boolean startedJump = false;
    private Resource resource;
    
    //Constructor
    public Enemies(double x, double y){
        XCoord = x;
        YCoord = y;
    }

    //Move Method, Similar to Player's
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
    public boolean checkSpeed(){
        if((abs(getLastCoord2() - getLastCoord1()) < abs(this.getSpeed())) && !isAttacking() && !isInAir()){
            return true;
        }
        else{
            return false;
        }
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
    public void AI(Player p, Graphics g, ArrayList<Terrain> terrain, ArrayList<Enemies> enem){}
    public void dropItem(){}
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
     public void CheckEnemyContact(Enemies e){
         double speedDif = 0;
         if((e.getSpeed()) < (this.getSpeed())){
             speedDif = this.getSpeed();
             
         }      
         else{
             speedDif = e.getSpeed();
         }
         //checks coords of enemy and if in contact with left side of terrain
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
         //check if coords of enemy in contact with Right side of terrain
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
//         //check if coords in contact with top of terrain
//         else if((startedJump == false) && ((((e.getYCoord() + e.getVerticalSize()) > this.getYCoord()) 
//                 && (e.getYCoord() < this.getYCoord())))
//                 && (((e.getXCoord() > this.getXCoord()) 
//                    && ((e.getXCoord()) < (this.getXCoord() + this.getHorizontalSize())))
//                 || ((((e.getXCoord() + e.getHorizontalSize()) 
//                 > this.getXCoord()) && ((e.getXCoord() + 
//                    e.getHorizontalSize()) < (this.getXCoord() + this.getHorizontalSize())))))){
//             //adjust coords anf velocities accordingly
//                
//                e.setYCoord(this.getYCoord() - e.getVerticalSize());
//                e.setYVel(0);
//                e.setYAcc(0);
//                e.setStartedJump(false);
//             
//         }
//         //check if enemy coords in contact with bot of terrain
//        else if((((e.getYCoord()) < (this.getYCoord() + this.getVerticalSize())) 
//                && (e.getYCoord() + e.getVerticalSize() >
//                (this.getYCoord() + this.getVerticalSize())))
//                 && (((e.getXCoord() > this.getXCoord()) 
//                && ((e.getXCoord()) < (this.getXCoord() + this.getHorizontalSize())))
//                 || ((((e.getXCoord() + e.getHorizontalSize()) 
//                > this.getXCoord()) && ((e.getXCoord() + e.getHorizontalSize())
//                < (this.getXCoord() + this.getHorizontalSize())))))){
//            
//            //adjust velocities accordingly
//                e.setYVel(-1 * e.getYVel());
//            
//         }
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
     public void setTerrainDimensions(ArrayList <Terrain> ter){
         this.setTerrains(ter);
         for(int i = 0; i < terrains.size(); i++){
             getTops().add(i, terrains.get(i).getTop());
         }
     }
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

}
