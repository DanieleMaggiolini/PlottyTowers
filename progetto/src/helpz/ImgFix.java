/**
* @author  Daniele Maggiolini
* @author  Mattia Minotti
* @version 1.0
* @file ImgFix.java 
* 
* @brief aggiusta le immagini.
*
*/
package helpz;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * @class ImgFix
 *
 * @brief gestisce gli aggiustamenti delle immagini.
 *
 */
public class ImgFix {
    
     /**
     @brief ritorna l'immagine ruotata
     * 
     * @param img imagine
     * @param angel angolo di rotazione
     */
    public static BufferedImage getRotImage(BufferedImage img, int angle){
        int w=img.getWidth();
        int h=img.getHeight();
        
        BufferedImage image= new BufferedImage(w, h, img.getType());
        Graphics2D g2d = image.createGraphics();
        
        g2d.rotate(Math.toRadians(angle),w/2,h/2);
        g2d.drawImage(img, 0, 0, null);
        g2d.dispose();
        
        return image;
    }
    
    /**
     @brief ritorna immagini unite
     * 
     * @param imgs array di immagini da unire
     */
    public static BufferedImage buildImage(BufferedImage[] imgs){
        int w=imgs[0].getWidth();
        int h=imgs[0].getHeight();
        
        BufferedImage image= new BufferedImage(w, h, imgs[0].getType());
        Graphics2D g2d = image.createGraphics();
        
        for(int i = 0; i < imgs.length; i++){
            g2d.drawImage(image , 0, 0, null);
        }
        g2d.dispose();
        return image;
    }
}
