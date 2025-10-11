package com.objects;

import java.io.IOException;
import javax.imageio.ImageIO;

public class OChest extends SuperObject {
        public OChest(){
        name = "Chest";

        try{
            image = ImageIO.read(getClass().getResourceAsStream("/graphics/objects/chest1.png"));
        } catch (IOException e) { e.printStackTrace();}
        collision = true;
    }
    
}
