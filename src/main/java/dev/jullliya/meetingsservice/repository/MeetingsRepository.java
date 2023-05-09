package dev.jullliya.meetingsservice.repository;

import dev.jullliya.meetingsservice.entity.Meeting;
import dev.jullliya.meetingsservice.entity.Members;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeetingsRepository extends JpaRepository<Meeting, Long> {

    @Query(value = "SELECT * FROM Meeting m WHERE m.Org_id=:id", nativeQuery = true)
    List<Meeting> findAllByOrgId(Long id);
}