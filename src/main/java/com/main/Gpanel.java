package com.main;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import com.entity.Entity;
import com.entity.Player;
import com.tile.TileManager;
import com.objects.SuperObject;

public class Gpanel extends JPanel  implements Runnable{
    
    //screen
    final int frameOriginalSize = 16;
    final int scale = 3;
    public final int frameActualSize = frameOriginalSize*scale;
    public final int columns = 28;
    public final int rows = 14;
    public final int screenWidth = frameActualSize*columns; //1344px
    public final int screenHeight = frameActualSize*rows;  //672px
    public final int fps = 60;

    //world
    public final int worldColumns = 50;
    public final int worldRows = 50;

    public Collision collisionCheck = new Collision(this);
    public AssetSetter aSetter = new AssetSetter(this);

    //systems
    Thread gThread; //it's the clock of the game
    public Keys keys = new Keys(this);
    public TileManager tileManager = new TileManager(this);
    Sound music = new Sound();
    Sound sound = new Sound();
    public UserInterface ui = new UserInterface(this);
    public Events events = new Events(this);

    public Player player = new Player(this, keys);
    public SuperObject obj[] = new SuperObject[10]; //to be adjusted
    public Entity npc[] = new Entity[10];

    public int gState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;

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
                if(tNext - System.currentTimeMillis() > 0){
                    Thread.sleep(tNext - System.currentTimeMillis());
                }
            } catch (InterruptedException e){ 
                e.printStackTrace();
            }
            tNext += tInterval;
        }
    }

    public void updateData(){
        if(gState == playState){
            player.update();
            
            for(int i = 0; i < npc.length; i++){
                if(npc[i] != null){
                    npc[i].update();
                }
            }
        } else if (gState == pauseState){
            ;//might be needed later
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        
        if(gState == titleState){
            ui.draw(g2d);
        }else{
            tileManager.draw(g2d);

            for(int i=0; i<obj.length; i++){
                if(obj[i] != null){
                    obj[i].draw(g2d, this);
                }
            }
            
            for(int i=0; i<npc.length; i++){
                if(npc[i] != null){
                    npc[i].draw(g2d);
                }
            }

            player.paint(g2d);

            ui.draw(g2d);
        }
        g2d.dispose();
    }

    public void setupGame(){
        aSetter.setObject();
        aSetter.setNPC();
        //playMusic(0);
        gState = titleState;
    }

    public void playMusic(int i){

        music.setFile(i);
        music.play();
        music.loop();
    }

    public void stopMusic(){
        music.stop();
    }

    public void playEffect(int i){
        sound.setFile(i);
        sound.play();
    }
}
