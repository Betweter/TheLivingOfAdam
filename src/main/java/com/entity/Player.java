package com.entity;
import com.main.Gpanel;
import com.main.Keys;
import java.awt.Graphics2D;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class Player extends Entity {
    Gpanel gp;
    Keys keys;

    public Player(Gpanel gp, Keys keys){
        this.gp = gp;
        this.keys = keys;
        x = 100;
        y = 100;
        speed = 5;

        getImage();
        direction = 's';
    }

    public void update(){
        if ( keys.isMovePressed()){

            if(keys.upPressed == true){
                direction = 'w';
                y -= speed;
            }
            else if(keys.downPressed == true){
                direction = 's';
                y += speed;
            }
            else if(keys.leftPressed == true){
                direction = 'a';
                x -= speed;
            }
            else if(keys.rightPressed == true){
                direction = 'd';
                x += speed;
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

        } catch (IOException e){
            e.printStackTrace(); //error message to terminal
        }
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
                image = w1;
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

        g2d.drawImage(image, x, y, gp.frameActualSize, gp.frameActualSize, null);
    }
}
