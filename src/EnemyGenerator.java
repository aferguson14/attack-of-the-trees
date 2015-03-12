import java.awt.*;
import java.awt.event.KeyEvent;
import static java.lang.Math.abs;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;

/** 
 * EnemyGenerator randomly generates enemies off screen that the player must defeat.
 * 
 * @author Shadee Barzin
 * @author Andrew Ferguson
 * @author Michele Haque
 * @author Brendan Murphy
 * @author Fengyu Wang
 * @version CS 48, Winter 2015
*/

public class EnemyGenerator {
    private Board board;

    /** 
     * @param b the game Board
     * Constructor
     */
    public EnemyGenerator(Board b){
        board = b;
    }
    
    /** 
     * @param enem Enemies
     * @param into ArrayList<Enemies> going into the game
     * @param p Player
     * @param randomGenerator Random
     * Randomly creates enemies to be added to the Board.
     */
    public void generateEnemy(Enemies enem, ArrayList<Enemies> into,Player p, Random randomGenerator){
         long rangeL = (long)this.getBoard().getWorldLeft() + (long)p.getXCoord() - 600;
         long rangeR = (long)6950 - (long)p.getXCoord() - 1150;
         
         long fractionL = (long)(rangeL * randomGenerator.nextDouble());
         long fractionR = (long)(rangeR * randomGenerator.nextDouble());
         
         double randomNumL = (double)(fractionL);
         double randomNumR = (double)(fractionR + p.getXCoord() + 1150);
         
         int pick = randomGenerator.nextInt(2);
         if(pick == 0){
             if(rangeL > 50){
             enem.setXCoord(randomNumL);
             }
             else{
                 enem.setXCoord(randomNumR);
             }
         }
         else{
             if(rangeR > 50){
                 enem.setXCoord(randomNumR);
             }
             else{
                 enem.setXCoord(randomNumL);
             }
         }
         into.add(enem);
     }
    
    /** 
     * @param range int
     * Randomly selects type of enemy to be added
     */
    public int chooseEnemyType(int range){
        Random randomInt = new Random();
        int number = randomInt.nextInt(range);
        return number;
    }
    
    /** 
     * @param enem2 ArrayList<Enemies>
     * Randomly creates enemies for level 1
     */
    public void createLVL1Enemies(ArrayList<Enemies> enem2){
	Random randomGenerator = new Random();

          for(int i = 0; i <  6; i++){
        int randoNum = chooseEnemyType(3);
        if(randoNum == 0){
            EnemyTree tree = new EnemyTree(0, getBoard().getWorldBot() - 115);
            this.generateEnemy(tree, enem2, getBoard().getP(), randomGenerator);
        }else if(randoNum == 1){
            EnemyBear bear = new EnemyBear(0, getBoard().getWorldBot() - 115);
            this.generateEnemy(bear, enem2, getBoard().getP(), randomGenerator);
        }else{
            EnemySunFlower flower = new EnemySunFlower(0, getBoard().getWorldBot() - 115);
            this.generateEnemy(flower, enem2, getBoard().getP(), randomGenerator);
        }
          }

}

    /** 
     * @param enem2 ArrayList<Enemies>
     * Randomly creates enemies for level 2
     */
   public void createLVL2Enemies(ArrayList<Enemies> enem2){
       Random randomGenerator = new Random();

          for(int i = 0; i <  6; i++){
        int randoNum = chooseEnemyType(4);
        if(randoNum == 0){
            EnemyTree tree = new EnemyTree(0, getBoard().getWorldBot() - 115);
            this.generateEnemy(tree, enem2, getBoard().getP(), randomGenerator);
        }else if(randoNum == 1){
            EnemyBear bear = new EnemyBear(0, getBoard().getWorldBot() - 115);
            this.generateEnemy(bear, enem2, getBoard().getP(), randomGenerator);
        }else if (randoNum == 2){
            EnemySunFlower flower = new EnemySunFlower(0, getBoard().getWorldBot() - 115);
            this.generateEnemy(flower, enem2, getBoard().getP(), randomGenerator);
        } else{
            EnemyBee bee = new EnemyBee(0, getBoard().getWorldBot() - 115);
            this.generateEnemy(bee, enem2, getBoard().getP(), randomGenerator);
        }
          }
   }    

