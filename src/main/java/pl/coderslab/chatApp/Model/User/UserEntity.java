package pl.coderslab.chatApp.Model.User;


import pl.coderslab.chatApp.Model.Chatroom.ChatroomEntity;
import pl.coderslab.chatApp.Model.Invitation.InvitationEntity;

import javax.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String username;
    private String password;
    private String email;
    private boolean enabled;

    @OneToMany(mappedBy = "inviter")
    private Set<InvitationEntity> inviters;

    @OneToMany(mappedBy = "invitee")
    private Set<InvitationEntity> invitee;

    @OneToMany(mappedBy = "chatOwner")
    private Set<ChatroomEntity> myChatroom;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_chatroom",
            joinColumns = { @JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "chatroom_id")})
    private Set<ChatroomEntity> chatrooms = new HashSet<>();



    public UserEntity() {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    //
//    public Set<Role> getRoles() {
//        return roles;
//    }
//
//    public void setRoles(Set<Role> roles) {
//        this.roles = roles;
//    }
}

