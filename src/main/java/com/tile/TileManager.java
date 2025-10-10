package com.tile;

import com.main.Gpanel;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.Graphics2D;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TileManager {
    Gpanel gp;
    Tile[] tiles;
    int mapTileNum[][];

    public TileManager(Gpanel gp){
        this.gp = gp;
        tiles = new Tile[10]; //types of tiles, to be changed
        mapTileNum = new int[gp.columns][gp.rows];

        getTileImage();
        loadMap("/maps/blank.txt");
    }

    public void getTileImage(){
        
        try{
            tiles[0] = new Tile();
            tiles[0].image = ImageIO.read(getClass().getResourceAsStream("/graphics/tiles/floors/grass.png"));

            tiles[1] = new Tile();
            tiles[1].image = ImageIO.read(getClass().getResourceAsStream("/graphics/tiles/floors/prp_bricks.png"));

            tiles[2] = new Tile();
            tiles[2].image = ImageIO.read(getClass().getResourceAsStream("/graphics/tiles/floors/water1.png"));

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath){
        try{
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while(col < gp.columns && row < gp.rows){
                String line = br.readLine();

                while(col < gp.columns){
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == gp.columns){
                    col = 0;
                    row++;
                }
            }
            br.close();

        } catch (Exception e) {
            e.printStackTrace(); 
        }
    }

    public void draw(Graphics2D g2d){

        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while(col < gp.columns && row < gp.rows){

            int tileNum = mapTileNum[col][row];

            g2d.drawImage(tiles[tileNum].image, x, y, gp.frameActualSize, gp.frameActualSize, null);
            col++;
            x += gp.frameActualSize;

            if(col == gp.columns){
                col = 0;
                x = 0;
                row++;
                y += gp.frameActualSize;
            }
        }
    }
}
