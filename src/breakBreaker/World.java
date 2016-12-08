package breakBreaker;

import serverSocket.Database;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.prefs.Preferences;

import javax.swing.JComponent;

class World extends JComponent {
    /**
	 * 
	 */
	public String playerNameBB;
	private static final long serialVersionUID = 1L;
	private Ball ball;  // A Object of type ball
    private Player player; // Object denoting player
    private Brick[][] bricks; // A 2d array of bricks
    private Ellipse2D e; // graphics object for Ball
    private Rectangle2D c; // Graphics object for Player
    private int score = 0; // Game score
    public boolean isGameOver = false; // Denotes whether the game is over or not
    private int lives = 3; // Number of lives
    private double impact_distance; /* distance from point of impact 
				       to the center of the Player block */
    private Font f1, f2;  /* Font objects for changing font styles */
    private TextLayout t1, t2, t3; /* Text layouts */
    private FontRenderContext frc;
    private Preferences prefs; // Top score preferences
    public World() {
	e = new Ellipse2D.Double();
	c = new Rectangle2D.Double();
	prefs = Preferences.userNodeForPackage(this.getClass());
    }
    public void paintComponent(Graphics g) {
	super.paintComponent(g);
	Graphics2D g2 = (Graphics2D) g;
	frc = g2.getFontRenderContext();
	if(isGameOver) {
		try{
		Database db = new Database();
		db.addScore(playerNameBB, Integer.toString(score), "Break Breaker");
		String addr = "localhost";
		Socket s = new Socket(addr, 9090);
		PrintWriter out = new PrintWriter(
				s.getOutputStream(),true
				);
		out.println(playerNameBB+" mengupload score Break Breaker dengan Score:"+Integer.toString(score));
		}
		catch(Exception error){
			error.printStackTrace();
		}
	    t1 = new TextLayout("Game Over", f1, frc);
	    t2 = new TextLayout("You cleared the stage!", f2, frc);
	    t3 = new TextLayout("Try Again", f2, frc);
	    t1.draw(g2, 300, 270);
	    if(score == 45) {
		t2.draw(g2, 350, 290);  // Means the player has cleared all the bricks
	    }else{
		t3.draw(g2, 350, 290); // Game ended but some bricks left
	    }
	    g2.drawString("Your Scores", 370, 350); // Display top scores of all time
	    g2.drawString("Score:  "+score, 360, 370);
	}
	e.setFrame(ball.x, ball.y, ball.radius, ball.radius); // Set ball position
	c.setFrame(player.x, player.y, player.width, player.height); // Set player position
	// Fill and draw the above objects
	g2.fill(e);
	g2.draw(e);
	g2.fill(c);
	g2.draw(c);
	g2.drawString("Score: "+Integer.toString(score), 20, 560);
	g2.drawString("Lives: "+Integer.toString(lives)+" Remaining", 650, 560);
	// Draw the Bricks
	for(Brick[] Row : bricks) {
	    for(Brick brick : Row) {
		if(brick.isRendered) {		    
		    g2.setColor(Color.red);		   
		    g2.fill(brick.br);
		    g2.setColor(Color.green);
		    g2.draw(brick.br);		    
		}
	    }
	}
    }
    
    public void resetPlayer() {
	// Resets the player position
	Dimension d = getSize();
	ball.x = d.width/2;
	ball.y = d.height - 65;
	ball.dx = 4.0;
	ball.dy = -4.0;
	player.x = d.width/2 - 50;
    }

    public void resetBricks() {
	// Resets the Bricks (Makes all them visible)
	for(Brick[] Row : bricks) {
	    for(Brick brick : Row) {
		brick.isRendered = true;
	    }
	}
    }
    
    public void resetStats() {
	// Resets the stats
	lives = 3;
	score = 0;
	isGameOver = false;
    }
    
    public void handleEvent(KeyEvent e) {
	// Handles the keyboard events
	double shift = 15;
	Dimension d = getSize();
	int k = e.getKeyCode();
	if((k == KeyEvent.VK_LEFT) || (k == KeyEvent.VK_KP_LEFT)) {
	    // If a left arrow key
	    if(player.x > 0) {
		player.x -= shift; // Move left
	    }
	}
	if((k == KeyEvent.VK_RIGHT) || (k == KeyEvent.VK_KP_RIGHT)) {
	    // If a right arrow key
	    if(player.x < (d.width - player.width - shift)) {
		player.x += shift;  // Move right
	    }
	}
    }

