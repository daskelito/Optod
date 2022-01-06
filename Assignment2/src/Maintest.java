import java.io.IOException;

public class Maintest {

    public static void main(String[] args) throws IOException {
        int port1 = 6060;
        int port2 = 8080;

        Buffer<Message> messageBuffer = new Buffer<Message>();
        Buffer<MessageProducer> producerBuffer = new Buffer<MessageProducer>();

        MessageManager messageManager = new MessageManager(messageBuffer);
        P1Viewer v1 = new P1Viewer(messageManager, 300, 200);
        P1Viewer v2 = new P1Viewer(messageManager, 320, 320);
        Viewer.showPanelInFrame(v1, "Viewer 1", 100, 50);
        Viewer.showPanelInFrame(v2, "Viewer 2", 450, 50);

        MessageServer messageServer = new MessageServer(messageManager, port1); // start av server
    }
}
