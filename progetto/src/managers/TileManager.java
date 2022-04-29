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
        tiles.add(ERBA = new Tile(getSprite(0, 0), id++, "erba"));
        tiles.add(ACQUA = new Tile(getSprite(1, 0), id++, "acqua"));
        tiles.add(STRADA = new Tile(getSprite(2, 0), id++, "strada"));
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
        return atlas.getSubimage(x*32, y*32, 32, 32);
    } 
}
