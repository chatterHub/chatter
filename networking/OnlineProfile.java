package networking;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import src.ProfilePage;
import src.User;

public class OnlineProfile {

	private Scanner sc;
	private User user;
	private String userName;
	private Socket s;
	
	private PrintWriter out;
	private BufferedReader in;
	public int idNumber;

	public OnlineProfile(User user) {
		this.user = user;
		userName = user.getUsername();
		sc = new Scanner(System.in);
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

	// initialize connection
	private void handshake() {
		String input;
		try {
			System.out.println("sending client");
			messageServer("CLIENT");
			input = in.readLine(); // get request for username
			messageServer(user.getUsername());
			int n = Integer.parseInt(in.readLine());
			System.out.println(n);
			idNumber = n;
			user.userOnline(true);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void startGame() {
		int optionCount = 2;
		System.out.println("What would you like to do: ");
		System.out.println("1) Search for new online game");
		System.out.println("2) Logout");
		System.out.print("--> ");
		String input = sc.next();
		while (true) {
			try {
				int option = Integer.parseInt(input);
				if (option < 1 || option > optionCount) {
					System.out.println("Invalid input.");
					System.out.println("enter an option between 1 and "
							+ optionCount + ": ");
					input = sc.next();
				}
				switch (option) {
				case 1:
					QueueForBattle();
					break;
				case 2:
					logout();
				}

				break;

			} catch (Exception e) {
				// not an integer
				System.out.println("Invalid input.");
				System.out.println("enter an option between 1 and " + optionCount + ": ");
				input = sc.next();
			}
		}
	}

	private void logout() {
		user.userOnline(false);
		in = null;
		out = null;
		try {
			s.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	private void QueueForBattle() {
		System.out.println("Prepare for battle!");
		System.out.println("please wait for more players...");
		messageServer("QUEUE");	
		try {
			String begin = in.readLine();
			if(begin.equals("GAMEREADY")){
				rungame();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void rungame(){
		System.out.println("game loading...");
		boolean gameon = true;
		Scanner sc = new Scanner(System.in);
		String input = null;
		try {
			input = in.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(input);
		//hardcoding amount of tries to three
		String fromClient;
		String response;
		for(int i = 0; i < 3; i++){
			System.out.print("enter guess 1: ");
			fromClient = sc.nextLine().toLowerCase();
			//send server the guess attempt
			messageServer(fromClient);
			try {
				response = in.readLine();
				if(response.equals("0")) System.out.println("LOSE");
				else if(response.equals("1")) System.out.println("WIN!!");
				else throw new IOException("illegal input from server: " + response);
			} catch (IOException e) {
				e.printStackTrace();
			}			
		}
	}
	

	
	private void messageServer(String s){
		out.write(s+"\n");
		out.flush();
	}
	
	//returns out
	public PrintWriter getOut(){
		return out;
	}
	
	//returns in
	public BufferedReader getIn(){
		return in;
	}

	public static void main(String[] args) {
		try {
			User u = new User("beerent");
			//new OnlineProfile(u);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void setIDNumber(int n){
		idNumber = n;
	}
}
