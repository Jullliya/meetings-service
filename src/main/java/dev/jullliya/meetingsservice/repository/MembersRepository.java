package dev.jullliya.meetingsservice.repository;

import dev.jullliya.meetingsservice.entity.Members;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MembersRepository extends JpaRepository<Members, Long> {

    @Query(value = "SELECT * FROM Members m WHERE m.User_id=:id", nativeQuery = true)
    List<Members> findAllByUserId(Long id);
}