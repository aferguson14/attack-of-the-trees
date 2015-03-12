import java.awt.*;
import javax.swing.ImageIcon;

/** 
 * Menu represents the window that appears on the user's screen when the game is first opened.
 * User is able to select new game or load an existing one.
 * 
 * @author Shadee Barzin
 * @author Andrew Ferguson
 * @author Michele Haque
 * @author Brendan Murphy
 * @author Fengyu Wang
 * @version CS 48, Winter 2015
*/

public class Menu {
	//Menu buttons
	public Rectangle newGameButtonOutline = new Rectangle(1210, 308, 353, 73);
	public Rectangle newGameButtonInline = new Rectangle(1214, 312, 346, 66);
	public Rectangle newGameButton = new Rectangle(1212, 310, 350, 70);
	public Rectangle loadGameButtonOutline = new Rectangle(1210, 458, 353, 73);
	public Rectangle loadGameButtonInline = new Rectangle(1214, 462, 346, 66);
	public Rectangle loadGameButton = new Rectangle(1212, 460, 350, 70);
	public ImageIcon i = new ImageIcon("../images/backgrounds/mainMenu.jpg");
	private int x = -100;
	private int y = 150;
	private int ngx = newGameButton.x+35;
	private int ngy = newGameButton.y+50;
	private int lgx = loadGameButton.x+30;
	private int lgy = loadGameButton.y+50;

    /** 
     * @param g Graphics
     * Paints menu onto screen when called
     */
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g; 
		Stroke oldStroke = g2d.getStroke();

		g.drawImage(i.getImage(), 0, 0, null);

		Font fnt1 = new Font("kristen itc", Font.BOLD, 50);
		g.setFont(fnt1);

		g.setColor(new Color(0x000066));
		g.drawString("New Game", ngx-1, ngy-1);
		g.drawString("New Game", ngx-1, ngy+1);
		g.drawString("New Game", ngx+1, ngy-1);
		g.drawString("New Game", ngx+1, ngy+1);

		g.setColor(new Color(0x000066));
		g.drawString("Load Game", lgx-1, lgy-1);
		g.drawString("Load Game", lgx-1, lgy+1);
		g.drawString("Load Game", lgx+1, lgy-1);
		g.drawString("Load Game", lgx+1, lgy+1);

		//g.setColor(new Color(0xEEFFEE));
		g.setColor(Color.white);
		g.drawString("New Game", ngx, ngy);
		g.drawString("Load Game", lgx, lgy);

		g2d.setStroke(new BasicStroke(4));
		g2d.draw(loadGameButton);
		g2d.draw(newGameButton);
		g2d.setColor(new Color(0x000066));
		g2d.setStroke(oldStroke);
		g2d.draw(newGameButtonOutline);
		g2d.draw(newGameButtonInline);
		g2d.draw(loadGameButtonOutline);
		g2d.draw(loadGameButtonInline);
		g2d.setStroke(oldStroke);
	}
}
