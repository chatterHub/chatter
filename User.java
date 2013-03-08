import java.util.*;
import java.io.*;

public class User {

    public String Username;
    private String Password;
    private String Email;
    private int level;
    private int points;
    private int qcorrect;
    private int qincorrect;
    private int totalquestions;
    public boolean online;
    
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
	//************************************
	
	//Mutator methods to change user information
    public void updateCorrect()throws FileNotFoundException{
        qcorrect++;
        totalquestions = qcorrect + qincorrect;
        String test = "Correct:";
        updateUserInfo(test,qcorrect);
    }
    public void updateIncorrect()throws FileNotFoundException{
        qincorrect++;
        totalquestions = qcorrect + qincorrect;
        String test = "Incorrect:";
        updateUserInfo(test,qincorrect);
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
	
	//Calculates the users new level and new point
	public void calcLevelPoints(int n){
	    int currentpnts = level*3;
	    currentpnts += n;
	    level = currentpnts / 3;
	    points = currentpnts % 3;
	}
	
	public void userOnline(boolean bool){
	    if(bool){
	        online=true;
	        System.out.println("You are now logged in as "+Username);
	    }else
	        online=false;
	        System.out.println(Username + " is now logged out...");
	}
	
	//Sends 
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






















