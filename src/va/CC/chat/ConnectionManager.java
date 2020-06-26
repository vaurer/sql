package va.CC.chat;

import java.util.Scanner;

public class ConnectionManager {

    private Scanner scanner;
    private  ChatDAO chatDAO;

    public ConnectionManager() {
        this.scanner = new Scanner(System.in);
        this.chatDAO = new ChatDAO();
    }


    public void option() {
        System.out.println(" What is your name:");
        String name1 = scanner.nextLine();
        System.out.println("With whom would you like to chat?");
        String name2 = scanner.nextLine();



    }
}
