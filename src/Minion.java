import java.awt.*;

public class Minion {
    public String name;                //holds the name of the hero
    public int xpos;                //the x position
    public int ypos;                //the y position
    public int dx;                    //the speed of the hero in the x direction
    public int dy;                    //the speed of the hero in the y direction
    public int width;
    public int height;
    public boolean isAlive;

    public boolean isCrashing = false;

    public Rectangle rec;


    public Minion(int pXpos, int pYpos) {
        xpos = pXpos;
        ypos = pYpos;
        dx =1;
        dy =0;
        width = 60;
        height = 60;
        isAlive = true;
        rec = new Rectangle(xpos,ypos,width,height);

    }
    public void move() {
        xpos = xpos + dx;
        ypos = ypos + dy;
        rec = new Rectangle(xpos,ypos,width,height);

    }

    public void bounce(){
        rec = new Rectangle(xpos,ypos,width,height);
        if(ypos>650){
          dy = -3;
      }

      if(ypos<0){
          dy = 3;
      }

      if(xpos<0){
          dx = 3;
      }
      if(xpos>1000){
          dx = -3;
      }
        }
    public void wrap() {
        rec = new Rectangle(xpos, ypos, width-100, height-100);

        xpos = xpos + dx;
        ypos = ypos + dy;

        if (xpos > 1000) {
            xpos = -300;
        }
    }

    }
