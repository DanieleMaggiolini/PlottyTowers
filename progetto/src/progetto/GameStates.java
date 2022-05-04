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
    PLAYING, MENU, SETTINGS, EDIT, LVL1;
    
    //oggetto gi questa classe inizializzato con il menu
    public static GameStates gamestates = MENU;
    
    /**
     @brief costruttore setta il gamestates in base al parametro passato.
     * 
     *@param state schermata da settare
     */
    public static void setGameState(GameStates state){
        gamestates=state;
    }
}
