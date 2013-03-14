package networking;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import src.User;

public class server extends Thread{
	private static ArrayList<User> onlineUsers;
	private static Queue<User> levelTen = new LinkedList<User>();
	
	private static int port;
	private Socket s;
	private PrintWriter out;
	private BufferedReader in;
	private String handshakeKey = "CLIENT";
	public server(){
		onlineUsers = new ArrayList<User>();
	}
	public server(Socket s){
		this.s = s;
		try {
			out = new PrintWriter(s.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(s.getInputStream()));
		} catch (IOException e) {
			System.out.println("could not open IO on socket.");
			System.exit(1);
		}
		
		try {
			if(in.readLine().equals(handshakeKey)){ //verify user
				setupNewUser();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setupNewUser(){
		out.write("USERNAME\n");
		out.flush();
		System.out.println("get username");
		try {
			String userName = in.readLine();
			User user = new User(userName);
			System.out.println("user accepted");
			onlineUsers.add(user);			
			System.out.println("user online");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void listen(){
		try {
			ServerSocket ss = new ServerSocket(6666);
			System.out.println("server listening on port " + 6666);
			while(true){			
				Socket s = ss.accept();
				System.out.println("new client connected");
				new server(s);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String [] args){
		server serv = new server();
		serv.listen();
	}
}
