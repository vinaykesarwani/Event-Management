package com.example.event_management.controller;

import com.example.event_management.dtos.EventDto;
import com.example.event_management.entity.Event;
import com.example.event_management.entity.User;
import com.example.event_management.service.EventService;
import com.example.event_management.service.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.AbstractMap;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/event")
public class EventController {

    private final EventService eventService;

    @PostMapping("/create")
    public ResponseEntity<String> createEvent(@RequestBody EventDto request, @NonNull HttpServletRequest header) throws Exception {

        String token = header.getHeader("Authorization").substring(7);
        List<AbstractMap.SimpleEntry<LocalDateTime, LocalDateTime>> timeSlots = eventService.createEvent(request, token);
        if (timeSlots.isEmpty()){
            return ResponseEntity.ok("Event added Successfully");
        }
        String timeSlotsString = timeSlots.stream()
                .map(slot -> "(" + slot.getKey() + ", " + slot.getValue() + ")") // Format each time slot
                .collect(Collectors.joining(", "));
        return ResponseEntity.ok("Event time overlapping with our events. Freezed time slots on that day are "+ timeSlotsString);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> getEvent(@PathVariable Long id){
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Event> deleteEvent(@PathVariable Long id){
        return null;
    }

    @PutMapping("/modify")
    public ResponseEntity<Event> modifyEvent(@RequestBody EventDto request){
        return null;
    }

    @GetMapping("/{id}/attendees")
    public ResponseEntity<List<User>> getAttendees(@PathVariable Long id){
        return null;
    }
}