package com.example.meetingsservice.db.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class MeetingsDto {

    private Long id;
    private String meetName;
    private String meetKey;
    private LocalDate meetStartDate;
    private LocalDate meetFinishDate;
    private LocalTime meetStartTime;
    private LocalTime meetFinishTime;
}
