import java.io.*;
import java.net.*;

public class server {
	public static void main(String argv[]) throws Exception {
		int n_port;
		int r_port;
		int req_code;
		
		if(argv.length!=1){
			System.out.print("server expect 1 argument\n");
			System.exit(0);
		}
		
		req_code = Integer.parseInt(argv[0]);
		ServerSocket welcomeSocket = new ServerSocket(0);
		n_port = welcomeSocket.getLocalPort();
		System.out.print("SERVER_PORT=" + n_port+"\n");
		
		while(true){
			Socket connectionSocket = welcomeSocket.accept();
			
			//check req_code whether same as received req_code from client
			BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
			int client_req_code = Integer.parseInt(inFromClient.readLine());
			if(req_code != client_req_code){
				connectionSocket.close();
				System.out.print("Received req_code from client is different from server\n");
				System.exit(0);
			}
			
			DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
			DatagramSocket serverSocket = new DatagramSocket(0);
			r_port = serverSocket.getLocalPort();
			outToClient.writeBytes(r_port+"\n");
			
			// UDP
			byte[] receiveData = new byte[1024];
			byte[] sendData = new byte[1024];
			
			DatagramPacket receivePacket = new DatagramPacket(receiveData,receiveData.length);
			serverSocket.receive(receivePacket);
			String receivedSentence = new String(receivePacket.getData());
			System.out.print("received sentece: " + receivedSentence +"\n");
			InetAddress IPAddress = receivePacket.getAddress();
			int port = receivePacket.getPort();
			String reversedSentence = new StringBuilder(receivedSentence).reverse().toString();
			sendData = reversedSentence.getBytes();
			DatagramPacket sendPacket = new DatagramPacket(sendData,sendData.length,IPAddress,port);
			serverSocket.send(sendPacket);
			serverSocket.close();
		}
	}
}
