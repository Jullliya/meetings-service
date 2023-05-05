package com.example.meetingsservice.db.dto;

import com.example.meetingsservice.db.entity.Members;
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
