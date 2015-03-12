import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/** 
 * MouseInput deals with user's mouse actions for attacking enemies.
 * 
 * @author Shadee Barzin
 * @author Andrew Ferguson
 * @author Michele Haque
 * @author Brendan Murphy
 * @author Fengyu Wang
 * @version CS 48, Winter 2015
*/

public class MouseInput implements MouseListener, MouseMotionListener {

    /**
     * @param e MouseEvent
     * Part of MouseListener interface
     */
    @Override
    public void mouseClicked(MouseEvent arg0) {}
    
    /**
     * @param e MouseEvent
     * Part of MouseMotionListener interface
     */
    @Override
    public void mouseEntered(MouseEvent arg0) {}
    
    /**
     * @param e MouseEvent
     * Part of MouseMotionListener interface
     */
    @Override
    public void mouseExited(MouseEvent arg0) {}
    
    /**
     * @param e MouseEvent
     * If in Menu, perform MenuMouse
     * If in Game, get point and set to Board.MouseCoords
     * and set Player attack = true
     */ 
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

    /**
     * @param arg0 MouseEvent
     * If mouse released and in game, set playerAttack = false
     */     
    @Override
    public void mouseReleased(MouseEvent arg0) {
	if(Board.getState() == Board.STATE.GAME){
	    Board.PlayerAttack = false;
        }
    }

    /**
     * @param arg0 MouseEvent
     * if mouse dragged in game, set point and player attack
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        if(Board.getState() == Board.STATE.GAME){
	    Point p = new Point(e.getX(), e.getY());
	    Board.MouseCoords = p;
	    Board.PlayerAttack = true;
        }
    }

    /**
     * @param e MouseEvent
     * Part of MouseMotionListener interface
     */
    @Override
    public void mouseMoved(MouseEvent e) {}

    /**
     * @param e MouseEvent
     * Takes in user mouse clicking.
     * Opens new game or loads previous game based on where user clicks.
     */
    public void MenuMouse(MouseEvent e){
        int mx = e.getX();
        int my = e.getY();

        if(mx >= 1210 && mx <= 1563) {
            //if within newGameButton, set state to Game
            if(my >= 308 && my <= 381){
                //Pressed newGameButton
                Board.setState(Board.STATE.GAME);
            }
            //if within loadGameButton, set state to Load
            else if(my >= 458 && my <= 531) {
        		// pressed loadGameButton
        		Board.setState(Board.STATE.LOAD);
            }
        }
    }   
}
