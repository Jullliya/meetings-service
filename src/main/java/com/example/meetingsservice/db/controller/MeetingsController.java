package com.example.meetingsservice.db.controller;

import com.example.meetingsservice.db.dto.MeetingsDto;
import com.example.meetingsservice.db.entity.Meeting;
import com.example.meetingsservice.db.service.MeetingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/meetings")
public class MeetingsController {

    private MeetingsService meetService;

    @Autowired
    public MeetingsController(MeetingsService meetService) {
        this.meetService = meetService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Meeting> createMeet(@RequestBody MeetingsDto meetDTO) {
        Meeting meet = meetService.createMeet(meetDTO.getMeetName(), meetDTO.getMeetKey(), meetDTO.getMeetStartDate(), meetDTO.getMeetFinishDate(), meetDTO.getMeetStartTime(), meetDTO.getMeetFinishTime());
        return ResponseEntity.ok(meet);
    };

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Meeting> updateMeet(@RequestBody MeetingsDto meetDTO) {
        Meeting meet = meetService.updateMeet(meetDTO.getId(), meetDTO.getMeetName(), meetDTO.getMeetKey(), meetDTO.getMeetStartDate(), meetDTO.getMeetFinishDate(), meetDTO.getMeetStartTime(), meetDTO.getMeetFinishTime());
        return ResponseEntity.ok(meet);
    };

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity<Meeting> getMeetById(@PathVariable("id") Long id) {
        Meeting meet = meetService.getMeetById(id);
        return ResponseEntity.ok(meet);
    };

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void deleteMeet(@PathVariable("id") Long id) {
        meetService.deleteMeetById(id);
    };


}