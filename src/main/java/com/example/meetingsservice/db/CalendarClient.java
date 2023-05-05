package com.example.meetingsservice.db;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "calendar", url = "http://isdayoff.ru")
public interface CalendarClient {

    @GetMapping(path = "/{date}")
    ResponseEntity<Long> isDayOff(@PathVariable("date") String date);
}