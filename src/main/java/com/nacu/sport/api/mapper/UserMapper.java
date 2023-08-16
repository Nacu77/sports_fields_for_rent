package com.nacu.sport.api.mapper;

import com.nacu.sport.api.dtos.UserDTO;
import com.nacu.sport.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends BaseMapper<UserDTO, User>
{
}
