package com.entity;

import java.util.Random;

import com.main.Gpanel;

public class N_Dorothy extends Entity {
    
    public N_Dorothy(Gpanel gp, int worldX, int worldY){

        super(gp, worldX, worldY);
        getImage();
        setDialogue();
    }

    @Override
    public void getImage(){
        w1 = setup("/graphics/npc/Dorothy/w1.png");
        w2 = setup("/graphics/npc/Dorothy/w2.png");
        s1 = setup("/graphics/npc/Dorothy/s1.png");
        s2 = setup("/graphics/npc/Dorothy/s2.png");
        d1 = setup("/graphics/npc/Dorothy/d1.png");
        d2 = setup("/graphics/npc/Dorothy/d2.png");
        a1 = setup("/graphics/npc/Dorothy/a1.png");
        a2 = setup("/graphics/npc/Dorothy/a2.png");
    }

    @Override
    public void setAction(){

        frameCounter++;
        if(frameCounter >= gp.fps * 2){
            Random random = new Random();
            int i = random.nextInt(100) + 1;

            if(i <=25){
                direction = 'w';
            } else if (i <= 50){
                direction = 'd';
            } else if (i <= 75){
                direction = 's';
            } else direction = 'a';
            
            frameCounter = 0;
        }
        
    }

    public void setDialogue(){
        dialogues[0] = "AAAAAAAAAaaaaaaaaaaa\naAAAaAAAaAaAaA!";
        dialogues[1] = "Adam we'll be late!";
        dialogues[2] = "AAAAAAAAAaaaaaaaaaaaaAAAaAAAaAaAaA!";
        dialogues[3] = "AAAAAAAAAaaaaaaaaaaaaAAAaAAAaAaAaA!";
    }

    @Override
    public void speak(){
        super.speak();
    }
}
