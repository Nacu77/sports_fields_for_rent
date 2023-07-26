package com.nacu.sport.controllers;

import com.nacu.sport.api.dtos.AppointmentDTO;
import com.nacu.sport.api.requests.GetAppointmentsForSpecificDateRequest;
import com.nacu.sport.services.AppointmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/appointments")
public class AppointmentController
{
    @Autowired
    private AppointmentService service;

    @PostMapping(value = "/")
    public ResponseEntity<AppointmentDTO> create(@RequestBody @Valid AppointmentDTO appointmentDTO)
    {
        return new ResponseEntity<>(service.create(appointmentDTO), HttpStatus.CREATED);
    }

    @PostMapping(value = "/get-appointments-for-specific-date")
    public ResponseEntity<List<AppointmentDTO>> getAppointmentsForSpecificDate(@RequestBody GetAppointmentsForSpecificDateRequest request)
    {
        return new ResponseEntity<>(service.getAppointmentsForSpecificDate(request), HttpStatus.OK);
    }
}
