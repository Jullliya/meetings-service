package com.example.meetingsservice.db.service;

import com.example.meetingsservice.db.entity.Meeting;
import com.example.meetingsservice.db.entity.Members;

import java.util.List;

public interface MembersService {
    
    Members createMember(Meeting meet, Long userId);

    void deleteMemberById(Long id);

    Members getMemberById(Long id);

    List<Members> getAllMembers();

}
