package networking;

import java.io.FileNotFoundException;
import java.util.Scanner;

import src.ProfilePage;
import src.Questions;
import src.User;

public class GameServer {
	public User [] players;
	private int levelCap;
	
	public GameServer(int levelCap){
		this.levelCap = levelCap;
	}
	
	public void newGame(){	
		Questions q = new Questions();
		String question = null;
		try {
			question = q.randomQuestion(levelCap);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String answer = q.getAnswer(question);
		String actualAnswer = answer.substring(0, answer.length() - 2).toLowerCase();

	}
}
