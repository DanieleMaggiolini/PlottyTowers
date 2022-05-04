package scenes;

import helpz.ImgFix;
import helpz.LoadSave;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import managers.TileManager;
import progetto.Game;
import static progetto.GameStates.PLAYING;
import static progetto.GameStates.MENU;
import static progetto.GameStates.setGameState;
import ui.ActionBar;
import ui.LevelMenu;
import ui.MyButton;
import ui.PauseOverlay;


public class Playing extends GameScene implements SceneMethods{
    private BufferedImage[] naruto;
    private BufferedImage narutoall;
    private BufferedImage ingranaggio;
    private LevelMenu levelmenu;
    
    private TileManager tilemanager;
    
    private int aniTick,aniIndex, aniSpeed = 30;
    private int assex=100;
    
    
    private MyButton Bmenu;
    private Font f;
    private Color c;
    
    private PauseOverlay pauseoverlay;
    private boolean paused=true;
            
    
    public Playing(Game game) {
        super(game);
 
        //il livello
        initClasses();
           
        impImage();   
        
        initButtons();
        
        loadAnimations();          
    }  
    public void initClasses(){
        //TileManager
        tilemanager = new TileManager();
        int tempHeight=(int)(Game.currentScreenHeight*0.17);
        levelmenu = new LevelMenu();
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
        naruto=new BufferedImage[6];
        for (int i = 0; i < naruto.length; i++) {
            naruto[i] = narutoall.getSubimage(64*i, 0, 64, 64);        
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
        //updateAnimationTick();
        //g.drawImage(naruto[aniIndex], assex, 100, 164, 164, null);
        //assex++;
        //pauseoverlay.update();
        //pauseoverlay.draw(g);
        
        levelmenu.draw(g);
        
        g.drawImage(ingranaggio, 10, 10, 80, 80, null);
        Bmenu.draw(g);      
    }
    public TileManager getTileManager(){
        return tilemanager;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        if (Bmenu.getBounds().contains(e.getX(), e.getY())) {
            setGameState(MENU);
        }
        levelmenu.mouseClicked(e);   
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        Bmenu.setMouseOver(false);
        if (Bmenu.getBounds().contains(e.getX(), e.getY())) {
            Bmenu.setMouseOver(true);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        levelmenu.mousePressed(e);
        if (Bmenu.getBounds().contains(e.getX(), e.getY())) {
            Bmenu.setMousePressed(true);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        resetButtons();
    }
    
    @Override
    public void mouseDragged(MouseEvent e) {
        levelmenu.mouseDragged(e);
    }
    private void resetButtons() {
        Bmenu.resetBooleans();
    }
    
}
