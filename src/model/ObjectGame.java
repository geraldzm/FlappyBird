package model;

import java.awt.*;

public abstract class ObjectGame {
    protected float x, y, speedX, speedY;
    protected int w, h;

    public abstract void render(Graphics g);
    public abstract void tick();

    public boolean intersects(Rectangle r){
        return new Rectangle((int)x, (int)y, w, h).intersects(r);
    }
    public Rectangle getRectangle(){
        return new Rectangle((int)x, (int)y, w, h);
    }

    public ObjectGame() {
    }

    public ObjectGame(float x, float y, float speedX, float speedY, int w, int h) {
        this.x = x;
        this.y = y;
        this.speedX = speedX;
        this.speedY = speedY;
        this.w = w;
        this.h = h;
    }

    public ObjectGame(float x, float y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public float getSpeedX() {
        return speedX;
    }

    public void setSpeedX(float speedX) {
        this.speedX = speedX;
    }

    public float getSpeedy() {
        return speedY;
    }

    public void setSpeedy(float speedy) {
        this.speedY = speedy;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
}
