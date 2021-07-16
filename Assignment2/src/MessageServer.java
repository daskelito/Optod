import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class MessageServer implements Runnable {
    private MessageManager messageManager;
    private Message message;
    private ServerSocket serverSocket;


    public MessageServer(MessageManager messageManager, int port) throws IOException {
        this.messageManager = messageManager;
        serverSocket = new ServerSocket(port);
        Thread serverthread = new Thread(this);
        serverthread.start();
    }

    @Override
    public void run() {
        while (true) {
            try {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Server running");
                new ClientHandler(clientSocket).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private class ClientHandler extends Thread {
        private Socket clientSocket;
        ObjectOutputStream oos;

        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
        }

        public void run() {
            try {
                oos = new ObjectOutputStream(new BufferedOutputStream(clientSocket.getOutputStream()));
                while (true) {
                    message = messageManager.getNextMessage();
                    oos.writeObject(message);
                    oos.flush();
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
