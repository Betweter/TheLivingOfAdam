package com.objects;

import java.io.IOException;

import javax.imageio.ImageIO;

import com.main.Gpanel;

public class OBoots extends SuperObject {
    
    public OBoots(Gpanel gp){
        name = "Boots";

        try{
            image = ImageIO.read(getClass().getResourceAsStream("/graphics/objects/boot_clrf.png"));
        } catch (IOException e) { e.printStackTrace();} 
    }
}