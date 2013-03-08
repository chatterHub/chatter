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
        	String answer = q.getAnswer(question);
        	String actualAnswer = answer.substring(0,answer.length()-2).toLowerCase();
        	System.out.println("\n"+question);
        	int left = 3;
        	while(left>0){
        	    System.out.print("\nYour answer: ");
            	String ans = sc.nextLine().toLowerCase();
        		if(ans.equals(actualAnswer)){
        			System.out.println(ans + " is Correct!");
        			u.updateCorrect();
        			u.updatePoints(3);
        			break;
        		}
        		else{
        		    left--;
        			System.out.println(ans + " is Incorrect");
        			System.out.println("Remaining attemps ["+left+"]");
        			if(left==1)
        			    System.out.println("HINT: Did you include spaces in your answer?");
        		}
        	}
        	if(left == 0){
        	    System.out.println("Sorry you lost...");
        	    System.out.println("Correct answer was " + answer);
        		u.updateIncorrect();
            }
        	ProfilePage p = new ProfilePage();
        	p.inProfile(u.Username);
        }
}
