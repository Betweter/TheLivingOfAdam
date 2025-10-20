package com.objects;

import java.io.IOException;
import javax.imageio.ImageIO;

import com.main.Gpanel;
import com.main.Utility;

public class OChest extends SuperObject {
    
    public OChest(Gpanel gp, int worldX, int worldY){
        name = "Chest";
        this.worldX = worldX * gp.frameActualSize;
        this.worldY = worldY * gp.frameActualSize;

        try{
            image = ImageIO.read(getClass().getResourceAsStream("/graphics/objects/chest1.png"));
            Utility.scaledImage(image, gp.frameActualSize, gp.frameActualSize);
        } catch (IOException e) { e.printStackTrace();}
        collision = true;
    }
}
