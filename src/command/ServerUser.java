package command;

import java.util.Scanner;

import server.Server;

public class ServerUser {

	public static void main(String args[]) {
		Server server = null;
		Scanner sc = new Scanner(System.in);

		// READ IN A VALID PORT FOR THE SERVER TO LISTEN ON
		System.out.println("Please enter a valid port to listen on...");
		while (sc.hasNext()) {
			String input = sc.next();
			int port;

			try {
				port = Integer.parseInt(input);
				if (isValidPort(port)) {
					server = new Server(port);
					System.out.println("  Now listening on port: "
							+ server.getPort());
					break;
				} else {
					System.out
							.println("  not a valid port, must be between 1025 and 65535");
				}
			} catch (NumberFormatException e) {
				System.out
						.println("  A port must be a valid number between 1025 and 65535");
			}
			System.out.print("Please enter a valid port to listen on...");
		}
		if (server != null) {
			server.connect();
		}

	}

	/**
	 * Determines if the port is valid
	 * 
	 * @param port
	 *            - a number to attempt listening on
	 * @return - true if the port is available, false if the port isn't
	 */
	public static boolean isValidPort(int port) {
		return 1024 < port && port < 65535;
	}

}
