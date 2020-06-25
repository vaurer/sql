package va.CC.contacts;

import java.sql.SQLException;

public class ContactStarter {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        ContactsManager contactsManager = new ContactsManager();
        ContactsDAO contactsDAO = new ContactsDAO();
        contactsManager.printAllContacts();
        contactsManager.addNewContact();
    }
}
