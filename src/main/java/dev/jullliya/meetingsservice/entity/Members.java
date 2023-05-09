package dev.jullliya.meetingsservice.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;


@Data
@Entity
@Table(name = "Members")
public class Members {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Member_id")
    private Long memberId;

    @ManyToOne( fetch = FetchType.EAGER)
    @JoinColumn(name = "Meet_id", nullable = false)
    @JsonBackReference
    private Meeting meet;

    @Column(name = "User_id")
    private Long userId;

}