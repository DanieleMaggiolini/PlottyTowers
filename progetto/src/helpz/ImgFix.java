package helpz;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class ImgFix {
    //rotate
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
    //crea nuove immagini unendone di già esistenti
    public static BufferedImage buildImage(BufferedImage[] imgs){
        int w=imgs[0].getWidth();
        int h=imgs[0].getHeight();
        
        BufferedImage image= new BufferedImage(w, h, imgs[0].getType());
        Graphics2D g2d = image.createGraphics();
        
        for(BufferedImage i : imgs){
            g2d.drawImage(image , 0, 0, null);
        }
        g2d.dispose();
        return image;
    }
}
