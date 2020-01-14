package pl.project.chatApp.Model.Invitation;


import pl.project.chatApp.Model.Chatroom.ChatroomEntity;
import pl.project.chatApp.Model.User.UserEntity;

import javax.persistence.*;

@Entity
@Table(name = "invitations")
public class InvitationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "inviter_id", nullable = false)
    private UserEntity inviter;

    @ManyToOne
    @JoinColumn(name = "invitee_id")
    private UserEntity invitee;

    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    private ChatroomEntity room;

    private String inviteLink;
   // private boolean accepted;

    public InvitationEntity() {
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
//
//    public boolean isAccepted() {
//        return accepted;
//    }
//
//    public void setAccepted(boolean accepted) {
//        this.accepted = accepted;
//    }
}
