package va.CC.chat;

import java.sql.SQLException;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class ConnectionManager {

    private Scanner scanner;
    private ChatDAO chatDAO;
    private UserVO userVO;
    private MessageVO messageVO;
    Timer timer = new Timer();


    TimerTask update = new TimerTask() {
        @Override
        public void run() {
            chatDAO.getExistingMessages();
            timer.scheduleAtFixedRate(update, 0, 1000);
        }
    };


    public ConnectionManager() {
        this.scanner = new Scanner(System.in);
        this.chatDAO = new ChatDAO();
    }

    public UserVO logIn() throws SQLException {
        System.out.println(" What is your name:");
        String name = scanner.nextLine();
        if (this.chatDAO.checkExistingContact(name) != true) {
            userVO = new UserVO(0, name);
            chatDAO.newUser(userVO);
            System.out.println(userVO.name);
        } else {
            userVO = chatDAO.getExistingUser(name);
            System.out.println(userVO.name);
        }
        return userVO;
    }


    public void chat() throws SQLException, ClassNotFoundException {
        boolean isFinish=false;
        chatDAO.getExistingMessages();
        while (!isFinish){

            chatDAO.newMessage(scanner.nextLine(), userVO);
        }


    }


}

