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
	
	private int levelCap;
	
	private Map<String, User> usersOnline;
	
	public GameServer(Map<User, PrintWriter> usersOut, Map<User, BufferedReader> playersIn, int levelCap){
		this.usersOut = usersOut;
		this.usersIn = usersIn;
		this.levelCap = levelCap;		
	}
	
	public void test(){
		for(PrintWriter out : usersOut.values()){
			out.write("testtesttest\n");
			out.flush();
		}
	}
}
