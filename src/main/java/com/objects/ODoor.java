package com.objects;

import java.io.IOException;

import javax.imageio.ImageIO;

import com.main.Gpanel;

public class ODoor extends SuperObject {
        
    public ODoor(Gpanel gp){
        name = "Door";

        try{
            image = ImageIO.read(getClass().getResourceAsStream("/graphics/objects/door1.png"));
        } catch (IOException e) { e.printStackTrace();}
        collision = true;
    }
}
