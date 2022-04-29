package helpz;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class LoadSave {
    public static final String BACKGROUND_MENU = "image/background/backgroundMenu.png";
    public static final String PAUSE_BACKGROUND = "image/other/PAUSE_MENU.png";
    public static final String SPRITE_ATLAS= "image/background/atlas.png";
    public static final String MENU_BUTTONS = "image/other/menuButton.png";
    public static final String LEVEL_MENU = "image/other/LevelMenu.png";
    public static final String INGRANAGGIO = "image/other/ingranaggio.png";
    public static final String NARUTO = "image/enemy/naruto.png";
               
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
}
