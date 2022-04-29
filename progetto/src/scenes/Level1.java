
package scenes;

import helpz.LevelBuild;
import helpz.LoadSave;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import managers.TileManager;
import objects.Tile;
import progetto.Game;
import static progetto.GameStates.MENU;
import static progetto.GameStates.setGameState;
import ui.BottomBar;
import ui.LevelMenu;
import ui.MyButton;
import ui.PauseOverlay;


public class Level1 extends GameScene implements SceneMethods{
    private BufferedImage[] naruto;
    private BufferedImage narutoall;
    private BufferedImage ingranaggio;
    
    private int[][] lvl;
    private TileManager tilemanager;
    private BottomBar bottombar;
    private Tile selectedTile;
    private int mouseX,mouseY;
    
    private int aniTick,aniIndex, aniSpeed = 5; //30
    private int assex=100;
    
    
    private MyButton Bmenu;
    private Font f;
    private Color c;
    
    private PauseOverlay pauseoverlay;
    private boolean paused=false;
            
    public Level1(Game game) {
        super(game);
 
        //il livello
        initClasses();
           
        impImage();   
        
        initButtons();
        
        loadAnimations();          
    }  
    public void initClasses(){
        lvl = LevelBuild.getLevelData();   
        //TileManager
        tilemanager = new TileManager();
        int tempHeight=(int)(Game.currentScreenHeight*0.17);
        bottombar=new BottomBar(0, Game.currentScreenHeight-tempHeight, Game.currentScreenWidth, tempHeight, this);  
        pauseoverlay=new PauseOverlay(this, super.getGame());
    }
    private void impImage() {
        ingranaggio= LoadSave.getImage(LoadSave.INGRANAGGIO);
        narutoall= LoadSave.getImage(LoadSave.NARUTO);
        
    }
    private void initButtons() {
        int w = 80;
        int h = 80;
        int x = 10;
        int y = 10;
        int yOffset = 100;
        f=new Font("Arial",Font.BOLD,40);
        c=new Color(255,0,0);
        Bmenu = new MyButton("", x, y, w, h, f, c);
    }
    
     private void loadAnimations() {
         //75==64
        naruto=new BufferedImage[8];
        for (int i = 0; i < naruto.length; i++) {
            naruto[i] = narutoall.getSubimage(75*i, 0, 75, 75);        
        }
    }
    private void updateAnimationTick() {
        aniTick++;
        if(aniTick>= aniSpeed){
            aniTick=0;
            aniIndex++;
            if(aniIndex>= naruto.length)
                aniIndex=0;
        }
    }
    
    public void updates(){
        
    }
    
    @Override
    public void render(Graphics g) {
        drawBackground(g);
        g.drawImage(ingranaggio, 10, 10, 80, 80, null);
        drawButton(g);
        
        
        g.drawImage(naruto[aniIndex], assex, 100, 134, 134, null); //widht = 164 height =164
        
        if(paused){
            pauseoverlay.draw(g);
            pauseoverlay.update();
        } else{
            
            updateAnimationTick();
            assex+=3;
        }   
        bottombar.draw(g);
        drawSelectedTile(g);
    }
    private void drawBackground(Graphics g){
        int tempHeight=(int)(Game.currentScreenHeight*0.17);
        int tempX = Game.currentScreenWidth/lvl[0].length;
        int tempY= (Game.currentScreenHeight-tempHeight)/lvl.length;
        for (int y = 0; y < lvl.length; y++) {
            for (int x = 0; x < lvl[0].length; x++) {
                int id = lvl[y][x];
                g.drawImage(tilemanager.getSprite(id), x*tempX, y*tempY, tempX, tempY, null);
            }
        }
    }
    
    private void drawButton(Graphics g) {
        Bmenu.draw(g);
    }
    public void drawSelectedTile(Graphics g){
        if(selectedTile!=null){
            g.drawImage(selectedTile.getSprite(), mouseX, mouseY, selectedTile.getSpriteWidth(), selectedTile.getSpriteHeight() , null);
        }
    }
    public void setSelectedTile(Tile tile){
        selectedTile=tile;
    }
    public TileManager getTileManager(){
        return tilemanager;
    }
    
    public void setPaused(boolean paused){
        this.paused=paused;
    }
    public boolean getPaused(){
        return paused;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        if (Bmenu.getBounds().contains(e.getX(), e.getY())) {
            setPaused(!getPaused());
        }
        bottombar.mouseClicked(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        Bmenu.setMouseOver(false);
        if (Bmenu.getBounds().contains(e.getX(), e.getY())) {
            Bmenu.setMouseOver(true);
        }
        if(e.getY()>(int)(Game.currentScreenHeight*0.829)){
            bottombar.mouseMoved(e);
        }
        mouseX=e.getX();
        mouseY=e.getY();            
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (Bmenu.getBounds().contains(e.getX(), e.getY())) {
            Bmenu.setMousePressed(true);
        }
        bottombar.mousePressed(e);
        pauseoverlay.mousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        resetButtons();
        bottombar.mouseReleased(e);
        pauseoverlay.mouseReleased(e);
    }
    
    @Override
    public void mouseDragged(MouseEvent e) {
    }
    private void resetButtons() {
        Bmenu.resetBooleans();
    }
    
}
