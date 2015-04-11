package client;

import java.util.Scanner;

public class ClientUser {
	private static Client attacker;

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String input, ip = null;
		int time = 0, port = 0, myport = 0;
		
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
					System.out.printf("  The port you've chosen is: %d and the ip you've chosen is: %s\n", port, ip);
				} catch (NumberFormatException e){
					System.out.print("Please enter a time of attack (HHMMSS): ");
					input = in.nextLine();
				}
		/*		
				System.out.print("Please enter a target ip: ");
				input = in.nextLine();
				
				ip = input;
				
				System.out.print("Please enter a target port: ");
				input = in.nextLine();

				try {
					port = Integer.parseInt(input);
					System.out.printf("  The port you've chosen is: %d and the ip you've chosen is: %s\n", port, ip);
				} catch (NumberFormatException e){
					System.out.print("Please enter a time of attack (HHMMSS): ");
					input = in.nextLine();
				}
				*/
			} else if(input.compareTo("exit") == 0){
				break;
			} else {
				System.out.println("Command not valid");
			}
			
			System.out.print("Please enter a command: ");

		}
		System.out.println("Hello, World!");
		System.out.println(System.currentTimeMillis());
	}

}
