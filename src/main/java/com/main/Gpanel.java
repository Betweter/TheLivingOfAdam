package com.main;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Color;

public class Gpanel extends JPanel  implements Runnable{
    final int frameOriginalSize = 16;
    final int scale = 3;
    final int frameActualSize = frameOriginalSize*scale;
    final int columns = 32;
    final int rows = 18;
    final int screenWidth = frameActualSize*columns; //1536px
    final int screenHeight = frameActualSize*rows;  //864px

    Thread gThread; //it's the clock of the game

    public Gpanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);   //performance
    }

    public void startGameThread(){
        gThread = new Thread(this);
        gThread.start();
    }

    @Override
    public void run(){

    }
}