    public void initscene() {
	// Initialize the scene
	double x0 = 50.0, y0 = 100.0;
	Dimension d = getSize();
	ball  = new Ball(d.width/2, d.height - 60, 4.0, -4.0, 10.0);
	player = new Player(d.width/2 - 50, d.height - 50, 100, 10);	
	bricks = new Brick[3][];
	bricks[0] = new Brick[17]; // First row
	bricks[1] = new Brick[15]; // second row
	bricks[2] = new Brick[13]; // third row
	

	double xs = x0, ys = y0;
	for(int i=0; i<17; ++i) {  // Layout first row
	    bricks[0][i] = new Brick(xs, ys);
	    xs += Brick.width;
	}
	xs = x0 + Brick.width;
	ys = y0 + Brick.height;
	for(int i=0; i<15; ++i) { // Layout second row
	    bricks[1][i] = new Brick(xs, ys);
	    xs += Brick.width;
	}
	xs = x0 + (2.0 * Brick.width);
	ys = y0 + (2.0 * Brick.height);
	for(int i=0; i<13; ++i) { // Layout third row
	    bricks[2][i] = new Brick(xs, ys);
	    xs += Brick.width;
	}



	f1 = new Font("Consolas", Font.BOLD, 32);
	f2 = new Font("Consolas", Font.BOLD, 20);
	
    }

    private void manageScores() { // Manages the top scores
	int[] a  = new int[3];
	a[0] = prefs.getInt("#1", 0);
	a[1] = prefs.getInt("#2", 0);
	a[2] = prefs.getInt("#3", 0);
	if(score > a[0]) { // Sort them
	    prefs.putInt("#1", score);
	    prefs.putInt("#2", a[0]);
	    prefs.putInt("#3", a[1]);
	}else if(score > a[1]) {
	    prefs.putInt("#2", score);
	    prefs.putInt("#3", a[1]);
	}else if(score > a[2]) {
	    prefs.putInt("#3", score);
	}
    }
    
    public void resetScores() { // Resets the top scores
	prefs.putInt("#1", 0);
	prefs.putInt("#2", 0);
	prefs.putInt("#3", 0);
    }

    public void play() throws Exception{
	// The game play
	Dimension d = getSize();
	if(ball.x < 0) {
	    // Ball hits the left wall
	    ball.x = 0;
	    ball.dx = -ball.dx;
	}
	if(ball.y < 0) {
	    // Ball hits the upper wall
	    ball.y = 0;
	    ball.dy = -ball.dy;
	}
	if((ball.x + ball.radius) >= d.width) {
	    // Ball hits the right wall
	    ball.x = d.width - ball.radius;
	    ball.dx = -ball.dx;
	}
	if((ball.y + ball.radius) >= d.height) {
	    // Ball hits the bottom wall
	    lives -= 1; // decrement the number of lives
	    if(lives > 0) {
		resetPlayer(); // Reset and resume if the player still has lives
	    }else {
		// Declare the game to be over
		isGameOver = true;
		BallBreaker.tmr.stop();
		manageScores();
	    }	    
	}
	checkCollisionWithPlayer(); // Check collision with the player
	if(ball.y < 200) { // If the ball is near the bricks
	    for(Brick[] Row : bricks) {
		for(Brick brick : Row) {
		    checkCollisionWithBrick(brick); // Check for collison with each of the bricks
		}
	    }
	}
	// Move the ball
	ball.x += ball.dx;
	ball.y += ball.dy;
    }
    private boolean checkCollisionWithPlayer() { // checking and handling collision of ball with Player
	if(e.intersects(c)) { // If the ball collides
	    ball.dy = - Math.abs(ball.dy); // Bounce it
	    // Calculate impact distance (The distance from the point of impact to the center of the player block )
	    impact_distance = Point2D.distance(e.getCenterX(), e.getCenterY(), c.getCenterX(), c.getCenterY());
	    if(impact_distance >= 30.0) { // If Impact distance is > 30 that is
		ball.dx = -ball.dx;       // if the ball has hit the player at the edges reverse the direction  
	    }
	    ball.x += ball.dx;
	    ball.y += ball.dy;
	    return true;
	}
	return false;
    }
    private void checkCollisionWithBrick(Brick b) throws Exception { // Checks collisons with brick
	if(b.isRendered) { // If the brick was rendered on the screen
	    if(e.intersects(b.br)) { // And if collided
		try {
		    if(BallBreaker.isSoundOn) {
			BallBreaker.playClip();
		    }
		} catch(Exception ex) {
		    BallBreaker.isSoundOn = false;
		}
		++score; // Increment score
		if(score == 45) { // If score is 50 end the game
		    isGameOver = true;
		    BallBreaker.tmr.stop();
		    manageScores();
		}
		b.isRendered = false; // The brick should disappear
		// Determine on which of the brick has the impact occured
		int k = b.br.outcode(e.getCenterX(), e.getCenterY());
		if((k == Rectangle2D.OUT_LEFT) || (k == Rectangle2D.OUT_RIGHT)) { // Left or right side
		    ball.dx = -ball.dx;
		}else { // Top of bottom side of the brick
		    ball.dy = -ball.dy;
		}
	    }
	}
    }
}