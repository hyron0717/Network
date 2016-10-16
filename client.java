import java.io.*;
import java.net.*;

public class client {
	public static void main(String argv[]) throws Exception {
		String  server_address;
		String req_code;
		String msg;
		int n_port;
		int r_port;
		
		if(argv.length != 4){
			System.out.print("client expect 4 arguments\n");
			System.exit(0);
		}
		server_address = argv[0];
		n_port = Integer.parseInt(argv[1]);
		req_code = argv[2];
		msg = argv[3];
		
		// TCP
		Socket TCPclientSocket = new Socket(server_address, n_port);
		DataOutputStream outToServer = new DataOutputStream(TCPclientSocket.getOutputStream());
		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(TCPclientSocket.getInputStream()));
		outToServer.writeBytes(req_code+"\n");
		r_port = Integer.parseInt(inFromServer.readLine());
		System.out.print("r_port=" + r_port + "\n");
		TCPclientSocket.close();
		
		// UDP
		DatagramSocket UDPclientSocket = new DatagramSocket();
		InetAddress IPAddress = InetAddress.getByName(server_address);
		byte[] sendData = new byte[1024];
		byte[] receiveData = new byte[1024];
		sendData = msg.getBytes();
		
		DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length,IPAddress,r_port);
		UDPclientSocket.send(sendPacket);
		
		DatagramPacket receivePacket = new DatagramPacket(receiveData,receiveData.length);
		UDPclientSocket.receive(receivePacket);
		
		String modifiedSentence = new String(receivePacket.getData());
		System.out.print("Reversed Sentence: " + modifiedSentence + "\n");
		UDPclientSocket.close();
	}

}
