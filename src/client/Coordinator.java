package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class Coordinator {

	private int timeOfAttack;
//	private int port = 2080;
//	private String ip;
	
	private String attackingIP;
	private int attackingPort;
	
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
	
	public Coordinator(int time, ArrayList<Integer> attackerPorts, String attIP, int attPort) {
		this.timeOfAttack = time;
		this.ports = attackerPorts;
		this.hostName = "localhost";
		this.attackingIP = attIP;
		this.attackingPort = attPort;
	}
	
	public void attack(){
		try {
			sendTOA();
			System.out.println("ATTACK!!!!!!!!");

		} catch (UnknownHostException e) {
			System.out.println("NONE ONE IS HOME!!");

		} catch(ConnectException e){
		//	System.out.println("No one is listening on port: " + thisPort);
			System.out.println("Either the attackers already know the time of attack, or don't want to know!");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Send the time to the attackers
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	@SuppressWarnings("resource")
	protected void sendTOA() throws UnknownHostException, ConnectException, IOException {
		// loop over all ports of the attackers.
		for (int i = 0; i < ports.size(); i++) {
			int thisPort = ports.get(i);
			System.out.printf("  Port: %d\n", thisPort);
			echoSocket = new Socket(hostName, thisPort);

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
			out.println(attackingIP);
			out.println(attackingPort);

			this.close();
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
