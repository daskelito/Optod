package l5games;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;

public class Controller implements ResultController {
    private GamesUI resultUI;
    private GameResults result;

    public Controller() {
        try {
            result = new GameResults("Laboration7/games.txt");
            resultUI = new GamesUI(this);
            showFrame(resultUI);
        } catch (IOException e) {
        }
    }

    private void showFrame(JPanel panel) {
        JFrame frame = new JFrame("Game results");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void start() {
        if (result != null) {
            result.startSimulation();
        }
    }

    @Override
    public void stop() {
        if (result != null) {
            result.stopSimulation();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Controller controller = new Controller();
            }
        });
    }

    private class WindowUpdater implements PropertyChangeListener {
        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    if (evt.getPropertyName().equals("game") && evt.getNewValue() instanceof Game) {
                        Game game = (Game) evt.getNewValue();
                        resultUI.newResult(game.toString());
                    }
                }
            });
        }
    }
}
