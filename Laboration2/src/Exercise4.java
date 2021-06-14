public class Exercise4 {
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
