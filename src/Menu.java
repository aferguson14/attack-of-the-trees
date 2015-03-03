import java.awt.*;


public class Menu {
    //Menu buttons
    public Rectangle newGameButton = new Rectangle((Board.WIDTH/2+100), 200, 300, 70);
    public Rectangle loadGameButton = new Rectangle((Board.WIDTH/2+100), 400, 300, 70);
    private int x = -100;
    private int y = 150;
    private int ngx = newGameButton.x+15;
    private int ngy = newGameButton.y+50;
    private int lgx = loadGameButton.x+15;
    private int lgy = loadGameButton.y+50;
    
    public void render(Graphics g) {
        //Paints Menu
	Graphics2D g2d = (Graphics2D) g; 
	Stroke oldStroke = g2d.getStroke();
	
	Font fnt0 = new Font("arial", Font.BOLD, 80);
	g.setFont(fnt0);
	g.setColor(new Color(0x000066));
	g.drawString("Attack of the Trees", x-2, y-2);
	g.drawString("Attack of the Trees", x-2, y+2);
	g.drawString("Attack of the Trees", x+2, y-2);
	g.drawString("Attack of the Trees", x+2, y+2);
	g.setColor(new Color(0xEEFFEE));
	g.drawString("Attack of the Trees", x, y);
	
	Font fnt1 = new Font("arial", Font.BOLD, 50);
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
	
	g.setColor(new Color(0xEEFFEE));
	g.drawString("New Game", ngx, ngy);
	g.drawString("Load Game", lgx, lgy);

	g2d.setStroke(new BasicStroke(4));
	g2d.draw(loadGameButton);
	g2d.draw(newGameButton);
	g2d.setStroke(oldStroke);
	
}
    
}
