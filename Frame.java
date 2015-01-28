import javax.swing.*;

public class Frame {
//    int WORLD_SIZE_X = 2000;
//    int WORLD_SIZE_Y = 900;
//    int VIEWPORT_SIZE_X = 1024;
//    int VIEWPORT_SIZE_Y = 410;
//    
//
//    
//    int offsetMaxX = WORLD_SIZE_X - VIEWPORT_SIZE_X;
//    int offsetMaxY = WORLD_SIZE_Y - VIEWPORT_SIZE_Y;
//    int offsetMinX = 0;
//    int offsetMinY = 0;
    public Frame(){
	JFrame frame = new JFrame();
	frame.getContentPane().add(new Board());
	frame.setTitle("2D Game");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setSize(1024,410); //frame size
	frame.setVisible(true);
	frame.setLocationRelativeTo(null);
    }
    
    public static void main(String args[]){
        Frame frame = new Frame();
    }

}