package controller;

import model.MenuGame;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class InputMouse extends MouseAdapter {
    private MenuGame menuGame;

    public InputMouse(MenuGame menuGame) {
        this.menuGame = menuGame;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        menuGame.clicked(e);
    }
}
