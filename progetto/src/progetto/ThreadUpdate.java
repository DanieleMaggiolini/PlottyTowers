/**
* @author  Daniele Maggiolini
* @author  Mattia Minotti
* @version 0.0
* @file ThreadUpdate.java 
* 
* @brief gestisce il richiamo dei metodi per l'aggiornare il gioco.
*
*/
package progetto;

/** 
* @class ThreadUpdate
* 
* @brief serve per gestire i vari metodi di update delle schermate
* richiamando solo l'update corrispondente ad esse.
* 
*/
public class ThreadUpdate extends Thread {
    
    //oggetto Thread per gli update del gioco
    private Thread gameUpdate;
    
    //oggetto del game
    private Game game;
    
    //contiene l'argomento che andra' ad aggiornare
    private String whatupdate;
    
    //numero di update al secondo
    private final double UPS_SET = 60.0;

    /**
     @brief costruttore che setta il game e inizializza e starta il gameUpdate(facendo partire la run).

     @param game oggetto del game
     */
    public ThreadUpdate(Game game, String whatupdate) {
        this.game = game;
        this.whatupdate=whatupdate;
        gameUpdate = new Thread(this) {

        };
        gameUpdate.start();
    }
    
    /**
     * @brief loop del gioco che gestisce la frequenza degli update.
     *
     * grazie agli attributi ogni volta che cicla aggiorna la variabile degli UPS 
     * e solo quando dall ultimo check supera il secondo li stampa.
     */
    @Override
    public void run() {
        double timePerUpdate = 1000000000.0 / UPS_SET;
        long lastUpdate = System.nanoTime();
        long lastTimeCheck = System.currentTimeMillis();
        int updates = 0;
        long now;
        while (true) {
            now = System.nanoTime();
            //aggiornamento UPS
            if (now - lastUpdate >= timePerUpdate) {
                switch(whatupdate){
                    case "enemy":
                        updateEnemy();
                        break;
                    case "tower":
                        updateTower();
                        break;
                    case "wave":
                        updateWave();
                        break;
                    case "proj":
                        updateProj();
                        break;
                    case "coin":
                        updateCoin();
                        break;    
                }
                lastUpdate = now;
                updates++;
            }
            if (System.currentTimeMillis() - lastTimeCheck >= 1000) {
                System.out.println("Ugrade " + whatupdate +"/s: "+ updates);
                updates = 0;
                lastTimeCheck = System.currentTimeMillis();
            }
        }
    }
    /**
     * @brief metodo che richiama l'update della schermata nella quale ci
     * troviamo nel gioco.
     *
     * grazie ad uno switch controlla l'attuale gamestates(schermata es:menu,
     * settings, level) e ne richiama l'update
     */
    private void updateEnemy() {
        switch (GameStates.gamestates) {
            case LVL1:
                game.getLevel1().updateEnemy();
                break;
            case LVL2:
                game.getLevel2().updateEnemy();
                break;
            case LVL3:
                game.getLevel3().updateEnemy();
                break;  
            case LVL4:
                game.getLevel4().updateEnemy();
                break;      
        }
    }
    private void updateTower() {
        switch (GameStates.gamestates) {
            case LVL1:
                game.getLevel1().updateTower();
                break;
            case LVL2:
                game.getLevel2().updateTower();
                break;
            case LVL3:
                game.getLevel3().updateTower();
                break;  
            case LVL4:
                game.getLevel4().updateTower();
                break;      
        }
    }
    private void updateWave() {
        switch (GameStates.gamestates) {
            case LVL1:
                game.getLevel1().updateWave();
                break;
            case LVL2:
                game.getLevel2().updateWave();
                break;
            case LVL3:
                game.getLevel3().updateWave();
                break;  
            case LVL4:
                game.getLevel4().updateWave();
                break;      
        }
    }
    private void updateProj() {
        switch (GameStates.gamestates) {
            case LVL1:
                game.getLevel1().updateProj();
                break;
            case LVL2:
                game.getLevel2().updateProj();
                break;
            case LVL3:
                game.getLevel3().updateProj();
                break;  
            case LVL4:
                game.getLevel4().updateProj();
                break;      
        }
    }
    public void updateCoin(){
        switch (GameStates.gamestates) {
            case LVL1:
                game.getLevel1().updateCoin();
                break;
            case LVL2:
                game.getLevel2().updateCoin();
                break;
            case LVL3:
                game.getLevel3().updateCoin();
                break;  
            case LVL4:
                game.getLevel4().updateCoin();
                break;      
        }
    }
}
