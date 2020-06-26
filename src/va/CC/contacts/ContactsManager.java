package va.CC.contacts;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
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

    public void addNewContact() throws SQLException, ClassNotFoundException {
        System.out.println("Enter the name:");
        String name = this.scanner.nextLine();
        System.out.println("Enter the address:");
        String address = this.scanner.nextLine();
        System.out.println("Enter the phone number:");
        int phoneNumber = this.scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter the email address:");
        String email = this.scanner.nextLine();

        ContactsVO contactsVO = new ContactsVO(0, name, address, phoneNumber, email);
        this.contactsDAO.insertNewContact(contactsVO);
        printAllContacts();
    }

    public void option() throws SQLException, ClassNotFoundException {
        System.out.println();
        System.out.println("PLEASE CHOOSE AN OPTION");
        System.out.println("PRESS: 1 - for see the contacts");
        System.out.println("Press: 2 - to add the new contact");
        System.out.println("PRESS: 3 - to delete the specific contact");
        System.out.println("Press: 4 - to edit the contact");
        System.out.println("PRESS: 5 - to EXIT");
        Scanner scanner = new Scanner(System.in);
        Integer option = scanner.nextInt();
        switch (option) {
            case 1:
                printAllContacts();
                option();
                break;
            case 2:
                addNewContact();
                break;
            case 3:
                deleteContact();
                break;
            case 4:
                edit();
                break;
            case 5:
                System.exit(0);
                break;
        }
    }

    public void deleteContact() throws SQLException, ClassNotFoundException {
        System.out.println();
        System.out.println("YOU WANT TO DELETE THE CONTACT PERMANENTLY? (YES/NO)");
        Scanner scanner = new Scanner(System.in);
        String option = scanner.nextLine();
        switch (option) {
            case "YES":
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/notizbuch?user=root");
                Statement statement = connection.createStatement();
                System.out.println("PRESS ID number of the contact you want to delete");
                int id = scanner.nextInt();
                String query3 = "DELETE FROM contacts WHERE contactID=" + id + ";";
                statement.executeUpdate(query3);
                connection.close();
                option();
                break;
            case "NO":
                option();
                break;
        }
    }

    public void edit() throws SQLException, ClassNotFoundException {
        System.out.println();
        System.out.println("YOU WANT TO EDIT THE CONTACT? (YES/NO)");
        Scanner scanner = new Scanner(System.in);
        String option = scanner.nextLine();
        String query = null;
        switch (option) {
            case "YES":
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/notizbuch?user=root");
                Statement statement = connection.createStatement();
                System.out.println("PRESS ID number of the contact you want to edit");
                int id = scanner.nextInt();
                scanner.nextLine();
                System.out.println("WHAT DO YOU WANT TO CHANGE?");
                System.out.println("PRESS 1 FOR NAME CHANGE");
                System.out.println("PRESS 2 FOR ADDRESS CHANGE");
                System.out.println("PRESS 3 FOR PHONE CHANGE");
                System.out.println("PRESS 4 FOR EMAIL CHANGE");
                int option2 = scanner.nextInt();
                scanner.nextLine();
                switch (option2) {
                    case 1:
                        System.out.println("ENTER THE NAME?");
                        String name = scanner.nextLine();
                        query = "UPDATE `contacts` SET `name`='" + name + "' WHERE contactID=" + id + ";";
                        break;
                    case 2:
                        System.out.println("ENTER THE STREET?");
                        String street = scanner.nextLine();
                        query = "UPDATE `contacts` SET `address`='" + street + "' WHERE contactID=" + id + ";";
                        break;
                    case 3:
                        System.out.println("ENTER THE PHONE?");
                        Integer phone = scanner.nextInt();
                        query = "UPDATE `contacts` SET `phonenumber`=" + phone + " WHERE contactID=" + id + ";";
                        break;
                    case 4:
                        System.out.println("ENTER THE EMAIL?");
                        String mail = scanner.nextLine();
                        query = "UPDATE `contacts` SET `email`='" + mail + "' WHERE contactID=" + id + ";";
                        break;
                }
                statement.executeUpdate(query);
                printAllContacts();
                connection.close();
                option();
                break;
            case "NO":
                option();
                break;
        }
    }
}