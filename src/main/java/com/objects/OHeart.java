package com.objects;

import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import com.main.Gpanel;
import com.main.Utility;

public class OHeart extends SuperObject{

    Gpanel gp;
    public BufferedImage image2, image3;
    
    public OHeart(Gpanel gp){
       
        this.gp = gp;
        name = "Heart";
    
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/graphics/objects/heart_full.png"));
            image2 = ImageIO.read(getClass().getResourceAsStream("/graphics/objects/heart_half.png"));
            image3 = ImageIO.read(getClass().getResourceAsStream("/graphics/objects/heart_empty.png"));
            image = Utility.scaledImage(image, gp.frameActualSize, gp.frameActualSize);
            image2 = Utility.scaledImage(image2, gp.frameActualSize, gp.frameActualSize);
            image3 = Utility.scaledImage(image3, gp.frameActualSize, gp.frameActualSize);
        } catch (IOException e) { e.printStackTrace();}
    }
}
