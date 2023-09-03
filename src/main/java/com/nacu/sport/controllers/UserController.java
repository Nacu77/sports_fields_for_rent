package com.nacu.sport.controllers;

import com.nacu.sport.api.dtos.UserDTO;
import com.nacu.sport.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/auth")
public class UserController
{
    @Autowired
    private JwtEncoder jwtEncoder;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public String login(Authentication authentication) {
        Instant now = Instant.now();
        long expiry = 36000L;
        String scope = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiry))
                .subject(authentication.getName())
                .claim("scope", scope)
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody @Valid UserDTO userDTO)
    {
        userService.create(userDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getProfile(@PathVariable String id)
    {
        return new ResponseEntity<>(userService.getProfile(id), HttpStatus.OK);
    }

    @PutMapping(value = "/")
    public ResponseEntity<UserDTO> updateProfile(@RequestBody @Valid UserDTO userDTO)
    {
        return new ResponseEntity<>(userService.update(userDTO), HttpStatus.OK);
    }

    @PostMapping("/reset-password")
    public ResponseEntity<Void> resetPassword(@RequestBody String username)
    {
        userService.resetPassword(username);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
