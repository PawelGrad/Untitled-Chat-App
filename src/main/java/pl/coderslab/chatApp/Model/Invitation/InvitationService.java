package pl.coderslab.chatApp.Model.Invitation;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.coderslab.chatApp.Model.Chatroom.ChatroomEntity;
import pl.coderslab.chatApp.Model.Chatroom.ChatroomService;
import pl.coderslab.chatApp.Model.Message.MessageEntity;
import pl.coderslab.chatApp.Model.User.UserEntity;
import pl.coderslab.chatApp.Model.User.UserService;
import pl.coderslab.chatApp.Repos.ChatroomRepository;
import pl.coderslab.chatApp.Repos.InvitationRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class InvitationService {

    private final InvitationMapper invitationMapper;
    private final InvitationRepository invitationRepository;
    private final UserService userService;
    private final ChatroomService chatroomService;

    public InvitationService(InvitationMapper invitationMapper, InvitationRepository invitationRepository, UserService userService, ChatroomService chatroomService) {
        this.invitationMapper = invitationMapper;
        this.invitationRepository = invitationRepository;
        this.userService = userService;
        this.chatroomService = chatroomService;
    }

    public void save(InvitationEntity invitationEntity) {
        invitationRepository.save(invitationEntity);
    }
    public List<Invitation> getUserInvitations() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        UserEntity user = userService.findByUserName(currentPrincipalName);
        return invitationMapper.mapListToDto(invitationRepository.findUserInvitations(user.getId()));}
        public void addUserToRoom(Long userId,Long roomId) {

            ChatroomEntity chatroomEntity = chatroomService.findRoomById(roomId);
            UserEntity userEntity = userService.findUserById(userId);

            userEntity.getChatrooms().add(chatroomEntity);
            userService.save(userEntity);

        }
        public void removeInvitation(Long id) {
            invitationRepository.deleteById(id);
        }
}
