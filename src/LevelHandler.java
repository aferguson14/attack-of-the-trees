
import java.awt.Graphics;
import java.util.ArrayList;


public class LevelHandler {
    private int Progress;
    private int ProgressNeeded;
    private int ProgressNeededLVL1 = 5;
    private int ProgressNeededLVL2 = 10;
    private int ProgressNeededLVL3 = 15;
    private int TotalProgressNeeded = getProgressNeededLVL1() + getProgressNeededLVL2() + getProgressNeededLVL3();
    private int WorldBot = 700;
    private int WorldLeft = 0;
    private int WorldRight = 7478;
    private int WorldTop = 0;
    public LevelHandler(){}
    
    public void HandleLVL1Start(ArrayList<Enemies> enem, ArrayList<Terrain> terrain, EnemyGenerator generator){
        generator.createLVL1Enemies(enem);
        setProgress(0);
        setProgressNeeded(getProgressNeededLVL1());
        
        createEndOfLevelWalls(terrain);
        
        createSegment1(terrain);
        
        createSegment2(terrain);
        
        createSegment3(terrain);
        
        createSegment4(terrain);

        
    }
    public void HandleLVL2Start(ArrayList<Enemies> enem, ArrayList<Terrain> terrain, EnemyGenerator generator){
        generator.createLVL2Enemies(enem);
        setProgress(0);
        setProgressNeeded(getProgressNeededLVL2());
    }
    public void HandleLVL3Start(ArrayList<Enemies> enem, ArrayList<Terrain> terrain, EnemyGenerator generator){
        generator.createLVL3Enemies(enem);
        setProgress(0);
        setProgressNeeded(getProgressNeededLVL3());
    }
    
    
    
    public void createEndOfLevelWalls(ArrayList<Terrain> terrain){
        
        //End of level Walls
        Rock block1 = new Rock(-100, WorldBot - 100);
        Rock block2 = new Rock(-100, WorldBot - 200);
        Rock block3 = new Rock(-100, WorldBot - 300);
        Rock block4 = new Rock(-100, WorldBot - 400);
        Rock block5 = new Rock(-100, WorldBot - 500);
        Rock block6 = new Rock(-100, WorldBot - 600);
	Rock block7 = new Rock(-100, WorldBot - 700);
	Rock block8 = new Rock(-100, WorldBot);
        
        terrain.add(block1);
        terrain.add(block2);
        terrain.add(block3);
        terrain.add(block4);
        terrain.add(block5);
        terrain.add(block6);
        terrain.add(block7);
	terrain.add(block8);
        
        Rock blockEnd1 = new Rock(6950, WorldBot - 100);
        Rock blockEnd2 = new Rock(6950, WorldBot - 200);
        Rock blockEnd3 = new Rock(6950, WorldBot - 300);
        Rock blockEnd4 = new Rock(6950, WorldBot - 400);
        Rock blockEnd5 = new Rock(6950, WorldBot - 500);
        Rock blockEnd6 = new Rock(6950, WorldBot - 600);
	Rock blockEnd7 = new Rock(6950, WorldBot - 700);
        Rock blockEnd8 = new Rock(6950, WorldBot);

        terrain.add(blockEnd1);
        terrain.add(blockEnd2);
        terrain.add(blockEnd3);
        terrain.add(blockEnd4);
        terrain.add(blockEnd5);
        terrain.add(blockEnd6);
        terrain.add(blockEnd7);
	terrain.add(blockEnd8);
    }
    
    public void createSegment1(ArrayList<Terrain> terrain){
        Platform plat1 = new Platform(0, WorldBot - 200);
        Rock block1 = new Rock(600, WorldBot - 100);
        Rock block2 = new Rock(700, WorldBot - 100);
        Rock block3 = new Rock(700, WorldBot - 200);
        Rock block4 = new Rock(800, WorldBot - 100);
        Rock block5 = new Rock(900, WorldBot - 100);
        Rock block6 = new Rock(900, WorldBot - 200);
        Rock block7 = new Rock(1000, WorldBot - 100);
        Platform plat2 = new Platform(1200, WorldBot - 300);
        plat2.setIgnoreRight(true);
        Platform plat3 = new Platform(1300, WorldBot - 300);
        plat3.setIgnoreLeft(true);
        plat3.setIgnoreRight(true);
        Platform plat4 = new Platform(1400, WorldBot - 300);
        plat4.setIgnoreLeft(true);
        plat4.setIgnoreRight(true);
        Platform plat5 = new Platform(1500, WorldBot - 300);
        plat5.setIgnoreLeft(true);
        Platform plat6 = new Platform(1800, WorldBot - 450);
        plat6.setIgnoreLeft(true);
        Platform plat7 = new Platform(1700, WorldBot - 450);
        plat7.setIgnoreRight(true);
        
        terrain.add(plat1);
        terrain.add(block1);
        terrain.add(block2);
        terrain.add(block3);
        terrain.add(block4);
        terrain.add(block5);
        terrain.add(block6);
        terrain.add(block7);
        terrain.add(plat2);
        terrain.add(plat3);
        terrain.add(plat4);
        terrain.add(plat5);
        terrain.add(plat6);  
        terrain.add(plat7);
    }
    
