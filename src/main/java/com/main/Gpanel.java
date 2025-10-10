package com.main;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import com.entity.Player;
import com.tile.TileManager;

public class Gpanel extends JPanel  implements Runnable{
    final int frameOriginalSize = 16;
    final int scale = 3;
    public final int frameActualSize = frameOriginalSize*scale;
    public final int columns = 28;
    public final int rows = 14;
    final int screenWidth = frameActualSize*columns; //1344px
    final int screenHeight = frameActualSize*rows;  //672px
    final int fps = 60;

    Thread gThread; //it's the clock of the game
    Keys keys = new Keys();
    TileManager tileManager = new TileManager(this);

    Player player = new Player(this, keys);

    public Gpanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);   //performance
        this.addKeyListener(keys);
        this.setFocusable(true);  //for receiving input
    }

    public void startGThread(){
        gThread = new Thread(this);
        gThread.start();
    }

    @Override
    public void run(){
            
        long tInterval = 1000/fps;
        long tNext = System.currentTimeMillis() + tInterval;

        while (gThread != null){

            updateData();
            repaint();

            try{
                long tLeft = tNext - System.currentTimeMillis();
                if(tLeft < 0){
                    tLeft = 0;
                }
                Thread.sleep(tNext - System.currentTimeMillis());
            } catch (InterruptedException e){ 
                e.printStackTrace();
            }
            tNext += tInterval;
        }
    }

    public void updateData(){
        player.update();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        
        tileManager.draw(g2d);
        player.paint(g2d);

        g2d.dispose();
    }
}
