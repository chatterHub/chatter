import java.util.*;
import java.io.*;

public class ProfilePage {

    private Scanner sc;
    private String Username;
    private String Password;
    private String email;
    
    public ProfilePage(){
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
    
    public void createProfile(){
         System.out.println("WE are in CreateProfile!");
            
    }
    public void enterProfile(){
        System.out.print("Enter username: ");
        Username = sc.nextLine();
        System.out.print("Enter password: ");
        Password = sc.nextLine();
        System.out.println("Arranging profile page...");
    }
    public boolean checkUsername(String u){
        return false;
    }
    public boolean checkPassword(String u){
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
