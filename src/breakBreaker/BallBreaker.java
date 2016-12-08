package breakBreaker;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.Window;
import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;

import javax.accessibility.Accessible;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.Timer;

import programUtama.SharedVariable;

public class BallBreaker extends JFrame{
    private static File soundFile = new File("impact.wav");
    public static boolean isSoundOn = true;
    public String playerName = SharedVariable.nama;
    
    public BallBreaker(){
    	setTitle("Ball Breaker");
    	final World w = new World();
    	System.out.println(playerName);
    	w.playerNameBB = playerName;
    	add(w);
    	setSize(850,600);
    	setResizable(true); // Frame not resizable
    	setLocationByPlatform(true); // Allow the platform to position the frame
    	KeyMon k = new KeyMon(w);
    	addKeyListener(k);
    	setVisible(true);
    	w.initscene(); // Initialize the scene
    	
    	Action playAndUpdateAction = new AbstractAction() {
    		/**
    		 * 
    		 */
    		private static final long serialVersionUID = 1L;

    		public void actionPerformed(ActionEvent e) {
    		    try {
    			w.play();
    		    }catch(Exception ex) {
    			ex.printStackTrace();
    		    }
    		    w.repaint();		
    		}
    	    };
    	tmr = new Timer(20, playAndUpdateAction); // Fire the above action event every 20 ms
    	tmr.start(); // Let's get it started!
    }
    
    
    public static void main(String[] args) {
	
    }
    public static Timer tmr; // Timer object

    public static void playClip() //Plays the sound clip
	throws IOException, UnsupportedAudioFileException,
	       LineUnavailableException {
	AudioInputStream auIn = null;
	Clip clip = null;
	try {
	    auIn = AudioSystem.getAudioInputStream(soundFile); // May fail in some cases
	    clip = AudioSystem.getClip();                      //  like when audio is in use by other programs
	    clip.open(auIn);
	    clip.start();	
	} finally {
	    if(auIn != null) {
		auIn.close();
	    }
	}
    }

}
