import java.util.*;
import java.io.*;

public class User {

    private String Username;
    private int level;
    private int points;
    private int qcorrect;
    private int qincorrect;
    private int totalquestions;
    
    public User(){
        level = 1;
        points = 0;
        qcorrect = 0;
        qincorrect = 0;
        totalquestions = 0;
    }
    
    public User(String u){
        Username = u;
    }
    
    public String getEmail() throws FileNotFoundException{
    	  Scanner input = new Scanner(new File("Users/" + Username + ".txt"));
        String text = "";
        while(input.hasNext()){
           text += input.next() + " ";
        }
        //System.out.println(text);
        String email = "";
        int temp = 0;
        for(int i=0; i<text.length(); i++){
            if(text.substring(i,i+8).equals("E-mail: ")){
                temp = i+8;
            }
            if(i+8>=text.length())
            	break;
        }
        email += text.substring(temp,text.length());
        return email;
	}
}
