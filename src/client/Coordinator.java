package client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Map;

/**
 * Deals with connection to attackers to broadcast the time of attack and the
 * target.
 * 
 * @author cse23170 (212166906)
 *
 */
public class Coordinator implements Attacker {

	private int timeOfAttack;
	private String attackingIP;
	private int attackingPort;

	protected static PrintWriter out;
	protected static Socket echoSocket;

	protected static String hostName;
	protected Map<Integer, String> attackers;

	/**
	 * Constructor used by the user interface to set the desired class
	 * parameters
	 * 
	 * @param time
	 *            - the time of attack.
	 * @param attks
	 *            - the list of attackers.
	 * @param attIP
	 *            - the targets ip address.
	 * @param attPort
	 *            - the target port.
	 */
	public Coordinator(int time, Map<Integer, String> attks, String attIP,
			int attPort) {
		this.timeOfAttack = time;
		this.attackers = attks;
		this.attackingIP = attIP;
		this.attackingPort = attPort;
	}

	/**
	 * Send the time of attack
	 */
	public void attack() {
		try {
			sendTOA();
			System.out.println("ATTACK!!!!!!!!");

		} catch (UnknownHostException e) {
			System.out.println("NONE ONE IS HOME!!");

		} catch (ConnectException e) {
			System.out
					.println("Either the attackers already know the time of attack, or don't want to know!");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Send the time to the attackers
	 * 
	 * @throws IOException
	 * @throws UnknownHostException
	 */
	@SuppressWarnings("resource")
	protected void sendTOA() throws UnknownHostException, ConnectException,
			IOException {
		String aip;
		int aport;
		for (Map.Entry<Integer, String> entry : attackers.entrySet()) {
			System.out.println("IP: " + entry.getKey() + " port: "
					+ entry.getValue());
			aip = entry.getValue();
			aport = entry.getKey();
			echoSocket = new Socket(aip, aport);

			try {
				out = new PrintWriter(echoSocket.getOutputStream(), true);
			} catch (IOException e) {
				e.printStackTrace();
			}

			out.println(timeOfAttack);
			out.println(attackingIP);
			out.println(attackingPort);

			close();

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
