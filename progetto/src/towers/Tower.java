package towers;

public class Tower {
    private int x, y, id, typetower;
    private float damage, range, cooldown;
    public Tower(int x, int y, int id, int typetower) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.typetower = typetower;
        setDamage();
        setRange();
        setCooldown();
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

    public float getDamage() {
        return damage;
    }

    public float getRange() {
        return range;
    }

    public float getCooldown() {
        return cooldown;
    }
    
}
