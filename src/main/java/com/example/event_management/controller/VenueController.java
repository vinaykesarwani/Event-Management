package com.example.event_management.controller;
import com.example.event_management.dtos.VenueDto;
import com.example.event_management.entity.Event;
import com.example.event_management.entity.Venue;
import com.example.event_management.service.VenueService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/venue")
@RestController
@RequiredArgsConstructor
public class VenueController {

    private final VenueService venueService;

    @PostMapping("/create")
    public ResponseEntity<String> register(@RequestBody VenueDto request) throws Exception {
        venueService.register(request);
        String response = "Successfully created venue -"+ request.getName();
        return ResponseEntity.ok(response);
    }
    @DeleteMapping ("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) throws Exception {
        Venue venue = venueService.delete(id);
        String response = "Successfully deleted venue -"+ venue.getName();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Venue> getVenue(@PathVariable Long id){
        return ResponseEntity.ok(venueService.getVenue(id));
    }

    @GetMapping("/{id}/events")
    public ResponseEntity<List<Event>> getEvents(@PathVariable Long id){
        return ResponseEntity.ok(venueService.getEvents(id));
    }

    @PutMapping("/modify")
    public ResponseEntity<Venue> modifyVenue(@RequestBody VenueDto request) throws Exception {
        return ResponseEntity.ok(venueService.modifyVenue(request));
    }
}