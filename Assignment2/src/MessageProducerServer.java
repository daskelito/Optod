import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class MessageProducerServer extends Thread {
    private MessageProducerInput mpInput;
    private int port;
    private ObjectInputStream ois;

    public MessageProducerServer(MessageProducerInput mpInput, int port) {
        this.mpInput = mpInput;
        this.port = port;
    }

    public void startServer() {
        start();
    }

    @Override
    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                Socket socket = serverSocket.accept();
                ois = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
                MessageProducer messageProducer = (MessageProducer) ois.readObject();
                mpInput.addMessageProducer(messageProducer);
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e);
        }
    }

}
