package model;

public class Panel {
    private float pX,speed;
    private int w;

    public Panel(float x, float speed, int w) {
        this.pX = x;
        this.speed = speed;
        this.w = w;
    }
    public void move(){
        setpX(pX - speed);
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getpX() {
        return pX;
    }

    public void setpX(float pX) {
        if(pX < (-w)){
            this.pX = Game.WIDTH;
        }else{
            this.pX = pX;
        }
    }
}
