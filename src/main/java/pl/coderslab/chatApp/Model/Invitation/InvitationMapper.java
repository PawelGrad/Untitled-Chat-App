package pl.coderslab.chatApp.Model.Invitation;

import org.springframework.stereotype.Component;
import pl.coderslab.chatApp.Model.Chatroom.Chatroom;
import pl.coderslab.chatApp.Model.Chatroom.ChatroomEntity;
import pl.coderslab.chatApp.Model.User.User;
import pl.coderslab.chatApp.Model.User.UserEntity;
import pl.coderslab.chatApp.Model.User.UserMapper;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class InvitationMapper {
    UserMapper userMapper;

    public InvitationMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public InvitationEntity convertToEntity(Invitation invitation) {
        InvitationEntity invitationEntity = new InvitationEntity();
        invitationEntity.setId(invitation.getId());
        invitationEntity.setRoom(invitation.getRoom());
        invitationEntity.setInviteLink(invitation.getInviteLink());
        invitationEntity.setInvitee(userMapper.convertToEntity(invitation.getInvitee()));
        invitationEntity.setInviter(userMapper.convertToEntity(invitation.getInviter()));
        return invitationEntity;
    }

    public Invitation convertToDto(InvitationEntity invitationEntity) {
        Invitation invitation = new Invitation();
        invitation.setId(invitationEntity.getId());
        invitation.setRoom(invitationEntity.getRoom());
        invitation.setInviteLink(invitationEntity.getInviteLink());
        invitation.setInvitee(userMapper.convertToDto(invitationEntity.getInvitee()));
        invitation.setInviter(userMapper.convertToDto(invitationEntity.getInviter()));
        return invitation;
    }

    public List<InvitationEntity> mapListToEntity(List<Invitation> invitations) {
        return invitations.stream().map(this::convertToEntity).collect(Collectors.toList());
    }

    public List<Invitation> mapListToDto(List<InvitationEntity> invitationEntities) {
        return invitationEntities.stream().map(this::convertToDto).collect(Collectors.toList());
    }


}
