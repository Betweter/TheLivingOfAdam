package com.main;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class Keys implements KeyListener{
    
    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed;
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

        if(gp.gState == gp.playState){
            switch(code){
                case KeyEvent.VK_W:
                    upPressed = true;
                    break;
                case KeyEvent.VK_A:
                    leftPressed = true;
                    break;
                case KeyEvent.VK_S:
                    downPressed = true;
                    break;
                case KeyEvent.VK_D:
                    rightPressed = true;
                    break;
                case KeyEvent.VK_ESCAPE:
                    gp.gState = gp.titleState;
                    gp.stopMusic();
                    break;
                case KeyEvent.VK_ENTER:
                    enterPressed = true;
                    break;
            }
        }
        
        else if(gp.gState == gp.pauseState){ 
            if(code == KeyEvent.VK_ESCAPE){
                gp.gState = gp.playState;
            }
        }

        else if(gp.gState == gp.dialogueState){
            if(code == KeyEvent.VK_ENTER){
                gp.gState = gp.playState;
            }
        }

        else if(gp.gState == gp.titleState){
            if(gp.ui.titleScreenState == gp.ui.titleScreenDefState){
                if(code == KeyEvent.VK_S && gp.ui.commandNr < gp.ui.maxCommandNr){
                    gp.ui.commandNr++;
                } else if(code == KeyEvent.VK_W && gp.ui.commandNr > 0){
                    gp.ui.commandNr--;
                } else if(code == KeyEvent.VK_ENTER){
                    if(gp.ui.commandNr == 0){
                        gp.gState = gp.playState;
                        gp.playMusic(0);
                    } else if (gp.ui.commandNr == 1){
                        gp.ui.titleScreenState = gp.ui.prologueState;
                    } else if (gp.ui.commandNr == 2){
                        gp.ui.titleScreenState = gp.ui.manualState;
                    } else if (gp.ui.commandNr == 3){
                        System.exit(0);
                    }
                }
            } else if(gp.ui.titleScreenState == gp.ui.manualState || gp.ui.titleScreenState == gp.ui.prologueState){
                if(code == KeyEvent.VK_ENTER ){
                    gp.ui.titleScreenState = gp.ui.titleScreenDefState;
                }
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
