package tebakAngka;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import programUtama.SharedVariable;
import serverSocket.Database;

public class GuessingNumber extends JFrame{
	int randomAngka;
	int lifed;
	int hasil;
	int scoreUpload;
	public static String playerAngka = SharedVariable.nama;
	public GuessingNumber(){
		setTitle("Tebak angka");
		setSize(900,120);
		setLayout (new GridLayout(3,3));
		setLocationRelativeTo(null);
		
		JTextField txtangka= new JTextField(20);
		JButton btntebak = new JButton("tebak");
		JButton NewGame = new JButton("New Game");
		JLabel angka = new JLabel("angka");
		JLabel life = new JLabel("0");
		JLabel masukan_angka = new JLabel("masukan angka");
		JLabel keterangan = new JLabel("Tebak angkanya");
		JLabel score = new JLabel ("Score:");
		JLabel angkascore = new JLabel ("0");
		
        NewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					Random rand = new Random();
					int randomedAngka = rand.nextInt(100) +1;
					randomAngka = randomedAngka;
					keterangan.setText("Angka sudah diset, silahkan tebak");
					life.setText("100");
					lifed = Integer.parseInt(life.getText());
					scoreUpload = 0;
				}
				catch(Exception e){
					e.printStackTrace();
				}
			}
        });
		
		btntebak.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
				lifed = Integer.parseInt(life.getText());
				hasil = Integer.parseInt(angkascore.getText());
				if(Integer.parseInt(life.getText())>0){
					int tebakan = Integer.parseInt(txtangka.getText());
					if(tebakan < randomAngka){
						keterangan.setText("Angka terlalu kecil, coba tebak lebih besar lagi.");
						lifed--;
						hasil ++;
						life.setText(String.valueOf(lifed));
						angkascore.setText(String.valueOf(hasil));
					}else if(tebakan > randomAngka){
						keterangan.setText("Angka terlalu besar, coba tebak lebih kecil lagi.");
						lifed--;
						hasil ++;
						life.setText(String.valueOf(lifed));
						angkascore.setText(String.valueOf(hasil));
				}else{
					keterangan.setText("Angka tebakan benar. Tebak angka berikutnya.");
					hasil = hasil + 10;
					Random rand = new Random();
					int randomedAngka = rand.nextInt(100) +1;
					randomAngka = randomedAngka;
					angkascore.setText(String.valueOf(hasil));
				}
				
				}
				else{
					life.setText("Nyawa Anda Sudah Habis");
					if(scoreUpload == 0){
						try{
							Database db = new Database();
							db.addScore(playerAngka, String.valueOf(hasil), "Angka Berapa?");
				    		String addr = "localhost";
				    		Socket s = new Socket(addr, 9090);
				    		PrintWriter out = new PrintWriter(
				    				s.getOutputStream(),true
				    				);
				    		out.println(playerAngka+" mengupload score Angka Berapa dengan Score:"+String.valueOf(hasil));
				    		}
							catch(Exception error){
								error.printStackTrace();
							}
						scoreUpload++;
					}
				}
				}
				catch(Exception e){
					e.printStackTrace();
				}
			}
		});
		
		add(NewGame);
		add(life);
		add(keterangan);
		
		add(masukan_angka);
		add(txtangka);
		add(btntebak);
		

		
		
		add (score);
		add(angkascore);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new GuessingNumber();
    }
}
