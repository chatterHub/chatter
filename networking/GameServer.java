package networking;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Scanner;

import src.ProfilePage;
import src.Questions;
import src.User;

public class GameServer{
	private Map<User, PrintWriter> usersOut;
	private Map<User, BufferedReader> usersIn;
	
	private int currentPlayerCount; //number of players in the game
	
	private int levelCap;
	
	private String question;
	private String answer;
	private String actualAnswer;
	
	
	private Map<String, User> usersOnline;
	
	public GameServer(Map<User, PrintWriter> usersOut, Map<User, BufferedReader> usersIn, int levelCap){
		System.out.println("creating new GameServer");
		this.usersOut = usersOut;
		this.usersIn = usersIn;
		this.levelCap = levelCap;	
		this.currentPlayerCount = usersOut.size();
		setGame();
	}
	
	private void setGame(){
		System.out.println("in setGame method");
		Questions q = new Questions();
		try {
			this.question = q.randomQuestion(9);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		this.answer = q.getAnswer(question);
		this.actualAnswer = answer.substring(0, answer.length() - 2).toLowerCase();
	}
	
	public void startGame(){
		announceStart();
		for(User user : usersIn.keySet()){
			if (user!= null){
				System.out.println("loading game for " + user.getUsername());
				gameRunner gr = new gameRunner(user.getUsername());
				gr.startGame();
			}
		}
	}
	
	private void announceStart(){
		for(PrintWriter out : usersOut.values()){
			out.write("GAMEREADY\n");
			out.flush();
		}
		System.out.println("initial GAMEREADY msg sent to all users");
	}
	
	private class gameRunner extends Thread{
		private String username;
		public gameRunner(String username){
			this.username = username;
		}
		protected void startGame(){
			new Thread(this).start();
		}
		
		public void run(){
			System.out.println(usersOut.get(username) == null);
			String input = null;
			usersOut.get(username).write(question+"\n");
			//hardcode number of guesses to 3
			for(int i = 0; i < 3; i++){
				try {
					input = usersIn.get(this.username).readLine();
				} catch (IOException e) {
					e.printStackTrace();
				}
				if(input.contains(actualAnswer)) usersOut.get(username).write("1");
				else usersOut.get(username).write("0");
			}
		}
	}
}
