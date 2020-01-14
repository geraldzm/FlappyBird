package model;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Background extends ObjectGame implements GetResources{
    private BufferedImage background;
    private Panel[] panels;

    public Background() {
        super(0,0,314,Game.HEIGHT-112);
        panels = new Panel[4];
        for (int i = 0; i < panels.length; i++) {
            panels[i] = new Panel(i*w,0.7f, w);
        }
        background = Utility.toBufferedImage(getBufferedImage("resource/bg.png")
                .getScaledInstance(w, h, BufferedImage.SCALE_SMOOTH));
    }

    @Override
    public void render(Graphics g) {
        for(Panel p: panels){
            g.drawImage(background, (int)p.getpX(),0,null);
        }
    }

    @Override
    public void tick() {
        for (int i = 0; i < panels.length; i++) {
            panels[i].move();
        }
    }
}
