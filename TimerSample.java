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
		public void run() {
			System.out.println("\nThe Timer has expired");
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

