
import java.awt.*;
import java.awt.event.*;
import static java.lang.Math.abs;
import java.util.ArrayList;
import javax.swing.*;

public class Board extends JPanel implements ActionListener {

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
    
    //private data
    private Player p;
    private ArrayList <Enemies> enemies = new ArrayList<Enemies>();
    private ArrayList <Terrain> terrain = new ArrayList<Terrain>();
    public Image farBackground;
    public Image nearBackground;
    public Image Far2, Far3;
    public Image Near2;
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

    
    public Board() {
        //creates player, enemies, terrain, weapon, menu, and background images
	p = new Player();
        EnemyRobot robo = new EnemyRobot(1000, WorldBot - 115);
	EnemyTree tree = new EnemyTree(2000, WorldBot - 115);
        EnemyBear bear = new EnemyBear(3000, WorldBot - 115);
        EnemySunFlower sunflower = new EnemySunFlower(900, WorldBot - 115);
        enemies.add(robo);
	enemies.add(tree);
        enemies.add(bear);
        enemies.add(sunflower);
        Rock rock = new Rock(300, WorldBot - 100);
        Ramp ramp = new Ramp(700, WorldBot);
        Rock rock2 = new Rock(3000, WorldBot - 250);
        Ramp ramp2 = new Ramp(2900, WorldBot);

        terrain.add(rock);
        terrain.add(ramp);
        terrain.add(rock2);
        terrain.add(ramp2);
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
	time = new Timer(5, this);
	time.start();
        Gun g = new Gun(p.getXCoord(),p.getYCoord());
	p.AddWeapon(g);
    }

    //states
    public static enum STATE {
    	MENU,
    	GAME,
	PAUSE,
        GAMEOVER
    };
    //initial state = MENU
    private static STATE State = STATE.MENU;

    
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
        for(int i = 0; i < getEnemies().size(); i++){
            if(getEnemies().get(i).getHp() <= 0){
                getEnemies().remove(i);
            }
        }
	repaint();
    }
    
    public void paint(Graphics g) {
	super.paint(g);
	Graphics2D g2d = (Graphics2D) g;
	
        //background images
	g2d.translate((p.getXCoord()*-1)+300, 0); //+300 because of player pos.
	g2d.drawImage(farBackground, (int) p.getXCoord()*(-1), -1800, null);
        g2d.drawImage(Far3, (int) p.getXCoord()*(-1) + 4500, -1800, null);
        g2d.drawImage(Far3, (int) p.getXCoord()*(-1) + 9000, -1800, null);
	g2d.drawImage(nearBackground,0, -1300, null);
        g2d.drawImage(Far2,-4500, -1800,null);
        g2d.drawImage(Near2,-7473 , -1305,null);

	if(getState() == STATE.GAME) {

            //paint terrain
            for(Terrain t : terrain){
                t.paintTerrain(g, getP(), enemies);
            }
            //paint player and weapon
	    getP().paintPlayer(g);
            getP().getCurrentWeapon().paintWeapon(g, getP(), getEnemies());

            //perform player attack, perform enemy AI
            if(getP().isAttacking()){
                getP().PlayerAttack(g);
            }
	    for(Enemies e : getEnemies()){
		e.AI(getP(), g, terrain);
		e.paintEnemy(getP(), g);
	    }
	    getP().AttackAnimation(g);
    	}
        //if State!=Game, perform other State actions
	if(getState() == STATE.PAUSE){
	   pmenu.requestFocusInWindow();
	}
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
}
