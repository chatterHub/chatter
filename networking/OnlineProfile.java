package networking;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import src.User;

public class OnlineProfile {
	
	private User user;
	private String userName;
	private Socket s;
	private PrintWriter out;
	private BufferedReader in;
	
	public OnlineProfile(User user){
		this.user = user;
		userName = user.getUsername();
		try {
			s = new Socket("localhost", 6666);
			PrintWriter out = new PrintWriter(s.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
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
			new OnlineProfile(u);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
