package networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class client {
	public static void main(String [] args){
		try {
			Socket s = new Socket("localhost", 6666);
			PrintWriter out = new PrintWriter(s.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
			System.out.println("waiting for input...");
			System.out.println(in.readLine());
			System.out.println("received.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
