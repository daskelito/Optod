import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * MessageServer is a multithreaded where every client is handled by a thread. It contains a MessageManager that in
 * turn contains messages that is sent to the clients for displaying.
 *
 * @author Henrik Heinze
 * @version 1.0
 */

public class MessageServer {
    private MessageManager messageManager;
    private Message message;
    private ServerSocket serverSocket;


    /**
     * Constructor of the server that requires a messageManager implementation and a port. Starts an instance of
     * Connection in order to listen for a new connecting client.
     *
     * @param messageManager contains the messages and sends them to the server
     * @param port the specified port of where to establish the sockets for sending data.
     * @throws IOException
     */
    public MessageServer(MessageManager messageManager, int port) throws IOException {
        this.messageManager = messageManager;
        new Connection(port).start();
    }

    /**
     * Inner class that operates on a seperate thread and handles one connection to a client and maintains it for
     * the entire runtime of the server. Starts an instance of ClientHandler upon detected request from client.
     */
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

    /**
     * Inner class that operates on a seperate thread and is isntanciated once a request from a client connection
     * is accepted. Contains a buffer for the mssages that is to be sent over the output stream.
     */
    private class ClientHandler extends Thread {
        private Socket socket;
        private ObjectOutputStream oos;
        private Buffer<Message> buffer;

        /**
         * Takes a socket as a parameter which is connected to the serversocket created in the construction from the
         * inner class Connection. Has a PropertyChanceListener that listens for changes on the MessageManager
         * where it is registrated and updates the buffer while alerting the listener.
         *
         * @param socket socket of operation using the same port as the corresponding server socket.
         */
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

