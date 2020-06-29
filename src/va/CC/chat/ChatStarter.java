package va.CC.chat;

import java.sql.SQLException;
import java.sql.Timestamp;

public class ChatStarter {


    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        ConnectionManager connectionManager = new ConnectionManager();
        ChatDAO chatDAO = new ChatDAO();
        UserVO userVO = connectionManager.logIn();
        connectionManager.chat();

    }

}
