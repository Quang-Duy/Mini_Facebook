package Facebook;

/**
 * Models a micro Facebook application
 * @author Quang-Duy Tran
 *
 */

import mvc.*;

public class Facebook extends Model
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//50 given names
	public static String NAMES = "Liam Emma Noah Olivia William Ava James Isabella Logan Sophia "
			+ "Benjamin Mia Mason Charlotte Elijah Amelia Oliver Evelyn Jacob Abigail "
			+ "Lucas Harper Michael Emily Alexander Elizabeth Ethan Avery Daniel Sofia "
			+ "Matthew Ella Aiden Madison Henry Scarlett Joseph Victoria Jackson Aria "
			+ "Samuel Grace Sebastian Chloe David Camila Carter Penelope Wyatt Riley ";
	
	private boolean isVisible;
	private Person account_owner;
	private HashTable system = new HashTable();
	
	/**
	 * Default constructor
	 * Initialize a new hash table
	 */
	public Facebook() {
		this.isVisible = true;
		//system = new HashTable();
	}
	
	/**
	 * Add new friend to the friend list by calling chain_hash_insert function
	 * Throws error message if friend is not in the hash table
	 * @param name the name of the new friend
	 */
	public void addFriend(String name) {
		
		if(this.account_owner.getName().equals(name)) {
			Utilities.error("Can't add yourself!");
			changed();
			return;
		}
		
		//When user hits cancel button and name = null, don't do anything
		if(name == null) {
			return;
		}
		
		Person new_friend = null;
		
		//Find that user in the hash table
		new_friend = system.chain_hash_search(system, name);
		
		if(new_friend != null)
		{
			system.chain_hash_insert(account_owner.getFriendsList(), new_friend); //Add new friend to self's friends list
			system.chain_hash_insert(new_friend.getFriendsList(), account_owner); //Add self to new friend's friends list
		}
		else
		{
			Utilities.error("Friend not found!");
		}
		changed();
	}

	/**
	 * Remove a friend in the list by calling chain_hash_delete function
	 * Throws error message if friend is not found in the hash table
	 * @param name the target friend
	 * @throws Exception exception from linked list class
	 */
	public void removeFriend(String name) throws Exception
	{
		Person old_friend = system.chain_hash_search(system, name);
		
		boolean deleteSuccess = false;
		
		for(int i = 0; i < this.account_owner.getFriendsList().size(); i++)
		{
			if(this.account_owner.getFriendsList().getData(i).getName().equals(name)) //Checking if this name is found inside account owner's friend list
			{
				system.chain_hash_delete(account_owner.getFriendsList(), old_friend); //Delete a friend to self's friends list
				system.chain_hash_delete(old_friend.getFriendsList(), account_owner); //Delete self to a friend's friends list
				
				deleteSuccess = true;
				break;
			}
		}
		
		if(!deleteSuccess)
			Utilities.error("Friend not found!");
		
		changed();
	}

	/**
	 * Search a friend by calling chain_hash_search method and display his/her friend list
	 * Throws error message if friend is not found in the hash table
	 * @param name
	 */
	public void search(String name)
	{
		Person person = system.chain_hash_search(this.system, name);
		
		if(person != null)
		{
			SinglyLinkedList<Person> friends = person.getFriendsList();
			
			String[] friendsList = new String[friends.size() + 1];
			friendsList[0] = name + "'s friend list:";
			
			for(int i = 1; i <= friends.size(); i++)
			{
				friendsList[i] = friends.getData(i-1).getName();
			}
			
			Utilities.inform(friendsList);
		}
		else
			Utilities.error("Friend not found!");
		
	}
	
	/**
	 * Check if two people are friend with each other by calling chain_hash_search method
	 * Throws error message if either friend is not found in the hash table
	 * @param response the names of two people
	 */
	public void checkIfFriend(String response) {
		String[] names = response.split("\\s+");
		
		if(names.length != 2) {
			Utilities.error("Only 2 friends at a time!");
			return;
		}
		
		Person person1 = system.chain_hash_search(system, names[0]);

		if(person1 == null) {
			Utilities.error(names[0] + " not found!");
			return;
		}
		
		Person person2 = system.chain_hash_search(system, names[1]);
		
		if(person2 == null) {
			Utilities.error(names[1] + " not found!");
			return;
		}
		
		boolean friendFound = false;
		
		if(person1.getFriendsList().isEmpty() || person2.getFriendsList().isEmpty()) {
			Utilities.inform("NO");
			return;
		}
		else {
			for(int i = 0; i < person1.getFriendsList().size(); i++)
			{
				if(person1.getFriendsList().getData(i).getName().equals(person2.getName())) {
					friendFound = true;
					break;
				}
			}
		}
		
		if(friendFound) {
			Utilities.inform("YES");
		} else {
			Utilities.inform("NO");
		}
	}
	
	/**
	 * Print the bucket for checking purposes
	 * @param bucket the bucket
	 * @param name the name of the bucket
	 */
	public void displayHashTable() 
	{
		String[] buckets = new String[system.getSize()];
		
		
		for(int i = 0; i < system.getSize(); i++)
		{
			if(system.getBucket()[i].size() == 0) { //If bucket is empty
				buckets[i] = "[Empty]";
			}
			else {
				String contents = "";
				contents += system.getBucket()[i].getData(0).getName();
				
				for(int j = 1; j < system.getBucket()[i].size(); j++)
				{
					contents += " -> " + system.getBucket()[i].getData(j).getName();
				}
				
				buckets[i] = contents;
			}
		}
		
		Utilities.inform(buckets);
		changed();
	}
	
	/**
	 * Insert one's friends into a binary tree and display them in alphabetical order
	 */
	public void binarySearchTree() 
	{
		BinarySearchTree tree = new BinarySearchTree();
		for(int i = 0; i < this.account_owner.getFriendsList().size(); i++)
		{
			tree.tree_insert(tree, this.account_owner.getFriendsList().getData(i));
			
		}
		tree.inorder_tree_walk(tree.getRoot());
		
		String[] sortedList = tree.getSortedList().split("\\s+");
		
		Utilities.inform(sortedList);
	}
	
	/**
	 * Sign out from facebook
	 */
	public void signOut() {
		setIsVisible(false); //Set the facebook to invisible
		changed();
	}
	
	/****** Getter functions ******/
	public Person getAccountOwner() { return this.account_owner; }
	
	public boolean getVisible() { return this.isVisible; }
	
	/**
	 * Set the account owner when user choose an account to log in
	 * @param name the chosen account from user
	 */
	public void setAccountOwner(String name) {
		int index = system.division_hash(name);
		
		for(int i = 0; i < system.getBucket()[index].size(); i++)
		{
			Person owner = system.getBucket()[index].getData(i);
			if(owner.getName().equals(name)) {
				this.account_owner = owner;
				break;
			}
		}
		
	}
	
	public void setIsVisible(boolean flag) { this.isVisible = flag; }
}
