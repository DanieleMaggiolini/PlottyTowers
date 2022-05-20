/**
* @author  Daniele Maggiolini
* @author  Mattia Minotti
* @version 0.0
* @file GameStates.java 
* 
* @brief file d'enumerazione a scopo di semplificare la modifica della schermata
* visualizzata
*
*/
package progetto;

public enum GameStates {
    PLAYING, MENU, EDIT, LVL1, LVL2, LVL3, LVL4;
    
    //oggetto contenente le musiche dei livelli
    public static Sound s = new Sound(-1);
    
    //oggetto gi questa classe inizializzato con il menu
    public static GameStates gamestates = MENU;
    
    /**
     @brief costruttore setta il gamestates in base al parametro passato
     * e nel caso vi sia riproduce la musica relativa alla schermata.
     * 
     *@param state schermata da settare
     */
    public static void setGameState(GameStates state){
        gamestates=state;
        if(s.clip!=null)
            s.stop();
        switch(state){
            case LVL1:
                s.playMusic(0);
                break;
            case LVL2:
                s.playMusic(1);
                break;
            case LVL3:
                s.playMusic(2);
                break;
            case LVL4:
                s.playMusic(3);
                break;
        }
    }
}
