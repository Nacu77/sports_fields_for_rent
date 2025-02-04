package com.nacu.sport.controllers;

import com.nacu.sport.api.dtos.AppointmentPostDTO;
import com.nacu.sport.api.requests.GetFilteredPostsRequest;
import com.nacu.sport.services.AppointmentPostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/appointment-posts")
public class AppointmentPostController
{
    @Autowired
    private AppointmentPostService service;

    @PostMapping(value = "/")
    public ResponseEntity<AppointmentPostDTO> create(@RequestBody @Valid AppointmentPostDTO appointmentPostDTO)
    {
        return new ResponseEntity<>(service.create(appointmentPostDTO), HttpStatus.CREATED);
    }

    @PutMapping(value = "/")
    public ResponseEntity<AppointmentPostDTO> update(@RequestBody @Valid AppointmentPostDTO appointmentPostDTO)
    {
        return new ResponseEntity<>(service.update(appointmentPostDTO), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id)
    {
        service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/get-all-appointment-posts-with-free-slots")
    public ResponseEntity<List<AppointmentPostDTO>> getAllAppointmentPostsWithFreeSlots()
    {
        return new ResponseEntity<>(service.getAllAppointmentPostsWithFreeSlots(), HttpStatus.OK);
    }

    @PostMapping(value = "/get-filtered-appointment-posts-with-free-slots")
    public ResponseEntity<List<AppointmentPostDTO>> getFilteredAppointmentPostsWithFreeSlots(@RequestBody @Valid GetFilteredPostsRequest getFilteredPostsRequest)
    {
        return new ResponseEntity<>(service.getFilteredAppointmentPostsWithFreeSlots(getFilteredPostsRequest), HttpStatus.OK);
    }

    @GetMapping(value = "/get-appointment-posts-for-specific-user/{username}")
    public ResponseEntity<List<AppointmentPostDTO>> getAppointmentPostsForSpecificUser(@PathVariable String username)
    {
        return new ResponseEntity<>(service.getAppointmentPostsForSpecificUser(username), HttpStatus.OK);
    }

    @GetMapping(value = "/get-applied-appointment-posts-for-specific-user/{username}")
    public ResponseEntity<List<AppointmentPostDTO>> getAppliedAppointmentPostsForSpecificUser(@PathVariable String username)
    {
        return new ResponseEntity<>(service.getAppliedAppointmentPostsForSpecificUser(username), HttpStatus.OK);
    }
}
