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
        
        try{
            tiles[0] = new Tile();
            tiles[0].image = ImageIO.read(getClass().getResourceAsStream("/graphics/tiles/floors/grass.png"));

            tiles[1] = new Tile();
            tiles[1].image = ImageIO.read(getClass().getResourceAsStream("/graphics/tiles/floors/prp_bricks.png"));
            tiles[1].collision = true;

            tiles[2] = new Tile();
            tiles[2].image = ImageIO.read(getClass().getResourceAsStream("/graphics/tiles/floors/water1.png"));
            tiles[2].collision = true;

            tiles[3] = new Tile();
            tiles[3].image = ImageIO.read(getClass().getResourceAsStream("/graphics/tiles/objects/tree1.png"));
            tiles[3].collision = true;

            tiles[4] = new Tile();
            tiles[4].image = ImageIO.read(getClass().getResourceAsStream("/graphics/tiles/floors/dirt.png"));

            tiles[5] = new Tile();
            tiles[5].image = ImageIO.read(getClass().getResourceAsStream("/graphics/tiles/floors/sand1.png"));

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
                g2d.drawImage(tiles[tileNum].image, screenX, screenY, gp.frameActualSize, gp.frameActualSize, null);  
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
