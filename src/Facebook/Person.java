package Facebook;

/**
 * Models a facebook user
 * @author Quang-Duy Tran
 *
 */

public class Person
{
	private String name;
	private SinglyLinkedList<Person> friends;

	/**
	 * Default constructor
	 * Initialize the name of a person to a given name
	 * Initialize the friend list to empty
	 * @param name the given name of the person
	 */
	public Person(String name) {
		this.name = name;
		this.friends = new SinglyLinkedList<>();
	}

	/****** Getter functions ******/
	public String getName() { return name; }
	
	public SinglyLinkedList<Person> getFriendsList() { return this.friends; }
}
