import java.util.*;
import java.io.*;

public class Chatter {
	  public static void main(String [] args) throws FileNotFoundException{
	  	ProfilePage p = new ProfilePage();
	  	p.promptPage();
	  }
        public static void play(int level, User u)throws FileNotFoundException{
        	System.out.println("playing");
            Scanner sc = new Scanner(System.in);
        	Questions q = new Questions();
        	String question = q.randomQuestion(level);
        	String answer = q.getAnswer(question).toLowerCase();
        	String actualAnswer = answer.substring(0,answer.length()-2);
        	System.out.println(question);
        	int left = 2;
        	while(left>0){
            	String ans = sc.nextLine().toLowerCase();
        		if(ans.equals(actualAnswer)){
        			System.out.println("Correct!");
        			u.updateCorrect();
        			break;
        		}
        		else{
        			System.out.println("Incorrect");
        			left--;
        			ans = sc.nextLine();
        		}
        	}
        	if(left == 0)
        		u.updateIncorrect();
        		
        	ProfilePage p = new ProfilePage();
        	p.inProfile(u.Username);
        }
}
