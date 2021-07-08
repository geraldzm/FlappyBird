package model;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Bird extends ObjectGame implements GetResources{

    private BufferedImage bird;
    private float velocity;
    private int flutter;

    public Bird(float x, float y, float speedX, float speedY, int w, int h) {
        super(x, y, speedX, speedY, w, h);
        stickImages();
        velocity = -4.2f;
        flutter = 0;
    }

    @Override
    public void render(Graphics g) {
        y = Utility.clamp((int)y, 0 , Game.HEIGHT-112-43);
        if(speedY >= 0) {
            g.drawImage(bird.getSubimage(0, 0, 43, 30), (int) x, (int)y, null);
            flutter = 0;
        }else{
            g.drawImage(bird.getSubimage(43*flutter, 0, 43, 30), (int) x, (int)y, null);
        }
    }

    @Override
    public void tick() {
        y += speedY;
        speedY = Utility.clamp(speedY+0.2f, -4f, 4f);
        flutter = Utility.clamp(flutter+1, 0, 3);
        flutter = flutter == 3 ? 0: flutter;
    }
    public void up(){
        speedY = velocity;
    }

    private void stickImages(){
        bird = new BufferedImage(102,24, BufferedImage.TYPE_INT_ARGB);
        Graphics2D bGr = bird.createGraphics();
        bGr.drawImage(getBufferedImage("resource/bird1.png"), 0, 0, null);
        bGr.drawImage(getBufferedImage("resource/bird2.png"), 34, 0, null);
        bGr.drawImage(getBufferedImage("resource/bird3.png"), 68, 0, null);
        bird = Utility.toBufferedImage(bird.getScaledInstance(w, h, BufferedImage.SCALE_SMOOTH));
        bGr.dispose();
    }

    @Override
    public Rectangle getRectangle() {
        return new Rectangle((int)x, (int)y, 43-5, h-5);
    }
}
