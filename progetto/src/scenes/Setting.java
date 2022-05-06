package scenes;

import helpz.LoadSave;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import progetto.Game;
import static progetto.GameStates.*;
import static progetto.GameStates.setGameState;
import ui.MyButton;


public class Setting extends GameScene implements SceneMethods{
    
    private BufferedImage ingranaggio;
    
    private MyButton Bmenu;
    private Font f;
    private Color c;
    
    private String previous = "";
    
    public Setting(Game game) {
        super(game);
        
        impImage();
        
        initButtons();
    }
    
    public void updates(){
        
    }
    
    @Override
    public void render(Graphics g) {
        g.setColor(Color.blue);
        g.fillRect(0, 0, 640, 640);
        g.drawImage(ingranaggio, 10, 10, 80, 80, null);
        drawButton(g);
              
        
        
    }
    
     private void initButtons() {
        int w = 80;
        int h = 80;
        int x = 10;
        int y = 10;
        int yOffset = 100;
        f=new Font("Arial",Font.BOLD,40);
        c=new Color(255,0,0);
        Bmenu = new MyButton("", x, y, w, h, f, c);
    }
     
    private void impImage() {
        ingranaggio= LoadSave.getImage(LoadSave.INGRANAGGIO);
    }
    
    private void drawButton(Graphics g) {
        Bmenu.draw(g);
    }
    
    public void setPrevious(String s){
        previous = s;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println(e.getX() +" - "+ e.getY());
        if (Bmenu.getBounds().contains(e.getX(), e.getY())) {
            if("menu".equals(previous)){
                setGameState(MENU);
            }else if("level1".equals(previous)){
                setGameState(LVL1);
            }
        }
        /*    
        }else if (Bsettings.getBounds().contains(e.getX(), e.getY())) {
            setGameState(SETTINGS);
        }else if (Bquit.getBounds().contains(e.getX(), e.getY())) {
            System.exit(0);
        } */
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }  
}
