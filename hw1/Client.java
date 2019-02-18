package hw1;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client {
	public Client() {
		try {
			// CONNECT TO THE SERVER AT PORT 4444
			Socket sock = new Socket("localhost", 4444);

			DataOutputStream outStream = new DataOutputStream(sock.getOutputStream());
			System.out.print("> Enter Username: ");

			// for handling multiple threads
			BufferedReader buffReader = new BufferedReader(new InputStreamReader(System.in));

			ClientHandler2 clientH = new ClientHandler2(sock);
			// create a new thread
			Thread thread = new Thread(clientH);
			thread.start();

			while (true) {
				String output = buffReader.readLine();
				outStream.writeUTF(output);
				// forces data from buffer to be sent to server
				outStream.flush();
				// client dies here
			}

		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Client SIDE: Client Exception");
		}
	}

	public static void main(String[] args) {
		new Client();
	}
}

class ClientHandler2 implements Runnable {
	Socket sock; // connects to server

	public ClientHandler2(Socket s) {
		sock = s;
	}

	@Override
	public void run() {
		DataInputStream input;
		try {
			input = new DataInputStream(sock.getInputStream());
			while (true) {
				String inputStr = null;
				inputStr = input.readUTF();

				if (!inputStr.equals(null) && !inputStr.equals("")) {
					System.out.println(inputStr);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Client SIDE: ClientIO Exception");
		} // end catch
	}// end run
}// end class