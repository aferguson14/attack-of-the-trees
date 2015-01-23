import javax.swing.*;

public class Frame {

    public Frame(){
	JFrame frame = new JFrame();
	frame.add(new Board());
	frame.setTitle("Attack of the Trees");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setSize(1024,410); //frame size
	frame.setVisible(true);
	frame.setLocationRelativeTo(null);
    }
    public static void main(String[] args){
	new Frame();
    }
}
