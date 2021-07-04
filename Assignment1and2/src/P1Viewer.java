import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Observable;
import java.util.Observer;

public class P1Viewer extends Viewer {

    private MessageManager messageManager;


    public P1Viewer(MessageManager messageManager, int width, int height) {
        super(width, height);

        messageManager.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                setMessage((Message) evt.getNewValue());
            }
        });

    }

}
