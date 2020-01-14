package controller;

import model.Bird;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class InputKey extends KeyAdapter {
    private boolean pressed = false;
    private Bird bird;

    public InputKey(Bird bird) {
        this.bird = bird;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE && !pressed){
            bird.up();
            pressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE)pressed = false;
    }
}
