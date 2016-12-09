package serverSocket;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JOptionPane;

public class Database {
private Connection conn;
	
	public Database() throws Exception{
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/gamebox",
				"root",
				""
				);
	}
	
	public boolean isConnected(){
		return (conn != null);
	}
	
	public Connection getConn() {
		return conn;
	}
	public void close() throws SQLException{
		conn.close();
		
	}
	
	//
	//Query Database untuk GameBox User/PLayer
	//
	public Vector<Vector<Object>> selectAllPlayer() throws SQLException {
        Statement stmt = this.conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM PLAYER");
        Vector<Vector<Object>> data = new Vector<Vector<Object>>(); // return the resultset as Vector
        while (rs.next()) {
            Vector<Object> v = new Vector<Object>();
            v.add(rs.getString("USERNAME"));
            v.add(rs.getString("PASSWORD"));
            data.add(v);
        }
        stmt.close();
        return data;
    }
	
	
	  public int loginCheck(String username, String password) throws SQLException {
	         String query = "SELECT * FROM PLAYER WHERE username = ? AND password = ?";
	         PreparedStatement pst = conn.prepareStatement(query);
	         pst.setString(1, username);
	         pst.setString(2, password);
	         
	         ResultSet rs = pst.executeQuery();
	         int count = 0;
	         while(rs.next()){
	        	 count=count+1;
	         }
	         return count;
	     }
	  
	  public String loginGetName(String username, String password) throws SQLException {
	         String query = "SELECT USERNAME FROM PLAYER WHERE username = ? AND password = ?";
	         PreparedStatement pst = conn.prepareStatement(query);
	         pst.setString(1, username);
	         ResultSet rs = pst.executeQuery();
	         String nama = rs.getString("USERNAME");
	         return nama;
	     }
	
	  public void addPlayer(String username,String password) throws SQLException{
		   String query = "INSERT INTO PLAYER(USERNAME, PASSWORD) VALUES (?,?)";
		   PreparedStatement pst = conn.prepareStatement(query);
		   pst.setString(1, username);
		   pst.setString(2, password);
		   pst.execute();
		   JOptionPane.showMessageDialog(null, "Success Insert Player");
		   pst.close();
		  }
	
	  public void updatePlayer (String username,String password) throws SQLException{
		     String query = "UPDATE player SET PASSWORD = ? WHERE USERNAME = ?";
		     PreparedStatement pst = conn.prepareStatement(query);
		     pst.setString(1, password);
		     pst.setString(2, username);
		     pst.execute();
		     JOptionPane.showMessageDialog(null, "Success Update Password Player");
		     pst.close();
		  }
	
	  public Vector<Vector<Object>> findPlayer(String username) throws SQLException {
	         Statement stmt = this.conn.createStatement();
	         String query = "SELECT * FROM player WHERE USERNAME LIKE ?";
	         PreparedStatement pst = conn.prepareStatement(query);
	         pst.setString(1, username);

	         ResultSet rs = pst.executeQuery();
	         Vector<Vector<Object>> data = new Vector<Vector<Object>>(); // return the resultset as Vector
	         while (rs.next()) {
	             Vector<Object> v = new Vector<Object>();
	             v.add(rs.getString("USERNAME"));
	             v.add(rs.getString("PASSWORD"));
	             data.add(v);
	         }
	         stmt.close();
	         return data;
	     }
	  
	  public void deletePlayer(String username)throws SQLException{
		   String query = "DELETE FROM PLAYER WHERE USERNAME=?";
		   PreparedStatement pst = conn.prepareStatement(query);
		   pst.setString(1, username);
		   pst.execute();
		   JOptionPane.showMessageDialog(null, "Success Delete Player");
		   pst.close();
		  }
	  
		//
		//Query Database Admin Login dan Control Highscore GameBox
		//
	  public int loginCheckAdmin(String username, String password) throws SQLException {
	         String query = "SELECT * FROM ADMIN WHERE username = ? AND password = ?";
	         PreparedStatement pst = conn.prepareStatement(query);
	         pst.setString(1, username);
	         pst.setString(2, password);
	         
	         ResultSet rs = pst.executeQuery();
	         int count = 0;
	         while(rs.next()){
	        	 count=count+1;
	         }
	         return count;
	     }
	  
		public Vector<Vector<Object>> selectAllHighscore() throws SQLException {
	        Statement stmt = this.conn.createStatement();
	        ResultSet rs = stmt.executeQuery("SELECT * FROM HIGHSCORE");
	        Vector<Vector<Object>> data = new Vector<Vector<Object>>(); // return the resultset as Vector
	        while (rs.next()) {
	            Vector<Object> v = new Vector<Object>();
	            v.add(rs.getString("INPUTID"));
	            v.add(rs.getString("USERNAME"));
	            v.add(rs.getString("SCORE"));
	            v.add(rs.getString("GAME"));
	            data.add(v);
	        }
	        stmt.close();
	        return data;
	    }
	  
		  public void addScore(String username,String score,String game) throws SQLException{
			   String query = "INSERT INTO HIGHSCORE(USERNAME, SCORE, GAME) VALUES (?,?,?)";
			   PreparedStatement pst = conn.prepareStatement(query);
			   pst.setString(1, username);
			   pst.setString(2, score);
			   pst.setString(3, game);
			   pst.execute();
			   JOptionPane.showMessageDialog(null, "Score Uploaded");
			   pst.close();
			  }
		  
		  public void deleteScore(String id)throws SQLException{
			   String query = "DELETE FROM HIGHSCORE WHERE INPUTID=?";
			   PreparedStatement pst = conn.prepareStatement(query);
			   pst.setString(1, id);
			   pst.execute();
			   JOptionPane.showMessageDialog(null, "Score Deleted");
			   pst.close();
			  }
		  
		  public void updateScore (String inputid, String username,String highscore, String game) throws SQLException{
			     String query = "UPDATE HIGHSCORE SET USERNAME = ?, SCORE=?, GAME=?  WHERE inputid = ?";
			     PreparedStatement pst = conn.prepareStatement(query);
			     pst.setString(1, username);
			     pst.setString(2, highscore);
			     pst.setString(3, game);
			     pst.setString(4, inputid);
			     pst.execute();
			     JOptionPane.showMessageDialog(null, "Success Updated");
			     pst.close();
			  }
		
		  public Vector<Vector<Object>> findScore(String id, String username, String highscore, String game) throws SQLException {
		         Statement stmt = this.conn.createStatement();
		         String query = "SELECT * FROM HIGHSCORE WHERE INPUTID LIKE ? OR USERNAME LIKE ? OR SCORE LIKE ? OR GAME LIKE ?";
		         PreparedStatement pst = conn.prepareStatement(query);
		         pst.setString(1, id);
		         pst.setString(2, username);
		         pst.setString(3, highscore);
		         pst.setString(4, game);
		         ResultSet rs = pst.executeQuery();
		         Vector<Vector<Object>> data = new Vector<Vector<Object>>(); // return the resultset as Vector
		         while (rs.next()) {
		             Vector<Object> v = new Vector<Object>();
		             v.add(rs.getString("INPUTID"));
		             v.add(rs.getString("USERNAME"));
		             v.add(rs.getString("SCORE"));
		             v.add(rs.getString("GAME"));
		             data.add(v);
		         }
		         stmt.close();
		         return data;
		     }
		  public Vector<Vector<Object>> findScoreGame(String game) throws SQLException {
		         Statement stmt = this.conn.createStatement();
		         String query = "SELECT USERNAME, SCORE, GAME FROM HIGHSCORE WHERE GAME = ? ORDER BY SCORE DESC";
		         PreparedStatement pst = conn.prepareStatement(query);
		         pst.setString(1, game);
		         ResultSet rs = pst.executeQuery();
		         Vector<Vector<Object>> data = new Vector<Vector<Object>>(); // return the resultset as Vector
		         while (rs.next()) {
		             Vector<Object> v = new Vector<Object>();
		             v.add(rs.getString("USERNAME"));
		             v.add(rs.getString("SCORE"));
		             v.add(rs.getString("GAME"));
		             data.add(v);
		         }
		         stmt.close();
		         return data;
		     }
}
