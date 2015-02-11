import java.awt.*;
import java.awt.event.*;
import static java.lang.Math.abs;
import java.util.ArrayList;
import javax.swing.*;

public class Board extends JPanel implements ActionListener {
    Player p;
    public ArrayList <Enemies> enemies = new ArrayList<Enemies>();
    public Image img;
    Timer time;
    int tic = 0;
    int toc = 50;
    boolean attack = false;
    private Menu menu;
    private PauseMenu pmenu;
    private GameOverScreen goscreen;
    private int WorldBot = 334;
    private int WorldLeft = 0;
    private int WorldRight = 1024;
    private int WorldTop = 0;

    
    public Board() {
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
    }

    public static enum STATE {
    	MENU,
    	GAME,
    	PAUSE,
    	GAMEOVER
    };
    
    public static STATE State = STATE.MENU;

    public void actionPerformed(ActionEvent e) {
    	p.move();
        if(p.isAttacking()){
        	p.PlayerAttack(enemies);
        }
        for(int i = 0; i < enemies.size(); i++){
            if(enemies.get(i).getHp() <= 0){
                enemies.remove(i);
            }
        }
        repaint();
    }
    
    public void paint(Graphics g) {
    	super.paint(g);
    	Graphics2D g2d = (Graphics2D) g;
	
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