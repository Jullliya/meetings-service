package dev.jullliya.meetingsservice.dto;

import dev.jullliya.meetingsservice.entity.Meeting;
import dev.jullliya.meetingsservice.entity.Members;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
public class MeetingsDto {

    private Long id;
    private String meetName;
    private String meetKey;
    private Long orgId;
    private LocalDate meetStartDate;
    private LocalDate meetFinishDate;
    private LocalTime meetStartTime;
    private LocalTime meetFinishTime;
    private List<Members> members;

}