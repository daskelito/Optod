public class ObjectQueue {
    private Object[] elements;
    private int size = 0;

    public ObjectQueue(int capacity) {
        elements = new Object[capacity];
    }

    public void add(Object elem) {
        if (size < elements.length) {
            throw new QueueException("enqueue: Queue is full");
        }
        elements[size++] = elem;
    }

    public Object remove() {
        if (size == 0) {
            throw new QueueException("dequeue: Queue is empty");
        }
        Object value = elements[0];
        size--;
        for (int i = 1; i < size; i++) {
            elements[i - 1] = elements[i];
        }
        elements[size] = null;
        return value;
    }

    public Object element() {
        if (size == 0) {
            throw new QueueException("peek: Queue is empty");
        }
        return elements[0];
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    public int size() {
        return size;
    }
}
