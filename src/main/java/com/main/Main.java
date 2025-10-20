package com.main;
import javax.swing.JFrame;

public class Main{
    public static void main( String[] args ){
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("The living of Adam");

        Gpanel gpanel = new Gpanel();
        window.add(gpanel);
        window.pack(); //adjust window size to the panel

        window.setLocationRelativeTo(null);  //center
        window.setVisible(true);

        gpanel.setupGame();        
        gpanel.startGThread();
    }
}
