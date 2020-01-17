package model;

import controller.InputKey;
import controller.InputMouse;
import view.WindowGame;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import model.MenuGame.Status;

public class Game extends Canvas implements Runnable{
    public static final int WIDTH = 942, HEIGHT = WIDTH / 12* 9;
    private Thread thread;
    private boolean running = false;
    private HandlerGameObjects handler;
    private Bird bird;
    private HeadBar headBar;
    private ArrayList<Integer> scores;
    private MenuGame menuGame;
    public static Status status;

    Game(){
        status = Status.START_MENU;
        menuGame = new MenuGame(this);
        restartGame();
        addMouseListener(new InputMouse(menuGame));
        scores = new ArrayList<>();
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
                //System.out.println("FPS: "+ frames);
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

        handler.render(g);
        headBar.render(g);
        if(status == Status.START_MENU || status == Status.RATING)menuGame.render(g);
        g.dispose();
        bs.show();
    }

    private void tick() {
        if(status == MenuGame.Status.GAMING) {
            handler.tick();
            headBar.tick();
        }else if(status == Status.START_MENU || status == Status.RATING){
            menuGame.tick();
        }
    }

    public void gameOver(){
        scores.add(headBar.getScore());
        status = MenuGame.Status.START_MENU;
    }

    public void restartGame(){
        Background background = new Background();
        bird = new Bird(100,150,0, 0, 43*3, 30);
        handler = new HandlerGameObjects(bird, this);
        handler.addElement(background);
        for (int i = 0; i < 4; i++) {
            handler.addElement(new Pipe(WIDTH + (i * 250 ), 1f, background.getH()));
        }
        addKeyListener(new InputKey(bird));
        handler.addElement(new Floor(1f));
        handler.addElement(bird);
        headBar = new HeadBar();
    }

    public static void main(String[] args) {
        new Game();
    }
}
