package l5alarm;

import java.util.LinkedList;

public class AlarmThread {
    private Thread thread;
    private long ms;
    private LinkedList<AlarmListener> list = new LinkedList<AlarmListener>();

    public AlarmThread(long ms) {
        this.ms = ms;
    }

    public void addListener(AlarmListener listener) {
        list.add(listener);
    }

    public void startAlarm() {
        if (thread == null) {
            thread = new AT();
            thread.start();
        }
    }

    private class AT extends Thread {
        public void run() {
            try {
                Thread.sleep(ms);
            } catch (InterruptedException e) {

            }
            for (AlarmListener l : list) l.alarm();
            thread = null;
        }
    }
}
