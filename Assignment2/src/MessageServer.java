import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class MessageServer {
    private MessageManager messageManager;
    private Message message;
    private ServerSocket serverSocket;


    public MessageServer(MessageManager messageManager, int port) throws IOException {
        this.messageManager = messageManager;
        new Connection(port).start();
    }


    private class Connection extends Thread {
        private int port;
        private int numofclients = 1;

        public Connection(int port) {
            this.port = port;
        }

        public void run() {
            try (ServerSocket serverSocket = new ServerSocket(port)) {
                while (!Thread.interrupted()) {
                    Socket socket = serverSocket.accept();
                    new ClientHandler(socket).start();
                    numofclients++;
                }
            } catch (IOException e) {
                System.err.println(e);
            }
        }
    }

    private class ClientHandler extends Thread {
        private Socket socket;
        private ObjectOutputStream oos;
        private Buffer<Message> buffer;


        public ClientHandler(Socket socket) {
            buffer = new Buffer<Message>();
            this.socket = socket;
            messageManager.addPropertyChangeListener(new PropertyChangeListener() {
                @Override
                public void propertyChange(PropertyChangeEvent evt) {
                    buffer.put((Message) evt.getNewValue());
                }
            });

            try {
                oos = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void run() {
            try {
                while (!Thread.interrupted()) {
                    message = buffer.get();
                    oos.writeObject(message);
                    oos.flush();
                }
            } catch (IOException | InterruptedException e) {
                System.err.println(e);
            }


        }

    }

}

