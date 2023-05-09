package dev.jullliya.meetingsservice.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

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

    @Column(name = "Organizer_Id")
    private Long orgId;

    @Column(name = "Meet_start_date")
    private LocalDate meetStartDate;

    @Column(name = "Meet_start_time")
    private LocalTime meetStartTime;

    @Column(name = "Meet_finish_date")
    private LocalDate meetFinishDate;

    @Column(name = "Meet_finish_time")
    private LocalTime meetFinishTime;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "meet")
    @JsonManagedReference
    private List<Members> members;

}