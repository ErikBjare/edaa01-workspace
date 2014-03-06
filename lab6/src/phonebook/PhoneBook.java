package phonebook;
import javax.swing.*;
import java.io.*;
import java.util.*;

public class PhoneBook implements Serializable {
	private Map<String,LinkedList<String>> phoneBook;
    private String filename;
	
	public PhoneBook() {
		phoneBook = new HashMap<String,LinkedList<String>>();
	}

    public PhoneBook(String fileName) {
        load(fileName);
    }
	
	
	/** 
	 * Associates the specified number with the specified 
	 * name in this phone book. 
	 * post: If the specified name is not present in this phone book,
	 *        the specified name is added and associated  with
	 *  	  the specified number. Otherwise the specified 
	 *  	  number is added to the set of number associated with name.
	 * @param name The name for which a phone number is to be added
	 * @param number The number associated with the specified name
	 * @return true if the specified name and number was inserted
	 */
	public boolean put(String name, String number) {
        if(phoneBook.containsKey(name)) {
            LinkedList<String> numbers = phoneBook.get(name);
            if(!numbers.contains(number)) {
                numbers.add(number);
                return true;
            }
        } else {
            LinkedList<String> numbers = new LinkedList<String>();
            numbers.add(number);
            phoneBook.put(name, numbers);
            return true;
        }
		return false;
	}
	
	
	/**
	 * Removes the the specified name from this phone book.
	 * post: If the specified name is present in this phone book,
	 * 		 it is removed. Otherwise this phone book is
	 * 		 unchanged.
	 * @param name The name to be removed
	 * @return true if the specified name was present
	 */
	public boolean remove(String name) {
		if(phoneBook.containsKey(name)) {
            phoneBook.remove(name);
            return true;
        }
        return false;
	}
	
	/**
	 * Retrieves a list of phone numbers for the specified name. If the 
	 * specified name is not present in this phone book an empty list is 
	 * returned.
	 * @param name The name whose associated phone numbers are to be returned  
	 * @return The phone numbers associated with the specified name
	 */
	public List<String> findNumber(String name) {
        if(phoneBook.containsKey(name)) {
            return phoneBook.get(name);
        }
		return new LinkedList<String>();
	}
	
	/**
	 * Retrieves a list of names associated with the specified phone number. 
	 * If the specified number is not present in this phone book an empty 
	 * list is returned.
	 * @param number The number for which the set of associated
	 * names is to be returned.
	 * @return The list of names associated with the specified number
	 */
	public List<String> findNames(String number) {
        LinkedList<String> names = new LinkedList<String>();
        for(String name : phoneBook.keySet()) {
            for(String num : phoneBook.get(name)) {
                if(num.equals(number)) names.add(name);
            }
        }
		return names;
	}
	
	/**
	 * Retrieves the set of all names present in this phone book.
	 * The set's iterator will return the names in ascending order
	 * @return The set of all names present in this phone book
	 */
	public Set<String> names() {
		return phoneBook.keySet();
	}
	
	/**
	 * Returns true if this phone book is empty
	 * @return true if this phone book is empty
	 */	
	public boolean isEmpty() {
		return phoneBook.size() == 0;
	}
	
	/**
	 * Returns the number of names in this phone book
	 * @return The number of names in this phone book
	 */
	public int size() {
		return phoneBook.size();
	}

    public void save() {
        if(filename == null) {
            filename = JOptionPane.showInputDialog("File to save to not specified,\nplease specify one or press cancel to discard changes");
        }
        if(filename == null || filename.equals("")) {
            return;
        }
        try {
            ObjectOutputStream out;
            out = new ObjectOutputStream(new FileOutputStream(filename));
            out.writeObject(this.phoneBook);
        } catch (Exception exc) {
            exc.printStackTrace();
            System.exit(1);
        }
    }

    public void load(String fileName) {
        filename = fileName;
        try {
            ObjectInputStream in =
                    new ObjectInputStream(new FileInputStream(fileName));
            phoneBook = (HashMap<String, LinkedList<String>>) in.readObject();
        } catch (FileNotFoundException e) {
            phoneBook = new HashMap<String,LinkedList<String>>();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

}
