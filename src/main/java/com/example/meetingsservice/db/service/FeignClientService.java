package com.example.meetingsservice.db.service;

import com.example.meetingsservice.MeetingsServiceApplication;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "calendar", url = "isdayoff.ru", configuration = MeetingsServiceApplication.class)
public interface FeignClientService {

    @GetMapping("https://www.isdayoff.ru/desc/{date}")
    ResponseEntity<Long> isDayOff(@PathVariable("date") String date);
}
