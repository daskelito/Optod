import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.LinkedList;

public class ObjectFileProducer implements MessageProducer{
    private int times;
    private int delay;
    private int size;
    private LinkedList<Message> messages = new LinkedList<Message>();

    public ObjectFileProducer(String filename){
      try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))){
          times = ois.readInt();
          delay = ois.readInt();
          size = ois.readInt();
          for(int i = 0; i < size(); i++){
              messages.add((Message) ois.readObject());
          }
      } catch (IOException | ClassNotFoundException e){

      }

    }

    @Override
    public int delay() {
        return delay;
    }

    @Override
    public int times() {
        return times;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Message nextMessage() {
        if (size == 0) return null;
        return messages.removeFirst();
    }
}
