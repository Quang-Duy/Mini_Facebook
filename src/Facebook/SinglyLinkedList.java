package Facebook;

/**
 * Models a Generic Singly Linked List
 * @author quangduytran
 *
 */

public class SinglyLinkedList<T> 
{
	private int node_count;
	private Node<T> head;
	private Node<T> tail;
	
	/**
	 * Contains data and next pointer
	 * @param <T> generic node
	 */
	private static class Node<T>
	{
		public T data;
		Node<T> next;
		
		public Node(T data, Node<T> next) {
			this.data = data;
			this.next = next;
		}
	}
	
	/****** Getter functions ******/
	public T getData(int index) {
		Node<T> node = iterate_to_index(index);
		return node.data;
	}
	
	/**
	 * Iterate to the given index
	 * @param index the given index
	 * @return the node at the index
	 */
	private Node<T> iterate_to_index(int index) {
		//If index is at tail, just return tail instead of iterating
		if(index == node_count - 1)
			return this.tail;
		
		Node<T> node = head;
		for(int i = 0; i < index; i++) {
			node = node.next;
		}
		
		return node;
	}
	
	/**
	 * Default constructor
	 * Initialize node count to zero
	 * Initialize head and tail to null pointer
	 */
	public SinglyLinkedList() {
		this.node_count = 0;
		this.head = null;
		this.tail = null;
	}
	
	/**
	 * Remove all the items in the linked list by ignoring them and let java collect them as trash memories
	 */
	public void clear() {
		this.head = null;
		this.tail = null;
		this.node_count = 0;
	}
	
	/**
	 * Check if the linked list is empty
	 * @return True if the size of linked list is 0; False otherwise
	 */
	public boolean isEmpty() {
		return this.node_count == 0;
	}
	
	/**
	 * Get the size of the linked list
	 * @return the size of the linked list
	 */
	public int size() {
		return this.node_count;
	}
	
	/**
	 * Add the new item to the front of the linked list
	 * @param data the new item
	 */
	public void push_front(T data) {
		this.head = new Node<T>(data, head);
		
		//Set tail = head if there is only one item in the list
		if(this.tail == null) {
			this.tail = this.head;
		}
		this.node_count++;
	}
	
	/**
	 * Add the new item to the back of the linked list
	 * @param data the new item
	 */
	public void push_back(T data) {
		//If not tail, then we call push front 
		if(this.tail == null) {
			push_front(data);
			return;
		}
		
		//Add new node to the tail of the linked list and update the new tail
		this.tail.next = new Node<T>(data, null);
		this.tail = this.tail.next;
		
		this.node_count++;
	}
	
	/**
	 * Insert new items in the middle of two items
	 * @param data the new items
	 * @param index the index to insert the new item
	 * @throws Exception throws out of bound and logic error
	 */
	public void insert(T data, int index) throws Exception 
	{
		if(index == 0) {
			push_front(data);
			return;
		}
		else if(index == this.node_count) {
			push_back(data);
			return;
		}
		else if(index > this.node_count) {
			throw new Exception("Tried to insert to an out of range index!"); //out of bound error
		}
		
		//We need to modify the node before the removed one
		Node<T> node = iterate_to_index(index - 1);
		if(node == null)
			throw new Exception("Ran into end of list while remaining within node_count!"); //logic error
		
		//Inserts between node and the current node.next
		node.next = new Node<T>(data, node.next);
		node_count++;
	}
	
	/**
	 * Delete and return the first item from the linked list
	 * @return the first item from the list
	 * @throws Exception out of range exception
	 */
	public T pop_front() throws Exception {
		if(this.node_count == 0)
			throw new Exception("No items in the list to pop!"); //out of range exception
		
		T data = head.data; //Get the data 
		
		this.head = this.head.next; //Set the new head to head.next and old head is forgotten/ignored
		
		//Update the tail if there is no head
		if(this.head == null)
			this.tail = null;
		
		this.node_count--;
		return data;
	}
	
	/**
	 * Delete and return the last item from the linked list
	 * @return the last item from the list
	 * @throws Exception out of range exception
	 */
	public T pop_back() throws Exception {
		if(this.node_count < 1)
			return pop_front();
		
		// Off by 1 for zero indexing, and then subtract one to get the node before the tail => minus 2
		Node<T> node = iterate_to_index(this.node_count - 2);
		
		T data = (T) node.next.data;
		node.next = null; //Set the node.next points to null pointer => old tail is forgotten/ignored
		
		this.tail = node; // Update the new tail
		
		this.node_count--;
		
		return data;
	}
	
	/**
	 * Delete and return the item in the middle of 2 items
	 * @param index the index of the item that got deleted
	 * @return the item that got deleted
	 * @throws Exception throw out of bound and logic error exception
	 */
	public T remove(int index) throws Exception {
		if(index == 0)
			return pop_front();
		else if(index == node_count - 1)
			return pop_back();
		else if(index >= node_count)
			throw new Exception("Try to remove an out of range index!"); //out of bound error
		
		//We need to modify the node before the removed one
		Node<T> node = iterate_to_index(index - 1);
		if(node == null)
			throw new Exception("Ran into end of list while remaining within node_count!"); //logic error
		
		Node<T> removed = node.next;
		node.next = removed.next; //ignored the removed node by pointing to the node next to the removed node
		
		T data = removed.data;
		
		this.node_count--;
		
		return data;
	}
	
	/**
	 * Find the item and return the index of that item in the linked list
	 * @param target the target item
	 * @return the index of the item if found; -1 otherwise
	 */
	public int find(T target) {
		Node<T> node = head;
		int index = 0;
		
		//Manual iteration makes more sense here
		while(node != null) {
			if(node.data == target)
				return index;
			node = node.next; //keep iterating forward
			index++;
		}
		
		//If item not found, return -1
		return -1;
	}
}
