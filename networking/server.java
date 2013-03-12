package networking;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class server {
	private static int port;
	private Socket s;
	
	public server(Socket s){
		this.s = s;
		System.out.println("connected");
	}
	
	private void run(){
		
	}

	public static void main(String [] args){
		try {
			ServerSocket ss = new ServerSocket(6666);
			while(true){			
				System.out.println("waiting...");
				Socket s = ss.accept();
				new server(s);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
