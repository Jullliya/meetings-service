package com.example.meetingsservice.db.service.impl;

import com.example.meetingsservice.db.dto.MembersDto;
import com.example.meetingsservice.db.entity.Meeting;
import com.example.meetingsservice.db.entity.Members;
import com.example.meetingsservice.db.repository.MembersRepository;
import com.example.meetingsservice.db.service.MembersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MembersServiceImpl implements MembersService {

    final MembersRepository membersRepository;

    @Override
    public Members createMember(Meeting meet, Long userId) {
        Members members = new Members();
        members = setData(members, meet, userId);
        return membersRepository.save(members);
    }

    @Override
    public Members getMemberById(Long id) {
        return membersRepository.findById(id).orElseThrow();
    };

    @Override
    public void deleteMemberById(Long id) {
        membersRepository.deleteById(id);
    }

    @Override
    public List<Members> getAllMembers() { return membersRepository.findAll();}

    private Members setData(Members members, Meeting meet, Long userId){
        members.setMeet(meet);
        members.setUserId(userId);
        return members;
    }

}
