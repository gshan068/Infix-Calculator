// This is the Queue class to be used in InfixtoPostfix and InfixCalculator classes.

class Queue<E> extends URLinkedList<E> {
	// Constructors
	public Queue() { 
		super(); 
	}
	
	// return Queue size 
	public int GetLength() {
		return size;
	}
	
	/*// check if Linkedlist is empty, if it is empty, return true, otherwise, return false
	public boolean isEmpty() {
		return size == 0;
	}
	*/	
	/*// Initialize queue 
	private void init() {
		head = tail = new URNode<E>(null, null, null);
		size = 0;
	}
	
	// Reinitialize queue 
	public void clear() { 
		init(); 
	}
	*/
	// Put element on tail 
	public void enqueue(E it) {
		addLast(it);
	}
	
	// Remove and return element from front
	public E dequeue() {
		return pollFirst();
	}
	
	// return Front element 
	public E frontValue() {
		return peekFirst();
	}
	
	//test code:
	/*public static void main(String[] args) {
		Queue<Integer> s = new Queue<Integer>();
		for(int i = 0; i<10; i++) {
			s.enqueue(i);
	
		}
		
		System.out.println(s.GetLength());
		while(!s.isEmpty()) {
			System.out.println(s.dequeue());
		}
		System.out.println("end");
	}
	*/
}
