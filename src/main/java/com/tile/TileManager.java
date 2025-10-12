package com.tile;

import com.main.Gpanel;
import com.main.Utility;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TileManager {
    Gpanel gp;
    public Tile[] tiles;
    public int mapTileNum[][];

    public TileManager(Gpanel gp){
        this.gp = gp;
        tiles = new Tile[10]; //types of tiles, to be changed
        mapTileNum = new int[gp.worldColumns][gp.worldRows];

        getTileImage();
        loadMap("/maps/50x50_1.txt");
    }

    public void getTileImage(){
        
        setup(0, "/graphics/tiles/floors/grass.png", false);
        setup(1, "/graphics/tiles/floors/prp_bricks.png", true);
        setup(2, "/graphics/tiles/floors/water1.png", true);
        setup(3, "/graphics/tiles/objects/tree1.png", true);
        setup(4, "/graphics/tiles/floors/dirt.png", false);
        setup(5, "/graphics/tiles/floors/sand1.png", false);

    }

    private void setup(int index, String path, boolean collision){

        try{
            tiles[index] = new Tile();
            tiles[index].image = ImageIO.read(getClass().getResourceAsStream(path));
            tiles[index].image = Utility.scaledImage(tiles[index].image, gp.frameActualSize, gp.frameActualSize);
            tiles[index].collision = collision;
        } catch (IOException e) {e.printStackTrace();}
    }

    public void loadMap(String filePath){
        try{
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while(col < gp.worldColumns && row < gp.worldRows){
                String line = br.readLine();

                while(col < gp.worldColumns){
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == gp.worldColumns){
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

        while(col < gp.worldColumns && row < gp.worldRows){

            int tileNum = mapTileNum[col][row];

            int worldX = col * gp.frameActualSize;
            int worldY = row * gp.frameActualSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            if(pixelFits(worldX, worldY)){
                g2d.drawImage(tiles[tileNum].image, screenX, screenY, null);  
            }
            col++;

            if(col == gp.worldColumns){
                col = 0;
                row++;
            }
        }
    }

    public boolean pixelFits(int x, int y){
        return x + gp.frameActualSize > gp.player.worldX - gp.player.screenX &&
               x - gp.frameActualSize < gp.player.worldX + gp.player.screenX &&
               y + gp.frameActualSize > gp.player.worldY - gp.player.screenY &&
               y - gp.frameActualSize < gp.player.worldY + gp.player.screenY;
    }

}
