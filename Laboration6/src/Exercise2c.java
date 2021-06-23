public class Exercise2c {
    private String[] strings;
    private long pause;
    private int index;
    private InnerClass ic = new InnerClass();

    public Exercise2c(String[] strings) {
        this(strings, 1000);
    }

    public Exercise2c(String[] strings, long pause) {
        this.strings = strings.clone();
        this.pause = pause;
    }

    public void start(){
        ic.start();
    }

    public class InnerClass extends Thread{

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
    }

    public static void main(String[] args){
        String[] strings1 = {"Jag heter Rut", "Du heter Sven", "Han heter Ola"};
        String[] strings2 = {"Måndag", "Tisdag", "Onsdag", "Torsdag", "Fredag",
                "Lördag", "Söndag"};
        Exercise2c ex2a = new Exercise2c(strings1);
        Exercise2c ex2b = new Exercise2c(strings2);
        ex2a.start();
        ex2b.start();
    }
}
