package helpz;

public class Constants {

    public static class Direction {

        public static final int LEFT = 0;
        public static final int UP = 1;
        public static final int RIGHT = 2;
        public static final int DOWN = 3;
    }

    public static class Tiles {

        public static final int WATER_TILE = 0;
        public static final int GRASS_TILE = 1;
        public static final int ROAD_TILE = 2;
    }

    public static class Enemy {

        public static final int OROCHIMARU = 0;
        public static final int TOBI = 1;
        public static final int MADARA = 2;
        public static final int POLLO = 3;
        public static final int MAIALE = 4;
        public static final int GREG = 5;
        public static final int JERRY = 6;
        public static final int SUMMER = 7;
        public static final int MORTY = 8;
        public static final int RICK = 9;
        public static final int LUFFY = 10;
        public static final int JINBE = 11;
        public static final int BARBABIANCA = 12;

        public static float getSpeed(int enemytype) {
            switch (enemytype) {
                case OROCHIMARU:
                    return 1.3f;
                case TOBI:
                    return 1.4f;
                case MADARA:
                    return 1.5f;
                case POLLO:
                    return 1.5f;
                case MAIALE:
                    return 1.5f;
                case GREG:
                    return 1.5f;
                case JERRY:
                    return 1.5f;
                case SUMMER:
                    return 1.5f;
                case MORTY:
                    return 1.5f;
                case RICK:
                    return 1.5f;
                case LUFFY:
                    return 1.5f;
                case JINBE:
                    return 1.5f;
                case BARBABIANCA:
                    return 1.5f;      
            }
            return 0;
        }

        public static int getStartHp(int enemytype) {
            switch (enemytype) {
                case OROCHIMARU:
                    return 200;
                case TOBI:
                    return 350;
                case MADARA:
                    return 450;
                case POLLO:
                    return 450;
                case MAIALE:
                    return 450;
                case GREG:
                    return 450;
                case JERRY:
                    return 200;
                case SUMMER:
                    return 200;
                case MORTY:
                    return 200;
                case RICK:
                    return 200;
                case LUFFY:
                    return 200;
                case JINBE:
                    return 350;
                case BARBABIANCA:
                    return 800;               
            }
            return 0;
        }

        public static int getCoin(int enemytype) {
            switch (enemytype) {
                case OROCHIMARU:
                    return 40;
                case TOBI:
                    return 70;
                case MADARA:
                    return 110;
                case POLLO:
                    return 40;
                case MAIALE:
                    return 60;
                case GREG:
                    return 200;
                case JERRY:
                    return 40;
                case SUMMER:
                    return 50;
                case MORTY:
                    return 60;
                case RICK:
                    return 100;
                case LUFFY:
                    return 40;
                case JINBE:
                    return 55;
                case BARBABIANCA:
                    return 150;     
            }
            return 0;
        }
    }

    public static class Towers {

        public static final int NARUTO = 0;
        public static final int SASUKE = 1;
        public static final int SAKURA = 2;
        public static final int CANNONE = 3;
        public static final int TESLA = 4;
        public static final int ARCOX = 5;
        public static final int MOSQUITOS = 6;
        public static final int DOG = 7;
        public static final int HEAD = 8;
        public static final int KIZARU = 9;
        public static final int AOKIJI = 10;
        public static final int AKAINU = 11;

        public static String getName(int towerType) {
            switch (towerType) {
                case NARUTO:
                    return "Naruto";
                case SASUKE:
                    return "Sasuke";
                case SAKURA:
                    return "Sakura";
                case CANNONE:
                    return "Cannone";
                case TESLA:
                    return "Tesla";
                case ARCOX:
                    return "Arcox";
                case MOSQUITOS:
                    return "Guardia Spaziale";
                case DOG:
                    return "Dog";
                case HEAD:
                    return "Big Head";
                case KIZARU:
                    return "Kizaru";
                case AOKIJI:
                    return "Aokiji";
                case AKAINU:
                    return "Akainu";     
            }
            return "";
        }

        public static int getDamage(int towerType) {
            switch (towerType) {
                case NARUTO:
                    return 300;
                case SASUKE:
                    return 400;
                case SAKURA:
                    return 800;
                case CANNONE:
                    return 250;
                case TESLA:
                    return 400;
                case ARCOX:
                    return 100;
                case MOSQUITOS:
                    return 150;
                case DOG:
                    return 200;
                case HEAD:
                    return 400;
                case KIZARU:
                    return 150;
                case AOKIJI:
                    return 200;
                case AKAINU:
                    return 250;      
            }
            return 0;
        }

        public static float getRange(int towerType) {
            switch (towerType) {
                case NARUTO:
                    return 300;
                case SASUKE:
                    return 230;
                case SAKURA:
                    return 140;
                case CANNONE:
                    return 140;
                case TESLA:
                    return 140;
                case ARCOX:
                    return 540;
                case MOSQUITOS:
                    return 300;
                case DOG:
                    return 230;
                case HEAD:
                    return 140;
                case KIZARU:
                    return 350;
                case AOKIJI:
                    return 200;
                case AKAINU:
                    return 150;     
            }
            return 0;
        }

        public static float getCooldown(int towerType) {
            switch (towerType) {
                case NARUTO:
                    return 10;
                case SASUKE:
                    return 10;
                case SAKURA:
                    return 10;
                case CANNONE:
                    return 50;
                case TESLA:
                    return 70;
                case ARCOX:
                    return 8; 
                case MOSQUITOS:
                    return 50;
                case DOG:
                    return 70;
                case HEAD:
                    return 140;
                case KIZARU:
                    return 10;
                case AOKIJI:
                    return 10;
                case AKAINU:
                    return 7; 
            }
            return 0;
        }

        public static float getCost(int towerType) {
            switch (towerType) {
                case NARUTO:
                    return 30;
                case SASUKE:
                    return 40;
                case SAKURA:
                    return 50;
                case CANNONE:
                    return 30;
                case TESLA:
                    return 40;
                case ARCOX:
                    return 70; 
                case MOSQUITOS:
                    return 35;
                case DOG:
                    return 45;
                case HEAD:
                    return 75;
                case KIZARU:
                    return 45;
                case AOKIJI:
                    return 50;
                case AKAINU:
                    return 55;    
            }
            return 0;
        }
    }

    public static class Projectiles {

        public static final int SHURIKEN = 0;
        public static final int FIREBALL = 1;
        public static final int PUNCH = 2;
        public static final int CANNONATA = 3;
        public static final int SCOSSA = 4;
        public static final int FRECCIA = 5;
        public static final int LASER = 6;
        public static final int RAGGIO = 7;
        public static final int CRATERE = 8;
        public static final int LIGHT = 9;
        public static final int ICE = 10;
        public static final int MAGMA = 11;

        public static float getSpeed(int projectiletype) {
            switch (projectiletype) {
                case SHURIKEN:
                    return 15f;
                case FIREBALL:
                    return 7f;
                case PUNCH:
                    return 5.5f;
                case CANNONATA:
                    return 10f;
                case SCOSSA:
                    return 12f;
                case FRECCIA:
                    return 10f;    
                case LASER:
                    return 18f;
                case RAGGIO:
                    return 7f;
                case CRATERE:
                    return 4f;     
                case LIGHT:
                    return 20f;
                case ICE:
                    return 7f;
                case MAGMA:
                    return 5.5f;    
            }
            return 0f;
        }
    }
}
