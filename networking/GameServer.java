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
	private Map<User, PrintWriter> usersOut;
	private Map<User, BufferedReader> usersIn;
	
	private int currentPlayerCount; //number of players in the game
	
	private int levelCap;
	
	private String question;
	private String answer;
	
	
	private Map<String, User> usersOnline;
	
	public GameServer(Map<User, PrintWriter> usersOut, Map<User, BufferedReader> playersIn, int levelCap){
		this.usersOut = usersOut;
		this.usersIn = usersIn;
		this.levelCap = levelCap;	
		this.currentPlayerCount = usersOut.size();
		setGame();
	}
	
	public void setGame(){
		Questions q = new Questions();
		try {
			this.question = q.randomQuestion(9);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		this.answer = q.getAnswer(question);
		String actualAnswer = answer.substring(0, answer.length() - 2).toLowerCase();
	}
	
	public void test(){
		for(PrintWriter out : usersOut.values()){
			out.write("testtesttest\n");
			out.flush();
		}
	}
}
