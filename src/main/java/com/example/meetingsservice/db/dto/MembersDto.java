package com.example.meetingsservice.db.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class MembersDto implements Serializable {

    private Long memberId;
    private Long userId;
    private Long meetId;

}
