package com.nacu.sport.services.implementations;

import com.nacu.sport.api.mapper.BaseMapper;
import com.nacu.sport.exceptions.ResourceNotFoundException;
import com.nacu.sport.services.CrudService;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public abstract class CrudServiceImpl<DTO, DOC> implements CrudService<DTO, String>
{
    private final ElasticsearchRepository<DOC, String> repository;
    private final BaseMapper<DTO, DOC> mapper;

    public CrudServiceImpl(ElasticsearchRepository<DOC, String> repository, BaseMapper<DTO, DOC> mapper)
    {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<DTO> findAll()
    {
        return StreamSupport.stream(repository.findAll().spliterator(), true)
                .map(mapper::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public DTO findById(String id)
    {
        return repository.findById(id)
                .map(mapper::entityToDto)
                .orElseThrow(() -> new ResourceNotFoundException(id));
    }

    @Override
    public DTO create(DTO dto)
    {
        DOC doc = mapper.dtoToEntity(dto);
        return mapper.entityToDto(repository.save(doc));
    }

    @Override
    public DTO update(DTO dto)
    {
        DOC doc = mapper.dtoToEntity(dto);
        return mapper.entityToDto(repository.save(doc));
    }

    @Override
    public void deleteById(String id)
    {
        repository.deleteById(id);
    }
}
