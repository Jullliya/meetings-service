package dev.jullliya.meetingsservice.controller;

import dev.jullliya.meetingsservice.client.CalendarClient;
import dev.jullliya.meetingsservice.dto.MeetingsDto;
import dev.jullliya.meetingsservice.entity.Meeting;
import dev.jullliya.meetingsservice.myexceptions.CreateMeetingExceptions;
import dev.jullliya.meetingsservice.service.MeetingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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
    public ResponseEntity<?> createMeet(@RequestBody MeetingsDto meetDTO) throws CreateMeetingExceptions{
        //проверка дня на статус выходного дня
        try {
            String response = calendarClient.isDayOff(meetDTO.getMeetStartDate().toString()).getBody();
            if (response.equals("1")) throw new CreateMeetingExceptions("This day is weekend! Choose another date.");
        }

        catch (CreateMeetingExceptions e){

            return ResponseEntity.ok(e.getMessage());
        }
        //проверка на существование другой встречи в указанное для новой время
        try {
            List<Meeting> meetings = meetService.getAllMeetings(meetDTO.getOrgId());

            LocalDateTime StartNewMeet = LocalDateTime.of(meetDTO.getMeetStartDate(), meetDTO.getMeetStartTime());
            LocalDateTime FinishNewMeet = LocalDateTime.of(meetDTO.getMeetFinishDate(), meetDTO.getMeetFinishTime());

            if (meetings.size() > 0) {
                for (int i = 0; i < meetings.size(); i++) {

                    LocalDateTime StartExistMeet = LocalDateTime.of(meetings.get(i).getMeetStartDate(), meetings.get(i).getMeetStartTime()) ;
                    LocalDateTime FinishExistMeet = LocalDateTime.of(meetings.get(i).getMeetFinishDate(), meetings.get(i).getMeetFinishTime());

                    if ((StartNewMeet.compareTo(StartExistMeet) >= 0 && (StartNewMeet.compareTo(FinishExistMeet) <= 0))
                            || (FinishNewMeet.compareTo(StartExistMeet) >= 0) && FinishNewMeet.compareTo(FinishExistMeet) <= 0){
                        {
                            throw new CreateMeetingExceptions("Meeting on this datetime already exist! Choose another datetime.");
                        }
                    }
                }
            }
        }
        catch (CreateMeetingExceptions e)
        {
            return ResponseEntity.ok(e.getMessage());
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
    public ResponseEntity<List<MeetingsDto>> getAllMeetings(Long id){
        List<Meeting> meetings = meetService.getAllMeetings(id);
        List<MeetingsDto> meetingsDtos = new ArrayList<MeetingsDto>();
        for (Meeting meeting: meetings){
            MeetingsDto meetingsDto = new MeetingsDto();
            meetingsDto = createDto(meetingsDto, meeting);
            meetingsDtos.add(meetingsDto);
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