public class ObjectFileProducer implements MessageProducer{
    @Override
    public int delay() {
        return 0;
    }

    @Override
    public int times() {
        return 0;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Message nextMessage() {
        return null;
    }
}
