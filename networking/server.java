package networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class server extends Thread{
	private static int port;
	private Socket s;
	private PrintWriter out;
	private BufferedReader in;
	
	public server(Socket s){
		this.s = s;
		try {
			out = new PrintWriter(s.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(s.getInputStream()));
		} catch (IOException e) {
			System.out.println("could not open IO on socket.");
			System.exit(1);
		}
		new Thread(this).start();
	}
	
	public void run(){
		System.out.println("SENDING");
		out.write("server sending test\n");
		System.out.println("sent");
		out.flush();
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
