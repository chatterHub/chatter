import java.util.Timer;
import java.util.TimerTask;
import java.io.*;

public class TimerSample {
	Timer myTimer;
	int seconds;
	User u;
	
	public TimerSample(int s, User user) {
		myTimer = new Timer();
		u = user;
		seconds = s;
	}

	class Notification extends TimerTask {
	    
	    private int s;
	    
	    public Notification(){
	        s = seconds;
	    }
	
		public void run() {
		    System.out.println("\nYOU'RE OUT OF TIME, BAHAHAHA");
			try{
			    u.updateIncorrect();
			   ProfilePage p = new ProfilePage();
			   p.inProfile(u.Username);
			}catch(Exception e){}
		}
	}
	public void start(){
		myTimer.schedule(new Notification(), seconds*1000);
	}
	public void cancel() throws FileNotFoundException{
		myTimer.cancel();
		ProfilePage p = new ProfilePage();
		p.inProfile(u.Username);
	}
}

