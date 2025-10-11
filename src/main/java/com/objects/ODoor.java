package com.objects;

import java.io.IOException;

import javax.imageio.ImageIO;

public class ODoor extends SuperObject {
        public ODoor(){
        name = "Door";

        try{
            image = ImageIO.read(getClass().getResourceAsStream("/graphics/objects/door1.png"));
        } catch (IOException e) { e.printStackTrace();}
        collision = true;
    }
}
