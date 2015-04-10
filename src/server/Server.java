package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	private static ServerSocket serverSocket;
	private static int listeningPort = 9999;

	public static void main(String args[]) {
		Socket socket;
		PrintWriter out;
		BufferedReader in;

		try {
			serverSocket = new ServerSocket(listeningPort);
			System.out.println("Server started.");
			while (true) {
				socket = serverSocket.accept();

				in = new BufferedReader(new InputStreamReader(
						socket.getInputStream()));
				out = new PrintWriter(socket.getOutputStream(), true);
				System.out.println("Server started.");

				String input = in.readLine();
				System.out.println(input);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
