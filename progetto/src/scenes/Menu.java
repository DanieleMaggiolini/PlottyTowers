package scenes;

import helpz.LoadSave;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import progetto.Game;
import progetto.GameStates;
import ui.MyButton;
import static progetto.GameStates.*;

public class Menu extends GameScene implements SceneMethods {
    //buffer contenente immagini
    private BufferedImage background;
    private MyButton Bplaying, Bsettings, Bquit;
    private Font f;
    private Color c;
    
    public Menu(Game game) {
        super(game);  
        initButtons();
        importImg();      
    }
    private void initButtons() {

        int w = Game.currentScreenWidth/5;   //1792/4;
        int h = Game.currentScreenHeight/24;   //960/24;960/24;
        int x = (int)(Game.currentScreenWidth*0.025);   //;40;
        int y = Game.currentScreenHeight/3+ (int)(Game.currentScreenHeight*0.067);   //960/3+65;  
        int yOffset =(int)(Game.currentScreenHeight*0.072);    //70;
        f=new Font("Arial",Font.BOLD,h);
        c=new Color(255,255,255);
        Bplaying = new MyButton("GIOCA", x, y, w, h, f, c);
        Bsettings = new MyButton("IMPOSTAZIONI", x, y + yOffset, w, h, f, c);
        Bquit = new MyButton("ESCI", x, y + yOffset * 2, w, h, f, c);
        
        
    }
    
    public void updates(){
        
    }
    
    @Override
    public void render(Graphics g) {

        g.drawImage(background, 0, 0, Game.currentScreenWidth, Game.currentScreenHeight, null);
        drawButton(g);

    }

    private void drawButton(Graphics g) {
        Bplaying.draw(g);
        Bsettings.draw(g);
        Bquit.draw(g);
    }

    //metodo per importare il buffer delle immagini nella variabile is
    private void importImg() {
        background= LoadSave.getImage(LoadSave.BACKGROUND_MENU);
    }

    
    @Override
    public void mouseClicked(MouseEvent e) {
        if (Bplaying.getBounds().contains(e.getX(), e.getY())) {
            setGameState(PLAYING);
        }else if (Bsettings.getBounds().contains(e.getX(), e.getY())) {
            setGameState(SETTINGS);
        }else if (Bquit.getBounds().contains(e.getX(), e.getY())) {
            System.exit(0);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (Bplaying.getBounds().contains(e.getX(), e.getY())) {
            Bplaying.setMouseOver(true);
            Bplaying.allunga(true);
            return;
        } else if (Bsettings.getBounds().contains(e.getX(), e.getY())) {
            Bsettings.setMouseOver(true);
            Bsettings.allunga(true);
            return;
        } else if (Bquit.getBounds().contains(e.getX(), e.getY())) {
            Bquit.setMouseOver(true);
            Bquit.allunga(true);
            return;
        }
        resetButtons();
    }
    @Override
    public void mousePressed(MouseEvent e) {
        if (Bplaying.getBounds().contains(e.getX(), e.getY())) {
            Bplaying.setMousePressed(true);       
        }else if(Bsettings.getBounds().contains(e.getX(), e.getY())){
            Bsettings.setMousePressed(true);
        }else if(Bquit.getBounds().contains(e.getX(), e.getY())){
            Bquit.setMousePressed(true);
        }
    }

    public void mouseReleased(MouseEvent e) {
        resetButtons();
    }

    public void resetButtons() {
        Bplaying.resetBooleans();
        Bsettings.resetBooleans();
        Bquit.resetBooleans();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        
    }
}
