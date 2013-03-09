import java.util.*;
import java.io.*;
import java.util.Timer;
import java.util.TimerTask;

public class Chatter {
	  
	  public static void main(String [] args) throws FileNotFoundException{
	    ProfilePage p = new ProfilePage();
	    p.promptPage();
	  }
	  //Parameters: int level - Users level
	  //            User u - to access, and mutate user information
	  //THE GAME CHATTER!
        public static void play(int level, User u)throws FileNotFoundException{
        Scanner sc = new Scanner(System.in);
        Questions q = new Questions();
        String question = q.randomQuestion(level);
        String answer = q.getAnswer(question);
        String actualAnswer = answer.substring(0,answer.length()-2).toLowerCase();
        TimerSample timer = new TimerSample(5, u);
        timer.start();
        System.out.println("\nTimer has started");
        System.out.println("\nQuestion: "+question);
        int left = 3;
        while(left>0&&timer.seconds>0){
            System.out.print("\nYour answer: ");
            String ans = sc.nextLine().toLowerCase();
        	if(ans.equals(actualAnswer)){
        		System.out.println(ans + " is Correct!");
        		u.updateCorrect();
        		u.updatePoints(3);
        		timer.cancel();
        	}else{
                left--;
        		System.out.println(ans + " is Incorrect");
        		System.out.println("\nRemaining attemps ["+left+"]");
        		if(left==2)
        			    System.out.println("HINT: Did you include spaces in your answer?");
        	      if(left==1)
        	            System.out.println("HINT: Did you spell your answer correctly?");
            }
        }
        if(left == 0||timer.seconds<=0){
            System.out.println("Sorry you lost...");
            System.out.println("Correct answer was " + answer);
        	u.updateIncorrect();
        	timer.cancel();
        }
        //ProfilePage p = new ProfilePage();
        //p.inProfile(u.Username);        
        }
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

