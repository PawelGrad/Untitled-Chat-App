package pl.coderslab.chatApp.Model.Message;

public class Message {



    public enum MessageType {
        CHAT, JOIN, LEAVE
    }

    private Long id;

    private String messageType;

    private String content;

    private String sender;

    public Message() {
    }

    public Message(Long id, String messageType, String content, String sender) {
        this.id = id;
        this.messageType = messageType;
        this.content = content;
        this.sender = sender;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
}