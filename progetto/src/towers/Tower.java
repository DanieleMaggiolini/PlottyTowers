package towers;

public class Tower {
    private int x, y, id, typetower;

    public Tower(int x, int y, int id, int typetower) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.typetower = typetower;
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
    
}
