import java.awt.Graphics;
import java.util.ArrayList;


public class LevelHandler {
    private int Progress;
    private int ProgressNeeded;
    private int WorldBot = 700;
    private int WorldLeft = 0;
    private int WorldRight = 7478;
    private int WorldTop = 0;
    public LevelHandler(){}
    
    public void HandleLVL1Start(ArrayList<Enemies> enem, ArrayList<Terrain> terrain, EnemyGenerator generator){
        generator.createLVL1Enemies(enem);
        setProgress(0);
        setProgressNeeded(5);
        
        Rock rock = new Rock(800, WorldBot - 100);
        rock.setIgnoreLeft(true);
        Ramp ramp = new Ramp(700, WorldBot);
        Rock rock2 = new Rock(900, WorldBot - 100);
        Rock rock3 = new Rock(900, WorldBot - 200);
        rock3.setIgnoreLeft(true);
        Ramp ramp2 = new Ramp(800, WorldBot - 100);
        Rock rock4 = new Rock(1000, WorldBot - 100);

       terrain.add(rock);
       terrain.add(rock2);
       terrain.add(rock3);
        terrain.add(ramp);
        terrain.add(rock4);
        terrain.add(ramp2);
    }
    public void HandleLVL2Start(ArrayList<Enemies> enem, ArrayList<Terrain> terrain, EnemyGenerator generator){
        generator.createLVL2Enemies(enem);
        setProgress(0);
        setProgressNeeded(10);
    }
    public void HandleLVL3Start(ArrayList<Enemies> enem, ArrayList<Terrain> terrain, EnemyGenerator generator){
        generator.createLVL3Enemies(enem);
        setProgress(0);
        setProgressNeeded(15);
    }
//------------------------------------------------------
    /**
     * @return the Progress
     */
    public int getProgress() {
        return Progress;
    }

    /**
     * @param Progress the Progress to set
     */
    public void setProgress(int Progress) {
        this.Progress = Progress;
    }

    /**
     * @return the ProgresNeeded
     */
    public int getProgressNeeded() {
        return ProgressNeeded;
    }

    /**
     * @param ProgresNeeded the ProgresNeeded to set
     */
    public void setProgressNeeded(int ProgressNeeded) {
        this.ProgressNeeded = ProgressNeeded;
    }
}
