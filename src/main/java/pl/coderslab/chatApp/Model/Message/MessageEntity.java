package pl.coderslab.chatApp.Model.Message;


import pl.coderslab.chatApp.Model.Chatroom.ChatroomEntity;

import javax.persistence.*;

@Entity
@Table(name = "messages")
public class MessageEntity {

    public enum MessageType {
        CHAT, JOIN, LEAVE
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Transient
    private MessageType messageType;

    @ManyToOne
    @JoinColumn(name = "chatroom_id", nullable = false)
    private ChatroomEntity chatroom;

    @Column
    private String content;

    @Column
    private String sender;


    public ChatroomEntity getChatroom() {
        return chatroom;
    }

    public void setChatroom(ChatroomEntity chatroom) {
        this.chatroom = chatroom;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MessageType getType() {
        return messageType;
    }

    public void setType(MessageType messageType) {
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