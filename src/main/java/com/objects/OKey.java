package com.objects;
import java.io.IOException;
import javax.imageio.ImageIO;

import com.main.Gpanel;
import com.main.Utility;

public class OKey extends SuperObject {
    
    public OKey(Gpanel gp){
        name = "Key";

        try{
            image = ImageIO.read(getClass().getResourceAsStream("/graphics/objects/giraffekey.png"));
            Utility.scaledImage(image, gp.frameActualSize, gp.frameActualSize);
        } catch (IOException e) { e.printStackTrace();}
    }
}
