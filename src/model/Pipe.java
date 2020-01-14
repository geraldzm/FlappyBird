package model;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Pipe extends ObjectGame implements GetResources{
    private BufferedImage pipeU;
    private BufferedImage pipeD;
    private float y2;
    private int backgroundHeight;
    public static int spreadBetweenPipes = 90;

    public Pipe(float x, float speed, int backgroundHeight) {
        super(x, 0, speed,0, 52,320);
        pipeU = getBufferedImage("resource/pipe.png");
        pipeD = getBufferedImage("resource/piped.png");
        this.backgroundHeight = backgroundHeight;
        spam();
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(pipeD, (int)x, (int)y, null);
        g.drawImage(pipeU, (int)x, (int)y2, null);
    }

    @Override
    public void tick() {
        x -= speedX;
        if(x < -w)spam();
    }

    private void spam(){
        setY(Utility.clamp((float) (Math.random()* -320), -(320-(backgroundHeight-(h+spreadBetweenPipes))),0f));
        y2 = y + spreadBetweenPipes + 320;
        if(x < -w)x = Game.WIDTH;
    }

    @Override
    public boolean contains(Rectangle r) {
        return super.contains(r) || new Rectangle((int)x, (int)y2, w, h).contains(r);
    }
}
