package pl.coderslab.chatApp.Model.Chatroom;

import pl.coderslab.chatApp.Model.Invitation.InvitationEntity;
import pl.coderslab.chatApp.Model.Message.MessageEntity;
import pl.coderslab.chatApp.Model.User.UserEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

public class Chatroom {

    private Long id;

    private String roomName;

    private Set<InvitationEntity> invitations;

    private Set<MessageEntity> messages;

    private UserEntity chatOwner;

    private Set<UserEntity> users = new HashSet<>();

    public Chatroom() {
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

    public Set<InvitationEntity> getInvitations() {
        return invitations;
    }

    public void setInvitations(Set<InvitationEntity> invitations) {
        this.invitations = invitations;
    }

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

    public Set<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(Set<UserEntity> users) {
        this.users = users;
    }
}
