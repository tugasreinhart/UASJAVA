package programUtama;

import java.awt.Button;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;
import javax.swing.border.*;

import Pacman.pacman;
import Snake.Snake;
import breakBreaker.BallBreaker;
import tangkapBola.Game;
import tebakAngka.GuessingNumber;

public class PlayerMenu extends JFrame{
	public JTextField txt_infoPlayer;
	public static String nama = SharedVariable.nama;
	String pacmanOpen = "no";
	String snakeOpen = "no";
	String bbOpen = "no";
	//String tkBola = "no";
	String tebakOpem = "no";
	String scoreOpen = "no";
	public PlayerMenu() {
		setTitle("Menu Utama");
		setSize(450,300);
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		JPanel content = new JPanel();
		content.setBorder(new EmptyBorder(10,10, 10, 10));
		content.setLayout(new GridLayout(6, 6, 6, 6));
		
		txt_infoPlayer = new JTextField();
		Button btn_pacman = new Button("Pac-Man");
		Button btn_snake = new Button("Snake");
		Button btn_breakb = new Button("Break Breaker");
		Button btn_tangkapb = new Button("Tangkap Bola");
		Button btn_tebakAngka = new Button("Angka Berapa?");
		
		btn_pacman.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	
						 if(pacmanOpen=="no") {
							 	pacman pacgame = new pacman();
							 	SharedVariable.nama = nama;
							 	System.out.println(nama);
							 	pacgame.setVisible(true);
							 	pacmanOpen = "yes";
							 	pacgame.addWindowListener(new WindowAdapter(){
				                    public void windowClosing(WindowEvent e) 
				                    {
				                    	pacmanOpen = "no";
				                    }
				                });
				                }
				                else 
				                    JOptionPane.showMessageDialog(null, "Game Pacman sudah terbuka.","Error",JOptionPane.ERROR_MESSAGE);  
				            }
		});
		
		btn_snake.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	
						 if(snakeOpen=="no") {
							 	Snake senekGame = new Snake();
							 	SharedVariable.nama = nama;
							 	System.out.println(nama);
							 	senekGame.setVisible(true);
							 	snakeOpen = "yes";
							 	senekGame.addWindowListener(new WindowAdapter(){
				                    public void windowClosing(WindowEvent e) 
				                    {
				                    	snakeOpen = "no";
				                    }
				                });
				                }
				                else 
				                    JOptionPane.showMessageDialog(null, "Game Snake sudah terbuka.","Error",JOptionPane.ERROR_MESSAGE);  
				            }
		});
		
		btn_breakb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	
						 if(bbOpen=="no") {
							 	BallBreaker ballGame = new BallBreaker();
							 	SharedVariable.nama = nama;
							 	System.out.println(nama);
							 	ballGame.setVisible(true);
							 	bbOpen = "yes";
							 	ballGame.addWindowListener(new WindowAdapter(){
				                    public void windowClosing(WindowEvent e) 
				                    {
				                    	bbOpen = "no";
				                    }
				                });
				                }
				                else 
				                    JOptionPane.showMessageDialog(null, "Game Break Breaker sudah terbuka.","Error",JOptionPane.ERROR_MESSAGE);  
				            }
		});
		
		//btn_tangkapb.addActionListener(new ActionListener() {
		//	public void actionPerformed(ActionEvent arg0) {	
		//					 	Game tangkapGame = new Game();
		//					 	SharedVariable.nama = nama;
		//					 	System.out.println(nama);
		//	}}
		//);
		
		btn_tebakAngka.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	
						 if(tebakOpem=="no") {
							 	GuessingNumber angkaan = new GuessingNumber();
							 	SharedVariable.nama = nama;
							 	System.out.println(nama);
							 	angkaan.setVisible(true);
							 	tebakOpem = "yes";
							 	angkaan.addWindowListener(new WindowAdapter(){
				                    public void windowClosing(WindowEvent e) 
				                    {
				                    	tebakOpem = "no";
				                    }
				                });
				                }
				                else 
				                    JOptionPane.showMessageDialog(null, "Game Angka Berapa sudah terbuka.","Error",JOptionPane.ERROR_MESSAGE);  
				            }
		});
		
		
		Button btn_score = new Button("Lihat Highscore");
		btn_score.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	
						 if(scoreOpen=="no") {
							 	CheckHighscore skor = new CheckHighscore();
							 	skor.setVisible(true);
							 	scoreOpen = "yes";
							 	skor.addWindowListener(new WindowAdapter(){
				                    public void windowClosing(WindowEvent e) 
				                    {
				                    	scoreOpen = "no";
				                    }
				                });
				                }
				                else 
				                    JOptionPane.showMessageDialog(null, "Highscore sudah terbuka.","Error",JOptionPane.ERROR_MESSAGE);  
				            }
		});

		content.add(txt_infoPlayer);
		content.add(btn_pacman);
		content.add(btn_snake);
		content.add(btn_breakb);
		content.add(btn_tebakAngka);
		content.add(btn_score);
		
		add(content);
	}
}
