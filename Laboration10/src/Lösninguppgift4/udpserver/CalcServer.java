package Lösninguppgift4.udpserver;

import java.io.IOException;
import java.net.*;
import java.util.*;

public class CalcServer {
	private UDPServer connection;

	public CalcServer(int requestPort) {
		TextWindow.println("Server: Start av CalcServer, Lyssnar på "+requestPort);
		connection = new UDPServer(requestPort, new ClientResponse());
	}

	private void send(InetAddress address, int clientPort, String response) {
		connection.send(address, clientPort, response);
	}

	private int calculate(String strNbr1, String operation, String strNbr2) {
		int result = 0, nbr1, nbr2;
		nbr1 = Integer.parseInt(strNbr1);
		nbr2 = Integer.parseInt(strNbr2);
		switch(operation.charAt(0)) {
		case '+' : result = nbr1 + nbr2; break;
		case '-' : result = nbr1 - nbr2; break;
		case '*' : result = nbr1 * nbr2; break;
		case '/' : result = nbr1 / nbr2; break;
		}
		return result;
	}

	private class ClientResponse implements ClientListener {
		public void receive(String message, InetAddress address, int port) {
			String response="";
			int result;
			String[] parts = message.split(",");
			try {
				TextWindow.println(message + " from "+address.getHostAddress()+":"+port);
				result = calculate(parts[0],parts[1],parts[2]);
				response = parts[0] + parts[1] + parts[2] + "=" + result;
			} catch(NumberFormatException e2) {
				response = e2.toString() + ": " + message;
			}
			send(address,port,response);
		}
	}
}
