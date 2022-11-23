// This is the Stack class to be used in InfixtoPostfix and InfixCalculator classes.

class Stack<E> extends URLinkedList<E>{
	
	// Constructors
	public Stack() { 
		super();
	}
	
	
	// return Linkedlist's size
	public int GetLength() {
		return size;
	}
	
	// check if Linkedlist is empty, if it is empty, return true, otherwise, return false
//	public boolean isEmpty() {
//		return size == 0;
//	}
	
	// Reinitialize stack 
	/*public void clear() { 
		head = null; 
		size = 0; 
	}
	*/
	
	// Put "it" on top of stack 
	public void push(E it) {
		addFirst(it);
	
	}
	
	// Remove and return "it" at top of stack
	public E pop() {
		return pollFirst();
	}
	
	// Return but does not remove item at top of stack
	public E peek() {
		return peekFirst();
	}
	
	//test code:
	/*public static void main(String[] args) {
		Stack<Integer> s = new Stack<Integer>();
		for(int i = 0; i<10; i++) {
			s.push(i);
	
		}
		
		System.out.println(s.GetLength());
		while(!s.isEmpty()) {
			System.out.println(s.pop());
		}
		System.out.println("end");
	}
	*/
}
