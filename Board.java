import java.awt.*;
import java.awt.Image;
import javax.swing.*;
import java.awt.event.ActionListener;

public class Board extends JPanel implements ActionListener {

    Player p;
    Image img; //background image (have to import)
    Time time;

    public Board() {
	p = new Player();
	addKeyListener(new AL());
	setFocusable(true);
	ImageIcon i = new ImageIcon("C:/test.png"); //file location of background
	img = i.getImage();
	time = new Timer(5, this);
	time.start();
    }
    
    public void actionPerformed (ActionEvent e){
	repaint();
    }

    public void paint (Graphics g) {
	super.paint(g);
	Graphics2D g2d = (Graphics2D) g;
	
	g2d.drawImage(img,0,0, null); // in place of null is "observer"
	g2d.drawImage(p.getImage(), p.getX(), p.getY(), null);
    }

    private class AL extends KeyAdapter{ //keyAdapter determines which key is pressed

	public void keyReleased(KeyEvent e){
	    p.keyReleased(e);
	}

	public void keyPressed(KeyEvent e){
	    p.keyPressed(e);
	}
    }
}
