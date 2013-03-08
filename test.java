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
        User u = new User("CRoller");
        System.out.println("Collen's email: " + u.getEmail());
        //get level isn't working correctly
        System.out.println("Collen's level: " + u.getLevel());
        System.out.println("Collen's points: " + u.getPoints());
        System.out.println("Collen's has answered: " + u.getCorrect() +" correct");
        System.out.println("Collen's has answered: " + u.getIncorrect() +" incorrect");
        System.out.println("Collen's has answered: " + u.getTotalQuestions() +" total questions");
        System.out.println("Collen's password is: " + u.getPassword());
        
    }
}
