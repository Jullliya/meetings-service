package dev.jullliya.meetingsservice.service;

import dev.jullliya.meetingsservice.entity.Meeting;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface MeetingsService {

    Meeting createMeet(String meetName, String meetKey, Long orgId, LocalDate meetStartDate, LocalDate meetFinishDate, LocalTime meetStartTime, LocalTime meetFinishTime);

    Meeting updateMeet(Long id, String meetName, String meetKey, Long orgId, LocalDate meetStartDate, LocalDate meetFinishDate, LocalTime meetStartTime, LocalTime meetFinishTime);

    Meeting getMeetById(Long id);

    void deleteMeetById(Long id);

    List<Meeting> getAllMeetings(Long id);
}
