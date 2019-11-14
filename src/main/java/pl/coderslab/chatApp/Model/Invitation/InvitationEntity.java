package pl.coderslab.chatApp.Model.Invitation;


import pl.coderslab.chatApp.Model.Chatroom.Chatroom;
import pl.coderslab.chatApp.Model.User.User;

import javax.persistence.*;

@Entity
@Table(name = "invitations")
public class InvitationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "inviter_id", nullable = false)
    private User inviter;

    @ManyToOne
    @JoinColumn(name = "invitee_id", nullable = false)
    private User invitee;

    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    private Chatroom room;

    private boolean accepted;
}
