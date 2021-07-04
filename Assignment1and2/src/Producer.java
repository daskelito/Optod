public class Producer extends Thread {
    private Buffer<MessageProducer> prodBuffer;
    private Buffer<Message> messageBuffer;

    /**
     * Creates producer that uses one thread and requires two instances of the Buffer class. One Buffer consists of
     * MessageProducers of various kinds and the other Buffer is a message buffer. The producers job is to take
     * messages from the MessageProducers and unpack them into the message buffer.
     *
     * @param prodBuffer
     * @param messageBuffer
     */
    public Producer(Buffer<MessageProducer> prodBuffer, Buffer<Message> messageBuffer) {
        this.prodBuffer = prodBuffer;
        this.messageBuffer = messageBuffer;
    }


    /**
     * The run()-method is invoked when starting the thread and takes the next MessageProducer in the buffer of
     * MessageProducers. It unpacks messages from them and loads them into the message buffer times() number of times.
     * Within each times() the size() may vary which is also taken into account.
     */
    @Override
    public void run() {
        while (prodBuffer.size() >= 0) {
            try {
                MessageProducer mp = prodBuffer.get();
                for (int k = 0; k < mp.times(); k++) {
                    for (int i = 0; i < mp.size(); i++) {
                        messageBuffer.put(mp.nextMessage());
                        sleep(mp.delay());
                    }
                }
            } catch (InterruptedException e) {
                System.err.println(e);
            }

        }
    }
}
