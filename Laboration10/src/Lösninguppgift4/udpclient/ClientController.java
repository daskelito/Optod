package Lösninguppgift4.udpclient;

import java.net.InetAddress;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class ClientController {
	private UserInput clientUI = new UserInput(this);
	private UDPClient connection;

	public ClientController(String ipAddress, int port) {
		InetAddress address;
		ServerListener listener = new ServerListener() {
			public void receive(String s) {
				clientUI.setResult(s);
			}
		};
		try {			
			connection = new UDPClient(ipAddress,port,listener);
			showClient();
		} catch(Exception e) { 
			System.err.println(e);
		}
	}


	private void showClient() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				clientUI = new UserInput(ClientController.this);
				JFrame frame = new JFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.add(clientUI);
				frame.pack();
				frame.setVisible(true);				
			}
		});
	}

	public void newCalculation(String nbr1, String nbr2, String operation) {
		connection.send(nbr1+","+operation+","+nbr2);
	}
}
