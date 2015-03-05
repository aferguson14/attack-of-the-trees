import java.awt.*;

import javax.swing.*;

public class CharacterMenu {
	
	ImageIcon guyCharac = new ImageIcon("../images/playerImages/guy/guyFront.png");
	Image guyCharacter = guyCharac.getImage();
	ImageIcon girlCharac = new ImageIcon("../images/playerImages/guy/girlFront.png");
	Image girlCharacter = girlCharac.getImage();

	public void CharacterMenu() { }
	
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g; 
		Stroke oldStroke = g2d.getStroke();
		
		// Display Character Images

		
	}

}