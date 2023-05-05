package com.example.meetingsservice.db.controller;

import com.example.meetingsservice.db.dto.MembersDto;
import com.example.meetingsservice.db.entity.Members;
import com.example.meetingsservice.db.service.MembersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/members")
public class MembersController {

    private final MembersService membersService;

    @Autowired
    public MembersController(MembersService membersService) {
        this.membersService = membersService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<MembersDto> createMember(@RequestBody MembersDto membersDto) {
        try{
            ArrayList<Members> members = (ArrayList<Members>) membersService.getAllMembers();
            if (members.size() > 0){
                for ( int i = 0; i < members.size(); i++){
                    if(membersDto.getUserId().equals(members.get(i).getUserId()) | membersDto.getMeet().getId().equals(members.get(i).getMeet().getId()) ){
                        if(membersDto.getMeet().getMeetStartDate().equals(members.get(i).getMeet().getMeetStartDate()) | membersDto.getMeet().getMeetStartDate().equals(members.get(i).getMeet().getMeetFinishDate())
                        | membersDto.getMeet().getMeetFinishDate().equals(members.get(i).getMeet().getMeetFinishDate()) | membersDto.getMeet().getMeetFinishDate().equals(members.get(i).getMeet().getMeetStartDate())){
                            if ((membersDto.getMeet().getMeetStartTime().compareTo(members.get(i).getMeet().getMeetStartTime()) >= 0 & membersDto.getMeet().getMeetStartTime().compareTo(members.get(i).getMeet().getMeetFinishTime()) <= 0)
                                    | (membersDto.getMeet().getMeetFinishTime().compareTo(members.get(i).getMeet().getMeetStartTime()) >= 0 & membersDto.getMeet().getMeetFinishTime().compareTo(members.get(i).getMeet().getMeetFinishTime()) <= 0)){
                                throw new Exception();
                            }
                        }
                    }
                }
            }
        }
        catch (Exception e){
            return new ResponseEntity<> (HttpStatus.ALREADY_REPORTED);
        }
        Members members = membersService.createMember(membersDto.getMeet(), membersDto.getUserId());
        membersDto.setMemberId(members.getMemberId());
        return ResponseEntity.ok(membersDto);
    };

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity<MembersDto> getMemberById(@PathVariable("id") Long id) {
        Members member = membersService.getMemberById(id);
        MembersDto membersDto = new MembersDto();
        membersDto = createDto(membersDto, member);
        return ResponseEntity.ok(membersDto);
    };

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void deleteMember(@PathVariable("id") Long id) {
        membersService.deleteMemberById(id);
    };

    @RequestMapping(value = "all", method = RequestMethod.GET)
    public ResponseEntity<ArrayList<MembersDto>> getAllMembers(){
        MembersDto membersDto = new MembersDto();
        ArrayList<Members> members = (ArrayList<Members>) membersService.getAllMembers();
        ArrayList<MembersDto> membersDtos = new ArrayList<MembersDto>();
        for (int i = 0; i < members.size(); i++){
            membersDtos.add(createDto(membersDto, members.get(i)));
        };
        return ResponseEntity.ok(membersDtos);
    };

    private MembersDto createDto(MembersDto membersDto, Members member){
        membersDto.setMemberId(member.getMemberId());
        membersDto.setMeet(member.getMeet());
        membersDto.setUserId(member.getUserId());
        return membersDto;
    };

}
