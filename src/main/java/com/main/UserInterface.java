package com.main;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import com.objects.*;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.BasicStroke;
import java.awt.Color;


public class UserInterface {
    
    Gpanel gp;
    Font comicsans, bigsans, maruMonica;
    //BufferedImage keymage; for keymarker (unused for now)

    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    public String currentDialogue;
    public int commandNr = 0;
    public int maxCommandNr = 3;
    public int titleScreenDefState = 0;
    public int titleScreenState = titleScreenDefState;
    public int manualState = 1;
    public int prologueState = 2;


    //float playTime;
    //DecimalFormat dFormat = new DecimalFormat("#0.00");
    private Graphics2D g2d;
    BufferedImage heart_full, heart_half, heart_empty;

    public UserInterface(Gpanel gp){
        this.gp = gp;
        comicsans = new Font("Comic Sans MS", Font.PLAIN, 40);
        bigsans = new Font("Comic Sans MS", Font.BOLD, 70);

        try{
            InputStream is = getClass().getResourceAsStream("/font/x12y16pxMaruMonica.ttf");
            maruMonica = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(Font.PLAIN, 40f);
        } catch (FontFormatException e){ e.printStackTrace();
        } catch (IOException e) { e.printStackTrace();}
        //OKey key = new OKey(gp); 
        //keymage = key.image;

        OHeart heart = new OHeart(gp);
        heart_full = heart.image;
        heart_half = heart.image2;
        heart_empty = heart.image3;
    }

    public void showMessage(String text){

        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2d){
        
        this.g2d = g2d;
        g2d.setFont(maruMonica);
        g2d.setColor(Color.white);

        if(gp.gState == gp.playState){
            drawPlayerLife();
        } else if (gp.gState == gp.pauseState){
            g2d.setFont(bigsans);
            drawPauseScreen();
            drawPlayerLife();
            g2d.setFont(comicsans);
        } else if (gp.gState == gp.dialogueState){
            drawDialogue();
            drawPlayerLife();
        } else if (gp.gState == gp.titleState){
            drawTitleScreen();
        }
    }

    private void drawPlayerLife() {
        
        int x = gp.frameActualSize /2;
        int y = gp.frameActualSize /2;

		int fullHearts = gp.player.life/2;
		int halfHearts = gp.player.life%2;
        int emptyHearts = (gp.player.maxLife - gp.player.life)/2;
		
		for(int i = 0; i < fullHearts; i++) {
			g2d.drawImage(heart_full, x, y, null);
			x += gp.frameActualSize;
		}
		for(int i = 0; i < halfHearts; i++) {
			g2d.drawImage(heart_half, x, y, null);
			x += gp.frameActualSize;
		}
        for(int i = 0; i < emptyHearts; i++) {
			g2d.drawImage(heart_empty, x, y, null);
			x += gp.frameActualSize;
		}
    }

    private void drawTitleScreen() {
        
        if(titleScreenState == titleScreenDefState){
            drawTitle();

            g2d.drawImage(gp.player.s1, gp.screenWidth/2 - gp.frameActualSize*2, gp.frameActualSize*5,
                gp.frameActualSize*4, gp.frameActualSize*4, null );

            g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 48F));
            String text = " Play";//for some reason space helps to even everything out
            g2d.drawString(text, getTxtCenter(text), gp.frameActualSize*10);
            if(commandNr == 0){
                g2d.drawString(">", getTxtCenter(text)-gp.frameActualSize/4, gp.frameActualSize*10);
            }        
            
            text = "Prologue";
            g2d.drawString(text, getTxtCenter(text), gp.frameActualSize*11);
            if(commandNr == 1){
                g2d.drawString(">", getTxtCenter(text)-gp.frameActualSize/2, gp.frameActualSize*11);
            } 

            text = "Manual";
            g2d.drawString(text, getTxtCenter(text), gp.frameActualSize*12);
            if(commandNr == 2){
                g2d.drawString(">", getTxtCenter(text)-gp.frameActualSize/2, gp.frameActualSize*12);
            } 

            text = "Quit";
            g2d.drawString(text, getTxtCenter(text), gp.frameActualSize*13);
            if(commandNr == 3){
                g2d.drawString(">", getTxtCenter(text)-gp.frameActualSize/2, gp.frameActualSize*13);
            } 
        } else if(titleScreenState == manualState){
            drawManualScreen();
        } else if(titleScreenState == prologueState){
            drawPrologueScreen();
        }
    }

    private void drawPrologueScreen() {
        drawTitle();
        String text = "Long ago, in a distant land,\n Adam - the (arguably) first man felt hunger.";
        drawText(text);
    }

     private void drawManualScreen() {
        drawTitle();
        String text = "Use WASD to walk,\n Enter to interact,\nEscape for pause.\nPress Enter to come back to title screen.";
        drawText(text);
    }

    private void drawDialogue() {
        
        int x = gp.frameActualSize *2;
        int y = gp.frameActualSize /2;
        int width = gp.screenWidth - gp.frameActualSize*4;
        int height = gp.frameActualSize *5;
        drawSubWindow(x, y, width, height);

        x+= gp.frameActualSize;
        y+= gp.frameActualSize;

        for(String line : currentDialogue.split("\n")){
            g2d.drawString(line, x, y);
            y+= height/5;
        }        
    }

    private void drawTitle(){
        g2d.setFont(g2d.getFont().deriveFont(Font.BOLD, 96F));
        String text = "The living of Adam";

        g2d.setColor(new Color(255,255,255,100));
        g2d.drawString(text, getTxtCenter(text)+5, gp.frameActualSize*3+5);
        g2d.setColor(Color.white);
        g2d.drawString(text, getTxtCenter(text), gp.frameActualSize*3);
    }

    private void drawText(String text){
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 48F));
        
        int y = gp.frameActualSize*5;
        for(String line : text.split("\n")){
            g2d.drawString(line, getTxtCenter(line), y);
            y+= gp.frameActualSize;
        } 
    }
    
    public void drawSubWindow(int x, int y, int width, int height){

        Color c = new Color(0,0,0, 190);
        g2d.setColor(c);
        int arc = 35;
        g2d.fillRoundRect(x, y, width, height, arc, arc);

        c = new Color(255,255,255);
        g2d.setColor(c);
        int z = 4;
        g2d.setStroke(new BasicStroke(z));
        g2d.drawRoundRect(x+z, y+z, width-2*z, height-2*z, arc-z, arc-z);
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