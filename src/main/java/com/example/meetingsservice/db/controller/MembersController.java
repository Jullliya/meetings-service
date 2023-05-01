package com.example.meetingsservice.db.controller;


import com.example.meetingsservice.db.dto.MembersDto;
import com.example.meetingsservice.db.entity.Members;
import com.example.meetingsservice.db.service.MembersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/meetings/members")
public class MembersController {

    private MembersService membersService;

    @Autowired
    public MembersController(MembersService membersService) {
        this.membersService = membersService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Members> createMember(@RequestBody MembersDto membersDto) {
        Members members = membersService.createMember(membersDto.getUserId(), membersDto.getMeetId());
        return ResponseEntity.ok(members);
    };

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity<Members> getMemberById(@PathVariable("id") Long id) {
        Members member = membersService.getMemberById(id);
        return ResponseEntity.ok(member);
    };

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void deleteMember(@PathVariable("id") Long id) {
        membersService.deleteMemberById(id);
    };
}
