package networking;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import src.User;

public class OnlineProfile {

	private User user;
	private String userName;
	private Socket s;
	private PrintWriter out;
	private BufferedReader in;
	private int idNumber;
	private Scanner sc;

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
			out.write("CLIENT\n");
			out.flush();
			input = in.readLine(); // get request for username
			out.write(user.getUsername() + "\n");
			out.flush();
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
				System.out.println("enter an option between 1 and "
						+ optionCount + ": ");
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
		
	}

	public static void main(String[] args) {
		try {
			User u = new User("beerent");
			new OnlineProfile(u);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
