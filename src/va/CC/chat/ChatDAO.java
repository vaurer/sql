package va.CC.chat;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChatDAO {
    List<MessageVO> messageVOList = new ArrayList<>();
    List<UserVO> userList = new ArrayList<>();
    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    Timestamp temp = null;

    Date date=new Date(timestamp.getTime());
    Time time = new Time(timestamp.getTime());


    public List<UserVO> userList() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/chat?user=root");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from userlist");
            while (resultSet.next()) {
                int userID = resultSet.getInt(1);
                String name = resultSet.getString(2);
                UserVO userVO = new UserVO(userID, name);
                userList.add(userVO);
            }
            connection.close();
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }
        return userList;
    }

    public boolean checkExistingContact(String userName) throws SQLException {
        userList();
        boolean isOnTheList = false;
        for (UserVO userVO : userList) {
            if (userVO.name.equals(userName)) {
                isOnTheList = true;
                break;
            }
        }
        return isOnTheList;
    }

    public UserVO getExistingUser(String userName) throws SQLException {
        userList();
        UserVO user = null;
        for (UserVO userVO : userList) {
            if (userVO.name.equals(userName)) {
                user = userVO;
            }
        }
        return user;
    }

    public void newUser(UserVO userVO) throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/chat?user=root");
            Statement statement = connection.createStatement();
            String query = "INSERT INTO `chat`.`userlist` (`name`) VALUES ('" + userVO.getName() + "');";
            statement.execute(query);
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }
    }

    public List<MessageVO> getExistingMessages() {
//        for (UserVO userVO : userList){
//            System.out.println(userVO.name + " - " + userVO.userID );
//        }
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/chat?user=root");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from messagelist");

            while (resultSet.next()) {
                int messageID = resultSet.getInt(1);
                String message = resultSet.getString(2);
                int fk_userID = resultSet.getInt(3);
                timestamp = resultSet.getTimestamp(4);
                System.out.println("ID: " + messageID + " -  " + message + " - USER: " + userList().get(fk_userID+1).name+ " " + this.date +" "+ this.time);
            }
            connection.close();
            temp = new Timestamp(System.currentTimeMillis());

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return messageVOList;
    }

    public void getNewMessages() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/chat?user=root");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from messagelist");

            while (resultSet.next()) {
                int messageID = resultSet.getInt(1);
                String message = resultSet.getString(2);
                int fk_userID = resultSet.getInt(3);
                timestamp = resultSet.getTimestamp(4);

                if (timestamp.after(temp)) {
                    System.out.println("ID: " + messageID + " -  " + message + " - USER: " + userList().get(fk_userID+1).name + " "  + this.date +" "+ this.time);
                }
            }
            connection.close();
            temp = new Timestamp(System.currentTimeMillis());
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
    }

    public void newMessage(String text, UserVO userVO) throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/chat?user=root");
            Statement statement = connection.createStatement();
            String query = "INSERT INTO `chat`.`messagelist` (`message`, `fk_userID`) VALUES ('" + text + "', '" + userVO.userID + "');";
            statement.execute(query);
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
