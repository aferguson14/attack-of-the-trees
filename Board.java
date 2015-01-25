import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Board extends JPanel implements ActionListener {
    Player p;
    public Image img;
    Timer time;
    private Menu menu; 

    public Board() {
	p = new Player();
	menu = new Menu();
	addKeyListener(new AL());
	addMouseListener(new MouseInput());
	setFocusable(true);
	ImageIcon i = new ImageIcon("images/backgrounds/JungleBackground.jpg");
	img = i.getImage();
	time = new Timer(5, this);
	time.start();
    }
    
    public static enum STATE {
    	MENU,
    	GAME
    };
    
    public static STATE State = STATE.MENU;

    public void actionPerformed(ActionEvent e) {
	p.move();
	repaint();
    }

    public void paint(Graphics g) {
	super.paint(g);
	Graphics2D g2d = (Graphics2D) g;

	g2d.drawImage(img, 0, 0, null);
	
	if(State == STATE.GAME) {
		g2d.drawImage(p.getImage(), p.getX(), p.getY(), null);
    	} else if(State == STATE.MENU) {
    		menu.render(g);
    	}
    }

    private class AL extends KeyAdapter {
	public void keyReleased(KeyEvent e) {
	    p.keyReleased(e);
	}

	public void keyPressed(KeyEvent e) {
	    p.keyPressed(e);
	}
    }
}
