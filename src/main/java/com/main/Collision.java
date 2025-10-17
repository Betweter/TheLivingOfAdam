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

    public int checkObject(Entity entity, boolean player){
        int index = 999;
        for(int i=0; i<gp.obj.length; i++){
            if(gp.obj[i] != null){

                entity.collisionArea.x = entity.worldX + entity.collisionArea.x;
                entity.collisionArea.y = entity.worldY + entity.collisionArea.y;

                gp.obj[i].collisionArea.x = gp.obj[i].worldX + gp.obj[i].collisionArea.x;
                gp.obj[i].collisionArea.y = gp.obj[i].worldY + gp.obj[i].collisionArea.y;

                switch(entity.direction){
                    case 'w':
                        entity.collisionArea.y -= entity.speed;
                        index = resultObjectCollision(entity, i, index, player);
                        break;
                    case 's':
                        entity.collisionArea.y += entity.speed;
                        index = resultObjectCollision(entity, i, index, player);
                        break;
                    case 'a':
                        entity.collisionArea.x -= entity.speed;
                        index = resultObjectCollision(entity, i, index, player);
                        break;
                    case 'd':
                        entity.collisionArea.x += entity.speed;
                        index = resultObjectCollision(entity, i, index, player);
                }

                entity.collisionArea.x = entity.collisionAreaXDefault;
                entity.collisionArea.y =  entity.collisionAreaYDefault;
                gp.obj[i].collisionArea.x = gp.obj[i].collisionAreaXDefault;
                gp.obj[i].collisionArea.y = gp.obj[i].collisonAreaYDefault;
            }
        }
        return index;
    }
    
    public int resultObjectCollision(Entity entity, int i, int index, boolean player){
        if(entity.collisionArea.intersects(gp.obj[i].collisionArea)){
             if(gp.obj[i].collision){
                entity.collision = true;
            }
            if(player){
                index = i;
            }
        }
        return index;
    }

    public int resultEntityCollision(Entity entity, Entity[] target, int i, int def){
        if(entity.collisionArea.intersects(target[i].collisionArea)){
            entity.collision = true;
            return i;
        }
        return def;
    }

    public int checkEntity(Entity entity, Entity[] target){
        
        int index = 999;//default value
        for(int i=0; i< target.length; i++){
            if(target[i] != null){

                entity.collisionArea.x = entity.worldX + entity.collisionArea.x;
                entity.collisionArea.y = entity.worldY + entity.collisionArea.y;

                target[i].collisionArea.x = target[i].worldX + target[i].collisionArea.x;
                target[i].collisionArea.y = target[i].worldY + target[i].collisionArea.y;

                switch(entity.direction){
                    case 'w':
                        entity.collisionArea.y -= entity.speed;
                        index = resultEntityCollision(entity, target, i, index);
                        break;
                    case 's':
                        entity.collisionArea.y += entity.speed;
                        index = resultEntityCollision(entity, target, i, index);
                        break;
                    case 'a':
                        entity.collisionArea.x -= entity.speed;
                        index = resultEntityCollision(entity, target, i, index);
                        break;
                    case 'd':
                        entity.collisionArea.x += entity.speed;
                        index = resultEntityCollision(entity, target, i, index);
                }

                entity.collisionArea.x = entity.collisionAreaXDefault;
                entity.collisionArea.y =  entity.collisionAreaYDefault;
                target[i].collisionArea.x = target[i].collisionAreaXDefault;
                target[i].collisionArea.y = target[i].collisionAreaYDefault;
            }
        }
        return index;
    }

    public void checkPlayer(Entity entity){
        if(gp.player != null){

            entity.collisionArea.x = entity.worldX + entity.collisionArea.x;
            entity.collisionArea.y = entity.worldY + entity.collisionArea.y;

            gp.player.collisionArea.x = gp.player.worldX + gp.player.collisionArea.x;
            gp.player.collisionArea.y = gp.player.worldY + gp.player.collisionArea.y;

            switch(entity.direction){
                case 'w':
                    entity.collisionArea.y -= entity.speed;
                    resultPlayerCollision(entity);
                    break;
                case 's':
                    entity.collisionArea.y += entity.speed;
                    resultPlayerCollision(entity);
                    break;
                case 'a':
                    entity.collisionArea.x -= entity.speed;
                    resultPlayerCollision(entity);
                    break;
                case 'd':
                    entity.collisionArea.x += entity.speed;
                    resultPlayerCollision(entity);
            }

            entity.collisionArea.x = entity.collisionAreaXDefault;
            entity.collisionArea.y =  entity.collisionAreaYDefault;
            gp.player.collisionArea.x = gp.player.collisionAreaXDefault;
            gp.player.collisionArea.y = gp.player.collisionAreaYDefault;
        }
    }

    public void resultPlayerCollision(Entity entity){
        if(entity.collisionArea.intersects(gp.player.collisionArea)){
            entity.collision = true;
        }
    }
}
