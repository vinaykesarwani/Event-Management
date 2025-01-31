package com.example.event_management.repository;

import com.example.event_management.entity.Venue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VenueRepository extends JpaRepository<Venue, Long> {
    Venue findByEmail(String email);
    Venue findByContactNumber(String contactNumber);
    Venue findByVenueId(Long id);
}
