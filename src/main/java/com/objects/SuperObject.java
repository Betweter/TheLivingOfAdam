package com.objects;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import com.main.Gpanel;

public abstract class SuperObject {
    
    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;
    public Rectangle collisionArea = new Rectangle(0,0, 48, 48); //frame size, to be generalized
    public int collisionAreaXDefault = 0;
    public int collisonAreaYDefault = 0;

    public void draw(Graphics2D g2d, Gpanel gp){

            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            if(gp.tileManager.pixelFits(worldX, worldY)){
                g2d.drawImage(image, screenX, screenY, gp.frameActualSize, gp.frameActualSize, null);  
            }
    }
}
