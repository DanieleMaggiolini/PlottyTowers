/**
 * @author  Daniele Maggiolini
 * @author Mattia Minotti
 * @version 1.0
 * @file ToolBar.java
 *
 * @brief barra per la modifica della mappa
 *
 */
package ui;

import helpz.ImgFix;
import helpz.LoadSave;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashSet;
import objects.Tile;
import progetto.Game;
import progetto.GameStates;
import static progetto.GameStates.*;
import scenes.Editing;

/**
 * @class ToolBar
 *
 * @brief gestice la barra per modificare le mappe dei vari livelli e salvarle.
 *
 */
public class ToolBar extends Bar{
    
    //bottone per salvare
    private MyButton save;
    
    //tile selezionata
    private Tile selectedTile;
    
    //Array dinamico di bottoni delle tiles
    private ArrayList<MyButton> tileButtons = new ArrayList<>();
    
    //stato di editing
    private Editing editing;
    
    /**
     @brief costruttore del bottone e carica l'immagine.
     * 
     * setta le variabili
     * 
     * @param x coordinata
     * @param y coordinata
     * @param width larghezza
     * @param height altezza
     * @param editing stato di editing
     */
    public ToolBar(int x, int y, int width, int height, Editing editing) {
        super(x, y, width, height);
        
        this.editing=editing;
        
        initButtons();
    }

    /**
     @brief inizializza i bottoni delle tiles.
     * 
     */
    private void initButtons() {
        Font f=new Font("Arial",Font.BOLD,19);
        Color c=new Color(255,0,0);
        //PULSANTE SALVA
        save = new MyButton("SAVE",(int)(Game.currentScreenWidth*0.9375), (int)(Game.currentScreenHeight*0.9259), 80, 30, f ,c);
      
        //pulsanti con tutte le tile della mappa
        int[][] lvl = LoadSave.loadLevel("level1");
        int tempHeight=(int)(Game.currentScreenHeight*0.17);
        int w = Game.currentScreenWidth/lvl[0].length-10;
        int h= (Game.currentScreenHeight-tempHeight)/lvl.length-10;
        int x = 20;
        int yOffset = 70;
        int XOffset= (int)(Game.currentScreenWidth*0.031);
        
        int i=0;
        for (Tile tile : editing.getGame().getTileManager().tiles) {
            if(i<30)
                tileButtons.add(new MyButton("", x + XOffset*i, (int)(Game.currentScreenHeight*0.855), w, h, f, c, i));
            else
                tileButtons.add(new MyButton("", x + XOffset*(i-30), (int)(Game.currentScreenHeight*0.855)+yOffset, w, h, f, c, i));     
            i++;
        }
    }
    
    /**
     @brief disegna i bottoni.
     * 
     * @param g oggetto della grafica
     */
    public void draw(Graphics g){
        g.setColor(new Color(220,123,15));
        g.fillRect(x, y, width, height);
        
        drawButton(g);
    }
    
    /**
     @brief disegna i bottoni.
     * 
     * @param g oggetto della grafica
     */
    public void drawButton(Graphics g) {
        drawTileButtons(g);
        drawSelectedTile(g);
        save.drawAC(g);
    }
    
    /**
     @brief disegna i bottoni.
     * 
     * @param g oggetto della grafica
     */    
    public void drawTileButtons(Graphics g){
        for (MyButton b : tileButtons) {
            
            //ogni tipologia di blocco
            g.drawImage(getButtonImage(b.getId()), b.x, b.y, b.width, b.height, null);
            
            
            //mouse over
            if(b.isMouseOver())
                g.setColor(Color.white);        
            else
                g.setColor(Color.black);  
            
            //bordo
            g.drawRect(b.x,b.y,b.width,b.height);
            
            //mouse pressed
            if(b.isMousePressed()){
                g.drawRect(b.x+1,b.y+1,b.width-2,b.height-2);
                g.drawRect(b.x+2,b.y+2,b.width-4,b.height-4);
            }
        }
    }
    
    /**
     @brief disegna la tile selezionata.
     * 
     * @param g oggetto della grafica
     */
    public void drawSelectedTile(Graphics g){
            if(selectedTile!=null){
                g.drawImage(selectedTile.getSprite(), (int)(Game.currentScreenWidth*0.94), (int)(Game.currentScreenHeight*0.855), selectedTile.getSpriteWidth(), selectedTile.getSpriteHeight(), null);
                g.setColor(Color.RED);
                g.drawRect((int)(Game.currentScreenWidth*0.94), (int)(Game.currentScreenHeight*0.855), selectedTile.getSpriteWidth(), selectedTile.getSpriteHeight());
            }
    }
    
    /**
     @brief ritorna l'immagine del tile di cui passiamo l'id.
     * 
     * @param identificatore per richiamare il get dell'immagine della tile
     */
    public BufferedImage getButtonImage(int id){
        return editing.getGame().getTileManager().getSprite(id);
    }
    
    /**
     @brief salva la mappa modificata.
     * 
     */
    private void saveLevel(){
        editing.saveLevel();
    }
    
    /**
     @brief gestisce i click del mouse
     * 
     * @param e evento del mouse
     */
    public void mouseClicked(MouseEvent e) {
        if(save.getBounds().contains(e.getX(), e.getY())){
            saveLevel();
            //GameStates.gamestates = LVL1;
        }
        else{
            for (MyButton b: tileButtons) {
                if(b.getBounds().contains(e.getX(),e.getY())){
                   selectedTile = editing.getGame().getTileManager().getTile(b.getId());
                   editing.setSelectedTile(selectedTile);

                   return;
                }       
            }   
        }
    }
    
    /**
     @brief gestisce le move del mouse
     * 
     * @param e evento del mouse
     */
    public void mouseMoved(MouseEvent e) {
        for (MyButton b: tileButtons) 
            b.setMouseOver(false);
        save.setMouseOver(false);
        
        if(save.getBounds().contains(e.getX(), e.getY())){
            save.setMouseOver(true);
        }else{
             for (MyButton b: tileButtons){
                if(b.getBounds().contains(e.getX(), e.getY()))
                    b.setMouseOver(true);
             }
        } 
    } 
    
    /**
     @brief gestisce le press del mouse
     * 
     * @param e evento del mouse
     */
    public void mousePressed(MouseEvent e) {
        if(save.getBounds().contains(e.getX(), e.getY())){
            save.setMousePressed(true);
        }
        else{          
            for (MyButton b: tileButtons) {
                if(b.getBounds().contains(e.getX(),e.getY())){
                   b.setMousePressed(true);
                   return;
                }
            }
        }
    }
    
    /**
     @brief gestisce le release del mouse
     * 
     * @param e evento del mouse
     */
    public void mouseReleased(MouseEvent e) {
        save.resetBooleans();
        for (MyButton b: tileButtons) {          
               b.resetBooleans();       
        }
    }
}
