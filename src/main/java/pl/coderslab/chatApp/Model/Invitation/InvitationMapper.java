package pl.coderslab.chatApp.Model.Invitation;

import org.springframework.stereotype.Component;
import pl.coderslab.chatApp.Model.Chatroom.Chatroom;
import pl.coderslab.chatApp.Model.Chatroom.ChatroomEntity;
import pl.coderslab.chatApp.Model.User.User;
import pl.coderslab.chatApp.Model.User.UserEntity;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class InvitationMapper {

    public InvitationEntity convertToEntity(Invitation invitation) {
        InvitationEntity invitationEntity = new InvitationEntity();
        invitationEntity.setId(invitation.getId());
        invitationEntity.setRoom(invitation.getRoom());
        invitationEntity.setAccepted(invitation.isAccepted());
        invitationEntity.setInvitee(invitation.getInvitee());
        invitationEntity.setInviter(invitation.getInviter());
        return invitationEntity;
    }

    public Invitation convertToDto(InvitationEntity invitationEntity) {
        Invitation invitation = new Invitation();
        invitation.setId(invitationEntity.getId());
        invitation.setRoom(invitationEntity.getRoom());
        invitation.setAccepted(invitationEntity.isAccepted());
        invitation.setInvitee(invitationEntity.getInvitee());
        invitation.setInviter(invitationEntity.getInviter());
        return invitation;
    }

    public Set<InvitationEntity> mapListToEntity(Set<Invitation> invitations) {
        return invitations.stream().map(this::convertToEntity).collect(Collectors.toSet());
    }

    public Set<Invitation> mapListToDto(Set<InvitationEntity> invitationEntities) {
        return invitationEntities.stream().map(this::convertToDto).collect(Collectors.toSet());
    }


}
