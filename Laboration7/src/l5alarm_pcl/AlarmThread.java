package l5alarm_pcl;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class AlarmThread {
	private Thread thread;
	private long ms;

	private PropertyChangeSupport change = new PropertyChangeSupport( this );

	public AlarmThread(long ms) {
		this.ms = ms;
	}

	public void addAlarmListener(PropertyChangeListener listener){
		change.addPropertyChangeListener(listener);
	}

	public void removeAlarmListener(PropertyChangeListener listener){
		change.removePropertyChangeListener(listener);
	}

	public void startAlarm() {
		if(thread==null) {
			thread = new AT();
			thread.start();
		}
	}

	private class AT extends Thread {
		public void run() {
			try {
				Thread.sleep(ms);
			}catch(InterruptedException e) {
				
			}
			System.out.println("Nu är det dags för alarm!");
			change.firePropertyChange("alarm", false, true);
			thread = null;
		}
	}
}