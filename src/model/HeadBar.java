package model;

import java.awt.*;

public class HeadBar {
        private float score;

        public HeadBar() {
                score = 0;
        }

        public void render(Graphics g){
            g.setColor(Color.BLUE.brighter());
            g.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
            g.drawString("Score: "+ (int)score, 10,35);
        }

        public void tick(){
            score += 0.5f;
        }
        public int getScore(){
            return (int)score;
        }
}
