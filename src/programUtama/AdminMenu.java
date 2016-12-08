package programUtama;

import java.awt.Button;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class AdminMenu extends JFrame{
	String userOpen = "no";
	String highOpen = "no";
	public String adminName = SharedVariable.adminName;
	JTextField txt_infoAdmin;
	
	public AdminMenu(){
	setTitle("Admin Menu");
	setSize(450,300);
	setBounds(100, 100, 450, 300);
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	JPanel content = new JPanel();
	content.setBorder(new EmptyBorder(10,10, 10, 10));
	content.setLayout(new GridLayout(5, 5, 5, 5));
	
	txt_infoAdmin = new JTextField();
	txt_infoAdmin.setEditable(false);
	
	Button btn_userpass = new Button("Username Password Editor");
	btn_userpass.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			{
			 if(userOpen=="no") {
				 	AdminFormPlayer admin = new AdminFormPlayer();
				 	userOpen = "yes";
	                admin.addWindowListener(new WindowAdapter(){
	                    public void windowClosing(WindowEvent e) 
	                    {
	                    	userOpen = "no";
	                    }
	                });
	                }
	                else 
	                    JOptionPane.showMessageDialog(null, "Window Username Password Editor sudah terbuka.","Error",JOptionPane.ERROR_MESSAGE);  
	            }	
		}
	});
	
	Button btn_highscore = new Button("Highscore Editor");
	btn_highscore.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			{	
				{
					 if(highOpen=="no") {
						 	AdminFormHighscore score = new AdminFormHighscore();
						 	highOpen = "yes";
						 	score.addWindowListener(new WindowAdapter(){
			                    public void windowClosing(WindowEvent e) 
			                    {
			                    	highOpen = "no";
			                    }
			                });
			                }
			                else 
			                    JOptionPane.showMessageDialog(null, "Window Highscore Editor sudah terbuka.","Error",JOptionPane.ERROR_MESSAGE);  
			            }	
				}
		}
			
	});
	

	content.add(txt_infoAdmin);
	content.add(btn_userpass);
	content.add(btn_highscore);
	
	add(content);
	}
	public static void main(String[] args){
		AdminMenu frame = new AdminMenu();
		frame.setVisible(true);
	}
}
