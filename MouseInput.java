import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;


public class MouseInput implements MouseListener, MouseMotionListener {

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
<<<<<<< HEAD
	
    	int mx = e.getX();
    	int my = e.getY();
	
    	/**
	   	public Rectangle newGameButton = new Rectangle(Board.WIDTH /2 + 400, 150, 200, 50);
	   	public Rectangle loadGameButton = new Rectangle(Board.WIDTH /2 + 400, 250, 200, 50);
    	 */
	
    	if (Board.State == Board.State.MENU){
    		//newGameButton
    		if(mx >= Board.WIDTH /2 + 400 && mx <= Board.WIDTH /2 + 600) {
    			if(my >= 150 && my <= 200){
    				//Pressed newGameButton
    				Board.State = Board.STATE.GAME;
    			}
    		}
    	}
=======
        if(Board.getState() == Board.STATE.MENU){
            MenuMouse(e);
        }
        
        if(Board.getState() == Board.STATE.GAME){
           Point p = new Point(e.getX(), e.getY());
           Board.MouseCoords = p;
           Board.PlayerAttack = true;
        }

        
        
>>>>>>> 7e6f5866f938029954048dab077603e0318884ea
    }
    @Override
    public void mouseReleased(MouseEvent arg0) {
	 if(Board.getState() == Board.STATE.GAME){
           Board.PlayerAttack = false;
        }
	
    }
 
    @Override
    public void mouseDragged(MouseEvent e) {
        if(Board.getState() == Board.STATE.GAME){
           Point p = new Point(e.getX(), e.getY());
           Board.MouseCoords = p;
           Board.PlayerAttack = true;
        }
    }
    @Override
    public void mouseMoved(MouseEvent e) {
        
        
    }
   
    
    

    
    public void MenuMouse(MouseEvent e){
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
                Board.setState(Board.STATE.GAME);
            }
        }
    }

    
}
