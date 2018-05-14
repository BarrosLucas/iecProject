package com.example.root.myapplication.Model;

public class Message {
    private int id;
    private User sender;
    private User recipient;
    private String message;
    private String datetime;

    public Message(int id, User sender, User recipient, String message, String datetime){

        setId(id);
        setSender(sender);
        setRecipient(recipient);
        setMessage(message);
        setDatetime(datetime);

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getRecipient() {
        return recipient;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }
}
