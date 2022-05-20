/**
 * @author  Daniele Maggiolini
 * @author Mattia Minotti
 * @version 0.0
 * @file Level1.java
 *
 * @brief file per gestire i proiettili.
 *
 */
package managers;

import enemies.Enemy;
import static helpz.Constants.Projectiles.*;
import static helpz.Constants.Towers.*;
import helpz.LoadSave;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashSet;
import objects.Projectile;
import objects.Tile;
import progetto.Game;
import towers.AttackAnimation;
import towers.Tower;

/**
 * @class ProjectileManager
 *
 * @brief classe per gestire il movimento, l'inclinazione, la posizione dei
 * proiettili interazioni dei proiettili coi nemici
 *
 */
public class ProjectileManager {

    //oggeto del game
    private Game game;

    //stringa contenente lo stato(quindi il livello) dal quale la classe è stata invocata
    private String state;

    //contenitore di tutti i proiettili presenti
    private ArrayList<Projectile> projectiles = new ArrayList<>();

    //contenitore di tutte le immagini dei proiettili con animazioni
    private BufferedImage[][] proj_attack;

    //id dei proiettili
    private int projId = 0;

    //oggetto per utilizzo del synchronized
    private Object a = new Object();

    /**
     * @brief costruttore. assegna il game e lo stato(il livello in cui ci
     * troviamo) e carica le immagini dei proiettili
     *
     * @param game
     * @param state
     */
    public ProjectileManager(Game game, String state) {
        this.game = game;
        this.state = state;
        importImgs();
    }

    /**
     * @brief assegnegnazioni delle immagini dei proiettili con animazioni,
     * diverse per ogni livello
     */
    private void importImgs() {
        BufferedImage atlas;
        switch (state) {
            case "level1":
                proj_attack = new BufferedImage[3][10];
                atlas = LoadSave.getImage(LoadSave.NARUTO_ATTACK);

                for (int i = 0; i < 4; i++) {
                    proj_attack[0][i] = atlas.getSubimage(64 * i, 0, 64, 64);
                }
                for (int i = 0; i < 3; i++) {
                    proj_attack[1][i] = atlas.getSubimage(64 * i, 64, 64, 64);
                }
                for (int i = 0; i < 10; i++) {
                    proj_attack[2][i] = atlas.getSubimage(0, 128 + 64 * i, 64 * 4, 64);
                }
                break;
            case "level2":
                proj_attack = new BufferedImage[1][3];
                atlas = LoadSave.getImage(LoadSave.COC_ATTACK);
                for (int i = 0; i < 3; i++) {
                    proj_attack[0][i] = atlas.getSubimage(i * 64, 0, 64, 64);
                }
                break;
            case "level3":
                proj_attack = new BufferedImage[3][10];
                atlas = LoadSave.getImage(LoadSave.RICKEMORTY_ATTACK);

                proj_attack[0][0] = atlas.getSubimage(0, 0, 64, 64);
                proj_attack[1][0] = atlas.getSubimage(0, 64, 64, 64);
                for (int i = 0; i < 10; i++) {
                    proj_attack[2][i] = atlas.getSubimage(0, 128 + 64 * i, 64 * 4, 64);
                }
                break;
            case "level4":
                proj_attack = new BufferedImage[1][3];
                atlas = LoadSave.getImage(LoadSave.ONEPIECE_ATTACK);

                proj_attack[0][0] = atlas.getSubimage(0, 0, 64, 64);
                proj_attack[0][1] = atlas.getSubimage(64, 0, 64, 64);
                proj_attack[0][2] = atlas.getSubimage(128, 0, 128, 128);
                break;
        }
    }

    /**
     * @brief aggiunta di un proiettile nell'array
     *
     * @param t torre specifica
     * @param e nemico specifico
     */
    public void newProjectile(Tower t, Enemy e) {
        int type = getProjectileType(t);

        int distx = (int) (t.getX() - e.getX());
        int disty = (int) (t.getY() - e.getY());
        int totaldistance = Math.abs(distx) + Math.abs(disty);

        float xper = (float) Math.abs(distx) / totaldistance;
        float yper = 1.0f - xper;
        float speedx = xper * helpz.Constants.Projectiles.getSpeed(type);
        float speedy = helpz.Constants.Projectiles.getSpeed(type) - speedx;

        if (t.getX() > e.getX()) {
            speedx *= -1;
        }
        if (t.getY() > e.getY()) {
            speedy *= -1;
        }

        float rotate = 0;

        if (t.getTypetower() != SAKURA && t.getTypetower() != AKAINU && t.getTypetower() != HEAD) {
            float arc = (float) Math.atan(disty / (float) distx);
            rotate = (float) Math.toDegrees(arc);
            if (distx < 0) {
                rotate += 180;
            }
        }
        synchronized (a) {
            projectiles.add(new Projectile(t.getX() + Tile.spriteWidth / 2, t.getY() + Tile.spriteHeight / 2, speedx, speedy, t.getDamage(), rotate, projId++, type));
        }

    }

    /**
     * @brief per ogni proiettile controllo se attivo, lo muove e controllo se 
     * ha colpito qualche nemico
     */
    public void update() {
        if (projectiles != null) {
            synchronized (a) {
                for (Projectile p : projectiles) {
                    if (p.isExist()) {
                        p.move();
                        if (isProjHit(p)) {
                            p.setExist(false);
                        } else if (isOutBounds(p)) {
                            p.setExist(false);
                        }
                    }
                }
            }
        }
    }

