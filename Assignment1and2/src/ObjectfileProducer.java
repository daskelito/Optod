import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.LinkedList;

public class ObjectfileProducer implements MessageProducer{
    private int times;
    private int delay;
    private int size;
    private LinkedList<Message> messages = new LinkedList<Message>();
    private int currentIndex = -1;

    public ObjectfileProducer(String filename){
      try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))){
          times = ois.readInt();
          delay = ois.readInt();
          size = ois.readInt();
          for(int i = 0; i < size(); i++){
              messages.add((Message) ois.readObject());
          }
      } catch (IOException | ClassNotFoundException e){}
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
        if (this.size() == 0) return null;
        currentIndex = (currentIndex + 1) % messages.size();
        return messages.get(currentIndex);
    }
}
