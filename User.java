import java.util.*;
import java.io.*;

public class User {

    private String Username;
    private String Password;
    private String Email;
    private int level;
    private int points;
    private int qcorrect;
    private int qincorrect;
    private int totalquestions;
    public boolean loggedIn;
    
    public User(String u) throws FileNotFoundException{
        Username = u;
        loggedIn = true;
        Scanner sc = new Scanner(new File("Users/" + Username + ".txt"));
        while(sc.hasNext()){
            String text = sc.next();
            if(text.equals("Password:"))
                Password = sc.next();
            if(text.equals("E-mail:"))
                Email = sc.next();
            if(text.equals("Level:"))
                level = sc.nextInt();
            if(text.equals("Points:"))
                points = sc.nextInt();
            if(text.equals("Correct:"))
                qcorrect = sc.nextInt();
            if(text.equals("Incorrect:"))
                qincorrect = sc.nextInt();
            if(text.equals("Questions:"))
                totalquestions = sc.nextInt();
        }
        System.out.println("User information stored");
    }
    //Accessor methods for user information
    public String getPassword(){
        return Password;
	}
    public String getEmail(){
        return Email;
	}
	public int getLevel(){
	    return level;
	}
	public int getPoints(){
	    return points;
	}
	public int getCorrect(){
	    return qcorrect;
	}
	public int getIncorrect(){
	    return qincorrect;
	}
	public int getTotalQuestions(){
	    return totalquestions;
	}	
}














