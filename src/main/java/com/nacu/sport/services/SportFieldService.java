package com.nacu.sport.services;

import com.nacu.sport.api.dtos.SportFieldDTO;

import java.util.List;

public interface SportFieldService extends CrudService<SportFieldDTO, String>
{
    List<SportFieldDTO> findAllByUser(String username);
}
