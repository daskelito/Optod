public class P2Viewer extends Viewer implements EventObserver {

    private MessageClient messageClient;

    public P2Viewer(MessageClient messageClient, int width, int height) {
        super(width, height);
        this.messageClient = messageClient;
        messageClient.registerObserver(this);
    }

    @Override
    public void onEvent(Message message){
        setMessage(message);
    }
}
