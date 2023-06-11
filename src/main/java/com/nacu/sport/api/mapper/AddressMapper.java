package com.nacu.sport.api.mapper;

import com.nacu.sport.api.dtos.AddressDTO;
import com.nacu.sport.model.Address;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper
{
    AddressDTO entityToDto(Address address);
    Address dtoToEntity(AddressDTO addressDTO);
}
