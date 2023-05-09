package dev.jullliya.meetingsservice.service;

import dev.jullliya.meetingsservice.entity.Meeting;
import dev.jullliya.meetingsservice.entity.Members;

import java.util.List;

public interface MembersService {
    
    Members createMember(Meeting meet, Long userId);

    void deleteMemberById(Long id);

    Members getMemberById(Long id);

    List<Members> getAllMembers(Long id);

}
