import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class TextfileProducer implements MessageProducer {
    private int delay;
    private int times;
    private int size;
    private LinkedList<Message> messages = new LinkedList<Message>();

    //first row in the text file is times
    //second row is delay in ms
    //third row is size, number of text-picture pairs
    private TextfileProducer(String filename) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename), "UTF-8"))) {
            times = Integer.parseInt(br.readLine());
            delay = Integer.parseInt(br.readLine());
            size = Integer.parseInt(br.readLine());
            for(int i = 0; i < size; i++){
                String s  = br.readLine();
                // Icon icon = TYPECAST ICON FROM STRING br.readLine();
                Message m = new Message(s, icon);
                messages.add(m);
            }
        } catch (IOException e) {
            System.err.println(e);
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
        return null;
    }
}
