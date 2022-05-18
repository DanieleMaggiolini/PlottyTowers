package managers;

import events.Wave;
import static helpz.Constants.Enemy.*;
import java.util.ArrayList;
import java.util.Arrays;
import progetto.Game;

public class WaveManager {

    private Game game;
    private String state;
    private ArrayList<Wave> waves = new ArrayList<>();
    //
    private int enemySpawnTickMax = 60 * 1;
    private int enemySpawnTick = enemySpawnTickMax;
    private int indexEnemy, indexWave;
    private int tick = 0;
    private int ticklimit = 5 * 60;
    private boolean wavetimer, wavetimerOver;

    public WaveManager(Game game, String state) {
        this.game = game;
        this.state = state;
        initWaves();
    }

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

    public void increaseWaveIndex() {
        indexWave++;
        tick = 0;
        wavetimerOver = false;
        wavetimer = false;
    }

    public void startTimer() {
        wavetimer = true;
    }

    public boolean isTimerOver() {
        return wavetimerOver;
    }

    public boolean isTimerStart() {
        return wavetimer;
    }

    private void initWaves() {
        switch (state) {
            case "level1":
                waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(OROCHIMARU, OROCHIMARU, OROCHIMARU, OROCHIMARU, OROCHIMARU, OROCHIMARU, OROCHIMARU, OROCHIMARU, OROCHIMARU, OROCHIMARU, TOBI))));
                waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(OROCHIMARU, TOBI, TOBI, TOBI, TOBI, OROCHIMARU, OROCHIMARU, TOBI, TOBI, TOBI, MADARA))));
                waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(MADARA, MADARA, MADARA, TOBI, OROCHIMARU, TOBI, MADARA, MADARA, MADARA, MADARA, MADARA, TOBI))));
                break;
            case "level2":
                waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(POLLO, POLLO, GREG, POLLO, POLLO, POLLO, MAIALE, POLLO, POLLO, POLLO, MAIALE, POLLO, POLLO))));
                waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(MAIALE, MAIALE, MAIALE, POLLO, POLLO, GREG, MAIALE, MAIALE, MAIALE, POLLO, GREG, GREG))));
                waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(MAIALE, MAIALE, GREG, GREG, GREG, GREG, GREG, MAIALE, GREG, GREG, GREG, GREG, MAIALE))));
                break;
        }

    }

    public ArrayList<Wave> getWaves() {
        return waves;
    }

    public int getNextEnemy() {
        enemySpawnTick = 0;
        return waves.get(indexWave).getEnemiesWave().get(indexEnemy++);
    }

    public boolean isEnemySpawn() {
        return enemySpawnTick >= enemySpawnTickMax;
    }

    public boolean isWaveEnd() {
        return indexEnemy >= waves.get(indexWave).getEnemiesWave().size();
    }

    public boolean isMoreWaves() {
        return indexWave + 1 < waves.size();
    }

    public void resetIndex() {
        indexEnemy = 0;
    }

    public int getWaveIndex() {
        return indexWave;
    }

    public float getTimeLeft() {
        float tickleft = ticklimit - tick;
        return tickleft / 60.0f;
    }
}