    public void createSegment2(ArrayList<Terrain> terrain){
        int RightShift = 2100;
        Rock block1 = new Rock(0 + RightShift, WorldBot - 100);
        block1.setIgnoreRight(true);
        Rock block2 = new Rock(100 + RightShift, WorldBot - 100);
        block2.setIgnoreLeft(true);
        block2.setIgnoreRight(true);
        Rock block3 = new Rock(200 + RightShift, WorldBot - 100);
        block3.setIgnoreLeft(true);
        block3.setIgnoreRight(true);
        Rock block4 = new Rock(300 + RightShift, WorldBot - 100);
        block4.setIgnoreLeft(true);
        block4.setIgnoreRight(true);
        Rock block5 = new Rock(400 + RightShift, WorldBot - 100);
        block5.setIgnoreLeft(true);
        block5.setIgnoreRight(true);
        Rock block6 = new Rock(500 + RightShift, WorldBot - 100);
        block6.setIgnoreLeft(true);
        block6.setIgnoreRight(true);
        Rock block7 = new Rock(600 + RightShift, WorldBot - 100);
        block7.setIgnoreLeft(true);

        Rock lvl2block = new Rock(0 + RightShift, WorldBot - 400);
        lvl2block.setIgnoreRight(true);
        Rock lvl2block1 = new Rock(100 + RightShift, WorldBot - 400);
        lvl2block1.setIgnoreLeft(true);
        lvl2block1.setIgnoreRight(true);
        Rock lvl2block2 = new Rock(200 + RightShift, WorldBot - 400);
        lvl2block2.setIgnoreLeft(true);
        lvl2block2.setIgnoreRight(true);
        Rock lvl2block3 = new Rock(300 + RightShift, WorldBot - 400);
        lvl2block3.setIgnoreLeft(true);
        lvl2block3.setIgnoreRight(true);
        Rock lvl2block4 = new Rock(400 + RightShift, WorldBot - 400);
        lvl2block4.setIgnoreLeft(true);
        lvl2block4.setIgnoreRight(true);
        Rock lvl2block5 = new Rock(500 + RightShift, WorldBot - 400);
        lvl2block5.setIgnoreRight(true);
        lvl2block5.setIgnoreLeft(true);
        Rock lvl2block6 = new Rock(600 + RightShift, WorldBot - 400);
        lvl2block6.setIgnoreLeft(true);
        
        Rock lvl3block = new Rock(100 + RightShift, WorldBot - 500);
         lvl3block.setIgnoreRight(true);
        Rock lvl3block1 = new Rock(200 + RightShift, WorldBot - 500);
        lvl3block1.setIgnoreLeft(true);
        lvl3block1.setIgnoreRight(true);
        Rock lvl3block2 = new Rock(300 + RightShift, WorldBot - 500);
        lvl3block2.setIgnoreLeft(true);
        lvl3block2.setIgnoreRight(true);
        Rock lvl3block3 = new Rock(400 + RightShift, WorldBot - 500);
        lvl3block3.setIgnoreLeft(true);
        lvl3block3.setIgnoreRight(true);
        Rock lvl3block4 = new Rock(500 + RightShift, WorldBot - 500);
        lvl3block4.setIgnoreLeft(true);
//        Platform plat2 = new Platform(1200, WorldBot - 300);
//        plat2.setIgnoreRight(true);
//        Platform plat3 = new Platform(1300, WorldBot - 300);
//        plat3.setIgnoreLeft(true);
//        plat3.setIgnoreRight(true);
//        Platform plat4 = new Platform(1400, WorldBot - 300);
//        plat4.setIgnoreLeft(true);
//        plat4.setIgnoreRight(true);
//        Platform plat5 = new Platform(1500, WorldBot - 300);
//        plat5.setIgnoreLeft(true);
//        Platform plat6 = new Platform(1800, WorldBot - 450);
        

        terrain.add(block1);
        terrain.add(block2);
        terrain.add(block3);
        terrain.add(block4);
        terrain.add(block5);
        terrain.add(block6);
        terrain.add(block7);
        terrain.add(lvl2block);
        terrain.add(lvl2block1);
        terrain.add(lvl2block2);
        terrain.add(lvl2block3);
        terrain.add(lvl2block4);
        terrain.add(lvl2block5);
        terrain.add(lvl2block6);
        terrain.add(lvl3block);
        terrain.add(lvl3block1);
        terrain.add(lvl3block2);
        terrain.add(lvl3block3);
        terrain.add(lvl3block4);
    }
    
