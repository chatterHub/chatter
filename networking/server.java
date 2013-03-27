package networking;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import src.User;

public class server extends Thread {
	private static Map<String, PrintWriter> usersOut;
	private static Map<String, BufferedReader> usersIn;
	private static Map<String, User> usersOnline;

	private static Queue<String> levelTen = new LinkedList<String>();

	private static int port;
	private Socket s;
	private PrintWriter out;
	private BufferedReader in;
	private String username;
	private String handshakeKey = "CLIENT";

	// TODO
	private static int onlinePlayerCount;

	public server() {
		usersOut = new HashMap<String, PrintWriter>();
		usersIn = new HashMap<String, BufferedReader>();
		usersOnline = new HashMap<String, User>();
	}

	public server(Socket s) {
		this.s = s;
		try{
			setServerClientIO();
		}catch(Exception e){
			System.out.println(e.getMessage());
			return;
		}
	}
	
	private void setServerClientIO() throws Exception{
		out = new PrintWriter(s.getOutputStream(), true);
		in = new BufferedReader(new InputStreamReader(s.getInputStream()));
		if (in.readLine().equals(handshakeKey)) { // verify user
			setupNewUser();
		}
	}

	private void sendStats() {
		// TODO send client personal stats
	}

	public void setupNewUser() {
		messageClient("USERNAME");
		try {
			String userName = in.readLine();
			username = userName;
			User user = new User(userName);
			usersOnline.put(userName, user);
			usersOut.put(userName, out);
			usersIn.put(userName, in);
			System.out.println(userName + " is online");
			
			//send player its personal player number	
			messageClient(""+onlinePlayerCount);
			onlinePlayerCount++;
			sendStats();
			new Thread(this).start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void run() {
		boolean playing = true;
		while (playing) {
			try {
				String input = in.readLine();
				if (input != null)
					analyzeInput(input);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void analyzeInput(String s) {
		if (s.equals("QUEUE")) {
			try {
				addToQueue(getUsername());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void addToQueue(String userName){
		System.out.println("adding " + userName + " to queue");
		levelTen.add(userName);
		if(levelTen.size() ==1){
			GameServer gameServer;
			Map<User, PrintWriter> playersOut = new HashMap<User, PrintWriter>();
			Map<User, BufferedReader> playersIn = new HashMap<User, BufferedReader>();
			String name;
			for(int i = 0; i < levelTen.size(); ){
				if(levelTen.size()!=0) name = levelTen.remove();
				else name = null;
				if(name != null){
					System.out.println("user ");
					playersOut.put(usersOnline.get(name), usersOut.get(name));
					playersIn.put(usersOnline.get(name), usersIn.get(name));	
				}
			}
			System.out.println("OOL");
			System.out.println(playersIn == null || playersOut == null);
			gameServer = new GameServer(playersOut, playersIn, 10);
			System.out.println("creating new level ten game");
			//TODO possibly thread this call, I dont think it will allow more than one game to simotaniously run yet
			gameServer.startGame();
		}
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
	
	private void messageClient(String msgOut){
		out.write(msgOut + "\n");
		out.flush();
	}
	private String receiveClient(){
		try {
			return in.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private String getUsername(){
		return username;
	}
}
