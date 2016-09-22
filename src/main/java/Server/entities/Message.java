package Server.entities;

import javax.persistence.*;

@Entity
@Table(name = "messages")
 public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "sender")
    private String sender;
    @Column(name = "receiver")
    private String receiver;
    @Column(name = "body")
    private String message;
    @Column(name = "ts")
    private long ts;


    public Message(String sender, String reciever, String message){
        this.message = message;
        this.receiver = reciever;
        this.sender = sender;
    }

    public Message(){

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

    public int getId() {
        return id;
    }

    public long getTs() {
        return ts;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setTs(long ts) {
        this.ts = ts;
    }
}