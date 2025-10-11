package com.objects;

import java.io.IOException;
import javax.imageio.ImageIO;

import com.main.Gpanel;

public class OChest extends SuperObject {
        
    public OChest(Gpanel gp){
        name = "Chest";

        try{
            image = ImageIO.read(getClass().getResourceAsStream("/graphics/objects/chest1.png"));
        } catch (IOException e) { e.printStackTrace();}
        collision = true;
    }
    
}
