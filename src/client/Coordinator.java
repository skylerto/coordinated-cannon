package client;

import java.util.ArrayList;

public class Coordinator {

	private int timeOfAttack;
	private int port;
	private String ip;
	
	// ATTACKER VARIABLES
	private int attackerPort;
	private String attackerIp;
	private ArrayList<Client> attackers;
	
	public Coordinator(int time) {
		this.timeOfAttack = time;
	}
	
	public void attack(){
		System.out.println("ATTACK!!!!!!!!");
	}

	
}
