import java.awt.*;

public class EvilMinion {
    public  int ypos;
    public String name;                //holds the name of the hero
    public  int xpos;                //the x position
    public int dx;                    //the speed of the hero in the x direction
    public int dy;                    //the speed of the hero in the y direction
    public  int width;
    public  int height;
    public boolean isAlive;
    public Rectangle rec;

    public EvilMinion(int pXpos, int pYpos) {
        xpos = pXpos;
        ypos = pYpos;
        dx =1;
        dy =0;
        width = 40;
        height = 40;
        isAlive = true;
        rec = new Rectangle(xpos,ypos,width,height);


    }
    public void move() {
        xpos = xpos + dx;
        ypos = ypos + dy;
        rec = new Rectangle(xpos,ypos,width,height);

    }

    public void bounce(){
        xpos = xpos + dx;
        ypos = ypos + dy;
        rec = new Rectangle(xpos,ypos,width,height);



        if(ypos>630){
            dy = -2;
        }

        if(ypos<0){
            dy = 2;
        }

        if(xpos<0){
            dx = 2;
        }
        if(xpos>1000){
            dx = -2;
        }
    }
}
