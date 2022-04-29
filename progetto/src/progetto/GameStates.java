package progetto;

public enum GameStates {
    PLAYING, MENU, SETTINGS, LVL1;
    
    public static GameStates gamestates = MENU;
    
    public static void setGameState(GameStates state){
        gamestates=state;
    }
}
