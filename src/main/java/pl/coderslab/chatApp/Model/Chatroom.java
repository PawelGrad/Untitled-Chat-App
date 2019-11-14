package pl.coderslab.chatApp.Model;


import pl.coderslab.chatApp.Model.Message.MessageEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "chatrooms")
public class Chatroom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String roomName;


    @OneToMany(mappedBy = "chatroom")
    private Set<MessageEntity> messages;


    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User chatOwner;

    @ManyToMany(mappedBy ="chatrooms", fetch = FetchType.EAGER)
    private Set<User> users = new HashSet<>();


    public Set<MessageEntity> getMessages() {
        return messages;
    }

    public void setMessages(Set<MessageEntity> messages) {
        this.messages = messages;
    }

    public User getChatOwner() {
        return chatOwner;
    }

    public void setChatOwner(User chatOwner) {
        this.chatOwner = chatOwner;
    }

    public Chatroom() {
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }


}