package Lösninguppgift4.udpserver;

import javax.swing.SwingUtilities;

public class StartServer {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					new CalcServer(3300);
				} catch(Exception e) {
					System.out.println("Program: "+e);
				}
			}
		});
	}
}
