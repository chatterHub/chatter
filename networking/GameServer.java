package networking;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Scanner;

import src.ProfilePage;
import src.Questions;
import src.User;

public class GameServer extends Thread{
	private Map<String, PrintWriter> usersOut;
	private Map<String, BufferedReader> usersIn;
	private Map<String, User> usersOnline;
	
	public GameServer(int levelCap){
		
	}
	
	public void newGame(){	
		Questions q = new Questions();
		String question = null;
		try {
			//question = q.randomQuestion(levelCap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String answer = q.getAnswer(question);
		String actualAnswer = answer.substring(0, answer.length() - 2).toLowerCase();

	}
}
