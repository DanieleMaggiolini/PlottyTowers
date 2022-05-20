/**
* @author  Daniele Maggiolini
* @author  Mattia Minotti
* @version 1.0
* @file SceneMetods.java 
* 
* @brief gestisce i metodi delle scene.
*
*/
package scenes;

import java.awt.Graphics;
import java.awt.event.MouseEvent;

public interface SceneMethods {
    public void render(Graphics g);
    
    public void mouseClicked(MouseEvent e);
    
    public void mouseMoved(MouseEvent e);
    
    public void mousePressed(MouseEvent e);
    
    public void mouseReleased(MouseEvent e);
    
    public void mouseDragged(MouseEvent e);
}
