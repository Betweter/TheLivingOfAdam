package com.main;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class Keys implements KeyListener{
    
    public boolean upPressed, downPressed, leftPressed, rightPressed;
    private Gpanel gp;

    public Keys(Gpanel gp){
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e){

    }

    @Override
    public void keyPressed(KeyEvent e){

        int code = e.getKeyCode();
        
        if (code == KeyEvent.VK_W){
            upPressed = true;
        }
        if (code == KeyEvent.VK_A){
            leftPressed = true;
        }
        if (code == KeyEvent.VK_S){
            downPressed = true;
        }
        if (code == KeyEvent.VK_D){
            rightPressed = true;
        }
        if (code == KeyEvent.VK_ESCAPE){
            if(gp.gState == gp.playState){
                gp.gState = gp.pauseState;
            } else if(gp.gState == gp.pauseState){ 
                gp.gState = gp.playState;
            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e){

        int code = e.getKeyCode();
        
        if (code == KeyEvent.VK_W){
            upPressed = false;
        }
        if (code == KeyEvent.VK_A){
            leftPressed = false;
        }
        if (code == KeyEvent.VK_S){
            downPressed = false;
        }
        if (code == KeyEvent.VK_D){
            rightPressed = false;
        }
    }

    public boolean isMovePressed(){
        return upPressed || downPressed || leftPressed || rightPressed;
    }
}
