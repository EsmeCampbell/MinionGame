public class Villian {
    public int xpos;                //the x position
    public int ypos;                //the y position
    public int dx;                    //the speed of the hero in the x direction
    public int dy;                    //the speed of the hero in the y direction
    public int width;
    public int height;
    public boolean isAlive;

    public Villian(int pXpos, int pYpos) {
        xpos = pXpos;
        ypos = pYpos;
        dx = 1;
        dy = 0;
        width = 60;
        height = 60;
        isAlive = true;
    }
    }
