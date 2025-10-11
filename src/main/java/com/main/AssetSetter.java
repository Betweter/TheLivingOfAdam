package com.main;
import com.objects.*;


public class AssetSetter {
    Gpanel gp;

    public AssetSetter(Gpanel gp){
        this.gp = gp;
    }

    public void set(){
        gp.obj[0] = new OKey(gp);
        gp.obj[0].worldX = gp.frameActualSize*23;
        gp.obj[0].worldY = gp.frameActualSize*22;

        gp.obj[1] = new OKey(gp);
        gp.obj[1].worldX = gp.frameActualSize*23;
        gp.obj[1].worldY = gp.frameActualSize*45;

        gp.obj[2] = new ODoor(gp);
        gp.obj[2].worldX = gp.frameActualSize*24;
        gp.obj[2].worldY = gp.frameActualSize*42;

        gp.obj[3] = new ODoor(gp);
        gp.obj[3].worldX = gp.frameActualSize*20;
        gp.obj[3].worldY = gp.frameActualSize*12;

        gp.obj[4] = new OChest(gp);
        gp.obj[4].worldX = gp.frameActualSize*32;
        gp.obj[4].worldY = gp.frameActualSize*11;
        
        gp.obj[5] = new OBoots(gp);
        gp.obj[5].worldX = gp.frameActualSize*42;
        gp.obj[5].worldY = gp.frameActualSize*38;
    }
    
}
