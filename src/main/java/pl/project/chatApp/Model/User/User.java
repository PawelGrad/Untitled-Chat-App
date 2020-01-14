package pl.project.chatApp.Model.User;

import pl.project.chatApp.Model.Chatroom.ChatroomEntity;
import pl.project.chatApp.Model.Invitation.InvitationEntity;

import java.util.HashSet;
import java.util.Set;

public class User {

    private Long id;

    private String username;
    private String password;
    private String email;
    private boolean enabled;


    private Set<InvitationEntity> inviters;


    private Set<InvitationEntity> invitee;


    private Set<ChatroomEntity> myChatroom;


    private Set<ChatroomEntity> chatrooms = new HashSet<>();

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<InvitationEntity> getInviters() {
        return inviters;
    }

    public void setInviters(Set<InvitationEntity> inviters) {
        this.inviters = inviters;
    }

    public Set<InvitationEntity> getInvitee() {
        return invitee;
    }

    public void setInvitee(Set<InvitationEntity> invitee) {
        this.invitee = invitee;
    }

    public Set<ChatroomEntity> getMyChatroom() {
        return myChatroom;
    }

    public void setMyChatroom(Set<ChatroomEntity> myChatroom) {
        this.myChatroom = myChatroom;
    }

    public Set<ChatroomEntity> getChatrooms() {
        return chatrooms;
    }

    public void setChatrooms(Set<ChatroomEntity> chatrooms) {
        this.chatrooms = chatrooms;
    }
}
