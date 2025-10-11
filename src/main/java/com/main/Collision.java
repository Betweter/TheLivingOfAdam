package com.main;
import com.entity.Entity;


public class Collision {
    
    Gpanel gp;

    public Collision(Gpanel gp){
        this.gp = gp;
    }

    public void checkTile(Entity entity){
        int entityLeftWorldX = entity.worldX + entity.collisionArea.x;
        int entityRightWorldX = entity.worldX + entity.collisionArea.x + entity.collisionArea.width;
        int entityTopWorldY = entity.worldY + entity.collisionArea.y;
        int entityBottomWorldY = entity.worldY + entity.collisionArea.y + entity.collisionArea.height;

        int entityLeftCol = entityLeftWorldX / gp.frameActualSize;
        int entityRightCol = entityRightWorldX / gp.frameActualSize;
        int entityTopRow = entityTopWorldY / gp.frameActualSize;
        int entityBottomRow = entityBottomWorldY / gp.frameActualSize;

        int tileNum1, tileNum2;

        switch(entity.direction){
            case 'w':
                entityTopRow = (entityTopWorldY - entity.speed) / gp.frameActualSize;
                tileNum1 = gp.tileManager.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileManager.mapTileNum[entityRightCol][entityTopRow];
                if (gp.tileManager.tiles[tileNum1].collision == true || gp.tileManager.tiles[tileNum2].collision == true){
                    entity.collision = true;
                }
                break;
            case 's':
                entityBottomRow = (entityBottomWorldY + entity.speed) / gp.frameActualSize;
                tileNum1 = gp.tileManager.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileManager.mapTileNum[entityRightCol][entityBottomRow];
                if (gp.tileManager.tiles[tileNum1].collision == true || gp.tileManager.tiles[tileNum2].collision == true){
                    entity.collision = true;
                }
                break;
            case 'a':
                entityLeftCol = (entityLeftWorldX - entity.speed) / gp.frameActualSize;
                tileNum1 = gp.tileManager.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileManager.mapTileNum[entityLeftCol][entityBottomRow];
                if (gp.tileManager.tiles[tileNum1].collision == true || gp.tileManager.tiles[tileNum2].collision == true){
                    entity.collision = true;
                }
                break;
            case 'd':
                entityRightCol = (entityRightWorldX + entity.speed) / gp.frameActualSize;
                tileNum1 = gp.tileManager.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gp.tileManager.mapTileNum[entityRightCol][entityBottomRow];
                if (gp.tileManager.tiles[tileNum1].collision == true || gp.tileManager.tiles[tileNum2].collision == true){
                    entity.collision = true;
                }
        }
    }
}
