package programUtama;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import serverSocket.Database;

public class AdminFormHighscore extends JFrame{
	public AdminFormHighscore(){
	setTitle("Admin Control Highscore");
	setSize(500,400);
	setLocationRelativeTo(null);
	
	JPanel panel1 = new JPanel(new GridLayout(1,1));
	Border loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
	panel1.setBorder(loweredetched);
	
	JPanel panel2 = new JPanel(new GridLayout(5,2));
	TitledBorder EmployeeRec;
	EmployeeRec = BorderFactory.createTitledBorder("Highscore Editor");
	panel2.setBorder(EmployeeRec);
	
	JPanel panel3 = new JPanel(new GridLayout(1,10,10,10));
	panel3.setBorder(loweredetched);
	
	//Panel 1 Component
	
	DefaultTableModel defaultTableModel = new DefaultTableModel();
	Vector<Object> columnNamesHighscore = new Vector<Object>();
	columnNamesHighscore.add("Input ID");
	columnNamesHighscore.add("Username");
	columnNamesHighscore.add("Highscore");
	columnNamesHighscore.add("Game");
	
	defaultTableModel.setDataVector(null, columnNamesHighscore);
	Vector<Vector<Object>> dataHighscore = new Vector<Vector<Object>>();
	
	try{
		Database db = new Database();
		dataHighscore = db.selectAllHighscore();
		defaultTableModel.setDataVector(dataHighscore, columnNamesHighscore);
		
	}catch(Exception e){
		e.printStackTrace();
		System.out.println("Error Select Highscore");
	}
	
	JScrollPane skrolpen = new JScrollPane();
	skrolpen.setBounds(10, 11, 464, 260);
	panel1.add(skrolpen);
	
	JTable table = new JTable(defaultTableModel);
	skrolpen.setViewportView(table);
	
	//Panel 2 Component
	JLabel lbl_inputid = new JLabel("Input ID");
	JLabel lbl_username = new JLabel("Username");
	JLabel lbl_highscore = new JLabel("Highscore");
	JLabel lbl_game = new JLabel("Game");
	
	JTextField txt_inputid = new JTextField();
	JTextField txt_username = new JTextField();
	JTextField txt_highscore = new JTextField();
	JTextField txt_game = new JTextField();
	
	panel2.add(lbl_inputid);
	panel2.add(txt_inputid);
	panel2.add(lbl_username);
	panel2.add(txt_username);
	panel2.add(lbl_highscore);
	panel2.add(txt_highscore);
	panel2.add(lbl_game);
	panel2.add(txt_game);
	

	//Panel 3 Component
	JButton btn_addnew = new JButton("Add New");
	btn_addnew.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			String username = txt_username.getText();
			String highscore = txt_highscore.getText();
			String game = txt_game.getText();
			
			try{
				Database db = new Database();
				db.addScore(username, highscore ,game);
				
				Vector<Vector<Object>> score = new Vector<Vector<Object>>();
				score = db.selectAllHighscore();
				defaultTableModel.setDataVector(score, columnNamesHighscore);
				System.out.println("Score Uploaded");
					
			}catch(Exception e2){
				e2.printStackTrace();
				System.out.println("Ada kesalahan");
			}
		}
	});
	
	JButton btn_update = new JButton("Update");
	btn_update.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			String id = txt_inputid.getText();
			String username = txt_username.getText();
			String highscore = txt_highscore.getText();
			String game = txt_game.getText();

			try{
				Database db = new Database();
				db.updateScore(id,username, highscore,game);
				
				Vector<Vector<Object>> score = new Vector<Vector<Object>>();
				score = db.selectAllHighscore();
				defaultTableModel.setDataVector(score, columnNamesHighscore);
				System.out.println("Score Updated");
					
			}catch(Exception e2){
				e2.printStackTrace();
				System.out.println("Ada kesalahan Update");
			}
		}
	});
	
	JButton btn_delete = new JButton("Delete");
	btn_delete.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			String id = txt_inputid.getText();

			try{
				Database db = new Database();
				db.deleteScore(id);
				
				Vector<Vector<Object>> score = new Vector<Vector<Object>>();
				score = db.selectAllHighscore();
				defaultTableModel.setDataVector(score, columnNamesHighscore);
				System.out.println("Delete Success");
					
			}catch(Exception e2){
				e2.printStackTrace();
				System.out.println("Ada kesalahan");
			}
		}
	});
	
	JButton btn_search = new JButton("Search");
	btn_search.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			String id = "%"+txt_inputid.getText()+"%";
			String username = "%"+txt_username.getText()+"%";
			String highscore = "%"+txt_highscore.getText()+"%";
			String game = "%"+txt_game.getText()+"%";
			try{
				Database db = new Database();
				Vector<Vector<Object>> score = new Vector<Vector<Object>>();
				score = db.findScore(id,username,highscore,game);
				defaultTableModel.setDataVector(score, columnNamesHighscore);
				System.out.println("Search Success");
					
			}catch(Exception e2){
				e2.printStackTrace();
				System.out.println("Ada kesalahan");
			}
		}
	});
	
	JButton btn_reload = new JButton("Reload");
	btn_reload.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			defaultTableModel.setDataVector(null, columnNamesHighscore);
			Vector<Vector<Object>> dataHighscore = new Vector<Vector<Object>>();
			try{
				Database db = new Database();
				dataHighscore = db.selectAllHighscore();
				defaultTableModel.setDataVector(dataHighscore, columnNamesHighscore);
				
			}catch(Exception ex){
				ex.printStackTrace();
				System.out.println("Error Select Highscore");
			}
		}
	});
	
	table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
        public void valueChanged(ListSelectionEvent event) {
            if(table.getSelectedRow()>-1) {       	
                int selectedRowIndex = table.getSelectedRow();
                txt_inputid.setText(table.getValueAt(selectedRowIndex, 0).toString());
                txt_username.setText(table.getValueAt(selectedRowIndex, 1).toString());
                txt_highscore.setText(table.getValueAt(selectedRowIndex, 2).toString());
                txt_game.setText(table.getValueAt(selectedRowIndex, 3).toString());
            }
        }
 });
	
	
	panel3.add(btn_addnew);
	panel3.add(btn_update);
	panel3.add(btn_delete);
	panel3.add(btn_search);
	panel3.add(btn_reload);
	
	getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
	getContentPane().add(panel1);
	getContentPane().add(panel2);
	getContentPane().add(panel3);
	
	setVisible(true);
}

public static void main(String[] args){
	new AdminFormHighscore();
}
}
