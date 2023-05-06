package com.nacu.sport.controllers;

import com.nacu.sport.model.SportField;
import com.nacu.sport.services.SportFieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/fields")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SportFieldController
{
    private final SportFieldService service;

    @Autowired
    public SportFieldController(SportFieldService service)
    {
        this.service = service;
    }

    @PostMapping(value = "/")
    public SportField create(@RequestBody SportField sportField)
    {
        return service.create(sportField);
    }

    @GetMapping(value = "/{id}")
    public SportField findById(@PathVariable String id)
    {
        return service.findById(id);
    }

    @GetMapping(value = "/")
    public List<SportField> findAll()
    {
        return service.findAll();
    }

    @PutMapping(value = "/")
    public SportField update(@RequestBody SportField sportField)
    {
        return service.update(sportField);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable String id)
    {
        service.deleteById(id);
    }
}
