package command;

import java.util.Scanner;

import client.Client;

/**
 * Command-line user interface for an attacker.
 * 
 *  Commands include:
 * 	- get time: 	listen on the specified port for the coordinator to broadcast the time of attack.
 * 	- attackinfo:	sets the listening port of the attacker.
 * 	- exit:			closes down the interface for the coordinator.
 * 
 * @author cse23170 (212166906)
 *
 */
public class ClientUser {
	private static Client attacker;

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		String input, ip = null;
		int port = 0, myport = 0;
		
		System.out.println("Welcome to Trojan Cannon, you're an attacker!");
		System.out.print("Please enter a command: ");

		while(in.hasNext())
		{
			input = in.nextLine();
			
			if(input.compareTo("get time") == 0){
				if(myport == 0){
					System.out.println("  Please run attackifo first...");	
				} else {
					System.out.println("  Waiting to receive time from Coordinator");
					attacker = new Client(myport);
					attacker.getTOA();
				}
			} else if(input.compareTo("attackinfo") == 0){
				
				System.out.print("Please enter a port to listen on: ");
				input = in.nextLine();

				try {
					myport = Integer.parseInt(input);
					System.out.printf("  The port you've chosen is: %d\n", myport);
				} catch (NumberFormatException e){
					System.out.print("  The port is not available.");
					input = in.nextLine();
				}
			} else if(input.compareTo("exit") == 0){
				break;
			} else {
				System.out.println("Command not valid");
			}
			
			System.out.print("Please enter a command: ");

		}
	}

}
