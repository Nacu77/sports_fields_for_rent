package com.nacu.sport.api.dtos;

import com.nacu.sport.security.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO
{
    @NotBlank(message = "Empty username")
    @NotNull(message = "Username is null")
    private String username;

    @NotBlank(message = "Empty first name")
    @NotNull(message = "First name is null")
    private String firstName;

    @NotBlank(message = "Empty last name")
    @NotNull(message = "Last name is null")
    private String lastName;

    @NotBlank(message = "Empty email")
    @NotNull(message = "Email is null")
    @Email(message = "Email has invalid format")
    private String email;

    @NotNull(message = "Password is null")
    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&+_:;,.><=*-]).{8,}$",
            message = "Password needs at least eight characters, including at least one number and includes both lower and uppercase letters and special characters")
    private String password;

    @NotNull(message = "Role is null")
    private Role role;

    @NotBlank(message = "Empty phone number")
    @NotNull(message = "Phone number is null")
//    @Pattern(regexp = "^\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}$",
//            message = "Phone number format is invalid")
    private String phoneNumber;
}
