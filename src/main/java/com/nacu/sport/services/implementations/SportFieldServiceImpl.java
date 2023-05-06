package com.nacu.sport.services.implementations;

import com.nacu.sport.exceptions.ResourceNotFoundException;
import com.nacu.sport.model.SportField;
import com.nacu.sport.repositories.SportFieldRepository;
import com.nacu.sport.services.SportFieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SportFieldServiceImpl implements SportFieldService
{
    private final SportFieldRepository repository;

    @Autowired
    public SportFieldServiceImpl(SportFieldRepository repository)
    {
        this.repository = repository;
    }

    @Override
    public List<SportField> findAll()
    {
        List<SportField> fields = new ArrayList<>();
        repository.findAll().forEach(fields::add);
        return fields;
    }

    @Override
    public SportField findById(String id)
    {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    }

    @Override
    public SportField create(SportField sportField)
    {
        return repository.save(sportField);
    }

    @Override
    public SportField update(SportField sportField)
    {
        return repository.save(sportField);
    }

    @Override
    public void deleteById(String id)
    {
        repository.deleteById(id);
    }
}
