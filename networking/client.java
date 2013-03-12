package networking;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class client {
	public static void main(String [] args){
		try {
			Socket s = new Socket("localhost", 6666);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
