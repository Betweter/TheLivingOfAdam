package com.entity;
import com.main.Gpanel;
import com.main.Keys;
import java.awt.Graphics2D;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.Rectangle;

public class Player extends Entity {
    Gpanel gp;
    Keys keys;

    public final int screenX;
    public final int screenY;

    int hasKeys = 0;

    public Player(Gpanel gp, Keys keys){
        this.gp = gp;
        this.keys = keys;
        worldX = gp.frameActualSize*10;
        worldY = gp.frameActualSize*10;
        
        screenX = gp.screenWidth/2 - (gp.frameActualSize/2);
        screenY = gp.screenHeight/2 - (gp.frameActualSize/2);
       
        int chunk = gp.frameActualSize/6;
        collisionAreaXDefault = chunk*2;
        collisionAreaYDefault = chunk*3;
        collisionArea = new Rectangle(collisionAreaXDefault, collisionAreaYDefault, chunk*2, chunk*2);
        speed = 5;

        getImage();
        direction = 's';
    }

    public void update(){
        if ( keys.isMovePressed()){

            if(keys.upPressed == true){
                direction = 'w';
            }
            else if(keys.downPressed == true){
                direction = 's';
            }
            else if(keys.leftPressed == true){
                direction = 'a';
            }
            else if(keys.rightPressed == true){
                direction = 'd';
            }

            collision = false;
            gp.collisionCheck.checkTile(this);
            int objectIndex = gp.collisionCheck.checkObject(this, true);
            pickUpObject(objectIndex);
            
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

    public void getImage(){
        try{
            w1 = ImageIO.read(getClass().getResourceAsStream("/graphics/player/w1.png"));
            w2 = ImageIO.read(getClass().getResourceAsStream("/graphics/player/w2.png"));
            s1 = ImageIO.read(getClass().getResourceAsStream("/graphics/player/s1.png"));
            s2 = ImageIO.read(getClass().getResourceAsStream("/graphics/player/s2.png"));
            d1 = ImageIO.read(getClass().getResourceAsStream("/graphics/player/d1.png"));
            d2 = ImageIO.read(getClass().getResourceAsStream("/graphics/player/d2.png"));
            a1 = ImageIO.read(getClass().getResourceAsStream("/graphics/player/a1.png"));
            a2 = ImageIO.read(getClass().getResourceAsStream("/graphics/player/a2.png"));

        } catch (IOException e){ e.printStackTrace(); }
    }

    public void paint(Graphics2D g2d){
    
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

    public void pickUpObject(int i){
        if(i != 999){
            String oName = gp.obj[i].name;

            switch(oName){
                case "Key":
                    hasKeys++;
                    gp.obj[i] = null;
                    gp.playEffect(1);
                    System.out.println("Nr of keys: " + hasKeys);
                    break;
                case "Door":
                    if(hasKeys > 0){
                        hasKeys--;
                        gp.playEffect(3);
                        gp.obj[i] = null;
                    }
                    break;
                case "Boots":
                    speed += 2;
                    gp.playEffect(2);
                    gp.obj[i] = null;
                    break;
            }
        }
    }
}
