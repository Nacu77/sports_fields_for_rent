package com.nacu.sport.services.implementations;

import com.nacu.sport.api.dtos.SportFieldDTO;
import com.nacu.sport.api.mapper.SportFieldMapper;
import com.nacu.sport.api.requests.GetFilteredFieldsRequest;
import com.nacu.sport.model.SportField;
import com.nacu.sport.repositories.SportFieldRepository;
import com.nacu.sport.services.AppointmentService;
import com.nacu.sport.services.SportFieldService;
import com.nacu.sport.utils.OpenStreetMapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
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
    public SportFieldDTO create(SportFieldDTO sportFieldDTO)
    {
        GeoPoint geoPoint = OpenStreetMapUtils.getInstance().getCoordinates(
                sportFieldDTO.getAddress().getStreet() + ", " +
                        sportFieldDTO.getAddress().getNumber() + ", " +
                        sportFieldDTO.getAddress().getCity() + ", " +
                        sportFieldDTO.getAddress().getCountry()
        );

        if (geoPoint != null)
        {
            sportFieldDTO.getAddress().setLatitude(geoPoint.getLat());
            sportFieldDTO.getAddress().setLongitude(geoPoint.getLon());
        }

        return super.create(sportFieldDTO);
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
                                          getFilteredFieldsRequest.getName() != null ? getFilteredFieldsRequest.getName(): "*",
                                          getFilteredFieldsRequest.getType() != null ? getFilteredFieldsRequest.getType(): "*")
                .parallelStream()
                .map(mapper::entityToDto)
                .collect(Collectors.toList());
    }
}
