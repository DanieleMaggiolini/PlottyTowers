/**
* @author  Daniele Maggiolini
* @author  Mattia Minotti
* @version 0.0
* @file TileManager.java 
* 
* @brief file per gestire i tile della mappa.
*
*/
package managers;

import static helpz.Constants.Tiles.*;
import helpz.ImgFix;
import helpz.LoadSave;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import objects.Tile;

/**
 * @class TileManager
 *
 * @brief classe per gestire i tile della mappa
 *
 */
public class TileManager {
    //tipologia del tile
    public Tile ERBA,ACQUA,STRADA;
    
    //immagine dei tile
    public BufferedImage atlas;
    
    //array contente tutti i tile con la loro tipologia
    public ArrayList<Tile> tiles = new ArrayList<>();

    /**
     * @brief costruttore
     * carica dell'immagine e assegnazione per ogni tile
     */
    public TileManager() {
        loadAtlas();
        createTiles();
    }
    
    /**
     * @brief crazione di un tile per ogni subimage e assegnazione della tipologia
     * del tile
     */
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
        tiles.add(STRADA = new Tile(getSprite(5, 6), id++, WATER_TILE));
        tiles.add(STRADA = new Tile(getSprite(6, 6), id++, WATER_TILE));
    }
    
    /**
     * @brief caricamento immagine
     */
    private void loadAtlas() {
        atlas = LoadSave.getImage(LoadSave.SPRITE_ATLAS);
    }
    
    /**
     * @brief ritorno di un tile passando un id
     * 
     * @param id id del tile richiesto
     * 
     * @return tile specifico
     */
    public Tile getTile(int id){
        return tiles.get(id);
    }
    
    /**
     * @brief ritorno di un'immagine passando un id
     * 
     * @param id id dell'immagine richiesta
     * 
     * @return immagine specifica
     */
    public BufferedImage getSprite(int id){
        return tiles.get(id).getSprite();
    }
    
    /**
     * @brief ritorno di un'immagine passando una posizione
     * 
     * @param x coordinata x dell'immagine richiesta
     * @param y coordinata y dell'immagine richiesta
     * 
     * @return immagine specifica
     */
    private BufferedImage getSprite(int x, int y){
        return atlas.getSubimage(x*64, y*64, 64, 64);
    } 
}
