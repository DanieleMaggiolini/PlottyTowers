/**
* @author  Daniele Maggiolini
* @author  Mattia Minotti
* @version 1.0
* @file Editing.java 
* 
* @brief gestisce la schermata di edit della mappa.
*
*/
package scenes;

import helpz.ImgFix;
import helpz.LoadSave;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import objects.Tile;
import progetto.Game;
import ui.ToolBar;

/**
 * @class Editing
 *
 * @brief gestice le mappe, le modifiche e i salvataggi di esse.
 *
 */
public class Editing extends GameScene implements SceneMethods{
    
    //matrice della mappa
    private int[][] lvl;
    
    //tile selezionata
    private Tile selectedTile;
    
    //booleana disegna o no la tile selezionata
    private boolean drawSelect=false;
    
    //coordinata x del mouse
    private int mouseX;
    
    //coordinata y del mouse
    private int mouseY;  
    
    //coordinata x dell'ultima tile
    private int lastTileX;
    
    //coordinata y dell'ultima tile
    private int lastTileY;
    
    //id dell'ultima tile
    private int lastTileId;
    
    //barra contenente i bottoni delle tile
    private ToolBar toolbar;
    
    //stato dal quale viene invocata la scena editing
    private String state="level1";
    
    /**
     @brief costruttore inizializza le classi e setta il game;
     * 
     * @param game oggetto del gioco
     */
    public Editing(Game game){
        super(game);
        initClasses();
    }
    
    /**
     @brief setta lo stato e carica la mappa relativa
     * 
     * @param state stato dal quale viene invocata la scena editing
     */
    public void setState(String state){
        this.state=state;
        createLevel();
        loadLevel();
    }
    
    /**
     @brief inizializza le classi.
     * 
     */
    public void initClasses(){
        createLevel();
        loadLevel(); 
        int tempHeight=(int)(Game.currentScreenHeight*0.17);
        toolbar= new ToolBar(0, Game.currentScreenHeight-tempHeight, Game.currentScreenWidth, tempHeight, this);
    }  
    
    /**
     @brief salva il livello.
     * 
     */
    public void saveLevel(){
        LoadSave.saveLevel(state, lvl);
        game.getLevel1().setLevel(lvl);
    }
    
    /**
     @brief carica il livello.
     * 
     */
    public void loadLevel(){
        lvl=LoadSave.loadLevel(state);
    }
    
    /**
     @brief inizializza il livello.
     * 
     */
    public void createLevel(){
        int[] newLevel= new int[420];
        for (int i = 0; i < newLevel.length; i++) {
            newLevel[0]=0;
        }
        LoadSave.createLevel(state, newLevel);
    }
    
    /**
     @brief disegna la mappa.
     * 
     * @param g oggetto della grafica
     */
    @Override
    public void render(Graphics g) {
        for (int y = 0; y < lvl.length; y++) {
            for (int x = 0; x < lvl[0].length; x++) {
                int id= lvl[y][x];
                g.drawImage(game.getTileManager().getSprite(id), x*Tile.spriteWidth, y*Tile.spriteHeight, Tile.spriteWidth,Tile.spriteHeight, null);
            }
        }
        
        toolbar.draw(g);
        drawSelectedTile(g);
    }
    
    /**
     @brief disegna tile selezionata.
     * 
     * @param g oggetto della grafica
     */
    public void drawSelectedTile(Graphics g){
        if(selectedTile!=null && drawSelect){
            g.drawImage(selectedTile.getSprite(), mouseX, mouseY, selectedTile.getSpriteWidth(), selectedTile.getSpriteHeight() , null);
        }
    }
    
    /**
     @brief imposta la tile selezionata.
     * 
     * @param tile selezionata
     */
    public void setSelectedTile(Tile tile){
        selectedTile=tile;
        drawSelect=true;
    }
    
    /**
     @brief cambia la tile date le coordinate.
     * 
     * @param x coordinata
     * @param y coordinata
     */
    private void changeTile(int x, int y) {
        if(selectedTile!=null){
            int tileX=x/selectedTile.getSpriteWidth();
            int tileY=y/selectedTile.getSpriteHeight();
            
            if(lastTileX== tileX && lastTileY==tileY && lastTileId==selectedTile.getId())
                return;
               
            lvl[tileY][tileX]= selectedTile.getId();
            lastTileX=tileX;
            lastTileY=tileY;
            lastTileId=selectedTile.getId();
        }
    }
    
        
    /**
     @brief gestisce i click del mouse
     * 
     * @param e evento del mouse
     */
    @Override
    public void mouseClicked(MouseEvent e) {
       if(e.getY()>(int)(Game.currentScreenHeight*0.829)){
            toolbar.mouseClicked(e);
        }else{
            changeTile(mouseX,mouseY);
        }   
    }

    /**
     @brief gestisce le move del mouse
     * 
     * @param e evento del mouse
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        if(e.getY()>(int)(Game.currentScreenHeight*0.829)){
            toolbar.mouseMoved(e);
            drawSelect=false;
        }else{
            drawSelect=true;
            if(selectedTile!=null){
                mouseX=(e.getX()/selectedTile.getSpriteWidth())*selectedTile.getSpriteWidth(); //tile viene spostato in una grlia e non pixel per pixel
                mouseY=(e.getY()/selectedTile.getSpriteHeight())*selectedTile.getSpriteHeight();  
            } 
        }
    }
    
    /**
     @brief gestisce le press del mouse
     * 
     * @param e evento del mouse
     */
    @Override
    public void mousePressed(MouseEvent e) {
        toolbar.mousePressed(e);
    }

    /**
     @brief gestisce le release del mouse
     * 
     * @param e evento del mouse
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        if(e.getY()>(int)(Game.currentScreenHeight*0.829)){
            toolbar.mouseReleased(e);
        }
    }

    /**
     @brief gestisce le drag del mouse
     * 
     * @param e evento del mouse
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        if(e.getY()>(int)(Game.currentScreenHeight*0.829)){
        }else{
            changeTile(e.getX(),e.getY());
        }
    }
    
    /**
     @brief ritorna lo stato
     * 
     * @return stato
     */
    public String getState(){
        return state;
    }
}