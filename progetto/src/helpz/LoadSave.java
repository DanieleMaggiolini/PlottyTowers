package helpz;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class LoadSave {
    public static final String BACKGROUND_MENU = "image/background/backgroundMenu.png";
    public static final String PAUSE_BACKGROUND = "image/other/pauseMenu.png";
    public static final String SPRITE_ATLAS= "image/background/atlas.png";
    public static final String MENU_BUTTONS = "image/other/menuButton.png";
    public static final String LEVEL_MENU = "image/background/levelmenu.png";
    public static final String LEVEL_BUTTON = "image/background/lvlbutton.png";
    
    public static final String SUSANO = "image/tower/susano.png";
    //tower
    public static final String NARUTO_TOWER = "image/tower/naruto_tower.png";
    public static final String COC_TOWER = "image/tower/coc_tower.png";
    public static final String RICKEMORTY_TOWER = "image/tower/rickemorty_tower.png";
    public static final String ONEPIECE_TOWER = "image/tower/onepiece_tower.png";
    //enemy
    public static final String NARUTO_ENEMY = "image/enemy/naruto_enemy.png";
    public static final String COC_ENEMY = "image/enemy/coc_enemy.png";
    public static final String RICKEMORTY_ENEMY = "image/enemy/rickemorty_enemy.png";
    public static final String ONEPIECE_ENEMY = "image/enemy/onepiece_enemy.png";
    //attack
    public static final String NARUTO_ATTACK = "image/tower/naruto_attack.png";
    public static final String ONEPIECE_ATTACK = "image/tower/onepiece_attack.png";
    //altro
    public static final String CUORI = "image/other/cuori.png";
    public static final String INGRANAGGIO = "image/other/ingranaggio.png";
    
    //LOAD IMAGE     
    public static BufferedImage getImage(String name){
        BufferedImage img = null;
        InputStream is = helpz.LoadSave.class.getResourceAsStream("/" + name);
        try {
            img=ImageIO.read(is);
        } catch (IOException ex) {
            Logger.getLogger(helpz.LoadSave.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                is.close();
            } catch (IOException ex) {
                Logger.getLogger(helpz.LoadSave.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return img; 
    }
    //create new array with default value
    public static void createLevel(String name, int[] id){
        File newLevel= new File("src/image/background/" + name + ".txt");
        
        if(newLevel.exists()){
            return;
        }
        try {
            newLevel.createNewFile();
        } catch (IOException ex) {
            Logger.getLogger(LoadSave.class.getName()).log(Level.SEVERE, null, ex);
        }
        writeToFile(newLevel, id);
    }
    //WRITE on file
    private static void writeToFile(File f, int[] id){
        try {
            PrintWriter printwriter =new PrintWriter(f);
            for (Integer i: id) {
                printwriter.println(i);
            }
            printwriter.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LoadSave.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //READ FILE
    private static ArrayList<Integer> readFile(File f){
        ArrayList<Integer> list= new ArrayList<>();
        try {
            Scanner s = new Scanner(f);
            while(s.hasNextLine()){
                list.add(Integer.parseInt(s.nextLine()));
            }
            s.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LoadSave.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    //load 2d int array from a file
    public static int[][] loadLevel(String name){
        File levelFile = new File("src/image/background/" + name + ".txt");
            
        if(levelFile.exists()){
            ArrayList<Integer> list= readFile(levelFile);
            return Utilz.ArrayListTo2dInt(list, 14, 30);
        }      
        return null; 
    }
    //save 2d int array to a file
    public static void saveLevel(String name, int[][] id){
        File levelFile = new File("src/image/background/" + name + ".txt");
        
        if(levelFile.exists()){
            writeToFile(levelFile,Utilz.twoDToOneDInt(id));
        }
        return;
    } 
}
