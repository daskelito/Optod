public class MessageManager extends Thread {

    private Buffer<Message> messageBuffer;
    //lista av viewers?


    @Override
    public void run(){
        try {
            messageBuffer.get()
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
