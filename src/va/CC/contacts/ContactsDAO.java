package va.CC.contacts;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactsDAO {
    List<ContactsVO> contacts = new ArrayList<>();

    public List<ContactsVO> getAllContacts() throws ClassNotFoundException, SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/notizbuch?user=root");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from contacts");
            while (resultSet.next()) {
                int contactID = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String address = resultSet.getString(3);
                int phonenumber = resultSet.getInt(4);
                String email = resultSet.getString(5);
                ContactsVO contactsVO= new ContactsVO(contactID, name, address, phonenumber, email);
                contacts.add(contactsVO);
            }
            connection.close();
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }
        return contacts;
    }

    public void insertNewContact(ContactsVO contactsVO) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/notizbuch?user=root");
        Statement statement = connection.createStatement();
        String query = "INSERT INTO `notizbuch`.`contacts` (`name`, `address`, `phonenumber`, `email`) VALUES ('" + contactsVO.getName() + "', '" + contactsVO.getAddress() + "', '" + contactsVO.getPhonenumber() + "', '" + contactsVO.getEmail() + "');";
        statement.executeUpdate(query);
        connection.close();
    }
}
