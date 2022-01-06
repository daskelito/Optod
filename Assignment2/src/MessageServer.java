import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class MessageServer {
    private MessageManager messageManager;
    private Message message;
    private ServerSocket serverSocket;
    private ArrayList<ClientHandler> chList;

    public MessageServer(MessageManager messageManager, int port) throws IOException {
        this.messageManager = messageManager;
        serverSocket = new ServerSocket(port);
        chList = new ArrayList<ClientHandler>();
        new Connection(port).start();
    }


    private class Connection extends Thread {
        private int port;

        public Connection(int port) {
            this.port = port;
        }

        public void run() {
            try (ServerSocket serverSocket = new ServerSocket(port)) {
                while (!Thread.interrupted()) {
                    Socket socket = serverSocket.accept();
                    new ClientHandler(messageManager, socket);
                    System.out.println("Clienthandler created");
                }
            } catch (IOException e) {
                System.err.println(e);
            }
        }
    }

    private class ClientHandler extends Thread {
        private Socket clientSocket;
        private ObjectOutputStream oos;
        private MessageManager innerMessageManager;

        public ClientHandler(MessageManager innerMessageManager, Socket socket) {
            this.clientSocket = socket;
            this.innerMessageManager = innerMessageManager;
            chList.add(this);
            try {
                oos = new ObjectOutputStream(new BufferedOutputStream(clientSocket.getOutputStream()));
                start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void run() {
            while (true) {
                try {
                    message = innerMessageManager.getNextMessage();
                    oos.writeObject(message);
                    oos.flush();
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }

    }

}

