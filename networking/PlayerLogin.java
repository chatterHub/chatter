package networking;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.*;

import src.ProfilePage;
import src.User;

public class PlayerLogin {

	private static Scanner sc;
	private static String Username;
	private static String Password;
	private static String Email;
	private ProfilePage pp;

	public PlayerLogin(ProfilePage pp, Scanner sc) {
		this.pp = pp;
		this.sc = sc;
	}

	public void login() {
		System.out.print("\nAre you a new user? (yes/no) ");
		String yn = sc.nextLine().toLowerCase();
		while (!yn.equals("yes") && !yn.equals("y") && !yn.equals("no")
				&& !yn.equals("n")) {
			System.out.println(yn + " is incorrect input, try again....");
			System.out.print("Are you a new user? (yes/no) ");
			yn = sc.nextLine().toLowerCase();
		}
		try {
			if (yn.equals("yes") || yn.equals("y")) {
				createProfile();
			} else if (yn.equals("no") || yn.equals("n")) {
				enterProfile();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/*
	 * prompts user for username, password, email if username already exists,
	 * error is shown with a reprompt if password and validation don't match,
	 * error is shown with reprompt if email and validation don't match or @
	 * doesen't appear... error message is shown with a reprompt Post-Condition:
	 * New profile is created in chatter/Users
	 */
	public static void createProfile() throws FileNotFoundException {
		// prompting for username
		System.out.print("\nWhat would you like your username to be? ");
		String u = sc.nextLine();
		while (checkUsername(u)) {
			System.out.println("This username is already being used.");
			System.out.print("What would you like your username to be? ");
			u = sc.nextLine();
		}
		System.out.println();
		Username = u;

		// prompting for password with masking ***
		// HidePassword hideThread = new HidePassword();
		// hideThread.start();
		String p1 = "";
		String p2 = "";
		try {
			// hideThread.hideInput = true;
			System.out.print("Enter a password: ");
			p1 = sc.nextLine();
			System.out.print("Verify password: ");
			p2 = sc.nextLine();
			while (!checkNewPassword(p1, p2)) {
				p1 = "";
				p2 = "";
				System.out.println("Passwords don't match.");
				System.out.print("Enter a password: ");
				p1 = sc.nextLine();
				System.out.print("Verify password: ");
				p2 = sc.nextLine();
			}
			// hideThread.stopThread= true;
		} catch (Exception e) {
		}
		System.out.println("\b\b");
		Password = p1;

		// prompting for email
		// who gives a shit about email anyways?
		String e1 = "";
		String e2 = "";
		System.out.print("What's your E-mail address? ");
		e1 = sc.nextLine();
		System.out.print("Verify E-mail address: ");
		e2 = sc.nextLine();
		while (!checkEmail(e1, e2)) {
			e1 = "";
			e2 = "";
			System.out.println("Invalid E-mail");
			System.out.print("Enter an E-mail: ");
			e1 = sc.nextLine();
			System.out.print("Verify E-mail: ");
			e2 = sc.nextLine();
		}
		Email = e1;

		// creating new file for each user
		File f = new File("Users/" + Username + ".txt");
		try {
			f.createNewFile();
		} catch (Exception e) {
		}

		// printstreaming info to users file
		PrintStream p = new PrintStream("Users/" + Username + ".txt");
		p.println("Username: " + Username);
		p.println("Password: " + Password);
		p.println("Level: 1");
		p.println("Points: 0");
		p.println("Questions Correct: 0");
		p.println("Questions Incorrect: 0");
		p.println("Total Questions: 0");
		p.println("E-mail: " + Email);

		// printstream into mailing list
		PrintStream f2 = new PrintStream("MailingList.txt");
		f2.println(Username + " : " + Email);
		f2.close();

		System.out.println("\nProfile Sucessfully Created!");
		enterProfile();

	}

	// Parameter: String u - Username to check
	// Returns: True - if username exists
	// False - otherwise
	public static boolean checkUsername(String u) throws FileNotFoundException {
		File f = new File("Users/" + u + ".txt");
		if (f.exists()) {
			return true;
		}
		return false;
	}

	// Post-Conditions: hides password so only user knows what they're typing
	private class HidePassword extends Thread {
		boolean stopThread = false;
		boolean hideInput = false;
		boolean shortMomentGone = false;

		public void run() {
			try {
				sleep(500);
			} catch (InterruptedException e) {
			}
			shortMomentGone = true;
			while (!stopThread) {
				if (hideInput) {
					System.out.print("\b*");
				}
				try {
					sleep(1);
				} catch (InterruptedException e) {
				}
			}
		}
	}

	// Paremeters: String p1 - first password entry
	// String p2 - second password entry
	// Returns: True - if passwords match
	// False - otherwise
	public static boolean checkNewPassword(String p1, String p2) {
		if (p1.equals(p2))
			return true;
		return false;
	}

	// Parameter: String e1 - first email entry
	// String e2 - second email entry
	// Returns: True - if emails match and contain @
	// False - otherwise
	public static boolean checkEmail(String e1, String e2) {
		if (e1.equals(e2) && e1.contains("@"))
			return true;
		return false;
	}

	// prompts user for an existing and checks if it exists
	// if not, you can be redirected to createProfile()
	// once username is found, password is checked with files password
	// Post-Conditions: User is logged in!
	public static void enterProfile() throws FileNotFoundException {
		System.out.print("\nEnter username: ");
		String u = sc.nextLine();
		boolean newProfileMade = false;
		while (!checkUsername(u)) {
			System.out.println("Username not found...");
			System.out.print("Try again(T)? or Create profile(C)? ");
			String temp = sc.nextLine().toUpperCase();
			while (!temp.equals("T") && !temp.equals("C")) {
				System.out.println(temp + " is not (T) or (C)...");
				System.out.print("Try again(T)? or Create profile(C)? ");
				temp = sc.nextLine().toUpperCase();
			}
			if (temp.equals("T")) {
				System.out.print("\nEnter username: ");
				u = sc.nextLine();
			} else if (temp.equals("C")) {
				newProfileMade = true;
				createProfile();
				break;
			}
		}
		Username = u;
		if (!newProfileMade) {
			// HidePassword hideThread = new HidePassword();
			// hideThread.start();
			try {
				// hideThread.hideInput = true;
				System.out.print("Enter password: ");
				Password = sc.nextLine();
				while (!checkPassword(Username, Password)) {
					System.out.println("Invalid password");
					System.out.print("Enter password: ");
					Password = sc.nextLine();
				}
				// hideThread.stopThread = true;
			} catch (Exception e) {
			}
			System.out.println("\n");
			System.out.println("Arranging profile page...");
			System.out.println();
			User user = new User(u);
			user.userOnline(true);
			System.out.println("\nWELCOME TO CHATTER!");
		}
	}

	// Parameter: String u - first password entry
	// String p - second password entry
	// Returns: True - if passwords match
	// False - otherwise
	public static boolean checkPassword(String u, String p)
			throws FileNotFoundException {
		String temp = "";
		Scanner userScan = new Scanner(new File("Users/" + u + ".txt"));
		userScan.next(); // scans the string "Username:"
		String user = userScan.next();
		userScan.next(); // selects the next String, "Password:"
		String pass = userScan.next();
		return u.equals(user) && p.equals(pass);

	}
	
	public boolean setInfo(){
		pp.setUsername(Username);
		pp.setEmail(Email);
		return true;
	}
}
