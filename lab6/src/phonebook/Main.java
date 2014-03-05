package phonebook;

public class Main {
	public static void main(String[] args) {
        PhoneBook pb;
        if(args.length == 0) {
            pb = new PhoneBook();
        } else {
            pb = new PhoneBook(args[0]);
        }
		new PhoneBookGUI(pb);
	}
}