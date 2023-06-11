package com.nacu.sport.services.implementations;

import com.nacu.sport.api.dtos.SportFieldDTO;
import com.nacu.sport.api.mapper.SportFieldMapper;
import com.nacu.sport.exceptions.ResourceNotFoundException;
import com.nacu.sport.model.SportField;
import com.nacu.sport.repositories.SportFieldRepository;
import com.nacu.sport.services.SportFieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class SportFieldServiceImpl implements SportFieldService
{
    @Autowired
    private SportFieldRepository repository;

    @Autowired
    private SportFieldMapper mapper;

    @Override
    public List<SportFieldDTO> findAll()
    {
        return StreamSupport.stream(repository.findAll().spliterator(), true)
                .map(sportField -> mapper.entityToDto(sportField))
                .collect(Collectors.toList());
    }

    @Override
    public SportFieldDTO findById(String id)
    {
        return repository.findById(id)
                .map(sportField -> mapper.entityToDto(sportField))
                .orElseThrow(() -> new ResourceNotFoundException(id));
    }

    @Override
    public SportFieldDTO create(SportFieldDTO sportFieldDTO)
    {
        SportField sportField = mapper.dtoToEntity(sportFieldDTO);
        return mapper.entityToDto(repository.save(sportField));
    }

    @Override
    public SportFieldDTO update(SportFieldDTO sportFieldDTO)
    {
        SportField sportField = mapper.dtoToEntity(sportFieldDTO);
        return mapper.entityToDto(repository.save(sportField));
    }

    @Override
    public void deleteById(String id)
    {
        repository.deleteById(id);
    }
}
