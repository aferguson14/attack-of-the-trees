import java.awt.*;
import javax.swing.*;


public class GameOverScreen {
	
	public void paint(Graphics g) {	
			Graphics2D g2d = (Graphics2D) g; 
			
			Font f = new Font("arial", Font.BOLD, 100);
			g2d.setFont(f);
			g2d.setColor(Color.white);
			g2d.drawString("GAME OVER", 275, 100);
	}

}
	
