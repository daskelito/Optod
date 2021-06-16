import java.util.LinkedList;

public class LinkedQueue<T> implements Queue<T> {
    private LinkedList<T> queue = new LinkedList<T>();


    public static void main(String[] args) {
        LinkedQueue<Double> queue = new LinkedQueue<Double>();
        for (double d = 10; d < 40; d++) {
            queue.add(d);
        }
        System.out.println("size=" + queue.size() + ", first element=" + queue.element());
        while (!queue.isEmpty()) {
            System.out.print(queue.remove() + " ");
        }
    }

    @Override
    public void add(T element) {
        queue.addLast(element);
    }

    @Override
    public T remove() {
        if (queue.size() == 0)
            throw new QueueException("dequeue: Queue is empty");
        return queue.removeFirst();
    }

    @Override
    public T element() {
        if (queue.size() == 0)
            throw new QueueException("peek: Queue is empty");
        return queue.removeFirst();
    }

    @Override
    public boolean isEmpty() {
        return (queue.size() == 0);
    }

    @Override
    public int size() {
        return queue.size();
    }
}
