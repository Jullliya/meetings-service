package dev.jullliya.meetingsservice.dto;

import dev.jullliya.meetingsservice.entity.Meeting;
import lombok.Data;

import java.io.Serializable;

@Data
public class MembersDto implements Serializable {

    private Long memberId;
    private Long userId;
    private Meeting meet;
}
