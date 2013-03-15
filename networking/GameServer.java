package networking;

import java.io.FileNotFoundException;

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
	
	public void broadcast(){
		for(int i = 0; i < players.length; i++){
			if(players[i] != null){
				
			}
		}
	}
}
