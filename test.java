import java.util.*;
import java.io.*;
public class test {

    public static void main(String [] args) throws FileNotFoundException{
        /*testing question 
        Questions q = new Questions();
        String s = q.randomQuestion(1);
        String n = q.getAnswer(s);
        System.out.println("Question: "+ s + "Answer: " + n);
        */
        
        //testing profilepage class
        //ProfilePage p = new ProfilePage();
        User u = new User("tyler");
        System.out.println("Tyler's email: " + u.getEmail());
        
    }
}
