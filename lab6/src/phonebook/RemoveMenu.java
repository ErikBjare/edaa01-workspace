package phonebook;
import javax.swing.*;
import java.awt.event.*;

public class RemoveMenu extends JMenuItem implements ActionListener {
	private PhoneBook phoneBook;
	private PhoneBookGUI gui;
	
	public RemoveMenu(PhoneBook phoneBook, PhoneBookGUI gui) {
		super("Remove");
		this.phoneBook = phoneBook;
		this.gui = gui;
		addActionListener(this);
	}
	
	 public void actionPerformed(ActionEvent e) {
         String name = JOptionPane.showInputDialog("Enter name");
         if(name.equals("") || name == null) {
             gui.println("User cancelled input");
             return;
         }
         if(phoneBook.remove(name)) {
             gui.println("Successfully removed name '" + name + "' from phonebook");
         } else {
             gui.println("Could not remove nonexistant name '" + name + "'");
         }
	 }
}
