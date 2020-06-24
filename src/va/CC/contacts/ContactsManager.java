package va.CC.contacts;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class ContactsManager {
    private Scanner scanner;
    private ContactsDAO contactsDAO;

    public ContactsManager() {
        this.scanner = new Scanner(System.in);
        this.contactsDAO = new ContactsDAO();
    }

    public void readInput() throws SQLException, ClassNotFoundException {
        System.out.println("Welcone to your contact lists");
        while (true) {
            System.out.println("Please choose an option 1 to print all contacts");
            int input = scanner.nextInt();
            switch (input) {
                case '1':
                    printAllContacts();
            }
        }
    }

    public void printAllContacts() throws SQLException, ClassNotFoundException {
        List<ContactsVO> contactsVOList = this.contactsDAO.getAllContacts();
        for (ContactsVO contact : contactsVOList
        ) {
            System.out.println(contact.getContactID() + "\t " + contact.getName() + "\t " + contact.getAddress() + "\t" + contact.getPhonenumber() + " \t" + contact.getEmail());
        }
    }

    public void addNewContact() {
        System.out.println("Enter the name:");
        String name = this.scanner.nextLine();
        System.out.println("Enter the address:");
        String address = this.scanner.nextLine();
        System.out.println("Enter the phone number:");
        int phoneNumber = this.scanner.nextInt();
        System.out.println("Enter the email address:");
        String email = this.scanner.nextLine();
        ContactsVO contactsVO = new ContactsVO(0, name, address, phoneNumber, email);
        //this.contactsDAO.insertTask();  //zu machen

//        String input = this.scanner.nextLine();
//        System.out.println("name of the new task:");
//        TaskVO taskVO = new TaskVO(0, input);
//        this.taskDAO.insertTask(taskVO);
//        printAllTasks();
    }

}
