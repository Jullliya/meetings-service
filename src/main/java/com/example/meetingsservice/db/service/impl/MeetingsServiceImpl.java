package com.example.meetingsservice.db.service.impl;

import com.example.meetingsservice.WeekDayExceptions;
import com.example.meetingsservice.db.entity.Meeting;
import com.example.meetingsservice.db.repository.MeetingsRepository;
import com.example.meetingsservice.db.service.FeignClientService;
import com.example.meetingsservice.db.service.MeetingsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
@RequiredArgsConstructor
public class MeetingsServiceImpl implements MeetingsService {

    final MeetingsRepository meetRepository;
    final FeignClientService meetingClient;

    @Override
    public Meeting createMeet(String meetName, String meetKey, LocalDate meetStartDate, LocalDate meetFinishDate, LocalTime meetStartTime, LocalTime meetFinishTime) {

        try {
            if (meetingClient.isDayOff(meetStartDate.toString()).toString() == "1" || meetingClient.isDayOff(meetFinishDate.toString()).toString() == "1")
            {
                throw new WeekDayExceptions();
            };
        }
        catch (WeekDayExceptions e)
        {
            System.out.println(e.toString());
            return null;
        }

        Meeting meet = new Meeting();
        meet = setData(meet, meetName, meetKey, meetStartDate, meetFinishDate, meetStartTime, meetFinishTime);

        return meetRepository.save(meet);
    }

    @Override
    public Meeting updateMeet(Long id, String meetName, String meetKey, LocalDate meetStartDate, LocalDate meetFinishDate, LocalTime meetStartTime, LocalTime meetFinishTime) {
        Meeting meet = meetRepository.findById(id).orElseThrow();
        meet = setData(meet, meetName, meetKey, meetStartDate, meetFinishDate, meetStartTime, meetFinishTime);
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

    private Meeting setData(Meeting meet, String meetName, String meetKey, LocalDate meetStartDate, LocalDate meetFinishDate, LocalTime meetStartTime, LocalTime meetFinishTime){
        meet.setMeetName(meetName);
        meet.setMeetKey(meetKey);
        meet.setMeetStartDate(meetStartDate);
        meet.setMeetFinishDate(meetFinishDate);
        meet.setMeetStartTime(meetStartTime);
        meet.setMeetFinishTime(meetFinishTime);
        return meet;
    }
}