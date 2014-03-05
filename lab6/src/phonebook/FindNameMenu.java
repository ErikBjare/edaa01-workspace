package phonebook;
import javax.swing.*;
import java.awt.event.*;
import java.util.LinkedList;
import java.util.List;

@SuppressWarnings("serial")
public class FindNameMenu extends JMenuItem implements ActionListener {
    private PhoneBook phoneBook;
    private PhoneBookGUI gui;

    public FindNameMenu(PhoneBook phoneBook, PhoneBookGUI gui) {
        super("Find name(s)");
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
        List<String> numbers = phoneBook.findNumber(name);
        if(numbers.size() != 0) {
            StringBuilder s = new StringBuilder(name);
            s.append(": [");
            for(String num : numbers) {
                s.append(num);
                s.append(", ");
            }
            s.delete(s.length()-2, s.length());
            s.append("]");
            gui.println(s.toString());
        } else {
            gui.println("Could not find any numbers associated with name");
        }
    }
}
