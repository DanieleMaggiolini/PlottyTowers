package towers;

import static helpz.Constants.Towers.*;

public class Tower {
    private int x, y, id, typetower, damage;
    private float range, cooldown;
    private int cooldownTick=0;
    private int lvl;
    public Tower(int x, int y, int id, int typetower) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.typetower = typetower;
        lvl=1;
        setDamage();
        setRange();
        setCooldown();
    }
    public void update(){
        cooldownTick++;
    }
    public void levelUp(){
        this.lvl++;
        switch(typetower){
            case NARUTO:
                damage+=15;
                range+=20;
                break;
            case SASUKE:
                damage+=20;
                range+=30;
                break;
            case SAKURA:
                damage+=30;
                range+=25;
                break;
        }
    }
    public int getLvl(){
        return lvl;
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getId() {
        return id;
    }

    public int getTypetower() {
        return typetower;
    }

    private void setDamage() {
        damage=helpz.Constants.Towers.getDamage(typetower);
    }
    private void setRange(){
        range=helpz.Constants.Towers.getRange(typetower);
    }
    private void setCooldown(){
        cooldown=helpz.Constants.Towers.getCooldown(typetower);
    }

    public int getDamage() {
        return damage;
    }

    public float getRange() {
        return range;
    }

    public float getCooldown() {
        return cooldown;
    }
    public boolean isCooldownOver(){
        return cooldownTick > cooldown;
    }
    public void resetCooldown(){
        cooldownTick=0;
    }
}
