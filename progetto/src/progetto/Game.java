/**
* @author  Daniele Maggiolini
* @author  Mattia Minotti
* @version 0.0
* @file Game.java 
* 
* @brief il file main che gestisce tutto il progetto,
* contiene i vari thread e tutte le classi del progetto
*
*/
package progetto;

import helpz.LoadSave;
import inputs.KeyboardListener;
import inputs.MyMouseListener;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import managers.TileManager;
import scenes.*;


/** 
* @class Game
* 
* @brief JFrame main gestisce la schermata di gioco 
* 
* questa classe gestira l'inizializzazione di classi e Thread del progetto
* 
*/ 

public class Game extends JFrame implements Runnable {
    
    //contiene risoluzione monitor
    private GraphicsDevice gDevice;
    
    //larghezza dello scermo
    public static int currentScreenWidth;
    
    //altezza dello schermo
    public static int currentScreenHeight;
          
    //classe JPanel 
    static GameScreen gamescreen;
    
    //Thread del gioco
    private Thread gameThread;
    
    //Thread degli update
    private ThreadUpdate threadupdate;
    
    //velocita aggiornamento FPS
    private final double FPS_SET = 120.0;

    //velocita aggiornamento Updates
    //private final double UPS_SET = 60.0;

    //classe del render
    private Render render;
    
    //schermata menu
    private Menu menu;
    
    //schermata del menu livelli
    private Playing playing;
    
    //schermata per l'editing della mappa
    private Editing editing;
    
    //schermata primo livello
    private Level1 level1;
    
    private Level2 level2;
    
    private Level3 level3;
    
    private Level4 level4;
    
    //oggetto per gestire le Tiles
    private TileManager tilemanager;
    
    /**
     @brief costruttore inizializza tutte le classi ed alcune info riguardo la visualizzazione della schermata.

     richiama il metodo per inizializzare le classi,
     imposta il full screen,
     definisce l'impostazione predefinita in chiusura dell'applicazione,
     imposta la schermata visibile.
     */
    public Game() {
        //prendere la risoluzione del monitor
        GraphicsEnvironment gEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        gDevice = gEnvironment.getDefaultScreenDevice();
        setFullScreen();
        
        //creazione finestra di gioco (@param width, @param height)
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        InitClasses();
        createLevel();
        setResizable(false); 
        add(gamescreen);
        pack();
        setVisible(true);
        setLocationRelativeTo(null);     
    }   
    /**
     @brief imposta la schermata di gioco in full screen.

     imposta il full screen richiamando la funzione setFullScreen(this)
     aggiorna le variabili di grandezza dello schermo (currentScreenWidth e currentScreenHeight)
     */
    public void setFullScreen(){
        setUndecorated(true);
        gDevice.setFullScreenWindow(this);
        currentScreenWidth = this.getWidth();
        currentScreenHeight = this.getHeight();
    }   
    
    /**
     * @brief get della larghezza dello scermo.
     *
     * @return variabile della larghezza dello schermo
     */
    public int getCurrentScreenWidth(){
        return currentScreenWidth;
    }
    
    /**
     * @brief get dell'altezza dello scermo.
     *
     * @return variabile dell'altezza dello schermo
     */
    public int getCurrentScreenHeight(){
        return currentScreenHeight;
    }
    
    /**
     * @brief inizializza gli oggetti presenti nel progetto.
     */
    private void InitClasses() {
        tilemanager = new TileManager();
        
        render = new Render(this);
        gamescreen = new GameScreen(this);
        menu = new Menu(this);
        playing = new Playing(this);
        int[] unlock =LoadSave.unlockLevel();
        if(unlock[1]==0)
            playing.getLevelMenu().unlockLevel("level1");
        if(unlock[2]==0)
            playing.getLevelMenu().unlockLevel("level2");
        if(unlock[3]==0)
            playing.getLevelMenu().unlockLevel("level3");
        editing = new Editing(this);
        level1= new Level1(this); 
        level2 = new Level2(this);
        level3 = new Level3(this);
        level4 = new Level4(this);
    }
    
    /**
     * @brief verificare che la mappa del primo livello esita in caso contrario 
     * crea il file contenente una mappa vuota.
     */
    public void createLevel(){
        int[] arr = new int[420];
        for (int i = 0; i < arr.length; i++) {
            arr[i]= 0;
        }
        LoadSave.createLevel("level1", arr);
    }
    
    
    /**
     * @brief metodo per inizializzare e far partire il Thread del gioco.
     */
    private void startGame() {
        gameThread = new Thread(this) {
        };
        
        gameThread.start();
        threadupdate= new ThreadUpdate(this);
    }
        
    /**
     * @brief inizializza la classe, il game screen e starta il gioco.
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.gamescreen.initImputs();
        game.startGame();
    }

    /**
     * @brief loop del gioco che gestisce FPS.
     *
     * grazie agli attributi ogni volta che cicla aggiorna la variabile degli FPS
     * e solo quando dall ultimo check supera il secondo li stampa.
     */
    @Override
    public void run() {
        //settings per i frame
        double timePerFrame = 1000000000.0 / FPS_SET;
        long lastFrame = System.nanoTime();
        long lastTimeCheck = System.currentTimeMillis();
        int frames = 0;
        //int updates = 0;
        long now;

        //loop del gioco
        while (true) {
            now = System.nanoTime();
            //aggiornamento FPS
            if (now - lastFrame >= timePerFrame) {
                repaint();
                lastFrame = now;
                frames++;
            }
            
            //stampa FPS
            if (System.currentTimeMillis() - lastTimeCheck >= 1000) {
                System.out.println("FPS: " + frames);
                frames = 0;
                //updates = 0;
                lastTimeCheck = System.currentTimeMillis();
            }
        }
    }
    
    /**
     * @brief getter dell'oggetto render.
     * 
     * @return oggetto render
     */
    public Render getRender() {
        return render;
    }
    
    /**
     * @brief getter dell'oggetto menu.
     * 
     * @return oggetto menu
     */
    public Menu getMenu() {
        return menu;
    }
    
    /**
     * @brief getter dell'oggetto playing.
     * 
     * @return oggetto playing
     */
    public Playing getPlaying() {
        return playing;
    }
    
    /**
     * @brief getter dell'oggetto editing.
     * 
     * @return oggetto editing
     */
    public Editing getEditing(){
        return editing;
    }
    
    /**
     * @brief getter dell'oggetto level1.
     * 
     * @return oggetto level1
     */
    public Level1 getLevel1() {
        return level1;
    }   
    
    public Level2 getLevel2() {
        return level2;
    } 
    
    public Level3 getLevel3() {
        return level3;
    }  
    
    public Level4 getLevel4() {
        return level4;
    }  
    /**
     * @brief metodo che restarta il primo livello.
     * 
     * viene modificata l'oggetto del primo livvello re inizializzandolo
     */
    public  void restartLevel(String state){
        switch(state){
            case "level1":
                level1= new Level1(this); 
                break;
            case "level2":
                level2= new Level2(this); 
                break;
            case "level3":
                level3= new Level3(this); 
                break; 
            case "level4":
                level4= new Level4(this); 
                break;      
        } 
    }
    
    /**
     * @brief getter dell'oggetto tilemanager.
     * 
     * @return oggetto tilemanager
     */
    public TileManager getTileManager(){
        return tilemanager;
    }
}