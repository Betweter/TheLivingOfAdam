package com.entity;

import com.main.Gpanel;

public class N_Crow extends Entity{
    public N_Crow(Gpanel gp, int worldX, int worldY){

        super(gp, worldX, worldY);
        getImage();
        setDialogue();
    }

    @Override
    public void getImage(){
        String path = "/graphics/npc/crow.png";
        w1 = setup(path);
        w2 = setup(path);
        s1 = setup(path);
        s2 = setup(path);
        d1 = setup(path);
        d2 = setup(path);
        a1 = setup(path);
        a2 = setup(path);
    }

    @Override
    public void setAction(){
        ;
    }

    @Override
    public void update(){
        ;
    }

    public void setDialogue(){
        dialogues[0] = "The sands of time do different things...";
        dialogues[1] = "Some hurt, some move, some heal,\nbut only if you take it into your hand";
    }

    @Override
    public void speak(){
        super.speak();
    }
}
