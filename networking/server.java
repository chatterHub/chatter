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

	private static Queue<User> levelTen = new LinkedList<User>();

	private static int port;
	private Socket s;
	private PrintWriter out;
	private BufferedReader in;
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

	private void sendStats() {
		// TODO send client personal stats
	}

	public void setupNewUser() {
		out.write("USERNAME\n");
		out.flush();
		try {
			String userName = in.readLine();
			User user = new User(userName);
			usersOnline.put(userName, user);
			usersOut.put(userName, out);
			usersIn.put(userName, in);
			System.out.println(userName + " is online");
			
			//send player its personal player number
			out.write(""+onlinePlayerCount+"\n");
			out.flush();			
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
				addToQueue();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void addToQueue(){
		GameServer gameServe = new GameServer(usersOut, usersIn, usersOnline);
		new Thread(gameServe).start();
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

}
