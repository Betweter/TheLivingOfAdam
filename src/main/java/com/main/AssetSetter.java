package com.main;

import com.objects.*;
import com.entity.*;

public class AssetSetter {
    Gpanel gp;

    public AssetSetter(Gpanel gp){
        this.gp = gp;
    }

    public void setObject(){
        gp.obj[0] = new OKey(gp);
        gp.obj[0].worldX = gp.frameActualSize*23;
        gp.obj[0].worldY = gp.frameActualSize*22;

    }

    public void setNPC(){
        
        gp.npc[0] = new N_Dorothy(gp);
        gp.npc[0].worldX = gp.frameActualSize * 15;
        gp.npc[0].worldY = gp.frameActualSize * 15;
    }
    
}

/* from "chest hunting"
       gp.obj[0] = new OKey(gp);
        gp.obj[0].worldX = gp.frameActualSize*23;
        gp.obj[0].worldY = gp.frameActualSize*22;
 */