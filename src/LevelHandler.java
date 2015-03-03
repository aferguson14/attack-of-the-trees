
import java.awt.Graphics;
import java.util.ArrayList;


public class LevelHandler {
    private int Progress;
    private int ProgressNeeded;
    public LevelHandler(){}
    
    public void HandleLVL1Start(ArrayList<Enemies> enem, EnemyGenerator generator){
        generator.createLVL1Enemies(enem);
        setProgress(0);
        setProgressNeeded(5);
    }
    public void HandleLVL2Start(ArrayList<Enemies> enem, EnemyGenerator generator){
        generator.createLVL2Enemies(enem);
        setProgress(0);
        setProgressNeeded(10);
    }
    public void HandleLVL3Start(ArrayList<Enemies> enem, EnemyGenerator generator){
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
