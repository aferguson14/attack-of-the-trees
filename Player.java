import java.awt.*;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

public class Player {
    int x, dx, y;
    Image still;

    public Player() {
	ImageIcon i = new ImageIcon("images/playerImages/guy/guyFront.jpg"); //character image
	still = i.getImage();
	x = 10;
	y = 172;
    }

    public void move() {
	x = x + dx;
    }

    public int getX() {
	return x;
    }

    public int getY() {
	return y;
    }

    public Image getImage() {
	return still;
    }

    public void keyPressed(KeyEvent e) {
	int key = e.getKeyCode();
	if (key == KeyEvent.VK_LEFT)
	    dx = -1;

	if (key == KeyEvent.VK_RIGHT)
	    dx = 1;
    }

    public void keyReleased(KeyEvent e) {
	int key = e.getKeyCode();

	if (key == KeyEvent.VK_LEFT)
	    dx = 0;

	if (key == KeyEvent.VK_RIGHT)
	    dx = 0;
    }

}
