import javax.swing.*;
import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;

public class Exercise3{

    private URL sourceUrl = new URL("https://webshare.mah.se/al4575/dog.png");

    public Exercise3() throws MalformedURLException {
        JFrame frame = new JFrame();
        ImageIcon icon = new ImageIcon(sourceUrl);
        JLabel label1 = new JLabel(icon);
        JLabel label2 = new JLabel(icon);
        frame.setLayout(new GridLayout(1,3));
        frame.add(label1);
        frame.add(label2);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) throws MalformedURLException {
        Exercise3 ex3 = new Exercise3();
    }
}



