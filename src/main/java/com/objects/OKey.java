package com.objects;
import java.io.IOException;
import javax.imageio.ImageIO;

public class OKey extends SuperObject {
    
    public OKey(){
        name = "Key";

        try{
            image = ImageIO.read(getClass().getResourceAsStream("/graphics/objects/giraffekey.png"));
        } catch (IOException e) { e.printStackTrace();}
    }
}
