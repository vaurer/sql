package va.CC.chat;

import java.sql.SQLException;

public class ChatStarter {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        //UserVO userVO = null;
        ConnectionManager connectionManager = new ConnectionManager();
        ChatDAO chatDAO = new ChatDAO();
        UserVO userVO = connectionManager.logIn();
        connectionManager.chat();
        connectionManager.update;
    }

}
