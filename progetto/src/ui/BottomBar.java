
package ui;

import helpz.LevelBuild;
import java.awt.Color;
import static java.awt.Color.red;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Set;
import objects.Tile;
import progetto.Game;
import static progetto.GameStates.PLAYING;
import static progetto.GameStates.SETTINGS;
import static progetto.GameStates.setGameState;
import scenes.*;
import scenes.Playing;


public class BottomBar {
    private int x,y,width,height;
    
    private ArrayList<MyButton> tileButtons = new ArrayList<>();
    private Font f;
    private Color c;
    private Level1 level1;
    private Tile selectedTile;
    
    public BottomBar(int x, int y, int width, int height, Level1 level1) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.level1=level1;
        
        initButton();
       
       
    }
    private void initButton() {
        int[][] lvl = LevelBuild.getLevelData();  
        int tempHeight=(int)(Game.currentScreenHeight*0.17);
        int w = Game.currentScreenWidth/lvl[0].length;
        int h= (Game.currentScreenHeight-tempHeight)/lvl.length;
        int x = 40;
        int y = 960/3+65;
        int yOffset = 70;
        int XOffset= 200;
        f=new Font("Arial",Font.BOLD,40);
        c=new Color(0,0,0);  
        
        int i=0;
        for (Tile tile : level1.getTileManager().tiles) {
            tileButtons.add(new MyButton(tile.getName(), x + XOffset*i, (int)(Game.currentScreenHeight*0.885), w, h, f, c, i));
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
                g.drawImage(selectedTile.getSprite(), (int)(Game.currentScreenWidth*0.94), (int)(Game.currentScreenHeight*0.885), selectedTile.getSpriteWidth(), selectedTile.getSpriteHeight(), null);
                g.setColor(Color.RED);
                g.drawRect((int)(Game.currentScreenWidth*0.94), (int)(Game.currentScreenHeight*0.885), selectedTile.getSpriteWidth(), selectedTile.getSpriteHeight());
            }
    }
    
    public BufferedImage getButtonImage(int id){
        return level1.getTileManager().getSprite(id);
    }
      
    public void mouseClicked(MouseEvent e) {
        for (MyButton b: tileButtons) {
            if(b.getBounds().contains(e.getX(),e.getY())){
               selectedTile = level1.getTileManager().getTile(b.getId());
               level1.setSelectedTile(selectedTile);
                
               return;
            }
        }
    }
    public void mouseMoved(MouseEvent e) {
        for (MyButton b: tileButtons) {
            b.setMouseOver(false);
        }
        for (MyButton b: tileButtons) {
            if(b.getBounds().contains(e.getX(),e.getY())){
               b.setMouseOver(true);
               return;
            }
        }
    }   
    public void mousePressed(MouseEvent e) {
        for (MyButton b: tileButtons) {
            if(b.getBounds().contains(e.getX(),e.getY())){
               b.setMousePressed(true);
               return;
            }
        }
    }

    public void mouseReleased(MouseEvent e) {
        for (MyButton b: tileButtons) {          
               b.resetBooleans();       
        }
    }
}
