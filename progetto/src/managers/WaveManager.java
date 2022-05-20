/**
 * @author  Daniele Maggiolini
 * @author Mattia Minotti
 * @version 1.0
 * @file Level1.java
 *
 * @brief file per gestire le ondate di nemici.
 *
 */
package managers;

import events.Wave;
import static helpz.Constants.Enemy.*;
import java.util.ArrayList;
import java.util.Arrays;
import progetto.Game;

/**
 * @class WaveManager
 *
 * @brief classe per gestire il numero di ondate, che nemici contengono, ogni
 * quanto generano un nemico e dopo quanto parte un'altra ondata
 *
 */
public class WaveManager {

    //oggeto del game
    private Game game;

    //stringa contenente lo stato(quindi il livello) dal quale la classe è stata invocata
    private String state;

    //contenitore di ondate
    private ArrayList<Wave> waves = new ArrayList<>();

    //ogni quanti millisecondi viene generato un nemico in un'ondata
    private int enemySpawnTickMax = (int) (60 * 2.5);

    //variabile per tenere conto se il tempo è minore del tempo in cui deve essere generato un nemico
    private int enemySpawnTick = enemySpawnTickMax;

    //indici del nemico in un ondata e di quale ondata
    private int indexEnemy, indexWave;

    //tick per contare millisecondi(all'incirca)
    private int tick = 0;

    //tempo trascorso tra un'ondata e un'altra
    private int ticklimit = 5 * 60;

    //booleane che controllano se il timer tra un'ondata e un'altra è partito e finito
    private boolean wavetimer, wavetimerOver;

    /**
     @brief costruttore.
     * assegna il game e lo stato(il livello in cui ci troviamo) e inizializza le ondate
     * 
     * @param game
     * @param state
     */
    public WaveManager(Game game, String state) {
        this.game = game;
        this.state = state;
        initWaves();
    }

    /**
     * @brief aggiornamento variabili tick per generare il successivo nemico
     * dell'ondata e se necessario aggiornamento timer tra due ondate
     */
    public void update() {
        if (enemySpawnTick < enemySpawnTickMax) {
            enemySpawnTick++;
        }
        if (wavetimer) {
            tick++;
            if (tick >= ticklimit) {
                wavetimerOver = true;
            }
        }
    }
    
    /**
     * @brief aumento dell'index e quindi passaggio all'ondata successiva con
     * azzeramento dei tick e timer
     */
    public void increaseWaveIndex() {
        indexWave++;
        tick = 0;
        wavetimerOver = false;
        wavetimer = false;
    }

    /**
     * @brief avviamento del timer tra un'ondata e un'altra
     */
    public void startTimer() {
        wavetimer = true;
    }

    /**
     * @brief richiesta di una variabile per controllare se la pausa tra 2 ondate
     * è finita
     * 
     * @return se la pausa tra ondate è finita
     */    
    public boolean isTimerOver() {
        return wavetimerOver;
    }

    /**
     * @brief richiesta di una variabile per controllare se il timer per la pausa
     * tra 2 ondate è partito
     * 
     * @return se il timer per la pausa tra 2 ondate è partito
     */    
    public boolean isTimerStart() {
        return wavetimer;
    }
    
    /**
     * @brief per ogni livello aggiunta all'array delle ondate 3 ondate con nemici
     * specifici per ogni livello
     */   
    private void initWaves() {
        switch (state) {
            case "level1":
                waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(OROCHIMARU, OROCHIMARU, OROCHIMARU, OROCHIMARU, OROCHIMARU, OROCHIMARU, OROCHIMARU, OROCHIMARU, OROCHIMARU, OROCHIMARU, TOBI))));
                waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(OROCHIMARU, TOBI, TOBI, TOBI, TOBI, OROCHIMARU, OROCHIMARU, TOBI, TOBI, TOBI, MADARA))));
                waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(MADARA, MADARA, MADARA, TOBI, OROCHIMARU, TOBI, MADARA, MADARA, MADARA, MADARA, MADARA, TOBI))));
                break;
            case "level2":
                waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(POLLO, POLLO, POLLO, POLLO, POLLO, POLLO, MAIALE, POLLO, POLLO, POLLO, MAIALE, POLLO, POLLO))));
                waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(MAIALE, MAIALE, MAIALE, POLLO, POLLO, GREG, MAIALE, MAIALE, MAIALE, POLLO, GREG, GREG))));
                waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(MAIALE, MAIALE, GREG, GREG, GREG, GREG, GREG, MAIALE, GREG, GREG, GREG, GREG, MAIALE))));
                break;
            case "level3":
                waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(JERRY, JERRY, JERRY, JERRY, SUMMER, JERRY, JERRY, SUMMER, SUMMER, SUMMER, JERRY, JERRY, SUMMER))));
                waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(SUMMER, SUMMER, SUMMER, MORTY, MORTY, MORTY, SUMMER, SUMMER, JERRY, MORTY, SUMMER, MORTY))));
                waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(MORTY, MORTY, MORTY, RICK, MORTY, SUMMER, RICK, RICK, RICK, MORTY, MORTY, RICK, RICK))));
                break;
            case "level4":
                waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(LUFFY, LUFFY, LUFFY, LUFFY, LUFFY, LUFFY, LUFFY, JINBE, LUFFY, LUFFY, LUFFY, JINBE, JINBE))));
                waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(LUFFY, JINBE, JINBE, JINBE, LUFFY, JINBE, JINBE, JINBE, JINBE, LUFFY, LUFFY, JINBE))));
                waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(JINBE, JINBE, JINBE, JINBE, BARBABIANCA, LUFFY, BARBABIANCA, JINBE, BARBABIANCA, JINBE, BARBABIANCA, JINBE, BARBABIANCA))));
                break;
        }
    }
    
    /**
     * @brief richiesta della variabile contenente tutte le ondate
     * 
     * @return array di waves
     */ 
    public ArrayList<Wave> getWaves() {
        return waves;
    }

    /**
     * @brief richiesta del nemico successivo dell'ondata
     * 
     * @return enemyType del nemico successivo
     */ 
    public int getNextEnemy() {
        enemySpawnTick = 0;
        return waves.get(indexWave).getEnemiesWave().get(indexEnemy++);
    }

    /**
     * @brief richiesta dello stato del timer per la generazione di un nuovo nemico
     * 
     * @return se è il momento di generare un nuovo nemico
     */
    public boolean isEnemySpawn() {
        return enemySpawnTick >= enemySpawnTickMax;
    }

    /**
     * @brief richiesta se l'ondata è finito o ci sono altri nemici
     * 
     * @return se non ci sono altri nemici nell'ondata
     */
    public boolean isWaveEnd() {
        return indexEnemy >= waves.get(indexWave).getEnemiesWave().size();
    }

    /**
     * @brief richiesta se ci sono altre ondate di nemici
     * 
     * @return presenza di altre ondate
     */
    public boolean isMoreWaves() {
        return indexWave + 1 < waves.size();
    }
    
    /**
     * @brief azzeramento index dei nemici in un'ondata
     */
    public void resetIndex() {
        indexEnemy = 0;
    }
    
    /**
     * @brief richiesta del numero di ondata in cui siamo
     * 
     * @return index dell'ondata
     */
    public int getWaveIndex() {
        return indexWave;
    }

    /**
     * @brief tempo rimanente all'inizio dell'ondata successiva
     * 
     * @return tempo rimasto alla prossima ondata
     */
    public float getTimeLeft() {
        float tickleft = ticklimit - tick;
        return tickleft / 60.0f;
    }
}
