package managers;

import helpz.LevelBuild;
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
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 9; j++) {
                tiles.add(STRADA = new Tile(getSprite(j, i), id++, "strada"));
            }
        }
        tiles.add(STRADA = new Tile(getSprite(0, 6), id++, "strada"));
        tiles.add(STRADA = new Tile(getSprite(1, 6), id++, "strada"));
        tiles.add(STRADA = new Tile(getSprite(2, 6), id++, "strada"));
        tiles.add(STRADA = new Tile(getSprite(3, 6), id++, "strada"));
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
