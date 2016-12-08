package serverSocket;

import java.io.*;
import java.net.*;
import programUtama.*;

public class GameboxSocket {
	private static final int PORT = 9090;
	public static String setLoginType;
	public static void main(String[] args) throws IOException{
		System.out.println("Server ini berjalan di Port:"+ PORT);
		ServerSocket listener =  new ServerSocket(PORT);
		try{
			while(true){
				
				Socket s = listener.accept();
				BufferedReader br = new BufferedReader(
						new InputStreamReader(
								s.getInputStream()
								)
						);
				String answer = br.readLine();
				setLoginType = answer;
				System.out.println(setLoginType);
				Thread t = new ThreadPlayer(s);
				t.start();
			}
		}finally{
			listener.close();
		}
	}
}