    /**
     * @brief controllo se il proiettile è uscito della schermata
     * 
     * @return se il proiettile è uscito
     */
    public boolean isOutBounds(Projectile p) {
        if (p.getPosition().x >= 0) {
            if (p.getPosition().x <= Game.currentScreenWidth) {
                if (p.getPosition().y >= 0) {
                    if (p.getPosition().y <= Game.currentScreenHeight) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * @brief per ogni proiettile controllo se attivo, se attivo disegno del proiettile e 
     * eventualmente la sua inclinazione
     *
     * @param g parte grafica
     *
     */
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        if (projectiles != null) {
            for (Projectile p : projectiles) {
                if (p.isExist()) {
                    if (p.getProjectiletype() != PUNCH && p.getProjectiletype() != MAGMA && p.getProjectiletype() != CRATERE) {
                        g2d.translate(p.getPosition().x, p.getPosition().y);
                        g2d.rotate(Math.toRadians(p.getRotation()));
                        switch (state) {
                            case "level1":
                                g2d.drawImage(proj_attack[p.getProjectiletype()][p.getIndex()], -Tile.spriteWidth / 2, -Tile.spriteHeight / 2, null);
                                break;
                            case "level2":
                                g2d.drawImage(proj_attack[0][p.getProjectiletype() - 3], -Tile.spriteWidth / 2, -Tile.spriteHeight / 2, null);
                                break;
                            case "level3":
                                g2d.drawImage(proj_attack[p.getProjectiletype() - 6][0], -Tile.spriteWidth / 2, -Tile.spriteHeight / 2, null);
                                break;
                            case "level4":
                                g2d.drawImage(proj_attack[0][p.getProjectiletype() - 9], -Tile.spriteWidth / 2, -Tile.spriteHeight / 2, null);
                                break;
                        }
                        g2d.rotate(-Math.toRadians(p.getRotation()));
                        g2d.translate(-p.getPosition().x, -p.getPosition().y);
                    } else {
                        g2d.translate(p.getPosition().x, p.getPosition().y);
                        switch (state) {
                            case "level1":
                                g2d.drawImage(proj_attack[p.getProjectiletype()][p.getIndex()], -2 * Tile.spriteWidth, -Tile.spriteHeight / 2, null);
                                break;
                            case "level3":
                                g2d.drawImage(proj_attack[2][p.getIndex()], -2 * Tile.spriteWidth, -Tile.spriteHeight / 2, null);
                                break;
                            case "level4":
                                g2d.drawImage(proj_attack[0][2], -Tile.spriteWidth, (int) (-Tile.spriteHeight * 1.5), null);
                                break;
                        }
                        g2d.translate(-p.getPosition().x, -p.getPosition().y);
                    }
                }
            }
        }
    }
    
    /**
     * @brief controolo per il proiettile passato se si trova nella hitbox di uno dei
     * nemici presenti nel gioco, se presenti disattivarlo e recare danno al nemico
     *
     * @param p proiettile controllato
     *
     * @return se il proiettile ha colpito un nemico
     */
    public boolean isProjHit(Projectile p) {
        switch (state) {
            case "level1":
                if (game.getLevel1().getEnemyManager().getEnemies() != null) {
                    for (Enemy e : game.getLevel1().getEnemyManager().getEnemies()) {
                        if (e.isAlive()) {
                            if (e.getBounds().contains(p.getPosition())) {
                                e.damage(p.getDmg());
                                return true;
                            }
                        }
                    }
                }
                break;
            case "level2":
                if (game.getLevel2().getEnemyManager().getEnemies() != null) {
                    for (Enemy e : game.getLevel2().getEnemyManager().getEnemies()) {
                        if (e.isAlive()) {
                            if (e.getBounds().contains(p.getPosition())) {
                                e.damage(p.getDmg());
                                return true;
                            }
                        }
                    }
                }
                break;
            case "level3":
                if (game.getLevel3().getEnemyManager().getEnemies() != null) {
                    for (Enemy e : game.getLevel3().getEnemyManager().getEnemies()) {
                        if (e.isAlive()) {
                            if (e.getBounds().contains(p.getPosition())) {
                                e.damage(p.getDmg());
                                return true;
                            }
                        }
                    }
                }
                break;
            case "level4":
                if (game.getLevel4().getEnemyManager().getEnemies() != null) {
                    for (Enemy e : game.getLevel4().getEnemyManager().getEnemies()) {
                        if (e.isAlive()) {
                            if (e.getBounds().contains(p.getPosition())) {
                                e.damage(p.getDmg());
                                return true;
                            }
                        }
                    }
                }
                break;
        }
        return false;
    }
    
    /**
     * @brief per la torre passata restituito il suo tipo di proiettile utilizzato
     *
     * @param t torre specifica
     *
     * @return tipologia di proiettile corrispondente alla torre
     */
    public int getProjectileType(Tower t) {
        switch (t.getTypetower()) {
            case NARUTO:
                return SHURIKEN;
            case SASUKE:
                return FIREBALL;
            case SAKURA:
                return PUNCH;
            case CANNONE:
                return CANNONATA;
            case TESLA:
                return SCOSSA;
            case ARCOX:
                return FRECCIA;
            case MOSQUITOS:
                return LASER;
            case DOG:
                return RAGGIO;
            case HEAD:
                return CRATERE;
            case KIZARU:
                return LIGHT;
            case AOKIJI:
                return ICE;
            case AKAINU:
                return MAGMA;
        }
        return 0;
    }
}
