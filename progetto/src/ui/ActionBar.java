/**
* @author  Daniele Maggiolini
* @author  Mattia Minotti
* @version 0.0
* @file ActionBar.java 
* 
* @brief barra visualizzata nei livelli contenente le varie difese,
* con le loro info, e informazioni vita ondata ecc.
*
*/

package ui;


import helpz.Constants.Towers;
import helpz.LoadSave;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.Set;
import progetto.Game;
import scenes.*;
import managers.*;
import objects.Tile;
import towers.*;

/** 
* @class ActionBar
* 
* @brief classe figlia della classe "Bar" per gestire la barra presente nei livelli
* 
*/ 

public class ActionBar extends Bar{
    
    //oggetto per la gestione del font delle scritte
    private Font f;
    
    //oggetto per la gestione del colore degli elemeni da draware
    private Color c;
    
    //oggeto del game
    private Game game;
    
    //stringa contenente lo stato(quindi il livello) dal quale la classe è stata invocata
    private String state;
    
    //array di bottoni 1*torre
    private MyButton[] towerButtons;
    
    //oggetto della torre  selezionata (ovvero clickata) per essere piazzata
    private Tower selectedTower;
    
    //oggetto della torre che abbiamo selezionato (ovvero clickato) per essere visualizzata
    private Tower displayedTower;
    
    public ActionBar(int x, int y, int width, int height, Game game, String state) {
        super(x,y,width,height);
        this.game = game;
        this.state = state;
        
        initButton(); 
    }
    private void initButton() {
        towerButtons = new MyButton[3];
        
        int[][] lvl = LoadSave.loadLevel("level1");
        int tempHeight=(int)(Game.currentScreenHeight*0.17);
        int w = Game.currentScreenWidth/lvl[0].length-10;
        int h= (Game.currentScreenHeight-tempHeight)/lvl.length-10;
        int x = 50;
        int y = (int)(Game.currentScreenHeight*0.89);
        int xOffset= (int)(Game.currentScreenWidth*0.05);
        
        for (int i = 0; i < towerButtons.length; i++) {
            towerButtons[i] = new MyButton("", x+xOffset*i, y, w, h, i);
        }
    }  
    public void draw(Graphics g){
        g.setColor(new Color(220,123,15));
        g.fillRect(x, y, width, height);
        
        drawButton(g);
        
        drawDiaplayedTower(g);
        
        //wave info 
        drawWaveInfo(g);
    }
    public void drawButton(Graphics g) {
        
        for (MyButton b : towerButtons) {
            b.drawA(g);
            switch(state){
                case "level1":
                    g.drawImage(game.getLevel1().getTowerManager().getTowerImgs()[b.getId()][0], b.x, b.y, b.width, b.height, null);
                    break;
            }
        }
    }
    
    private void drawDiaplayedTower(Graphics g) {
        int[][] lvl = LoadSave.loadLevel("level1");
        
        int w = Tile.spriteWidth-10;
        int h=  Tile.spriteHeight-10;
        int x = (int) (Game.currentScreenWidth*0.7);
        int y = (int)(Game.currentScreenHeight*0.89);
        int xOffset= (int)(Game.currentScreenWidth*0.05);
        if(displayedTower != null){
            g.setColor(Color.gray);
            g.fillRect (x-10, y-15, 220, 85);
            g.setColor(Color.black);
            g.drawRect (x-10, y-15, 220, 85);
            g.drawRect (x, y, w, h);
            switch(state){
                case "level1":
                    g.drawImage(game.getLevel1().getTowerManager().getTowerImgs()[displayedTower.getTypetower()][0], x, y, w, h, null);
                    break;
            }       
            g.setFont (new Font("LucidaSans", Font.BOLD, 15));
            g.drawString("" + Towers.getName(displayedTower.getTypetower()), x+60, y);
            g.drawString("ID: " + displayedTower.getId(), x+60, y+40);   
            //drawDisplayedTowerBorder(g);
            drawDisplayedTowerRange(g);
        }
    }
    public void drawDisplayedTowerBorder(Graphics g){
        g.setColor(Color.WHITE);
        g.drawRect(displayedTower.getX(), displayedTower.getY(), Tile.spriteWidth, Tile.spriteHeight);
    }
    
    public void drawDisplayedTowerRange(Graphics g){
        g.setColor(Color.WHITE);
        g.drawOval(displayedTower.getX()- (int)(displayedTower.getRange()/2)*2 + Tile.spriteWidth/2, displayedTower.getY()-(int)(displayedTower.getRange()/2)*2 + Tile.spriteHeight/2, (int)displayedTower.getRange()*2, (int)displayedTower.getRange()*2);
    }
    
    public void drawWaveInfo(Graphics g){
          drawWaveTimer(g);
          drawEnemyLeft(g);
          drawWaveLeft(g);
    }
      
    private DecimalFormat decimal= new DecimalFormat("0.0");
    private void drawWaveTimer(Graphics g){
        switch(state){
            case "level1":
                if(game.getLevel1().getWaveManager().isTimerStart()){
                    int timeleftx= (int)(Game.currentScreenWidth*0.850);
                    int timelefty= (int)(Game.currentScreenHeight*0.870);
                    g.setColor(Color.BLACK);
                    g.setFont (new Font("LucidaSans", Font.BOLD, 22));
                    g.drawString("prossima ondata tra: " + decimal.format(game.getLevel1().getWaveManager().getTimeLeft()), timeleftx, timelefty);
                }  
                break;
        } 
    }
    
    private void drawEnemyLeft(Graphics g){
        int current=0;
        int size=0;
        int waveleftx= (int)(Game.currentScreenWidth*0.850);
        int wavelefty= (int)(Game.currentScreenHeight*0.925);
        g.setColor(Color.BLACK);
        g.setFont (new Font("LucidaSans", Font.BOLD, 30));
        switch(state){
            case "level1":
                    current= game.getLevel1().getWaveManager().getWaveIndex();
                    size= game.getLevel1().getWaveManager().getWaves().size(); 
                break;
        } 
        g.drawString("ondata: " + (current+1) + "/" + size, waveleftx, wavelefty);
    }
    
    private void drawWaveLeft(Graphics g){
        int enemyleft=0;
        int enemyleftx= (int)(Game.currentScreenWidth*0.850);
        int enemylefty= (int)(Game.currentScreenHeight*0.980);
        g.setColor(Color.BLACK);
        g.setFont (new Font("LucidaSans", Font.BOLD, 22));
        switch(state){
            case "level1":
                    enemyleft= game.getLevel1().getEnemyManager().getEnemyRemaning();
                break;
        } 
        g.drawString("nemici rimasti: " + enemyleft, enemyleftx, enemylefty);
    }
    
    public void displayTower(Tower t) {
        displayedTower = t;
    }
    
    public void mouseClicked(MouseEvent e) {
        for(MyButton b : towerButtons){
            if(b.getBounds().contains(e.getX(), e.getY())){
                selectedTower = new Tower(0, 0, -1, b.getId());
                switch(state){
                case "level1":
                    game.getLevel1().setSelectedTower(selectedTower);
                    break;
                }
                
                return;
            }
        }
    }
    public void mouseMoved(MouseEvent e) {
        
    }   
    public void mousePressed(MouseEvent e) {
        
    }
    public void mouseReleased(MouseEvent e) {
        
    }  
}