package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;

public class Coordinator {

	private int timeOfAttack;
//	private int port = 2080;
//	private String ip;
	
	protected static PrintWriter out;
	protected static BufferedReader in;
	protected static BufferedReader stdIn;
	protected static Socket echoSocket;
	protected static String hostName;
	protected static int portNumber;
	protected static ArrayList<Integer> ports;

	
	// ATTACKER VARIABLES
	private int attackerPort;
	private String attackerIp;
	
	public Coordinator(int time, ArrayList<Integer> attackerPorts) {
		this.timeOfAttack = time;
		this.ports = attackerPorts;
		this.hostName = "localhost";
	}
	
	public void attack(){
		sendTOA();
		System.out.println("ATTACK!!!!!!!!");
	}
	
	
	/**
	 * Send the time to the attackers
	 */
	@SuppressWarnings("resource")
	protected void sendTOA() {
		// loop over all ports of the attackers.
		for (int i = 0; i < ports.size(); i++) {
			System.out.printf("  Port: %d\n", ports.get(i));
			try {
				echoSocket = new Socket(hostName, ports.get(i));
			} catch (UnknownHostException e) {
				System.out.println("NONE ONE IS HOME!!");
				break;

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				out = new PrintWriter(echoSocket.getOutputStream(), true);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				in = new BufferedReader(new InputStreamReader(
						echoSocket.getInputStream()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			stdIn = new BufferedReader(new InputStreamReader(System.in));
			
			out.println(timeOfAttack);
			
		}

		
	
	}

	
	/**
	 * Close the connection to the server
	 */
	protected static void close() {
		try {
			echoSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
}
