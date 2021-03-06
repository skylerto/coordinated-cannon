package command;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import client.Coordinator;

/**
 * Command-line user interface for an attack coordinator.
 * 
 * Commands include: 
 * - settime:	 	send the time of attack to a list of attackers,
 * 					must have already specified the attackers ip 
 * 					addresses and ports they’re listening on.
 * 
 *  - attackinfo: 	specify a list of attackers (ip address and port) 
 *  				and a target ip and port.
 *  
 *  - attack:		send the clients the time of attacker and target, 
 *  				then let them DoS their target.
 *  
 *  - exit:			Closes down the interface for the coordinator.
 * 
 * @author cse23170 (212166906)
 *
 */
public class CoordUser {

	private static Coordinator coord;

	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		String input, ip = null;
		int time, port = 0;
		Map<Integer, String> ports = new HashMap<Integer, String>();

		System.out.println("Welcome to Trojan Cannon, you're a coordinator!");
		System.out.print("Please enter a command: ");

		while (in.hasNext()) {
			input = in.nextLine();

			if (input.compareTo("settime") == 0) {
				if (ports.size() < 1) {
					System.out.println("  please run attackinfo first");
				} else {
					System.out
							.print("Please enter a time of attack (HHMMSS): ");
					input = in.nextLine();

					try {
						time = Integer.parseInt(input);
						System.out
								.printf("  The time you've chosen is: %d today\n",
										time);
						coord = new Coordinator(time, ports, ip, port);

					} catch (NumberFormatException e) {
						System.out
								.print("Please enter a time of attack (in milliseconds from now): ");
						input = in.nextLine();
					}
				}

			} else if (input.compareTo("attack") == 0) {
				System.out.print("  Ready to mount your attack?(y/n)");
				input = in.nextLine();
				if (input.compareTo("y") == 0) {
					if (coord == null) {
						System.out.println("  Please run settime");
					} else {
						coord.attack();
					}
				} else if (input.compareTo("n") == 1) {
					// loop again.
				} else {
					// didn't recognie
				}
			} else if (input.compareTo("attackinfo") == 0) {

				System.out
						.print("Please enter an attacker port to sent time: ");
				do {
					input = in.nextLine();
					if (input.compareTo("done") == 0) {
						break;
					} else {
						try {
							port = Integer.parseInt(input);
							System.out.printf(
									"  The port you've chosen is: %d \n", port);

							System.out
									.print("Please enter an attacker ip address: ");

							input = in.nextLine();
							System.out.println("  The ip you've chosen is: "
									+ input);
							ports.put(port, input);
						} catch (NumberFormatException e) {
							System.out.println("Not a number! ");
							break;
						}
						System.out
								.print("Please enter another attacker port, or say done: ");
					}
				} while (in.hasNext());

				System.out.print("Please enter a target ip: ");
				input = in.nextLine();

				ip = input;

				System.out.print("Please enter a target port: ");
				input = in.nextLine();

				try {
					port = Integer.parseInt(input);
					System.out
							.printf("  The port you've chosen is: %d and the ip you've chosen is: %s\n",
									port, ip);
				} catch (NumberFormatException e) {
					System.out
							.print("Please enter a time of attack (HHMMSS): ");
					input = in.nextLine();
				}
			} else if (input.compareTo("exit") == 0) {
				break;
			} else {
				System.out.println("Command not valid");
			}

			System.out.print("Please enter a command: ");

		}
	}
}
