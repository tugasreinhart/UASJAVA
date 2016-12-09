package programUtama;

import java.awt.FlowLayout;
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
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import serverSocket.Database;

public class CheckHighscore extends JFrame{
	public CheckHighscore(){
		setTitle("Highscore");
		setSize(500,400);
		setLocationRelativeTo(null);
		
		JPanel panelScore = new JPanel(new GridLayout(1,1));
		Border loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		panelScore.setBorder(loweredetched);
		JPanel panelButton = new JPanel(new FlowLayout());
		
		//Panel 1 Component
		DefaultTableModel defaultTableModel = new DefaultTableModel();
		Vector<Object> columnNamesHighscore = new Vector<Object>();
		columnNamesHighscore.add("Username");
		columnNamesHighscore.add("Highscore");
		columnNamesHighscore.add("Game");
		defaultTableModel.setDataVector(null, columnNamesHighscore);
		Vector<Vector<Object>> dataHighscore = new Vector<Vector<Object>>();
		try{
			Database db = new Database();
			String game = "Snake";
			dataHighscore = db.findScoreGame(game);
			defaultTableModel.setDataVector(dataHighscore, columnNamesHighscore);
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Error Select Highscore");
		}
		
		JScrollPane skrolpen = new JScrollPane();
		skrolpen.setBounds(10, 11, 464, 260);
		panelScore.add(skrolpen);
		JTable table = new JTable(defaultTableModel);
		skrolpen.setViewportView(table);
		
		JButton btn_snake = new JButton("Snake");
		JButton btn_pacman = new JButton("Pacman");
		JButton btn_breakb = new JButton("Break Breaker");
		JButton btn_tebaka = new JButton("Angka Berapa");
		
		btn_snake.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String game = "Snake";
				try{
					Vector<Vector<Object>> dataHighscore = new Vector<Vector<Object>>();
					Database db = new Database();
					dataHighscore = db.findScoreGame(game);
					defaultTableModel.setDataVector(dataHighscore, columnNamesHighscore);
				}catch(Exception e2){
					e2.printStackTrace();
					System.out.println("Ada kesalahan");
				}
			}
		});
		
		btn_pacman.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String game = "Pacman";
				try{
					Vector<Vector<Object>> dataHighscore = new Vector<Vector<Object>>();
					Database db = new Database();
					dataHighscore = db.findScoreGame(game);
					defaultTableModel.setDataVector(dataHighscore, columnNamesHighscore);
				}catch(Exception e2){
					e2.printStackTrace();
					System.out.println("Ada kesalahan");
				}
			}
		});
		
		btn_breakb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String game = "Break Breaker";
				try{
					Vector<Vector<Object>> dataHighscore = new Vector<Vector<Object>>();
					Database db = new Database();
					dataHighscore = db.findScoreGame(game);
					defaultTableModel.setDataVector(dataHighscore, columnNamesHighscore);
				}catch(Exception e2){
					e2.printStackTrace();
					System.out.println("Ada kesalahan");
				}
			}
		});
		
		btn_tebaka.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String game = "Angka Berapa?";
				try{
					Vector<Vector<Object>> dataHighscore = new Vector<Vector<Object>>();
					Database db = new Database();
					dataHighscore = db.findScoreGame(game);
					defaultTableModel.setDataVector(dataHighscore, columnNamesHighscore);
				}catch(Exception e2){
					e2.printStackTrace();
					System.out.println("Ada kesalahan");
				}
			}
		});
		
		panelButton.add(btn_snake);
		panelButton.add(btn_pacman);
		panelButton.add(btn_breakb);
		panelButton.add(btn_tebaka);
		
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		getContentPane().add(panelScore);
		getContentPane().add(panelButton);
		
		setVisible(true);
	}

	public static void main(String[] args){
		new CheckHighscore();
	}

}
