//This is the URLinkedList class to be used in Stack and Queue.

import java.util.Collection;
import java.util.Iterator;

public class URLinkedList<E> implements URList<E>{
	public URNode<E> head; // Reference to next node in linkedlist
	public URNode<E> tail; // Reference to previous node
	public int size; //Reference to the size of the node
	
	// create the constructor
	public URLinkedList(){
		head = null;
		tail = null;
		size = 0;
	}
	
	// Appends the specified element to the end of this list 
	public boolean add(E e) {
		addLast(e);
		return true;
	}

	// Inserts the specified element at the specified position in this list 
	/*issue I faces: I was confused with how the node connect with each other at the beginning.
	 * After I asked my TA, I knew how the linked list works when adding the linked node.
	 */
	public void add(int index, E element) {
		// Throw new IndexOutOfBoundsException if the index is out of bound.
		if(index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
		else if(index==0) {
			addFirst(element);
		}
		else if(index ==size) {
			addLast(element);
		}
		else {
			URNode<E> current = head;
			//for loop to find the index in the linkedlist
			for(int i = 0; i < index; i++) {
				current = current.next();
			}
			
			URNode<E> prevNode = current.prev();
			URNode<E> newNode = new URNode<>(element,prevNode,current);
			current.setPrev(newNode);
			prevNode.setNext(newNode);
			size++;
		}
		
	}

	// Appends all of the elements in the specified collection to the end of this list,
	// in the order that they are returned by the specified collection's iterator 
	public boolean addAll(Collection<? extends E> c) {
		Iterator<? extends E> iterator = c.iterator(); //create new iterator
		//add all the elements in c to the end of the list.
		while(iterator.hasNext()) {
			add(iterator.next());
		}
		size += c.size();
		return true;
	}

	// Inserts all of the elements in the specified collection into this list 
	// at the specified position
	public boolean addAll(int index, Collection<? extends E> c) {
		Iterator<? extends E> iterator = c.iterator(); //create new iterator
		//add all the elements in c to the end of the list.
		while(iterator.hasNext()) {
			add(index, iterator.next());
		}
		size += c.size();
		return true;
	}

	// Removes all of the elements from this list 
	public void clear() {
		//make the size to 0 and set head and tail to null.
		size = 0;
		head = null;
		tail = null;
	}

	// Returns true if this list contains the specified element.
	public boolean contains(Object o) {
		URNode<E> first = head;
		if(first == null) {
			return false;
		}
		for(int i = 0; i < size; i++) {
			if(first.element().equals(o))	
				return true;
			
			first = first.next();	//go to next node.
			if(first == null) return false;
		}
		return false;
	}

	// Returns true if this list contains all of the elements of the specified collection
	public boolean containsAll(Collection<?> c) {
		Iterator<?> iterator = c.iterator();
		while(iterator.hasNext()) {
			if(contains(iterator.next())==false){
				return false;
			}
		}
		return true;
	}

	// Compares the specified object with this list for equality. 
	// Returns true if both contain the same elements. Ignore capacity
	public boolean equals(Object o) {
		//use for loop to check if object o is equal to one of the node in the linked list, return true.
		URNode<E> first = head;
		for(int i = 0; i < size; i++) {
			if(first.element().equals(o))
				return true;
			first = first.next();
		}
		return false;
	}

	// Returns the element at the specified position in this list.
	public E get(int index) {
		//check for index first, if it is less than 0 or greater than size, throw new IndexOutOfBoundsException.
		if(index < 0 || index >= size) {
			 throw new IndexOutOfBoundsException();
		}
		
		//use current node to find out the data in the index location and return
		URNode<E> current = head;
		for(int i = 0; i<index; i++) {
			
			current = current.next();
			
		}
		return current.element();
	}

	// Returns the index of the first occurrence of the specified element in this list, 
	// or -1 if this list does not contain the element.
	public int indexOf(Object o) {
		int index = 0;
		URNode<E> current = head;
		while(current != null) {
			if(current.element() != null && current.element().equals(o)) {
				return index;
			}
			current = current.next();
			index++;
		}
		return -1;
		
	}

	// Returns true if this list contains no elements.
	public boolean isEmpty() {
		
		return head==null & tail == null;
	}

	// Returns an iterator over the elements in this list in proper sequence.
	public Iterator<E> iterator(){
		//make iterator, then include hasNext, next(), 
		Iterator<E> it = new Iterator<E>(){
			URNode<E> current = head;
			public boolean hasNext () {
				return current != null;	
			}
			public E next() {
				URNode<E> temp = current;
				current = current.next();
				return temp.element();
			}
		};
		return it;
	}


	// Removes the element at the specified position in this list 
	public E remove(int index) {
		// if the index is less than 0 or greater than size, return null.
		if(index < 0 || index >= size) {
			return null;
		}
		else {
			URNode<E> current = head;
			E num;
			for(int i =0; i<size; i++) {
				if(i == index) {
					if(i == 0) {
						num = current.element();
						current.setElement(null);
						return num;
					}
					else if(i == size-1) {
						num = current.element();
						current.setElement(null);
						return num;
					}
					else {
						current.prev().setNext(current.next());
						current.next().setPrev(current.prev());
						return current.element();
					}
				}
				current = current.next();
			}
			size--;
			return null;
		}
	}

	// Removes the first occurrence of the specified element from this list,
	// if it is present 
	// return the element that has been removed
	public boolean remove(Object o) {
		URNode<E> current = head;
		for(int i = 0; i<size;i++) {
			if(current.element().equals(o)) {
				remove(current);
				return true;
			}
			current = current.next();
		}
		size--;
		return false;
	}

	// Removes from this list all of its elements that are contained
	//  in the specified collection
	public boolean removeAll(Collection<?> c) {
		//I created a count variable to count for the number of elements included in the linkedlist. 
		//if all element of c collection included in the linkedlist, return true, otherwise, return false.
		int count = 0;
		Iterator<?> iterator = c.iterator();
		while(iterator.hasNext()) {
			if(c.contains(iterator.next())) {
				iterator.remove();
				count++;
			}
		}
		size = size - c.size();
		if(count < c.size()) {
			return false;
		}
		else {
			return true;
		} 
	}

	// Replaces the element at the specified position in this list
	// with the specified element 
	public E set(int index, E element) {
		URNode<E> current = head;
		if(index < 0 || index >= size) {
				throw new IndexOutOfBoundsException();
		}
		else {
			for(int i = 0; i< size; i++) {
				if(i == index) {
					current.setElement(element);
					return current.element();
				}
				current = current.next();
			}
			
		}
		return null;
	}

	// Returns the number of elements in this list.
	public int size() {
		return size;
	}

	// Returns a view of the portion of this list 
	// between the specified fromIndex, inclusive, and toIndex, exclusive.
	
	public URList<E> subList(int fromIndex, int toIndex){
		URNode<E> current = head;
		int count = 0;
		URLinkedList<E> urlist = new URLinkedList<E>();
		if(fromIndex < 0 || toIndex >= size) {
			throw new IndexOutOfBoundsException();
		}
		else if (fromIndex > toIndex) {
			throw new IllegalArgumentException();
		}
		else if(fromIndex == toIndex) {
			return null;
		}
		else {
			for(int i =0; i<size; i++) {
				count++;
				current = current.next();
				if(count >= fromIndex & count<toIndex) {
					urlist.add(current.element());
				}
			}
		}
		return urlist;
		
		 
	}

	// Returns an array containing all of the elements in this list
	//  in proper sequence (from first to the last element).
	public Object[] toArray() {
		if(size == 0) {
			return null;
		}
		else {
			//create object[] and store the node into the object and return.
			Object[] list = new Object[size];
			int index = 0;
			for(URNode<E> current = head; current != null; current=current.next()) {
				list[index++] = current.element();
			}
			return list;
		}
	}
	
	// Inserts the specified element at the beginning of this list.
	public void addFirst(E e) {
		URNode <E> newNode= new URNode<>(e,null,head);
		if(head == null) {
			tail = newNode;
		}
		else {
			head.setPrev(newNode);
		}
		head = newNode;
		
		size++;
	}
	
	// Appends the specified element to the end of this list.
	public void addLast(E e) {
		URNode<E> newNode = new URNode<>(e,tail,null);
		if(tail==null) {
			head = newNode;
		}
		else {
			tail.setNext(newNode);
			
			
		}
		tail = newNode;
		size++;
	}
	
	// Retrieves, but does not remove, the first element of this list, or returns null if
	// this list is empty.
	public E peekFirst() {
		if(head == null) {
			return null;
		}
		return head.element();
	}
	
	// Retrieves, but does not remove, the last element of this list, or returns null if
	// this list is empty.
	public E peekLast() {
		if(tail == null) {
			return null;
		}
		return tail.element();
	}
	
	// Retrieves and removes the first element of this list, or returns null if this list
	// is empty.
	public E pollFirst() {
		if(head == null) {
			return null;
		}
		URNode<E> temp = head;
		head = temp.next();
		if(head==null) {
			tail = null;
		}
		else {
			head.setPrev(null);
		}
		size--;
		return temp.element();
	}
	
	// Retrieves and removes the last element of this list, or returns null if this list
	// is empty.
	public E pollLast() {
		if(tail == null) {
			return null;
		}
		URNode<E> temp = tail;
		tail = temp.prev();
		if(tail == null) {
			head = null;
		}
		else {
			tail.setNext(null);
		}
		size--;
		return temp.element();
		
	}
}

