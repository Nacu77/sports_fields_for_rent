package com.nacu.sport.services;

import com.nacu.sport.api.dtos.UserDTO;

public interface UserService extends CrudService<UserDTO, String>
{
    UserDTO getProfile(String id);
}
