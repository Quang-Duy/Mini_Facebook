package Facebook;

/**
 * Models a hash table 
 * @author Quang-Duy Tran
 *
 */

public class HashTable
{
	public static Integer MAX_SIZE = 13;
	public static Double LETTER_BASE = 128d;
	
	private int max_size;
	private SinglyLinkedList<Person>[] bucket;
	
	/**
	 * Default constructor
	 * Initialize the max_size to MAX_SIZE
	 * Initialize the empty chain (empty linked list)
	 * Store 50 given names in the hash table
	 */
	public HashTable()
	{
		this.max_size = MAX_SIZE;
		this.bucket = new SinglyLinkedList[max_size];
		
		//Initialize empty chain
		for(int i = 0; i < max_size; i++) {
			this.bucket[i] = new SinglyLinkedList<>();
		}
		
		//Store 50 given names in hash table
		String[] split = Facebook.NAMES.split("\\s+");
		for(int i = 0; i < split.length; i++) {
			int index = division_hash(split[i]);
			bucket[index].push_back(new Person(split[i]));
		}
	}
	
	/**
	 * Insert a new friend into one's friend list
	 * @param friends one's friend list
	 * @param person a new friend
	 */
	public void chain_hash_insert(SinglyLinkedList<Person> friends, Person person) 
	{
		//If person is already in the friends' friend list, don't do anything
		for(int i = 0; i < friends.size(); i++)
		{
			if(friends.getData(i).getName().equals(person.getName()))
				return;
		}
		
		friends.push_front(person);
	}
	
	/**
	 * Remove a friend from one's friend list; Knowing for sure that friend is existed in one's friend list before calling this method
	 * @param friends one's friend list
	 * @param person a friend
	 * @throws Exception throws empty list exception 
	 */
	public void chain_hash_delete(SinglyLinkedList<Person> friends, Person person) throws Exception {
		int index = -1;
		
		//Find the position of the person in the friend list
		for(int i = 0; i < friends.size(); i++) 
		{
			if(friends.getData(i).getName().equals(person.getName())) {
				index = i;
				break;
			}
		}
		
		friends.remove(index); //If list is empty, index = -1 => throw empty list exception
	}
	
	/**
	 * Look up a friend in the hash table
	 * @param table the hash table
	 * @param name the name of a friend
	 * @return null if friend not found; Person object if friend is found
	 */
	public Person chain_hash_search(HashTable table, String name) 
	{
		int index = division_hash(name);
		for(int i = 0; i < table.getBucket()[index].size(); i++)
		{
			if(table.getBucket()[index].getData(i).getName().equals(name))
				return table.getBucket()[index].getData(i);
		}
		
		return null; //Return null if friend not found
	}
	
	/**
	 * Hash the name with based 128 to get the index of the bucket
	 * @param name the given name
	 * @return the index of the bucket
	 */
	public int division_hash(String name) 
	{
		int key = 0;
		int j = name.length() - 1; // 128 to the power of j
		for(int i = 0; i < name.length(); i++)
		{
			key += (((int)name.charAt(i))%this.max_size) * (Math.pow((LETTER_BASE % this.max_size), j) % this.max_size) % this.max_size;
			j--;
		}
		
		return (int)key % this.max_size;
	}
	
	/****** Getter functions ******/
	public SinglyLinkedList<Person>[] getBucket() { return this.bucket; }
	
	public int getSize() { return this.max_size; }
}
