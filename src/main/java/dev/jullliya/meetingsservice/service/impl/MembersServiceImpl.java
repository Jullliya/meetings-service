package dev.jullliya.meetingsservice.service.impl;

import dev.jullliya.meetingsservice.entity.Meeting;
import dev.jullliya.meetingsservice.entity.Members;
import dev.jullliya.meetingsservice.repository.MembersRepository;
import dev.jullliya.meetingsservice.service.MembersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
    public List<Members> getAllMembers(Long id) { return membersRepository.findAllByUserId(id);}

    private Members setData(Members members, Meeting meet, Long userId){
        members.setMeet(meet);
        members.setUserId(userId);
        return members;
    }

}
