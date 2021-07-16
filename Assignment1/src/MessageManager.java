import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Observable;

public class MessageManager extends Thread {
    private Buffer<Message> messageBuffer;
    private PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    private Message message;

    public MessageManager(Buffer<Message> messageBuffer) {
        this.messageBuffer = messageBuffer;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }

    public Message getNextMessage() throws InterruptedException {
        return messageBuffer.get();
    }

    @Override
    public void run() {
        try {
            while (messageBuffer.size() > -1) {
                Message oldMessage = this.message;
                Message newMessage = messageBuffer.get();
                pcs.firePropertyChange("Message", oldMessage, newMessage);
                this.message = newMessage;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
