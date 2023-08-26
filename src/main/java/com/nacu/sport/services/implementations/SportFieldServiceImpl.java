package com.nacu.sport.services.implementations;

import com.nacu.sport.api.dtos.SportFieldDTO;
import com.nacu.sport.api.mapper.SportFieldMapper;
import com.nacu.sport.api.requests.GetFilteredFieldsRequest;
import com.nacu.sport.model.SportField;
import com.nacu.sport.repositories.SportFieldRepository;
import com.nacu.sport.services.AppointmentService;
import com.nacu.sport.services.SportFieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SportFieldServiceImpl extends CrudServiceImpl<SportFieldDTO, SportField> implements SportFieldService
{
    private final SportFieldRepository repository;
    private final SportFieldMapper mapper;

    private final AppointmentService appointmentService;

    @Autowired
    public SportFieldServiceImpl(SportFieldRepository repository, SportFieldMapper mapper, AppointmentService appointmentService)
    {
        super(repository, mapper);
        this.repository = repository;
        this.mapper = mapper;
        this.appointmentService = appointmentService;
    }

    @Override
    public void deleteById(String id)
    {
        super.deleteById(id);
        appointmentService.deleteCurrentAppointmentsForSpecificField(id);
    }

    @Override
    public List<SportFieldDTO> findAllByUser(String username)
    {
        return repository.findAllByCreatedBy(username)
                .parallelStream()
                .map(mapper::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<SportFieldDTO> getFilteredFields(GetFilteredFieldsRequest getFilteredFieldsRequest)
    {
        return repository.findAllFiltered(getFilteredFieldsRequest.getMinPrice() != null ? getFilteredFieldsRequest.getMinPrice() : 0d,
                                          getFilteredFieldsRequest.getMaxPrice() != null ? getFilteredFieldsRequest.getMaxPrice() : 100000000d,
                                          getFilteredFieldsRequest.getCountry() != null ? getFilteredFieldsRequest.getCountry() : "*",
                                          getFilteredFieldsRequest.getCity() != null ? getFilteredFieldsRequest.getCity() : "*",
                                          getFilteredFieldsRequest.getName() != null ? getFilteredFieldsRequest.getName(): "*")
                .parallelStream()
                .map(mapper::entityToDto)
                .collect(Collectors.toList());
    }
}
