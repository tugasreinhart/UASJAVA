package breakBreaker;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class KeyMon implements KeyListener { // Monitors keyboard events
    public KeyMon(World w) {
	this.w = w;
    }
    public void keyPressed(KeyEvent e) {
	if(!w.isGameOver) { // If the game is running
	    if(e.getKeyCode() == KeyEvent.VK_SPACE) { // If SPACE is pressed
		if(BallBreaker.tmr.isRunning()) { // If the game is running pause it
		    BallBreaker.tmr.stop();
		} else {
		    BallBreaker.tmr.start(); // If not resume it
		}
		return;
	    }
	}else{ // If no game is running
	    if(e.getKeyCode() == KeyEvent.VK_S) { // If 'S' is pressed
		w.resetPlayer(); // Reset Players
		w.resetBricks(); // Reset Bricks
		w.resetStats();  // Reset Stats
		BallBreaker.tmr.start(); // Start the game
	    }
	    if(e.getKeyCode() == KeyEvent.VK_R) {
		w.resetScores();
		w.repaint();
	    }
	}
	if(BallBreaker.tmr.isRunning()) { // If the game is running
	    w.handleEvent(e); // Handle the "Left" and "Right" arrow keys
	}
    }
    public void keyTyped(KeyEvent e) {
	// Implemented
    }
    public void keyReleased(KeyEvent e) {
	// Implemented
    }
    public World w;
}