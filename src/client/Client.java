package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import server.Server;

public class Client implements Attacker {
	
	private static ServerSocket serverSocket;
	private static int portNumb;
	
	private int listeningPort;
	
	private String attackingIP;
	private int attackingPort;
	
	private int TOA;
	
	public Client(int myport, String attackIP, int attackPort){
		this.attackingIP = attackIP;
		this.attackingPort = attackPort;
		this.portNumb = myport;
	}
	
	/**
	 * Listen on the listening port, once an attack time has been sent, attack!
	 */
	public void getTOA(){
		Socket socket;
		PrintWriter out;
		BufferedReader in;
		
		try {
			socket = serverSocket.accept();
			
			in = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));

			out = new PrintWriter(socket.getOutputStream(), true);
			
			String input = in.readLine();
			System.out.println(input);
			
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
		
		
	}

}
