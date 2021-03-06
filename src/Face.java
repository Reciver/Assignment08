import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 *
 * @author alled7036
 */
  
   
public class Face extends JComponent{

    // Height and Width of our game
    static final int WIDTH = 800;
    static final int HEIGHT = 600;
    
    // sets the framerate and delay for our game
    // you just need to select an approproate framerate
    long desiredFPS = 60;
    long desiredTime = (1000)/desiredFPS;
    
   int mouth = -1; 
   int moveMouth = 350;
    
    // drawing of the game happens in here
    // we use the Graphics object, g, to perform the drawing
    // NOTE: This is already double buffered!(helps with framerate/speed)
    @Override
    public void paintComponent(Graphics g)
    {
        // always clear the screen first!
        g.clearRect(0, 0, WIDTH, HEIGHT);
        
        // GAME DRAWING GOES HERE
        
        
        //Making the face
        Color lightBrown = new Color(227,176,125);
        g.setColor(lightBrown);
        g.fillOval(250, 150, 300, 300);
        
        // GAME DRAWING ENDS HERE
        
        //Detail for the eyes
        g.setColor(Color.WHITE);
        g.fillOval(300, 225, 50, 50);
        
        g.setColor(Color.WHITE);
        g.fillOval(450, 225, 50, 50);
        
        g.setColor(Color.BLACK);
        g.fillOval(315, 237, 20, 20);
        
        g.setColor(Color.BLACK);
        g.fillOval(465, 237, 20, 20);
        
        //Ears
        g.setColor(lightBrown);
        g.fillOval(500, 225, 75, 150 );
        
        g.setColor(lightBrown);
        g.fillOval(225, 225, 75, 150 );
        
        //Mouth Details
        g.setColor(Color.BLACK);
        g.fillRect(325, moveMouth, 150, 25);
        
        //hair
        g.fillArc(350, 110, 100, 100, 0, 180);
        
        
    }
    
   
    // The main game loop
    // In here is where all the logic for my game will go
    public void run()
    {
        // Used to keep track of time used to draw and update the game
        // This is used to limit the framerate later on
        long startTime;
        long deltaTime;
        
        // the main game loop section
        // game will end if you set done = false;
        boolean done = false; 
        while(!done)
        {
            // determines when we started so we can keep a framerate
            startTime = System.currentTimeMillis();
            
            // all your game rules and move is done in here
            // GAME LOGIC STARTS HERE 
            
            //Moving mouth if the mouth is lower then 340 it moves up
             if(moveMouth < 340){
             mouth =  +1;
           }
             //if mouth moves higher then 360 it moves back down
             if(moveMouth > 360){
                 mouth = -1;
             }
            moveMouth = moveMouth + mouth;
            // GAME LOGIC ENDS HERE 
            
            // update the drawing (calls paintComponent)
            repaint();
            
            
            
            // SLOWS DOWN THE GAME BASED ON THE FRAMERATE ABOVE
            // USING SOME SIMPLE MATH
            deltaTime = System.currentTimeMillis() - startTime;
            if(deltaTime > desiredTime)
            {
                //took too much time, don't wait
            }else
            {
                try
                {
                    Thread.sleep(desiredTime - deltaTime);
                }catch(Exception e){};
            }
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // creates a windows to show my game
        JFrame frame = new JFrame("My Game");
       
        // creates an instance of my game
        Face game = new Face();
        // sets the size of my game
        game.setPreferredSize(new Dimension(WIDTH,HEIGHT));
        // adds the game to the window
        frame.add(game);
         
        // sets some options and size of the window automatically
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        // shows the window to the user
        frame.setVisible(true);
        
        // starts my game loop
        game.run();
    }
}
