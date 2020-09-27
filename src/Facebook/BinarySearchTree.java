package Facebook;

/**
 * Models a binary search tree
 * @author Quang-Duy Tran
 *
 */

public class BinarySearchTree
{
	private Node root;
	String names; //Used to display the binary tree. I use String type because my GUI display takes in a string type
	
	private static class Node
	{
		Person data;
		Node parent;
		Node left;
		Node right;
		
		public Node(Person data) {
			this.data = data;
			
		}
	}
	
	/**
	 * Default constructor
	 * Initialize variable root to null pointer
	 * Initialize first word of names to "Sorted"
	 */
	public BinarySearchTree() {
		this.root = null;
		names = "Sorted:";
	}
	
	/**
	 * Insert a person object into a binary tree
	 * @param tree the binary tree
	 * @param person the person object
	 */
	public void tree_insert(BinarySearchTree tree, Person person)
	{
		Node node = new Node(person);
		
		Node trailing = null;
		Node iterator = tree.root;
		
		while(iterator != null)
		{
			trailing = iterator;
			if(node.data.getName().compareTo(iterator.data.getName()) < 0)
			{
				iterator = iterator.left;
			}
			else 
			{
				iterator = iterator.right;
			}
		}
		
		node.parent = trailing;
		
		if(trailing == null) //Tree is empty
			tree.root = node;
		else if (node.data.getName().compareTo(trailing.data.getName()) < 0)
			trailing.left = node;
		else
			trailing.right = node;
	}
	
	/**
	 * Walk through the tree to get the names in alphabetical order
	 * @param node the root of the tree
	 */
	public void inorder_tree_walk(Node node) {
		if(node != null) {
			inorder_tree_walk(node.left);
			this.names += " " + node.data.getName();
			inorder_tree_walk(node.right);
		}
	}
	
	/****** Getter functions ******/
	public String getSortedList() { return this.names; }

	public Node getRoot() { return this.root; }
	
	
}
