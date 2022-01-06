import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.LinkedList;

public class MessageClient implements Runnable {
    private int port;
    private String host;
    private ObjectInputStream ois;
    private LinkedList<EventObserver> observers = new LinkedList<EventObserver>();

    public MessageClient(String host, int port) {
        this.host = host;
        this.port = port;
        Thread clientThread = new Thread(this);
        clientThread.start();
    }

    public void registerObserver(EventObserver eventObserver) {
        observers.add(eventObserver);
    }

    public void alertObservers(Message message) {
        for (EventObserver eventObserver : observers) eventObserver.onEvent(message);
    }

    @Override
    public void run() {
        try (Socket socket = new Socket(host, port)) {
            ois = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
            System.out.println(Thread.currentThread().getName() + ": Client connected");
            while (true) {
                Message message = (Message) ois.readObject();
                alertObservers(message);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
