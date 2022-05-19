
package progetto;

public class ThreadUpdate extends Thread {

    private Thread gameUpdate;
    private Game game;

    private final double UPS_SET = 60.0;

    public ThreadUpdate(Game game) {
        this.game = game;
        gameUpdate = new Thread(this) {

        };
        gameUpdate.start();
    }
    
    /**
     * @brief loop del gioco che gestisce UPS.
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
                updateGame();
                lastUpdate = now;
                updates++;
            }
            if (System.currentTimeMillis() - lastTimeCheck >= 1000) {
                System.out.println("UPS: " + updates);
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
    private void updateGame() {
        switch (GameStates.gamestates) {
            case MENU:
                game.getMenu().updates();
                break;
            case PLAYING:
                game.getPlaying().updates();
                break;
            case SETTINGS:
                game.getSetting().updates();
                break;
            case LVL1:
                game.getLevel1().updates();
                break;
            case LVL2:
                game.getLevel2().updates();
                break;
            case LVL3:
                game.getLevel3().updates();
                break;  
            case LVL4:
                game.getLevel4().updates();
                break;      
        }
    }

}
