import java.io.BufferedInputStream;
import java.io.FileInputStream;

import javazoom.jl.player.Player;


public class MP3 extends Thread{
    private String filename;
    private Player player; 
    private boolean loopBool;

    // constructor that takes the name of an MP3 file
    public MP3(String filename) {
        this.filename = filename;
	this.loopBool = true;
    }

    public void close() { 
	loopBool = false;
	if (player != null) player.close();
	this.interrupt();
    }

    // play the MP3 file to the sound card
    public void play() {
        try {
            FileInputStream fis     = new FileInputStream(filename);
            BufferedInputStream bis = new BufferedInputStream(fis);
            player = new Player(bis);
        }
        catch (Exception e) {
            System.out.println("Problem playing file " + filename);
            System.out.println(e);
        }

        // run in new thread to play in background
        new Thread() {
            public void run() {
                try { player.play(); }
                catch (Exception e) { System.out.println(e); }
            }
        }.start();

    }

    public void loop() {
	/*        try {
	    do{
            FileInputStream fis = new FileInputStream(filename);
            player = new Player(fis);
	    player.play();
	    }while(loopBool);
        }
        catch (Exception e) {
            System.out.println("Problem playing file " + filename);
            System.out.println(e);
        }
	

       try {
            FileInputStream fis     = new FileInputStream(filename);
            BufferedInputStream bis = new BufferedInputStream(fis);
            player = new Player(bis);
        }
        catch (Exception e) {
            System.out.println("Problem playing file " + filename);
            System.out.println(e);
        }
	*/
        // run in new thread to play in background
        new Thread() {
            public void run() {
		do{
                try { 
		    FileInputStream fis     = new FileInputStream(filename);
		    BufferedInputStream bis = new BufferedInputStream(fis);
		    player = new Player(bis);
		    player.play(); 
		}
                catch (Exception e) { System.out.println(e); }
		}while(loopBool);
            }
        }.start();
    }
}
