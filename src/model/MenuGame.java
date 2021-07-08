package model;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.HashMap;

import static model.Game.status;

public class MenuGame {
    public enum Status{START_MENU, RATING, GAMING};
    private enum ButtonsBoxes{START_GAME, RATING, QUIT};
    private HashMap<ButtonsBoxes, Rectangle2D> buttons;
    private Game game;

    public MenuGame(Game game) {
        this.game = game;
        buttons = new HashMap<>();
        buttons.put(ButtonsBoxes.START_GAME, new Rectangle(Game.WIDTH/2 - 100, 150, 200, 100));
        buttons.put(ButtonsBoxes.RATING, new Rectangle(Game.WIDTH/2 - 100, 265, 200, 100));
    }

    public void render(Graphics g){
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.BLACK);
        g.setFont(new Font("Comic Sans MS", Font.ITALIC, 30));
        switch (status){
            case START_MENU:
                Rectangle2D r = buttons.get(ButtonsBoxes.START_GAME);
                drawTextWithRect("Start Game", g2, r);
                r = buttons.get(ButtonsBoxes.RATING);
                drawTextWithRect("See rating", g2, r);
                break;
            case RATING:

        }
    }

    public void clicked(MouseEvent e){
        switch (status){
            case START_MENU:
                if(containsClicked(e, buttons.get(ButtonsBoxes.START_GAME))){
                    game.restartGame();
                    status = Status.GAMING;
                }
                break;
            case RATING:

        }
    }

    private boolean containsClicked(MouseEvent e, Rectangle2D r){
        return e.getX() > r.getX() && e.getX() < r.getX()+r.getWidth() && e.getY() > r.getY() && e.getY() < r.getY()+r.getHeight();
    }

    private void drawTextWithRect(String s, Graphics2D g, Rectangle2D r){
        g.draw(r);
        g.drawString(s, (int)r.getX()+20, (int)r.getY()+60);
    }

    public void tick(){

    }
}
