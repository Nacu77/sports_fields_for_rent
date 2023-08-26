package com.nacu.sport.services;

import com.nacu.sport.api.dtos.SportFieldDTO;
import com.nacu.sport.api.requests.GetFilteredFieldsRequest;

import java.util.List;

public interface SportFieldService extends CrudService<SportFieldDTO, String>
{
    List<SportFieldDTO> findAllByUser(String username);
    List<SportFieldDTO> getFilteredFields(GetFilteredFieldsRequest getFilteredFieldsRequest);
}
