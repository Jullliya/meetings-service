package com.example.meetingsservice.db.dto;

import com.example.meetingsservice.db.entity.Meeting;
import lombok.Data;

import java.io.Serializable;

@Data
public class MembersDto implements Serializable {

    private Long memberId;
    private Long userId;
    private Meeting meet;

}
