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
    
    //If in Menu, preform MenuMouse
    //if in Game, get point and set to Board.MouseCoords
    //and set Player attack = true
    public void mousePressed(MouseEvent e) {
        if(Board.getState() == Board.STATE.MENU){
            MenuMouse(e);
        }
        
        if(Board.getState() == Board.STATE.GAME){
           Point p = new Point(e.getX(), e.getY());
           Board.MouseCoords = p;
           Board.PlayerAttack = true;
        }

    }
    
    //if mouse released and in game, set playerAttack = false
    @Override
    public void mouseReleased(MouseEvent arg0) {
	 if(Board.getState() == Board.STATE.GAME){
           Board.PlayerAttack = false;
        }
	
    }
 
    //if mouse dragged in game, set point and player attack
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


        //if within newGameButton, set state to Game
        if(mx >= Board.WIDTH /2 + 400 && mx <= Board.WIDTH /2 + 600) {
            if(my >= 150 && my <= 200){
                //Pressed newGameButton
                Board.setState(Board.STATE.GAME);
            }
        }
    }

    
}
