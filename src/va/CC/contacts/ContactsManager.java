package va.CC.contacts;

import java.util.Scanner;

public class ContactsManager {
    private Scanner scanner;
    private ContactsDAO contactsDAO;

    public ContactsManager(Scanner scanner, ContactsDAO contactsDAO) {
        this.scanner = new Scanner(System.in);
        this.contactsDAO = new ContactsDAO();
    }

    public void readInput() {
        System.out.println("Welcone to your contact lists");
        while (true) {
            System.out.println("Please choose an option 1 to print all contacts");
            int input = scanner.nextInt();
            switch (input){
                case '1':
                    printAllContacts();
            }
        }
    }

    public void printAllContacts() {

    }

    public void addNewContact() {

    }

}
