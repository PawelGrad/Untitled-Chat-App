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
    InvitationEntity findByInviteLink(String string);

    @Query(value = "SELECT * FROM invitations WHERE invitee_id = ?1 and inviter_id = ?2 and room_id = ?3", nativeQuery = true)
    InvitationEntity exists(Long inviteeId, Long inviterId, Long roomId);

    @Query(value = "SELECT inviteLink FROM invitations WHERE room_id =?1", nativeQuery = true)
    String getLink(Long id);
    void deleteById(Long id);
}
