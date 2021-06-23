package l5alarm;

public class DemoAlarm {
	public DemoAlarm(int ms) {
		AlarmThread bt = new AlarmThread(ms);
		bt.addListener(new AlarmPrinter());
		bt.addListener(new WakeUpPrinter());
		bt.addListener(new ConsolePrinter("Enkelrikta Öresundsbron"));
		bt.startAlarm();
	}

	private class AlarmPrinter implements AlarmListener{
	    public void alarm(){
	        System.out.println("ALARM!");
        }
    }

    private class WakeUpPrinter implements AlarmListener{
		public void alarm(){
			System.out.println("WAKE UP!");
		}
	}

	private class ConsolePrinter implements AlarmListener{
		private String message;

		public ConsolePrinter(String string){
			this.message = string;
		}

		public void alarm() {
			System.out.println(message);
		}
	}

	public static void main(String[] args) {
		DemoAlarm da = new DemoAlarm(1000);
	}
}
