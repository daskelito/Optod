import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class TextfileProducer implements MessageProducer {
    private int times;
    private int delay;
    private int size;
    private LinkedList<Message> messages = new LinkedList<Message>();

    //1st row in the text file is times
    //2nd row is delay in ms
    //3rd row is size, number of string-icon pairs
    //4th row is a string (repeating every other)
    //5th row is an icon (repeating every other)
    public TextfileProducer(String filename) {
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
        if (size == 0) return null;
        return messages.removeFirst();
    }
}
