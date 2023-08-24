package com.nacu.sport.controllers;

import com.nacu.sport.api.dtos.AppointmentPostDTO;
import com.nacu.sport.services.AppointmentPostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
