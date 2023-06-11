package com.nacu.sport.api.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressDTO
{
    @NotBlank(message = "Invalid country: Empty country")
    @NotNull(message = "Invalid country: Country is null")
    private String country;

    @NotBlank(message = "Invalid city: Empty city")
    @NotNull(message = "Invalid city: City is null")
    private String city;

    @NotBlank(message = "Invalid street: Empty street")
    @NotNull(message = "Invalid street: Street is null")
    private String street;

    @NotNull(message = "Invalid number: Number is null")
    @Min(value = 1, message = "Invalid number: Number should be greater than 0")
    private Integer number;
}
