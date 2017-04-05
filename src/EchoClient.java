import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.BufferedReader;
import java.util.Scanner;
import java.net.Socket;


public final class EchoClient {

	public static void main(String[] args) throws Exception {

		try (Socket socket = new Socket("localhost", 22222)) {
			// echo from server
			InputStream is = socket.getInputStream();
			InputStreamReader isr = new InputStreamReader(is, "UTF-8");
			BufferedReader br = new BufferedReader(isr);
			// send data to server
			OutputStream os = socket.getOutputStream();
			PrintStream out = new PrintStream(os, true, "UTF-8");
			
			String input = "";
			String serverInput = "";
			Scanner sc = new Scanner(System.in);

			// starts client loop
			System.out.printf("Client> ");
			while (sc.hasNext()) {
				input = sc.nextLine();
				if(input.equals("exit")){
					out.println(input);
					System.out.println("Client disconnect.");
					System.exit(0);
				} else {
					out.println(input);
					serverInput = br.readLine();
					System.out.println("Server> " + serverInput);
					System.out.printf("Client> ");
				}
			}
		}
	}
}