import java.util.*;
import java.io.*;

public class ProfilePage {

    private Scanner sc;
    private String Username;
    private String Password;
    private String Email;
    
    public ProfilePage() throws FileNotFoundException{
        sc = new Scanner(System.in);
        System.out.print("Are you a new user? (yes/no) ");
        String yn = sc.nextLine().toLowerCase();
        while(!yn.equals("yes") && !yn.equals("y") 
            && !yn.equals("no") && !yn.equals("n")){
                System.out.println(yn+" Is incorrect input, try again....");
                System.out.print("Are you a new user? (yes/no) ");
                yn = sc.nextLine().toLowerCase();
        }if(yn.equals("yes") || yn.equals("y"))
            createProfile();
        else if(yn.equals("no") || yn.equals("n")){
            enterProfile();
        }
    }
    
    public void createProfile() throws FileNotFoundException {
	   //prompting for username
         System.out.print("What would you like your username to be? ");
         String u = sc.nextLine();
         while(!checkUsername(u)){
         	System.out.println("This username is already being used.");
         	System.out.println();
         	System.out.print("What would you like your username to be? ");
         	u = sc.nextLine();
         }
         Username = u;
         //prompting for password
         String p1 = "";
         String p2 = "";
         System.out.print("What would you like your password to be? ");
         p1 = sc.nextLine();
         System.out.print("Verify password: ");
         p2 = sc.nextLine(); 
         while(!checkNewPassword(p1,p2)){
         	p1 = "";
         	p2 = "";
         	System.out.println("Passwords don't match.");
            System.out.print("Enter a password: ");
            p1 = sc.nextLine();
            System.out.print("Verify password: ");
            p2 = sc.nextLine(); 
         }
         Password = p1;
         //prompting for email
         //who gives a shit about email anyways?
         String e1 = "";
         String e2 = "";
         System.out.print("What's your E-mail address? ");
         e1 = sc.nextLine();
         System.out.print("Verify E-mail address: ");
         e2 = sc.nextLine(); 
         while(!checkEmail(e1,e2)){
         	e1 = "";
         	e2 = "";
         	System.out.println("Invalid E-mail");
            System.out.print("Enter an E-mail: ");
            e1 = sc.nextLine();
            System.out.print("Verify E-mail: ");
            e2 = sc.nextLine(); 
         }
         Email = e1;
         //creating new file for each user
         File f = new File("Users/" + Username + ".txt");
         try{
         f.createNewFile();
         }catch(Exception e){
         }
         //printstreaming info to files
         PrintStream p = new PrintStream("Users/" + Username + ".txt");
         p.println("Username: " + Username);
         p.println("Password: " + Password);
         p.println("E-mail: " + Email);
         //printstream into mailing list
         PrintStream f2 = new PrintStream("MailingList.txt");
         f2.println(Username + " : " + Email);
         
    }
    public void enterProfile(){
        System.out.print("Enter username: ");
        Username = sc.nextLine();
        checkUsername(Username);
        System.out.print("Enter password: ");
        Password = sc.nextLine();
        checkPassword(Password);
        System.out.println("Arranging profile page...");
    }
    public boolean checkUsername(String u){
        return true;
    }
    public boolean checkPassword(String p){
    	  return false;
    }
    public boolean checkEmail(String e1, String e2){
    	  if(e1.equals(e2)&&e1.contains("@"))
    	  	return true;
        return false;
    }
    public boolean checkNewPassword(String p1, String p2){
    	  if(p1.equals(p2))
    	  	return true;
        return false;
    }
    private class EraserThread implements Runnable {
        
        private boolean stop;

         /**
         *@param The prompt displayed to the user
         */
        public EraserThread(String prompt) {
        System.out.print(prompt);
        }

        /**
        * Begin masking...display asterisks (*)
        */
        public void run () {
            stop = true;
            while (stop) {
                System.out.print("\010*");
                try {
                    Thread.currentThread().sleep(1);
                }catch(InterruptedException ie) {
                    ie.printStackTrace();
                }
            }
        }
    }
}
