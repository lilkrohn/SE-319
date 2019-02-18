package hw1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
	static ArrayList<ClientHandler> clientsList = new ArrayList<ClientHandler>();

	public static void main(String[] args) {
		try {

			// CREATE A NEW SERVERSOCKET
			ServerSocket sock = new ServerSocket(4444);
			System.out.println(sock); // to confirm connection
			String username = null;

			// create a forever loop here so that the server can always provide service
			while (true) {
				Socket clientSock = sock.accept(); // waiting for client to connect
				System.out.println("A user is connecting...");

				DataInputStream inStream = new DataInputStream(clientSock.getInputStream());
				username = inStream.readUTF();
				System.out.println(username + " has successfully been connected to the server");

				ClientHandler clientH = new ClientHandler(clientSock, username);
				clientsList.add(clientH);// add to client list

				Thread thread = new Thread(clientH);
				thread.start();
			}
		} catch (IOException e) {
			System.out.println("Server SIDE: clientSocket excpetion");
			e.printStackTrace();
		}
		// now the loop goes back to wait for any other clients
	}
}

class ClientHandler implements Runnable {
	Socket socket; // socket that connects to the client
	String name; // keeps track of user names

	ClientHandler(Socket socket, String username) {
		this.socket = socket;
		name = username;
	}

	@Override
	public void run() {
		try {
			// client enters the chatroom
			DataInputStream inStream = new DataInputStream(this.socket.getInputStream());
			DataOutputStream outStream = new DataOutputStream(this.socket.getOutputStream());
			outStream.writeUTF("Welcome to the chatroom, " + name + "!");
			outStream.flush();

			// this section will handle the clients chats
			while (true) {
				String input = inStream.readUTF();
				System.out.println(name + ": " + input);
				for (ClientHandler t1 : Server.clientsList) {
					if (!t1.socket.equals(this.socket)) {
						DataOutputStream output = new DataOutputStream(t1.socket.getOutputStream());
						output.writeUTF(name + ":  " + input);
						output.flush();
					}
				}
			}
		} catch (IOException e) {
			System.out.println("Server SIDE: ServerIO Exception");
			e.printStackTrace();
		}
	}
} // end