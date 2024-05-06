//Basic Game Application
//Version 2
// Basic Object, Image, Movement
// Astronaut moves to the right.
// Threaded

//K. Chun 8/2018

//*******************************************************************************
//Import Section
//Add Java libraries needed for the game
//import java.awt.Canvas;

//Graphics Libraries
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;


//*******************************************************************************
// Class Definition Section

public class BasicGameApp implements Runnable, KeyListener {

   //Variable Definition Section
   //Declare the variables used in the program 
   //You can set their initial values too
   
   //Sets the width and height of the program window
	final int WIDTH = 1000;
	final int HEIGHT = 700;

   //Declare the variables needed for the graphics
	public JFrame frame;
	public Canvas canvas;
   public JPanel panel;
   
	public BufferStrategy bufferStrategy;

	public Image CartoonRoad;

	public Image CarPic;

	public Image WinScreen;

//	public Image elmacho;

//	public Image Scarlet;

	public Image EvilMinionPic;

	public int backgroundX = 0;

	public Image minionPic;

	public boolean winGame;


	public int score = 0;
   //Declare the objects used in the program
   //These are things that are made up of more than one variable type


	private Minion minion;

    private Minion Car;
	public EvilMinion[] aMinion;

   // Main method definition
   // This is the code that runs first and automatically
	public static void main(String[] args) {
		BasicGameApp ex = new BasicGameApp();   //creates a new instance of the game
		new Thread(ex).start();                 //creates a threads & starts up the code in the run( ) method  
	}


   // Constructor Method
   // This has the same name as the class
   // This section is the setup portion of the program
   // Initialize your variables and construct your program objects here.
	public BasicGameApp() {
      
      setUpGraphics();
       
      //variable and objects
      //create (construct) the objects needed for the game and load up 

		CartoonRoad = Toolkit.getDefaultToolkit().getImage("cartoon_road.jpg");
		CarPic = Toolkit.getDefaultToolkit().getImage("Lucy'sCar.png");
		WinScreen = Toolkit.getDefaultToolkit().getImage("WinScreen.jpg");
		minionPic = Toolkit.getDefaultToolkit().getImage("minions_PNG71.png");
		EvilMinionPic = Toolkit.getDefaultToolkit().getImage("EvilMinion.png");
        Car = new Minion (700,400);
        Car.width = 300;
        Car.height = 300;
        Car.dx = 3;
		winGame = false;
        minion = new Minion (10,100);
		aMinion = new EvilMinion[70];
		for(int i =0; i<70; i++){
			aMinion[i] = new EvilMinion((int)(Math.random()*1000), (int)(Math.random()*700));
		}
//		elmacho = new Villian (100,100);
//		Scarlet = new Villian (1000,100);


	}// BasicGameApp()

   
//*******************************************************************************
//User Method Section
//
// put your code to do things here.

   // main thread
   // this is the code that plays the game after you set things up
	public void run() {

      //for the moment we will loop things forever.
		while (true) {

         moveThings();  //move all the game objects
         render();  // paint the graphics
         pause(20); // sleep for 10 ms
		}
	}


	public void moveThings()
	{
      //calls the move( ) code in the objects

		minion.move();
        Car.wrap();
		minion.bounce();
		for(int i =0; i<aMinion.length; i++) {
			aMinion[i].bounce();
			if(minion.rec.intersects(aMinion[i].rec) && aMinion[i].isCrashing == false){
				minion.isAlive = false;
				aMinion[i].isCrashing = true;
				score-=1;
				System.out.println("Score: " + score);
			}
			if(minion.rec.intersects(aMinion[i].rec) == false){
				aMinion[i].isCrashing = false;
			}
		}

        if(minion.rec.intersects(Car.rec)){
            winGame = true;
        }


	}
	
   //Pauses or sleeps the computer for the amount specified in milliseconds
   public void pause(int time ){
   		//sleep
			try {
				Thread.sleep(time);
			} catch (InterruptedException e) {

			}
   }

   //Graphics setup method
   private void setUpGraphics() {
      frame = new JFrame("Application Template");   //Create the program window or frame.  Names it.
   
      panel = (JPanel) frame.getContentPane();  //sets up a JPanel which is what goes in the frame
      panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));  //sizes the JPanel
      panel.setLayout(null);   //set the layout
   
      // creates a canvas which is a blank rectangular area of the screen onto which the application can draw
      // and trap input events (Mouse and Keyboard events)
      canvas = new Canvas();  
      canvas.setBounds(0, 0, WIDTH, HEIGHT);
	  canvas.addKeyListener(this);
      canvas.setIgnoreRepaint(true);
   
      panel.add(canvas);  // adds the canvas to the panel.
   
      // frame operations
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //makes the frame close and exit nicely
      frame.pack();  //adjusts the frame and its contents so the sizes are at their default or larger
      frame.setResizable(false);   //makes it so the frame cannot be resized
      frame.setVisible(true);      //IMPORTANT!!!  if the frame is not set to visible it will not appear on the screen!
      
      // sets up things so the screen displays images nicely.
      canvas.createBufferStrategy(2);
      bufferStrategy = canvas.getBufferStrategy();
      canvas.requestFocus();
      System.out.println("DONE graphic setup");
   
   }


	//paints things on the screen using bufferStrategy
	private void render() {
		Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
		g.clearRect(0, 0, WIDTH, HEIGHT);
		if(winGame == false) {


			if (backgroundX > -1000) {
				backgroundX = backgroundX - 10;
			} else {
				backgroundX = 0;
			}
			//draw the image of the astronaut

			g.drawImage(CartoonRoad, backgroundX, 0, WIDTH, HEIGHT, null);
			g.drawImage(CartoonRoad, backgroundX + 1000, 0, WIDTH, HEIGHT, null);
			g.drawImage(CarPic, Car.xpos, Car.ypos, Car.width, Car.height, null);
			g.drawImage(minionPic, minion.xpos, minion.ypos, minion.width, minion.height, null);
			for (int i = 0; i < aMinion.length; i++) {
				g.drawImage(EvilMinionPic, aMinion[i].xpos, aMinion[i].ypos, aMinion[i].width, aMinion[i].height, null);
				//	g.drawRect( aMinion[i].rec.x, aMinion[i].rec.y, aMinion[i].rec.width, aMinion[i].rec.height);

			}
			g.setColor(Color.BLACK);
			g.drawString("Score: " + score, 50, 50);
		}else{
			g.drawImage(WinScreen, 0, 0, WIDTH, HEIGHT,null);
		}
		g.dispose();

		bufferStrategy.show();
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println(e.getKeyCode());
	if(e.getKeyCode()==38){
		minion.dy = -2;
	}
	if(e.getKeyCode() == 40){
		minion.dy = 2;
	}

	if(e.getKeyCode() == 39){
		minion.dx = 2;
	}

	if(e.getKeyCode() == 37){
		minion.dx = -2;
	}
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}
}