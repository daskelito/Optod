public class Exercise2b implements Runnable {
    private String[] strings;
    private long pause;
    int index = 0;


    public Exercise2b(String[] strings) {
        this(strings, 1000);
    }

    public Exercise2b(String[] strings, long pause) {
        this.strings = strings.clone();
        this.pause = pause;
    }

    @Override
    public void run() {
        while (index != strings.length) {
            System.out.println(strings[index]);
            index++;
            try {
                Thread.sleep(pause);
            } catch (InterruptedException e) {
            }
        }
    }

    public static void main(String[] args) {
        String[] strings1 = {"Jag heter Rut", "Du heter Sven", "Han heter Ola", "Han heter Ola", "Han heter Ola"};
        String[] strings2 = {"Måndag", "Tisdag", "Onsdag", "Torsdag", "Fredag",
                "Lördag", "Söndag"};
        Thread t2a = new Thread(new Exercise2b(strings1));
        Thread t2b = new Thread(new Exercise2b(strings2));
        t2a.start();
        t2b.start();
    }
}
