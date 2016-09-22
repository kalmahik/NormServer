package Client;

public class Message {
    private String sender;
    private String receiver;
    private String message;

    public Message(String sender, String reciever, String message){
        this.message = message;
        this.receiver = reciever;
        this.sender = sender;
    }

    public String getSender() {
        return sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public String getMessage() {
        return message;
    }
}