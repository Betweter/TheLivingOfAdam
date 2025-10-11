package com.entity;
import java.awt.image.BufferedImage;
import java.awt.Rectangle;

public class Entity {
    public int worldX, worldY;
    public int speed;

    public BufferedImage w1, w2, s1, s2, a1, a2, d1, d2;
    public char direction;

    public int spriteCounter = 0;
    public int spriteNr = 1;

    public Rectangle collisionArea;
    public int collisionAreaXDefault, collisionAreaYDefault;
    public boolean collision = false;


}   
