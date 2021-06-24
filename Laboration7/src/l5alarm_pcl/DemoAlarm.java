package l5alarm_pcl;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class DemoAlarm {
    public DemoAlarm(int ms) {
        AlarmThread at = new AlarmThread(ms);
        at.addAlarmListener(new AlarmPrinter());
        at.startAlarm();
    }

    public static void main(String[] args) {
        DemoAlarm da = new DemoAlarm(2000);
    }

    private class AlarmPrinter implements PropertyChangeListener {
        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            if (evt.getPropertyName().equals("alarm"))
                System.out.println("ALARM!");
        }
    }
}
