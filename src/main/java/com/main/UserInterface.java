package com.main;
import java.awt.Graphics2D;
import java.text.DecimalFormat;

import java.awt.Font;
import java.awt.Color;


public class UserInterface {
    
    Gpanel gp;
    Font comicsans, bigsans;
    //BufferedImage keymage; for keymarker (unused for now)

    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;

    float playTime;
    DecimalFormat dFormat = new DecimalFormat("#0.00");
    private Graphics2D g2d;

    public UserInterface(Gpanel gp){
        this.gp = gp;
        comicsans = new Font("Comic Sans MS", Font.PLAIN, 40);
        bigsans = new Font("Comic Sans MS", Font.BOLD, 70);
        //OKey key = new OKey(gp); 
        //keymage = key.image;
    }

    public void showMessage(String text){

        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2d){
        
        this.g2d = g2d;
        g2d.setFont(comicsans);
        g2d.setColor(Color.white);

        if(gp.gState == gp.playState){
            //todo
        } else if (gp.gState == gp.pauseState){
            g2d.setFont(bigsans);
            drawPauseScreen();
            g2d.setFont(comicsans);
        }
    }

    public void drawPauseScreen(){

        String text = "PAUSE";
        g2d.drawString(text, getTxtCenter(text), gp.screenHeight/2 );
    }

    public int getTxtCenter(String text){

        int length = (int)g2d.getFontMetrics().getStringBounds(text, g2d).getWidth();
        return (gp.screenWidth - length )/2;
    }

}


/*  Draw for old markers - currently unused
    public void draw(Graphics2D g2d){
        if(!gameFinished){
            g2d.setFont(comicsans);
            g2d.setColor(Color.white);

            g2d.drawImage(keymage, gp.frameActualSize/3, gp.frameActualSize/101, gp.frameActualSize, gp.frameActualSize, null);
            g2d.drawString("x " + gp.player.hasKeys,75,40);

            if(messageOn){
                g2d.drawString(message, gp.frameActualSize/2, gp.frameActualSize*5);
                messageCounter++;

                if(messageCounter >= 60*2){
                    messageCounter = 0;
                    messageOn = false;
                }
            }

            playTime += (float) 1/60;
            g2d.drawString("time: " + dFormat.format(playTime), gp.screenWidth - gp.frameActualSize*5, gp.frameActualSize);

        } else {
            g2d.setFont(comicsans);
            g2d.setColor(Color.white);

            String text = "You've found the treasure";
            int textLength = (int) g2d.getFontMetrics().getStringBounds(text, g2d).getWidth();
            int x = gp.screenWidth/2 - textLength/2;
            int y = gp.screenHeight/2 + gp.frameActualSize*2;
            g2d.drawString(text, x, y);

            text = "It took you: " + dFormat.format(playTime) + " seconds";
            textLength = (int) g2d.getFontMetrics().getStringBounds(text, g2d).getWidth();
            x = gp.screenWidth/2 - textLength/2;
            y = gp.screenHeight/2 + gp.frameActualSize*5;
            g2d.drawString(text, x, y);

            g2d.setFont(bigsans);
            g2d.setColor(Color.magenta);

            text = "YUPI";
            textLength = (int) g2d.getFontMetrics().getStringBounds(text, g2d).getWidth();
            x = gp.screenWidth/2 - textLength/2;
            y = gp.screenHeight/2 + gp.frameActualSize*4;
            g2d.drawString(text, x, y);
            
            gp.gThread = null;
        }
    } */