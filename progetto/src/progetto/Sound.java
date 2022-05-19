/**
* @author  Daniele Maggiolini
* @author  Mattia Minotti
* @version 0.0
* @file Sound.java 
* 
* @brief gestisce tutti i suoni.
*
*/
package progetto;

import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/** 
* @class Game
* 
* @brief classe per la gestione completa dei suoni livello per livello
* 
*/ 
public class Sound {
    
    //oggetto che conterra la traccia audio
    Clip clip;
    
    //array di percorsi relativi alle tracce audio
    URL soundURL[] = new URL[30];
    
    /**
     @brief costruttore popola l'array dei suoni relativi al corretto state.

     @param n numero relativo allo state
     */
    public Sound(int n){
        switch(n){
            case -1://themes
                soundURL[0] = getClass().getResource("/audio/lvl1theme.wav");
                break;
            case 1://suoni per il lvl 1
                soundURL[0] = getClass().getResource("/audio/pop.wav");
                soundURL[1] = getClass().getResource("/audio/death.wav");
                break;
        }
    }
    
    /**
     @brief imposta la clip con la corretta traccia audio.

     @param i indice dell'array per la selezione della cella corretta
     */
    public void setFIle(int i){
        try{
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        }catch(Exception e){
            
        }
    }
    
    /**
     @brief richiama i metodi per riprodurre in loop una traccia audio.

     @param i indice dell'array per la selezione della cella corretta
     */
    public void playMusic(int i){
        setFIle(i);
        play();
        loop();
    }
    
    /**
     @brief richiama i metodi per riprodurre una traccia audio.

     @param i indice dell'array per la selezione della cella corretta
     */
    public void playSE(int i){
        setFIle(i);
        play();
    }
    
    /**
     @brief inizia la riproduzione di un suono.
     */
    public void play(){
        clip.start();
    }
    
    /**
     @brief rende ciclica la riproduzione del suono.
     */
    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    
    /**
     @brief termina la riproduzione di un suono.
     */
    public void stop(){
        clip.stop();
    }
    
}
