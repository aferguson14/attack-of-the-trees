import java.awt.*;
import java.awt.event.KeyEvent;
import static java.lang.Math.abs;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;

public class EnemyGenerator {
    
    private Board board;
    public EnemyGenerator(Board b){
        board = b;
    }
    
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
    
    public int chooseEnemyType(int range){
        Random randomInt = new Random();
        int number = randomInt.nextInt(range);
        return number;
    }
    
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
    
public void testLevel(ArrayList<Enemies> enem2){
    //Random randomGenerator = new Random();
//        EnemySunFlower flower = new EnemySunFlower(0, getBoard().getWorldBot() - 115);
//        this.generateEnemy(flower, enem2, getBoard().getP(), randomGenerator);
        Fish fish = new Fish(2800, getBoard().getWorldBot() - 450);
        enem2.add(fish);
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
