package client;
import java.util.*;
import java.io.*;

public class User {

    //Fields - stores users information
    public String Username;
    private String Password;
    private String Email;
    private int level;
    private int points;
    private int qcorrect;
    private int qincorrect;
    private int totalquestions;
    public boolean online;
    
    //Constructor: reads the users file and sets information to fields
    public User(String u) throws FileNotFoundException{
        Username = u;
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
	public boolean getStatus(){
	    return online;
	}
	//************************************
	//Mutator methods to change user information
    public void updateCorrect()throws FileNotFoundException{
        qcorrect++;
        totalquestions = qcorrect + qincorrect;
        String test = "Correct:";
        updateUserInfo(test,qcorrect);
        updateTotal();
    }
    public void updateIncorrect()throws FileNotFoundException{
        qincorrect++;
        totalquestions = qcorrect + qincorrect;
        String test = "Incorrect:";
        updateUserInfo(test,qincorrect);
        updateTotal();
    }
    public void updateTotal()throws FileNotFoundException{
        String test = "Total Questions:";
        updateUserInfo(test,totalquestions);
    }
    public void updatePoints(int n)throws FileNotFoundException{
        String test = "Points:";
        calcLevelPoints(n);
        updateUserInfo(test,points);
        updateLevel();
    }
    public void updateLevel()throws FileNotFoundException{
	    String test = "Level:";
	    updateUserInfo(test,level);
	}
	
	//Parameter: int n - points that will be added to user
	//Calculates the users new level and new point
	//changes level if enough points are present
	//otherwise prints how many more points are needed until next lvl
	public void calcLevelPoints(int n){
	    int pointsneeded = level*3;
	    int currentpoints = points + n;
	    if(currentpoints >= pointsneeded){
	        level++;
	        points = currentpoints - pointsneeded;
	        System.out.println("\nYOU'VE LEVELED UP TO LEVEL "+level);
	    }else{
	        points = currentpoints;
	        int tolevelup = pointsneeded - currentpoints;
	        System.out.println("\nYou still need "+tolevelup+" points to level up!");
	    } 
	}
	//tells if the user is online or not
	public void userOnline(boolean bool){
	    online = bool;
	    if(online)
	        System.out.println("You are now logged in as "+Username);
	    else
	        System.out.println(Username + " is now logged out...");
	}
	//Parameters: String s - what information will be reconstructed
	//            int n - the integer that will follow the string
	//Essentially: Reconstructs users text file with modified information
	//so that file can be updated after each game
	public void updateUserInfo(String s, int n) throws FileNotFoundException{
        File f = new File("Users/"+Username+".txt");
        Scanner sc = new Scanner(f);
        ArrayList<String> list = new ArrayList<String>();
        while(sc.hasNext()){
            String line = sc.nextLine();
            if(line.contains(s)){
                String temp = s + " " + n;
                list.add(temp);
            }else
                list.add(line);
        }
        PrintStream ps = new PrintStream(f);
        for(int i=0; i<list.size(); i++){
            String line = list.get(i);
            ps.println(line);
        }
    }
}






















