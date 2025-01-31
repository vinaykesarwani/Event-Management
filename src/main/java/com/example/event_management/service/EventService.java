package com.example.event_management.service;

import com.example.event_management.dtos.EventDto;
import com.example.event_management.entity.Event;
import com.example.event_management.entity.User;
import com.example.event_management.entity.Venue;
import com.example.event_management.repository.EventRepository;
import com.example.event_management.repository.UserRepository;
import com.example.event_management.repository.VenueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventService {

    private final VenueRepository venueRepository;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;
    private final JwtService jwtService;

    public List<AbstractMap.SimpleEntry<LocalDateTime, LocalDateTime>> createEvent(EventDto request, String token) throws Exception {
        Venue venue = venueRepository.findByVenueId(request.getVenueId());
        if(venue==null){
            throw new Exception("Venue Not found");
        }
//
//        List<Event> existingEvents = venue.getEvents();

        LocalDateTime startTime = request.getStartTime();
        LocalDateTime endTime = request.getEndTime();
//        boolean hasOverlap=false;
//        List<Event> overlappingEvents=new ArrayList<>();
//        System.out.println(overlappingEvents);
//        List<Event> overlaps = existingEvents.stream()
//                .filter(event ->
//                        event.getStartTime().isBefore(endTime) &&
//                                event.getEndTime().isAfter(startTime)
//                )
//                .collect(Collectors.toList());
//
//        overlappingEvents.addAll(overlaps);
//
//
//        if (!overlappingEvents.isEmpty()){
//            hasOverlap=true;
//        }
//
//
//        var newEventDate = startTime.toLocalDate();
        List<AbstractMap.SimpleEntry<LocalDateTime, LocalDateTime>> timeSlots = new ArrayList<>();


//        if (hasOverlap) {
//            timeSlots = existingEvents.stream()
//                    .filter(event -> event.getStartTime().toLocalDate().equals(newEventDate)) // Filter events on the same date
//                    .map(event -> new AbstractMap.SimpleEntry<>(event.getStartTime(), event.getEndTime()))
//                    .collect(Collectors.toList());
//        } else {
            // No overlap, create the new event
            String userEmail = jwtService.extractUsername(token);
            User organizer = userRepository.findByEmail(userEmail).orElseThrow();
//            Integer organizerId = organizer.getId();

            Venue ven= venueRepository.findByVenueId(request.getVenueId());

            Event event = new Event();
            event.setName(request.getName());
            event.setDescription(request.getDescription());
            event.setStartTime(startTime);
            event.setEndTime(endTime);
            event.setVenue(ven);
            event.setOrganizer(organizer);

            eventRepository.save(event);
//        }
//        System.out.println(overlappingEvents);

        return timeSlots;
    }
}
