package com.entity;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.main.Gpanel;
import com.main.Utility;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public abstract class Entity {
    public int worldX, worldY;
    public int speed = 3;

    public BufferedImage w1, w2, s1, s2, a1, a2, d1, d2;
    public char direction = 's';

    public int spriteCounter = 0;
    public int spriteNr = 1;

    Gpanel gp;
    public Rectangle collisionArea;
    public int collisionAreaXDefault, collisionAreaYDefault;
    public boolean collision = false;
    public int frameCounter = 0;

    public Entity(Gpanel gp){
        this.gp = gp;
        collisionArea = new Rectangle(0,0, gp.frameActualSize-1, gp.frameActualSize-1);
    }
    
    public abstract void getImage();

    public BufferedImage setup(String path){
        BufferedImage scaledImage = null;
        try{
            scaledImage = ImageIO.read(getClass().getResourceAsStream(path));
            scaledImage = Utility.scaledImage(scaledImage, gp.frameActualSize, gp.frameActualSize);
        } catch (IOException e) {e.printStackTrace();}

        return scaledImage;
    }

    public void draw(Graphics2D g2d){
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if(gp.tileManager.pixelFits(worldX, worldY)){

            BufferedImage image = null;
            switch(direction){
                case 'w':    
                    if (spriteNr == 1){
                        image = w1;
                    } else if (spriteNr == 2){
                        image = w2;
                    }
                    break;
                case 's':
                    if (spriteNr == 1){
                        image = s1;
                    } else if (spriteNr == 2){
                        image = s2;
                    }
                    break;
                case 'a':
                    if (spriteNr == 1){
                        image = a1;
                    } else if (spriteNr == 2){
                        image = a2;
                    }
                    break;
                case 'd':
                    if (spriteNr == 1){
                        image = d1;
                    } else if (spriteNr == 2){
                        image = d2;
                    }
                    break;
            }
            g2d.drawImage(image, screenX, screenY, gp.frameActualSize, gp.frameActualSize, null);  
        }
    }

    public abstract void setAction();
    
    public void update(){

        setAction();
        collision = false;
        gp.collisionCheck.checkTile(this);
        gp.collisionCheck.checkObject(this, false);
        gp.collisionCheck.checkPlayer(this);

        if (collision == false){
            switch(direction){
                case 'w': worldY -= speed; break;
                case 's': worldY += speed; break;
                case 'a': worldX -= speed; break;
                case 'd': worldX += speed; break;
            }
        }

        spriteCounter++;
        if(spriteCounter > 13){
            if (spriteNr == 1){
                spriteNr = 2;
            } else if (spriteNr == 2){
                spriteNr = 1;
            }
            spriteCounter = 0;
        }       
    }
}   
