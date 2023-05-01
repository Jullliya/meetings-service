package com.example.meetingsservice.db.entity;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "Members")
public class Members {

    @Id
    @GeneratedValue
    @Column(name = "Member_id")
    private Long memberId;

    @Column(name = "User_id")
    private Long userId;

    @Column(name = "Meet_ID")
    private Long meetId;

}