package model;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Floor extends ObjectGame implements GetResources{
    private BufferedImage floor;
    private Panel[] panels;

    public Floor(float speed) {
        super(0,Game.HEIGHT-112,314,112);
        panels = new Panel[4];
        for (int i = 0; i < panels.length; i++) {
            panels[i] = new Panel(i*w, speed, w);
        }
        floor = getBufferedImage("resource/base.png");
    }

    @Override
    public void render(Graphics g) {
        for(Panel p: panels){
            g.drawImage(floor, (int)p.getpX(),(int)getY(),null);
        }
    }

    @Override
    public void tick() {
        for (int i = 0; i < panels.length; i++) {
            panels[i].move();
        }
    }
}
