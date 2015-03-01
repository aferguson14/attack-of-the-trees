import java.awt.*;
import java.awt.event.*;
import static java.lang.Math.abs;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class Board extends JPanel implements ActionListener {
    
    //private data

    //Objects
    Random randomGenerator = new Random();
    private Player p;
    private ArrayList <Enemies> enemies = new ArrayList<Enemies>();
    private ArrayList <Terrain> terrain = new ArrayList<Terrain>();
    private ArrayList <Resource> resources = new ArrayList<Resource>();
    private ArrayList <Enemies> boss = new ArrayList<Enemies>();
    private EnemyGenerator generator = new EnemyGenerator(this);
    
    //Background Images
    public Image farBackground;
    public Image nearBackground;
    public Image Far2, Far3;
    public Image Near2;
 
    //Resource Images
    public Image LogImage;
    public Image CoinImage;

    private Image img;
    private Timer time;
    private boolean attack = false;
    private Menu menu;
    private PauseMenu pmenu;
    //world dimensions
    private int WorldBot = 700;
    private int WorldLeft = 0;
    private int WorldRight = 7478;
    private int WorldTop = 0;
    public static Point MouseCoords;
    public static boolean PlayerAttack = false;
    private boolean StartLevel = true;
    private int level = 0;
    private LevelHandler lvlhandler = new LevelHandler();
    
        //states
    public static enum STATE {
    	MENU,
    	GAME,
	PAUSE,
        GAMEOVER
    };
    //initial state = MENU
    private static STATE State = STATE.MENU;
    
    
    public Board() {
        //creates player, enemies, terrain, weapon, menu, and background images
	p = new Player();
        lvlhandler.HandleLVL1Start(enemies, generator);

        Rock rock = new Rock(300, WorldBot - 100);
        Ramp ramp = new Ramp(700, WorldBot);
        Rock rock2 = new Rock(3000, WorldBot - 250);
        Ramp ramp2 = new Ramp(1000, WorldBot);

//        terrain.add(rock);
        terrain.add(ramp);
//        terrain.add(rock2);
        terrain.add(ramp2);
        
        for(int i = 0; i < enemies.size(); i++){
            enemies.get(i).setTerrainDimensions(terrain);
        }
        
	addKeyListener(new AL());
	menu = new Menu();
	pmenu = new PauseMenu();
        MouseInput m = new MouseInput();
	addMouseListener(m);
        addMouseMotionListener(m);
	setFocusable(true);
        //not stitched together, used multiple background images
	ImageIcon far = new 
	    ImageIcon("../images/backgrounds/far-background.png");
        
        farBackground = far.getImage();
        ImageIcon far2 = new ImageIcon("../images/backgrounds/far-background.png");
	ImageIcon near = new 
	    ImageIcon("../images/backgrounds/near-background.png");
        ImageIcon near2 = new ImageIcon("../images/backgrounds/near-background.png");
	nearBackground = near.getImage();
        ImageIcon far3 = new 
	    ImageIcon("../images/backgrounds/far-background.png");
        
        Far3 = far.getImage();
        Far2 = far2.getImage();
        Near2 = near2.getImage();
	
	//RESOURCE IMAGES
	ImageIcon logImage = new ImageIcon("../images/sourceImage/wood.png");
	LogImage = logImage.getImage();
	ImageIcon coinImage = new ImageIcon("../images/sourceImage/coin.png");
	CoinImage = coinImage.getImage();
	//TIME
	time = new Timer(5, this);
	time.start();

	//WEAPONS
        Gun g = new Gun(p.getXCoord(),p.getYCoord());
	p.AddWeapon(g);
    }

    
    public void actionPerformed(ActionEvent e) {
        //move player, move weapon

        getP().move(terrain);
        getP().getCurrentWeapon().move(getP());
        //check Player attacking
        if(Board.PlayerAttack){
            getP().setAttacking(true);
//            Board.MouseCoords
        }
        else{
            getP().setAttacking(false);
        }

        //if any enemies are below 0 health, delete
        if(boss.size() == 0){
            for(int i = 0; i < getEnemies().size(); i++){
                if(getEnemies().get(i).getHp() <= 0){
		    //Resource Drop
		    getEnemies().get(i).getResource().setXCoord
			(getEnemies().get(i).getXCoord());
		    getEnemies().get(i).getResource().setYCoord
			(getEnemies().get(i).getYCoord()+70);
       
		    resources.add(getEnemies().get(i).getResource());


                    getEnemies().remove(i);
                    if(lvlhandler.getProgress() < lvlhandler.getProgressNeeded()){
                        lvlhandler.setProgress(lvlhandler.getProgress() + 1);
                    }
                    if(lvlhandler.getProgress()<lvlhandler.getProgressNeeded()){
                        if(level == 0){
                        generator.updateLVL1Enemies(enemies);
                        enemies.get(enemies.size()-1).setTerrainDimensions(terrain);
                        } else if(level == 1){
                            generator.updateLVL2Enemies(enemies);
                            enemies.get(enemies.size() - 1).setTerrainDimensions(terrain);
                        }else{
                            generator.updateLVL3Enemies(enemies);
                            enemies.get(enemies.size() - 1).setTerrainDimensions(terrain);
                        }
                    } else if(enemies.size() > 0){

                    } else{
                        //boss stuff
                        EnemyGnome gnome = new EnemyGnome(2000, getWorldBot() - 115);
                        boss.add(gnome);
                    }
                }

            }
        }
        else{
                    if(boss.get(0).getHp() <= 0){
		    //Resource Drop **NEEDS TO BE UPDATED FOR MORE BOSSES**
		    boss.get(0).getResource().setXCoord
			(boss.get(0).getXCoord());
		    boss.get(0).getResource().setYCoord
			(boss.get(0).getYCoord()+70);
       
		    resources.add(boss.get(0).getResource());

                    boss.remove(0);
                    level++;
                    if(level == 1){
                    lvlhandler.HandleLVL2Start(enemies, generator);
                        for(int i = 0; i < enemies.size(); i++){
                            enemies.get(i).setTerrainDimensions(terrain);
                         }
                    }
                    else{
                        lvlhandler.HandleLVL3Start(enemies, generator);
                            for(int i = 0; i < enemies.size(); i++){
                                enemies.get(i).setTerrainDimensions(terrain);
                            }
                    }
                    }

        }

	//if Player runs over resource, collect
	for(int i = 0; i< getResources().size();i++){
	    
	    if((getP().getXCoord() >= getResources().get(i).getXCoord()-25) && 
	       (getP().getXCoord() <= getResources().get(i).getXCoord()+25) &&
	       (getP().getYCoord() <= getResources().get(i).getYCoord()+25) &&
	       (getP().getYCoord() >= getResources().get(i).getYCoord()-70) ){

		if(getResources().get(i).getResourceType() == "log")
		    getP().setLogCount(getP().getLogCount() + 1);
		else if(getResources().get(i).getResourceType() == "coin")
		    getP().setCoinCount(getP().getCoinCount() + 1);
	     /*	else if(getResources().get(i).getResourceType() == "coal")
		    getP().setCoalCount(getP().getCoalCount() + 1);
		else if(getResources().get(i).getResourceType() == "oil")
		    getP().setOilCount(getP().getOilCount() + 1);
		*/
		//**ABOVE IS TO BE UNCOMMENTED ONCE WE HAVE IMAGES**
		getResources().remove(i);
	    }
	}
		repaint();
    }
    
    public void paint(Graphics g) {
	//Player is painted last to make him in front of enemies
	super.paint(g);
	Graphics2D g2d = (Graphics2D) g;
	
        //background images
	g2d.translate((p.getXCoord()*-1)+300, 0); //+300 because of player pos.
	//above line changes where player appears on screen

	g2d.drawImage(farBackground, (int) p.getXCoord()/2*(-1), -1800, null);
        g2d.drawImage(Far3, (int) p.getXCoord()/2*(-1) + 4500, -1800, null);
        g2d.drawImage(Far3, (int) p.getXCoord()/2*(-1) + 9000, -1800, null);
	g2d.drawImage(nearBackground,0, -1300, null);
        g2d.drawImage(Far2,-4500, -1800,null);
        g2d.drawImage(Near2,-7473 , -1305,null);

        g.drawRect((int) (p.getXCoord() -280) , (int) p.getHealthBarY() + 40, 500, 30);
        g.setColor(Color.white);
        g.fillRect((int) (p.getXCoord() -280), (int) p.getHealthBarY() + 40, 
		  lvlhandler.getProgress() * (500/(lvlhandler.getProgressNeeded())), 30);

	if(getState() == STATE.GAME || getState() == STATE.PAUSE) {

            //paint terrain
            for(Terrain t : terrain){
                t.paintTerrain(g, getP(), enemies);
            }
            //perform player attack, perform enemy AI
            if(getP().isAttacking()){
                getP().PlayerAttack(g);
            }
	    for(Enemies e : getEnemies()){
		e.AI(getP(), g, terrain, enemies);
		e.paintEnemy(getP(), g);
	    }
            for(Enemies b : boss){
                b.AI(getP(), g, terrain, enemies);
		b.paintEnemy(getP(), g);
            }
            for(Terrain t : terrain){
                t.paintTerrain(g, getP(), enemies);
            }
	    //Paint resources
	    for(Resource r : getResources()){
		r.paintResource(g);
	    }

	    getP().AttackAnimation(g);
	    if(getState() == STATE.PAUSE){
	   	pmenu.requestFocusInWindow();
	    }
	    
	    //RESOURCE BAR
	    Stroke oldStroke = g2d.getStroke();
	    Font fnt0 = new Font("arial", Font.BOLD, 25);
	    g.setFont(fnt0);
	    g.setColor(Color.white);

	    g2d.drawImage(LogImage, (int)getP().getXCoord()+950, 50, null);
	    g.drawString("x" + getP().getLogCount()+"", (int) getP().getXCoord()+970, 50);	
	    g2d.drawImage(CoinImage, (int)getP().getXCoord()+1050, 50, null);
	    g.drawString("x" + getP().getCoinCount()+"", (int) getP().getXCoord()+1070, 50);	

	    g2d.setStroke(oldStroke);

	    
            //paint player and weapon
	    getP().paintPlayer(g);
            if(getEnemies().size() > 0){
            getP().getCurrentWeapon().paintWeapon(g, getP(), getEnemies());
            } else if(boss.size() > 0){
                getP().getCurrentWeapon().paintWeapon(g, getP(), boss);
            }

	    //ATTACK ANIMATION
	    getP().AttackAnimation(g);
	    if(getState() == STATE.PAUSE){
	   	pmenu.requestFocusInWindow();
		int brightness = (int)(256 - 256 * 0.5f);
		g.setColor(new Color(0,0,0,brightness));
		g.fillRect((int)getP().getXCoord()-1000, 0, 7478, 1000);
	    }
    	}
        //if State!=Game, perform other State actions
	
	else if(getState() == STATE.MENU) {
	    getMenu().render(g);
    	}
    }

    //User Key Input adapter
    private class AL extends KeyAdapter {
	public void keyReleased(KeyEvent e) {
	    getP().keyReleased(e);
	}
	
	public void keyPressed(KeyEvent e) {
	    getP().keyPressed(e);
	    pmenu.keyPressedMenu(e);
	}
    }

    
    
    //------------------------------Getters/Setters---------------------------[
    /**
     * @return the p
     */
    public Player getP() {
        return p;
    }

    /**
     * @param p the p to set
     */
    public void setP(Player p) {
        this.p = p;
    }

    /**
     * @return the enemies
     */
    public ArrayList <Enemies> getEnemies() {
        return enemies;
    }

    /**
     * @param enemies the enemies to set
     */
    public void setEnemies(ArrayList <Enemies> enemies) {
        this.enemies = enemies;
    }

    public ArrayList <Resource> getResources() {
	return resources;
    }

    public void setResources(ArrayList <Resource> resources){
	this.resources = resources;
    }

    /**
     * @return the img
     */
    public Image getImg() {
        return img;
    }

    /**
     * @param img the img to set
     */
    public void setImg(Image img) {
        this.img = img;
    }

    /**
     * @return the time
     */
    public Timer getTime() {
        return time;
    }

    /**
     * @param time the time to set
     */
    public void setTime(Timer time) {
        this.time = time;
    }

    /**
     * @return the attack
     */
    public boolean isAttack() {
        return attack;
    }

    /**
     * @param attack the attack to set
     */
    public void setAttack(boolean attack) {
        this.attack = attack;
    }

    /**
     * @return the menu
     */
    public Menu getMenu() {
        return menu;
    }

    /**
     * @param menu the menu to set
     */
    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    /**
     * @return the pmenu
     */
    public PauseMenu getPmenu() {
        return pmenu;
    }

    /**
     * @param pmenu the pmenu to set
     */
    public void setPmenu(PauseMenu pmenu) {
        this.pmenu = pmenu;
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
     * @return the MouseCoords
     */
    public Point getMouseCoords() {
        return MouseCoords;
    }

    /**
     * @param MouseCoords the MouseCoords to set
     */
    public void setMouseCoords(Point MouseCoords) {
        this.MouseCoords = MouseCoords;
    }
    
        /**
     * @return the State
     */
    public static STATE getState() {
        return State;
    }

    /**
     * @param aState the State to set
     */
    public static void setState(STATE aState) {
        State = aState;
    }

}
