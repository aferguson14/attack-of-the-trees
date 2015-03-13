import java.awt.*;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import static java.lang.Math.abs;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;
import javax.sound.sampled.*;
//import javazoom.jl.player.Player;

/** 
 * Board represents the panel where all graphics are displayed and the game is played.
 * 
 * @author Shadee Barzin
 * @author Andrew Ferguson
 * @author Michele Haque
 * @author Brendan Murphy
 * @author Fengyu Wang
 * @version CS 48, Winter 2015
*/

public class Board extends JPanel implements ActionListener {
    //private data
    //Objects
    Random randomGenerator;
    private Player p;
    private ArrayList <Enemies> enemies;
    private ArrayList <Terrain> terrain;
    private ArrayList <Resource> resources;
    private ArrayList <Enemies> boss;
    private EnemyGenerator generator;
    private int TotalProgress;

    //Background ../images
    public Image farBackground;
    public Image nearBackground;
    public Image Far2, Far3;
    public Image Near2;
    public Image BlackFiller;
    public Image BlackFillerLarge;
    //Grass ../images
    public Image Grass1;
    public Image Grass2;
    public Image Grass3;
    public Image DirtBlock;
    //Resource ../images
    public Image LogImage;
    public Image CoinImage;
    public Image HeartImage;
    public Image BlueHeartImage;
    //Weapon ../images
    public Image SwordImage;
    public Image AxeImage;
    public Image StickImage;
    public Image GunImage;
    public Image BowImage;

    private Image img;
    private Timer time;
    private boolean attack = false;
    private Menu menu;
    private PauseMenu pmenu;
    private static WinScreen winscreen;
    private static GameOverScreen goscreen;
    //world dimensions
    private int WorldBot = 700;
    private int WorldLeft = 0;
    private int WorldRight = 7478;
    private int WorldTop = 0;
    public static Point MouseCoords;
    public static Point BoardLoc;
    public static boolean PlayerAttack = false;
    private boolean StartLevel = true;
    private int level = 0;
    private LevelHandler lvlhandler;
    //    public Sound backgroundMusic;
    public MP3 backgroundMusic;

    //states
    public static enum STATE {
    	MENU,
	GAME,
	PAUSE,
	GAMEOVER,
	LOAD
    };
    //initial state = MENU
    private static STATE State = STATE.MENU;
    
    /** 
     * Constructor
     */    
    public Board() {
	randomGenerator = new Random();
        //creates player, enemies, terrain, weapon, menu, and background ../images
	p = new Player();
	backgroundMusic = new MP3("../sound/backgroundMusic.mp3");
	backgroundMusic.play();

	enemies = new ArrayList<Enemies>();
	terrain = new ArrayList<Terrain>();
	resources = new ArrayList<Resource>();
	boss = new ArrayList<Enemies>();
	TotalProgress = 0;
	lvlhandler = new LevelHandler();
	generator = new EnemyGenerator(this);
        lvlhandler.HandleLVL1Start(enemies, terrain, generator);
        
        for(int i = 0; i < enemies.size(); i++){
            enemies.get(i).setTerrainDimensions(terrain);
        }
        p.setTerrains(terrain);
        
	addKeyListener(new AL());
	menu = new Menu();
        pmenu = new PauseMenu(p, this);
        winscreen = new WinScreen(this);
        goscreen = new GameOverScreen(this);
        MouseInput m = new MouseInput();
        addMouseListener(m);
        addMouseMotionListener(m);
        setFocusable(true);
        //not stitched together, used multiple background ../images
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
	
	ImageIcon blackFiller = new ImageIcon("../images/backgrounds/blackFiller.png");
	ImageIcon blackFillerLarge = new ImageIcon("../images/backgrounds/blackFillerLarge.png");
	BlackFiller = blackFiller.getImage();
	BlackFillerLarge = blackFillerLarge.getImage();

	ImageIcon grass1 = new ImageIcon("../images/grassImages/grass1.png");
	ImageIcon grass2 = new ImageIcon("../images/grassImages/grass2.png");
	ImageIcon grass3 = new ImageIcon("../images/grassImages/grass3.png");

	Grass1 = grass1.getImage();
	Grass2 = grass2.getImage();
	Grass3 = grass3.getImage();

	ImageIcon dirt = new ImageIcon("../images/grassImages/dirtNew.png");
	DirtBlock = dirt.getImage();

	//RESOURCE IMAGES
	ImageIcon logImage = new ImageIcon("../images/sourceImage/wood.png");
	LogImage = logImage.getImage();
	ImageIcon coinImage = new ImageIcon("../images/sourceImage/coin.png");
	CoinImage = coinImage.getImage();
	ImageIcon heartImage = new ImageIcon("../images/sourceImage/heart.png");
	HeartImage = heartImage.getImage();
	ImageIcon blueHeartImage = new ImageIcon ("../images/sourceImage/blueHeart.png");
	BlueHeartImage = blueHeartImage.getImage();
	//TIME
	time = new Timer(5, this);
	time.start();

	//WEAPON ../images
	ImageIcon axeImage = new ImageIcon("../images/weaponImage/axe.png");
	AxeImage = axeImage.getImage();
	ImageIcon swordImage = new ImageIcon("../images/weaponImage/sword.png");
	SwordImage = swordImage.getImage();
	ImageIcon stickImage = new ImageIcon("../images/weaponImage/stick.png");
	StickImage = stickImage.getImage();
	ImageIcon gunImage = new ImageIcon("../images/weaponImage/gun.png");
	GunImage = gunImage.getImage();
	ImageIcon bowImage = new ImageIcon("../images/weaponImage/bowRight.png");
	BowImage = bowImage.getImage();

	//WEAPONS
	Gun w = new Gun(0,0);//temp holder to intialize arrayList
	w.setWeaponType("empty");
	for(int i=0;i<7;i++){ //7
	p.AddWeapon(i, w);
	}

	Stick s = new Stick(p.getXCoord(),p.getYCoord());
	p.AddWeapon(0, s);
	/*
	Sword sw = new Sword(p.getXCoord(),p.getYCoord());
	p.AddWeapon(1, sw);
	Axe a = new Axe(p.getXCoord(),p.getYCoord());
	p.AddWeapon(2, a);
	Gun g = new Gun(p.getXCoord(),p.getYCoord());
	p.AddWeapon(3, g);
	Bow b = new Bow(p.getXCoord(),p.getYCoord());
	p.AddWeapon(4, b);//index in arraylist*/
	p.setCurrentWeapon(s);
}

