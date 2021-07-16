import java.io.*;
import java.lang.reflect.Type;
import java.net.Socket;
import java.net.SocketException;

public class MessageProducerClient {
    private String host;
    private int port;
    private ObjectOutputStream oos;
    private Socket socket;

    public MessageProducerClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void send(MessageProducer mp)  {
        try {
            socket = new Socket(host, port);
            oos = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            oos.writeObject(mp);
            oos.flush();
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
