package va.CC.chat;

public class MessageVO {

    int messageID;
    String message;
    int fk_userID;

    public MessageVO(int messageID, String message, int fk_userID) {
        this.messageID = messageID;
        this.message = message;
        this.fk_userID = fk_userID;
    }

    public int getMessageID() {
        return messageID;
    }

    public void setMessageID(int messageID) {
        this.messageID = messageID;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getFk_userID() {
        return fk_userID;
    }

    public void setFk_userID(int fk_userID) {
        this.fk_userID = fk_userID;
    }
}
