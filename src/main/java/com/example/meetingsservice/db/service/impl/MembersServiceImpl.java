package com.example.meetingsservice.db.service.impl;

import com.example.meetingsservice.db.entity.Members;
import com.example.meetingsservice.db.repository.MembersRepository;
import com.example.meetingsservice.db.service.MembersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MembersServiceImpl implements MembersService {

    final MembersRepository membersRepository;
    @Override
    public Members createMember(Long meetId, Long userId) {
        Members members = new Members();
        members = setData(members, meetId, userId);
        return membersRepository.save(members);
    }

    @Override
    public Members getMemberById(Long id) {
        return membersRepository.findById(id).orElseThrow();
    }

    @Override
    public void deleteMemberById(Long id) {
        membersRepository.deleteById(id);
    }

    private Members setData(Members members, Long meetId, Long userId){
        members.setMeetId(meetId);
        members.setUserId(userId);
        return members;
    }

}
