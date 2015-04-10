package command;

import java.util.Scanner;

import client.Coordinator;

public class CoordUser {
	
	private static Coordinator coord;
	
	public static void main(String args[]){
		Scanner in = new Scanner(System.in);
		String input, ip;
		int time, port;
		
		System.out.println("Welcome to Trojan Cannon!");
		System.out.print("Please enter a time of attack (HHMMSS): ");
		input = in.nextLine();
		while(!(input.compareTo("exit") == 0))
		{
			try {
				time = Integer.parseInt(input);
				System.out.printf("  The time you've chosen is: %d today\n", time);

			} catch (NumberFormatException e){
				System.out.print("Please enter a time of attack (HHMMSS): ");
				input = in.nextLine();
			}
			
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
			
			System.out.print("Ready to mount your attack?(y/n)");
			input = in.nextLine();
			if(input.compareTo("y") == 1){
				coord.attack();
			} else if(input.compareTo("n") == 1){
				// loop again.
			} else {
				// didn't recognie
			}
				

		}
		System.out.println("Hello, World!");
		System.out.println(System.currentTimeMillis());
	}

}
