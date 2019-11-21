package pl.coderslab.chatApp.Model.Invitation;

import pl.coderslab.chatApp.Model.Chatroom.ChatroomEntity;
import pl.coderslab.chatApp.Model.User.User;
import pl.coderslab.chatApp.Model.User.UserEntity;

import javax.persistence.*;

public class Invitation {

    private Long id;

    private User inviter;

    private User invitee;

    private ChatroomEntity room;

    private String inviteLink;
  //  private boolean accepted;

    public Invitation() {
    }

    public String getInviteLink() {
        return inviteLink;
    }

    public void setInviteLink(String inviteLink) {
        this.inviteLink = inviteLink;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getInviter() {
        return inviter;
    }

    public void setInviter(User inviter) {
        this.inviter = inviter;
    }

    public User getInvitee() {
        return invitee;
    }

    public void setInvitee(User invitee) {
        this.invitee = invitee;
    }

    public ChatroomEntity getRoom() {
        return room;
    }

    public void setRoom(ChatroomEntity room) {
        this.room = room;
    }

//    public boolean isAccepted() {
//        return accepted;
//    }
//
//    public void setAccepted(boolean accepted) {
//        this.accepted = accepted;
//    }
}
