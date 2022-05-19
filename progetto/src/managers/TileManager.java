package managers;

import static helpz.Constants.Tiles.*;
import helpz.ImgFix;
import helpz.LoadSave;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import objects.Tile;

public class TileManager {
    public Tile ERBA,ACQUA,STRADA;
    public BufferedImage atlas;
    public ArrayList<Tile> tiles = new ArrayList<>();

    public TileManager() {
        loadAtlas();
        createTiles();
    }
    
    private void createTiles() {
        int id=0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 9; j++) {
                tiles.add(STRADA = new Tile(getSprite(j, i), id++, ROAD_TILE));
            }
        }
        tiles.add(STRADA = new Tile(getSprite(0, 5), id++, ROAD_TILE));
        tiles.add(STRADA = new Tile(getSprite(1, 5), id++, ROAD_TILE));
        tiles.add(STRADA = new Tile(getSprite(2, 5), id++, ROAD_TILE));
        tiles.add(STRADA = new Tile(getSprite(3, 5), id++, ROAD_TILE));
        tiles.add(STRADA = new Tile(getSprite(4, 5), id++, GRASS_TILE));
        tiles.add(STRADA = new Tile(getSprite(5, 5), id++, WATER_TILE));
        tiles.add(STRADA = new Tile(getSprite(6, 5), id++, ROAD_TILE));
        tiles.add(STRADA = new Tile(getSprite(7, 5), id++, ROAD_TILE));
        tiles.add(STRADA = new Tile(getSprite(8, 5), id++, ROAD_TILE));
        ///////////////
        tiles.add(STRADA = new Tile(getSprite(0, 6), id++, ROAD_TILE));
        tiles.add(STRADA = new Tile(getSprite(1, 6), id++, ROAD_TILE));
        tiles.add(STRADA = new Tile(getSprite(2, 6), id++, ROAD_TILE));
        tiles.add(STRADA = new Tile(getSprite(3, 6), id++, WATER_TILE));
        tiles.add(STRADA = new Tile(getSprite(4, 6), id++, GRASS_TILE));
    }
    
    private void loadAtlas() {
        atlas = LoadSave.getImage(LoadSave.SPRITE_ATLAS);
    }
    public Tile getTile(int id){
        return tiles.get(id);
    }
    public BufferedImage getSprite(int id){
        return tiles.get(id).getSprite();
    }
    
    private BufferedImage getSprite(int x, int y){
        return atlas.getSubimage(x*64, y*64, 64, 64);
    } 
}
