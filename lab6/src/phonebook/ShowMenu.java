package phonebook;
import javax.swing.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class ShowMenu extends JMenuItem implements ActionListener {
    private PhoneBook phoneBook;
    private PhoneBookGUI gui;

    public ShowMenu(PhoneBook phoneBook, PhoneBookGUI gui) {
        super("Show all");
        this.phoneBook = phoneBook;
        this.gui = gui;
        addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        StringBuilder sb;
        for(String name : phoneBook.names()) {
            sb = new StringBuilder();
            sb.append(name);
            sb.append(": [");
            for(String number : phoneBook.findNumber(name)) {
                sb.append(number);
                sb.append(", ");
            }
            sb.delete(sb.length()-2, sb.length());
            sb.append("]");
            gui.println(sb.toString());
        }
    }
}
