package com.example.event_management.service;


import com.example.event_management.dtos.VenueDto;
import com.example.event_management.entity.Event;
import com.example.event_management.entity.Venue;
import com.example.event_management.repository.VenueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VenueService {

    private final VenueRepository venueRepository;

    public void register( VenueDto request ) throws Exception {
        if(venueRepository.findByEmail(request.getEmail()) != null || venueRepository.findByContactNumber(request.getContactNumber())!= null ){
            throw new Exception("Venue exists with the mail");
        }
        Venue venue = new Venue();
        venue.setName(request.getName());
        venue.setCapacity(request.getCapacity());
        venue.setAddress(request.getAddress());
        venue.setContactNumber(request.getContactNumber());
        venue.setEmail(request.getEmail());
        venueRepository.save(venue);
    }


    public Venue modifyVenue( VenueDto request ) throws Exception {
        Venue venue = venueRepository.findByEmail(request.getEmail());
        venue.setName(request.getName());
        venue.setCapacity(request.getCapacity());
        venue.setAddress(request.getAddress());
        venue.setContactNumber(request.getContactNumber());
        venueRepository.save(venue);
        return venue;
    }

    public Venue delete(Long id){
        Venue venue = venueRepository.findByVenueId(id);
        venueRepository.delete(venue);
        return venue;
    }

    public Venue getVenue( Long id){
        return venueRepository.findByVenueId(id);
    }
    public List<Event> getEvents(Long id){
        return venueRepository.findByVenueId(id).getEvents();
    }


}

