import java.awt.*;


public class Menu {

	public Rectangle newGameButton = new Rectangle(Board.WIDTH /2 + 400, 150, 200, 50);
	public Rectangle loadGameButton = new Rectangle(Board.WIDTH /2 + 400, 250, 200, 50);

	
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g; 
		
		Font fnt0 = new Font("arial", Font.BOLD, 50);
		g.setFont(fnt0);
		g.setColor(Color.white);
		g.drawString("Attack of the Trees", 275, 100);
		
		Font fnt1 = new Font("arial", Font.BOLD, 30);
		g.setFont(fnt1);
		g.drawString("New Game", newGameButton.x + 22, newGameButton.y + 35);
		g2d.draw(newGameButton);
		g.drawString("Load Game", loadGameButton.x + 22, loadGameButton.y + 35);
		g2d.draw(loadGameButton);
	}
	
}
