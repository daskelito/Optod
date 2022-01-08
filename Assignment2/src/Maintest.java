import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Maintest {

    public static void main(String[] args) throws IOException {
        MessageProducer mp = TestP2Input.getArrayProducer(10, 10);

        Message message = mp.nextMessage();
        System.out.println(message.getText() + "  " + message.getIcon());
        message = mp.nextMessage();
        System.out.println(message.getText() + "  " + message.getIcon());
        message = mp.nextMessage();
        System.out.println(message.getText() + "  " + message.getIcon());
        message = mp.nextMessage();
        System.out.println(message.getText() + "  " + message.getIcon());
        message = mp.nextMessage();
        System.out.println(message.getText() + "  " + message.getIcon());
        message = mp.nextMessage();
        System.out.println(message.getText() + "  " + message.getIcon());




    }
}
