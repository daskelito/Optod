import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Exercise1 {

    public Exercise1() {
        JFrame frame = new JFrame("IP");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new UI());
        frame.pack();
        frame.setVisible(true);
    }

    private class UI extends JPanel implements ActionListener {
        private JLabel lblLocalIp = new JLabel(" ");
        private JTextField tfHostname = new JTextField();
        private JLabel lblIp = new JLabel(" ");

        public UI() {
            JPanel pnlHostname = new JPanel(new BorderLayout());
            pnlHostname.add(new JLabel("Host name;"), BorderLayout.WEST);
            pnlHostname.add(tfHostname);

            lblLocalIp.setFont(new Font("SansSerif", Font.PLAIN, 14));
            try {
                InetAddress localAddress = InetAddress.getLocalHost();
                lblLocalIp.setText("IP: " + localAddress.getHostAddress() + ", Namn: " + localAddress.getHostName());
            } catch (UnknownHostException e) {
                lblLocalIp.setText("IP: - , Namn: - ");
            }

            setLayout(new GridLayout(3, 1));
            setPreferredSize(new Dimension(300, 100));
            add(lblLocalIp);
            add(pnlHostname);
            add(lblIp);

            tfHostname.addActionListener(this);
        }

        public void actionPerformed(ActionEvent e) {
            try {
                InetAddress address = InetAddress.getByName(tfHostname.getText());
                lblIp.setText("Host ip: " + address.getHostAddress());
            } catch (UnknownHostException e1) {
                e1.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Exercise1();
            }
        });
    }

}
