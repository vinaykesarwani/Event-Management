package com.example.event_management.controller;

import com.example.event_management.dtos.UserDto;
import com.example.event_management.entity.Event;
import com.example.event_management.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @PutMapping("/modify")
    public ResponseEntity<User> modifyUser(@RequestBody UserDto request){
        return null;
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable Integer id){
        return null;
    }
    @GetMapping("/events/{id}")
    public ResponseEntity<List<Event>> getEvents(@PathVariable Integer id){
        return null;
    }
    @PutMapping("/{id}/{event_id}")
    public ResponseEntity<Event> bookTicket(@PathVariable("id") Long id, @PathVariable("event_id") Long event_id){
        return null;
    }
    @DeleteMapping("/{id}/{event_id}")
    public ResponseEntity<Event> cancelTicket(@PathVariable("id") Long id, @PathVariable("event_id") Long event_id){
        return null;
    }
}