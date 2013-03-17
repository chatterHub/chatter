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

public class server extends Thread {
	private static User [] onlineUsers;
	private static Queue<User> levelTen = new LinkedList<User>();
	private static boolean queueWatch;

	private static int port;
	private Socket s;
	private PrintWriter out;
	private BufferedReader in;
	private String handshakeKey = "CLIENT";
	
	private int onlinePlayerCount;

	public server() {
		onlineUsers = new User[100];
	}
	
	public server(boolean queueWatch){
		this.queueWatch = queueWatch;
	}

	public server(Socket s) {
		this.s = s;
		try {
			out = new PrintWriter(s.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(s.getInputStream()));
			
			if (in.readLine().equals(handshakeKey)) { // verify user
				setupNewUser();
			}
		} catch (IOException e) {
			System.out.println("could not open IO on socket.");
			return;
		}
	}
	
	private void sendStats(){
		//TODO send client personal stats
	}

	public void setupNewUser() {
		out.write("USERNAME\n");
		out.flush();
		try {
			String userName = in.readLine();
			User user = new User(userName);
			addUser(user);
			System.out.println(userName + " is online");
			onlinePlayerCount++;
			sendStats();
			new Thread(this).start();
		} catch (Exception e ) {
			e.printStackTrace();
		}
	}
	
	private void addUser(User user){
		for(int i = 0; i <onlineUsers.length; i++){
			if(onlineUsers[i] == null){
				onlineUsers[i] =  user;
				out.write(""+i+"\n");
				out.flush();
				return;
			}
		}
		System.out.println("TOO MANY PLAYERS PLAYING. SORRY.");
	}
	
	public void run(){
		boolean playing = true;
		while(playing){
			try {
				String input = in.readLine();
				if(input!=null)
					analyzeInput(input);
			} catch (IOException e) {
				e.printStackTrace();
			}			
		}
	}

	
	private void analyzeInput(String s){
		if(s.equals("QUEUE")){
			try {
				addToQueue(in.readLine());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void addToQueue(String s){	
		levelTen.add(onlineUsers[Integer.parseInt(s)]);
		System.out.println("added " + onlineUsers[Integer.parseInt(s)] + " to level 10 queue.");
	}
	
	private void listen() {
		onlinePlayerCount = 0;
		try {
			ServerSocket ss = new ServerSocket(6666);
			System.out.println("server listening on port " + 6666);
			while (true) {
				Socket s = ss.accept();
				System.out.println("new client connected");
				new server(s);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		server serv = new server();
		serv.listen();
	}
	
	private static void terminateQueueWatcher(){
		queueWatch = false;
	}
	
	public static void queueWatcher(){
		queueWatch = true;
		while(queueWatch){
			
		}
	}
}
