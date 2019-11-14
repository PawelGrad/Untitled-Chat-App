package pl.coderslab.chatApp.Model.Invitation;


import org.springframework.stereotype.Service;
import pl.coderslab.chatApp.Model.Message.MessageEntity;
import pl.coderslab.chatApp.Repos.InvitationRepository;

import javax.transaction.Transactional;

@Service
@Transactional
public class InvitationService {

    private final InvitationMapper invitationMapper;
    private final InvitationRepository invitationRepository;

    public InvitationService(InvitationMapper invitationMapper, InvitationRepository invitationRepository) {
        this.invitationMapper = invitationMapper;
        this.invitationRepository = invitationRepository;
    }

    public void save(InvitationEntity invitationEntity) {
        invitationRepository.save(invitationEntity);
    }
}
