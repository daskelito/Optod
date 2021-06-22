import java.util.Timer;
import java.util.TimerTask;

public class Exercise1 {
    private String[] strings;
    private long pause;
    private Timer timer;

    public Exercise1(String[] strings) {
        this(strings, 1000);
    }

    public Exercise1(String[] strings, long pause) {
        this.strings = strings.clone();
        this.pause = pause;
        timer = new Timer();
        timer.schedule(new ToDo(), 0, 1000);
    }

    public class ToDo extends TimerTask {
        private int index = 0;
        public void run() {
            if (index < strings.length) {
                System.out.println(strings[index]);
                index++;
            } else {
                timer.cancel();
            }
        }
    }

    public static void main(String[] args) {
        //String[] strings = {"Måndag", "Tisdag", "Onsdag", "Torsdag", "Fredag", "Lördag", "Söndag"};
        //Exercise1 ex1 = new Exercise1(strings);
        String[] strings1 = {"Jag heter Rut", "Du heter Sven", "Han heter Ola"};
        String[] strings2 = {"Måndag", "Tisdag", "Onsdag", "Torsdag", "Fredag", "Lördag", "Söndag"};
        Exercise1 ex1a = new Exercise1(strings1, 10000);
        Exercise1 ex1b = new Exercise1(strings2, 333);
    }
}






