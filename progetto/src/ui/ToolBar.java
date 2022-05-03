
package ui;

import helpz.LevelBuild;
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


public class ToolBar extends Bar{
    private MyButton save;
    private Tile selectedTile;
    private ArrayList<MyButton> tileButtons = new ArrayList<>();
    
    private Editing editing;
    public ToolBar(int x, int y, int width, int height, Editing editing) {
        super(x, y, width, height);
        
        this.editing=editing;
        
        initButtons();
    }

    private void initButtons() {
        Font f=new Font("Arial",Font.BOLD,19);
        Color c=new Color(255,0,0);
        //PULSANTE SALVA
        save = new MyButton("SAVE",(int)(Game.currentScreenWidth*0.9375), (int)(Game.currentScreenHeight*0.9259), 80, 30, f ,c);
      
        //pulsanti con tutte le tile della mappa
        int[][] lvl = LevelBuild.getLevelData();  
        int tempHeight=(int)(Game.currentScreenHeight*0.17);
        int w = Game.currentScreenWidth/lvl[0].length-10;
        int h= (Game.currentScreenHeight-tempHeight)/lvl.length-10;
        int x = 20;
        int yOffset = 70;
        int XOffset= 60;
        
        int i=0;
        for (Tile tile : editing.getGame().getTileManager().tiles) {
            if(i<29)
                tileButtons.add(new MyButton(tile.getName(), x + XOffset*i, (int)(Game.currentScreenHeight*0.855), w, h, f, c, i));
            else
                tileButtons.add(new MyButton(tile.getName(), x + XOffset*(i-29), (int)(Game.currentScreenHeight*0.855)+yOffset, w, h, f, c, i));     
            i++;
        }
    }
    
    
    public void draw(Graphics g){
        g.setColor(new Color(220,123,15));
        g.fillRect(x, y, width, height);
        
        drawButton(g);
    }
    public void drawButton(Graphics g) {
        drawTileButtons(g);
        drawSelectedTile(g);
        save.drawAC(g);
    }
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
    public void drawSelectedTile(Graphics g){
            if(selectedTile!=null){
                g.drawImage(selectedTile.getSprite(), (int)(Game.currentScreenWidth*0.94), (int)(Game.currentScreenHeight*0.855), selectedTile.getSpriteWidth(), selectedTile.getSpriteHeight(), null);
                g.setColor(Color.RED);
                g.drawRect((int)(Game.currentScreenWidth*0.94), (int)(Game.currentScreenHeight*0.855), selectedTile.getSpriteWidth(), selectedTile.getSpriteHeight());
            }
    }
    public BufferedImage getButtonImage(int id){
        return editing.getGame().getTileManager().getSprite(id);
    }
    
    private void saveLevel(){
        editing.saveLevel();
    }
    public void mouseClicked(MouseEvent e) {
        if(save.getBounds().contains(e.getX(), e.getY())){
            saveLevel();
            GameStates.gamestates = LVL1;
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
    public void mouseReleased(MouseEvent e) {
        save.resetBooleans();
        for (MyButton b: tileButtons) {          
               b.resetBooleans();       
        }
    }
}
