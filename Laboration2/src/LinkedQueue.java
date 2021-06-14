import java.util.LinkedList;

import laboration1.QueueException;

public class LinkedQueue<T> implements Queue<T> {
	private LinkedList<T> queue = new LinkedList<T>();


	
public static void main(String[] args) {
		LinkedQueue<Double> queue = new LinkedQueue<Double>();
		for(double d = 10; d<40; d++) {
			queue.add(d);
		}
		System.out.println("size="+queue.size()+", first element="+queue.element());
		while(!queue.isEmpty()) {
			System.out.print(queue.remove()+" ");
		}
	}

}
