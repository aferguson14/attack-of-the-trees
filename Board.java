
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
    private Player p;
    private ArrayList <Enemies> enemies = new ArrayList<Enemies>();
    private ArrayList <Terrain> terrain = new ArrayList<Terrain>();
    public Image farBackground;
    public Image nearBackground;
    public Image Far2;
    public Image Near2;
//    int fps; //Added to try and solve fast enemy
//    private long diff, start = System.currentTimeMillis(); //Added to solve fast enemy
    private Image img;
    private Timer time;
    private int tic = 0;
    private int toc = 50;
    private boolean attack = false;
    private Menu menu;
    private PauseMenu pmenu;
<<<<<<< HEAD
    private GameOverScreen goscreen;
    private int WorldBot = 334;
=======
    //private GameOver screen;
     private int WorldBot = 2103;
>>>>>>> 7e6f5866f938029954048dab077603e0318884ea
    private int WorldLeft = 0;
    private int WorldRight = 5120;
    private int WorldTop = 0;
    public static Point MouseCoords;
    public static boolean PlayerAttack = false;

    
    public Board() {
<<<<<<< HEAD
    	p = new Player();
        EnemyRobot robo = new EnemyRobot(700, 265);
        EnemyTree tree = new EnemyTree(650, 265);
        enemies.add(robo);
        enemies.add(tree);
        addKeyListener(new AL());
        menu = new Menu();
        pmenu = new PauseMenu();
        addMouseListener(new MouseInput());
        setFocusable(true);
        ImageIcon i = new ImageIcon("images/backgrounds/JungleBackground1.jpg");
        img = i.getImage();
        time = new Timer(5, this);
        time.start();
=======
	p = new Player();
        EnemyRobot robo = new EnemyRobot(700, WorldBot - 115);
//	EnemyTree tree = new EnemyTree(650, 265);
//        EnemyBear bear = new EnemyBear(750, 265);
//        EnemySunFlower sunflower = new EnemySunFlower(500, 265);
        enemies.add(robo);
//	enemies.add(tree);
//        enemies.add(bear);
//        enemies.add(sunflower);
        Rock rock = new Rock(300, WorldBot - 100);
        Ramp ramp = new Ramp(500, WorldBot);
        rock.setYCoord(rock.getWorldBot() - 100);
        ramp.setYCoord(rock.getWorldBot());
        terrain.add(rock);
        terrain.add(ramp);
	addKeyListener(new AL());
	menu = new Menu();
	pmenu = new PauseMenu();
	//screen = new GameOver();
        MouseInput m = new MouseInput();
	addMouseListener(m);
        addMouseMotionListener(m);
	setFocusable(true);
	ImageIcon far = new 
	    ImageIcon("images/backgrounds/far-background.png");
        
        farBackground = far.getImage();
        ImageIcon far2 = new ImageIcon("images/backgrounds/far-background.png");
	ImageIcon near = new 
	    ImageIcon("images/backgrounds/near-background.png");
        ImageIcon near2 = new ImageIcon("images/backgrounds/near-background.png");
	nearBackground = near.getImage();
        Far2 = far2.getImage();
        Near2 = near2.getImage();
	time = new Timer(5, this);
	time.start();
        gun g = new gun(p.getXCoord() + 115,p.getYCoord() + 25);
	p.AddWeapon(g);
>>>>>>> 7e6f5866f938029954048dab077603e0318884ea
    }


    public static enum STATE {
    	MENU,
    	GAME,
<<<<<<< HEAD
    	PAUSE,
    	GAMEOVER
=======
	PAUSE,
	GAMEOVER
>>>>>>> 7e6f5866f938029954048dab077603e0318884ea
    };
    
    private static STATE State = STATE.MENU;

    public void actionPerformed(ActionEvent e) {
<<<<<<< HEAD
    	p.move();
        if(p.isAttacking()){
        	p.PlayerAttack(enemies);
        }
        for(int i = 0; i < enemies.size(); i++){
            if(enemies.get(i).getHp() <= 0){
                enemies.remove(i);
=======

        getP().move(terrain);
        getP().getCurrentWeapon().move(getP());
        if(Board.PlayerAttack){
            getP().setAttacking(true);
//            Board.MouseCoords
        }
        else{
            getP().setAttacking(false);
        }
//        if(p.isAttacking()){
//	    p.PlayerAttack(enemies);
//	}
        for(int i = 0; i < getEnemies().size(); i++){
            if(getEnemies().get(i).getHp() <= 0){
                getEnemies().remove(i);
>>>>>>> 7e6f5866f938029954048dab077603e0318884ea
            }
        }
        repaint();
    }
    
    public void paint(Graphics g) {
    	super.paint(g);
    	Graphics2D g2d = (Graphics2D) g;
	
<<<<<<< HEAD
    	g2d.drawImage(img, 0, 0, null);

    	if(State == STATE.MENU)
    		menu.render(g);
    	if(State == STATE.GAME) {
            //paint player
    		p.paintPlayer(g);
            //perform enemy AI/ Paint
    		for(Enemies e : enemies){
    			for(int i = 0; i < enemies.size(); i++){
    				enemies.get(i).AI(p, g);
    			}
    			e.paintEnemy(p, g);
    		}
    		p.AttackAnimation(g);
    		}
    	if(State == STATE.PAUSE){
    		p.pausePlayer();
    		for (int i = 0; i < enemies.size(); i++){
    			enemies.get(i).pauseEnemy();
    		}
    		pmenu.setPauseMenuVisible(true);
    		State = STATE.GAME;
    	}
//    	if(State == STATE.GAMEOVER)
//    		goscreen.paint(g);
    }
   
    private class AL extends KeyAdapter {
    	public void keyReleased(KeyEvent e) {
    		p.keyReleased(e);
    	}
	
    	public void keyPressed(KeyEvent e) {
    		int key = e.getKeyCode();	    
    		if(key == KeyEvent.VK_P){
    			if(State == STATE.GAME)
    				State = STATE.PAUSE;
    			else if (State == STATE.PAUSE){
    				State = STATE.GAME;
    			}
    		}
    		else{
    			p.keyPressed(e);
    		}
    	}
    }
}
=======
	g2d.translate((p.getXCoord()*-1)+300, 0); //+300 because of player pos.
	g2d.drawImage(farBackground, (int) p.getXCoord()/2*(-1), -1800, null);
	g2d.drawImage(nearBackground,0, -1300, null);
                g2d.drawImage(Far2,-4500, -1800,null);
        g2d.drawImage(Near2,-7473 , -1305,null);

        
	//(int) p.getXCoord()/2*(-1)
	if(getState() == STATE.GAME || getState() == STATE.PAUSE) {
            //paint player
            for(Terrain t : terrain){
                t.paintTerrain(g, getP(), enemies);
            }
	    getP().paintPlayer(g);
            getP().getCurrentWeapon().paintWeapon(g, getP(), getEnemies());
            //perform enemy AI/ Paint
            if(getP().isAttacking()){
                getP().PlayerAttack(g);
            }
//            getP().testWeapon(g);

	    for(Enemies e : getEnemies()){
		for(int i = 0; i < getEnemies().size(); i++){
		    getEnemies().get(i).AI(getP(), g, terrain);
		}
		e.paintEnemy(getP(), g);
	    }
	    getP().AttackAnimation(g);
	    if(getState() == STATE.PAUSE){
		pmenu.requestFocusInWindow();
	    }
    	}

	else if(getState() == STATE.MENU) {
	    getMenu().render(g);
    	}
        
        //sleep(fps); //New Code
	
    }
    
//        //New Code
//    public void sleep(int fps) {
//	if(fps>0){
//	    diff = System.currentTimeMillis() - start;
//	    long targetDelay = 1000/fps;
//	    if (diff < targetDelay) {
//		try{
//		    Thread.sleep(targetDelay - diff);
//		} catch (InterruptedException e) {}
//	    }   
//	    start = System.currentTimeMillis();
//	}
//    }
    
    private class AL extends KeyAdapter {
	public void keyReleased(KeyEvent e) {
	    getP().keyReleased(e);
	}
	
	public void keyPressed(KeyEvent e) {
	    getP().keyPressed(e);
	    pmenu.keyPressedMenu(e);
	   
	}
    }

    
    
    //------------------------------Getters/Setters---------------------------------------[
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
     * @return the tic
     */
    public int getTic() {
        return tic;
    }

    /**
     * @param tic the tic to set
     */
    public void setTic(int tic) {
        this.tic = tic;
    }

    /**
     * @return the toc
     */
    public int getToc() {
        return toc;
    }

    /**
     * @param toc the toc to set
     */
    public void setToc(int toc) {
        this.toc = toc;
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
>>>>>>> 7e6f5866f938029954048dab077603e0318884ea
