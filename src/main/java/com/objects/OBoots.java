package com.objects;

import java.io.IOException;

import javax.imageio.ImageIO;

import com.main.Gpanel;
import com.main.Utility;

public class OBoots extends SuperObject {
    
    public OBoots(Gpanel gp){
        name = "Boots";

        try{
            image = ImageIO.read(getClass().getResourceAsStream("/graphics/objects/boot_clrf.png"));
        } catch (IOException e) { e.printStackTrace();} 
    }

    public OBoots(Gpanel gp, int worldX, int worldY){
        name = "Boots";
        this.worldX = worldX * gp.frameActualSize;
        this.worldY = worldY * gp.frameActualSize;

        try{
            image = ImageIO.read(getClass().getResourceAsStream("/graphics/objects/boot_clrf.png"));
            Utility.scaledImage(image, gp.frameActualSize, gp.frameActualSize);
        } catch (IOException e) { e.printStackTrace();}
    }
}