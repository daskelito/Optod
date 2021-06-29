public class Producer extends Thread {

    private Buffer<MessageProducer> producerBuffer;
    private Buffer<Message> messageBuffer;

    // takes message objects from the message producer and passes them on to message buffer
    @Override
    public void run() {
        Message m = null;
        while (producerBuffer.size() > 0) {
            try {
                m = (Message) producerBuffer.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            messageBuffer.put(m);
        }
    }
}
