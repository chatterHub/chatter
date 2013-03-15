package networking;

import java.io.FileNotFoundException;
import java.util.Scanner;

import src.ProfilePage;
import src.Questions;
import src.User;

public class GameServer {
	public User [] players;
	private int levelCap;
	
	public GameServer(User[] players, int levelCap){
		this.levelCap = levelCap;
		this.players = players;
	}
	
	public void newGame(){	
		//players[0].
		Questions q = new Questions();
		String question = q.randomQuestion(level);
		String answer = q.getAnswer(question);
		String actualAnswer = answer.substring(0, answer.length() - 2)
				.toLowerCase();

		System.out.println("\nQuestion: " + question);
		int left = 3;
		while (left > 0) {
			System.out.print("\nYour answer: ");
			String ans = sc.nextLine().toLowerCase();
			if (ans.contains(actualAnswer)) {
				System.out.println(actualAnswer + " is Correct!");
				u.updateCorrect();
				u.updatePoints(3);
				break;
			} else {
				left--;
				System.out.println(ans + " is Incorrect");
				System.out.println("\nRemaining attemps [" + left + "]");
				if (left == 2)
					System.out
							.println("HINT: Did you include spaces in your answer?");
				if (left == 1)
					System.out
							.println("HINT: Did you spell your answer correctly?");
			}
		}
		if (left == 0) {
			System.out.println("Sorry you lost...");
			System.out.println("Correct answer was " + answer);
			u.updateIncorrect();
		}
		ProfilePage p = new ProfilePage();
		p.inProfile(u.Username);
	}
}
