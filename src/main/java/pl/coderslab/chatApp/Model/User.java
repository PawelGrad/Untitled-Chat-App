package pl.coderslab.chatApp.Model;


import javax.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String username;
    private String password;
    private String email;
    private boolean enabled;



    @OneToMany(mappedBy = "chatOwner")
    private Set<Chatroom> myChatroom;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_chatroom",
            joinColumns = { @JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "chatroom_id")})
    private Set<Chatroom> chatrooms = new HashSet<>();

    public User() {
    }


    public Set<Chatroom> getMyChatroom() {
        return myChatroom;
    }

    public void setMyChatroom(Set<Chatroom> myChatroom) {
        this.myChatroom = myChatroom;
    }

    public Set<Chatroom> getChatrooms() {
        return chatrooms;
    }

    public void setChatrooms(Set<Chatroom> chatrooms) {
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

