package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * Class allows connections on a certain, specified port.
 * 
 * @author cse23170 (212166906)
 *
 */
public class Server {

	private static ServerSocket serverSocket;
	private int listeningPort;

	public Server(int port) {
		this.listeningPort = port;
	}
	
	public int getPort(){
		return this.listeningPort;
	}

	public void connect() {
		Socket socket;
		BufferedReader in;
		

		// BEGIN LISTENING ON THE DESIRED PORT
		try {
			serverSocket = new ServerSocket(listeningPort);
			System.out.println("Server started on port: " + listeningPort);
			while (true) {
				socket = serverSocket.accept();

				in = new BufferedReader(new InputStreamReader(
						socket.getInputStream()));

				String input = in.readLine();
				System.out.println(input);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
