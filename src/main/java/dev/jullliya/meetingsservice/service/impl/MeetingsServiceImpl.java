package dev.jullliya.meetingsservice.service.impl;

import dev.jullliya.meetingsservice.entity.Meeting;
import dev.jullliya.meetingsservice.repository.MeetingsRepository;
import dev.jullliya.meetingsservice.service.MeetingsService;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


@Service
@RequiredArgsConstructor
public class MeetingsServiceImpl implements MeetingsService {

    final MeetingsRepository meetRepository;

    @Override
    public Meeting createMeet(String meetName, String meetKey, Long orgId, LocalDate meetStartDate, LocalDate meetFinishDate, LocalTime meetStartTime, LocalTime meetFinishTime) {
        Meeting meet = new Meeting();
        meet = setData(meet, meetName, meetKey, orgId, meetStartDate, meetFinishDate, meetStartTime, meetFinishTime);
        return meetRepository.save(meet);
    }

    @Override
    public Meeting updateMeet(Long id, String meetName, String meetKey, Long orgId, LocalDate meetStartDate, LocalDate meetFinishDate, LocalTime meetStartTime, LocalTime meetFinishTime) {
        Meeting meet = meetRepository.findById(id).orElseThrow();
        meet = setData(meet, meetName, meetKey, orgId, meetStartDate, meetFinishDate, meetStartTime, meetFinishTime);
        return meetRepository.save(meet);
    }

    @Override
    public Meeting getMeetById(Long id) {
        return meetRepository.findById(id).orElseThrow();
    }

    @Override
    public void deleteMeetById(Long id) {
        meetRepository.deleteById(id);
    }

    @Override
    public List<Meeting> getAllMeetings(Long id) { return meetRepository.findAllByOrgId(id);}

    private Meeting setData(Meeting meet, String meetName, String meetKey, Long orgId, LocalDate meetStartDate, LocalDate meetFinishDate, LocalTime meetStartTime, LocalTime meetFinishTime){
        meet.setMeetName(meetName);
        meet.setMeetKey(meetKey);
        meet.setOrgId(orgId);
        meet.setMeetStartDate(meetStartDate);
        meet.setMeetFinishDate(meetFinishDate);
        meet.setMeetStartTime(meetStartTime);
        meet.setMeetFinishTime(meetFinishTime);
        return meet;
    }
}