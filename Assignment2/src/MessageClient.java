import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.LinkedList;

/**
 * MessageClient contains logic for the client side of the corresponding MessageServer. Contains an input stream
 * for information that is sent to the client. Also has a list of observers that listens to the client for changes
 * in its data.
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

    /**
     * Adds the specified eventObserver to the internal list of observers. Entry must implement EventObserver.
     * @param eventObserver the specified eventObserver.
     */
    public void registerObserver(EventObserver eventObserver) {
        observers.add(eventObserver);
    }

    /**
     * When this function is called, it notifies all the registered observers that change has occured.
     * @param message
     */
    public void alertObservers(Message message) {
        for (EventObserver eventObserver : observers) eventObserver.onEvent(message);
    }

    /**
     * Inner class that operates on a seperate thread and is instanciated once for each client. Constantly
     * listens for new messages on the input stream and notifies the observers when a new message has been read.
     */
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
