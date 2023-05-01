package com.example.meetingsservice.db.entity;

import jakarta.persistence.*;
import lombok.Data;


import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;


@Data
@Entity
@Table(name = "Meeting")
public class Meeting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Meet_Id", nullable = false)
    private Long id;

    @Column(name = "Meet_name", length = 128)
    private String meetName;

    @Column(name = "Meet_key", unique = true, length = 16)
    private String meetKey;

    @Column(name = "Meet_start_date")
    private LocalDate meetStartDate;

    @Column(name = "Meet_start_time")
    private LocalTime meetStartTime;

    @Column(name = "Meet_finish_date")
    private LocalDate meetFinishDate;

    @Column(name = "Meet_finish_time")
    private LocalTime meetFinishTime;

}