import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class P1Viewer extends Viewer {

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
