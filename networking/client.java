package networking;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import src.User;

public class client {
	
	private User user;
	private Socket s;
	private PrintWriter out;
	private BufferedReader in;
	
	public client(User user){
		this.user = user;
		try {
			s = new Socket("localhost", 6666);
			out = new PrintWriter(s.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(s.getInputStream()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		handshake();
		startGame();
	}
	
	//initialize connection
	private void handshake(){
		String input;
		try {
			out.write("CLIENT\n");
			out.flush();
			input = in.readLine(); //get request for username
			out.write(user.getUsername() + "\n");
			out.flush();
			user.userOnline(true);
			System.out.println("here");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void startGame(){
		out.write("newGame\n");
		out.flush();
	}
	
	public static void main(String [] args){
		try {
			User u = new User("beerent");
			new client(u);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
