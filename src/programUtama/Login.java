package programUtama;
import serverSocket.Database;
import serverSocket.GameboxSocket;
import serverSocket.ThreadPlayer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.*;

public class Login extends JFrame {
	String signupOpen = "no";
	String adminOpen = "no";
	
	public Login() {
		setTitle("Login");
		setSize(300,300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JPanel panel1 = new JPanel(new GridLayout(1,1));
		JPanel panel2 = new JPanel(new GridLayout(2,2));
		JPanel panel3 = new JPanel(new GridLayout(3,2));
		
		JLabel lblLoginGamebox = new JLabel("Login GameBox" , SwingConstants.CENTER);
		JLabel lbl_username = new JLabel("Username");
		JLabel lbl_password = new JLabel("Password");
		JTextField txt_username = new JTextField();
		JPasswordField txt_password = new JPasswordField();
		
		
		panel1.add(lblLoginGamebox);
		
		panel2.add(lbl_username);
		panel2.add(txt_username);
		panel2.add(lbl_password);
		panel2.add(txt_password);
		
		JButton btn_login = new JButton("Login");
		JButton btn_signup = new JButton("Signup");
		JButton btn_adminLog = new JButton("Login Sebagai Admin");
		
		btn_login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try{
					Database db = new Database();
					String username = txt_username.getText();
					String password = txt_password.getText();
					int dataPlayer;
					dataPlayer = db.loginCheck(username,password);
					if (dataPlayer == 1){
						String addr = "localhost";
						Socket s = new Socket(addr, 9090);
						PrintWriter out = new PrintWriter(
								s.getOutputStream(),true
								);
						out.println("User "+username+" logged");
						BufferedReader br = new BufferedReader(
								new InputStreamReader(
										s.getInputStream()
										)
								);
						String answer = br.readLine();
						JOptionPane.showMessageDialog(null, answer);
						dispose();
						PlayerMenu frame = new PlayerMenu();
						SharedVariable.nama = username;
						frame.nama = SharedVariable.nama;
						frame.txt_infoPlayer.setText("Selamat datang di Gamebox, "+frame.nama+"! Silahkan pilih game favoritmu!");
						frame.setVisible(true);
						Login frame2 = new Login ();
						frame2.setVisible(false);
					}
					else {
						JOptionPane.showMessageDialog(null, "Login Error");
					}
				}
				catch(Exception e){
					JOptionPane.showMessageDialog(null, "Login Error");
					e.printStackTrace();
				}
			}
		});
		
		btn_signup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	
				 if(signupOpen=="no") {
		                Signup Autor = new Signup();
		                signupOpen = "yes";
		                Autor.addWindowListener(new WindowAdapter(){
		                    public void windowClosing(WindowEvent e) 
		                    {
		                    	signupOpen = "no";
		                    }
		                });
		                }
		                else 
		                    JOptionPane.showMessageDialog(null, "Window Signup sudah terbuka.","Error",JOptionPane.ERROR_MESSAGE);  
		            }	
		});
		
		btn_adminLog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				{
					 if(adminOpen=="no") {
						 	AdminLogin admin = new AdminLogin();
			                adminOpen = "yes";
			                admin.addWindowListener(new WindowAdapter(){
			                    public void windowClosing(WindowEvent e) 
			                    {
			                    	adminOpen = "no";
			                    }
			                });
			                }
			                else 
			                    JOptionPane.showMessageDialog(null, "Window Admin Login sudah terbuka.","Error",JOptionPane.ERROR_MESSAGE);  
			            }	
			}
		});
		
		panel3.add(btn_login);
		panel3.add(btn_signup);
		panel3.add(btn_adminLog);
		
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		add(panel1);
		add(panel2);
		add(panel3);
		setVisible(true);
	}
	
	public static void main(String[] args){
		new Login();
	}
}

