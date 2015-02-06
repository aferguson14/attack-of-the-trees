import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class MouseInput implements MouseListener {

    @Override
    public void mouseClicked(MouseEvent arg0) {
	// TODO Auto-generated method stub
	
    }
    
    @Override
    public void mouseEntered(MouseEvent arg0) {
	// TODO Auto-generated method stub
	
    }
    
    @Override
    public void mouseExited(MouseEvent arg0) {
	// TODO Auto-generated method stub
	
    }
    
    
    public void mousePressed(MouseEvent e) {
	
	int mx = e.getX();
	int my = e.getY();
	
	/**
	   public Rectangle newGameButton = new Rectangle(Board.WIDTH /2 + 400, 150, 200, 50);
	   public Rectangle loadGameButton = new Rectangle(Board.WIDTH /2 + 400, 250, 200, 50);
	*/
	
	//newGameButton
	if(mx >= Board.WIDTH /2 + 400 && mx <= Board.WIDTH /2 + 600) {
	    if(my >= 150 && my <= 200){
		//Pressed newGameButton
		Board.State = Board.STATE.GAME;
	    }
	}
    }
    
    @Override
    public void mouseReleased(MouseEvent arg0) {
	// TODO Auto-generated method stub
	
    }
    
}
