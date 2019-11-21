package pl.coderslab.chatApp.Model.Chatroom;


import pl.coderslab.chatApp.Model.Invitation.InvitationEntity;
import pl.coderslab.chatApp.Model.Message.MessageEntity;
import pl.coderslab.chatApp.Model.User.UserEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "chatrooms")
public class ChatroomEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String roomName;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "room")
    private Set<InvitationEntity> invitations;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "chatroom")
    private Set<MessageEntity> messages;


    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity chatOwner;

    @ManyToMany(mappedBy ="chatrooms", fetch = FetchType.EAGER)
    private Set<UserEntity> users = new HashSet<>();



    public Set<MessageEntity> getMessages() {
        return messages;
    }

    public void setMessages(Set<MessageEntity> messages) {
        this.messages = messages;
    }

    public UserEntity getChatOwner() {
        return chatOwner;
    }

    public void setChatOwner(UserEntity chatOwner) {
        this.chatOwner = chatOwner;
    }

    public ChatroomEntity() {
    }

    public Set<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(Set<UserEntity> users) {
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