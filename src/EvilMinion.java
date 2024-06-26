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

    public boolean isCrashing = false;

    public Rectangle rec;

    public EvilMinion(int pXpos, int pYpos) {
        xpos = pXpos;
        ypos = pYpos;
        dx =3;
        dy =3;
        width = 100;
        height = 100;
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



        if(ypos>700){
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
}
