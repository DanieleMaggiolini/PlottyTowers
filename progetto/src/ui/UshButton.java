
package ui;

import helpz.LoadSave;
import java.awt.Graphics;
import java.awt.image.BufferedImage;


public class UshButton extends PauseButton{
    private BufferedImage[] buttonImg; 
    private boolean mouseOver,mousePressed;
    private boolean muted;
    private int rowIndex;
    public UshButton(int x, int y, int width, int height, int rowIndex) {
        super(x, y, width, height);
        this.rowIndex=rowIndex;
        loadImage();
    }          

    private void loadImage() {
       BufferedImage temp= LoadSave.getImage(LoadSave.MENU_BUTTONS);
       buttonImg = new BufferedImage[3];
       buttonImg[0]= temp.getSubimage(256*2, 0, 256, 256);
       buttonImg[1]= temp.getSubimage(256*3, 0, 256, 256);
       buttonImg[2]= temp.getSubimage(0, 256, 256*3, 256);
    }
    public void update(){      
    }
    public void draw(Graphics g){
        g.drawImage(buttonImg[rowIndex], x, y, width, height, null);
    }

    public boolean isMouseOver() {
        return mouseOver;
    }

    public boolean isMousePressed() {
        return mousePressed;
    }

    public boolean isMuted() {
        return muted;
    }
    
    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }
    public void resetBools(){
        mousePressed=false;
    }
}
