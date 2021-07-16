import javax.swing.*;
import java.io.*;
import java.util.LinkedList;

public class TextfileProducer implements MessageProducer, Serializable {
    private int times;
    private int delay;
    private int size;
    private LinkedList<Message> messages = new LinkedList<Message>();
    private int currentIndex = -1;

    /**
     * Creates an instance of the TextfileProducer-object. A filename/path is required as a paramter from which the
     * object reads data using a FileInputStream, within an InputStreamReader, within a BufferedReader.
     * Times, delay and size are read from the first 3 lines. Times is the amount to repeat the whole lines of messages,
     * delay is the amount of time between each message and size is the amount of text/image-pairs in the file. A pair
     * of one text and one image is added to a Message-object and then added to a list of messages within the object.
     * @param filename
     */
    public TextfileProducer(String filename) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename), "UTF-8"))) {
            times = Integer.parseInt(br.readLine());
            delay = Integer.parseInt(br.readLine());
            size = Integer.parseInt(br.readLine());
            for (int i = 0; i < size; i++) {
                String s = br.readLine();
                String iconFilePath = br.readLine();
                iconFilePath = "C:\\Users\\Dragon\\IdeaProjects\\Optod\\Assignment1\\" + iconFilePath;
                Message m = new Message(s, new ImageIcon(iconFilePath));
                messages.add(m);
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    /**
     * Delay is the amount of time between each message.
     * @return the delay in miliseconds
     */
    @Override
    public int delay() {
        return delay;
    }

    /**
     * Times is the amount to repeat the whole lines of messages
     * @return
     */
    @Override
    public int times() {
        return times;
    }

    /**
     * Size is the amount of text/image-pairs in the file.
     * @return the size property of the object.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Uses an index to loop through the list of messages. Method is used to get the next message in the list.
     * @return the next Message-object in the list of messages.
     */
    @Override
    public Message nextMessage() {
        if (this.size() == 0) return null;
        currentIndex = (currentIndex + 1) % messages.size();
        return messages.get(currentIndex);
    }
}
