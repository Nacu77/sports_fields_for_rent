package com.nacu.sport.controllers;

import com.nacu.sport.api.dtos.SportFieldDTO;
import com.nacu.sport.api.requests.GetFilteredFieldsRequest;
import com.nacu.sport.services.SportFieldService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/fields")
public class SportFieldController
{
    @Autowired
    private SportFieldService service;

    @PostMapping(value = "/")
    public ResponseEntity<SportFieldDTO> create(@RequestBody @Valid SportFieldDTO sportFieldDTO)
    {
        return new ResponseEntity<>(service.create(sportFieldDTO), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<SportFieldDTO> findById(@PathVariable String id)
    {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @GetMapping(value = "/")
    public ResponseEntity<List<SportFieldDTO>> findAll()
    {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @PutMapping(value = "/")
    public ResponseEntity<SportFieldDTO> update(@RequestBody @Valid SportFieldDTO sportFieldDTO)
    {
        return new ResponseEntity<>(service.update(sportFieldDTO), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id)
    {
        service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/specific/{username}")
    public ResponseEntity<List<SportFieldDTO>> findAllByUser(@PathVariable String username)
    {
        return new ResponseEntity<>(service.findAllByUser(username), HttpStatus.OK);
    }

    @PostMapping(value = "/get-filtered-fields")
    public ResponseEntity<List<SportFieldDTO>> getFilteredFields(@RequestBody @Valid GetFilteredFieldsRequest getFilteredFieldsRequest)
    {
        return new ResponseEntity<>(service.getFilteredFields(getFilteredFieldsRequest), HttpStatus.OK);
    }
}