    /** 
     * @param enem2 ArrayList<Enemies>
     * Randomly creates enemies for level 3
     */
     public void createLVL3Enemies(ArrayList<Enemies> enem2){
       Random randomGenerator = new Random();

          for(int i = 0; i <  6; i++){
        int randoNum = chooseEnemyType(5);
        if(randoNum == 0){
            EnemyTree tree = new EnemyTree(0, getBoard().getWorldBot() - 115);
            this.generateEnemy(tree, enem2, getBoard().getP(), randomGenerator);
        }else if(randoNum == 1){
            EnemyBear bear = new EnemyBear(0, getBoard().getWorldBot() - 115);
            this.generateEnemy(bear, enem2, getBoard().getP(), randomGenerator);
        }else if (randoNum == 2){
            EnemySunFlower flower = new EnemySunFlower(0, getBoard().getWorldBot() - 115);
            this.generateEnemy(flower, enem2, getBoard().getP(), randomGenerator);
        } else if (randoNum == 3){
            EnemyBee bee = new EnemyBee(0, getBoard().getWorldBot() - 115);
            this.generateEnemy(bee, enem2, getBoard().getP(), randomGenerator);
        } else{
            EnemyGnome gnomey = new EnemyGnome(0, getBoard().getWorldBot() - 115);
            this.generateEnemy(gnomey, enem2, getBoard().getP(), randomGenerator);
        }
          }
     
   }    

    /** 
     * @param enem2 ArrayList<Enemies>
     * Updates/adds to the enemies in level 1
     */
public void updateLVL1Enemies(ArrayList<Enemies> enem2){
       Random randomGenerator = new Random();
        int randoNum = chooseEnemyType(3);
        if(randoNum == 0){
            EnemyTree tree = new EnemyTree(0, getBoard().getWorldBot() - 115);
            this.generateEnemy(tree, enem2, getBoard().getP(), randomGenerator);
        }else if(randoNum == 1){
            EnemyBear bear = new EnemyBear(0, getBoard().getWorldBot() - 115);
            this.generateEnemy(bear, enem2, getBoard().getP(), randomGenerator);
        }else{
            EnemySunFlower flower = new EnemySunFlower(0, getBoard().getWorldBot() - 115);
            this.generateEnemy(flower, enem2, getBoard().getP(), randomGenerator);
        }
}

    /** 
     * @param enem2 ArrayList<Enemies>
     * Updates/adds to the enemies in level 2
     */
public void updateLVL2Enemies(ArrayList<Enemies> enem2){
        Random randomGenerator = new Random();
        int randoNum = chooseEnemyType(4);
        if(randoNum == 0){
            EnemyTree tree = new EnemyTree(0, getBoard().getWorldBot() - 115);
            this.generateEnemy(tree, enem2, getBoard().getP(), randomGenerator);
        }else if(randoNum == 1){
            EnemyBear bear = new EnemyBear(0, getBoard().getWorldBot() - 115);
            this.generateEnemy(bear, enem2, getBoard().getP(), randomGenerator);
        }else if (randoNum == 2){
            EnemySunFlower flower = new EnemySunFlower(0, getBoard().getWorldBot() - 115);
            this.generateEnemy(flower, enem2, getBoard().getP(), randomGenerator);
        } else{
            EnemyBee bee = new EnemyBee(0, getBoard().getWorldBot() - 115);
            this.generateEnemy(bee, enem2, getBoard().getP(), randomGenerator);
        }
}

