package phonebook;
import sun.awt.image.ImageWatched;

import javax.swing.*;
import java.awt.event.*;
import java.util.LinkedList;

@SuppressWarnings("serial")
public class AddMenu extends JMenuItem implements ActionListener {
	private PhoneBook phoneBook;
	private PhoneBookGUI gui;
	
	public AddMenu(PhoneBook phoneBook, PhoneBookGUI gui) {
		super("Add");
		this.phoneBook = phoneBook;
		this.gui = gui;
		addActionListener(this);
	}
	
    public void actionPerformed(ActionEvent e) {
        String name = JOptionPane.showInputDialog("Enter name");
        if(name == null || name.equals("")) {
            gui.println("User cancelled input");
            return;
        }
        String number = JOptionPane.showInputDialog("Enter number");
        if(number == null || number.equals("")) {
            gui.println("User cancelled input");
            return;
        }
        if(phoneBook.put(name, number)) {
            gui.println("Successfully added number '" + number + "' to name '" + name + "'");
        } else {
            gui.println("Entry already existed (number '" + number + "' with name '" + name + "')");
        }
    }
}