    public void createSegment3(ArrayList<Terrain> terrain){
        int RightShift = 3400;
        Platform plat1 = new Platform(-600 + RightShift, WorldBot-400);
        plat1.setIgnoreLeft(true);
        plat1.setIgnoreRight(true);
        Platform plat2 = new Platform(-500 + RightShift, WorldBot-400);
        plat2.setIgnoreLeft(true);
        plat2.setIgnoreRight(true);
        Platform plat3 = new Platform(-400 + RightShift, WorldBot-400);
        plat3.setIgnoreLeft(true);
        plat3.setIgnoreRight(true);
        Platform plat4 = new Platform(-300 + RightShift, WorldBot-400);
        plat4.setIgnoreLeft(true);
        plat4.setIgnoreRight(true);
        Platform plat5 = new Platform(-200 + RightShift, WorldBot-400);
        plat5.setIgnoreLeft(true);
        plat5.setIgnoreRight(true);
        Platform plat6 = new Platform(-100 + RightShift, WorldBot-400);
        plat6.setIgnoreLeft(true);
        plat6.setIgnoreRight(true);
        Rock block1 = new Rock(0 + RightShift, WorldBot - 100);
        block1.setIgnoreRight(true);
        Rock block2 = new Rock(100 + RightShift, WorldBot - 100);
        block2.setIgnoreLeft(true);
        block2.setIgnoreRight(true);
        Rock block3 = new Rock(200 + RightShift, WorldBot - 100);
        block3.setIgnoreLeft(true);
        block3.setIgnoreRight(true);
        Rock block4 = new Rock(300 + RightShift, WorldBot - 100);
        block4.setIgnoreLeft(true);
        block4.setIgnoreRight(true);
        Rock block5 = new Rock(400 + RightShift, WorldBot - 100);
        block5.setIgnoreLeft(true);
        block5.setIgnoreRight(true);
        Rock block6 = new Rock(500 + RightShift, WorldBot - 100);
        block6.setIgnoreLeft(true);
        block6.setIgnoreRight(true);
        Rock block7 = new Rock(600 + RightShift, WorldBot - 100);
        block7.setIgnoreLeft(true);

        Rock lvl2block = new Rock(0 + RightShift, WorldBot - 400);
        lvl2block.setIgnoreRight(true);
        Rock lvl2block1 = new Rock(100 + RightShift, WorldBot - 400);
        lvl2block1.setIgnoreLeft(true);
        lvl2block1.setIgnoreRight(true);
        Rock lvl2block2 = new Rock(200 + RightShift, WorldBot - 400);
        lvl2block2.setIgnoreLeft(true);
        lvl2block2.setIgnoreRight(true);
        Rock lvl2block3 = new Rock(300 + RightShift, WorldBot - 400);
        lvl2block3.setIgnoreLeft(true);
        lvl2block3.setIgnoreRight(true);
        Rock lvl2block4 = new Rock(400 + RightShift, WorldBot - 400);
        lvl2block4.setIgnoreLeft(true);
        lvl2block4.setIgnoreRight(true);
        Rock lvl2block5 = new Rock(500 + RightShift, WorldBot - 400);
        lvl2block5.setIgnoreRight(true);
        lvl2block5.setIgnoreLeft(true);
        Rock lvl2block6 = new Rock(600 + RightShift, WorldBot - 400);
        lvl2block6.setIgnoreLeft(true);
        
        Rock lvl3block = new Rock(100 + RightShift, WorldBot - 500);
         lvl3block.setIgnoreRight(true);
        Rock lvl3block1 = new Rock(200 + RightShift, WorldBot - 500);
        lvl3block1.setIgnoreLeft(true);
        lvl3block1.setIgnoreRight(true);
        Rock lvl3block2 = new Rock(300 + RightShift, WorldBot - 500);
        lvl3block2.setIgnoreLeft(true);
        lvl3block2.setIgnoreRight(true);
        Rock lvl3block3 = new Rock(400 + RightShift, WorldBot - 500);
        lvl3block3.setIgnoreLeft(true);
        lvl3block3.setIgnoreRight(true);
        Rock lvl3block4 = new Rock(500 + RightShift, WorldBot - 500);
        lvl3block4.setIgnoreLeft(true);
//        Platform plat2 = new Platform(1200, WorldBot - 300);
//        plat2.setIgnoreRight(true);
//        Platform plat3 = new Platform(1300, WorldBot - 300);
//        plat3.setIgnoreLeft(true);
//        plat3.setIgnoreRight(true);
//        Platform plat4 = new Platform(1400, WorldBot - 300);
//        plat4.setIgnoreLeft(true);
//        plat4.setIgnoreRight(true);
//        Platform plat5 = new Platform(1500, WorldBot - 300);
//        plat5.setIgnoreLeft(true);
//        Platform plat6 = new Platform(1800, WorldBot - 450);
        
        terrain.add(plat1);
        terrain.add(plat2);
        terrain.add(plat3);
        terrain.add(plat4);
        terrain.add(plat5);
        terrain.add(plat6);
        terrain.add(block1);
        terrain.add(block2);
        terrain.add(block3);
        terrain.add(block4);
        terrain.add(block5);
        terrain.add(block6);
        terrain.add(block7);
        terrain.add(lvl2block);
        terrain.add(lvl2block1);
        terrain.add(lvl2block2);
        terrain.add(lvl2block3);
        terrain.add(lvl2block4);
        terrain.add(lvl2block5);
        terrain.add(lvl2block6);
        terrain.add(lvl3block);
        terrain.add(lvl3block1);
        terrain.add(lvl3block2);
        terrain.add(lvl3block3);
        terrain.add(lvl3block4);
    }
    