    /** 
     * @param enem2 ArrayList<Enemies>
     * Updates/adds to the enemies in level 3
     */
public void updateLVL3Enemies(ArrayList<Enemies> enem2){
       Random randomGenerator = new Random();
        int randoNum = chooseEnemyType(5);
        if(randoNum == 0){
            EnemyTree tree = new EnemyTree(0, getBoard().getWorldBot() - 115);
            this.generateEnemy(tree, enem2, getBoard().getP(), randomGenerator);
        }else if(randoNum == 1){
            EnemyBear bear = new EnemyBear(0, getBoard().getWorldBot() - 115);
            this.generateEnemy(bear, enem2, getBoard().getP(), randomGenerator);
        }else if (randoNum == 2){
            EnemySunFlower flower = new EnemySunFlower(0, getBoard().getWorldBot() - 115);
            this.generateEnemy(flower, enem2, getBoard().getP(), randomGenerator);
        } else if (randoNum == 3){
            EnemyBee bee = new EnemyBee(0, getBoard().getWorldBot() - 115);
            this.generateEnemy(bee, enem2, getBoard().getP(), randomGenerator);
        } else{
            EnemyGnome gnomey = new EnemyGnome(0, getBoard().getWorldBot() - 115);
            this.generateEnemy(gnomey, enem2, getBoard().getP(), randomGenerator);
        }
}

    /** 
     * @param boss ArrayList<Enemies>
     * Creates Boss enemy (after all other enemies on the level have been killed) for level 1
     */
public void createLVL1Boss(ArrayList<Enemies> boss){
            Random randomGenerator = new Random();
            Fish fish = new Fish(0, getBoard().getWorldBot() - 115);
            this.generateEnemy(fish, boss, getBoard().getP(), randomGenerator);
}

    /** 
     * @param boss ArrayList<Enemies>
     * Creates Boss enemy (after all other enemies on the level have been killed) for level 2
     */
public void createLVL2Boss(ArrayList<Enemies> boss){
            Random randomGenerator = new Random();
            Random randomGenerator2 = new Random();
            Random randomGenerator3 = new Random();
            EnemyRobot robo = new EnemyRobot(0, getBoard().getWorldBot() - 115);
            this.generateEnemy(robo, boss, getBoard().getP(), randomGenerator);
            FireRobot firerobo = new FireRobot(0, getBoard().getWorldBot() - 115);
            this.generateEnemy(firerobo, boss, getBoard().getP(), randomGenerator2);
            WaterRobot waterrobo = new WaterRobot(0, getBoard().getWorldBot() - 115);
            this.generateEnemy(waterrobo, boss, getBoard().getP(), randomGenerator3);
}

    /** 
     * @param boss ArrayList<Enemies>
     * Creates Boss enemy (after all other enemies on the level have been killed) for level 3
     */
public void createLVL3Boss(ArrayList<Enemies> boss){
            Random randomGenerator = new Random();
            TreeBoss tree = new TreeBoss(0, getBoard().getWorldBot() - 115);
            this.generateEnemy(tree, boss, getBoard().getP(), randomGenerator);
}
    
    /** 
     * @param enem2 ArrayList<Enemies>
     * Creates a level to test enemy creation.
     */
public void testLevel(ArrayList<Enemies> enem2){
    //Random randomGenerator = new Random();
//        EnemySunFlower flower = new EnemySunFlower(0, getBoard().getWorldBot() - 115);
//        this.generateEnemy(flower, enem2, getBoard().getP(), randomGenerator);
        EnemyBee bee = new EnemyBee(5000, getBoard().getWorldBot() - 115);
        enem2.add(bee);
         //this.generateEnemy(bear, enem2, getBoard().getP(), randomGenerator);
}


//--------------------Getters/Setters--------------------------------------------
    public Board getBoard() {
        return board;
    }

    /**
     * @param board the board to set
     */
    public void setBoard(Board board) {
        this.board = board;
    }
}
