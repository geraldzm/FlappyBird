package model;

import controller.InputKey;
import view.WindowGame;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable{
    public static final int WIDTH = 942, HEIGHT = WIDTH / 12* 9;
    private Thread thread;
    private boolean running = false;
    private Background background;
    private Floor floor;
    private Pipe[] pipes;
    private Bird bird;

    Game(){
        background = new Background();
        floor = new Floor(1f);
        pipes = new Pipe[4];
        bird = new Bird(100,150,0, 0, 43*3, 30);
        for (int i = 0; i < pipes.length; i++) {
            pipes[i] = new Pipe(WIDTH + (i * 250 ), 1f, background.getH());
        }
        addKeyListener(new InputKey(bird));

        new WindowGame(WIDTH, HEIGHT, "Flappy Bird", this);
    }

    public synchronized void start(){
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop(){
        try{
            thread.join();
            running = false;
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void run(){
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            boolean shouldRender = false;
            while(delta >=1) {
                tick();
                delta--;
                shouldRender = true;
            }
            if(shouldRender) {
                render();
                frames++;
            }

            if(System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("FPS: "+ frames);
                frames = 0;
            }
        }
        stop();
    }

    private void render() {
        BufferStrategy bs = getBufferStrategy();
        if(bs == null){
            createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();

        //handler.render(g);
        background.render(g);
        for (int i = 0; i < pipes.length; i++) {
            pipes[i].render(g);
        }
        floor.render(g);
        bird.render(g);

        g.dispose();
        bs.show();
    }

    private void tick() {
        floor.tick();
        background.tick();
        for (int i = 0; i < pipes.length; i++) {
            pipes[i].tick();
        }
        bird.tick();
    }

    public static void main(String[] args) {
        new Game();
    }
}
