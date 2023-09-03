package com.nacu.sport.bootstrap;

import com.nacu.sport.model.User;
import com.nacu.sport.repositories.UserRepository;
import com.nacu.sport.security.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner
{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception
    {
        loadAdmin();
    }

    private void loadAdmin()
    {
        if (userRepository.existsById("admin"))
        {
            userRepository.deleteById("admin");
        }

        User user = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("admin"))
                .firstName("Admin")
                .lastName("")
                .role(Role.ADMIN)
                .build();
        userRepository.save(user);
    }
}
