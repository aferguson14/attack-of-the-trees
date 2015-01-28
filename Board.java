import java.awt.*;
import java.awt.event.*;
import static java.lang.Math.abs;
import javax.swing.*;

public class Board extends JPanel implements ActionListener {
    Player p;
    Enemy enemy;
    public Image img;
    Timer time;
    int tic = 0;
    int toc = 50;
    boolean attack = false;

    
//    int camX;
//    int camY;
//    int WORLD_SIZE_X = 2000;
//    int WORLD_SIZE_Y = 900;
//    int VIEWPORT_SIZE_X = 1024;
//    int VIEWPORT_SIZE_Y = 410;
//    
//    int offsetMaxX = WORLD_SIZE_X - VIEWPORT_SIZE_X;
//    int offsetMaxY = WORLD_SIZE_Y - VIEWPORT_SIZE_Y;
//    int offsetMinX = 0;
//    int offsetMinY = 0;
    
    public Board() {
	p = new Player();
        enemy = new Enemy();
	addKeyListener(new AL());
	setFocusable(true);
	ImageIcon i = new ImageIcon("images/backgrounds/JungleBackground.jpg");
	img = i.getImage();
	time = new Timer(5, this);
	time.start();
        
        
//        camX = p.getX() - VIEWPORT_SIZE_X / 2;
//        camY = p.getY() - VIEWPORT_SIZE_Y / 2;
//        if (camX > offsetMaxX)
//            camX = offsetMaxX;
//        else if (camX < offsetMinX)
//            camX = offsetMinX;
//        
//        if (camY > offsetMaxY)
//            camY = offsetMaxY;
//        else if (camY < offsetMinY)
//            camY = offsetMinY;
    }

    public void actionPerformed(ActionEvent e) {
	p.move();
        enemy.move();
        
        if(p.attacking){
            tic+=1;
            if(tic == toc){
                PlayerAttack();
                attack = true;
                tic = 0;
            }
            
        }
	repaint();
    }

    public void PlayerAttack(){
        if(p.attacking()){
            if(p.facing() == 0){
                if(abs(p.getX() - (enemy.getX() + 57)) <= 20){
                  enemy.takeDmg(p);
                }
            }
            else if(p.facing == 1){
                if(abs((p.getX() + 57) - enemy.getX()) <= 20){
                    enemy.takeDmg(p);
                }
            }
        }
    }

    public void paint(Graphics g) {
	super.paint(g);
	Graphics2D g2d = (Graphics2D) g;
        
        
	g2d.drawImage(img, 0, 0, null);
	g2d.drawImage(p.getImage(), p.getX(), p.getY(), null);
        g2d.drawImage(enemy.getImage(), enemy.getX(), enemy.getY(), null);
        
        Font fntP = new Font("arial", Font.BOLD, 10);
	g.setFont(fntP);
	g.setColor(Color.white);
	g.drawString("" + p.getHP(), p.getX(), p.getY() - 10);
        
        Font fntE = new Font("arial", Font.BOLD, 10);
	g.setFont(fntE);
	g.setColor(Color.red);
	g.drawString("" + enemy.getHP(), enemy.getX(), enemy.getY() - 10);
        
        if(p.attacking()){
            if(attack){
                if(p.facing() == 0){
                    Rectangle rect = new Rectangle(p.getX() - 20, p.getY() + 20, 20, 10);
                    g2d.setColor(Color.red);
                    g2d.fill(rect);
                }
                else if(p.facing() == 1){
                    Rectangle rect = new Rectangle(p.getX() + 57, p.getY() + 20, 20, 10);
                    g2d.setColor(Color.red);
                    g2d.fill(rect);

                }
            }
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
