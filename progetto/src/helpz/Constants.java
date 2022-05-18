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
        public static int getStartHp(int enemytype){
            switch(enemytype){
                case OROCHIMARU:
                    return 200;
                case TOBI:
                    return 350;
                case MADARA:
                    return 450;
            }
            return 0;
        } 
        public static int getCoin(int enemytype){
            switch(enemytype){
                case OROCHIMARU:
                    return 40;
                case TOBI:
                    return 70;
                case MADARA:
                    return 110;
            }
            return 0;
        } 
    }
    public static class Towers{
        public static final int NARUTO=0;
        public static final int SASUKE=1;
        public static final int SAKURA=2;
        
        public static String getName(int towerType){
            switch(towerType){
                case NARUTO:
                    return "Naruto";
                case SASUKE:
                    return "Sasuke";
                case SAKURA:
                    return "Sakura";
            }
            return"";
        }
        public static int getDamage(int towerType){
            switch(towerType){
                case NARUTO:
                    return 300;
                case SASUKE:
                    return 400;
                case SAKURA:
                    return 800;
            }
            return 0;
        }
        public static float getRange(int towerType){
            switch(towerType){
                case NARUTO:
                    return 300;
                case SASUKE:
                    return 230;
                case SAKURA:
                    return 140;
            }
            return 0;
        }
        public static float getCooldown(int towerType){
            switch(towerType){
                case NARUTO:
                    return 10;
                case SASUKE:
                    return 10;
                case SAKURA:
                    return 10;
            }
            return 0;
        }
        public static float getCost(int towerType){
            switch(towerType){
                case NARUTO:
                    return 30;
                case SASUKE:
                    return 40;
                case SAKURA:
                    return 50;
            }
            return 0;
        }
    }
    public static class Projectiles{
        public static final int SHURIKEN=0;
        public static final int FIREBALL=1;
        public static final int PUNCH=2;
        public static float getSpeed(int projectiletype){
            switch(projectiletype){
                case SHURIKEN:
                    return 15f;
                case FIREBALL:
                    return 7f;
                case PUNCH:
                    return 4f;
            }
            return 0f;
        }
    }
 }
