package serverSocket;

import java.io.*;
import java.net.*;

import programUtama.SharedVariable;

public class ThreadPlayer extends Thread{
	private Socket socket;
	private String client;
	
	ThreadPlayer(Socket s){
			socket = s;
			client = s.getRemoteSocketAddress().toString();
			System.out.println("Tersambung dari alamat "+ client);
	}
	
	public void run(){
		try{
					PrintWriter out = new PrintWriter(
					socket.getOutputStream(), true);	
					out.println("Welcome to the GameBox!");
		}catch(Exception e){
			System.out.println(
					"Error "+client+": "+ e);
		}finally{
			try{
				socket.close();
			}catch(IOException e){
				e.printStackTrace();
			}
		}
	}

}
