package com.nacu.sport.services.implementations;

import com.nacu.sport.api.dtos.UserDTO;
import com.nacu.sport.api.mapper.UserMapper;
import com.nacu.sport.model.User;
import com.nacu.sport.repositories.UserRepository;
import com.nacu.sport.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends CrudServiceImpl<UserDTO, User> implements UserService
{
    @Autowired
    public UserServiceImpl(UserRepository repository, UserMapper mapper)
    {
        super(repository, mapper);
    }
}
