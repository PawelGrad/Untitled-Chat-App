package pl.coderslab.chatApp.Repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.chatApp.Model.Chatroom.ChatroomEntity;
import pl.coderslab.chatApp.Model.Invitation.InvitationEntity;

import java.util.List;

public interface InvitationRepository extends JpaRepository<InvitationEntity, Long> {

    @Query(value = "SELECT *" +
            "FROM invitations " +
            "WHERE invitee_id = ?;", nativeQuery = true)
    List<InvitationEntity> findUserInvitations(Long id);
}
