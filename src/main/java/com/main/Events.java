package com.main;

import java.awt.Rectangle;

public class Events {

    Gpanel gp;
    Rectangle eRect;
    int eRectDefX, eRectDefY;
    char anyDirection = 'X';

    public Events(Gpanel gp){

        this.gp = gp;
        eRect = new Rectangle(23, 23, 2, 2);
        eRectDefX = eRect.x;
        eRectDefY = eRect.y;
    }

    public void eCheck(){

        if(hit(6, 1, anyDirection)){ damagePit(gp.dialogueState); }
        if(hit(8, 5, anyDirection)){ healingPit(gp.dialogueState); }
        if(hit(13, 4, anyDirection)){ teleport(gp.dialogueState, 10, 10); }
        if(hit(47, 49, anyDirection)){ teleport(gp.dialogueState, 10, 10); }
        if(hit(47, 48, anyDirection)){ teleport(gp.dialogueState, 10, 10); }
    }

    public boolean hit(int eCol, int eRow, char reqDirection){

        boolean hit = false;
        gp.player.collisionArea.x = gp.player.worldX + gp.player.collisionArea.x;
        gp.player.collisionArea.y = gp.player.worldY + gp.player.collisionArea.y;
        eRect.x = eCol*gp.frameActualSize + eRect.x;
        eRect.y = eRow*gp.frameActualSize + eRect.y;

        if(gp.player.collisionArea.intersects(eRect)){
            if(gp.player.direction == reqDirection || reqDirection == anyDirection){
                hit = true;
            }
        }
        gp.player.collisionArea.x = gp.player.collisionAreaXDefault;
        gp.player.collisionArea.y = gp.player.collisionAreaYDefault;
        eRect.x = eRectDefX;
        eRect.y = eRectDefY;

        return hit;
    }

    private void damagePit(int gState) {
        
        gp.gState = gState;
        gp.ui.currentDialogue = "you fell off";
        gp.player.life -= 1;
    }

    private void healingPit(int gState) {
        
        if(gp.keys.enterPressed){
            gp.gState = gState;
            gp.ui.currentDialogue = "you healed off";
            gp.player.life = gp.player.maxLife;
        }
    }

    private void teleport(int gState, int x, int y){
        gp.gState = gState;
        gp.ui.currentDialogue = "teleportation";
        gp.player.worldX = x*gp.frameActualSize;
        gp.player.worldY = y*gp.frameActualSize;
    }

}
