package Pacman;

import java.awt.EventQueue;
import javax.swing.JFrame;

import programUtama.SharedVariable;

public class pacman extends JFrame {
	public String playerName = SharedVariable.nama;
	private int pacmanx, pacmany, pacmandx, pacmandy;
	private final short leveldata[] = {
		    19, 26, 26, 26, 18, 18, 18, 18
		};
	
	
    public pacman() {
        
        initUI();
    }
    
    private void initUI() {
        
        add(new Board());
        setTitle("Pacman");
        setSize(380, 420);
        setLocationRelativeTo(null);
        setVisible(true);        
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                pacman ex = new pacman();
                ex.setVisible(true);
            }
        });
    }
}