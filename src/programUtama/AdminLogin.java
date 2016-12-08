package programUtama;
import serverSocket.Database;
import serverSocket.GameboxSocket;
import serverSocket.ThreadPlayer;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.*;

public class AdminLogin extends JFrame{
	public AdminLogin(){
	setTitle("Admin Login");
	setSize(300,300);
	setLocationRelativeTo(null);
	
	JPanel panel1 = new JPanel(new GridLayout(1,1));
	JPanel panel2 = new JPanel(new GridLayout(2,2));
	JPanel panel3 = new JPanel(new GridLayout(1,1));
	
	JLabel lblLoginAdmin = new JLabel("Login Admin GameBox" , SwingConstants.CENTER);
	JLabel lbl_username = new JLabel("Username");
	JLabel lbl_password = new JLabel("Password");
	JTextField txt_username = new JTextField();
	JPasswordField txt_password = new JPasswordField();
	
	panel1.add(lblLoginAdmin);
	panel2.add(lbl_username);
	panel2.add(txt_username);
	panel2.add(lbl_password);
	panel2.add(txt_password);
	
	JButton btn_adminLog = new JButton("Login Sebagai Admin");
	btn_adminLog.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			GameboxSocket.setLoginType = "admin";
			try{
				SharedVariable.adminName = txt_username.getText();
				Database db = new Database();
				String username = txt_username.getText();
				String password = txt_password.getText();
				int dataPlayer;
				dataPlayer = db.loginCheckAdmin(username,password);
				if (dataPlayer == 1){
					
					String addr = "localhost";
					Socket s = new Socket(addr, 9090);
					PrintWriter out = new PrintWriter(
							s.getOutputStream(),true
							);
					out.println(username+" logged");
					BufferedReader br = new BufferedReader(
							new InputStreamReader(
									s.getInputStream()
									)
							);
					String answer = br.readLine();
					JOptionPane.showMessageDialog(null, answer);
					AdminMenu framed = new AdminMenu();
					framed.setVisible(true);
					framed.txt_infoAdmin.setText("Selamat datang di Admin Control, "+framed.adminName+"! Silahkan pilih Opsi!");
					dispose();
				}
				else {
					JOptionPane.showMessageDialog(null, "Login Error, Username/Password tidak match");
				}
			}
			catch(Exception e){
				JOptionPane.showMessageDialog(null, "Login Error");
				e.printStackTrace();
			}
		}
	});
	
	panel3.add(btn_adminLog);
	
	setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
	add(panel1);
	add(panel2);
	add(panel3);
	setVisible(true);
}

public static void main(String[] args){
	new AdminLogin();
}
}

