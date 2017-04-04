import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public final class EchoServer {

	public static void main(String[] args) throws Exception {
		try (ServerSocket serverSocket = new ServerSocket(22222)) {
			while (true) {
				try (Socket socket = serverSocket.accept()) {
					//	indicate client connected
					String address = socket.getInetAddress().getHostAddress();
					System.out.printf("Client connected: %s%n", address);
					// get client input
					InputStream is = socket.getInputStream();
					InputStreamReader isr = new InputStreamReader(is, "UTF-8");
					BufferedReader br = new BufferedReader(isr);
					// send client data
					OutputStream os = socket.getOutputStream();
					PrintStream out = new PrintStream(os, true, "UTF-8");
					String clientInput = "";
					
                    while(!clientInput.equals("exit")) {
                    	clientInput = br.readLine();
        				out.println(clientInput);
                    }
				}
			}
		}
	}
}
