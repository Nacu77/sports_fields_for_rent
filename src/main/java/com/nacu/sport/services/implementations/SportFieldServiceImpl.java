package com.nacu.sport.services.implementations;

import com.nacu.sport.api.dtos.SportFieldDTO;
import com.nacu.sport.api.mapper.SportFieldMapper;
import com.nacu.sport.model.SportField;
import com.nacu.sport.repositories.SportFieldRepository;
import com.nacu.sport.services.SportFieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SportFieldServiceImpl extends CrudServiceImpl<SportFieldDTO, SportField> implements SportFieldService
{
    @Autowired
    public SportFieldServiceImpl(SportFieldRepository repository, SportFieldMapper mapper)
    {
        super(repository, mapper);
    }
}
