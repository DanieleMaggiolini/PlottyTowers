package helpz;


public class Constants {
    public static class Direction{
        public static final int LEFT = 0;
        public static final int UP = 1;
        public static final int RIGHT = 2;
        public static final int DOWN = 3;
    }
    public static class Tiles{
        public static final int WATER_TILE=0;
        public static final int GRASS_TILE=1;
        public static final int ROAD_TILE=2;
    }
    public static class Enemy{
        public static final int OROCHIMARU=0;
        public static final int TOBI=1;
        public static final int MADARA=2;
        
        public static float getSpeed(int enemytype){
            switch(enemytype){
                case OROCHIMARU:
                    return 1.3f;
                case TOBI:
                    return 1.4f;
                case MADARA:
                    return 1.5f;
            }
            return 0;
        }
    }
    public static class Towers{
        public static final int NARUTO=0;
        public static final int SASUKE=1;
        public static final int SAKURA=2;
    }
}
