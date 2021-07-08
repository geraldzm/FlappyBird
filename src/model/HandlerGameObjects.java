package model;

import java.awt.*;
import java.util.ArrayList;

public class HandlerGameObjects {
    private ArrayList<ObjectGame> objects;
    private Bird bird;
    private Game game;

    public HandlerGameObjects(Bird bird, Game game) {
        objects = new ArrayList<>();
        this.bird = bird;
        this.game = game;
    }

    public void render(Graphics g){
        for (int i = 0; i < objects.size(); i++) {
            objects.get(i).render(g);
        }
    }
    public void tick(){
        for (int i = 0; i < objects.size(); i++) {
            ObjectGame o = objects.get(i);
            if(o instanceof Pipe){
                if(o.intersects(bird.getRectangle())){
                    game.gameOver();
                }
            }
            o.tick();
        }
    }

    public void addElement(ObjectGame o){
        objects.add(o);
    }
    public void removeElement(ObjectGame o){
        objects.remove(o);
    }
    public boolean contains(ObjectGame o){
        return objects.contains(o);
    }

}
