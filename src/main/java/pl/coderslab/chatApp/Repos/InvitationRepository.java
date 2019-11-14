package pl.coderslab.chatApp.Repos;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.chatApp.Model.Invitation.InvitationEntity;

public interface InvitationRepository extends JpaRepository<InvitationEntity, Long> {

}
