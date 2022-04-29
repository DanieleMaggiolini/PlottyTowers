
package ui;

import helpz.LoadSave;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class SoundButton extends PauseButton{
    
    private BufferedImage[] soundImg; 
    private boolean mouseOver,mousePressed;
    private boolean muted;
    private int rowIndex;
    public SoundButton(int x, int y, int width, int height) {
        super(x, y, width, height);
        loadImage();
    }          

    private void loadImage() {
       BufferedImage temp= LoadSave.getImage(LoadSave.MENU_BUTTONS);
       soundImg = new BufferedImage[2];
       soundImg[0]= temp.getSubimage(0, 0, 256, 256);
       soundImg[1]= temp.getSubimage(256, 0, 256, 256);
    }
    public void update(){
        if(muted)
            rowIndex=1;
        else
            rowIndex=0;      
    }
    public void draw(Graphics g){
        g.drawImage(soundImg[rowIndex], x, y, width, height, null);
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

    public void setMuted(boolean muted) {
        this.muted = muted;
    }
    public void resetBools(){
        mousePressed=false;
    }
}
