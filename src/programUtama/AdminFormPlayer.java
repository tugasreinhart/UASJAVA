package programUtama;
import serverSocket.Database;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.*;

public class AdminFormPlayer extends JFrame {
	public AdminFormPlayer() {
		setTitle("Admin Control Player");
		setSize(500,400);
		setLocationRelativeTo(null);
		
		JPanel panel1 = new JPanel(new GridLayout(1,1));

		Border loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		panel1.setBorder(loweredetched);
		
		JPanel panel2 = new JPanel(new GridLayout(5,2));
		TitledBorder EmployeeRec;
		EmployeeRec = BorderFactory.createTitledBorder("Username Password Editor");
		panel2.setBorder(EmployeeRec);
		
		//JPanel panel3 = new JPanel(new FlowLayout(FlowLayout.CENTER,20,5));
		JPanel panel3 = new JPanel(new GridLayout(1,10,10,10));
		panel3.setBorder(loweredetched);
		
		//Panel 1 Component
		
		DefaultTableModel defaultTableModel = new DefaultTableModel();
		Vector<Object> columnNamesPlayer = new Vector<Object>();
		columnNamesPlayer.add("Username");
		columnNamesPlayer.add("Password");
		
		defaultTableModel.setDataVector(null, columnNamesPlayer);
		Vector<Vector<Object>> dataPlayer = new Vector<Vector<Object>>();
		
		try{
			Database db = new Database();
			dataPlayer = db.selectAllPlayer();
			defaultTableModel.setDataVector(dataPlayer, columnNamesPlayer);
			
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("ERROR SELECT PLAYER");
		}
		
		JScrollPane skrolpen = new JScrollPane();
		skrolpen.setBounds(10, 11, 464, 260);
		panel1.add(skrolpen);
		
		JTable table = new JTable(defaultTableModel);
		skrolpen.setViewportView(table);
		
		//Panel 2 Component
		JLabel lbl_id = new JLabel("Username");
		JLabel lbl_fullname = new JLabel("Password");
		
		JTextField txt_username = new JTextField();
		JTextField txt_password = new JTextField();
		
		panel2.add(lbl_id);
		panel2.add(txt_username);
		panel2.add(lbl_fullname);
		panel2.add(txt_password);
		

		//Panel 3 Component
		JButton btn_addnew = new JButton("Add New");
		btn_addnew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String username = txt_username.getText();
				String password = txt_password.getText();
				
				try{
					Database db = new Database();
					db.addPlayer(username, password);
					
					Vector<Vector<Object>> player = new Vector<Vector<Object>>();
					player = db.selectAllPlayer();
					defaultTableModel.setDataVector(player, columnNamesPlayer);
					System.out.println("Data Berhasil dimasukan");
						
				}catch(Exception e2){
					e2.printStackTrace();
					System.out.println("Ada kesalahan");
				}
			}
		});
		
		JButton btn_update = new JButton("Update");
		btn_update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = txt_username.getText();
				String password = txt_password.getText();
   
				try{
					Database db = new Database();
					db.updatePlayer(username, password);
					
					Vector<Vector<Object>> player = new Vector<Vector<Object>>();
					player = db.selectAllPlayer();
					defaultTableModel.setDataVector(player, columnNamesPlayer);
					System.out.println("Data Berhasil diupdate");
						
				}catch(Exception e2){
					e2.printStackTrace();
					System.out.println("Ada kesalahan Update");
				}
			}
		});
		
		JButton btn_delete = new JButton("Delete");
		btn_delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String username = txt_username.getText();

				try{
					Database db = new Database();
					db.deletePlayer(username);
					
					Vector<Vector<Object>> player = new Vector<Vector<Object>>();
					player = db.selectAllPlayer();
					defaultTableModel.setDataVector(player, columnNamesPlayer);
					System.out.println("Data Berhasil dihapus");
						
				}catch(Exception e2){
					e2.printStackTrace();
					System.out.println("Ada kesalahan");
				}
			}
		});
		
		JButton btn_search = new JButton("Search");
		btn_search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = "%"+txt_username.getText()+"%";
				  
				try{
					Database db = new Database();
					Vector<Vector<Object>> player = new Vector<Vector<Object>>();
					player = db.findPlayer(username);
					defaultTableModel.setDataVector(player, columnNamesPlayer);
					
						
				}catch(Exception e2){
					e2.printStackTrace();
					System.out.println("Ada kesalahan");
				}
			}
		});
		
		JButton btn_reload = new JButton("Reload");
		btn_reload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Vector<Vector<Object>> dataPlayer = new Vector<Vector<Object>>();
				try{
					Database db = new Database();
					dataPlayer = db.selectAllPlayer();
					defaultTableModel.setDataVector(dataPlayer, columnNamesPlayer);
					System.out.println("Reloaded");
				}catch(Exception e2){
					e2.printStackTrace();
					System.out.println("Ada kesalahan");
				}
			}
		});
		
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
                if(table.getSelectedRow()>-1) {       	
                    int selectedRowIndex = table.getSelectedRow();
                    txt_username.setText(table.getValueAt(selectedRowIndex, 0).toString());
                    txt_password.setText(table.getValueAt(selectedRowIndex, 1).toString());
                }
            }
	 });
		
		
		panel3.add(btn_addnew);
		panel3.add(btn_update);
		panel3.add(btn_delete);
		panel3.add(btn_search);
		panel3.add(btn_reload);
		
		//setLayout(new GridLayout(2,1));
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		getContentPane().add(panel1);
		getContentPane().add(panel2);
		getContentPane().add(panel3);
		
		setVisible(true);
	}
	
	public static void main(String[] args){
		new AdminFormPlayer();
	}
}
