package com.entity;
import com.main.Gpanel;
import com.main.Keys;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.Rectangle;

public class Player extends Entity {
    Keys keys;

    public final int screenX;
    public final int screenY;

    //public int hasKeys = 0;

    public Player(Gpanel gp, Keys keys){
        super(gp);
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

    @Override
    public void update(){
        
        if ( keys.isMovePressed()){
            setAction();
        
            collision = false;
            gp.collisionCheck.checkTile(this);
            int objectIndex = gp.collisionCheck.checkObject(this, true);
            pickUpObject(objectIndex);

            int entityIndex = gp.collisionCheck.checkEntity(this, gp.npc);
            interactNPC(entityIndex);
                
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

    @Override
    public void setAction(){
        if(keys.upPressed == true){
            direction = 'w';
        } else if(keys.downPressed == true){
            direction = 's';
        } else if(keys.leftPressed == true){
            direction = 'a';
        } else if(keys.rightPressed == true){
            direction = 'd';
        }
    }

    @Override
    public void getImage(){
        w1 = setup("/graphics/player/w1.png");
        w2 = setup("/graphics/player/w2.png");
        s1 = setup("/graphics/player/s1.png");
        s2 = setup("/graphics/player/s2.png");
        d1 = setup("/graphics/player/d1.png");
        d2 = setup("/graphics/player/d2.png");
        a1 = setup("/graphics/player/a1.png");
        a2 = setup("/graphics/player/a2.png");
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

        g2d.drawImage(image, screenX, screenY, null);
    }

    public void pickUpObject(int i){
        if(i != 999){
        
        }
    }

    public void interactNPC(int i){
        if(i != 999){
            if(gp.keys.enterPressed){
                gp.gState = gp.dialogueState;
                gp.npc[i].speak();
            }
        }
        gp.keys.enterPressed = false;
    }
}



/* from pickupobject if.
 *     String oName = gp.obj[i].name;

            switch(oName){
                case "Key":
                    hasKeys++;
                    gp.obj[i] = null;
                    gp.playEffect(1);
                    gp.ui.showMessage("You've found a giraffe!");
                    break;
                case "Door":
                    if(hasKeys > 0){
                        hasKeys--;
                        gp.playEffect(3);
                        gp.obj[i] = null;
                    } else {
                        gp.ui.showMessage("Find a giraffe, she'll help you!");
                    }
                    break;
                case "Boots":
                    speed += 2;
                    gp.playEffect(2);
                    gp.obj[i] = null;
                    break;
                case "Chest":
                    gp.ui.gameFinished = true;
                    gp.stopMusic();
                    gp.playEffect(4);
                    break;
 */