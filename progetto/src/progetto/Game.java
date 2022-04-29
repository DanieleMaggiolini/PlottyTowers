package progetto;

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
import scenes.*;

public class Game extends JFrame implements Runnable {
    //contiene risoluzione monitor
    private GraphicsDevice gDevice;
    public static int currentScreenWidth, currentScreenHeight;
            
    static GameScreen gamescreen;
    //Thread del gioco
    private Thread gameThread;
    //velocita aggiornamento FPS
    private final double FPS_SET = 120.0;

    //velocita aggiornamento Updates
    private final double UPS_SET = 60.0;

    //Classes
    private Render render;
    private Menu menu;
    private Playing playing;
    private Setting setting;
    private Level1 level1;
    
    public Game() {
        //prendere la risoluzione del monitor
        GraphicsEnvironment gEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        gDevice = gEnvironment.getDefaultScreenDevice();
        setFullScreen();
        
        //creazione finestra di gioco (@param width, @param height)
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        InitClasses();
        setResizable(false); 
        add(gamescreen);
        pack();
        setVisible(true);
        setLocationRelativeTo(null);     
    }
    
    public void setFullScreen(){
        gDevice.setFullScreenWindow(this);
        currentScreenWidth = this.getWidth();
        currentScreenHeight = this.getHeight();
    }
    public int getCurrentScreenWidth(){
        return currentScreenWidth;
    }
    public int getCurrentScreenHeight(){
        return currentScreenHeight;
    }
    private void InitClasses() {
        render = new Render(this);
        gamescreen = new GameScreen(this);
        menu = new Menu(this);
        playing = new Playing(this);
        setting = new Setting(this);
        level1= new Level1(this);
    }

    //metodo per far partire il Thread del gioco
    private void start() {
        gameThread = new Thread(this) {
        };
        gameThread.start();
    }

    private void updateGame(){
        switch(GameStates.gamestates){    
                case MENU:
                    menu.updates();
                    break;
                case PLAYING:
                    playing.updates();
                    break;
                case SETTINGS:
                    setting.updates();
                    break;
                case LVL1:
                    level1.updates();
        }
    }
    
    public static void main(String[] args) {
        Game game = new Game();
        game.gamescreen.initImputs();
        game.start();
    }

    //metodo del loop del gioco
    @Override
    public void run() {
        //settings per i frame e gli update
        double timePerFrame = 1000000000.0 / FPS_SET;
        double timePerUpdate = 1000000000.0 / UPS_SET;
        long lastFrame = System.nanoTime();
        long lastUpdate = System.nanoTime();
        long lastTimeCheck = System.currentTimeMillis();
        int frames = 0;
        int updates = 0;
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

            //aggiornamento UPS
            if (now - lastUpdate >= timePerUpdate) {
                updateGame();
                lastUpdate = now;
                updates++;
            }

            //stampa FPS e UPS
            if (System.currentTimeMillis() - lastTimeCheck >= 1000) {
                System.out.println("FPS: " + frames + " UPS: " + updates);
                frames = 0;
                updates = 0;
                lastTimeCheck = System.currentTimeMillis();
            }
        }
    }
    //Getters and Setters
    public Render getRender() {
        return render;
    }

    public Menu getMenu() {
        return menu;
    }

    public Playing getPlaying() {
        return playing;
    }

    public Setting getSetting() {
        return setting;
    }
    public Level1 getLevel1() {
        return level1;
    }   
    public  void restartLevel1(){
        level1= new Level1(this);   
    }
}