    public void createSegment4(ArrayList<Terrain> terrain){
        int RightShift = 4200;
        
        Platform plat1 = new Platform(RightShift + 50, WorldBot - 200);
        plat1.setIgnoreRight(true);
        Platform plat2 = new Platform(150 + RightShift, WorldBot - 200);
        plat2.setIgnoreLeft(true);
        Platform plat3 = new Platform(6850, WorldBot - 200);
        Rock block1 = new Rock(800 + RightShift, WorldBot - 100);
        Rock block2 = new Rock(900 + RightShift, WorldBot - 100);
        Rock block3 = new Rock(1000 + RightShift, WorldBot - 100);
        Rock block4 = new Rock(1100 + RightShift, WorldBot - 100);
        Rock block5 = new Rock(900 + RightShift, WorldBot - 200);
        Rock block6 = new Rock(1000 + RightShift, WorldBot - 200);
        Rock block7 = new Rock(1100 + RightShift, WorldBot - 200);
        Rock block8 = new Rock(1000 + RightShift, WorldBot - 300);
        Rock block9 = new Rock(1100 + RightShift, WorldBot - 300);
        Rock block10 = new Rock(1100 + RightShift, WorldBot - 400);
        block10.setIgnoreRight(true);
        //-----------------------------------------------------------------------
        Rock block11 = new Rock(1200 + RightShift, WorldBot - 400);
        block11.setIgnoreLeft(true);
        block11.setIgnoreRight(true);
        Rock block12 = new Rock(1300 + RightShift, WorldBot - 400);
        block12.setIgnoreLeft(true);
        block12.setIgnoreRight(true);
        Rock block13 = new Rock(1400 + RightShift, WorldBot - 400);
        block13.setIgnoreLeft(true);
        block13.setIgnoreRight(true);
        Rock block14 = new Rock(1500 + RightShift, WorldBot - 400);
        block14.setIgnoreLeft(true);
        block14.setIgnoreRight(true);
        Rock block15 = new Rock(1600 + RightShift, WorldBot - 400);
        block15.setIgnoreLeft(true);
        block15.setIgnoreRight(true);
        Rock block16 = new Rock(1700 + RightShift, WorldBot - 400);
        block16.setIgnoreLeft(true);
        block16.setIgnoreRight(true);
        //------------------------------------------------------------------------
        Rock block17 = new Rock(1800 + RightShift, WorldBot - 400);
        block17.setIgnoreLeft(true);
        Rock block18 = new Rock(1800 + RightShift, WorldBot - 300);
        Rock block19 = new Rock(1900 + RightShift, WorldBot - 300);
        Rock block20 = new Rock(1800 + RightShift, WorldBot - 200);
        Rock block21 = new Rock(1900 + RightShift, WorldBot - 200);
        Rock block22 = new Rock(2000 + RightShift, WorldBot - 200);
        Rock block23 = new Rock(1800 + RightShift, WorldBot - 100);
        Rock block24 = new Rock(1900 + RightShift, WorldBot - 100);
        Rock block25 = new Rock(2000 + RightShift, WorldBot - 100);
        Rock block26 = new Rock(2100 + RightShift, WorldBot - 100);
        
        Rock block27 = new Rock(1200 + RightShift, WorldBot - 300);
        Rock block28 = new Rock(1200 + RightShift, WorldBot - 200);
        Rock block29 = new Rock(1200 + RightShift, WorldBot - 100);
        Rock block30 = new Rock(1300 + RightShift, WorldBot - 300);
        Rock block31 = new Rock(1300 + RightShift, WorldBot - 200);
        Rock block32 = new Rock(1300 + RightShift, WorldBot - 100);
        Rock block33 = new Rock(1400 + RightShift, WorldBot - 300);
        Rock block34 = new Rock(1400 + RightShift, WorldBot - 200);
        Rock block35 = new Rock(1400 + RightShift, WorldBot - 100);
        Rock block36 = new Rock(1500 + RightShift, WorldBot - 300);
        Rock block37 = new Rock(1500 + RightShift, WorldBot - 200);
        Rock block38 = new Rock(1500 + RightShift, WorldBot - 100);
        Rock block39 = new Rock(1600 + RightShift, WorldBot - 300);
        Rock block40 = new Rock(1600 + RightShift, WorldBot - 200);
        Rock block41 = new Rock(1600 + RightShift, WorldBot - 100);
        Rock block42 = new Rock(1700 + RightShift, WorldBot - 300);
        Rock block43 = new Rock(1700 + RightShift, WorldBot - 200);
        Rock block44 = new Rock(1700 + RightShift, WorldBot - 100);

        terrain.add(plat1);
        terrain.add(plat2);
        terrain.add(plat3);
        terrain.add(block1);
        terrain.add(block2);
        terrain.add(block3);
        terrain.add(block4);
        terrain.add(block5);
        terrain.add(block6);
        terrain.add(block7);
        terrain.add(block8);
        terrain.add(block9);
        terrain.add(block10);
        terrain.add(block11);
        terrain.add(block12);
        terrain.add(block13);
        terrain.add(block14);
        terrain.add(block15);
        terrain.add(block16);
        terrain.add(block17);
        terrain.add(block18);
        terrain.add(block19);
        terrain.add(block20);
        terrain.add(block21);
        terrain.add(block22);
        terrain.add(block23);
        terrain.add(block24);
        terrain.add(block25);
        terrain.add(block26);
        
        terrain.add(block27);
        terrain.add(block28);
        terrain.add(block29);
        terrain.add(block30);
        terrain.add(block31);
        terrain.add(block32);
        terrain.add(block33);
        terrain.add(block34);
        terrain.add(block35);
        terrain.add(block36);
        terrain.add(block37);
        terrain.add(block38);
        terrain.add(block39);
        terrain.add(block40);
        terrain.add(block41);
        terrain.add(block42);
        terrain.add(block43);
        terrain.add(block44);
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

    /**
     * @return the TotalProgressNeeded
     */
    public int getTotalProgressNeeded() {
        return TotalProgressNeeded;
    }

    /**
     * @param TotalProgressNeeded the TotalProgressNeeded to set
     */
    public void setTotalProgressNeeded(int TotalProgressNeeded) {
        this.TotalProgressNeeded = TotalProgressNeeded;
    }

    /**
     * @return the ProgressNeededLVL1
     */
    public int getProgressNeededLVL1() {
        return ProgressNeededLVL1;
    }

    /**
     * @param ProgressNeededLVL1 the ProgressNeededLVL1 to set
     */
    public void setProgressNeededLVL1(int ProgressNeededLVL1) {
        this.ProgressNeededLVL1 = ProgressNeededLVL1;
    }

    /**
     * @return the ProgressNeededLVL2
     */
    public int getProgressNeededLVL2() {
        return ProgressNeededLVL2;
    }

    /**
     * @param ProgressNeededLVL2 the ProgressNeededLVL2 to set
     */
    public void setProgressNeededLVL2(int ProgressNeededLVL2) {
        this.ProgressNeededLVL2 = ProgressNeededLVL2;
    }

    /**
     * @return the ProgressNeededLVL3
     */
    public int getProgressNeededLVL3() {
        return ProgressNeededLVL3;
    }

    /**
     * @param ProgressNeededLVL3 the ProgressNeededLVL3 to set
     */
    public void setProgressNeededLVL3(int ProgressNeededLVL3) {
        this.ProgressNeededLVL3 = ProgressNeededLVL3;
    }
}
