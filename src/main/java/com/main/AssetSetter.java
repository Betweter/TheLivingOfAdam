package com.main;

import com.objects.*;
import com.entity.*;

public class AssetSetter {
    Gpanel gp;

    public AssetSetter(Gpanel gp){
        this.gp = gp;
    }

    public void setObject(){
        gp.obj[0] = new OKey(gp, 22, 23);
        gp.obj[1] = new ODoor(gp, 21, 10);
        gp.obj[2] = new OChest(gp, 40, 40);
        gp.obj[3] = new OBoots(gp, 5, 20);
    }

    public void setNPC(){
        
        gp.npc[0] = new N_Dorothy(gp);
        gp.npc[0].worldX = gp.frameActualSize * 15;
        gp.npc[0].worldY = gp.frameActualSize * 15;
    }
    
}
