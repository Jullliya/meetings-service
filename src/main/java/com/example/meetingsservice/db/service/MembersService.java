package com.example.meetingsservice.db.service;

import com.example.meetingsservice.db.entity.Members;

public interface MembersService {
    
    Members createMember(Long meetId, Long userId);

    Members getMemberById(Long id);

    void deleteMemberById(Long id);
}
