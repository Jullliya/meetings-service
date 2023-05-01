package com.example.meetingsservice.db.repository;

import com.example.meetingsservice.db.entity.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeetingsRepository extends JpaRepository<Meeting, Long> {

}