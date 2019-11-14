package pl.coderslab.chatApp.Model.Invitation;

import pl.coderslab.chatApp.Model.Chatroom.ChatroomEntity;
import pl.coderslab.chatApp.Model.User.UserEntity;

import javax.persistence.*;

public class Invitation {

    private Long id;

    private UserEntity inviter;

    private UserEntity invitee;

    private ChatroomEntity room;

    private boolean accepted;

    public Invitation() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserEntity getInviter() {
        return inviter;
    }

    public void setInviter(UserEntity inviter) {
        this.inviter = inviter;
    }

    public UserEntity getInvitee() {
        return invitee;
    }

    public void setInvitee(UserEntity invitee) {
        this.invitee = invitee;
    }

    public ChatroomEntity getRoom() {
        return room;
    }

    public void setRoom(ChatroomEntity room) {
        this.room = room;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }
}
