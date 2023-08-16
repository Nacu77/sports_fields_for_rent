package com.nacu.sport.model;

import com.nacu.sport.security.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User
{
    @Id
    private String username;

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Role role;
    private String phoneNumber;
}
