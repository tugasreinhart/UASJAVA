package programUtama;
import serverSocket.Database;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

import javax.swing.*;


public class Signup extends JFrame{
	public Signup(){
		setTitle("Signup");
		setSize(300,300);
		setLocationRelativeTo(null);
		
		JPanel panel1 = new JPanel(new GridLayout(1,1));
		JPanel panel2 = new JPanel(new GridLayout(3,2));
		JPanel panel3 = new JPanel(new GridLayout(1,1));
		
		JLabel lblSignUp = new JLabel("Sign Up GameBox" , SwingConstants.CENTER);
		JLabel lbl_username = new JLabel("Username");
		JLabel lbl_password = new JLabel("Password");
		JLabel lbl_passwordRe = new JLabel("Re-Enter Password");
		JTextField txt_username = new JTextField();
		JPasswordField txt_password = new JPasswordField();
		JPasswordField txt_passwordRe = new JPasswordField();
		
		panel1.add(lblSignUp);
		
		panel2.add(lbl_username);
		panel2.add(txt_username);
		panel2.add(lbl_password);
		panel2.add(txt_password);
		panel2.add(lbl_passwordRe);
		panel2.add(txt_passwordRe);
		
		JButton btn_signup = new JButton("Signup");
		
		btn_signup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String a = txt_password.getText();
				String b = txt_passwordRe.getText();
				Boolean x = (a.equals(b));
				System.out.println(x);
				if(x){
					String username = txt_username.getText();
					String password = txt_passwordRe.getText();
					try{
						Database db = new Database();
						db.addPlayer(username, password);
						System.out.println("Sign Up Berhasil. Silahkan Login.");
						JOptionPane.showMessageDialog(null, "Sign Up Berhasil. Silahkan Login.");
						dispose();
					}catch(Exception e2){
						e2.printStackTrace();
						System.out.println("Username sudah terpakai, silahkan ubah kembali");
						JOptionPane.showMessageDialog(null, "Error, Username sudah terpakai atau tidak bisa menyambung keserver.");
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "Password tidak match, Silahkan ubah kembali");
				}

			}
		});
		
		
		panel3.add(btn_signup);
		
		
		
		//setLayout(new GridLayout(2,1));
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		add(panel1);
		add(panel2);
		add(panel3);
		setVisible(true);
	}
	
	public static void main(String[] args){
		new Signup();
	}
}
