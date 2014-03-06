package phonebook;
import javax.swing.*;
import java.awt.event.*;
import java.util.List;

@SuppressWarnings("serial")
public class FindNumberMenu extends JMenuItem implements ActionListener {
    private PhoneBook phoneBook;
    private PhoneBookGUI gui;

    public FindNumberMenu(PhoneBook phoneBook, PhoneBookGUI gui) {
        super("Find number(s)");
        this.phoneBook = phoneBook;
        this.gui = gui;
        addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        String number = JOptionPane.showInputDialog("Enter number");
        if(number == null || number.equals("")) {
            gui.println("User cancelled input");
            return;
        }
        List<String> names = phoneBook.findNames(number);
        if(names.size() != 0) {
            StringBuilder s = new StringBuilder(number);
            s.append(": [");
            for(String name : names) {
                s.append(name);
                s.append(", ");
            }
            s.delete(s.length()-2, s.length());
            s.append("]");
            gui.println(s.toString());
        } else {
            gui.println("Could not find any names associated with number");
        }
    }
}
