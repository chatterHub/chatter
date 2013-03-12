package networking;

import java.awt.Color;
import java.util.Scanner;
import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import ai.AI;

import main.Board;
import ui.BaseWindow;
import ui.ButtonTemplate;
import ui.Players;


public class QServer extends Thread {

    private Socket s;
    private static ServerSocket ss = null;
    private PrintStream out;
    private Scanner in;
    private static int count = 1;
    private int playerNumber, playerCount;
    private Board board;
    
    public boolean isAI;
    public boolean legalPlayer;

    public QServer() {
        s = null;
    }

    public QServer(Socket sock) {
        s = sock;
        new Thread(this).start();
    }

    public void go() {
        try {
            ss = new ServerSocket(3007);
            System.out.println("Server running on 3007");
        } catch (IOException ex) {
            System.err.println("serversocket error.");
        }
        while (true) {
            try {
                Socket sock = ss.accept();
                new QServer(sock);
            } catch (Exception ex) {
                System.out.println("new connection failure.");
            }
        }
    }

    public void run() {
        legalPlayer = true;        
        String incoming;
        Scanner in;
        try {
            this.out = new PrintStream(s.getOutputStream(), true);
            in = new Scanner(s.getInputStream());
            if (in.hasNext() && legalPlayer) {
                incoming = in.nextLine();
                inputHandle(incoming);
            }
            while (in.hasNext() && legalPlayer) {
                incoming = in.nextLine();
                System.out.println("Player " + playerNumber + ": " + incoming);
                inputHandle(incoming);
            }
            s.close();
        } catch (Exception e) {
            System.err.println("SERVER ERROR");
            System.exit(1);
        }
    }

    public void getMove(){
    	/*if(isAI){
    		AI ai = new AI(board);
    		String move = ai.ChooseMove();
    		out.println("MOVE M (0, 4) (" +move.substring(0,1) + ", " + move.substring(1) + ")\n");
    	}*/
        Scanner sc = new Scanner(System.in);
        String inMove = sc.nextLine();
        out.print(inMove + "\n");
        //int [] a = ButtonTemplate.moveReq;
        //out.println("MOVE M (0, 0) " +"(" + a[0] + ", " + a[1] + ")" + "\n");
    }
    
    public void loadBoard(){
    	//checkAI();
        board = new Board(playerCount, false);
    } 
    
    public void sendName(){
        out.print("READY angryMan" + playerNumber + "\n");
    }
    
    public void inputHandle(String s) {
        Scanner sc = new Scanner(s);
        String op = sc.next();
        if (op.equals("QUORIDOR")) {
            playerCount = sc.nextInt();
            playerNumber = sc.nextInt();
            sendName();
            loadBoard();
        } else if (op.equals("MOVE?")) {
            getMove();
            //BaseWindow.f.highlightLegal(p.getX(this.f.getColor()), p.getY(this.f.getColor()));
        } else if (op.equals("MOVED")) {
            movePlayer(sc.nextLine());
        } else if (op.equals("REMOVED")) {
            int i = sc.nextInt();
            if(i == playerNumber) legalPlayer = false;  //closes connection.               
        } else if (op.equals("WINNER")) {
            int winner = sc.nextInt();
            System.out.println("Player " + winner + " has won the game. nice try!");
            /*
             * END THE GAME
             */
        } else if (op.equals("DRAW")) {
            System.out.println("There has been a draw in the game.");
        }

    }
    
    public void movePlayer(String s){
        /*MOVE PLAYER*/
    }
    
    public void checkAI(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Player " + playerNumber + "AI? (y/n): ");
        String ai = sc.next();
        if(ai.equals("y")){
            isAI = true;
        }
        else{
            isAI = false;
        }
    }
    
    public static void main(String[] args) {
        //Scanner sc = new Scanner(System.in);
        try {
            QServer server = new QServer();
            server.go();
        } catch (Exception ex) {
            System.out.println("serversocket failure.");
        }
    }
}