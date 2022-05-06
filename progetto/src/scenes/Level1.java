package scenes;

import helpz.LoadSave;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import managers.EnemyManager;
import progetto.Game;
import ui.ActionBar;
import ui.MyButton;
import ui.PauseOverlay;
import objects.Tile;


public class Level1 extends GameScene implements SceneMethods{
    private BufferedImage[] naruto;
    private BufferedImage narutoall;
    
    private EnemyManager enemymanager;
    ////////////////////
    private BufferedImage[] luffy;
    private BufferedImage luffyall;
    
    private int[] susanoIndex;
    private int susanoRow=0;
    private BufferedImage[][] susano;
    private BufferedImage susanoall;
    
    private BufferedImage sasukeAttackall;
    private BufferedImage[][] sasukeAttack;
    /////////////////////
    
    private BufferedImage ingranaggio;
    
    private int[][] lvl;
    private ActionBar bottombar;
    
    private int mouseX,mouseY;
    
    private int aniTick,aniIndex,Index7, aniSpeed = 18;
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
        loadLevel();
        enemymanager = new EnemyManager(this);
        int tempHeight=(int)(Game.currentScreenHeight*0.17);
        bottombar=new ActionBar(0, Game.currentScreenHeight-tempHeight, Game.currentScreenWidth, tempHeight, this);  
        pauseoverlay=new PauseOverlay(this, super.getGame(), "level1");
        susanoIndex= new int[8];
    }
    private void impImage() {
        ingranaggio= LoadSave.getImage(LoadSave.INGRANAGGIO);
        narutoall= LoadSave.getImage(LoadSave.NARUTO);
        luffyall= LoadSave.getImage(LoadSave.LUFFY);
        susanoall= LoadSave.getImage(LoadSave.SUSANO);
        sasukeAttackall= LoadSave.getImage(LoadSave.SASUKE_ATTACK);
        
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
        luffy=new BufferedImage[8];
        for (int i = 0; i < naruto.length; i++) {
            luffy[i] = luffyall.getSubimage(75*i, 0, 75, 75);        
        }
        sasukeAttack=new BufferedImage[2][7];
        for (int i = 0; i < sasukeAttack[0].length; i++) {
            sasukeAttack[0][i] = sasukeAttackall.getSubimage(64*i, 0, 64, 128);        
        }
        for (int i = 0; i < 3; i++) {
            sasukeAttack[1][i] = sasukeAttackall.getSubimage(64*i, 128, 64, 64);        
        }
        loadSusano();
    }
     private void loadSusano() {
        susano=new BufferedImage[8][7];
        for (int i = 0; i < 7; i++) {
            susano[0][i] = susanoall.getSubimage(128*i, 0, 128, 128);        
        }
        for (int i = 0; i < 6; i++) {
            if(i!=5){
                susano[1][i] = susanoall.getSubimage(128*i, 128, 128, 128);      
            }else{
                susano[1][5] = susanoall.getSubimage(128*i, 128, 128*2, 128);      
            }       
        }
        susano[2][0] = susanoall.getSubimage(0, 256, 128*3, 128*2);         
        susano[2][1] = susanoall.getSubimage(128*3, 256, 128*4, 128*3);
        susano[3][0] = susanoall.getSubimage(0, 128*5, 128*5, 128*4);       
        for (int i = 0; i < 2; i++) {
              susano[4][i] = susanoall.getSubimage(448*i, 128*9, 448, 128*4);         
        }
        for (int i = 0; i < 3; i++) {
              susano[5][i] = susanoall.getSubimage(298*i, 128*13, 298, 128*2);         
        }
        for (int i = 0; i < 3; i++) {
              susano[6][i] = susanoall.getSubimage(298*i, 128*15, 298, 128*2);         
        }
        for (int i = 0; i < 3; i++) {
              susano[7][i] = susanoall.getSubimage(298*i, 128*17, 298, 128*2);         
        }
    }
    public void loadLevel(){
        lvl=LoadSave.loadLevel("level1");
    }
    public void setLevel(int[][] lvl){
        this.lvl=lvl;
    }
    private void updateAnimationTick() {
        aniTick++;
        if(aniTick>= aniSpeed){
            aniTick=0;
            aniIndex++;
            if(Index7 < 6)
                Index7++;
            
            susanoIndex[susanoRow]++;
            
            if(aniIndex>= naruto.length)
                aniIndex=0;
                
            
            
            if(susanoIndex[0]>= 7){
                susanoIndex[0]=0;
                susanoRow++;
            }
            else if(susanoIndex[1]>= 6){
                susanoIndex[1]=0;
                susanoRow++;
            }
            else if(susanoIndex[2]>= 2){
                susanoIndex[2]=0;
                susanoRow++;
            }
            else if(susanoIndex[3]>= 1){
                susanoIndex[3]=0;
                susanoRow++;
            }
            else if(susanoIndex[4]>= 2){
                susanoIndex[4]=0;
                susanoRow++;
            }
            else if(susanoIndex[5]>= 3){
                susanoIndex[5]=0;
                susanoRow++;
            }
            else if(susanoIndex[6]>= 3){
                susanoIndex[6]=0;
                susanoRow++;
            }
            else if(susanoIndex[7]>= 1){
                susanoIndex[7]=0;
                susanoRow=0;
            }
        }   
    }
    
    public void updates(){
        enemymanager.update();
    }
    int fire=890;
    int fireIndex=0;
    @Override
    public void render(Graphics g) {
        drawBackground(g);
        g.drawImage(ingranaggio, 10, 10, 80, 80, null);
        drawButton(g);
        enemymanager.draw(g);
              
        g.drawImage(naruto[aniIndex], assex, 100, 134, 134, null);
        g.drawImage(sasukeAttack[0][Index7], 900, 400, 64, 128, null);
        if(Index7>=5){
            g.drawImage(sasukeAttack[1][fireIndex/40], fire, 435, 64, 64, null);
            fire--;
            if(fireIndex<119)
                fireIndex++;
            if(fire<=-64){
                fire=890;
                Index7=0;
                fireIndex=0;
            }
        }
        
        //////////////
        int susanoX=600;
        int susanoY=600;
        int dim=128;
        switch(susanoRow){
            case 0:
                    g.drawImage(susano[0][susanoIndex[0]], susanoX-dim/2, susanoY-dim, dim, dim, null);
                break;
            case 1:
                    if(susanoIndex[1]!=5){
                    g.drawImage(susano[1][susanoIndex[1]], susanoX-dim/2, susanoY-dim, dim, dim, null);    
                     }else{
                    g.drawImage(susano[1][5], susanoX-dim, susanoY-dim, dim*2, dim, null);    
                    } 
                break;
            case 2:
                if(susanoIndex[2]==0){
                    g.drawImage(susano[2][susanoIndex[2]], susanoX-dim*3/2, susanoY-dim*2, dim*3, dim*2, null);
                }else{
                    g.drawImage(susano[2][susanoIndex[2]], susanoX-dim*2, susanoY-dim*3, dim*4, dim*3, null);
                }  
                break;
            case 3:
                g.drawImage(susano[3][susanoIndex[3]], susanoX-dim*5/2, susanoY-dim*4, dim*5, dim*4, null);
                break;
            case 4:
                g.drawImage(susano[4][susanoIndex[4]], susanoX-((int)(dim*3.5))/2, susanoY-dim*4,(int)(dim*3.5), dim*4, null);
                break;
            default:
                g.drawImage(susano[susanoRow][susanoIndex[susanoRow]], susanoX-((int)(dim*2.32))/2, susanoY-dim*2, (int)(dim*2.32), dim*2, null);
                break;
        }
        //////////////
        if(paused){
            pauseoverlay.draw(g);
            pauseoverlay.update();
        } else{
            
            updateAnimationTick();
            assex++;
        }   
        bottombar.draw(g);
        
    }
    private void drawBackground(Graphics g){
        for (int y = 0; y < lvl.length; y++) {
            for (int x = 0; x < lvl[0].length; x++) {
                int id= lvl[y][x];
                g.drawImage(game.getTileManager().getSprite(id), x*Tile.spriteWidth, y*Tile.spriteHeight,Tile.spriteWidth,Tile.spriteHeight, null);
            }
        }
    }
    
    private void drawButton(Graphics g) {
        Bmenu.draw(g);
    }
    public void setPaused(boolean paused){
        this.paused=paused;
    }
    public boolean getPaused(){
        return paused;
    }
    public int getTileType(int x, int y){
        if(x>= Game.currentScreenWidth || x<0)
            return 0;
        if(y>=Tile.spriteHeight*14 || y<0)
            return 0;   
        int id= lvl[y/Tile.spriteHeight][x/Tile.spriteWidth];
        return game.getTileManager().getTile(id).getTileType();
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getY()>(int)(Game.currentScreenHeight*0.829)){
            bottombar.mouseClicked(e);
        }else{
            if (Bmenu.getBounds().contains(e.getX(), e.getY())) {
            setPaused(!getPaused());
            }  
        }  
    }
    @Override
    public void mouseMoved(MouseEvent e) {
        Bmenu.setMouseOver(false);
        if (Bmenu.getBounds().contains(e.getX(), e.getY())) {
            Bmenu.setMouseOver(true);
        }
        if(e.getY()>(int)(Game.currentScreenHeight*0.829)){
            bottombar.mouseMoved(e);      
        }else{        
                mouseX=(e.getX()/Tile.spriteWidth)*Tile.spriteWidth; //tile viene spostato in una grlia e non pixel per pixel
                mouseY=(e.getY()/Tile.spriteHeight)*Tile.spriteHeight;   
        }
             
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (Bmenu.getBounds().contains(e.getX(), e.getY())) {
            Bmenu.setMousePressed(true);
        }
        if(e.getY()>(int)(Game.currentScreenHeight*0.829)){
            bottombar.mousePressed(e);
        }
        if(paused)
            pauseoverlay.mousePressed(e);      
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        resetButtons();
        if(e.getY()>(int)(Game.currentScreenHeight*0.829)){
            bottombar.mouseReleased(e);
        }   
        if(paused)
            pauseoverlay.mouseReleased(e);
    }
    @Override
    public void mouseDragged(MouseEvent e) {
    }
    private void resetButtons() {
        Bmenu.resetBooleans();
    }  
}