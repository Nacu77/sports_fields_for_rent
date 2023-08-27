package com.nacu.sport.services.implementations;

import com.nacu.sport.api.dtos.UserDTO;
import com.nacu.sport.api.mapper.UserMapper;
import com.nacu.sport.model.User;
import com.nacu.sport.repositories.UserRepository;
import com.nacu.sport.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends CrudServiceImpl<UserDTO, User> implements UserService
{
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository repository, UserMapper mapper, PasswordEncoder passwordEncoder)
    {
        super(repository, mapper);
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDTO create(UserDTO userDTO)
    {
        String hashedPassword = passwordEncoder.encode(userDTO.getPassword());
        userDTO.setPassword(hashedPassword);
        return super.create(userDTO);
    }

    @Override
    public UserDTO update(UserDTO userDTO)
    {
        User user = repository.findByUsername(userDTO.getUsername());
        if (!userDTO.getPassword().equals(user.getPassword()))
        {
            String hashedPassword = passwordEncoder.encode(userDTO.getPassword());
            userDTO.setPassword(hashedPassword);
        }
        return super.update(userDTO);
    }

    @Override
    public UserDTO getProfile(String id)
    {
        return findById(id);
    }
}
