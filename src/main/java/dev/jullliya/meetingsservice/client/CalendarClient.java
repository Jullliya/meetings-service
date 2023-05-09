package dev.jullliya.meetingsservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "calendar", url = "http://isdayoff.ru")
public interface CalendarClient {

    @GetMapping(value = "/{date}")
    ResponseEntity<String> isDayOff(@PathVariable("date") String date);
}