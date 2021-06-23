public class Exercise2a extends Thread {
    private String[] strings;
    private long pause;
    private int index = 0;

    public Exercise2a(String[] strings) {
        this(strings, 1000);
    }

    public Exercise2a(String[] strings, long pause) {
        this.strings = strings.clone();
        this.pause = pause;
    }


    @Override
    public void run() {

        while (index < strings.length) {
            System.out.println(strings[index]);
            index++;
            try {
                Thread.sleep(pause);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        String[] strings = {"Måndag", "Tisdag", "Onsdag", "Torsdag", "Fredag",
                "Lördag", "Söndag"};
        Exercise2a ex2a = new Exercise2a(strings);
        ex2a.start();
    }
}