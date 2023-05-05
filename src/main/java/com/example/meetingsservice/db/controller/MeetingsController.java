package com.example.meetingsservice.db.controller;

import com.example.meetingsservice.db.CalendarClient;
import com.example.meetingsservice.db.dto.MeetingsDto;
import com.example.meetingsservice.db.entity.Meeting;
import com.example.meetingsservice.db.service.MeetingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/meetings")
public class MeetingsController {

    private final MeetingsService meetService;
    @Autowired
    private CalendarClient calendarClient;

    @Autowired
    public MeetingsController(MeetingsService meetService) {
        this.meetService = meetService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> createMeet(@RequestBody MeetingsDto meetDTO) {

        // Пробую получить ответ по дате старта события, все еще прилетет ошибка 500
//        Long response = calendarClient.isDayOff(meetDTO.getMeetStartDate().toString()).getBody();
//        if (response == 1L) meetDTO.setMeetStartDate(meetDTO.getMeetStartDate().plusDays(1));

        try {
            ArrayList<Meeting> meetings = (ArrayList<Meeting>) meetService.getAllMeetings();
            if (meetings.size() > 0) {
                for (int i = 0; i < meetings.size(); i++) {
                    if (meetDTO.getOrgId().equals(meetings.get(i).getOrgId()) & (meetDTO.getMeetStartDate().equals(meetings.get(i).getMeetStartDate()) | meetDTO.getMeetFinishDate().equals(meetings.get(i).getMeetFinishDate())
                            | meetDTO.getMeetStartDate().equals(meetings.get(i).getMeetFinishDate()) | meetDTO.getMeetFinishDate().equals(meetings.get(i).getMeetStartDate()))) {
                        if ((meetDTO.getMeetStartTime().compareTo(meetings.get(i).getMeetStartTime()) >= 0 & meetDTO.getMeetStartTime().compareTo(meetings.get(i).getMeetFinishTime()) <= 0)
                                | (meetDTO.getMeetFinishTime().compareTo(meetings.get(i).getMeetStartTime()) >= 0 & meetDTO.getMeetFinishTime().compareTo(meetings.get(i).getMeetFinishTime()) <= 0)) {
                            throw new Exception();
                        }
                    }
                }
            }
        }
        catch (Exception e)
        {
            return new ResponseEntity<> (HttpStatus.ALREADY_REPORTED);
        }
        Meeting meet = meetService.createMeet(meetDTO.getMeetName(), meetDTO.getMeetKey(), meetDTO.getOrgId(), meetDTO.getMeetStartDate(), meetDTO.getMeetFinishDate(), meetDTO.getMeetStartTime(), meetDTO.getMeetFinishTime());
        meetDTO.setId(meet.getId());
        return ResponseEntity.ok(meetDTO);
    };

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<MeetingsDto> updateMeet(@RequestBody MeetingsDto meetDTO) {
        Meeting meet = meetService.updateMeet(meetDTO.getId(), meetDTO.getMeetName(), meetDTO.getMeetKey(), meetDTO.getOrgId(), meetDTO.getMeetStartDate(), meetDTO.getMeetFinishDate(), meetDTO.getMeetStartTime(), meetDTO.getMeetFinishTime());
        return ResponseEntity.ok(meetDTO);
    };

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity<MeetingsDto> getMeetById(@PathVariable("id") Long id) {
        Meeting meet = meetService.getMeetById(id);
        MeetingsDto meetingsDto = new MeetingsDto();
        meetingsDto = createDto(meetingsDto, meet);
        return ResponseEntity.ok(meetingsDto);
    };

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void deleteMeet(@PathVariable("id") Long id) {
        meetService.deleteMeetById(id);
    };

    @RequestMapping(value = "all", method = RequestMethod.GET)
    public ResponseEntity<ArrayList<MeetingsDto>> getAllMeetings(){
        MeetingsDto meetingsDto = new MeetingsDto();
        ArrayList<Meeting> meetings = (ArrayList<Meeting>) meetService.getAllMeetings();
        ArrayList<MeetingsDto> meetingsDtos = new ArrayList<MeetingsDto>();
        for (int i = 0; i < meetings.size(); i++){
            meetingsDtos.add(createDto(meetingsDto, meetings.get(i)));
        };
        return ResponseEntity.ok(meetingsDtos);
    };

    private MeetingsDto createDto(MeetingsDto meetingsDto, Meeting meet){
        meetingsDto.setId(meet.getId());
        meetingsDto.setMeetName(meet.getMeetName());
        meetingsDto.setMeetKey(meet.getMeetKey());
        meetingsDto.setOrgId(meet.getOrgId());
        meetingsDto.setMeetStartDate(meet.getMeetStartDate());
        meetingsDto.setMeetFinishDate(meet.getMeetFinishDate());
        meetingsDto.setMeetStartTime(meet.getMeetStartTime());
        meetingsDto.setMeetFinishTime(meet.getMeetFinishTime());
        return meetingsDto;
    };
}