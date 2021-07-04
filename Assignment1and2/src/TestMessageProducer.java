import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class TestMessageProducer {
    private static ArrayProducer getArrayProducer(int times, int delay) {
        String filepath = "C:\\Users\\Dragon\\IdeaProjects\\Optod\\Assignment1and2\\";
        Message[] messages = {new Message("UP", new ImageIcon(filepath + "images/new1.jpg")),
                new Message("Going down.", new ImageIcon(filepath + "images/new2.jpg")),
                new Message("Going down..", new ImageIcon(filepath + "images/new3.jpg")),
                new Message("Going down...", new ImageIcon(filepath + "images/new4.jpg")),
                new Message("Going down....", new ImageIcon(filepath + "images/new5.jpg")),
                new Message("Almost down", new ImageIcon(filepath + "images/new6.jpg")),
                new Message("DOWN", new ImageIcon(filepath + "images/new7.jpg")),
                new Message("Going up.", new ImageIcon(filepath + "images/new8.jpg")),
                new Message("Going up..", new ImageIcon(filepath + "images/new9.jpg")),
                new Message("Almost up", new ImageIcon(filepath + "images/new10.jpg"))};
        return new ArrayProducer(messages, times, delay);
    }

    private static void writeToObjectStream(String filename, MessageProducer mp) {
        try (FileOutputStream fos = new FileOutputStream(filename);
             BufferedOutputStream bos = new BufferedOutputStream(fos);
             ObjectOutputStream oos = new ObjectOutputStream(bos)) {
            oos.writeInt(mp.times());
            oos.writeInt(mp.delay());
            oos.writeInt(mp.size());
            for (int i = 0; i < mp.size(); i++) {
                oos.writeObject(mp.nextMessage());
            }
            oos.flush();
        } catch (IOException e) {
            System.err.println(e.toString());
        }
    }

    public static void main(String[] args) {
        Viewer viewer = new Viewer(300, 200);
        Viewer.showPanelInFrame(viewer, "From MessageProducer", 100, 50);
        String filepath = "C:\\Users\\Dragon\\IdeaProjects\\Optod\\Assignment1and2\\";
        //MessageProducer mp = getArrayProducer(2,100);
        //MessageProducer mp = new TextfileProducer(filepath + "new.txt");
        writeToObjectStream(filepath + "files/new.dat", getArrayProducer(4, 100));
        MessageProducer mp = new ObjectfileProducer(filepath + "files/new.dat");
        Message message;
        int times = mp.times();
        int delay = mp.delay();
        int size = mp.size();
        System.out.println(times + " " + delay + " " + size);
        for (int i = 0; i < times; i++) {
            for (int j = 0; j < size; j++) {
                message = mp.nextMessage();
                try {
                    viewer.setMessage(message);
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                }
            }
        }
    }
}
