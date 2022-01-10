import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.LinkedList;

/**
 * MessageClient contains logic for the client side of the corresponding MessageServer.
 *
 * @author Henrik Heinze
 * @version 1.0
 */
public class MessageClient {
    private int port;
    private String host;
    private ObjectInputStream ois;
    private LinkedList<EventObserver> observers = new LinkedList<EventObserver>();


    /**
     * Constructor of the client that requires a host ip and a port on where to operate.
     * Initiates a new Connection upon creation.
     *
     * @param host
     * @param port
     */
    public MessageClient(String host, int port) {
        this.host = host;
        this.port = port;
        new Connection().start();
    }

    public void registerObserver(EventObserver eventObserver) {
        observers.add(eventObserver);
    }

    public void alertObservers(Message message) {
        for (EventObserver eventObserver : observers) eventObserver.onEvent(message);
    }

    private class Connection extends Thread {
        @Override
        public void run() {
            try (Socket socket = new Socket(host, port)) {
                ois = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
                System.out.println(Thread.currentThread().getName() + ": Client connected");
                while (!Thread.interrupted()) {
                    Message message = (Message) ois.readObject();
                    alertObservers(message);
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

    }

}
