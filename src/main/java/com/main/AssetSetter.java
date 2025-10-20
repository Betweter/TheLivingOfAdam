package com.main;

import com.objects.*;
import com.entity.*;

public class AssetSetter {
    Gpanel gp;

    public AssetSetter(Gpanel gp){
        this.gp = gp;
    }

    public void setObject(){
        gp.obj[0] = new OKey(gp, 48, 48);
        gp.obj[1] = new ODoor(gp, 27,8);
        gp.obj[2] = new OChest(gp, 28, 8);
        gp.obj[3] = new OBoots(gp, 48, 18);
    }

    public void setNPC(){
        
        gp.npc[0] = new N_Dorothy(gp, 15, 18);
        gp.npc[1] = new N_Crow(gp, 5, 5);
        gp.npc[2] = new N_Crow(gp, 45, 48);
    }
    
}