    /** 
     * @param e ActionEvent 
     * Takes in Mouse actions and converts them into actions the program will perform.
     * The system will perform specific actions based on the state of the game.
     */        
    public void actionPerformed(ActionEvent e) {
         //move player, move weapon
	//backgroundMusic.play();
	//stickSound.play();

	if(getState() == STATE.PAUSE){
	    getP().setXVel(0);
	    getP().setYVel(0);
	} //fixes the player moving after resuming
        
        if(getState() == STATE.GAME){
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
                        if(level == 0){
                            generator.createLVL1Boss(boss);
                            for(int x = 0; x < boss.size(); x++){
                                boss.get(x).setTerrainDimensions(terrain);
                             }
                        }
                        else if(level ==1){
                            generator.createLVL2Boss(boss);
                            for(int x = 0; x < boss.size(); x++){
                                boss.get(x).setTerrainDimensions(terrain);
                             }
                        }else{
                            generator.createLVL3Boss(boss);
                            for(int x = 0; x < boss.size(); x++){
                                boss.get(x).setTerrainDimensions(terrain);
                             }
                        }
                    }
                }
            }
        }
        else{
            for(int y = 0; y < boss.size(); y++){
                if(boss.get(y).getHp() <= 0){
                    boss.get(y).getResource().setXCoord
			(boss.get(y).getXCoord());
		    boss.get(y).getResource().setYCoord
			(boss.get(y).getYCoord()+70);
       
		    resources.add(boss.get(y).getResource());

                    boss.remove(y);
                }
            }
                if(boss.isEmpty()){
                    TotalProgress += lvlhandler.getProgress();
		    //Resource Drop **NEEDS TO BE UPDATED FOR MORE BOSSES**
		    
                    level++;
                    if(level == 1){
                    lvlhandler.HandleLVL2Start(enemies, terrain, generator);
                        for(int i = 0; i < enemies.size(); i++){
                            enemies.get(i).setTerrainDimensions(terrain);
                         }
                    }
                    else{
                        lvlhandler.HandleLVL3Start(enemies, terrain, generator);
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

		if(getResources().get(i).getResourceType().equals("log"))
		    getP().setLogCount(getP().getLogCount() + 1);
		else if(getResources().get(i).getResourceType().equals("coin"))
		    getP().setCoinCount(getP().getCoinCount() + 1);
		else if(getResources().get(i).getResourceType().equals("heart")){
		    if(getP().getHp()>=80)
			getP().setHp(100);
		    else
			getP().setHp(getP().getHp()+20);
		}
		else if(getResources().get(i).getResourceType().equals("blueHeart")){
		    getP().setHp(100);
		}
	     /*	else if(getResources().get(i).getResourceType() == "coal")
		    getP().setCoalCount(getP().getCoalCount() + 1);
		else if(getResources().get(i).getResourceType() == "oil")
		    getP().setOilCount(getP().getOilCount() + 1);
		*/
		//**ABOVE IS TO BE UNCOMMENTED ONCE WE HAVE IMAGES**
		getResources().remove(i);
	    }
	}
}
		repaint();
    }

    /** 
     * @param g Graphics
     * Paints the background, player, enemies, terrain, weapons, etc. onto screen.
     */            
    public void paint(Graphics g) {
	//Player is painted last to make him in front of enemies
	super.paint(g);
	Graphics2D g2d = (Graphics2D) g;
	
	if(getState() == STATE.MENU) {
	    getMenu().render(g);
	}
	else{
	        if (getState() == STATE.LOAD) {
			try {
				FileInputStream fs = new FileInputStream("saved.ser");
				ObjectInputStream os = new ObjectInputStream(fs);
				p = (Player) os.readObject();
				enemies = (ArrayList<Enemies>) os.readObject();
				resources = (ArrayList<Resource>) os.readObject();
				lvlhandler = (LevelHandler) os.readObject();
				pmenu = new PauseMenu(p, this);
				((ShopPanel) pmenu.getShop()).setWeaponsBought((int[]) os.readObject());
				TotalProgress = os.readInt();
				level = os.readInt();
				os.close();
				setState(STATE.GAME);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			// repaint();
		}
	        BoardLoc = this.getLocationOnScreen();
		
	        //background ../images
		g2d.translate((p.getXCoord()*-1)+600, 0); //+600 because of player pos.
		//above line changes where player appears on screen
	
		g2d.drawImage(farBackground, (int) p.getXCoord()/2*(-1), -1800, null);
		g2d.drawImage(Far3, (int) p.getXCoord()/2*(-1) + 4500, -1800, null);
		g2d.drawImage(Far3, (int) p.getXCoord()/2*(-1) + 9000, -1800, null);
		g2d.drawImage(nearBackground,0, -1300, null);
		g2d.drawImage(Far2,-4500, -1800,null);
		g2d.drawImage(Near2,-7473 , -1305,null);
		g2d.drawImage(BlackFiller, -600, 0, null);
		g2d.drawImage(BlackFillerLarge, 7050, 0, null);
		Color prev = g.getColor();
		Color greyTransp = new Color(70, 70, 70, 150);
	
		if(getState() == STATE.GAME || getState() == STATE.PAUSE) {
	         if (TotalProgress > 20){
				setState(STATE.GAMEOVER);
			    }
		//Grass Images
		/*int q=0; //this is for the dirt under grass
		while(q<7478){ //188x29
		    g2d.drawImage(DirtBlock, q, 720, null);
		    q+=324;
		}
		*/
		 int q=-20;
		 while(q<7478){ //188x29
		    g2d.drawImage(DirtBlock, q, 690, null);
		    q+=190;
		 }

		q = 0;
		while(q<7478){
		    g2d.drawImage(Grass3, q+73, 657, null);
		    g2d.drawImage(Grass1, q, 657, null); //657
		    q+=75;
		    q+=28;
		    g2d.drawImage(Grass2, q, 658, null);
		    q+=80;
		}
	
	
	
	        g.setColor(greyTransp);
		g.fillRect((int)p.getXCoord()-300, (int) p.getHealthBarY()-20 , 750, 120);
		g.setColor(Color.blue);
	                    
	        g.drawRect((int) (p.getXCoord() -280), (int) p.getHealthBarY() + 40, lvlhandler.getProgressNeededLVL1() * (700/(lvlhandler.getTotalProgressNeeded())), 30);
	        g.drawRect((int) (p.getXCoord() -280) + (lvlhandler.getProgressNeededLVL1()* (700/(lvlhandler.getTotalProgressNeeded()))), (int) p.getHealthBarY() + 40, lvlhandler.getProgressNeededLVL2() * (700/(lvlhandler.getTotalProgressNeeded())), 30);
	        g.drawRect((int) (p.getXCoord() -280)  + (lvlhandler.getProgressNeededLVL1()* (700/(lvlhandler.getTotalProgressNeeded()))) + (lvlhandler.getProgressNeededLVL2()* (700/(lvlhandler.getTotalProgressNeeded()))), (int) p.getHealthBarY() + 40, lvlhandler.getProgressNeededLVL3() * (700/(lvlhandler.getTotalProgressNeeded())), 30);
	        g.setColor(Color.white);
	        g.fillRect((int) (p.getXCoord() -280), (int) p.getHealthBarY() + 40, 
			  (lvlhandler.getProgress() + TotalProgress) * (700/(lvlhandler.getTotalProgressNeeded())), 30);
	        g.drawRect((int) (p.getXCoord() -280), (int) p.getHealthBarY() + 80, 700, 10);
	        g.setColor(Color.YELLOW);
	        g.fillRect((int) (p.getXCoord() -280), (int) p.getHealthBarY() + 80, 
			  p.getCurrentWeapon().getAttackSpeedTimer() * (700/p.getCurrentWeapon().getAttackSpeed()), 10);
	            //paint terrain
	            
	            //perform player attack, perform enemy AI
	            if(getP().isAttacking()){
	                getP().PlayerAttack(g);
	            }
	            else{
	                getP().getCurrentWeapon().setAttackSpeedTimer(0);
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
	                for(int i = 0; i < enemies.size(); i++){
	                    t.paintTerrain(g, getP(),enemies, enemies.get(i).getProjectiles(), p.getCurrentWeapon().getProjectiles());
	                }
	                for(int i = 0; i < boss.size(); i++){
	                    t.paintTerrain(g, getP(),boss, boss.get(i).getProjectiles(), p.getCurrentWeapon().getProjectiles());
	                }
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
		    int resourceBarX = (int)getP().getXCoord()+630;
				
				g.setColor(greyTransp);
				g.fillRect(resourceBarX-20, 25, 300, 160);
				
				Stroke oldStroke = g2d.getStroke();
				Font fnt0 = new Font("arial", Font.BOLD, 25);
				g.setFont(fnt0);
				g.setColor(Color.white);
	
				g2d.drawImage(LogImage, resourceBarX, 50, null);
				g.drawString("x" + getP().getLogCount()+"", resourceBarX, 50);	
				g2d.drawImage(CoinImage, resourceBarX, 125, null);
				g.drawString("x" + getP().getCoinCount()+"", resourceBarX, 125);	
	
				g2d.setStroke(oldStroke);
	
				//WEAPON BAR
				int weaponBarX = (int)getP().getXCoord()-560;
	
				g.setColor(greyTransp);
				g.fillRect(weaponBarX-20, 32, 100, 390);
				
				Color whiteTransp = new Color(255,255,255,200);
				g.setColor(whiteTransp);
				if(getP().getCurrentWeapon().getWeaponType().equals("Gun")){
				    g.fillRect(weaponBarX-20, 240, 100, 60);
				}
				else if(getP().getCurrentWeapon().getWeaponType().equals("Bow")){
				    g.fillRect(weaponBarX-20, 300, 100, 122);
				}
				else if(getP().getCurrentWeapon().getWeaponType().equals("Axe")){
				    g.fillRect(weaponBarX-20, 173, 100, 67);
				}
				else if(getP().getCurrentWeapon().getWeaponType().equals("Sword")){
				    g.fillRect(weaponBarX-20, 103, 100, 70);
				}
				else if(getP().getCurrentWeapon().getWeaponType().equals("Stick")){
				    g.fillRect(weaponBarX-20, 32, 100, 71);
				}
	
				//oldStroke = g2d.getStroke();
				//fnt0 = new Font("arial", Font.BOLD, 25);
				g.setFont(fnt0);
				g.setColor(Color.white);
			       
				g2d.drawImage(StickImage, weaponBarX, 50, null);
				g.drawString("1", weaponBarX-10, 50+5);	
				g2d.drawImage(SwordImage, weaponBarX, 120, null);
				g.drawString("2", weaponBarX-10, 120+5);	
				g2d.drawImage(AxeImage, weaponBarX, 190, null);
				g.drawString("3", weaponBarX-10, 190+5);	
				g2d.drawImage(GunImage, weaponBarX, 260, null);
				g.drawString("4", weaponBarX-10, 260+5);
				g2d.drawImage(BowImage, weaponBarX, 330, null);
				g.drawString("5", weaponBarX-10, 330+5);
				
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
	
			else if (getState() == STATE.GAMEOVER){
				// reset Player stats
				getP().setXCoord(10); //was 10
				getP().setYCoord(WorldBot - getP().getVerticalSize());
				getP().setYAcc(.5);
				getP().setYVel(0);
				getP().setXVel(0);
				getP().setHp(100);
				getP().setAttack(500);
				getP().setAttackSpeed(50);
				getP().setSpeed(4);
				getP().setJumpSpeed(-15);
				getP().setHealthBarY((getP().getYCoord() - 580));
				getP().setLogCount(0);
				getP().setCoinCount(0);

				// if game is won display win screen
				if (TotalProgress > 20){
					getWinscreen().setVisible(true);
					getWinscreen().requestFocusInWindow();
					int brightness = (int)(256 - 256 * 0.5f);
					g.setColor(new Color(0,0,0,brightness));
					g.fillRect((int)getP().getXCoord()-1000,0,7478,1000);
					
					// reset level
					setTotalProgress(0);
					randomGenerator = new Random();
					generator = new EnemyGenerator(this);
					enemies = new ArrayList<Enemies>();
					terrain = new ArrayList<Terrain>();
					resources = new ArrayList<Resource>();
					boss = new ArrayList<Enemies>();
					TotalProgress = 0;
					lvlhandler = new LevelHandler();
					lvlhandler.HandleLVL1Start(enemies, terrain, generator);
					for(int i = 0; i < enemies.size(); i++){
						enemies.get(i).setTerrainDimensions(terrain);
					}
					p.setTerrains(terrain);
					
					//WEAPONS
					Gun w = new Gun(0,0);//temp holder to initialize arrayList
					w.setWeaponType("empty");
					for(int i=0;i<7;i++){ //7
						getP().AddWeapon(i, w);
					}
					Stick s = new Stick(getP().getXCoord(),getP().getYCoord());
					getP().AddWeapon(0, s);
					getP().setCurrentWeapon(s);
				}
				// else display game over screen
				else{
					getGoscreen().requestFocusInWindow();
					int brightness = (int)(256 - 256 * 0.5f);
					g.setColor(new Color(0,0,0,brightness));
					g.fillRect((int)getP().getXCoord()-1000,0,7478,1000);
					
					// reset level
					setTotalProgress(0);
					randomGenerator = new Random();
					generator = new EnemyGenerator(this);
					enemies = new ArrayList<Enemies>();
					terrain = new ArrayList<Terrain>();
					resources = new ArrayList<Resource>();
					boss = new ArrayList<Enemies>();
					TotalProgress = 0;
					lvlhandler = new LevelHandler();
					lvlhandler.HandleLVL1Start(enemies, terrain, generator);
					for(int i = 0; i < enemies.size(); i++){
						enemies.get(i).setTerrainDimensions(terrain);
					}
					getP().setTerrains(terrain);

					//WEAPONS
					Gun w = new Gun(0,0);//temp holder to initialize arrayList
					w.setWeaponType("empty");
					for(int i=0;i<7;i++){ //7
						getP().AddWeapon(i, w);
					}
					Stick s = new Stick(getP().getXCoord(),getP().getYCoord());
					getP().AddWeapon(0, s);
					getP().setCurrentWeapon(s);
				}
			}
		}
	}

	private class AL extends KeyAdapter {
	    /**
	     * @param e KeyEvent
	     * Takes in user keyboard input and performs specific actions based on the key pressed.
	     */
		public void keyReleased(KeyEvent e) {
			getP().keyReleased(e);
		}

	    /**
	     * @param e KeyEvent
	     * Takes in user keyboard input and performs specific actions based on the key pressed.
	     */
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

	/**
	 * @return the goscreen
	 */
	public static GameOverScreen getGoscreen() {
		return goscreen;
	}

	/**
	 * @param aGoscreen the goscreen to set
	 */
	public static void setGoscreen(GameOverScreen aGoscreen) {
		goscreen = aGoscreen;
	}
	
	public int getTotalProgress() {
		return TotalProgress;
	}

	public void setTotalProgress(int totalProgress) {
		TotalProgress = totalProgress;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	
	/**
	 * @return the winscreen
	 */
	public static WinScreen getWinscreen() {
		return winscreen;
	}

	/**
	 * @param aWinscreen the winscreen to set
	 */
	public static void setWinscreen(WinScreen aWinscreen) {
		winscreen = aWinscreen;
	}
	
	/**
	 * @return the levelhandler
	 */
	public LevelHandler getLvlhandler() {
		return lvlhandler;
	}

	/**
	 * @param lvlhandler the levelhandler to set
	 */
	public void setLvlhandler(LevelHandler lvlhandler) {
		this.lvlhandler = lvlhandler;
	}
}
