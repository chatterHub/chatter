import java.util.*;
import java.io.*;

public class ProfilePage {

    private Scanner sc;
    private String Username;
    private String Password;
    private String Email;
    protected boolean userLoggedIn;
    
    public ProfilePage() throws FileNotFoundException{
        sc = new Scanner(System.in);
        System.out.print("\nAre you a new user? (yes/no) ");
        String yn = sc.nextLine().toLowerCase();
        while(!yn.equals("yes") && !yn.equals("y") 
            && !yn.equals("no") && !yn.equals("n")){
                System.out.println(yn+" is incorrect input, try again....");
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
         System.out.print("\nWhat would you like your username to be? ");
         String u = sc.nextLine();
         while(checkUsername(u)){
         	System.out.println("This username is already being used.");
         	System.out.print("What would you like your username to be? ");
         	u = sc.nextLine();
         }
         System.out.println();
         Username = u;
         
         //prompting for password with masking ***
         HidePassword hideThread = new HidePassword();
         hideThread.start();
         String p1 = "";
         String p2 = "";
         try {
            hideThread.hideInput = true;
            System.out.print("Enter a password: ");
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
         hideThread.stopThread= true;
         }
         catch (Exception e) {}
         System.out.println("\b\b"); 
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
         }catch(Exception e){}
         
         //printstreaming info to files
         PrintStream p = new PrintStream("Users/" + Username + ".txt");
         p.println("Username: " + Username);
         p.println("Password: " + Password);
         p.println("Level: 1");
         p.println("Points: 0");
         p.println("Questions Correct: 0");
         p.println("Questions Incorrect: 0");
         p.println("Total Questions: 0");
         p.println("E-mail: " + Email);
         
         //printstream into mailing list
         PrintStream f2 = new PrintStream("MailingList.txt");
         f2.println(Username + " : " + Email);
         f2.close();
         
         System.out.println("Profile Sucessfully Created!");
    
    }
    
    public void enterProfile() throws FileNotFoundException{
        System.out.print("Enter username: ");
        String u = sc.nextLine();
        boolean newProfileMade = false;
        while(!checkUsername(u)){
            System.out.println("Username not found...");
            System.out.print("Try again(T)? or Create profile(C)? ");
            String temp = sc.nextLine().toUpperCase();
            while(!temp.equals("T") && !temp.equals("C")){
                System.out.println(temp +" is not (T) or (C)...");
                System.out.print("Try again(T)? or Create profile(C)? ");
                temp= sc.nextLine().toUpperCase();
            }if(temp.equals("T")){
                System.out.print("\nEnter username: ");
                u = sc.nextLine();
            }else if(temp.equals("C")){
                newProfileMade = true;
                createProfile();
                break;
            } 
        }
        Username = u;
        if(!newProfileMade){
            HidePassword hideThread = new HidePassword();
            hideThread.start();
            try {
                hideThread.hideInput = true;
                System.out.print("Enter password: ");
                Password = sc.nextLine();
                while(!checkPassword(Username, Password)){
                    System.out.println("Invalid password");
                    System.out.print("Enter password: ");
                    Password = sc.nextLine();
                }
                hideThread.stopThread = true;
            }catch (Exception e) {}
        System.out.println("\b\b");
        userLoggedIn = true;
        System.out.println("Arranging profile page..."); 
        }
    }

    public boolean checkUsername(String u) throws FileNotFoundException{
        File f = new File("Users/"+u+".txt");
        if(f.exists()){
            return true;
        }
        return false;
     }
    
    public boolean checkPassword(String u, String p) throws FileNotFoundException{
          String temp = "";
          Scanner test = new Scanner(new File("Users/" + u + ".txt"));
          while(test.hasNext()){
                temp += test.next() + " ";
          }
          for(int i = 0;i<temp.length();i++){
                if(temp.contains(" " +u+ " ") && temp.contains(" "+p+" "))
                        return true;
          }
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
    
    private class HidePassword extends Thread {
        boolean stopThread= false;
        boolean hideInput= false;
        boolean shortMomentGone= false;
  
        public void run() {
            try {
                sleep(500);
            }catch (InterruptedException e) {}
            shortMomentGone= true;
            while (!stopThread) {
                if (hideInput) {
                    System.out.print("\b*");
                }try {
                    sleep(1);
                }catch (InterruptedException e) {}
            }             
        } 
    }
}
