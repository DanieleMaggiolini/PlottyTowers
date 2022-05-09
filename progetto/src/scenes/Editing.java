package scenes;

import helpz.ImgFix;
import helpz.LoadSave;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import objects.Tile;
import progetto.Game;
import ui.ToolBar;

public class Editing extends GameScene implements SceneMethods{
    private int[][] lvl;
    private Tile selectedTile;
    private boolean drawSelect=false;
    private int mouseX,mouseY;  
    private int lastTileX, lastTileY, lastTileId;
    private ToolBar toolbar;
    
    private String state="level1";
    
    public Editing(Game game){
        super(game);
        initClasses();
    }
    public void setState(String state){
        this.state=state;
        createLevel();
        loadLevel();
    }
    public void initClasses(){
        createLevel();
        loadLevel(); 
        int tempHeight=(int)(Game.currentScreenHeight*0.17);
        toolbar= new ToolBar(0, Game.currentScreenHeight-tempHeight, Game.currentScreenWidth, tempHeight, this);
    }  
    public void saveLevel(){
        LoadSave.saveLevel(state, lvl);
        game.getLevel1().setLevel(lvl);
    }
    public void loadLevel(){
        lvl=LoadSave.loadLevel(state);
    }
    public void createLevel(){
        int[] newLevel= new int[420];
        for (int i = 0; i < newLevel.length; i++) {
            newLevel[0]=0;
        }
        LoadSave.createLevel(state, newLevel);
    }
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
    public void drawSelectedTile(Graphics g){
        if(selectedTile!=null && drawSelect){
            g.drawImage(selectedTile.getSprite(), mouseX, mouseY, selectedTile.getSpriteWidth(), selectedTile.getSpriteHeight() , null);
        }
    }
    public void setSelectedTile(Tile tile){
        selectedTile=tile;
        drawSelect=true;
    }
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
    
        
    @Override
    public void mouseClicked(MouseEvent e) {
       if(e.getY()>(int)(Game.currentScreenHeight*0.829)){
            toolbar.mouseClicked(e);
        }else{
            changeTile(mouseX,mouseY);
        }   
    }

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
    @Override
    public void mousePressed(MouseEvent e) {
        toolbar.mousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(e.getY()>(int)(Game.currentScreenHeight*0.829)){
            toolbar.mouseReleased(e);
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if(e.getY()>(int)(Game.currentScreenHeight*0.829)){
        }else{
            changeTile(e.getX(),e.getY());
        }
    }
    
}